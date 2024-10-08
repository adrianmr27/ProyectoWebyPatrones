package com.proyecto.service;

import com.proyecto.domain.Estudiante;
import java.util.List;

public interface EstudianteService {
    
    public List<Estudiante> getEstudiantes();
    
    public Estudiante getEstudiante(Estudiante estudiante);
    
    public void save(Estudiante estudiante);
   
    public void delete(Estudiante estudiante);

    public List<Estudiante> buscar(String nombre);

    public Estudiante findById(Long idEstudiante);

    public List<Estudiante> getEstudiantesPorCurso(long cursoId);

}
