package com.proyecto.service;

import com.proyecto.domain.Calificacion;
import com.proyecto.domain.Matricula;
import java.util.List;

public interface CalificacionService {
    public List<Calificacion> getCalificaciones();
    public Calificacion getCalificacionById(Long id);
    public void save(Calificacion calificacion);
    public void delete(Calificacion calificacion);
    
    // Nuevo método para obtener la matrícula por el ID de la calificación
    public Matricula getMatriculaByCalificacionId(Long id);
}

