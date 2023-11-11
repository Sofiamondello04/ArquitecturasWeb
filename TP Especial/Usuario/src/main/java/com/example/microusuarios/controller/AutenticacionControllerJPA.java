package com.example.microusuarios.controller;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import com.example.microusuarios.model.TokenInfo;
import com.example.microusuarios.model.Usuario;
import com.example.microusuarios.service.JwtUtilService;


@RestController
@RequestMapping("api/v1") //URL general
public class AutenticacionControllerJPA {
	



	  @Autowired
	  private AuthenticationManager authenticationManager;

	  @Autowired
	  UserDetailsService usuarioDetailsService;

	  @Autowired
	  private JwtUtilService jwtUtilService;
	  private static final Logger logger = LoggerFactory.getLogger(AutenticacionControllerJPA.class);

	  @GetMapping("/mensaje")
	  public ResponseEntity<?> getMensaje() {
	    logger.info("Obteniendo el mensaje");

	    var auth =  SecurityContextHolder.getContext().getAuthentication();
	    logger.info("Datos del Usuario: {}", auth.getPrincipal());
	    logger.info("Datos de los Roles {}", auth.getAuthorities());
	    logger.info("Esta autenticado {}", auth.isAuthenticated());

	    Map<String, String> mensaje = new HashMap<>();
	    mensaje.put("contenido", "Hola Peru");
	    return ResponseEntity.ok(mensaje);
	  }

	  @GetMapping("/admin")
	  public ResponseEntity<?> getMensajeAdmin() {

	    var auth =  SecurityContextHolder.getContext().getAuthentication();
	    logger.info("Datos del Usuario: {}", auth.getPrincipal());
	    logger.info("Datos de los Permisos {}", auth.getAuthorities());
	    logger.info("Esta autenticado {}", auth.isAuthenticated());

	    Map<String, String> mensaje = new HashMap<>();
	    mensaje.put("contenido", "Hola Admin");
	    return ResponseEntity.ok(mensaje);
	  }

	  @GetMapping("/publico")
	  public ResponseEntity<?> getMensajePublico() {
	    var auth =  SecurityContextHolder.getContext().getAuthentication();
	    logger.info("Datos del Usuario: {}", auth.getPrincipal());
	    logger.info("Datos de los Permisos {}", auth.getAuthorities());
	    logger.info("Esta autenticado {}", auth.isAuthenticated());

	    Map<String, String> mensaje = new HashMap<>();
	    mensaje.put("contenido", "Hola. esto es publico");
	    return ResponseEntity.ok(mensaje);
	  }



	  @PostMapping("/publico/authenticate")
	  public ResponseEntity<TokenInfo> authenticate(@RequestBody Usuario usuario) {
	    logger.info("Autenticando al usuario {}", usuario.getEmail());

	    /*authenticationManager.authenticate(
	        new UsernamePasswordAuthenticationToken(usuario.getEmail(),
	        		usuario.getPassword()));
	    logger.info("Autenticando al usuario con password {}", usuario.getPassword());

	    final UserDetails userDetails = usuarioDetailsService.loadUserByUsername(
	    		usuario.getEmail());

	    final String jwt = jwtUtilService.generateToken(userDetails);

	    return ResponseEntity.ok(new TokenInfo(jwt));*/
	    try {
	        authenticationManager.authenticate(
	            new UsernamePasswordAuthenticationToken(usuario.getEmail(), usuario.getPassword()));
	        logger.info("Autenticación exitosa para el usuario {}", usuario.getEmail());

	        final UserDetails userDetails = usuarioDetailsService.loadUserByUsername(usuario.getEmail());
	        
	        logger.info("UserDetails correcto para {}", usuario.getEmail());

	        final String jwt = jwtUtilService.generateToken(userDetails);
	        
	        logger.info("generando token para {}", usuario.getEmail());

	        return ResponseEntity.ok(new TokenInfo(jwt));
	        
	    } catch (AuthenticationException e) {
	        logger.error("Error de autenticación para el usuario {}", usuario.getEmail(), e);
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	    }
	  }

	}
