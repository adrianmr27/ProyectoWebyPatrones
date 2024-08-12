package com.proyecto.controller;

import com.proyecto.domain.Matricula;
import com.proyecto.service.MatriculaService;
import com.proyecto.service.CursoService;
import com.proyecto.service.EstudianteService;
import com.proyecto.service.PeriodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/matriculas")
public class MatriculaController {

    @Autowired
    private MatriculaService matriculaService;
    
    @Autowired
    private CursoService cursoService;
    
    @Autowired
    private EstudianteService estudianteService;
    
    @Autowired
    private PeriodoService periodoService;

    @GetMapping("/listado")
    public String listarMatriculas(Model model) {
        var matriculas = matriculaService.getMatriculas();
        model.addAttribute("matriculas", matriculas);
        return "matricula/listado";
    }

    @GetMapping("/nuevo")
    public String nuevaMatricula(Model model) {
        var cursos = cursoService.getCursos();
        var estudiantes = estudianteService.getEstudiantes();
        var periodos = periodoService.getPeriodos();
        model.addAttribute("matricula", new Matricula());
        model.addAttribute("cursos", cursos);
        model.addAttribute("estudiantes", estudiantes);
        model.addAttribute("periodos", periodos);
        return "matricula/modifica";
    }

    @PostMapping("/guardar")
    public String guardarMatricula(@ModelAttribute("matricula") Matricula matricula, BindingResult result, Model model) {
        if (result.hasErrors()) {
            var cursos = cursoService.getCursos();
            var estudiantes = estudianteService.getEstudiantes();
            var periodos = periodoService.getPeriodos();
            model.addAttribute("cursos", cursos);
            model.addAttribute("estudiantes", estudiantes);
            model.addAttribute("periodos", periodos);
            return "matricula/modifica";
        }
        matriculaService.save(matricula);
        return "redirect:/matriculas/listado";
    }

    @GetMapping("/modificar/{id}")
    public String modificarMatricula(@PathVariable("id") Long id, Model model) {
        Matricula matricula = matriculaService.getMatricula(id);
        if (matricula != null) {
            model.addAttribute("matricula", matricula);
            var cursos = cursoService.getCursos();
            var estudiantes = estudianteService.getEstudiantes();
            var periodos = periodoService.getPeriodos();
            model.addAttribute("cursos", cursos);
            model.addAttribute("estudiantes", estudiantes);
            model.addAttribute("periodos", periodos);
            return "matricula/modifica";
        }
        return "redirect:/matriculas/listado";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarMatricula(@PathVariable("id") Long id) {
        Matricula matricula = matriculaService.getMatricula(id);
        if (matricula != null) {
            matriculaService.delete(matricula);
        }
        return "redirect:/matriculas/listado";
    }
}
