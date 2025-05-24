package com.codesa.registroEscolar.controller;
import com.codesa.registroEscolar.dto.CursoDTO;
import com.codesa.registroEscolar.exceptions.BusinessRuleException;
import com.codesa.registroEscolar.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/curso")
@CrossOrigin(origins = "http://localhost:4200")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping
    public ResponseEntity<List<CursoDTO>> getAllCursos() {
        List<CursoDTO> cursos = cursoService.getAllCursos();
        if(cursos == null){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(cursos);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoDTO> getCursoById(@PathVariable Long id) {
        return cursoService.getCursoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CursoDTO> postCurso(@RequestBody CursoDTO cursoDTO) throws BusinessRuleException {
        CursoDTO creado = cursoService.postCurso(cursoDTO);
        return ResponseEntity.ok(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CursoDTO> putCurso(@PathVariable Long id, @RequestBody CursoDTO cursoDTO) throws BusinessRuleException {
        CursoDTO actualizado = cursoService.putCurso(id, cursoDTO);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCurso(@PathVariable Long id) throws BusinessRuleException {
        cursoService.eliminarCurso(id);
        return ResponseEntity.noContent().build();
    }
}

