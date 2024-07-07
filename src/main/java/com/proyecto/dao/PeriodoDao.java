package com.proyecto.dao;

import com.proyecto.domain.Periodo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeriodoDao extends JpaRepository<Periodo, Long> {

}
