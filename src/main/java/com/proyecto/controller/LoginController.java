package com.proyecto.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@Slf4j
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public String login() {
        return "login/login"; // Muestra el formulario de inicio de sesión
    }
}