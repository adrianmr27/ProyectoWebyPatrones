
package com.proyecto.service.impl;

import com.proyecto.dao.SuscripcionDao;
import com.proyecto.domain.Suscripcion;
import com.proyecto.service.SuscripcionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SuscripcionServiceImpl implements SuscripcionService {

    @Autowired
    private SuscripcionDao suscripcionDao;

    @Override
    @Transactional(readOnly = true)
    public List<Suscripcion> getSuscripciones() {
        return suscripcionDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Suscripcion getSuscripcion(Suscripcion suscripcion) {
        return suscripcionDao.findById(suscripcion.getIdSuscripcion()).orElse(null);
    }

    @Override
    @Transactional
    public void save(Suscripcion suscripcion) {
        suscripcionDao.save(suscripcion);
    }

    @Override
    @Transactional
    public void delete(Suscripcion suscripcion) {
        suscripcionDao.delete(suscripcion);
    }
}