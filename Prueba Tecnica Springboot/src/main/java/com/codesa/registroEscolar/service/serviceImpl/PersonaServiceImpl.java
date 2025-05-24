package com.codesa.registroEscolar.service.serviceImpl;
import com.codesa.registroEscolar.dto.PersonaDTO;
import com.codesa.registroEscolar.exceptions.BusinessRuleException;
import com.codesa.registroEscolar.model.Persona;
import com.codesa.registroEscolar.repository.PersonaRepository;
import com.codesa.registroEscolar.service.PersonaService;
import com.codesa.registroEscolar.validation.PersonaValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<PersonaDTO> getListadoPersonas() {
        return personaRepository.findAll()
                .stream()
                .map(persona -> modelMapper.map(persona, PersonaDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PersonaDTO> getPersonaById(Long id) {
        return personaRepository.findById(id)
                .map(persona -> modelMapper.map(persona, PersonaDTO.class));
    }

    @Override
    public PersonaDTO postPersona(PersonaDTO personaDTO) throws BusinessRuleException {
        PersonaValidator.validar(personaDTO, true, personaRepository);
        Persona persona = modelMapper.map(personaDTO, Persona.class);
        Persona saved = personaRepository.save(persona);
        return modelMapper.map(saved, PersonaDTO.class);
    }

    @Override
    public PersonaDTO putPersona(Long id, PersonaDTO personaDTO) {
        return personaRepository.findById(id).map(personaEncontrada -> {
            // Actualizando los campos de personaEncontrada con personaDTO
            modelMapper.map(personaDTO, personaEncontrada);
            Persona actualizada = personaRepository.save(personaEncontrada);
            return modelMapper.map(actualizada, PersonaDTO.class);
        }).orElseThrow(() -> new RuntimeException("No se encontró persona con ID " + id));
    }

    @Override
    public void eliminarPersona(Long id) {
        personaRepository.deleteById(id);
    }
}
