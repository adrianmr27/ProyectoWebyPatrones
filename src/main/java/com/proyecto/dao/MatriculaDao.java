package com.proyecto.dao;

import com.proyecto.domain.Curso;
import com.proyecto.domain.Matricula;
import com.proyecto.domain.Periodo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatriculaDao extends JpaRepository<Matricula, Long> {
    Matricula findByEstudianteId(Long estudianteId); // Método agregado
   
    List<Matricula> findByCursoId(Long cursoId);
}