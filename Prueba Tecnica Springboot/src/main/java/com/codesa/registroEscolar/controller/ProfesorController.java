package com.codesa.registroEscolar.controller;
import com.codesa.registroEscolar.dto.ProfesorDTO;
import com.codesa.registroEscolar.exceptions.BusinessRuleException;
import com.codesa.registroEscolar.service.ProfesorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("api/profesor")
@CrossOrigin(origins = "http://localhost:4200")
public class ProfesorController {

    @Autowired
    private ProfesorService profesorService;

    @GetMapping
    public ResponseEntity<List<ProfesorDTO>> getAllProfesores() {
        List<ProfesorDTO> profesorDTOS = profesorService.getListadoProfesores();
        if (profesorDTOS == null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }else {
            return ResponseEntity.ok(profesorDTOS);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<ProfesorDTO>> getProfesorById(@PathVariable Long id) {
        Optional<ProfesorDTO> profesorDTO = profesorService.getProfesorById(id);
        if (profesorDTO.isEmpty()){
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.ok(profesorDTO);
        }
    }

    @PostMapping
    public ResponseEntity<ProfesorDTO> postProfesor(@Valid @RequestBody ProfesorDTO profesorDTO) throws BusinessRuleException, InterruptedException {
        ProfesorDTO creado = profesorService.postProfesor(profesorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfesorDTO> putProfesor(@PathVariable Long id, @Valid @RequestBody ProfesorDTO profesorDTO) throws BusinessRuleException {
        ProfesorDTO actualizado = profesorService.putProfesor(id, profesorDTO);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfesor(@PathVariable Long id) {
        profesorService.deleteProfesor(id);
        return ResponseEntity.noContent().build();
    }
}
