package com.example.demo.service;

import com.example.demo.dao.UsuarioDao;
import com.example.demo.domain.Usuario;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

//Implementa esta inferface para la seguridad de Spring
@Service("userDetailsService")
@Slf4j
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioDao usuarioDao;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioDao.findByUsername(username);

        if (usuario == null) throw new UsernameNotFoundException(username);

        //Para que funcione la seguridad de Spring hay que utilizar GrantedAuthority
        ArrayList<GrantedAuthority> roles = new ArrayList<>();

        usuario.getRoles().forEach(rol -> {
            roles.add(new SimpleGrantedAuthority(rol.getNombre()));
        });

        //User es una clase de Spring
        return new User(usuario.getUsername(), usuario.getPassword(), roles);
    }
}
