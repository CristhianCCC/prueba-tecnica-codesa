package com.codesa.registroEscolar.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class EstudianteDTO extends PersonaDTO {

    @NotBlank(message = "El número de matrícula es obligatorio")
    private String numeroMatricula;

    @NotNull(message = "El grado es obligatorio")
    private Integer grado;


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