
package com.proyecto.service;

import com.proyecto.domain.Suscripcion;
import java.util.List;

public interface SuscripcionService {
    public List<Suscripcion> getSuscripciones();
    
    public Suscripcion getSuscripcion(Suscripcion suscripcion);
    
    public void save(Suscripcion suscripcion);
    
    public void delete(Suscripcion suscripcion);
}
