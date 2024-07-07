
package com.proyecto.controller;

import com.proyecto.service.PlanesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
@Slf4j
@RequestMapping("/planes")
public class PlanesController {
    
    @Autowired
    private PlanesService planesService;
    
    
    @GetMapping
    public String mostrarPlanes(Model model) {
        var planes = planesService.getPlanes();
        model.addAttribute("planes", planes);
        return "planes/planes";
    }
    
}
