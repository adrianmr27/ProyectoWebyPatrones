package com.proyecto.service.impl;

import com.proyecto.dao.ProfesorDao;
import com.proyecto.domain.Profesor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service("userDetailsService")
public class ProfesorDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ProfesorDao profesorDao;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {
        // Busca el profesor por el usuario en la tabla
        Profesor profesor = profesorDao.findByUsuario(usuario);

        // Si no existe el usuario lanza una excepción
        if (profesor == null) {
            System.out.println("Usuario no encontrado: " + usuario);
            throw new UsernameNotFoundException("Usuario no encontrado: " + usuario);
        }

        // Verificar si la contraseña coincide (añadiendo la depuración)
        boolean passwordMatches = new BCryptPasswordEncoder().matches("tuContraseña", profesor.getClave());
        System.out.println("¿La contraseña coincide?: " + passwordMatches);

        // Crear una autoridad (rol) basada en el nombre del rol
        GrantedAuthority rol = new SimpleGrantedAuthority(profesor.getRol().getNombre());

        // Se devuelve un objeto User (clase de UserDetails) con el nombre de usuario, contraseña y rol
        return new User(profesor.getUsuario(), profesor.getClave(), Collections.singletonList(rol));
    }
}
