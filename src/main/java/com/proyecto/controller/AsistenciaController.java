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

    @GetMapping("/listado")
    public String listAsistencias(Model model) {
        List<Asistencia> asistencias = asistenciaService.getAsistencias();
        model.addAttribute("asistencias", asistencias);
        return "asistencias/listado";
    }

    @GetMapping("/add")
    public String addAsistenciaForm(Model model) {
        model.addAttribute("asistencia", new Asistencia());
        return "asistencias/form";
    }

    @PostMapping("/save")
    public String saveAsistencia(@ModelAttribute("asistencia") Asistencia asistencia) {
        asistenciaService.save(asistencia);
        return "redirect:/asistencias/listado";
    }

    @GetMapping("/edit/{id}")
    public String editAsistenciaForm(@PathVariable("id") Long id, Model model) {
        Asistencia asistencia = asistenciaService.getAsistenciaById(id);
        model.addAttribute("asistencia", asistencia);
        return "asistencias/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteAsistencia(@PathVariable("id") Long id) {
        asistenciaService.deleteAsistenciaById(id);
        return "redirect:/asistencias/list";
    }
}
