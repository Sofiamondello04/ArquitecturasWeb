package com.example.demo.controller;


import java.util.List;

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

import com.example.demo.Service.MonopatinService;

import com.example.demo.model.Monopatin;
import com.example.demo.response.MonopatinResponseRest;

import io.swagger.v3.oas.annotations.Operation;

//ACA SE MAPEAN LOS METODOS DEL SERVICE CON REST

@RestController
@RequestMapping("api/v1/monopatines") //URL general
public class MonopatinControllerJPA {

	@Autowired //Se utiliza para realizar la inyección de dependencias automáticamente
	private MonopatinService monopatinService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView method() {
	    return new ModelAndView("redirect:/swagger-ui.html");
	}

	@Operation(summary = "Buscar monopatines", description = "Retorna todos los monopatines.")
	@GetMapping("")
	public ResponseEntity<MonopatinResponseRest> getMonopatines() {		
		ResponseEntity<MonopatinResponseRest> response = monopatinService.getAll();
		return response;
		
	}
	
	@Operation(summary = "Buscar un monopatin", description = "Retorna un monopatin dado un ID.")
	@GetMapping("/{id}")
	public ResponseEntity<MonopatinResponseRest> getMonopatinById(@PathVariable Long id) {	
		ResponseEntity<MonopatinResponseRest> response = monopatinService.getById(id);
		return response;
	}
	
	@Operation(summary = "Crear monopatin", description = "Da de alta un nuevo monopatin.")
	@PostMapping("")
	public ResponseEntity<MonopatinResponseRest> save(@RequestBody Monopatin monopatin) {
		
		ResponseEntity<MonopatinResponseRest> response = monopatinService.save(monopatin);
		return response;
	}
	
	@Operation(summary = "Actualizar monopatin", description = "Actualiza un monopatin dado un ID.")
	@PutMapping("/{id}")
	public ResponseEntity<MonopatinResponseRest> update(@RequestBody Monopatin monopatin, @PathVariable Long id) {
		ResponseEntity<MonopatinResponseRest> response = monopatinService.updateById(monopatin, id);
		return response;
	}
	
	@Operation(summary = "Eliminar monopatin", description = "Elimina un monopatin dado un ID.")
	@DeleteMapping("/{id}")
	public ResponseEntity<MonopatinResponseRest> delete(@PathVariable Long id) {
		ResponseEntity<MonopatinResponseRest> response = monopatinService.deleteById(id);
		return response;
	}
	
	/*@GetMapping("/monopatin/{id}")
	public ResponseEntity<MonopatinResponseRest> reporteKilometrosMonopatin(@PathVariable Long id) {	
		ResponseEntity<MonopatinResponseRest> response = monopatinService.getKilometrosById(id);
		return response;
	}*/
	
	@Operation(summary = "Lista de monopatines que requieren mantenimiento", description = "Retorna aquellos monopatines que superen los 5000 km.")
	@GetMapping("/mantenimientoMonopatines")
	public List<Monopatin> mantenimientoMonopatines() {	
		List<Monopatin> mantenimientoMonopatines = monopatinService.getEstadoMonopatines();
		return mantenimientoMonopatines;
	}
	
	 
}
