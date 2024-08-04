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
    public String estudianteGuardar(@ModelAttribute("estudiante") Estudiante estudiante,
            @RequestParam("curso.id") Long cursoId,
            @RequestParam("periodo.id") Long periodoId,
            Model model) {
        // Verificar si estamos editando o creando un nuevo estudiante
        if (estudiante.getId() != null) {
            // Verificación para la edición de un estudiante existente
            Persona personaExistente = personaService.findByCorreoExcludingId(estudiante.getPersona().getCorreo(), estudiante.getPersona().getId());
            if (personaExistente != null) {
                // Manejar el error de duplicidad
                model.addAttribute("error", "El correo electrónico ya está en uso por otra persona.");
                List<Curso> cursos = cursoService.getCursos();
                List<Periodo> periodos = periodoService.getPeriodos();
                List<Estudiante> estudiantes = estudianteService.getEstudiantes();
                model.addAttribute("cursos", cursos);
                model.addAttribute("periodos", periodos);
                model.addAttribute("estudiantes", estudiantes);
                return "estudiante/modifica";
            } else {
                // Cargar la persona existente y actualizar sus datos
                Persona persona = personaService.getPersona(estudiante.getPersona());
                if (persona != null) {
                    persona.setIdentificacion(estudiante.getPersona().getIdentificacion());
                    persona.setNombre(estudiante.getPersona().getNombre());
                    persona.setApellido1(estudiante.getPersona().getApellido1());
                    persona.setApellido2(estudiante.getPersona().getApellido2());
                    persona.setCorreo(estudiante.getPersona().getCorreo());
                    persona.setTelefono(estudiante.getPersona().getTelefono());
                    estudiante.setPersona(persona);
                }
            }
        } else {
            // Verificación para la creación de un nuevo estudiante
            Persona personaExisteCorreo = personaService.findByCorreo(estudiante.getPersona().getCorreo());
            if (personaExisteCorreo != null) {
                // Manejar el error de duplicidad
                model.addAttribute("error", "El correo electrónico ya está en uso.");
                List<Curso> cursos = cursoService.getCursos();
                List<Periodo> periodos = periodoService.getPeriodos();
                List<Estudiante> estudiantes = estudianteService.getEstudiantes();
                model.addAttribute("cursos", cursos);
                model.addAttribute("periodos", periodos);
                model.addAttribute("estudiantes", estudiantes);
                model.addAttribute("showAddModal", true); // Mostrar el modal de agregar
                model.addAttribute("showEditModal", false); // No mostrar el modal de editar
                return "estudiante/listado";
            }
        }

        // Guardar o actualizar la persona
        personaService.save(estudiante.getPersona());

        // Guardar o actualizar el estudiante
        estudianteService.save(estudiante);

        // Actualizar o crear y guardar la matrícula
        Matricula matricula = matriculaService.findByEstudianteId(estudiante.getId());
        if (matricula == null) {
            matricula = new Matricula();
            matricula.setEstudiante(estudiante);
        }
        Curso curso = cursoService.getCursoById(cursoId);
        Periodo periodo = periodoService.getPeriodoById(periodoId);
        matricula.setCurso(curso);
        matricula.setPeriodo(periodo);
        matriculaService.save(matricula);

        return "redirect:/estudiantes/listado";
    }

    @GetMapping("/eliminar/{idEstudiante}")
    public String estudianteEliminar(@PathVariable("idEstudiante") Long idEstudiante) {
        Estudiante estudiante = estudianteService.findById(idEstudiante);
        if (estudiante != null) {
            // Eliminar las matrículas del estudiante
            List<Matricula> matriculas = estudiante.getMatriculas();
            for (Matricula matricula : matriculas) {
                matriculaService.delete(matricula);
            }

            estudianteService.delete(estudiante);
            Persona persona = estudiante.getPersona();
            if (persona != null) {
                personaService.delete(persona);
            }
        }
        return "redirect:/estudiantes/listado";
    }

    @GetMapping("/modificar/{idEstudiante}")
    public String estudianteModificar(@PathVariable("idEstudiante") Long idEstudiante, Model model) {
        Estudiante estudiante = estudianteService.findById(idEstudiante);
        if (estudiante != null) {
            List<Curso> cursos = cursoService.getCursos();
            List<Periodo> periodos = periodoService.getPeriodos();
            model.addAttribute("estudiante", estudiante);
            model.addAttribute("persona", estudiante.getPersona());
            model.addAttribute("cursos", cursos);
            model.addAttribute("periodos", periodos);
        }
        return "estudiante/modifica";
    }
}
