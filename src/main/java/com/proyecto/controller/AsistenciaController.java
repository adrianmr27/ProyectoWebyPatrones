package com.proyecto.controller;

import com.proyecto.domain.Asistencia;
import com.proyecto.service.AsistenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/asistencias")          
public class AsistenciaController {

    @Autowired
    private AsistenciaService asistenciaService;

    @GetMapping("/registrar")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("asistencia", new Asistencia());
        return "asistencias/registrar";
    }

    @PostMapping("/registrar")
    public String registrarAsistencia(@ModelAttribute Asistencia asistencia) {
        asistenciaService.save(asistencia);
        return "redirect:/asistencias";
    }

    @GetMapping("/matricula/{matriculaId}")
    public String obtenerAsistenciasPorMatricula(@PathVariable Long matriculaId, Model model) {
        List<Asistencia> asistencias = asistenciaService.getAsistenciasPorMatricula(matriculaId);
        model.addAttribute("asistencias", asistencias);
        return "asistencias/listado";
    }

    @GetMapping
    public String listarAsistencias(Model model) {
        List<Asistencia> asistencias = asistenciaService.getAsistencias();
        model.addAttribute("asistencias", asistencias);
        return "asistencias/listado";
    }
}
