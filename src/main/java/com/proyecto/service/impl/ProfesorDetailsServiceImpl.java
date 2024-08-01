package com.proyecto.service.impl;

//import com.proyecto.dao.ProfesorDao;
//import com.proyecto.domain.Profesor;
//import com.proyecto.service.ProfesorDetailsService;
//import jakarta.servlet.http.HttpSession;
//import java.util.ArrayList;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;

//@Service("userDetailsService")
//public class ProfesorDetailsServiceImpl implements ProfesorDetailsService, UserDetailsService  {
//    
////    @Autowired
////    private ProfesorDao profesorDao;
////    @Autowired
////    private HttpSession session;
////
////    @Override
////    @Transactional(readOnly = true)
////    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
////        Profesor profesor = profesorDao.findByUsuario(username);
////        if (profesor == null) {
////            throw new UsernameNotFoundException(username);
////        }
////        return User.builder()
////                .username(profesor.getUsuario())
////                .password(profesor.getClave())
////                .authorities(new ArrayList<>()) // Sin roles
////                .build();
////    }
//}

