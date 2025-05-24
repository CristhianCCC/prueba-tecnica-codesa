package com.codesa.registroEscolar.dto;

import java.time.LocalDate;

public class InscripcionDTO {

    private Long id;
    private Long idEstudiante;
    private Long idCurso;
    private LocalDate fechaInscripcion;

    /**
     * mandando a llamar los nombres de estudiante y curso a esta clase para disponer de ellos en el service
     */
    private String nombreEstudiante;
    private String nombreCurso;

    public InscripcionDTO() {}

    public InscripcionDTO(Long id, Long idEstudiante, Long idCurso, LocalDate fechaInscripcion,
                          String nombreEstudiante, String nombreCurso) {
        this.id = id;
        this.idEstudiante = idEstudiante;
        this.idCurso = idCurso;
        this.fechaInscripcion = fechaInscripcion;
        this.nombreEstudiante = nombreEstudiante;
        this.nombreCurso = nombreCurso;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(Long idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public Long getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Long idCurso) {
        this.idCurso = idCurso;
    }

    public LocalDate getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(LocalDate fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public String getNombreEstudiante() {
        return nombreEstudiante;
    }

    public void setNombreEstudiante(String nombreEstudiante) {
        this.nombreEstudiante = nombreEstudiante;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }
}
