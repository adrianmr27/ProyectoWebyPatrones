package com.proyecto.service.impl;

import com.proyecto.dao.CursoDao;
import com.proyecto.domain.Curso;
import com.proyecto.domain.Matricula;
import com.proyecto.domain.Periodo;
import com.proyecto.service.CursoService;
import com.proyecto.service.MatriculaService;
import com.proyecto.service.PeriodoService;
import java.util.List;
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
    
//    @Override
//    @Transactional(readOnly = true)
//    public List<Curso> getCursos() {
//        return cursoDao.findAll();
//    }

    @Override
    public List<Curso> getCursos() {
        List<Curso> cursos = cursoDao.findAll();
        for (Curso curso : cursos) {
            List<Matricula> matriculas = matriculaService.getMatriculasByCursoId(curso.getId());
            if (!matriculas.isEmpty()) {
                Periodo periodo = matriculas.get(0).getPeriodo();
                curso.setPeriodo(periodo); // Asigna el periodo del primer registro de matricula
            }
        }
        return cursos;
    }
    
    @Override
    @Transactional(readOnly = true)
    public Curso getCursoById(Long id) {
        Curso curso = cursoDao.findById(id).orElse(null);
        if (curso != null) {
            List<Matricula> matriculas = matriculaService.findByCurso(curso);
            // Establece el período en el curso si es necesario
            // curso.setPeriodos(matriculas.stream().map(Matricula::getPeriodo).collect(Collectors.toList()));
        }
        return curso;
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

    @Override
    public Curso getCurso(Curso curso) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}