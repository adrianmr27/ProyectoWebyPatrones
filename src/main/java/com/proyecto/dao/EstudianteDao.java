package com.proyecto.dao;

import com.proyecto.domain.Estudiante;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudianteDao extends JpaRepository<Estudiante, Long> {
}
