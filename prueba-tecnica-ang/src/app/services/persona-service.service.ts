import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Persona } from '../models/Persona';

@Injectable({
  providedIn: 'root'
})
export class PersonaServiceService {

  private personaApiUrl = 'http://localhost:8080/api/persona';

  constructor(private http: HttpClient) { }

  getListadoPersonas(): Observable<Persona[]> {
    return this.http.get<Persona[]>(this.personaApiUrl).pipe(
      catchError(error => {
        console.error('Error al obtener personas', error);
        alert('Error al obtener la lista de personas');
        return throwError(() => error);
      })
    );
  }

  getPersonaById(id: number): Observable<Persona> {
    return this.http.get<Persona>(`${this.personaApiUrl}/${id}`).pipe(
      catchError(error => {
        console.error('Error al obtener persona', error);
        alert('Error al obtener los datos de la persona');
        return throwError(() => error);
      })
    );
  }

  postPersona(persona: Persona): Observable<Persona> {
    return this.http.post<Persona>(this.personaApiUrl, persona).pipe(
      catchError(error => {
        console.error('Error al crear persona', error);
        alert('No se pudo crear la persona');
        return throwError(() => error);
      })
    );
  }

  putPersona(persona: Persona): Observable<any> {
    return this.http.put(`${this.personaApiUrl}/${persona.idPersona}`, persona).pipe(
      catchError(error => {
        console.error('Error al actualizar persona', error);
        alert('No se pudo actualizar la persona');
        return throwError(() => error);
      })
    );
  }

  deletePersona(id: number): Observable<void> {
    return this.http.delete<void>(`${this.personaApiUrl}/${id}`).pipe(
      catchError(error => {
        console.error('Error al eliminar persona', error);
        alert('No se pudo eliminar la persona con id: ' + id);
        return throwError(() => error);
      })
    );
  }
}
