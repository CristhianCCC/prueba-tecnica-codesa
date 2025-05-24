package com.codesa.registroEscolar.controller;

import com.codesa.registroEscolar.dto.InscripcionDTO;
import com.codesa.registroEscolar.exceptions.BusinessRuleException;
import com.codesa.registroEscolar.service.InscripcionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/inscripcion")
@CrossOrigin(origins = "http://localhost:4200")
public class InscripcionController {

    private final InscripcionService inscripcionService;

    public InscripcionController(InscripcionService inscripcionService) {
        this.inscripcionService = inscripcionService;
    }

    @GetMapping
    public ResponseEntity<List<InscripcionDTO>> getInscripciones() {
        List<InscripcionDTO> inscripciones = inscripcionService.getInscripciones();
        if (inscripciones == null){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(inscripciones);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<InscripcionDTO> getInscripcionesById(@PathVariable Long id) throws BusinessRuleException {
        return inscripcionService.getInscripcionesById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @PostMapping
    public ResponseEntity<InscripcionDTO> postInscripcion(@RequestBody InscripcionDTO dto) throws BusinessRuleException {
        InscripcionDTO creado = inscripcionService.postInscripcion(dto);
        return ResponseEntity.ok(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InscripcionDTO> putInscripcion(@PathVariable Long id, @RequestBody InscripcionDTO dto) throws BusinessRuleException {
        InscripcionDTO actualizado = inscripcionService.putInscripcion(id, dto);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarInscripcion(@PathVariable Long id) throws BusinessRuleException {
        inscripcionService.eliminarInscripcion(id);
        return ResponseEntity.noContent().build();
    }
}
