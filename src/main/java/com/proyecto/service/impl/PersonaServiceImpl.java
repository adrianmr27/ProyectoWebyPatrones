package com.proyecto.service.impl;

import com.proyecto.dao.PersonaDao;
import com.proyecto.domain.Persona;
import com.proyecto.service.PersonaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    private PersonaDao personaDao;

    @Override
    @Transactional(readOnly = true)
    public List<Persona> getPersonas() {
        return personaDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Persona getPersona(Persona persona) {
        return personaDao.findById(persona.getId())
                .orElse(null);
    }

    @Override
    @Transactional
    public void save(Persona persona) {
        personaDao.save(persona);
    }

    @Override
    @Transactional
    public void delete(Persona persona) {
        personaDao.delete(persona);
    }
}