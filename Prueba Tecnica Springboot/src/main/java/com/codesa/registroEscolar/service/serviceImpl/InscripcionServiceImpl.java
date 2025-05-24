package com.codesa.registroEscolar.service.serviceImpl;

import com.codesa.registroEscolar.dto.InscripcionDTO;
import com.codesa.registroEscolar.exceptions.BusinessRuleException;
import com.codesa.registroEscolar.model.Curso;
import com.codesa.registroEscolar.model.Estudiante;
import com.codesa.registroEscolar.model.Inscripcion;
import com.codesa.registroEscolar.repository.CursoRepository;
import com.codesa.registroEscolar.repository.EstudianteRepository;
import com.codesa.registroEscolar.repository.InscripcionRepository;
import com.codesa.registroEscolar.service.InscripcionService;
import com.codesa.registroEscolar.validation.InscripcionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InscripcionServiceImpl implements InscripcionService {

    @Autowired
    private InscripcionRepository inscripcionRepository;

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Override
    public List<InscripcionDTO> getInscripciones() {
        return inscripcionRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<InscripcionDTO> getInscripcionesById(Long id) {
        return inscripcionRepository.findById(id).map(this::convertToDTO);
    }

    @Override
    public InscripcionDTO postInscripcion(InscripcionDTO dto) throws BusinessRuleException {
        // Validación previa a creación
        InscripcionValidator.validar(dto, true, inscripcionRepository, estudianteRepository, cursoRepository);

        Inscripcion entidad = new Inscripcion();

        Estudiante estudiante = estudianteRepository.findById(dto.getIdEstudiante())
                .orElseThrow(() -> new BusinessRuleException("2003", "Estudiante no encontrado", null));
        Curso curso = cursoRepository.findById(dto.getIdCurso())
                .orElseThrow(() -> new BusinessRuleException("2004", "Curso no encontrado", null));

        entidad.setEstudiante(estudiante);
        entidad.setCurso(curso);
        entidad.setFechaInscripcion(dto.getFechaInscripcion());

        Inscripcion guardado = inscripcionRepository.save(entidad);
        return convertToDTO(guardado);
    }

    @Override
    public InscripcionDTO putInscripcion(Long id, InscripcionDTO dto) throws BusinessRuleException {
        if (!inscripcionRepository.existsById(id)) {
            throw new BusinessRuleException("2005", "Inscripción no encontrada con ID: " + id, null);
        }

        InscripcionValidator.validar(dto, false, inscripcionRepository, estudianteRepository, cursoRepository);

        Inscripcion entidad = inscripcionRepository.findById(id)
                .orElseThrow(() -> new BusinessRuleException("2005", "Inscripción no encontrada con ID: " + id, null));

        Estudiante estudiante = estudianteRepository.findById(dto.getIdEstudiante())
                .orElseThrow(() -> new BusinessRuleException("2003", "Estudiante no encontrado", null));
        Curso curso = cursoRepository.findById(dto.getIdCurso())
                .orElseThrow(() -> new BusinessRuleException("2004", "Curso no encontrado", null));

        entidad.setEstudiante(estudiante);
        entidad.setCurso(curso);
        entidad.setFechaInscripcion(dto.getFechaInscripcion());

        Inscripcion actualizado = inscripcionRepository.save(entidad);
        return convertToDTO(actualizado);
    }

    @Override
    public void eliminarInscripcion(Long id) throws BusinessRuleException {
        if (!inscripcionRepository.existsById(id)) {
            throw new BusinessRuleException("2005", "Inscripción no encontrada con ID: " + id, null);
        }
        inscripcionRepository.deleteById(id);
    }
    private InscripcionDTO convertToDTO(Inscripcion inscripcion) {
        InscripcionDTO dto = new InscripcionDTO();
        dto.setId(inscripcion.getIdInscripcion());
        dto.setIdEstudiante(inscripcion.getEstudiante().getIdPersona());
        dto.setIdCurso(inscripcion.getCurso().getIdCurso());
        dto.setFechaInscripcion(inscripcion.getFechaInscripcion());
        dto.setNombreEstudiante(inscripcion.getEstudiante().getNombre() + " " + inscripcion.getEstudiante().getApellido());
        dto.setNombreCurso(inscripcion.getCurso().getNombre());
        return dto;
    }
}
