package com.proyecto.controller;

import com.proyecto.domain.Curso;
import com.proyecto.domain.Estudiante;
import com.proyecto.domain.Matricula;
import com.proyecto.domain.Persona;
import com.proyecto.domain.Periodo;
import com.proyecto.service.CursoService;
import com.proyecto.service.EstudianteService;
import com.proyecto.service.MatriculaService;
import com.proyecto.service.PersonaService;
import com.proyecto.service.PeriodoService;
import java.util.ArrayList;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
@RequestMapping("/estudiantes")
public class EstudiantesController {

    @Autowired
    private EstudianteService estudianteService;

    @Autowired
    private PersonaService personaService;

    @Autowired
    private CursoService cursoService;

    @Autowired
    private MatriculaService matriculaService;

    @Autowired
    private PeriodoService periodoService;

    @GetMapping("/listado")
    public String mostrarEstudiantes(Model model) {
        List<Estudiante> estudiantes = estudianteService.getEstudiantes();
        if (estudiantes == null) {
            estudiantes = new ArrayList<>();
        }
        List<Curso> cursos = cursoService.getCursos();
        List<Periodo> periodos = periodoService.getPeriodos();
        model.addAttribute("estudiantes", estudiantes);
        model.addAttribute("totalEstudiantes", estudiantes.size());
        model.addAttribute("estudiante", new Estudiante());
        model.addAttribute("cursos", cursos);
        model.addAttribute("periodos", periodos);
        return "estudiante/listado";
    }

    @PostMapping("/guardar")
    public String crearEstudiante(@ModelAttribute("estudiante") Estudiante estudiante,
            @RequestParam("curso.id") Long cursoId,
            @RequestParam("periodo.id") Long periodoId,
            Model model) {
        // Verificar si el correo electrónico ya está en uso
        Persona personaExisteCorreo = personaService.findByCorreo(estudiante.getPersona().getCorreo());
        if (personaExisteCorreo != null) {
            // Manejar el error de duplicidad
            model.addAttribute("error", "El correo electrónico ya está en uso.");
            List<Curso> cursos = cursoService.getCursos();
            List<Periodo> periodos = periodoService.getPeriodos();
            model.addAttribute("cursos", cursos);
            model.addAttribute("periodos", periodos);
            model.addAttribute("estudiante", estudiante);
            model.addAttribute("showAddModal", true); // Mostrar el modal de agregar
            return "estudiante/listado";
        }

        // Guardar la persona y el estudiante
        personaService.save(estudiante.getPersona());
        estudianteService.save(estudiante);

        // Crear y guardar la matrícula
        Curso curso = cursoService.getCursoById(cursoId);
        Periodo periodo = periodoService.getPeriodoById(periodoId);
        Matricula matricula = new Matricula(estudiante, curso, periodo);
        matriculaService.save(matricula);

        return "redirect:/estudiantes/listado";
    }

    @PostMapping("/actualizar")
    public String actualizarEstudiante(@ModelAttribute("matricula") Matricula matricula, Model model) {
        Estudiante estudiante = matricula.getEstudiante();
        Persona persona = estudiante.getPersona();

        // Verificar si estamos editando un estudiante existente
        if (estudiante.getId() != null) {
            // Verificación para la edición de un estudiante existente
            Persona personaExistente = personaService.findByCorreoExcludingId(persona.getCorreo(), persona.getId());
            if (personaExistente != null) {
                // Manejar el error de duplicidad
                model.addAttribute("error", "El correo electrónico ya está en uso por otra persona.");
                List<Curso> cursos = cursoService.getCursos();
                List<Periodo> periodos = periodoService.getPeriodos();
                model.addAttribute("cursos", cursos);
                model.addAttribute("periodos", periodos);
                model.addAttribute("matricula", matricula);
                return "estudiante/modifica";
            } else {
                // Cargar la persona existente y actualizar sus datos
                personaExistente = estudiante.getPersona();
                System.out.print(personaExistente);
                if (personaExistente != null) {
                    personaExistente.setIdentificacion(persona.getIdentificacion());
                    personaExistente.setNombre(persona.getNombre());
                    personaExistente.setApellido1(persona.getApellido1());
                    personaExistente.setApellido2(persona.getApellido2());
                    personaExistente.setCorreo(persona.getCorreo());
                    personaExistente.setTelefono(persona.getTelefono());
                    estudiante.setPersona(personaExistente);
                }
            }
        }

        // Guardar o actualizar la persona
        personaService.save(persona);

        // Guardar o actualizar el estudiante
        estudianteService.save(estudiante);

        // Buscar la matrícula existente del estudiante
        Matricula matriculaExistente = matriculaService.findByEstudianteId(estudiante.getId());
        if (matriculaExistente != null) {
            // Actualizar la matrícula existente
            matriculaExistente.setCurso(matricula.getCurso());
            matriculaExistente.setPeriodo(matricula.getPeriodo());
            matriculaService.save(matriculaExistente);
        } else {
            // Si no existe, se guarda la nueva matrícula
            Curso curso = cursoService.getCursoById(matricula.getCurso().getId());
            Periodo periodo = periodoService.getPeriodoById(matricula.getPeriodo().getId());
            matricula.setCurso(curso);
            matricula.setPeriodo(periodo);
            matriculaService.save(matricula);
        }

        return "redirect:/estudiantes/listado";
    }

    @GetMapping("/modificar/{idEstudiante}")
    public String estudianteModificar(@PathVariable("idEstudiante") Long idEstudiante, Model model) {
        // Buscar el estudiante por su ID
        Estudiante estudiante = estudianteService.findById(idEstudiante);

        if (estudiante != null) {
            // Buscar la matrícula asociada al estudiante
            Matricula matricula = matriculaService.findByEstudianteId(idEstudiante);

            // Obtener listas de todos los cursos y periodos disponibles
            List<Curso> cursos = cursoService.getCursos();
            List<Periodo> periodos = periodoService.getPeriodos();

            // Agregar los atributos necesarios al modelo
            model.addAttribute("estudiante", estudiante);
            model.addAttribute("persona", estudiante.getPersona());
            model.addAttribute("matricula", matricula);
            model.addAttribute("cursos", cursos);
            model.addAttribute("periodos", periodos);
        }

        // Devolver la vista de modificación del estudiante
        return "estudiante/modifica";
    }
}
