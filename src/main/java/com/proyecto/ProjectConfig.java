
package com.proyecto;

import com.proyecto.service.impl.ProfesorDetailsServiceImpl;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class ProjectConfig implements WebMvcConfigurer {

    /* Los siguientes métodos son para incorporar el tema de internacionalización en el proyecto */

 /* localeResolver se utiliza para crear una sesión de cambio de idioma */
    @Bean
    public LocaleResolver localeResolver() {
        var slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.getDefault());
        slr.setLocaleAttributeName("session.current.locale");
        slr.setTimeZoneAttributeName("session.current.timezone");

        return slr;
    }

    /* localeChangeInterceptor se utiliza para crear un interceptor de cambio de idioma */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        var lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

    //Bean para poder acceder a los Messages.properties en código Java...
    @Bean("messageSource")
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    /* Los siguiente métodos son para implementar el tema de seguridad dentro del proyecto */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/login").setViewName("login/login");
        registry.addViewController("/registro/nuevo").setViewName("/registro/nuevo");
    }

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private ProfesorDetailsServiceImpl profesorDetailsService;

    @Autowired
    public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception {
        build.userDetailsService(profesorDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((request) -> request
                .requestMatchers("/", "/index", "/errores/**", "/error", "/error/**",
                        "/registro/**", "/js/**", "/webjars/**", "/planes", "/suscripcion","/logout","/logout/**",
                        "/planes/**", "/suscripcion/**", "/pendienteActivar", "/activar", "/activar/**")
                .permitAll()
                .requestMatchers(
                        "/producto/nuevo", "/producto/guardar",
                        "/producto/modificar/**", "/producto/eliminar/**",
                        "/categoria/nuevo", "/categoria/guardar",
                        "/categoria/modificar/**", "/categoria/eliminar/**",
                        "/usuario/nuevo", "/usuario/guardar",
                        "/usuario/modificar/**", "/usuario/eliminar/**",
                        "/reportes/**", "/asistencias", "/asistencias/**",
                        "/asistencias/registrar", "/asistencias/matricula/**",
                        "/calificaciones", "/calificaciones/list", "/calificaciones/**",
                        "/calificaciones/add", "/calificaciones/save",
                        "/calificaciones/edit/**", "/calificaciones/delete/**",
                        "/estudiantes**", "/estudiantes/listado",
                        "/estudiante", "/estudiante/**",
                        "/layout/**", "/login",
                        "/cursos**", "/cursos/listado", "/cursos/listado2",
                        "/curso", "/curso/**",
                        "/cursos/nuevo", "/cursos/guardar",
                        "/asistencia**", "/asistencia/curso/**", "/asistencia/curso", "/asistencia/estudiantes", "/asistencia/estudiantes/**",
                        "/asistencia", "/asistencia/**",
                        "/cursos/nuevo", "/cursos/guardar",
                        "/cursos/eliminar/**", "/cursos/modifica/**", "/cursos/editar/**",
                        "/estudiantes/nuevo", "/estudiantes/guardar",
                        "/estudiantes/eliminar/**", "/estudiantes/modificar/**",
                        "/periodos/nuevo", "/periodos/guardar",
                        "/periodos/eliminar/**", "/periodos/modificar/**", "/periodos/modifica/**",
                        "/periodos**", "/periodos/listado",
                        "/matriculas/nuevo", "/matriculas/guardar",
                        "/matriculas/eliminar/**", "/matriculas/modificar/**", "/matriculas/modifica/**",
                        "/matriculas**", "/matriculas/listado",
                        "/js/**", "/webjars/**", "/tamplates", "templates/**", "/estudiantes/actualizar", "/estudiantes/actualizar/**",
                        "/estudiantes/modificar/**", "/planes/**", "/suscripcion/**", "/pendienteActivar", "/activar", "/activar/**"
                ).hasRole("TEACH")
                .requestMatchers("/facturar/carrito")
                .hasRole("TEACH")
                )
                .formLogin((form) -> form
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/estudiantes/listado", true) // Redirigir a estudiantes/listado tras login exitoso
                )
                .logout((logout) -> logout
                        .permitAll());
        return http.build();
    }

}
