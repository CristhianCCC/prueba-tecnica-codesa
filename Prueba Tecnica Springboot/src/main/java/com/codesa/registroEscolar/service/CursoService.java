package com.codesa.registroEscolar.service;

import com.codesa.registroEscolar.dto.CursoDTO;
import com.codesa.registroEscolar.exceptions.BusinessRuleException;
import com.codesa.registroEscolar.model.Curso;

import java.util.List;
import java.util.Optional;

public interface CursoService {

    public List<CursoDTO> getAllCursos();

    public Optional<CursoDTO> getCursoById (Long id);

    public CursoDTO postCurso (CursoDTO cursoDTO) throws BusinessRuleException;

    public CursoDTO putCurso (Long id, CursoDTO cursoDTO) throws BusinessRuleException;

    public void eliminarCurso (Long id) throws BusinessRuleException;

}
