package com.proyecto.service.impl;

import com.proyecto.dao.AsistenciaDao;
import com.proyecto.domain.Asistencia;
import com.proyecto.service.AsistenciaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AsistenciaServiceImpl implements AsistenciaService {

    @Autowired
    private AsistenciaDao asistenciaDao;

    @Override
    @Transactional(readOnly = true)
    public List<Asistencia> getAsistencias() {
        return asistenciaDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Asistencia getAsistencia(Asistencia asistencia) {
        return asistenciaDao.findByFecha(asistencia.getFecha());
    }

    @Override
    @Transactional
    public void save(Asistencia asistencia) {
        asistenciaDao.save(asistencia);
    }

    @Override
    @Transactional
    public void delete(Asistencia asistencia) {
        asistenciaDao.delete(asistencia);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Asistencia> getAsistenciasPorMatricula(Long matriculaId) {
        return asistenciaDao.findByMatriculaid(matriculaId);
    }
}
    
