package com.proyecto.controller;

import com.proyecto.domain.Calificacion;
import com.proyecto.domain.Matricula;
import com.proyecto.service.CalificacionService;
import com.proyecto.service.MatriculaService;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;

@Controller
@RequestMapping("/calificaciones")
public class CalificacionController {

    @Autowired
    private CalificacionService calificacionService;

    @Autowired
    private MatriculaService matriculaService;

    @GetMapping("/list")
    public String listCalificaciones(Model model) {
        List<Calificacion> calificaciones = calificacionService.getCalificaciones();
        model.addAttribute("calificaciones", calificaciones);
        return "calificaciones/list";
    }

    @GetMapping("/add")
    public String addCalificacionForm(Model model) {
        model.addAttribute("calificacion", new Calificacion());
        List<Matricula> matriculas = matriculaService.getMatriculas();
        model.addAttribute("matriculas", matriculas);
        return "calificaciones/form";
    }

    @GetMapping("/add/{id}")
    @ResponseBody
    public ResponseEntity<Map<String, String>> obtenerDetallesPorMatriculaId(@PathVariable("id") Long id) {
        Matricula matricula = matriculaService.getMatricula(id);
        if (matricula == null) {
            return ResponseEntity.notFound().build();
        }

        Map<String, String> detalles = new HashMap<>();
        detalles.put("curso", matricula.getCurso().getNombre());
        detalles.put("estudiante", matricula.getEstudiante().getPersona().getNombre());

        return ResponseEntity.ok(detalles);
    }

    @PostMapping("/save")
    public String saveCalificacion(@ModelAttribute("calificacion") Calificacion calificacion) {
        calificacionService.save(calificacion);
        return "redirect:/calificaciones/list";
    }

    @GetMapping("/edit/{id}")
    public String editCalificacionForm(@PathVariable("id") Long id, Model model) {
        // Obtener la calificación por su ID
        Calificacion calificacion = calificacionService.getCalificacionById(id);

        if (calificacion == null) {
            // Manejar el caso donde no se encuentra la calificación
            model.addAttribute("error", "La calificación no existe");
            return "error";
        }

        // Obtener la matrícula asociada a la calificación
        Matricula matricula = calificacionService.getMatriculaByCalificacionId(id);

        if (matricula != null) {
            // Añadir la información del curso, estudiante y matrícula al modelo
            model.addAttribute("nombreEstudiante", matricula.getEstudiante().getPersona().getNombre());
            model.addAttribute("nombreCurso", matricula.getCurso().getNombre());
            model.addAttribute("matriculaId", matricula.getId());
        } else {
            // En caso de que la matrícula no esté disponible
            model.addAttribute("nombreEstudiante", "No disponible");
            model.addAttribute("nombreCurso", "No disponible");
            model.addAttribute("matriculaId", "No disponible");
        }

        // Añadir la calificación al modelo para la edición de notas
        model.addAttribute("calificacion", calificacion);

        return "calificaciones/edit"; // Cargar la nueva vista `edit.html`
    }

    @GetMapping("/delete/{id}")
    public String deleteCalificacion(@PathVariable("id") Long id) {
        calificacionService.delete(calificacionService.getCalificacionById(id));
        return "redirect:/calificaciones/list";
    }
}
