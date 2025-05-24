import { Persona } from "./Persona";


export interface Estudiante extends Persona {
numeroMatricula: string;
grado: number;
}