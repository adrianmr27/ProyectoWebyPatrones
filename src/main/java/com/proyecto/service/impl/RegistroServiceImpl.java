package com.proyecto.service.impl;

import com.proyecto.domain.Profesor;
import com.proyecto.domain.Persona;
import com.proyecto.domain.Planes;
import com.proyecto.domain.Suscripcion;
import com.proyecto.domain.Rol;
import com.proyecto.service.CorreoService;
import com.proyecto.service.ProfesorService;
import com.proyecto.service.RegistroService;
import com.proyecto.service.PlanesService;
import com.proyecto.service.PersonaService;
import com.proyecto.service.SuscripcionService;
import jakarta.mail.MessagingException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class RegistroServiceImpl implements RegistroService {

    @Autowired
    private CorreoService correoService;

    @Autowired
    private ProfesorService profesorService;

    @Autowired
    private PersonaService personaService;

    @Autowired
    private SuscripcionService suscripcionService;

    @Autowired
    private PlanesService planesService;

    @Autowired
    private MessageSource messageSource;

    @Value("${servidor.http}")
    private String servidor;

    @Override
    public Model activar(Model model, String usuario, String clave) {
        Profesor profesor = profesorService.getProfesorPorUsuarioYClave(usuario, clave);
        if (profesor != null) {
            model.addAttribute("usuario", profesor);
        } else {
            model.addAttribute("titulo", messageSource.getMessage("registro.activar", null, Locale.getDefault()));
            model.addAttribute("mensaje", messageSource.getMessage("registro.activar.error", null, Locale.getDefault()));
        }
        return model;
    }

    @Override
    public void activar(Profesor usuario) {
        var codigo = new BCryptPasswordEncoder();
        String encodedClave = codigo.encode(usuario.getClave());
        System.out.println("Clave codificada: " + encodedClave);
        usuario.setClave(encodedClave);

        // Asegura que el estado activo se establece correctamente
        usuario.setActivo(true);
        System.out.println("Usuario activo: " + usuario.isActivo());

        profesorService.save(usuario);
    }

    @Override
    public Model crearProfesor(Model model, Profesor profesor, Long planId) throws MessagingException {
        String mensaje;

        if (!profesorService.existeProfesorPorUsuarioOCorreo(profesor.getUsuario(), profesor.getPersona().getCorreo())) {
            // Encriptar contraseña temporal
            String claveTemporal = demeClave();
            profesor.setClave(new BCryptPasswordEncoder().encode(claveTemporal));
            profesor.setActivo(false);

            // Asignar el rol de profesor (id 2)
            Rol rolProfesor = new Rol();
            rolProfesor.setId(2);
            profesor.setRol(rolProfesor);

            Persona persona = profesor.getPersona();
            // Guardar la persona
            personaService.save(persona);

            // Guardar el profesor
            profesorService.save(profesor);

            // Enviar correo con la contraseña temporal
            enviaCorreoActivar(profesor, claveTemporal);

            // Crear una nueva suscripción si el plan es válido
            if (planId != null) {
                Planes plan = planesService.getPlanesById(planId);
                Suscripcion suscripcion = new Suscripcion();
                suscripcion.setProfesor(profesor);
                suscripcion.setPlan(plan);
                suscripcion.setFechaInicio(new Date());

                // Suscripción es por un año
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(suscripcion.getFechaInicio());
                calendar.add(Calendar.YEAR, 1);
                suscripcion.setFechaFin(calendar.getTime());
                suscripcion.setActivo(true);

                // Guardar la suscripción
                suscripcionService.save(suscripcion);
            }

            mensaje = String.format(
                    messageSource.getMessage("registro.mensaje.activacion.ok", null, Locale.getDefault()),
                    profesor.getPersona().getCorreo()
            );
            model.addAttribute("mensaje", mensaje);
            return model;
        } else {
            // Usuario o correo ya existen, mostrar error
            mensaje = String.format(
                    messageSource.getMessage("registro.mensaje.usuario.o.correo", null, Locale.getDefault()),
                    profesor.getUsuario(), profesor.getPersona().getCorreo()
            );
            model.addAttribute("error", mensaje);
            return model;
        }
    }

    @Override
    public Model recordarUsuarioProfesor(Model model, Profesor profesor) throws MessagingException {
        String mensaje;
        Profesor profesor2 = profesorService.getProfesorPorUsuarioOCorreo(profesor.getUsuario(), profesor.getPersona().getCorreo());
        if (profesor2 != null) {
            String clave = demeClave();
            profesor2.setClave(clave);
            profesor2.setActivo(false);
            profesorService.save(profesor2);
            enviaCorreoRecordar(profesor2, clave);
            mensaje = String.format(
                    messageSource.getMessage("registro.mensaje.recordar.ok", null, Locale.getDefault()),
                    profesor.getPersona().getCorreo()
            );
        } else {
            mensaje = String.format(
                    messageSource.getMessage("registro.mensaje.usuario.o.correo", null, Locale.getDefault()),
                    profesor.getUsuario(), profesor.getPersona().getCorreo()
            );
        }
        model.addAttribute("titulo", messageSource.getMessage("registro.activar", null, Locale.getDefault()));
        model.addAttribute("mensaje", mensaje);
        return model;
    }

    private String demeClave() {
        String tira = "ABCDEFGHIJKLMNOPQRSTUXYZabcdefghijklmnopqrstuvwxyz0123456789_*+-";
        StringBuilder clave = new StringBuilder();
        for (int i = 0; i < 40; i++) {
            clave.append(tira.charAt((int) (Math.random() * tira.length())));
        }
        return clave.toString();
    }

    private void enviaCorreoActivar(Profesor profesor, String clave) throws MessagingException {
        // Obtener el mensaje desde el archivo de properties
        String mensaje = messageSource.getMessage("registro.correo.activar", null, Locale.getDefault());

        // Formatear el mensaje con los datos del profesor y el enlace de activación
        mensaje = String.format(mensaje, profesor.getPersona().getNombre(), profesor.getPersona().getApellido1(), servidor, profesor.getUsuario(), clave);

        // Asunto del correo
        String asunto = messageSource.getMessage("registro.mensaje.activacion", null, Locale.getDefault());

        // Enviar el correo HTML
        correoService.enviarCorreoHtml(profesor.getPersona().getCorreo(), asunto, mensaje);
    }

    private void enviaCorreoRecordar(Profesor profesor, String clave) throws MessagingException {
        String mensaje = messageSource.getMessage("registro.correo.recordar", null, Locale.getDefault());
        mensaje = String.format(mensaje, profesor.getPersona().getNombre(), profesor.getPersona().getApellido1(), servidor, profesor.getUsuario(), clave);
        String asunto = messageSource.getMessage("registro.mensaje.recordar", null, Locale.getDefault());
        correoService.enviarCorreoHtml(profesor.getPersona().getCorreo(), asunto, mensaje);
    }

}
