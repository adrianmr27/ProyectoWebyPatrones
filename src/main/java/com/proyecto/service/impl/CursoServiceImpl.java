package com.proyecto.service.impl;

import com.proyecto.dao.CursoDao;
import com.proyecto.dao.MatriculaDao;
import com.proyecto.dao.ProfesorDao;
import com.proyecto.domain.Curso;
import com.proyecto.domain.Matricula;
import com.proyecto.domain.Periodo;
import com.proyecto.domain.Profesor;
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

    @Autowired
    private ProfesorDao profesorDao; // Inyecta el DAO de Profesor

    @Autowired
    private MatriculaDao matriculaDao; // Inyecta el DAO de Matricula

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

//    @Override
//    @Transactional
//    public void delete(Long id) {
//        cursoDao.deleteById(id);
//    }
    
    @Override
    public void eliminarCursoPorId(Long id) { // Implementación del método de eliminación
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

    //nuevos metodos profesor
    @Override
    @Transactional(readOnly = true)
    public List<Curso> getCursosByProfesorId(Long profesorId) {
        return cursoDao.findByProfesorId(profesorId);
    }

    @Transactional
    public void saveCursoConProfesor(Curso curso, Long profesorId) {
        curso.setProfesorId(profesorId); // Establece el ID del profesor en el curso
        cursoDao.save(curso);
    }

//    @Override
//    @Transactional
//    public void asociarPeriodoACurso(Long cursoId, Long periodoId) {
//        Curso curso = cursoDao.findById(cursoId).orElse(null);
//        Periodo periodo = periodoService.getPeriodoById(periodoId);
//
//        if (curso != null && periodo != null) {
//            Matricula matricula = new Matricula();
//            matricula.setCurso(curso);
//            matricula.setPeriodo(periodo);
//            matriculaService.save(matricula);
//        }
//    }

}
