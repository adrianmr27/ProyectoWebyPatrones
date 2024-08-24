package com.proyecto.service.impl;

import com.proyecto.dao.EstudianteDao;
import com.proyecto.domain.Estudiante;
import com.proyecto.service.EstudianteService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EstudianteServiceImpl implements EstudianteService {
    
    @Autowired
    private EstudianteDao estudianteDao;

    @Override
    @Transactional(readOnly = true)
    public List<Estudiante> getEstudiantes() {
        return estudianteDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Estudiante getEstudiante(Estudiante estudiante) {
        return estudianteDao.findById(estudiante.getId())
                .orElse(null);
    }

    @Override
    @Transactional
    public void save(Estudiante estudiante) {
        estudianteDao.save(estudiante);
    }

    @Override
    @Transactional
    public void delete(Estudiante estudiante) {
        estudianteDao.delete(estudiante);
    }
    
    @Override
    public List<Estudiante> buscar(String nombre) {
        
        List<Estudiante> estudiantes = estudianteDao.findAll();
        
        //filtrado
        List<Estudiante> resultados = estudiantes.stream()
                .filter(estudiante -> estudiante.getPersona().getNombre().toLowerCase().contains(nombre.toLowerCase()))
                .collect(Collectors.toList());
        return resultados;
    }

    @Override
    public Estudiante findById(Long idEstudiante) {
        return estudianteDao.findById(idEstudiante).orElse(null);
    }

     @Override
    public List<Estudiante> getEstudiantesPorCurso(long cursoId) {
        return estudianteDao.findByCursoId(cursoId);
    }

    
}
