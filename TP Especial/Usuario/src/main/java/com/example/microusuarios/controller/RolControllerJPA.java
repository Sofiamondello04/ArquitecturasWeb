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
import org.springframework.web.bind.annotation.RestController;

import com.example.microusuarios.Service.RolService;
import com.example.microusuarios.Service.UsuarioService;
import com.example.microusuarios.model.Rol;
import com.example.microusuarios.model.Usuario;
import com.example.microusuarios.response.RolResponseRest;
import com.example.microusuarios.response.UsuarioResponseRest;

@RestController
@RequestMapping("api/v1") //URL general
public class RolControllerJPA {
	@Autowired //Se utiliza para realizar la inyección de dependencias automáticamente
	private RolService rolService;
	
	@GetMapping("/roles")
	public ResponseEntity<RolResponseRest> getRoles() {		
		ResponseEntity<RolResponseRest> response = rolService.getAll();
		return response;
		
	}
		
	@GetMapping("/rol/{id}")
	public ResponseEntity<RolResponseRest> getRolById(@PathVariable Long id) {	
		ResponseEntity<RolResponseRest> response = rolService.getById(id);
		return response;
	}
	
	@PostMapping("/rol")
	public ResponseEntity<RolResponseRest> save(@RequestBody Rol rol) {
		
		ResponseEntity<RolResponseRest> response = rolService.save(rol);
		return response;
	}
	
	@PutMapping("/rol/{id}")
	public ResponseEntity<RolResponseRest> update(@RequestBody Rol rol, @PathVariable Long id) {
		ResponseEntity<RolResponseRest> response = rolService.updateById(rol, id);
		return response;
	}
	
	@DeleteMapping("/rol/{id}")
	public ResponseEntity<RolResponseRest> delete(@PathVariable Long id) {
		ResponseEntity<RolResponseRest> response = rolService.deleteById(id);
		return response;
	}
	
	@PostMapping("/rol/{idRol}/vincularUsuario/{idUsuario}")
	public ResponseEntity<RolResponseRest> vincularUsuarioARol(
	    @PathVariable("idRol") Long idRol,
	    @PathVariable("idUsuario") Long idUsuario
	) {
	    ResponseEntity<RolResponseRest> response = rolService.vincularUsuarioRol(idRol, idUsuario);
	    return response;
	}
	
	@PostMapping("/rol/{idRol}/desvincularUsuario/{idUsuario}")
	public ResponseEntity<RolResponseRest> desVincularUsuarioARol(
			@PathVariable("idRol") Long idRol,
	    @PathVariable("idUsuario") Long idUsuario
	) {
	    ResponseEntity<RolResponseRest> response = rolService.desVincularUsuarioRol(idRol, idUsuario);
	    return response;
	}
	
}
