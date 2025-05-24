import { CommonModule } from '@angular/common';
import { Component, OnInit, ViewChild, TemplateRef } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Curso } from '../models/Curso';
import { CursoService } from '../services/curso.service';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatSortModule } from '@angular/material/sort';

@Component({
  selector: 'app-cursos',
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
    MatTooltipModule,
    MatSortModule
  ],
  templateUrl: './curso.component.html',
  styleUrls: ['./curso.component.css']
})
export class CursosComponent implements OnInit {

  cursos: Curso[] = [];
  formNuevoCurso!: FormGroup;
  editando: boolean = false;
  cursoEditando: Curso | null = null;
  cursoSeleccionado: Curso | null = null;

  columnas = ['idCurso', 'nombre', 'descripcion', 'creditos', 'idProfesor', 'nombreProfesor', 'acciones'];

  @ViewChild('modalNuevo') modalNuevo!: TemplateRef<any>;
  @ViewChild('modalEliminar') modalEliminar!: TemplateRef<any>;

  constructor(
    private cursoService: CursoService,
    private fb: FormBuilder,
    private dialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.getListadoCursos();
    this.inicializarFormulario();
  }

  getListadoCursos(): void {
    this.cursoService.getAllCursos().subscribe({
      next: (data: Curso[]) => {
        this.cursos = data;
      },
      error: (err) => {
        console.error('Error al obtener cursos', err);
      }
    });
  }

  inicializarFormulario(): void {
    this.formNuevoCurso = this.fb.group({
      idCurso: [0],
      nombre: ['', Validators.required],
      descripcion: ['', Validators.required],
      creditos: [0, [Validators.required, Validators.min(1)]],
      idProfesor: [null, Validators.required],
      nombreProfesor: ['']
    });
  }

  campoInvalido(campo: string): boolean {
    const control = this.formNuevoCurso.get(campo);
    return !!(control && control.invalid && (control.dirty || control.touched));
  }

  abrirModalNuevo(): void {
    this.editando = false;
    this.cursoEditando = null;
    this.formNuevoCurso.reset({idCurso: 0, nombreProfesor: ''});
    this.dialog.open(this.modalNuevo, { width: '400px' });
  }

  editarCurso(curso: Curso): void {
    this.editando = true;
    this.cursoEditando = curso;
    this.formNuevoCurso.patchValue({
      idCurso: curso.idCurso,
      nombre: curso.nombre,
      descripcion: curso.descripcion,
      creditos: curso.creditos,
      idProfesor: curso.idProfesor,
      nombreProfesor: curso.nombreProfesor
    });
    this.dialog.open(this.modalNuevo, { width: '400px' });
  }

  guardarNuevoCurso(): void {
    if (this.formNuevoCurso.invalid) return;

    const datos = this.formNuevoCurso.value as Curso;

    if (this.editando && this.cursoEditando) {
      this.cursoService.putCurso(datos.idCurso, datos).subscribe({
        next: () => {
          this.getListadoCursos();
          this.dialog.closeAll();
        },
        error: (err) => console.error('Error actualizando curso', err)
      });
    }
    else {
      this.cursoService.postCurso(datos).subscribe({
        next: () => {
          this.getListadoCursos();
          this.dialog.closeAll();
        },
        error: (err) => console.error('Error guardando curso', err)
      });
    }
  }

  abrirModalEliminar(curso: Curso): void {
    this.cursoSeleccionado = curso;
    this.dialog.open(this.modalEliminar, { width: '300px' });
  }

  eliminarCurso(): void {
    if (!this.cursoSeleccionado) return;
    this.cursoService.eliminarCurso(this.cursoSeleccionado.idCurso).subscribe({
      next: () => {
        this.getListadoCursos();
        this.dialog.closeAll();
      },
      error: (err) => console.error('Error eliminando curso', err)
    });
  }

  cerrarModalNuevo(): void {
    this.dialog.closeAll();
  }

  cerrarModalEliminar(): void {
    this.dialog.closeAll();
  }
}
