package com.proyecto.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface ProfesorDetailsService {
    UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException;
}
