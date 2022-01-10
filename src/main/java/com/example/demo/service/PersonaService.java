package com.example.demo.service;

import com.example.demo.domain.Persona;

import java.util.List;
import java.util.Optional;

public interface PersonaService {

    public List<Persona> obtenerPersonas();

    public void guardar(Persona persona);

    public void eliminar(Persona persona);

    public Optional<Persona> encontrarPersona(Persona persona);


}
