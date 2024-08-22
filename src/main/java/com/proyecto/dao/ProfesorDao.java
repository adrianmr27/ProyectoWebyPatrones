package com.proyecto.dao;

import com.proyecto.domain.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfesorDao extends JpaRepository<Profesor, Long> {
    Profesor findByUsuario(String usuario);

    Profesor findByUsuarioAndClave(String usuario, String clave);

    Profesor findByUsuarioOrPersonaCorreo(String usuario, String correo);  // Cambiado a persona.correo

    boolean existsByUsuarioOrPersonaCorreo(String usuario, String correo); // Cambiado a persona.correo
}