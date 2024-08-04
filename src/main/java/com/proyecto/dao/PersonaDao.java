package com.proyecto.dao;

import com.proyecto.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaDao extends JpaRepository<Persona, Long> {
    Persona findByCorreo(String correo);
    Persona findByCorreoAndIdNot(String correo, Long id);
}