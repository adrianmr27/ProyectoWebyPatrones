package com.proyecto.service;

import com.proyecto.domain.Curso;
import java.util.List;

public interface CursoService {
    
    public List<Curso> getCursos();
    
    public Curso getCurso(Curso curso);
   
    public void save(Curso curso);
    
    public Curso getCursoById(Long id);
   
    public void delete(Curso curso);
}
