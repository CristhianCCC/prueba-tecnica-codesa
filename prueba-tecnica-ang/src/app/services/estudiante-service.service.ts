import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Estudiante } from '../models/Estudiante';

@Injectable({
  providedIn: 'root'
})
export class EstudianteServiceService {

  private readonly estudianteApiUrl = 'http://localhost:8080/api/estudiante';

  constructor(private http: HttpClient) {}

  getListadoEstudiantes(): Observable<Estudiante[]> {
    return this.http.get<Estudiante[]>(this.estudianteApiUrl)
      .pipe(
        catchError(this.manejarError)
      );
  }

  getEstudianteById(id: number): Observable<Estudiante> {
    return this.http.get<Estudiante>(`${this.estudianteApiUrl}/${id}`)
      .pipe(
        catchError(this.manejarError)
      );
  }

  postEstudiante(estudiante: Estudiante): Observable<Estudiante> {
    return this.http.post<Estudiante>(this.estudianteApiUrl, estudiante)
      .pipe(
        catchError(this.manejarError)
      );
  }

  putEstudiante(estudiante: Estudiante): Observable<Estudiante> {
    return this.http.put<Estudiante>(`${this.estudianteApiUrl}/${estudiante.idPersona}`, estudiante)
      .pipe(
        catchError(this.manejarError)
      );
  }

  deleteEstudiante(id: number): Observable<void> {
    return this.http.delete<void>(`${this.estudianteApiUrl}/${id}`)
      .pipe(
        catchError(this.manejarError)
      );
  }

  private manejarError(error: HttpErrorResponse) {
    let mensajeError = '';
    if (error.error instanceof ErrorEvent) {
      // Error del lado cliente (network error, etc.)
      mensajeError = `Error de cliente: ${error.error.message}`;
    } else {
      // Error del lado servidor
      mensajeError = `Error del servidor: Código ${error.status}, ` +
                    `Mensaje: ${error.message || error.error?.mensaje || 'No disponible'}`;
    }
    console.error(mensajeError);
    // Puedes usar alert, toast o cualquier notificación visual en el componente
    return throwError(() => new Error(mensajeError));
  }
}