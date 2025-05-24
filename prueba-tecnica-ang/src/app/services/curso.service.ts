import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Curso } from '../models/Curso';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class CursoService {

  private readonly cursoApiUrl = 'http://localhost:8080/api/curso';

  constructor(private http: HttpClient) {}

  getAllCursos(): Observable<Curso[]> {
    return this.http.get<Curso[]>(this.cursoApiUrl);
  }

  getCursoById(id: number): Observable<Curso> {
    return this.http.get<Curso>(`${this.cursoApiUrl}/${id}`);
  }

  postCurso(curso: Curso): Observable<Curso> {
    return this.http.post<Curso>(this.cursoApiUrl, curso).pipe(
      catchError((error: HttpErrorResponse) => {
        if (error.status === 500 || error.status === 500) {
          alert('Es probable que el id del profesor no exista. Por favor, verificar.');
        } else {
          alert('Ocurrió un error inesperado. Por favor, inténtelo más tarde.');
        }
        return throwError(() => error);
      })
    );
  }

  putCurso(id: number, curso: Curso): Observable<Curso> {
    return this.http.put<Curso>(`${this.cursoApiUrl}/${id}`, curso).pipe(
      catchError((error: HttpErrorResponse) => {
        if (error.status === 500 || error.status === 500) {
          alert('Es probable que el id del profesor no exista. Por favor, verificar.');
        } else {
          alert('Ocurrió un error inesperado. Por favor, inténtelo más tarde.');
        }
        return throwError(() => error);
      })
    );
  }

  eliminarCurso(id: number): Observable<void> {
    return this.http.delete<void>(`${this.cursoApiUrl}/${id}`);
  }
}
