package com.proyecto.service;

import com.proyecto.domain.Asistencia;
import java.util.List;

public interface AsistenciaService {
    
    public List<Asistencia> getAsistencias();
    
    public Asistencia getAsistencia(Asistencia asistencia);
    
    public void save(Asistencia asistencia);
   
    public void delete(Asistencia asistencia);

    public List<Asistencia> getAsistenciasPorMatricula(Long matriculaId);
}
