package com.codesa.registroEscolar.service;

import com.codesa.registroEscolar.dto.EstudianteDTO;
import com.codesa.registroEscolar.exceptions.BusinessRuleException;

import java.util.List;
import java.util.Optional;

public interface EstudianteService {

    List<EstudianteDTO> getListadoEstudiantes();

    Optional<EstudianteDTO> getEstudianteById(Long id);

    EstudianteDTO postEstudiante(EstudianteDTO estudianteDTO) throws BusinessRuleException;

    EstudianteDTO putEstudiante(Long id, EstudianteDTO estudianteDTO) throws BusinessRuleException;

    void deleteEstudiante(Long id);

}