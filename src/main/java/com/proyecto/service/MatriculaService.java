package com.proyecto.service;

import com.proyecto.domain.Curso;
import com.proyecto.domain.Estudiante;
import com.proyecto.domain.Matricula;
import com.proyecto.domain.Periodo;
import java.util.List;

public interface MatriculaService {
    
    List<Matricula> getMatriculas();
    Matricula getMatricula(Long id);
    List<Matricula> getMatriculasByCursoId(Long cursoId);
    Matricula findByEstudianteId(Long estudianteId);
    void save(Matricula matricula);
    void delete(Matricula matricula);
    List<Matricula> findByPeriodo(Periodo periodo);
    
    Matricula encontrarMatricula(Matricula matricula);
    
    // Nuevos métodos
    Estudiante findEstudianteByMatriculaId(Long matriculaId);
    Curso findCursoByMatriculaId(Long matriculaId);
}