package com.proyecto.controller;

import com.proyecto.domain.Profesor;
import com.proyecto.domain.Persona;
import com.proyecto.domain.Suscripcion;
import com.proyecto.domain.Planes;
import com.proyecto.domain.Rol;
import com.proyecto.service.ProfesorService;
import com.proyecto.service.PersonaService;
import com.proyecto.service.SuscripcionService;
import com.proyecto.service.PlanesService;
import com.proyecto.service.RegistroService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Date;
import java.util.Calendar;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class SuscripcionController {

    @Autowired
    private ProfesorService profesorService;

    @Autowired
    private PersonaService personaService;

    @Autowired
    private SuscripcionService suscripcionService;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private RegistroService registroService;

    @Autowired
    private PlanesService planesService;

    @GetMapping("/suscripcion")
    public String mostrarFormularioSuscripcion(@RequestParam("planId") Long planId, Model model) {
        Planes plan = planesService.getPlanesById(planId);
        model.addAttribute("planes", planesService.getPlanes());
        model.addAttribute("selectedPlan", plan);
        model.addAttribute("profesor", new Profesor());
        model.addAttribute("suscripcion", new Suscripcion());
        return "planes/formulario";
    }

    @PostMapping("/suscripcion")
    public String procesarSuscripcion(Profesor profesor, @RequestParam("planId") Long planId, Model model) {
        try {
            // Llama al método crearProfesor para manejar la suscripción y envío de correo
            registroService.crearProfesor(model, profesor, planId);
            if (model.containsAttribute("error")) {
                // Volver a mostrar el formulario de suscripción con el mensaje de error
                model.addAttribute("selectedPlan", planesService.getPlanesById(planId));
                return "planes/formulario";
            }
        } catch (MessagingException e) {
            e.printStackTrace();
            return "redirect:/error";
        }

        return "redirect:/pendienteActivar";
    }

    @GetMapping("/pendienteActivar")
    public String mostrarPendienteActivar() {
        return "planes/pendienteActivar";
    }

    /**
     * Maneja la solicitud GET para activar una cuenta de usuario. Este método
     * agrega el objeto `Profesor` al modelo para que sea accesible desde la
     * vista.
     */
    @GetMapping("/planes/activar/{usuario}/{clave}")
    public String activarCuenta(@PathVariable("usuario") String usuario,
            @PathVariable("clave") String clave,
            Model model) {
        System.out.println("Usuario recibido: " + usuario);
        System.out.println("Clave recibida: " + clave);

        Profesor profesor = profesorService.getProfesorPorUsuario(usuario);

        if (profesor != null) {
            if (passwordEncoder.matches(clave, profesor.getClave())) {
                System.out.println("Profesor encontrado: " + profesor.getUsuario());
                // Asegúrate de que la Persona está asociada
                if (profesor.getPersona() == null) {
                    throw new IllegalStateException("El profesor no tiene una persona asociada correctamente.");
                }
                model.addAttribute("profesor", profesor);
            } else {
                System.out.println("La clave no coincide.");
                model.addAttribute("mensaje", "Las credenciales no son correctas.");
                return "error";  // O cualquier otra vista de error que manejes
            }
        } else {
            System.out.println("No se encontró un profesor con el usuario proporcionado.");
            model.addAttribute("mensaje", "No se encontró un profesor con las credenciales proporcionadas.");
            return "error";  // O cualquier otra vista de error que manejes
        }

    return "planes/activar";
}
    /**
     * Maneja la solicitud POST para completar la activación de la cuenta de
     * usuario. Guarda la nueva contraseña y marca al usuario como activo.
     */
    @PostMapping("/activar")
    public String activarProfesor(@ModelAttribute("profesor") Profesor profesor) {
        
        Persona persona = profesor.getPersona();
        profesor.setRol(profesor.getRol());

        if (profesor.getId() != null) {
            System.out.print(persona);
            if (persona != null) {
                persona.setIdentificacion(persona.getIdentificacion());
                persona.setNombre(persona.getNombre());
                persona.setApellido1(persona.getApellido1());
                persona.setApellido2(persona.getApellido2());
                persona.setCorreo(persona.getCorreo());
                persona.setTelefono(persona.getTelefono());
                profesor.setPersona(persona);
                personaService.save(persona);
            }

        } else {
            throw new IllegalStateException("El profesor no tiene una persona asociada correctamente.");
        }



        // Llamar al servicio de registro para activar el profesor
        registroService.activar(profesor);

        // Redirigir al usuario a la página de login
        return "redirect:/login";
    }

}
