package com.proyecto.service;

import com.proyecto.domain.Profesor;
import java.util.List;

public interface ProfesorService {
    
    public List<Profesor> getProfesores();
    
    public Profesor getProfesor(Profesor profesor);
    
    public void save(Profesor profesor);
   
    public void delete(Profesor profesor);
}
