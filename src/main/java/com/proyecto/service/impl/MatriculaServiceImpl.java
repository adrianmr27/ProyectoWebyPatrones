package com.proyecto.service.impl;

import com.proyecto.dao.MatriculaDao;
import com.proyecto.domain.Curso;
import com.proyecto.domain.Estudiante;
import com.proyecto.domain.Matricula;
import com.proyecto.domain.Periodo;
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
    public Matricula getMatricula(Long id) {
        return matriculaDao.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Matricula> getMatriculasByCursoId(Long cursoId) {
        return matriculaDao.findByCursoId(cursoId);
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
    
//    @Override
//    @Transactional(readOnly = true)
//    public List<Matricula> findByPeriodo(Periodo periodo) {
//        return matriculaDao.findByPeriodo(periodo); // Implementar este método en el DAO
//    }
    
    @Override
    public Matricula encontrarMatricula(Matricula matricula) {
        return matriculaDao.findById(matricula.getId()).orElse(null);
    }

    @Override
    public List<Matricula> findByPeriodo(Periodo periodo) {
        return matriculaDao.findByPeriodoId(periodo.getId());
    }
    
        // Implementación de los nuevos métodos
    @Override
    @Transactional(readOnly = true)
    public Estudiante findEstudianteByMatriculaId(Long matriculaId) {
        Matricula matricula = matriculaDao.findById(matriculaId).orElse(null);
        return (matricula != null) ? matricula.getEstudiante() : null;
    }

    @Override
    @Transactional(readOnly = true)
    public Curso findCursoByMatriculaId(Long matriculaId) {
        Matricula matricula = matriculaDao.findById(matriculaId).orElse(null);
        return (matricula != null) ? matricula.getCurso() : null;
    }

}



