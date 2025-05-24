package com.codesa.registroEscolar.service;

import com.codesa.registroEscolar.dto.PersonaDTO;
import com.codesa.registroEscolar.exceptions.BusinessRuleException;
import com.codesa.registroEscolar.model.Persona;

import java.util.List;
import java.util.Optional;

public interface PersonaService {

    public List<PersonaDTO> getListadoPersonas();

    public Optional<PersonaDTO> getPersonaById(Long id);

    public PersonaDTO postPersona(PersonaDTO personaDTO) throws BusinessRuleException;

    public PersonaDTO putPersona (Long id, PersonaDTO personaDTO);

    public void eliminarPersona (Long id);

}
