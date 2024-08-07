package com.proyecto.dao;

import com.proyecto.domain.Curso;
import com.proyecto.domain.Matricula;
import com.proyecto.domain.Periodo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatriculaDao extends JpaRepository<Matricula, Long> {
    List<Matricula> findByCurso(Curso curso);
    List<Matricula> findByCursoId(Long cursoId);
    Matricula findByEstudianteId(Long estudianteId);
    List<Matricula> findByPeriodo(Periodo periodo);
   
}