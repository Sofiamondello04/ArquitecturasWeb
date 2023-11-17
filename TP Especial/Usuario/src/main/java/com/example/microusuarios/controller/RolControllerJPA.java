package com.example.microusuarios.controller;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.microusuarios.model.Rol;
import com.example.microusuarios.model.Usuario;
import com.example.microusuarios.response.RolResponseRest;
import com.example.microusuarios.response.UsuarioResponseRest;
import com.example.microusuarios.service.RolService;
import com.example.microusuarios.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("api/v1") //URL general
public class RolControllerJPA {
	@Autowired //Se utiliza para realizar la inyección de dependencias automáticamente
	private RolService rolService;
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView method() {
	    return new ModelAndView("redirect:/swagger-ui.html");
	}
	
	
	@Operation(summary = "Buscar todos las roles", description = "Retorna todos los roles.")	
	@GetMapping("/roles")
	public ResponseEntity<RolResponseRest> getRoles() {		
		ResponseEntity<RolResponseRest> response = rolService.getAll();
		return response;
		
	}
		
	@Operation(summary = "Buscar un rol", description = "Retorna un rol dado un ID (nombre).")	
	@GetMapping("/roles/{nombre}")
	public ResponseEntity<RolResponseRest> getRolById(@PathVariable String nombre) {	
		ResponseEntity<RolResponseRest> response = rolService.getById(nombre);
		return response;
	}
	
	@Operation(summary = "Crear rol", description = "Da de alta un nuevo rol.")
	@PostMapping("/roles")
	public ResponseEntity<RolResponseRest> save(@RequestBody Rol rol) {
		
		ResponseEntity<RolResponseRest> response = rolService.save(rol);
		return response;
	}
	
	
	@Operation(summary = "Eliminar rol", description = "Elimina un rol dado un ID.")
	@DeleteMapping("/roles/{nombre}")
	public ResponseEntity<RolResponseRest> delete(@PathVariable String nombre) {
		ResponseEntity<RolResponseRest> response = rolService.deleteById(nombre);
		return response;
	}
	
	@Operation(summary = "Vincular usuario", description = "Vincula un usuario dado un ID a un rol dado un ID (nombre).")
	@PostMapping("/roles/{nombre}/vincularUsuario/{idUsuario}")
	public ResponseEntity<RolResponseRest> vincularUsuarioARol(
	    @PathVariable("nombre") String nombre,
	    @PathVariable("idUsuario") Long idUsuario
	) {
	    ResponseEntity<RolResponseRest> response = rolService.vincularUsuarioRol(nombre, idUsuario);
	    return response;
	}
	
	@Operation(summary = "Desvincular usuario", description = "Desvincula un usuario dado un ID a un rol dado un ID (nombre).")
	@PostMapping("/roles/{nombre}/desvincularUsuario/{idUsuario}")
	public ResponseEntity<RolResponseRest> desVincularUsuarioARol(
			@PathVariable("nombre") String nombre,
	    @PathVariable("idUsuario") Long idUsuario
	) {
	    ResponseEntity<RolResponseRest> response = rolService.desVincularUsuarioRol(nombre, idUsuario);
	    return response;
	}
	
}
