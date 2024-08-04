package com.proyecto.service;

import com.proyecto.domain.Persona;
import java.util.List;

public interface PersonaService {
    
    public List<Persona> getPersonas();
    
    public Persona getPersona(Persona persona);
    
    public Persona findByCorreo(String correo); // Método agregado

    public Persona findByCorreoExcludingId(String correo, Long id); // Método agregado
    
    public void save(Persona persona);
   
    public void delete(Persona persona);
}
