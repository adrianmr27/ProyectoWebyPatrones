package com.proyecto.controller;

import com.proyecto.domain.Asistencia;
import com.proyecto.domain.Curso;
import com.proyecto.domain.Estudiante;
import com.proyecto.domain.Matricula;
import com.proyecto.service.AsistenciaService;
import com.proyecto.service.CursoService;
import com.proyecto.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/asistencia")
public class AsistenciaController {

    @Autowired
    private CursoService cursoService;

    @Autowired
    private EstudianteService estudianteService;

    @Autowired
    private AsistenciaService asistenciaService;

    @GetMapping("/curso")
    public String seleccionarCurso(Model model) {
        List<Curso> cursos = cursoService.getCursos();
        model.addAttribute("cursos", cursos);
        return "asistencia/curso"; // Vista para seleccionar el curso
    }

    @GetMapping("/estudiantes")
    public String listarEstudiantesPorCurso(@RequestParam("cursoId") Long cursoId, Model model) {
        List<Estudiante> estudiantes = estudianteService.getEstudiantesPorCurso(cursoId);
        model.addAttribute("estudiantes", estudiantes);
        model.addAttribute("cursoId", cursoId);
        return "asistencia/estudiantes"; // Vista para pasar asistencia
    }
    
}