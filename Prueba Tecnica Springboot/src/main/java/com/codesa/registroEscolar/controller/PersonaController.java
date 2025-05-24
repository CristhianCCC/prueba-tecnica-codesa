package com.codesa.registroEscolar.controller;
import com.codesa.registroEscolar.dto.PersonaDTO;
import com.codesa.registroEscolar.exceptions.BusinessRuleException;
import com.codesa.registroEscolar.service.PersonaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/persona")
@CrossOrigin(origins = "http://localhost:4200")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @GetMapping
    public ResponseEntity<List<PersonaDTO>> getListadoPersonas(){
        List<PersonaDTO> personaDTO = personaService.getListadoPersonas();
        if (personaDTO == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }else {
            return ResponseEntity.ok(personaDTO);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<PersonaDTO>> getPersonaById(@PathVariable Long id){
        Optional<PersonaDTO> persona = personaService.getPersonaById(id);
        if(persona.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }else {
            return ResponseEntity.ok(persona);
        }
    }

    @PostMapping
    public ResponseEntity<PersonaDTO> postPersona(@Valid @RequestBody PersonaDTO personaDto) throws BusinessRuleException {
        PersonaDTO personaCreated = personaService.postPersona(personaDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(personaCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonaDTO> putPersona (@PathVariable Long id, @Valid @RequestBody PersonaDTO personaDTO){
        PersonaDTO personaEdited = personaService.putPersona(id, personaDTO);
        return ResponseEntity.ok(personaEdited);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersona (@PathVariable Long id){
        personaService.eliminarPersona(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
