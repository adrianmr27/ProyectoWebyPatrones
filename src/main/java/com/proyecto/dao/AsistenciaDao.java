package com.proyecto.dao;

import com.proyecto.domain.Asistencia;
import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AsistenciaDao extends JpaRepository<Asistencia, Long> {
    Asistencia findByFecha(Date fecha);   
}
