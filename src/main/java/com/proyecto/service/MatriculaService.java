package com.proyecto.service;

import com.proyecto.domain.Matricula;
import java.util.List;

public interface MatriculaService {
    
    public List<Matricula> getMatriculas();
    
    public Matricula getMatricula(Matricula matricula);
    
    public Matricula findByEstudianteId(Long estudianteId); // Método agregado
    
    public void save(Matricula matricula);
   
    public void delete(Matricula matricula);
}
