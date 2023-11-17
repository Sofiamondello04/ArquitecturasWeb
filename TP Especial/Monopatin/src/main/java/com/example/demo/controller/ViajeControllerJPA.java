package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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

import com.example.demo.Service.ViajeService;
import com.example.demo.model.Viaje;
import com.example.demo.response.ViajeResponseRest;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

//ACA SE MAPEAN LOS METODOS DEL SERVICE CON REST

@RestController
@Validated
@RequestMapping("api/v1/viajes") //URL general
public class ViajeControllerJPA {

	@Autowired //Se utiliza para realizar la inyección de dependencias automáticamente
	private ViajeService viajeService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView method() {
	    return new ModelAndView("redirect:/swagger-ui.html");
	}

	@Operation(summary = "Buscar viajes", description = "Retorna todos los viajes.")
	@GetMapping("")
	public ResponseEntity<ViajeResponseRest> getViajes() {		
		ResponseEntity<ViajeResponseRest> response = viajeService.getAll();
		return response;
		
	}
	
	@Operation(summary = "Buscar un viaje", description = "Retorna un viaje dado un ID.")
	@GetMapping("/{id}")
	public ResponseEntity<ViajeResponseRest> getViajeById(@PathVariable Long id) {	
		ResponseEntity<ViajeResponseRest> response = viajeService.getById(id);
		return response;
	}
	
	@Operation(summary = "Crear viaje", description = "Da de alta un nuevo viaje.")
	@PostMapping("")
	public ResponseEntity<ViajeResponseRest> save(@Valid @RequestBody Viaje viaje) {
		
		ResponseEntity<ViajeResponseRest> response = viajeService.save(viaje);
		return response;
	}
	
	@Operation(summary = "Actualizar viaje", description = "Actualizar un viaje dado un ID.")
	@PutMapping("/{id}")
	public ResponseEntity<ViajeResponseRest> update(@RequestBody Viaje viaje, @PathVariable Long id) {
		ResponseEntity<ViajeResponseRest> response = viajeService.updateById(viaje, id);
		return response;
	}
	
	@Operation(summary = "Eliminar viaje", description = "Elimina un viaje dado un ID.")
	@DeleteMapping("/{id}")
	public ResponseEntity<ViajeResponseRest> delete(@PathVariable Long id) {
		ResponseEntity<ViajeResponseRest> response = viajeService.deleteById(id);
		return response;
	}
	 
}
