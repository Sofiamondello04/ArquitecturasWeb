package com.example.microusuarios.service;

import java.util.List;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.microusuarios.model.Rol;
import com.example.microusuarios.model.Usuario;
import com.example.microusuarios.repository.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private  UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
            return usuarioRepository
                    .findUserByEmailIgnoreCase( email )
                    .map(this::createSpringSecurityUser)
                    .orElseThrow(() -> new UsernameNotFoundException("No existe el usuario con email " + email ));
    }


    private org.springframework.security.core.userdetails.User createSpringSecurityUser(Usuario usuario) {
        List<GrantedAuthority> grantedAuthorities = usuario
                .getRoles()
                .stream()
                .map(Rol::getNombre)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(usuario.getEmail(), usuario.getPassword(), grantedAuthorities);
    }
	
	
	
	
	
}
