package com.proyecto.controller;

import com.proyecto.domain.Periodo;
import com.proyecto.service.PeriodoService;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/periodos")
public class PeriodoController {

    @Autowired
    private PeriodoService periodoService;

    @GetMapping("/listado")
    public String mostrarPeriodos(Model model) {
        model.addAttribute("periodos", periodoService.getPeriodos());
        return "periodo/listado";
    }

    @GetMapping("/nuevo")
    public String nuevoPeriodo(Model model) {
        model.addAttribute("periodo", new Periodo());
        return "periodo/nuevo";
    }

    @PostMapping("/guardar")
    public String guardarPeriodo(@ModelAttribute("periodo") Periodo periodo) {
        periodoService.save(periodo);
        return "redirect:/periodos/listado";
    }

    @GetMapping("/modifica/{id}")
    public String modificarPeriodo(@PathVariable("id") Long id, Model model) {
        Periodo periodo = periodoService.getPeriodoById(id);
        if (periodo != null) {
            model.addAttribute("periodo", periodo);
            return "periodo/modifica";
        }
        return "redirect:/periodos/listado";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarPeriodo(@PathVariable("id") Long id) {
        Periodo periodo = periodoService.getPeriodoById(id);
        if (periodo != null) {
            periodoService.delete(periodo);
        }
        return "redirect:/periodos/listado";
    }
    
     @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

}