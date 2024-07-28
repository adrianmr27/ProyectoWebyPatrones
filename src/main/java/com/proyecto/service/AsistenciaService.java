package com.proyecto.service;

import com.proyecto.domain.Asistencia;
import java.util.List;

public interface AsistenciaService {
    List<Asistencia> getAsistencias();
    Asistencia getAsistencia(Asistencia asistencia);  // Si ya está definido
    void save(Asistencia asistencia);
    void delete(Asistencia asistencia);
    Asistencia getAsistenciaById(Long id); // Agregar este método
    void deleteAsistenciaById(Long id); // Asegúrate de tener este método si lo usas
}