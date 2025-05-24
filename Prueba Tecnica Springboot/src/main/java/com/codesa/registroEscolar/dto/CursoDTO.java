package com.codesa.registroEscolar.dto;

import jakarta.validation.constraints.NotBlank;

public class CursoDTO {

    private Long idCurso;

    @NotBlank(message = "el curso es obligatorio")
    private String nombre;

    @NotBlank(message = "la descripcion es obligatoria")
    private String descripcion;

    @NotBlank(message = "los creditos son obligatorios")
    private Integer creditos;

    /*
    * mandando a llamar los campos del profesor para los metodos del service
    */
    @NotBlank(message = "se debe agregar un profesor al curso")
    private Long idProfesor;

    @NotBlank(message = "el nombre del profesor debe ser especificado")
    private String nombreProfesor;



    public Integer getCreditos() {
        return creditos;
    }

    public void setCreditos(Integer creditos) {
        this.creditos = creditos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Long idCurso) {
        this.idCurso = idCurso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(Long idProfesor) {
        this.idProfesor = idProfesor;
    }

    public String getNombreProfesor() {
        return nombreProfesor;
    }

    public void setNombreProfesor(String nombreProfesor) {
        this.nombreProfesor = nombreProfesor;
    }
}
