package com.proyecto.controller;

import com.proyecto.domain.Estudiante;
import com.proyecto.service.EstudianteService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
@RequestMapping("/estudiante")
public class EstudiantesController {

    @Autowired
    private EstudianteService estudianteService;

    @GetMapping("/listado")
    public String mostrarEstudiantes(Model model) {
        List<Estudiante> estudiantes = estudianteService.getEstudiantes();
        model.addAttribute("estudiantes", estudiantes);
        model.addAttribute("totalEstudiantes", estudiantes.size());
        return "estudiante/listado";
    }

    @GetMapping("/nuevo")
    public String estudianteNuevo(Estudiante estudiante) {
        return "/estudiante/modifica";
    }


    @PostMapping("/guardar")
    public String estudianteGuardar(Estudiante estudiante) {
        estudianteService.save(estudiante);
        return "redirect:/estudiante/listado";
    }

    @GetMapping("/eliminar/{idEstudiante}")
    public String estudianteEliminar(Estudiante estudiante) {
        estudianteService.delete(estudiante);
        return "redirect:/estudiante/listado";
    }

    @GetMapping("/modificar/{idEstudiante}")
    public String estudianteModificar(Estudiante estudiante, Model model) {
        estudiante = estudianteService.getEstudiante(estudiante);
        model.addAttribute("estudiante", estudiante);
        return "/estudiante/modifica";
    }
/*
    @GetMapping("/modificar/{idEstudiante}")
    public String estudianteModificar(@PathVariable Long idEstudiante, Model model) {
        Estudiante estudiante = estudianteService.findById(idEstudiante);
        model.addAttribute("estudiante", estudiante);
        return "estudiantes/modifica";
  
    }

    @GetMapping("/modificar/{idEstudiante}")
    public String estudianteModificar(@PathVariable Long idEstudiante, Model model) {
        Estudiante estudiante = estudianteService.findById(idEstudiante);
        model.addAttribute("estudiante", estudiante);
    }*/

    @GetMapping("/buscarEstudiante")
    public String buscarEstudiante(@RequestParam("q") String nombre, Model model) {
        List<Estudiante> estudiantes = estudianteService.buscar(nombre);

        model.addAttribute("estudiantes", estudiantes);

        model.addAttribute("resultados", resultados);
        return "resultadosBusqueda"; // Nombre de la vista que mostrará los resultados de la búsqueda

    }

}
