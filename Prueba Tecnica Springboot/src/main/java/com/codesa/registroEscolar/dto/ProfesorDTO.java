package com.codesa.registroEscolar.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import java.time.LocalDate;

public class ProfesorDTO extends PersonaDTO {

    @NotBlank(message = "La especialidad es obligatoria")
    private String especialidad;

    @PastOrPresent(message = "La fecha de contratación no puede ser futura")
    private LocalDate fechaContratacion;

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
}
