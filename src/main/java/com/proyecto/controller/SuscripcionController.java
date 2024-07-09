package com.proyecto.controller;

import com.proyecto.domain.Profesor;
import com.proyecto.domain.Persona;
import com.proyecto.domain.Suscripcion;
import com.proyecto.domain.Planes;
import com.proyecto.service.ProfesorService;
import com.proyecto.service.PersonaService;
import com.proyecto.service.SuscripcionService;
import com.proyecto.service.PlanesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Date;
import java.util.Calendar;

@Controller
public class SuscripcionController {

    @Autowired
    private ProfesorService profesorService;

    @Autowired
    private PersonaService personaService;

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
        return "planes/formulario"; // Asegúrate de que el path sea correcto y la vista exista
    }

    @PostMapping("/suscripcion")
    public String procesarSuscripcion(Profesor profesor, @RequestParam("planId") Long planId, Model model) {
        // Guardar la persona primero
        Persona persona = profesor.getPersona();
        personaService.save(persona);
        
        // Guardar el profesor
        profesorService.save(profesor);

        // Crear una nueva suscripción
        Suscripcion suscripcion = new Suscripcion();
        suscripcion.setProfesor(profesor);
        suscripcion.setPlan(planesService.getPlanesById(planId));
        suscripcion.setFechaInicio(new Date());

        // Suscripción es por un año para pruebas
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(suscripcion.getFechaInicio());
        calendar.add(Calendar.YEAR, 1);
        suscripcion.setFechaFin(calendar.getTime());
        suscripcion.setActivo(true);

        // Guardar la suscripción
        suscripcionService.save(suscripcion);

        return "redirect:/";
    }
}