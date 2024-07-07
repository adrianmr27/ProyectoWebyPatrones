package com.proyecto.dao;

import com.proyecto.domain.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatriculaDao extends JpaRepository<Matricula, Long> {
    
}
