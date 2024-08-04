package com.proyecto.service.impl;

import com.proyecto.dao.MatriculaDao;
import com.proyecto.domain.Matricula;
import com.proyecto.service.MatriculaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MatriculaServiceImpl implements MatriculaService {

    @Autowired
    private MatriculaDao matriculaDao;

    @Override
    @Transactional(readOnly = true)
    public List<Matricula> getMatriculas() {
        return matriculaDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Matricula getMatricula(Matricula matricula) {
        return matriculaDao.findById(matricula.getId())
                .orElse(null);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Matricula findByEstudianteId(Long estudianteId) {
        return matriculaDao.findByEstudianteId(estudianteId);
    }

    @Override
    @Transactional
    public void save(Matricula matricula) {
        matriculaDao.save(matricula);
    }

    @Override
    @Transactional
    public void delete(Matricula matricula) {
        matriculaDao.delete(matricula);
    }
}