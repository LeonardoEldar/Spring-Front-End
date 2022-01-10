package com.example.demo.service;

import com.example.demo.dao.PersonaDao;
import com.example.demo.domain.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaServiceImp implements PersonaService {

    @Autowired
    private PersonaDao personaDao;

    @Override
    @Transactional(readOnly = true)
    public List<Persona> obtenerPersonas() {
        return personaDao.findAll();
    }

    @Transactional
    @Override
    public void guardar(Persona persona) {
        personaDao.save(persona);
    }

    @Transactional
    @Override
    public void eliminar(Persona persona) {
        personaDao.delete(persona);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Persona> encontrarPersona(Persona persona) {
        return personaDao.findById(persona.getIdPersona());
    }
}
