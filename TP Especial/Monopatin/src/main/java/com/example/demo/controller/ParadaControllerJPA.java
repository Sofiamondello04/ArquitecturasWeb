package com.example.demo.controller;


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

import com.example.demo.Service.ParadaService;
import com.example.demo.model.Parada;
import com.example.demo.response.ParadaResponseRest;

import io.swagger.v3.oas.annotations.Operation;

//ACA SE MAPEAN LOS METODOS DEL SERVICE CON REST

@RestController
@RequestMapping("api/v1/paradas") //URL general
public class ParadaControllerJPA {

	@Autowired //Se utiliza para realizar la inyección de dependencias automáticamente
	private ParadaService paradaService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView method() {
	    return new ModelAndView("redirect:/swagger-ui.html");
	}

	@Operation(summary = "Buscar paradas", description = "Retorna todas las paradas.")
	@GetMapping("")
	public ResponseEntity<ParadaResponseRest> getParadas() {		
		ResponseEntity<ParadaResponseRest> response = paradaService.getAll();
		return response;
		
	}
		
	@Operation(summary = "Buscar una parada", description = "Retorna una parada dado un ID.")
	@GetMapping("/{id}")
	public ResponseEntity<ParadaResponseRest> getParadaById(@PathVariable Long id) {	
		ResponseEntity<ParadaResponseRest> response = paradaService.getById(id);
		return response;
	}
	
	@Operation(summary = "Crear parada", description = "Da de alta una parada.")
	@PostMapping("")
	public ResponseEntity<ParadaResponseRest> save(@RequestBody Parada parada) {
		
		ResponseEntity<ParadaResponseRest> response = paradaService.save(parada);
		return response;
	}
	
	@Operation(summary = "Actualizar parada", description = "Actualiza una parada dado un ID.")
	@PutMapping("/{id}")
	public ResponseEntity<ParadaResponseRest> update(@RequestBody Parada parada, @PathVariable Long id) {
		ResponseEntity<ParadaResponseRest> response = paradaService.updateById(parada, id);
		return response;
	}
	@Operation(summary = "Eliminar parada", description = "Elimina una parada dado un ID.")
	@DeleteMapping("/{id}")
	public ResponseEntity<ParadaResponseRest> delete(@PathVariable Long id) {
		ResponseEntity<ParadaResponseRest> response = paradaService.deleteById(id);
		return response;
	}
	 
}
