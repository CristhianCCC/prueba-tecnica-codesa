package com.codesa.registroEscolar.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "estudiante")
@PrimaryKeyJoinColumn(name = "id_persona")
public class Estudiante extends Persona {

    @Column(unique = true, nullable = false)
    private String numeroMatricula;
    private Integer grado;

    public Estudiante() {}

    public Estudiante(Long idPersona,  String nombre, String apellido, LocalDate fechaNacimiento, String email, Long telefono,
                      String numeroMatricula, Integer grado) {
        super(idPersona, nombre, apellido, fechaNacimiento, email, telefono);
        this.numeroMatricula = numeroMatricula;
        this.grado = grado;
    }

    @OneToMany(mappedBy = "estudiante", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Inscripcion> inscripciones = new ArrayList<>();

    public Integer getGrado() {
        return grado;
    }

    public void setGrado(Integer grado) {
        this.grado = grado;
    }

    public String getNumeroMatricula() {
        return numeroMatricula;
    }

    public void setNumeroMatricula(String numeroMatricula) {
        this.numeroMatricula = numeroMatricula;
    }
}
