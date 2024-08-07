package com.proyecto.service;

import com.proyecto.domain.Curso;
import com.proyecto.domain.Matricula;
import com.proyecto.domain.Periodo;
import java.util.List;

public interface MatriculaService {
    
//    List<Matricula> getMatriculas();
//    
//    Matricula getMatricula(Matricula matricula);
//    
//    Matricula findByEstudianteId(Long estudianteId);
//    
//    void save(Matricula matricula);
//    
//    void delete(Matricula matricula);
//    
//    List<Matricula> findByCurso(Curso curso);
//    
//    List<Matricula> getMatriculasByCursoId(Long cursoId);
//    
    
    List<Matricula> getMatriculas();
    Matricula getMatricula(Long id);
    List<Matricula> getMatriculasByCursoId(Long cursoId);
    Matricula findByEstudianteId(Long estudianteId);
    void save(Matricula matricula);
    void delete(Matricula matricula);
    List<Matricula> findByPeriodo(Periodo periodo); // Añadir este método
}

