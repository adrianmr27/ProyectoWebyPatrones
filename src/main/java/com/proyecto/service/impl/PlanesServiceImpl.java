package com.proyecto.service.impl;

import com.proyecto.dao.PlanesDao;
import com.proyecto.domain.Planes;
import com.proyecto.service.PlanesService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

@Service
public class PlanesServiceImpl implements PlanesService{

    @Autowired
    private PlanesDao planesDao;

    @Override
    @Transactional(readOnly = true)
    public List<Planes> getPlanes() {
        return planesDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Planes getPlanes(Planes plan) {
        return planesDao.findById(plan.getId())
                .orElse(null);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Planes getPlanesById(Long id) {
        return planesDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void save(Planes plan) {
        planesDao.save(plan);
    }

    @Override
    @Transactional
    public void delete(Planes plan) {
        planesDao.delete(plan);
    }
    
}
