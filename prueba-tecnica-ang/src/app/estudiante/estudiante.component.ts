import { CommonModule } from '@angular/common';
import { Component, OnInit, ViewChild, TemplateRef } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators, ValidatorFn, AbstractControl, ValidationErrors } from '@angular/forms';
import { Estudiante } from '../models/Estudiante';
import { EstudianteServiceService } from '../services/estudiante-service.service';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatSortModule } from '@angular/material/sort';

@Component({
  selector: 'app-estudiantes',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatDialogModule,
    MatTableModule,
    MatButtonModule,
    MatIconModule,
    MatInputModule,
    MatFormFieldModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatTooltipModule,
    MatSortModule
  ],
  templateUrl: './estudiante.component.html',
  styleUrls: ['./estudiante.component.css']
})
export class EstudiantesComponent implements OnInit {

  estudiantes: Estudiante[] = [];
  formNuevoEstudiante!: FormGroup;
  editando: boolean = false;
  estudianteEditando: Estudiante | null = null;
  estudianteSeleccionado: Estudiante | null = null;

  columnas = ['idPersona', 'nombre', 'apellido', 'fechaNacimiento', 'email', 'telefono', 'numeroMatricula', 'grado', 'acciones'];

  @ViewChild('modalNuevo') modalNuevo!: TemplateRef<any>;
  @ViewChild('modalEliminar') modalEliminar!: TemplateRef<any>;

  constructor(
    private estudianteService: EstudianteServiceService,
    private fb: FormBuilder,
    private dialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.getListadoEstudiantes();
    this.inicializarFormulario();
  }

  //cargando listado de estudiantes
  getListadoEstudiantes(): void {
    this.estudianteService.getListadoEstudiantes().subscribe({
      next: (data: Estudiante[]) => {
        this.estudiantes = data;
      },
      error: (err) => {
        console.error('Error al obtener estudiantes', err);
      }
    });
  }

  //validaciones de formulario
  inicializarFormulario(): void {
    this.formNuevoEstudiante = this.fb.group({
      nombre: ['', Validators.required],
      apellido: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      telefono: ['', [Validators.required, Validators.pattern(/^\d{10}$/)]],
      fechaNacimiento: ['', [Validators.required, this.edadMinimaValidator(4)]],
      numeroMatricula: ['', Validators.required],
      grado: ['', Validators.required]
    });
  }

  campoInvalido(campo: string): boolean {
    const control = this.formNuevoEstudiante.get(campo);
    return !!(control && control.invalid && (control.dirty || control.touched));
  }

  abrirModalNuevo(): void {
    this.editando = false;
    this.estudianteEditando = null;
    this.formNuevoEstudiante.reset();
    this.dialog.open(this.modalNuevo, { width: '400px' });
  }

  editarEstudiante(estudiante: Estudiante): void {
    this.editando = true;
    this.estudianteEditando = estudiante;
    this.formNuevoEstudiante.patchValue({
      nombre: estudiante.nombre,
      apellido: estudiante.apellido,
      email: estudiante.email,
      telefono: estudiante.telefono,
      fechaNacimiento: new Date(estudiante.fechaNacimiento),
      numeroMatricula: estudiante.numeroMatricula,
      grado: estudiante.grado
    });
    this.dialog.open(this.modalNuevo, { width: '600px' });
  }

  guardarNuevoEstudiante(): void {
    if (this.formNuevoEstudiante.invalid) return;

    const datos = this.formNuevoEstudiante.value as Estudiante;

    if (this.editando && this.estudianteEditando) {
      datos.idPersona = this.estudianteEditando.idPersona;
      this.estudianteService.putEstudiante(datos).subscribe({
        next: () => {
          this.getListadoEstudiantes();
          this.dialog.closeAll();
        },
        error: (err) => console.error('Error actualizando estudiante', err)
      });
    } else {
      this.estudianteService.postEstudiante(datos).subscribe({
        next: () => {
          this.getListadoEstudiantes();
          this.dialog.closeAll();
        },
        error: (err) => console.error('Error guardando estudiante', err)
      });
    }
  }

  abrirModalEliminar(estudiante: Estudiante): void {
    this.estudianteSeleccionado = estudiante;
    this.dialog.open(this.modalEliminar, { width: '300px' });
  }

  eliminarEstudiante(): void {
    if (!this.estudianteSeleccionado) return;
    this.estudianteService.deleteEstudiante(this.estudianteSeleccionado.idPersona).subscribe({
      next: () => {
        this.getListadoEstudiantes();
        this.dialog.closeAll();
      },
      error: (err) => console.error('Error eliminando estudiante', err)
    });
  }

  cerrarModalNuevo(): void {
    this.dialog.closeAll();
  }

  cerrarModalEliminar(): void {
    this.dialog.closeAll();
  }

  // validando edad minima, si el estudiante es menor a 4 años muestra un error
  edadMinimaValidator(minEdad: number): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {
      const fecha = control.value;
      if (!fecha) return null;
      const hoy = new Date();
      const fechaNacimiento = new Date(fecha);
      const edad = hoy.getFullYear() - fechaNacimiento.getFullYear();
      const mes = hoy.getMonth() - fechaNacimiento.getMonth();
      if (mes < 0 || (mes === 0 && hoy.getDate() < fechaNacimiento.getDate())) {
        // Resta un año si no ha cumplido aún este año
        return edad - 1 >= minEdad ? null : { edadMinima: true };
      }
      return edad >= minEdad ? null : { edadMinima: true };
    };
  }

}
