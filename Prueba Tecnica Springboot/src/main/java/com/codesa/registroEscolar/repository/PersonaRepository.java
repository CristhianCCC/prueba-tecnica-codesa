package com.codesa.registroEscolar.repository;

import com.codesa.registroEscolar.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends JpaRepository <Persona, Long> {



}
