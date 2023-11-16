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

import com.example.microusuarios.model.Usuario;
import com.example.microusuarios.response.UsuarioResponseRest;
import com.example.microusuarios.service.UsuarioService;

@RestController
@RequestMapping("api/v1/usuarios") //URL general
public class UsuarioControllerJPA {
	@Autowired //Se utiliza para realizar la inyección de dependencias automáticamente
	private UsuarioService usuarioService;
	
	@GetMapping("")
	public ResponseEntity<UsuarioResponseRest> getUsuarios() {		
		ResponseEntity<UsuarioResponseRest> response = usuarioService.getAll();
		return response;
		
	}
		
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioResponseRest> getUsuarioById(@PathVariable Long id) {	
		ResponseEntity<UsuarioResponseRest> response = usuarioService.getById(id);
		return response;
	}
	
	@PostMapping("")
	public ResponseEntity<UsuarioResponseRest> save(@RequestBody Usuario usuario) {
		
		ResponseEntity<UsuarioResponseRest> response = usuarioService.save(usuario);
		return response;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UsuarioResponseRest> update(@RequestBody Usuario usuario, @PathVariable Long id) {
		ResponseEntity<UsuarioResponseRest> response = usuarioService.updateById(usuario, id);
		return response;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<UsuarioResponseRest> delete(@PathVariable Long id) {
		ResponseEntity<UsuarioResponseRest> response = usuarioService.deleteById(id);
		return response;
	}
	
	@PostMapping("/{idUsuario}/vincularCuenta/{idCuenta}")
	public ResponseEntity<UsuarioResponseRest> vincularCuentaAUsuario(
	    @PathVariable("idUsuario") Long idUsuario,
	    @PathVariable("idCuenta") Long idCuenta
	) {
	    ResponseEntity<UsuarioResponseRest> response = usuarioService.vincularCuentaUsuario(idUsuario, idCuenta);
	    return response;
	}
	

	/*---------------------------------endpoints administradores-----------------------------*/
	
	@PostMapping("/anularCuenta/{idUsuario}/{idCuenta}")
    public ResponseEntity<UsuarioResponseRest> anularCuenta(@PathVariable Long idUsuario, @PathVariable Long idCuenta ) {
        ResponseEntity<UsuarioResponseRest> response = usuarioService.anularCuenta(idUsuario, idCuenta);
        return response;
    }
	 

    
    /*@GetMapping("/monopatines")
    public ResponseEntity<MonopatinResponseRest> getEstadoMonopatines() {
        ResponseEntity<MonopatinResponseRest> response = administradorService.requiereMantenimiento();
        return response;
    }*/
	
}
