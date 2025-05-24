package com.codesa.registroEscolar.validation;
import com.codesa.registroEscolar.dto.CursoDTO;
import com.codesa.registroEscolar.exceptions.BusinessRuleException;
import com.codesa.registroEscolar.repository.CursoRepository;
import org.springframework.http.HttpStatus;


public class CursoValidator {

    public static void validar(CursoDTO dto, boolean esNuevo, CursoRepository repo) throws BusinessRuleException {
        StringBuilder errores = new StringBuilder();

        if (dto == null) {
            throw new BusinessRuleException("1001", "El Curso no puede estar vacio", HttpStatus.BAD_REQUEST);
        }

        if (dto.getNombre() == null || dto.getNombre().trim().isEmpty()) {
            throw new BusinessRuleException("1000", "El nombre no puede ser vacio", HttpStatus.BAD_REQUEST);
        }

        if (dto.getDescripcion() == null || dto.getDescripcion().trim().isEmpty()) {
            throw new BusinessRuleException("1001", "La descripcion del curso no puede estar vacia", HttpStatus.BAD_REQUEST);
        }

        if (dto.getCreditos() == null) {
            throw new BusinessRuleException("1002", "El curso debe tener creditos asignados", HttpStatus.BAD_REQUEST);
        }

        if (dto.getIdProfesor() == null) {
            throw new BusinessRuleException("1003", "El curso debe tener un profesor asignado", HttpStatus.BAD_REQUEST);
        }

        if (!errores.isEmpty()) {
            throw new BusinessRuleException("1000", errores.toString().trim(), HttpStatus.BAD_REQUEST);
        }
    }

}
