package com.codesa.registroEscolar.repository;

import com.codesa.registroEscolar.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {



}
