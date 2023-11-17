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

import com.example.microusuarios.model.Usuario;
import com.example.microusuarios.response.UsuarioResponseRest;
import com.example.microusuarios.service.UsuarioService;


import org.springframework.web.servlet.ModelAndView;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("api/v1/usuarios") //URL general
public class UsuarioControllerJPA {
	@Autowired //Se utiliza para realizar la inyección de dependencias automáticamente
	private UsuarioService usuarioService;
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView method() {
	    return new ModelAndView("redirect:/swagger-ui.html");
	}
	
	
	@Operation(summary = "Buscar todos los usuarios", description = "Retorna todos los usuarios.")
	@GetMapping("")
	public ResponseEntity<UsuarioResponseRest> getUsuarios() {		
		ResponseEntity<UsuarioResponseRest> response = usuarioService.getAll();
		return response;
		
	}
		
	@Operation(summary = "Buscar un usuario", description = "Retorna un usuario por su ID.")	
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioResponseRest> getUsuarioById(@PathVariable Long id) {	
		ResponseEntity<UsuarioResponseRest> response = usuarioService.getById(id);
		return response;
	}
	
	@Operation(summary = "Alta Usuario", description = "Da de alta un usuario.")
	@PostMapping("")
	public ResponseEntity<UsuarioResponseRest> save(@RequestBody Usuario usuario) {
		
		ResponseEntity<UsuarioResponseRest> response = usuarioService.save(usuario);
		return response;
	}
	
	@Operation(summary = "Actualizar un usuario", description = "Actualiza un usuario por su ID.")
	@PutMapping("/{id}")
	public ResponseEntity<UsuarioResponseRest> update(@RequestBody Usuario usuario, @PathVariable Long id) {
		ResponseEntity<UsuarioResponseRest> response = usuarioService.updateById(usuario, id);
		return response;
	}
	
	@Operation(summary = "Eliminar un usuario", description = "Elimina un usuario por su ID.")
	@DeleteMapping("/{id}")
	public ResponseEntity<UsuarioResponseRest> delete(@PathVariable Long id) {
		ResponseEntity<UsuarioResponseRest> response = usuarioService.deleteById(id);
		return response;
	}
	
	
	@Operation(summary = "Vincular cuenta", description = "Vincula una cuenta a un usuario por sus ID.")
	@PostMapping("/{idUsuario}/vincularCuenta/{idCuenta}")
	public ResponseEntity<UsuarioResponseRest> vincularCuentaAUsuario(
	    @PathVariable("idUsuario") Long idUsuario,
	    @PathVariable("idCuenta") Long idCuenta
	) {
	    ResponseEntity<UsuarioResponseRest> response = usuarioService.vincularCuentaUsuario(idUsuario, idCuenta);
	    return response;
	}
	

	/*---------------------------------endpoints administradores-----------------------------*/
	
	@Operation(summary = "Anular cuenta", description = "Anula la cuenta de un usuario por sus ID.")
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
