package com.proyecto.service;

import com.proyecto.domain.Calificacion;
import java.util.List;

public interface CalificacionService {
    public List<Calificacion> getCalificaciones();
    public Calificacion getCalificacionById(Long id);
    public void save(Calificacion calificacion);
    public void delete(Calificacion calificacion);
}
