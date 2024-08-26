package com.proyecto.controller;

import com.proyecto.domain.Asistencia;
import com.proyecto.domain.Curso;
import com.proyecto.domain.Estudiante;
import com.proyecto.domain.Matricula;
import com.proyecto.service.AsistenciaService;
import com.proyecto.service.CursoService;
import com.proyecto.service.EstudianteService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/asistencia")
public class AsistenciaController {

    @Autowired
    private CursoService cursoService;

    @Autowired
    private EstudianteService estudianteService;

    @Autowired
    private AsistenciaService asistenciaService;

    @GetMapping("/curso")
    public String seleccionarCurso(Model model) {
        List<Curso> cursos = cursoService.getCursos();
        model.addAttribute("cursos", cursos);
        System.out.println("Cursos cargados: " + cursos.size());
        return "asistencia/curso"; // Vista para seleccionar el curso
    }

    @GetMapping("/estudiantes")
    public String listarEstudiantesPorCurso(@RequestParam("cursoId") Long cursoId, Model model) {
        Curso curso = cursoService.getCursoById(cursoId); // Obtener el curso por ID
        List<Estudiante> estudiantes = estudianteService.getEstudiantesPorCurso(cursoId);
        model.addAttribute("estudiantes", estudiantes);
        model.addAttribute("cursoId", cursoId);
        model.addAttribute("cursoNombre", curso.getNombre()); // Pasar el nombre del curso al modelo
        model.addAttribute("fecha", new Date()); // Asegúrate de que 'fecha' esté disponible en el modelo
        return "asistencia/estudiantes"; // Vista para pasar asistencia
    }


    @PostMapping("/registrar")
    public String registrarAsistencias(
            @RequestParam("cursoId") Long cursoId,
            @RequestParam("fecha") String fechaStr,
            @RequestParam Map<String, String> params) {

        List<Asistencia> asistencias = new ArrayList<>();

        // Iterar sobre los parámetros y procesar cada uno
        for (Map.Entry<String, String> entry : params.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            if (key.startsWith("matriculaId")) {
                Long matriculaId = Long.valueOf(value);

                // Construir la clave para el checkbox correspondiente
                String asistioKey = "asistio" + matriculaId;
                Boolean asistio = "on".equals(params.get(asistioKey));  // Verificar si el checkbox está marcado

                // Crear la instancia de Asistencia
                Asistencia asistencia = new Asistencia();
                Matricula matricula = new Matricula();
                matricula.setId(matriculaId);
                asistencia.setMatricula(matricula);
                asistencia.setFecha(parseDate(fechaStr));
                asistencia.setAsistio(asistio);

                // Agregar la asistencia a la lista
                asistencias.add(asistencia);
            }
        }

        // Llamar al servicio para registrar las asistencias
        asistenciaService.registrarAsistencias(asistencias);

        // Redirigir a la página de estudiantes del curso
        return "redirect:/asistencia/curso";
    }

    private Date parseDate(String dateStr) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            return formatter.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    } 
}
    

