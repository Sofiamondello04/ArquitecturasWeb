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

import com.example.microusuarios.model.Cuenta;
import com.example.microusuarios.response.CuentaResponseRest;
import com.example.microusuarios.service.CuentaService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("api/v1/cuentas") //URL general
public class CuentaControllerJPA {
	@Autowired //Se utiliza para realizar la inyección de dependencias automáticamente
	private CuentaService cuentaService;
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView method() {
	    return new ModelAndView("redirect:/swagger-ui.html");
	}
	
	
	@Operation(summary = "Buscar todas las cuentas", description = "Retorna todas los cuentas.")
	@GetMapping("")
	public ResponseEntity<CuentaResponseRest> getCuentas() {		
		ResponseEntity<CuentaResponseRest> response = cuentaService.getAll();
		return response;
		
	}
		
	@Operation(summary = "Buscar una cuenta", description = "Retorna una cuenta dado un ID.")
	@GetMapping("/{id}")
	public ResponseEntity<CuentaResponseRest> getCuentasById(@PathVariable Long id) {	
		ResponseEntity<CuentaResponseRest> response = cuentaService.getById(id);
		return response;
	}
	
	@Operation(summary = "Crear cuenta", description = "Da de alta una cuenta.")
	@PostMapping("")
	public ResponseEntity<CuentaResponseRest> save(@RequestBody Cuenta cuenta) {
		
		ResponseEntity<CuentaResponseRest> response = cuentaService.save(cuenta);
		return response;
	}
	
	@Operation(summary = "Actualizar cuenta", description = "Actualiza una cuenta dado un ID.")
	@PutMapping("/{id}")
	public ResponseEntity<CuentaResponseRest> update(@RequestBody Cuenta cuenta, @PathVariable Long id) {
		ResponseEntity<CuentaResponseRest> response = cuentaService.updateById(cuenta, id);
		return response;
	}
	
	@Operation(summary = "Eliminar cuenta", description = "Elimina una cuenta dado un ID.")
	@DeleteMapping("/{id}")
	public ResponseEntity<CuentaResponseRest> delete(@PathVariable Long id) {
		ResponseEntity<CuentaResponseRest> response = cuentaService.deleteById(id);
		return response;
	}
	
	/*@PutMapping("/desactivarCuenta/{id}")
	public ResponseEntity<CuentaResponseRest> desactivarCuenta(@PathVariable Long id) {
		ResponseEntity<CuentaResponseRest> response = cuentaService.desactivarCuenta(id);
		return response;
	}*/
	 
}
