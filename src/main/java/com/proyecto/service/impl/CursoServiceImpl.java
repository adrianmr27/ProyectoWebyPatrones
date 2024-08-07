package com.proyecto.service.impl;

import com.proyecto.dao.CursoDao;
import com.proyecto.domain.Curso;
import com.proyecto.domain.Matricula;
import com.proyecto.domain.Periodo;
import com.proyecto.service.CursoService;
import com.proyecto.service.MatriculaService;
import com.proyecto.service.PeriodoService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CursoServiceImpl implements CursoService {

    @Autowired
    private CursoDao cursoDao;

    @Autowired
    private MatriculaService matriculaService;
    
    @Autowired
    private PeriodoService periodoService;
    
 @Override
    @Transactional(readOnly = true)
    public List<Curso> getCursos() {
        return cursoDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Curso getCursoById(Long id) {
        return cursoDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void save(Curso curso) {
        cursoDao.save(curso);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        cursoDao.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Curso> getCursosByPeriodo(Periodo periodo) {
        List<Matricula> matriculas = matriculaService.findByPeriodo(periodo);
        return matriculas.stream()
                         .map(Matricula::getCurso)
                         .distinct() // Elimina duplicados si hay
                         .collect(Collectors.toList());
    }
}