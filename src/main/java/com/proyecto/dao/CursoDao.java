package com.proyecto.dao;

import com.proyecto.domain.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoDao extends JpaRepository<Curso, Long> {
    
}
