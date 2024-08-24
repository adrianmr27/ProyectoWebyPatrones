package com.proyecto.service;

import com.proyecto.domain.Asistencia;
import com.proyecto.domain.Estudiante;
import java.util.List;
import java.util.Map;

public interface AsistenciaService {

    List<Asistencia> getAsistenciasPorCurso(Long cursoId);

    void registrarAsistencia(Asistencia asistencia);
}