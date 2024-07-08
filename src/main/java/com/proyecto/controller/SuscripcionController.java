package com.proyecto.controller;

import com.proyecto.domain.Profesor;
import com.proyecto.domain.Suscripcion;
import com.proyecto.domain.Planes;
import com.proyecto.service.ProfesorService;
import com.proyecto.service.SuscripcionService;
import com.proyecto.service.PlanesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Date;  // Importación de Date
import java.util.Calendar;  // Importación de Calendar

@Controller
public class SuscripcionController {

    @Autowired
    private ProfesorService profesorService;

    @Autowired
    private SuscripcionService suscripcionService;

    @Autowired
    private PlanesService planesService;

    @GetMapping("/suscripcion")
    public String mostrarFormularioSuscripcion(@RequestParam("planId") Long planId, Model model) {
        Planes plan = planesService.getPlanesById(planId);
        model.addAttribute("planes", planesService.getPlanes());
        model.addAttribute("selectedPlan", plan);
        model.addAttribute("profesor", new Profesor());
        model.addAttribute("suscripcion", new Suscripcion());
        return "planes/formulario"; // Asegúrate de que el path sea correcto y la vista existe
    }

    @PostMapping("/suscripcion")
    public String procesarSuscripcion(Profesor profesor, @RequestParam("planId") Long planId, Model model) {
        
        // Obtener el plan seleccionado
        Planes plan = planesService.getPlanesById(planId);

        // Guardar el profesor y los datos de la persona
        profesorService.save(profesor);

        // Crear una nueva suscripción
        Suscripcion suscripcion = new Suscripcion();
        suscripcion.setProfesor(profesor);
        suscripcion.setPlan(plan);
        suscripcion.setFechaInicio(new Date());
        
        // Suponiendo que la suscripción es por un año
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(suscripcion.getFechaInicio());
        calendar.add(Calendar.YEAR, 1);
        suscripcion.setFechaFin(calendar.getTime());
        suscripcion.setActivo(true);

        // Guardar la suscripción
        suscripcionService.save(suscripcion);

        return "redirect:/login"; // Redirigir a la página de inicio de sesión
    }
}