import { Component, OnInit, ViewChild, TemplateRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AbstractControl, FormBuilder, FormGroup, ReactiveFormsModule, ValidationErrors, ValidatorFn, Validators } from '@angular/forms';
import { Profesor } from '../models/Profesor';

import { MatTableModule } from '@angular/material/table';
import { MatSortModule, MatSort } from '@angular/material/sort';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { ProfesorServiceService } from '../services/profesor-service.service';

@Component({
  selector: 'app-profesores',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatTableModule,
    MatSortModule,
    MatDialogModule,
    MatButtonModule,
    MatIconModule,
    MatTooltipModule,
    MatFormFieldModule,
    MatInputModule,
    MatDatepickerModule,
    MatNativeDateModule,
  ],
  templateUrl: './profesores.component.html',
  styleUrls: ['./profesores.component.css']
})
export class ProfesoresComponent implements OnInit {

  profesores: Profesor[] = [];
  columnas = ['idPersona', 'nombre', 'apellido', 'fechaNacimiento', 'email', 'telefono', 'fechaContratacion', 'especialidad', 'acciones'];

  @ViewChild(MatSort) sort!: MatSort;
  @ViewChild('modalNuevoEditar') modalNuevoEditar!: TemplateRef<any>;
  @ViewChild('modalEliminar') modalEliminar!: TemplateRef<any>;

  formProfesor!: FormGroup;
  editando = false;
  profesorSeleccionado!: Profesor | null;

  constructor(
    private profesorService: ProfesorServiceService,
    private fb: FormBuilder,
    private dialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.getListadoProfesores();
    this.initForm();
  }

  getListadoProfesores() {
    this.profesorService.getListadoProfesores().subscribe(data => {
      this.profesores = data;
    });
  }

  initForm() {
    this.formProfesor = this.fb.group({
      idPersona: [null],
      nombre: ['', Validators.required],
      apellido: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      telefono: ['', [Validators.required, Validators.pattern(/^\d{10}$/)]],
      fechaContratacion: ['', Validators.required],
      fechaNacimiento: ['', [Validators.required, this.edadMinimaValidator(18)]],
      especialidad: ['', Validators.required],
    });
  }

  campoInvalido(campo: string): boolean {
    const control = this.formProfesor.get(campo);
    return !!(control && control.invalid && (control.dirty || control.touched));
  }

  abrirModalNuevo() {
    this.editando = false;
    this.profesorSeleccionado = null;
    this.formProfesor.reset();
    this.dialog.open(this.modalNuevoEditar);
  }

  abrirModalEditar(profesor: Profesor) {
    this.editando = true;
    this.profesorSeleccionado = profesor;
    this.formProfesor.patchValue({
      idPersona: profesor.idPersona,
      nombre: profesor.nombre,
      apellido: profesor.apellido,
      fechaNacimiento: new Date(profesor.fechaNacimiento),
      email: profesor.email,
      telefono: profesor.telefono,
      fechaContratacion: new Date(profesor.fechaContratacion),
      especialidad: profesor.especialidad,
    });
    this.dialog.open(this.modalNuevoEditar);
  }

  guardarProfesor() {
    if (this.formProfesor.invalid) return;

    const prof: Profesor = this.formProfesor.value;

    if (this.editando) {
      this.profesorService.putProfesor(prof).subscribe(() => {
        this.getListadoProfesores();
        this.cerrarModalNuevoEditar();
      });
    } else {
      this.profesorService.postProfesor(prof).subscribe(() => {
        this.getListadoProfesores();
        this.cerrarModalNuevoEditar();
      });
    }
  }

  cerrarModalNuevoEditar() {
    this.dialog.closeAll();
  }

  abrirModalEliminar(profesor: Profesor) {
    this.profesorSeleccionado = profesor;
    this.dialog.open(this.modalEliminar);
  }

  eliminarProfesor() {
    if (!this.profesorSeleccionado) return;

    this.profesorService.deleteProfesor(this.profesorSeleccionado.idPersona).subscribe(() => {
      this.getListadoProfesores();
      this.cerrarModalEliminar();
    });
  }

  cerrarModalEliminar() {
    this.dialog.closeAll();
  }

// validando edad minima, si el estudiante es menor a 18 años muestra un error
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
