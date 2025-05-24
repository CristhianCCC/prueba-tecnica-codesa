package com.codesa.registroEscolar.dto;

public class ProfesorResumenDTO {

    private Long idProfesor;
    private String nombre;

    public ProfesorResumenDTO() {}

    public ProfesorResumenDTO(Long idProfesor, String nombre) {
        this.idProfesor = idProfesor;
        this.nombre = nombre;
    }

    public Long getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(Long idProfesor) {
        this.idProfesor = idProfesor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
