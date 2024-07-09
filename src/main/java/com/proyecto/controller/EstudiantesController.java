package com.proyecto.controller;

import com.proyecto.domain.Estudiante;
import com.proyecto.service.EstudianteService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
@RequestMapping("/estudiantes")
public class EstudiantesController {

    @Autowired
    private EstudianteService estudianteService;

    @GetMapping
    public String mostrarEstudiantes(Model model) {
        List<Estudiante> lista = estudianteService.getEstudiantes();
        model.addAttribute("estudiantes", lista);
        model.addAttribute("totalEstudiantes", lista.size());
        return "estudiantes/estudiantes";
    }

    @GetMapping("/nuevo")
    public String estudianteNuevo(Estudiante estudiante) {
        return "/estudiantes/modifica";
    }

    @PostMapping("/guardar")
    public String estudianteGuardar(Estudiante estudiante) {
        estudianteService.save(estudiante);
        return "redirect:/estudiantes";
    }

    @DeleteMapping("/eliminar/{idEstudiante}")
    public String estudianteEliminar(Estudiante estudiante) {
        estudianteService.delete(estudiante);
        return "redirect:/estudiantes";
    }

    @GetMapping("/modificar/{idEstudiante}")
    public String estudianteModificar(Estudiante estudiante, Model model) {
        estudiante = estudianteService.getEstudiante(estudiante);
        model.addAttribute("estudiante", estudiante);
        return "/estudiantes/modifica";
    }
//    @GetMapping("/modificar/{idEstudiante}")
//    public String estudianteModificar(@PathVariable Long idEstudiante, Model model) {
//        Estudiante estudiante = estudianteService.findById(idEstudiante);
//        model.addAttribute("estudiante", estudiante);
//        return "estudiantes/modifica";
//    }

//    @GetMapping("/modificar/{idEstudiante}")
//    public String estudianteModificar(@PathVariable Long idEstudiante, Model model) {
//        Estudiante estudiante = estudianteService.findById(idEstudiante);
//        model.addAttribute("estudiante", estudiante);
//        return "/estudiantes/modifica";
//    }

    @GetMapping("/buscarEstudiante")
    public String buscarEstudiante(@RequestParam("q") String query, Model model) {
        List<Estudiante> estudiantes = estudianteService.getEstudiantes();

        // Filtrar estudiantes por nombre
        List<Estudiante> resultados = estudiantes.stream()
                .filter(estudiante -> estudiante.getPersona().getNombre().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());

        model.addAttribute("resultados", resultados);
        return "resultadosBusqueda"; // Nombre de la vista que mostrará los resultados de la búsqueda
    }

}