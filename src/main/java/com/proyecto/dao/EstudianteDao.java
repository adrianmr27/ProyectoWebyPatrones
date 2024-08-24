package com.proyecto.dao;

import com.proyecto.domain.Estudiante;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EstudianteDao extends JpaRepository<Estudiante, Long> {
    
    @Query("SELECT e FROM Estudiante e JOIN Matricula m ON e.id = m.estudiante.id WHERE m.curso.id = :cursoId")
    List<Estudiante> findByCursoId(@Param("cursoId") long cursoId);
}
