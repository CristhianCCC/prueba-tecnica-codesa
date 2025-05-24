package com.codesa.registroEscolar.service.serviceImpl;

import com.codesa.registroEscolar.dto.CursoDTO;
import com.codesa.registroEscolar.exceptions.BusinessRuleException;
import com.codesa.registroEscolar.model.Curso;
import com.codesa.registroEscolar.model.Profesor;
import com.codesa.registroEscolar.repository.CursoRepository;
import com.codesa.registroEscolar.repository.ProfesorRepository;
import com.codesa.registroEscolar.service.CursoService;
import com.codesa.registroEscolar.validation.CursoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CursoServiceImpl implements CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private ProfesorRepository profesorRepository;

    @Override
    public List<CursoDTO> getAllCursos() {
        return cursoRepository.findAll()
                .stream()
                .map(this::mapearCursoADTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CursoDTO> getCursoById(Long id) {
        return cursoRepository.findById(id)
                .map(this::mapearCursoADTO);
    }

    @Override
    public CursoDTO postCurso(CursoDTO cursoDTO) throws BusinessRuleException {
        CursoValidator.validar(cursoDTO, true, cursoRepository);

        Profesor profesor = profesorRepository.findById(cursoDTO.getIdProfesor())
                .orElseThrow(() -> new BusinessRuleException("2001", "Profesor no encontrado", null));

        Curso curso = new Curso(
                cursoDTO.getNombre(),
                cursoDTO.getDescripcion(),
                cursoDTO.getCreditos(),
                profesor
        );

        Curso cursoGuardado = cursoRepository.save(curso);

        return mapearCursoADTO(cursoGuardado);
    }

    @Override
    public CursoDTO putCurso(Long id, CursoDTO cursoDTO) throws BusinessRuleException {

        CursoValidator.validar(cursoDTO, false, cursoRepository);

        Curso cursoExistente = cursoRepository.findById(id)
                .orElseThrow(() -> new BusinessRuleException("2002", "Curso no encontrado", null));

        Profesor profesor = profesorRepository.findById(cursoDTO.getIdProfesor())
                .orElseThrow(() -> new BusinessRuleException("2001", "Profesor no encontrado", null));

        cursoExistente.setNombre(cursoDTO.getNombre());
        cursoExistente.setDescripcion(cursoDTO.getDescripcion());
        cursoExistente.setCreditos(cursoDTO.getCreditos());
        cursoExistente.setProfesor(profesor);

        Curso cursoActualizado = cursoRepository.save(cursoExistente);

        return mapearCursoADTO(cursoActualizado);
    }

    @Override
    public void eliminarCurso(Long id) throws BusinessRuleException {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new BusinessRuleException("2002", "Curso no encontrado", null));
        cursoRepository.delete(curso);
    }

    private CursoDTO mapearCursoADTO(Curso curso) {
        CursoDTO dto = new CursoDTO();
        dto.setIdCurso(curso.getIdCurso());
        dto.setNombre(curso.getNombre());
        dto.setDescripcion(curso.getDescripcion());
        dto.setCreditos(curso.getCreditos());
        if (curso.getProfesor() != null) {
            dto.setIdProfesor(curso.getProfesor().getIdPersona());
            dto.setNombreProfesor(curso.getProfesor().getNombre());
        }
        return dto;
    }
}
