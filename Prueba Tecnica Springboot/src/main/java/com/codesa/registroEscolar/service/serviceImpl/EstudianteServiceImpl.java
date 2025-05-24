package com.codesa.registroEscolar.service.serviceImpl;
import com.codesa.registroEscolar.dto.EstudianteDTO;
import com.codesa.registroEscolar.exceptions.BusinessRuleException;
import com.codesa.registroEscolar.model.Estudiante;
import com.codesa.registroEscolar.repository.EstudianteRepository;
import com.codesa.registroEscolar.service.EstudianteService;
import com.codesa.registroEscolar.validation.EstudianteValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EstudianteServiceImpl implements EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<EstudianteDTO> getListadoEstudiantes() {
        List<Estudiante> estudiantes = estudianteRepository.findAll();
        return estudiantes.stream()
                .map(est -> modelMapper.map(est, EstudianteDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<EstudianteDTO> getEstudianteById(Long id) {
        Optional<Estudiante> estudianteOpt = estudianteRepository.findById(id);
        return estudianteOpt.map(est -> modelMapper.map(est, EstudianteDTO.class));
    }

    @Override
    public EstudianteDTO postEstudiante(EstudianteDTO estudianteDTO) throws BusinessRuleException {
        EstudianteValidator.validar(estudianteDTO, true, estudianteRepository);
        Estudiante estudiante = modelMapper.map(estudianteDTO, Estudiante.class);
        Estudiante saved = estudianteRepository.save(estudiante);
        return modelMapper.map(saved, EstudianteDTO.class);
    }


    @Override
    public EstudianteDTO putEstudiante(Long id, EstudianteDTO estudianteDTO) throws BusinessRuleException {
        Optional<Estudiante> estudianteOpt = estudianteRepository.findById(id);
        if (estudianteOpt.isEmpty()) {
            throw new RuntimeException("No se encontró el estudiante con id " + id);
        }
        Estudiante estudianteEncontrado = estudianteOpt.get();
        boolean cambiandoMatricula = !estudianteEncontrado.getNumeroMatricula()
                .equals(estudianteDTO.getNumeroMatricula());
        EstudianteValidator.validar(estudianteDTO, cambiandoMatricula, estudianteRepository);
        estudianteEncontrado.setNombre(estudianteDTO.getNombre());
        estudianteEncontrado.setApellido(estudianteDTO.getApellido());
        estudianteEncontrado.setFechaNacimiento(estudianteDTO.getFechaNacimiento());
        estudianteEncontrado.setEmail(estudianteDTO.getEmail());
        estudianteEncontrado.setTelefono(estudianteDTO.getTelefono());
        estudianteEncontrado.setGrado(estudianteDTO.getGrado());
        estudianteEncontrado.setNumeroMatricula(estudianteDTO.getNumeroMatricula());
        Estudiante updated = estudianteRepository.save(estudianteEncontrado);
        return modelMapper.map(updated, EstudianteDTO.class);
    }

    @Override
    public void deleteEstudiante(Long id) {
        Optional<Estudiante> estudianteOpt = estudianteRepository.findById(id);
        if (estudianteOpt.isPresent()) {
            estudianteRepository.delete(estudianteOpt.get());
        } else {
            throw new RuntimeException("No se encontró el estudiante con id " + id);
        }
    }
}
