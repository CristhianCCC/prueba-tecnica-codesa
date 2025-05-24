import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Inscripcion } from '../models/Inscripcion';

@Injectable({
  providedIn: 'root'
})
export class InscripcionService {

  private readonly inscripcionApiUrl = 'http://localhost:8080/api/inscripcion';

  constructor(private http: HttpClient) {}

  getListadoInscripciones(): Observable<Inscripcion[]> {
    return this.http.get<Inscripcion[]>(this.inscripcionApiUrl);
  }

  getInscripcionById(id: number): Observable<Inscripcion> {
    return this.http.get<Inscripcion>(`${this.inscripcionApiUrl}/${id}`);
  }

  postInscripcion(inscripcion: Inscripcion): Observable<Inscripcion> {
    return this.http.post<Inscripcion>(this.inscripcionApiUrl, inscripcion).pipe(
      catchError((error: HttpErrorResponse) => {
        if (error.status === 400 || error.status === 404) {
          alert('Error: Es probable que el id del estudiante o del curso no exista. Por favor, verificar.');
        } else {
          alert('Ocurrió un error inesperado. Por favor, inténtelo más tarde.');
        }
        return throwError(() => error);
      })
    );
  }

  putInscripcion(id: number, inscripcion: Inscripcion): Observable<Inscripcion> {
    return this.http.put<Inscripcion>(`${this.inscripcionApiUrl}/${id}`, inscripcion).pipe(
      catchError((error: HttpErrorResponse) => {
        if (error.status === 400 || error.status === 404) {
          alert('Error: Es probable que el id del estudiante o del curso no exista. Por favor, verificar.');
        } else {
          alert('Ocurrió un error inesperado. Por favor, inténtelo más tarde.');
        }
        return throwError(() => error);
      })
    );
  }

  deleteInscripcion(id: number): Observable<void> {
    return this.http.delete<void>(`${this.inscripcionApiUrl}/${id}`);
  }
}
