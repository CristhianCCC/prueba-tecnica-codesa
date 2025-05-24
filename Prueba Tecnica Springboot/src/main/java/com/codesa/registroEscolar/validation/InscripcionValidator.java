package com.codesa.registroEscolar.validation;

import com.codesa.registroEscolar.dto.InscripcionDTO;
import com.codesa.registroEscolar.exceptions.BusinessRuleException;
import com.codesa.registroEscolar.repository.CursoRepository;
import com.codesa.registroEscolar.repository.EstudianteRepository;
import com.codesa.registroEscolar.repository.InscripcionRepository;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;

public class InscripcionValidator {
    /**
     *Pasando los repositorios para aplicar validacion si el id existe
     */
    public static void validar(InscripcionDTO dto, boolean esNuevo, InscripcionRepository inscripcionRepo, EstudianteRepository estudianteRepo, CursoRepository cursoRepo) throws BusinessRuleException {

        if (dto.getFechaInscripcion() == null || dto.getFechaInscripcion().isAfter(LocalDate.now())) {
            throw new BusinessRuleException("2000", "La inscripción no puede tener una fecha futura", HttpStatus.BAD_REQUEST);
        }

        if (dto.getIdEstudiante() == null) {
            throw new BusinessRuleException("2001", "El ID del estudiante no puede ser nulo", HttpStatus.BAD_REQUEST);
        }

        if (dto.getIdCurso() == null) {
            throw new BusinessRuleException("2002", "El ID del curso no puede ser nulo", HttpStatus.BAD_REQUEST);
        }

        /**
         * aplicando validaciones de id's existentes
         */
        if (!estudianteRepo.existsById(dto.getIdEstudiante())) {
            throw new BusinessRuleException("2003", "No existe un estudiante con ID: " + dto.getIdEstudiante(), HttpStatus.BAD_REQUEST);
        }

        if (!cursoRepo.existsById(dto.getIdCurso())) {
            throw new BusinessRuleException("2004", "No existe un curso con ID: " + dto.getIdCurso(), HttpStatus.BAD_REQUEST);
        }
    }
}
