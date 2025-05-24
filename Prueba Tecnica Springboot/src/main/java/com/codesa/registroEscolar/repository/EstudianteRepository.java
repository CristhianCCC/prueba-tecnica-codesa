package com.codesa.registroEscolar.repository;

import com.codesa.registroEscolar.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {

    boolean existsByNumeroMatricula(String numeroMatricula);

}
