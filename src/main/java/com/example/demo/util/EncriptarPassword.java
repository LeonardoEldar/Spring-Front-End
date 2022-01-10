package com.example.demo.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//Clase para generar contraseñas encriptadas
public class EncriptarPassword {
    public static void main(String[] args) {
        String password = "user";
        System.out.printf("Password Encriptado: %s%n", new BCryptPasswordEncoder().encode(password));
    }
}
