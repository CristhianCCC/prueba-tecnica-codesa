import { Component, OnInit, ViewChild, TemplateRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { PersonaServiceService } from '../services/persona-service.service';
import { Persona } from '../models/Persona';
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

@Component({
  selector: 'app-persona',
  standalone: true,
  imports: [
    CommonModule, ReactiveFormsModule,
    MatTableModule, MatSortModule,
    MatDialogModule, MatButtonModule,
    MatIconModule, MatTooltipModule,
    MatFormFieldModule, MatInputModule,
    MatDatepickerModule, MatNativeDateModule
  ],
  templateUrl: './persona.component.html',
  styleUrls: ['./persona.component.css']
})
export class PersonaComponent implements OnInit {
  @ViewChild(MatSort) sort!: MatSort;
  @ViewChild('modalEditar') modalEditar!: TemplateRef<any>;
  @ViewChild('modalEliminar') modalEliminar!: TemplateRef<any>;

  personas: Persona[] = [];
  personaSeleccionada: Persona | null = null;
  editarForm!: FormGroup;
  columnas = ['idPersona', 'nombre', 'apellido', 'fechaNacimiento', 'email', 'telefono', 'acciones'];

  constructor(
    private personaService: PersonaServiceService,
    private formBuilder: FormBuilder,
    private dialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.getListadoPersonas();

    this.editarForm = this.formBuilder.group({
      nombre: ['', Validators.required],
      apellido: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      telefono: ['', Validators.required],
      fechaNacimiento: ['', Validators.required],
    });
  }

  getListadoPersonas(): void {
    this.personaService.getListadoPersonas().subscribe({
      next: (data) => {
        this.personas = data;
        setTimeout(() => {
          if (this.sort) {
          }
        });
      },
      error: (err) => console.error('Error al obtener personas', err)
    });
  }


  //para editar persona
  abrirModalEditar(persona: Persona): void {
    this.personaSeleccionada = persona;
    this.editarForm.patchValue({
      nombre: persona.nombre,
      apellido: persona.apellido,
      email: persona.email,
      telefono: persona.telefono,
      fechaNacimiento: persona.fechaNacimiento ? new Date(persona.fechaNacimiento) : '',
    });
    this.dialog.open(this.modalEditar, { width: '400px' });
  }

    //para cerrar la ventana modal
  cerrarModalEditar(): void {
    this.dialog.closeAll();
    this.personaSeleccionada = null;
    this.editarForm.reset();
  }

  guardarCambios(): void {
    if (this.editarForm.invalid) {
      this.editarForm.markAllAsTouched();
      return;
    }
    if (!this.personaSeleccionada) return;

    const personaActualizada: Persona = {
      ...this.personaSeleccionada,
      ...this.editarForm.value
    };

    this.personaService.putPersona(personaActualizada).subscribe({
      next: () => {
        alert('Persona actualizada correctamente.');
        this.getListadoPersonas();
        this.cerrarModalEditar();
      },
      error: (err) => alert('Error al actualizar la persona: ' + err.message)
    });
  }

  abrirModalEliminar(persona: Persona): void {
    this.personaSeleccionada = persona;
    this.dialog.open(this.modalEliminar, { width: '350px' });
  }

  cerrarModalEliminar(): void {
    this.dialog.closeAll();
    this.personaSeleccionada = null;
  }

  eliminarPersona(): void {
    if (!this.personaSeleccionada) return;
    this.personaService.deletePersona(this.personaSeleccionada.idPersona).subscribe({
      next: () => {
        alert('Persona eliminada correctamente.');
        this.getListadoPersonas();
        this.cerrarModalEliminar();
      },
      error: (err) => alert('Error al eliminar la persona: ' + err.message)
    });
  }
}
