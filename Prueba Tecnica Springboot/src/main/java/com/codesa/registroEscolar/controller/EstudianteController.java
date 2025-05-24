package com.codesa.registroEscolar.controller;
import com.codesa.registroEscolar.dto.EstudianteDTO;
import com.codesa.registroEscolar.exceptions.BusinessRuleException;
import com.codesa.registroEscolar.service.EstudianteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("api/estudiante")
@CrossOrigin(origins = "http://localhost:4200")
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;

    @GetMapping
    public ResponseEntity<List<EstudianteDTO>> getAllEstudiantes() {
        List<EstudianteDTO> estudianteDTOS = estudianteService.getListadoEstudiantes();
        if (estudianteDTOS == null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }else {
            return ResponseEntity.ok(estudianteDTOS);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<EstudianteDTO>> getEstudianteById(@PathVariable Long id) {
        Optional<EstudianteDTO> estudianteDTO = estudianteService.getEstudianteById(id);
        if (estudianteDTO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }else {
            return ResponseEntity.ok(estudianteDTO);
        }
    }

    @PostMapping
    public ResponseEntity<EstudianteDTO> postEstudiante(@Valid @RequestBody EstudianteDTO estudianteDTO) throws BusinessRuleException {
        EstudianteDTO creado = estudianteService.postEstudiante(estudianteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstudianteDTO> putEstudiante(@PathVariable Long id, @Valid @RequestBody EstudianteDTO estudianteDTO) throws BusinessRuleException {
        EstudianteDTO actualizado = estudianteService.putEstudiante(id, estudianteDTO);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEstudiante(@PathVariable Long id) {
        estudianteService.deleteEstudiante(id);
        return ResponseEntity.noContent().build();
    }

}

