import { Persona } from "./Persona";


export interface Profesor extends Persona {
numeroMatricula: string;
grado: number;
especialidad: string;
fechaContratacion:Date;

}