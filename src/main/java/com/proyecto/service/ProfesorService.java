package com.proyecto.service;

import com.proyecto.domain.Profesor;
import java.util.List;

public interface ProfesorService {
    
    public List<Profesor> getProfesores();
    
    public Profesor getProfesor(Profesor profesor);
    
    public void save(Profesor profesor);
   
    public void delete(Profesor profesor);
    
    List<Profesor> getAllProfesores();
    
    // Se obtiene un Profesor, a partir del usuario de un profesor
    public Profesor getProfesorPorUsuario(String usuario);

    // Se obtiene un Profesor, a partir del usuario y la clave de un usuario
    public Profesor getProfesorPorUsuarioYClave(String usuario, String clave);
    
    // Se obtiene un Profesor, a partir del username y el password de un usuario
    public Profesor getProfesorPorUsuarioOCorreo(String usuario, String correo);
    
    // Se valida si existe un Profesor considerando el usuario
    public boolean existeProfesorPorUsuarioOCorreo(String usuario, String correo);
}
