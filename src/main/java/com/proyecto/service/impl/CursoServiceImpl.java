package com.proyecto.service.impl;

import com.proyecto.dao.CursoDao;
import com.proyecto.domain.Curso;
import com.proyecto.service.CursoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CursoServiceImpl implements CursoService {

    @Autowired
    private CursoDao cursoDao;

    @Override
    @Transactional(readOnly = true)
    public List<Curso> getCursos() {
        return cursoDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Curso getCurso(Curso curso) {
        return cursoDao.findById(curso.getId())
                .orElse(null);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Curso getCursoById(Long id) {
        return cursoDao.findById(id)
                .orElse(null);
    }

    @Override
    @Transactional
    public void save(Curso curso) {
        cursoDao.save(curso);
    }

    @Override
    @Transactional
    public void delete(Curso curso) {
        cursoDao.delete(curso);
    }

}
