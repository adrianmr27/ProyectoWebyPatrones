package com.proyecto.controller;

import com.proyecto.domain.Calificacion;
import com.proyecto.service.CalificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/calificaciones")
public class CalificacionController {

    @Autowired
    private CalificacionService calificacionService;

    @GetMapping("/list")
    public String listCalificaciones(Model model) {
        List<Calificacion> calificaciones = calificacionService.getCalificaciones();
        model.addAttribute("calificaciones", calificaciones);
        return "calificaciones/list";
    }

    @GetMapping("/add")
    public String addCalificacionForm(Model model) {
        model.addAttribute("calificacion", new Calificacion());
        return "calificaciones/form";
    }

    @PostMapping("/save")
    public String saveCalificacion(@ModelAttribute("calificacion") Calificacion calificacion) {
        calificacionService.save(calificacion);
        return "redirect:/calificaciones/list";
    }

    @GetMapping("/edit/{id}")
    public String editCalificacionForm(@PathVariable("id") Long id, Model model) {
        Calificacion calificacion = calificacionService.getCalificacionById(id);
        model.addAttribute("calificacion", calificacion);
        return "calificaciones/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteCalificacion(@PathVariable("id") Long id) {
        calificacionService.deleteCalificacionById(id);
        return "redirect:/calificaciones/list";
    }
}