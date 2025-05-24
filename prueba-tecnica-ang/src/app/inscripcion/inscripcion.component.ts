import { Component, OnInit, Pipe, TemplateRef, ViewChild } from '@angular/core';
import { Inscripcion } from '../models/Inscripcion';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { EstudianteServiceService } from '../services/estudiante-service.service';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { InscripcionService } from '../services/inscripcion.service';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatSortModule } from '@angular/material/sort';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-inscripcion',
  imports: [MatDialogModule,
    MatTableModule,
    MatButtonModule,
    MatIconModule,
    MatInputModule,
    MatFormFieldModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatTooltipModule,
    MatSortModule,
    CommonModule,
    ReactiveFormsModule
  ],
  templateUrl: './inscripcion.component.html',
  styleUrl: './inscripcion.component.css'
})
export class InscripcionComponent implements OnInit{

  inscripciones: Inscripcion[] = [];
  formNuevaInscripcion!: FormGroup;
  editando = false;
  inscripcionEditando: Inscripcion | null = null;
  inscripcionSeleccionada: Inscripcion | null = null;

  displayedColumns: string[] = ['idInscripcion', 'idEstudiante', 'nombreEstudiante', 'idCurso', 'nombreCurso', 'fechaInscripcion', 'acciones'];

  @ViewChild('modalNuevo') modalNuevo!: TemplateRef<any>;
  @ViewChild('modalEliminar') modalEliminar!: TemplateRef<any>;

  constructor(
    private inscripcionService: InscripcionService,
    private fb: FormBuilder,
    private dialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.getListadoInscripciones();
    this.iniciarFormulario();
  }

  getListadoInscripciones(): void {
  this.inscripcionService.getListadoInscripciones().subscribe({
    next: (data: any[]) => {
      this.inscripciones = data.map(item => ({
        idInscripcion: item.id,  
        idEstudiante: item.idEstudiante,
        nombreEstudiante: item.nombreEstudiante,
        idCurso: item.idCurso,
        nombreCurso: item.nombreCurso,
        fechaInscripcion: new Date(item.fechaInscripcion)
      }));
    },
    error: (err) => console.error('Error al obtener inscripciones', err)
  });
}
  iniciarFormulario(): void {
    this.formNuevaInscripcion = this.fb.group({
      idInscripcion: [0],
      idEstudiante: ['', Validators.required],
      nombreEstudiante: [''],
      idCurso: ['', Validators.required],
      nombreCurso: [''],
      fechaInscripcion: ['', Validators.required]
    });
  }

  campoInvalido(campo: string): boolean {
    const control = this.formNuevaInscripcion.get(campo);
    return !!(control && control.invalid && (control.dirty || control.touched));
  }

  abrirModalNuevo(): void {
    this.editando = false;
    this.inscripcionEditando = null;
    this.formNuevaInscripcion.reset({idInscripcion: 0, idEstudiante: '', nombreEstudiante: '', idCurso: '', nombreCurso: '', fechaInscripcion: ''});
    this.dialog.open(this.modalNuevo, { width: '400px' });
  }

  editarInscripcion(inscripcion: Inscripcion): void {
    this.editando = true;
    this.inscripcionEditando = inscripcion;
    this.formNuevaInscripcion.patchValue({
      idInscripcion: inscripcion.idInscripcion,
      idEstudiante: inscripcion.idEstudiante,
      nombreEstudiante: inscripcion.nombreEstudiante,
      idCurso: inscripcion.idCurso,
      nombreCurso: inscripcion.nombreCurso,
      fechaInscripcion: inscripcion.fechaInscripcion
    });
    this.dialog.open(this.modalNuevo, { width: '400px' });
  }

  guardarNuevaInscripcion(): void {
    if (this.formNuevaInscripcion.invalid) return;
    
    const datos = this.formNuevaInscripcion.value as Inscripcion;

    if (this.editando && this.inscripcionEditando) {
      datos.idInscripcion = this.inscripcionEditando.idInscripcion;
      console.log(datos);
      this.inscripcionService.putInscripcion(datos.idInscripcion, datos).subscribe({
        next: () => {
          this.getListadoInscripciones();
          this.dialog.closeAll();
        },
        error: (err) => console.error('Error actualizando inscripción', err)
      });
    } else {
      this.inscripcionService.postInscripcion(datos).subscribe({
        next: () => {
          this.getListadoInscripciones();
          this.dialog.closeAll();
        },
        error: (err) => console.error('Error guardando inscripción', err)
      });
    }
  }

  abrirModalEliminar(inscripcion: Inscripcion): void {
    this.inscripcionSeleccionada = inscripcion;
    this.dialog.open(this.modalEliminar, { width: '300px' });
  }

  eliminarInscripcion(): void {
    if (!this.inscripcionSeleccionada) return;
    this.inscripcionService.deleteInscripcion(this.inscripcionSeleccionada.idInscripcion).subscribe({
      next: () => {
        this.getListadoInscripciones();
        this.dialog.closeAll();
      },
      error: (err) => console.error('Error eliminando inscripción', err)
    });
  }

  cerrarModalNuevo(): void {
    this.dialog.closeAll();
  }

  cerrarModalEliminar(): void {
    this.dialog.closeAll();
  }
}