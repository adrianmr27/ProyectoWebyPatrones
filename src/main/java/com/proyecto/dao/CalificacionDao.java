package com.proyecto.dao;

import com.proyecto.domain.Calificacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalificacionDao extends JpaRepository<Calificacion, Long> {
}
