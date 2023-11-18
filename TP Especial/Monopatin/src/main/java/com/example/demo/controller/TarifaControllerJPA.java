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

import com.example.demo.Service.TarifaService;
import com.example.demo.model.mysql.Tarifa;
import com.example.demo.response.TarifaResponseRest;

import io.swagger.v3.oas.annotations.Operation;

//ACA SE MAPEAN LOS METODOS DEL SERVICE CON REST

@RestController
@RequestMapping("api/v1/tarifas") //URL general
public class TarifaControllerJPA {

	@Autowired //Se utiliza para realizar la inyección de dependencias automáticamente
	private TarifaService tarifaService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView method() {
	    return new ModelAndView("redirect:/swagger-ui.html");
	}

	@Operation(summary = "Buscar tarifas", description = "Retorna todos las tarifas.")
	@GetMapping("")
	public ResponseEntity<TarifaResponseRest> getTarifas() {		
		ResponseEntity<TarifaResponseRest> response = tarifaService.getAll();
		return response;
		
	}
	@Operation(summary = "Buscar una tarifa", description = "Retorna una tarifa dado un ID.")	
	@GetMapping("/{id}")
	public ResponseEntity<TarifaResponseRest> getTarifaById(@PathVariable Long id) {	
		ResponseEntity<TarifaResponseRest> response = tarifaService.getById(id);
		return response;
	}
	
	@Operation(summary = "Crear tarifa", description = "Da de alta una nueva tarifa.")
	@PostMapping("")
	public ResponseEntity<TarifaResponseRest> save(@RequestBody Tarifa tarifa) {		
		ResponseEntity<TarifaResponseRest> response = tarifaService.save(tarifa);
		return response;
	}
	
	@Operation(summary = "Actualizar una tarifa", description = "Actualiza una tarifa dado un ID.")
	@PutMapping("/{id}")
	public ResponseEntity<TarifaResponseRest> update(@RequestBody Tarifa tarifa, @PathVariable Long id) {
		ResponseEntity<TarifaResponseRest> response = tarifaService.updateById(tarifa, id);
		return response;
	}
	
	@Operation(summary = "Elimimar una tarifa", description = "Elimina una tarifa dado un ID.")
	@DeleteMapping("/{id}")
	public ResponseEntity<TarifaResponseRest> delete(@PathVariable Long id) {
		ResponseEntity<TarifaResponseRest> response = tarifaService.deleteById(id);
		return response;
	}
	 
}
