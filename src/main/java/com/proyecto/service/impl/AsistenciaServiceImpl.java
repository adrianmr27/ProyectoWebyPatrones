package com.proyecto.service.impl;

import com.proyecto.dao.AsistenciaDao;
import com.proyecto.domain.Asistencia;
import com.proyecto.service.AsistenciaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AsistenciaServiceImpl implements AsistenciaService {

    @Autowired
    private AsistenciaDao asistenciaDao;

    @Override
    public List<Asistencia> getAsistenciasPorCurso(Long cursoId) {
        return asistenciaDao.findByCursoId(cursoId);
    }

    @Override
    public void registrarAsistencias(List<Asistencia> asistencias) {
        asistenciaDao.saveAll(asistencias);
    }

}