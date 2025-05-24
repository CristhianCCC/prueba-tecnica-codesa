import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Profesor } from '../models/Profesor';

@Injectable({
  providedIn: 'root'
})
export class ProfesorServiceService {

  private readonly profesorApiUrl = 'http://localhost:8080/api/profesor';

  constructor(private http: HttpClient) {}

  getListadoProfesores(): Observable<Profesor[]> {
    return this.http.get<Profesor[]>(this.profesorApiUrl);
  }

  getProfesorById(id: number): Observable<Profesor> {
    return this.http.get<Profesor>(`${this.profesorApiUrl}/${id}`);
  }

  postProfesor(profesor: Profesor): Observable<Profesor> {
    return this.http.post<Profesor>(this.profesorApiUrl, profesor);
  }

  putProfesor(profesor: Profesor): Observable<Profesor> {
    return this.http.put<Profesor>(`${this.profesorApiUrl}/${profesor.idPersona}`, profesor);
  }

  deleteProfesor(id: number): Observable<void> {
    return this.http.delete<void>(`${this.profesorApiUrl}/${id}`);
  }
}
