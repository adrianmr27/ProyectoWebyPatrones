package com.proyecto.service;

import com.proyecto.domain.Persona;
import java.util.List;

public interface PersonaService {
    
    public List<Persona> getPersonas();
    
    public Persona getPersona(Persona persona);
    
    public void save(Persona persona);
   
    public void delete(Persona persona);
    
    public Persona findByid(Long id);
    
    
    
}
