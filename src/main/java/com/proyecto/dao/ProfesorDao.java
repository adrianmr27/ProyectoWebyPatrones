package com.proyecto.dao;

import com.proyecto.domain.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfesorDao extends JpaRepository<Profesor, Long> {

}
