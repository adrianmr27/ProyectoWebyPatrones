package com.proyecto.service;

import com.proyecto.domain.Calificacion;
import java.util.List;

public interface CalificacionService {
    public List<Calificacion> getCalificaciones();
    public Calificacion getCalificacion(Calificacion calificacion);
    public void save(Calificacion calificacion);
    public void delete(Calificacion calificacion);
    public Calificacion getCalificacionById(Long id); // Nuevo método
    public void deleteCalificacionById(Long id); // Nuevo método
}
