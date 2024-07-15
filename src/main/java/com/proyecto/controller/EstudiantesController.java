package com.proyecto.controller;

import com.proyecto.domain.Estudiante;
import com.proyecto.service.EstudianteService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
@RequestMapping("/estudiantes") // mapeo de la URL base para todos los métodos dentro de un controlador Estudiantes
public class EstudiantesController {

    //@GetMapping se usa para manejar solicitudes GET, típicamente para obtener información y mostrar vistas.
    //@PostMapping se usa para manejar solicitudes POST, típicamente para enviar datos a la aplicación (como formularios).
    @Autowired
    private EstudianteService estudianteService;

    @GetMapping("/listado") //Va a llamar a estudiantes/listado 
    public String mostrarEstudiantes(Model model) {
        List<Estudiante> estudiantes = estudianteService.getEstudiantes();
        model.addAttribute("estudiantes", estudiantes);
        model.addAttribute("totalEstudiantes", estudiantes.size());
        return "estudiante/listado";
    }

    @GetMapping("/nuevo")
    public String estudianteNuevo(Estudiante estudiante) {
        return "/estudiante/modifica";
    }


    @PostMapping("/guardar")
    public String estudianteGuardar(Estudiante estudiante) {
        estudianteService.save(estudiante);
        return "redirect:/estudiantes/listado"; //cuando hay un redirec busca en el controller la direccíon del controller no la del archivo
    }

    @GetMapping("/eliminar/{idEstudiante}")
    public String estudianteEliminar(Estudiante estudiante) {
        estudianteService.delete(estudiante);
        return "redirect:/estudiantes/listado ";
    }

    @GetMapping("/modificar/{idEstudiante}") //estudiantes/modificar/{idEstudiante}
    public String estudianteModificar(@PathVariable("idEstudiante") Long idEstudiante, Model model) {
        Estudiante estudiante = estudianteService.findById(idEstudiante);
        model.addAttribute("estudiante", estudiante);
        return "/estudiante/modifica";
    }
    /*
    @GetMapping("/buscarEstudiante")
    public String buscarEstudiante(@RequestParam("q") String nombre, Model model) {
        List<Estudiante> estudiantes = estudianteService.buscar(nombre);

        model.addAttribute("estudiantes", estudiantes);

    }*/

}
