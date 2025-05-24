package com.codesa.registroEscolar.service;

import com.codesa.registroEscolar.dto.InscripcionDTO;
import com.codesa.registroEscolar.exceptions.BusinessRuleException;

import java.util.List;
import java.util.Optional;

public interface InscripcionService {

    List<InscripcionDTO> getInscripciones();

    Optional<InscripcionDTO> getInscripcionesById(Long id);

    InscripcionDTO postInscripcion(InscripcionDTO dto) throws BusinessRuleException;

    InscripcionDTO putInscripcion(Long id, InscripcionDTO inscripcionDTO) throws BusinessRuleException;

    void eliminarInscripcion(Long id) throws BusinessRuleException;
}
