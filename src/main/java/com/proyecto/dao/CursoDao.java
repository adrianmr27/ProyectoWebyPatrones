package com.proyecto.dao;

import com.proyecto.domain.Curso;
import com.proyecto.domain.Periodo;
import com.proyecto.domain.Profesor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoDao extends JpaRepository<Curso, Long> {
//    List<Curso> findByPeriodo(Periodo periodo);
    
    // Método para buscar cursos por profesor
     List<Curso> findByProfesorId(Long profesorId); // Cambiado a profesorId
}
