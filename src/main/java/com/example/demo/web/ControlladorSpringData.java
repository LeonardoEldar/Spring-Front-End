package com.example.demo.web;

import com.example.demo.domain.Persona;
import com.example.demo.service.PersonaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class ControlladorSpringData {

    Logger logger = LoggerFactory.getLogger(ControlladorSpringData.class);

    @Autowired
    private PersonaService personaService;

    @GetMapping("/")
    public String springData(Model model, @AuthenticationPrincipal User user){
        logger.info(String.format("User logeado: %S", user.getUsername()));

        List<Persona> personas = personaService.obtenerPersonas();


        double saldoTotal = 0D;
        for (Persona persona : personas) {
            saldoTotal+= persona.getSaldo();
        }

        model.addAttribute("personas", personas);
        model.addAttribute("saldoTotal", saldoTotal);
        model.addAttribute("totalClientes", personas.size());

        return "index";
    }

    @GetMapping("/agregar")
    public String agregar(Persona persona){
        return "modificar";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid Persona persona, Errors errors){
        if (errors.hasErrors()) return "modificar";
        personaService.guardar(persona);
        return "redirect:/";
    }

    @GetMapping("/editar/{idPersona}")
    public String editar(Persona persona, Model model){
        Optional<Persona> persona1 = personaService.encontrarPersona(persona);
        persona1.ifPresent(value -> model.addAttribute("persona", value));
        return "modificar";
    }

    @GetMapping("/eliminar/{idPersona}")
    public String eliminar(Persona persona){
        personaService.eliminar(persona);
        return "redirect:/";
    }

    @GetMapping("/eliminarV2")
    public String eliminarV2(Persona persona){
        personaService.eliminar(persona);
        return "redirect:/";
    }
}
