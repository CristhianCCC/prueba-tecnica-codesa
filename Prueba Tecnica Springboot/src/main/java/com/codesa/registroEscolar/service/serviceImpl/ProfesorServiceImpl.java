package com.codesa.registroEscolar.service.serviceImpl;

import com.codesa.registroEscolar.dto.ProfesorDTO;
import com.codesa.registroEscolar.exceptions.BusinessRuleException;
import com.codesa.registroEscolar.model.Profesor;
import com.codesa.registroEscolar.repository.ProfesorRepository;
import com.codesa.registroEscolar.service.ProfesorService;
import com.codesa.registroEscolar.validation.ProfesorValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfesorServiceImpl implements ProfesorService {

    @Autowired
    private ProfesorRepository profesorRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ProfesorDTO> getListadoProfesores() {
        List<Profesor> profesores = profesorRepository.findAll();
        return profesores.stream()
                .map(prof -> modelMapper.map(prof, ProfesorDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProfesorDTO> getProfesorById(Long id) {
        Optional<Profesor> profesorOpt = profesorRepository.findById(id);
        return profesorOpt.map(prof -> modelMapper.map(prof, ProfesorDTO.class));
    }

    @Override
    public ProfesorDTO postProfesor(ProfesorDTO profesorDTO) throws BusinessRuleException {
        ProfesorValidator.validar(profesorDTO, true, profesorRepository);
        Profesor profesor = modelMapper.map(profesorDTO, Profesor.class);
        Profesor saved = profesorRepository.save(profesor);
        return modelMapper.map(saved, ProfesorDTO.class);
    }


    @Override
    public ProfesorDTO putProfesor(Long id, ProfesorDTO profesorDTO) throws BusinessRuleException {
        Profesor profesorEncontrado = profesorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el profesor con id " + id));

        ProfesorValidator.validar(profesorDTO, false, profesorRepository);

        profesorEncontrado.setNombre(profesorDTO.getNombre());
        profesorEncontrado.setApellido(profesorDTO.getApellido());
        profesorEncontrado.setFechaNacimiento(profesorDTO.getFechaNacimiento());
        profesorEncontrado.setEmail(profesorDTO.getEmail());
        profesorEncontrado.setTelefono(profesorDTO.getTelefono());
        profesorEncontrado.setEspecialidad(profesorDTO.getEspecialidad());
        profesorEncontrado.setFechaContratacion(profesorDTO.getFechaContratacion());

        Profesor updated = profesorRepository.save(profesorEncontrado);
        return modelMapper.map(updated, ProfesorDTO.class);
    }

    @Override
    public void deleteProfesor(Long id) {
        Profesor profesor = profesorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profesor no encontrado con id: " + id));
        profesorRepository.delete(profesor);    }
}
