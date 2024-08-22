
package com.proyecto.service;

import com.proyecto.domain.Profesor;
import jakarta.mail.MessagingException;
import org.springframework.ui.Model;

public interface RegistroService {

    public Model activar(Model model, String usuario, String clave);

     public Model crearProfesor(Model model, Profesor profesor, Long planId) throws MessagingException;
    
    public void activar(Profesor usuario);
    
    public Model recordarUsuarioProfesor(Model model, Profesor usuario) throws MessagingException;
    
}