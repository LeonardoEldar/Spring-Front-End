package com.example.demo.dao;

import com.example.demo.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaDao extends JpaRepository<Persona, Long> {
}
