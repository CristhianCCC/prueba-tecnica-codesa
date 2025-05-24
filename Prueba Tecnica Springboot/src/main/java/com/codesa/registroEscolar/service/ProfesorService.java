package com.codesa.registroEscolar.service;

import com.codesa.registroEscolar.dto.ProfesorDTO;
import com.codesa.registroEscolar.exceptions.BusinessRuleException;

import java.util.List;
import java.util.Optional;

public interface ProfesorService {

    List<ProfesorDTO> getListadoProfesores();

    Optional<ProfesorDTO> getProfesorById(Long id);

    ProfesorDTO postProfesor(ProfesorDTO profesorDTO) throws BusinessRuleException, InterruptedException;

    ProfesorDTO putProfesor(Long id, ProfesorDTO profesorDTO) throws BusinessRuleException;

    void deleteProfesor(Long id);

}
