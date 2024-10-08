package com.proyecto.service.impl;

import com.proyecto.dao.CalificacionDao;
import com.proyecto.domain.Calificacion;
import com.proyecto.domain.Matricula;
import com.proyecto.service.CalificacionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CalificacionServiceImpl implements CalificacionService {

    @Autowired
    private CalificacionDao calificacionDao;

    @Override
    @Transactional(readOnly = true)
    public List<Calificacion> getCalificaciones() {
        return calificacionDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Calificacion getCalificacionById(Long id) {
        return calificacionDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void save(Calificacion calificacion) {
        calificacionDao.save(calificacion);
    }

    @Override
    @Transactional
    public void delete(Calificacion calificacion) {
        calificacionDao.delete(calificacion);
    }
    
    // Implementación del nuevo método
    @Override
    @Transactional(readOnly = true)
    public Matricula getMatriculaByCalificacionId(Long id) {
        Calificacion calificacion = calificacionDao.findById(id).orElse(null);
        if (calificacion != null) {
            return calificacion.getMatriculaId();  // Aquí se asume que getMatriculaId() es el método que devuelve la matrícula asociada.
        }
        return null;
    }
}

