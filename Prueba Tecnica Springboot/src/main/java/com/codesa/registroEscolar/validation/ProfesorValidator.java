package com.codesa.registroEscolar.validation;

import com.codesa.registroEscolar.dto.ProfesorDTO;
import com.codesa.registroEscolar.exceptions.BusinessRuleException;
import com.codesa.registroEscolar.repository.ProfesorRepository;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;

public class ProfesorValidator {

    public static void validar(ProfesorDTO dto, boolean esNuevo, ProfesorRepository repo) throws BusinessRuleException {

        if (dto == null) {
            throw new BusinessRuleException("1000", "El profesor no puede ser vacio", HttpStatus.BAD_REQUEST);
        }

        if (dto.getNombre() == null || dto.getNombre().trim().isEmpty()) {
            throw new BusinessRuleException("1001", "El nombre no puede ser vacio", HttpStatus.BAD_REQUEST);
        }

        if (dto.getApellido() == null || dto.getApellido().trim().isEmpty()) {
            throw new BusinessRuleException("1002", "El apellido no puede ser vacio", HttpStatus.BAD_REQUEST);
        }

        if (dto.getTelefono() == null) {
            throw new BusinessRuleException("1003", "El número de telefono no puede ser vacio", HttpStatus.BAD_REQUEST);
        } else if (String.valueOf(dto.getTelefono()).length() < 10) {
            throw new BusinessRuleException("1003", "El número de telefono debe tener 10 digitos", HttpStatus.BAD_REQUEST);
        }

        if (dto.getEspecialidad() == null || dto.getEspecialidad().trim().isEmpty()) {
            throw new BusinessRuleException("1004", "La especialidad no puede estar vacia", HttpStatus.BAD_REQUEST);
        }

        if (dto.getFechaNacimiento() == null || dto.getFechaNacimiento().isAfter(LocalDate.now())) {
            throw new BusinessRuleException("1005", "La fecha no puede ser nula o mayor a la actual", HttpStatus.BAD_REQUEST);
        }

        // Si quieres agregar más validaciones, hazlo aquí...
    }
}
