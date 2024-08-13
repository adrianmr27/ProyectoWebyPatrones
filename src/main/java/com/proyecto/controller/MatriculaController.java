package com.proyecto.controller;

import com.proyecto.domain.Curso;
import com.proyecto.domain.Estudiante;
import com.proyecto.domain.Matricula;
import com.proyecto.domain.Periodo;
import com.proyecto.service.MatriculaService;
import com.proyecto.service.CursoService;
import com.proyecto.service.EstudianteService;
import com.proyecto.service.PeriodoService;
import java.util.List;
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
        List<Matricula> matriculas = matriculaService.getMatriculas();
        List<Curso> cursos = cursoService.getCursos();
        List<Estudiante> estudiantes = estudianteService.getEstudiantes();
        List<Periodo> periodos = periodoService.getPeriodos();

        model.addAttribute("matriculas", matriculas);
        model.addAttribute("cursos", cursos);
        model.addAttribute("estudiantes", estudiantes);
        model.addAttribute("periodos", periodos);

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

    @PostMapping("/matricular")
    public String matricular(@RequestParam("estudianteId") Long estudianteId,
            @RequestParam("cursoId") Long cursoId,
            @RequestParam("periodoId") Long periodoId) {

        Estudiante estudiante = estudianteService.findById(estudianteId);
        Curso curso = cursoService.getCursoById(cursoId);
        Periodo periodo = periodoService.getPeriodoById(periodoId);

        Matricula matricula = new Matricula(estudiante, curso, periodo);
        matriculaService.save(matricula);

        return "redirect:/matriculas/listado";
    }

    @PostMapping("/desmatricular")
    public String desmatricular(@RequestParam("matriculaId") Long matriculaId) {
        Matricula matricula = matriculaService.getMatricula(matriculaId);
        if (matricula != null) {
            matriculaService.delete(matricula);
        }
        return "redirect:/matriculas/listado";
    }

}
