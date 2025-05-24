package com.codesa.registroEscolar.validation;

import com.codesa.registroEscolar.dto.EstudianteDTO;
import com.codesa.registroEscolar.dto.PersonaDTO;
import com.codesa.registroEscolar.exceptions.BusinessRuleException;
import com.codesa.registroEscolar.repository.EstudianteRepository;
import com.codesa.registroEscolar.repository.PersonaRepository;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;

public class PersonaValidator {

    public static void validar(PersonaDTO dto, boolean esNuevo, PersonaRepository repo) throws BusinessRuleException {
        StringBuilder errores = new StringBuilder();

        if (dto == null) {
            throw new BusinessRuleException("1001", "El estudiante no puede ser null", HttpStatus.BAD_REQUEST);
        }

        if (dto.getNombre() == null || dto.getNombre().trim().isEmpty()) {
            throw new BusinessRuleException("1000", "El nombre no puede ser vacio", HttpStatus.BAD_REQUEST);
        }

        if (dto.getApellido() == null || dto.getApellido().trim().isEmpty()) {
            throw new BusinessRuleException("1001", "El apellido no puede ser vacio", HttpStatus.BAD_REQUEST);
        }

        if (dto.getTelefono() == null) {
            throw new BusinessRuleException("1002", "El telefono no puede ser vacio", HttpStatus.BAD_REQUEST);
        } else if (String.valueOf(dto.getTelefono()).length() < 10) {
            throw new BusinessRuleException("1002", "El telefono debe tener 10 digitos", HttpStatus.BAD_REQUEST);
        }

        if (dto.getEmail() == null || dto.getEmail().trim().isEmpty()) {
            throw new BusinessRuleException("1003", "El correo debe no puede estar vacio", HttpStatus.BAD_REQUEST);
        }

        if (dto.getFechaNacimiento() == null) {
            throw new BusinessRuleException("1004", "La fecha no puede estar vacia", HttpStatus.BAD_REQUEST);
        } else if (dto.getFechaNacimiento().isAfter(LocalDate.now().minusYears(4))) {
            throw new BusinessRuleException("1004", "El estudiante debe tener al menos 4 años", HttpStatus.BAD_REQUEST);
        }

        if (!errores.isEmpty()) {
            throw new BusinessRuleException("1000", errores.toString().trim(), HttpStatus.BAD_REQUEST);
        }
    }

}
