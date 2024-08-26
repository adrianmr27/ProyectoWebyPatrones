package com.proyecto.dao;

import com.proyecto.domain.Asistencia;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AsistenciaDao extends JpaRepository<Asistencia, Long> {
    List<Asistencia> findByMatriculaId(Long matriculaId);
    
    @Query("SELECT a FROM Asistencia a WHERE a.matricula.curso.id = :cursoId")
    List<Asistencia> findByCursoId(@Param("cursoId") Long cursoId);    
}