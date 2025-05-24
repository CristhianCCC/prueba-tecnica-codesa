import { Routes } from '@angular/router';
import { PersonaComponent } from './persona/persona.component';
import { EstudiantesComponent } from './estudiante/estudiante.component';
import { ProfesoresComponent } from './profesores/profesores.component';
import { CursosComponent } from './curso/curso.component';
import { InscripcionService } from './services/inscripcion.service';
import { InscripcionComponent } from './inscripcion/inscripcion.component';


export const routes: Routes = [
    {path: 'personas', component: PersonaComponent},
    {path: 'estudiantes', component: EstudiantesComponent},
    {path: 'profesores', component: ProfesoresComponent},
    {path: 'cursos', component: CursosComponent},
    {path: 'inscripciones', component: InscripcionComponent}
];

