package com.codesa.registroEscolar.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "profesor")
@PrimaryKeyJoinColumn(name = "id_persona")
public class Profesor extends Persona{

    private String especialidad;
    private LocalDate fechaContratacion;


    @OneToMany(mappedBy = "profesor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Curso> cursos = new ArrayList<>();

    public Profesor () {}

    public Profesor(Long idPersona, String nombre, String apellido, LocalDate fechaNacimiento, String email, Long telefono,
                    String especialidad, LocalDate fechaContratacion) {
        super(idPersona, nombre, apellido, fechaNacimiento, email, telefono);
        this.especialidad = especialidad;
        this.fechaContratacion = fechaContratacion;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public LocalDate getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(LocalDate fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }
}
