package com.proyecto.controller;

import com.proyecto.domain.Curso;
import com.proyecto.domain.Estudiante;
import com.proyecto.domain.Matricula;
import com.proyecto.domain.Persona;
import com.proyecto.domain.Periodo;
import com.proyecto.service.CursoService;
import com.proyecto.service.EstudianteService;
import com.proyecto.service.MatriculaService;
import com.proyecto.service.PersonaService;
import com.proyecto.service.PeriodoService;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

//    @Autowired
//    private PersonaService personaService;
//
//    @Autowired
//    private CursoService cursoService;
//
    @Autowired
    private MatriculaService matriculaService;
//
    @Autowired
    private PeriodoService periodoService;

    @GetMapping("/listado")
    public String mostrarCursos(Model model) {
        List<Curso> cursos = cursoService.getCursos();
        model.addAttribute("cursos", cursos);
        return "curso/listado";
    }

    @GetMapping("/nuevo")
    public String nuevoCursoForm(Model model) {
        model.addAttribute("curso", new Curso());
        return "curso-form";
    }

    @PostMapping("/guardar")
    public String guardarCurso(@ModelAttribute("curso") Curso curso) {
        cursoService.save(curso);
        return "redirect:/cursos/listado";
    }

    @GetMapping("/editar/{id}")
    public String editarCursoForm(@PathVariable("id") Long id, Model model) {
        Curso curso = cursoService.getCursoById(id);
        model.addAttribute("curso", curso);
        return "curso-form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCurso(@PathVariable("id") Long id) {
        Curso curso = cursoService.getCursoById(id);
        cursoService.delete(curso);
        return "redirect:/cursos/listado";
    }
}
