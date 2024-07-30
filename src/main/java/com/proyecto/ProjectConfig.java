package com.proyecto;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ProjectConfig implements WebMvcConfigurer {

////    /* Los siguiente métodos son para implementar el tema de seguridad dentro del proyecto */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/login").setViewName("login");
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                // Rutas públicas accesibles sin autenticación
                .requestMatchers("/", "/index", "/errores/**", "/error", "/error/**",
                        "/carrito/**", "/pruebas/**", "/reportes/**",
                        "/js/**", "/css/**", "/webjars/**", "/login/login", "planes/**", "suscripcion/**")
                .permitAll()
                // Rutas privadas accesibles solo para usuarios autenticados
                .requestMatchers("/estudiantes/listado", "/estudiante/listado", "/estudiante/modificar/**", "/estudiante/eliminar/**",
                        "/asistencias/listado", "/asistencias/form/**",
                        "/calificaciones/list", "/calificaciones/form/**",
                        "/reportes/**")
                .hasRole("ADMIN")
                )
                .formLogin((form) -> form
                .loginPage("/login")
                .defaultSuccessUrl("/estudiantes/listado", true)
                .permitAll())
                .logout((logout) -> logout.permitAll());
        return http.build();
    }

    /* El siguiente método se utiliza para completar la clase no es 
    realmente funcional, la próxima semana se reemplaza con usuarios de BD */
    @Bean
    public UserDetailsService users() {
        UserDetails admin = User.builder()
                .username("admin")
                .password("{noop}1234")
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(admin);
    }
}
