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
import org.springframework.web.servlet.ModelAndView;

import com.example.microusuarios.model.TokenInfo;
import com.example.microusuarios.model.Usuario;
import com.example.microusuarios.service.JwtUtilService;

import io.swagger.v3.oas.annotations.Operation;


@RestController
@RequestMapping("") //URL general
public class AutenticacionControllerJPA {
	

	  @Autowired
	  private AuthenticationManager authenticationManager;

	  @Autowired
	  UserDetailsService usuarioDetailsService;

	  @Autowired
	  private JwtUtilService jwtUtilService;
	  private static final Logger logger = LoggerFactory.getLogger(AutenticacionControllerJPA.class);

	  @RequestMapping(value = "/", method = RequestMethod.GET)
		public ModelAndView method() {
		    return new ModelAndView("redirect:/swagger-ui.html");
		}

	  @Operation(summary = "Autenticar usuario", description = "Retorna un JWT dado un email y password válidos. El usuario debe tener un rol asignado.")
	  @PostMapping("/publico/authenticate")
	  public ResponseEntity<TokenInfo> authenticate(@RequestBody Usuario usuario) {
	    logger.info("Autenticando al usuario {}", usuario.getEmail());

	    try {
	        authenticationManager.authenticate(
	            new UsernamePasswordAuthenticationToken(usuario.getEmail(), usuario.getPassword()));
	        logger.info("Autenticación exitosa para el usuario {}", usuario.getEmail());

	        final UserDetails userDetails = usuarioDetailsService.loadUserByUsername(usuario.getEmail());
	        
	        logger.info("UserDetails correcto para {}", usuario.getEmail());

	        final String jwt = jwtUtilService.generateToken(userDetails);
	        
	        logger.info("generando token para {}", usuario.getEmail());
	        
	        logger.info("rol {}", usuario.getRoles());
	        return ResponseEntity.ok(new TokenInfo(jwt));
	        
	    } catch (AuthenticationException e) {
	        logger.error("Error de autenticación para el usuario {}", usuario.getEmail(), e);
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	    }
	  }

	}
