package com.proyecto.controller;

import com.proyecto.domain.Curso;
import com.proyecto.domain.Estudiante;
import com.proyecto.domain.Matricula;
import com.proyecto.domain.Persona;
import com.proyecto.domain.Periodo;
import com.proyecto.domain.Profesor;
import com.proyecto.service.CursoService;
import com.proyecto.service.EstudianteService;
import com.proyecto.service.MatriculaService;
import com.proyecto.service.PersonaService;
import com.proyecto.service.PeriodoService;
import com.proyecto.service.ProfesorService;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

//@Controller
//@Slf4j
//@RequestMapping("/cursos")
//public class CursoController {
//
//    @Autowired
//    private CursoService cursoService;
//
//    @Autowired
//    private PeriodoService periodoService;
//
//    @Autowired
//    private MatriculaService matriculaService;
//    
//    @Autowired
//
//    @GetMapping("/listado")
//    public String mostrarCursos(Model model) {
//        List<Curso> cursos = cursoService.getCursos();
//        List<Periodo> periodos = periodoService.getPeriodos(); // Cargar los periodos
//
//        model.addAttribute("cursos", cursos);
//        model.addAttribute("periodos", periodos); // Añadir los periodos al modelo
//        model.addAttribute("curso", new Curso());
//
//        return "curso/listado";
//    }
//    //viejo
////   @GetMapping("/listado")
////    public String mostrarCursos(Model model) {
////        model.addAttribute("cursos", cursoService.getCursos());
////        model.addAttribute("curso", new Curso());
////        return "curso/listado";
////    }
//
//    @PostMapping("/guardar")
//    public String guardarCurso(@ModelAttribute("curso") Curso curso,
//            RedirectAttributes redirectAttributes) {
//        cursoService.save(curso);
//        redirectAttributes.addFlashAttribute("message", "Curso guardado exitosamente");
//        return "redirect:/cursos/listado";
//    }
//
//    @GetMapping("/modificar/{idCurso}")
//    public String modificarCurso(@PathVariable("idCurso") Long idCurso, Model model) {
//        Curso curso = cursoService.getCursoById(idCurso);
//        if (curso != null) {
//
//            List<Periodo> periodos = periodoService.getPeriodos();
//
//            model.addAttribute("curso", curso);
//            model.addAttribute("periodos", periodoService.getPeriodos());
//        }
//        return "curso/modificar";
//    }
//
//    @PostMapping("/actualizar")
//    public String actualizarCurso(@ModelAttribute("curso") Curso curso,
//            RedirectAttributes redirectAttributes) {
//        cursoService.save(curso);
//        redirectAttributes.addFlashAttribute("message", "Curso actualizado exitosamente");
//        return "redirect:/cursos/listado";
//    }
//
//    @PostMapping("/asociar-periodo")
//    public String asociarPeriodoACurso(@RequestParam("cursoId") Long cursoId,
//            @RequestParam("periodoId") Long periodoId,
//            RedirectAttributes redirectAttributes) {
//        Curso curso = cursoService.getCursoById(cursoId);
//        Periodo periodo = periodoService.getPeriodoById(periodoId);
//
//        if (curso != null && periodo != null) {
//            Matricula matricula = new Matricula();
//            matricula.setCurso(curso);
//            matricula.setPeriodo(periodo);
//            matriculaService.save(matricula);
//
//            redirectAttributes.addFlashAttribute("message", "Período asociado exitosamente al curso");
//        } else {
//            redirectAttributes.addFlashAttribute("error", "Curso o período no encontrados");
//        }
//
//        return "redirect:/cursos/listado";
//    }
//}
@Controller
@Slf4j
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @Autowired
    private PeriodoService periodoService;

    @Autowired
    private MatriculaService matriculaService;

    @Autowired
    private ProfesorService profesorService;

    @GetMapping("/listado")
    public String mostrarCursos(Model model) {
        model.addAttribute("cursos", cursoService.getCursos());
        model.addAttribute("curso", new Curso());
        model.addAttribute("profesores", profesorService.getProfesores());
        return "curso/listado";
    }

    @PostMapping("/guardar")
    public String guardarCurso(@ModelAttribute("curso") Curso curso,
            @RequestParam("profesorId") Long profesorId,
            RedirectAttributes redirectAttributes) {
        if (curso.getCantidadSesiones() == null) {
            curso.setCantidadSesiones(4); // Establecer cantidad_sesiones en 4 si es nulo
        }

        cursoService.saveCursoConProfesor(curso, profesorId);
        redirectAttributes.addFlashAttribute("message", "Curso guardado exitosamente");
        return "redirect:/cursos/listado";
    }

    @GetMapping("/modifica/{idCurso}")
    public String modificarCurso(@PathVariable("idCurso") Long idCurso, Model model) {
        Curso curso = cursoService.getCursoById(idCurso);
        if (curso != null) {
            model.addAttribute("curso", curso);
            model.addAttribute("profesores", profesorService.getProfesores());
        }
        return "curso/modifica";
    }

    @PostMapping("/actualizar")
    public String actualizarCurso(@ModelAttribute("curso") Curso curso,
            @RequestParam("profesorId") Long profesorId,
            RedirectAttributes redirectAttributes) {
        cursoService.saveCursoConProfesor(curso, profesorId);
        redirectAttributes.addFlashAttribute("message", "Curso actualizado exitosamente");
        return "redirect:/cursos/listado";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCurso(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        cursoService.eliminarCursoPorId(id);
        redirectAttributes.addFlashAttribute("message", "Curso eliminado exitosamente");
        return "redirect:/cursos/listado";
    }

    @GetMapping("/listado2")
    public String mostrarListado2(Model model) {
        model.addAttribute("cursos", cursoService.getCursos());
        return "curso/listado2";  // Asegúrate de que este sea el nombre correcto del archivo HTML
    }
}
