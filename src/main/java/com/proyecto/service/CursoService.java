package com.proyecto.service;

import com.proyecto.domain.Curso;
import com.proyecto.domain.Periodo;
import com.proyecto.domain.Profesor;
import java.util.List;

public interface CursoService {
    
//    public List<Curso> getCursos();
//    
//    public Curso getCurso(Curso curso);
//   
//    public void save(Curso curso);
//    
//    public Curso getCursoById(Long id);
//   
//    public void delete(Curso curso);
//    
//    List<Curso> getCursos();
//    Curso getCursoById(Long id);
//    void saveCursoConPeriodo(Curso curso, Long periodoId);
//    void delete(Long id);
    
    List<Curso> getCursos();
    Curso getCursoById(Long id);
    void save(Curso curso);
    void delete(Long id);
    List<Curso> getCursosByPeriodo(Periodo periodo); // Método para obtener cursos por período
    
    List<Curso> getCursosByProfesorId(Long profesorId); // Nuevo método
    
    void saveCursoConProfesor(Curso curso, Long profesorId); // Añadir este método
    void asociarPeriodoACurso(Long cursoId, Long periodoId); // Nuevo método para asociar periodo
}

