package com.proyecto.service.impl;

import com.proyecto.dao.ProfesorDao;
import com.proyecto.domain.Profesor;
import com.proyecto.service.ProfesorService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProfesorServiceImpl implements ProfesorService {

    @Autowired
    private ProfesorDao profesorDao;

    @Override
    @Transactional(readOnly = true)
    public List<Profesor> getProfesores() {
        return profesorDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Profesor getProfesor(Profesor profesor) {
        return profesorDao.findById(profesor.getId())
                .orElse(null);
    }

    @Override
    @Transactional
    public void save(Profesor profesor) {
        profesorDao.save(profesor);
    }

    @Override
    @Transactional
    public void delete(Profesor profesor) {
        profesorDao.delete(profesor);
    }
}