package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.InscripcionService;
import com.example.demo.model.Inscripcion;
import com.example.demo.repository.InscripcionRepository;

//ACA SE MAPEAN LOS METODOS QUE MAPEAN CON LAS QUERYS PRA CADA CONSULTA REST

@RestController
@RequestMapping("inscripciones")
public class InscripcionControllerJPA {

	@Autowired
	private InscripcionService inscripcionService;
	
	@Qualifier("inscripcionRepository")
	@Autowired
	private final InscripcionRepository repository;

	public InscripcionControllerJPA(@Qualifier("inscripcionRepository") InscripcionRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/")
	public List<Inscripcion> getInscripciones() {
		return this.inscripcionService.getInscripciones();
	}
	
	@PostMapping("/")
	public Inscripcion newInscripcion(@RequestBody Inscripcion i) {
		return inscripcionService.saveInscripcion(i);
	}
	
	@GetMapping("/{id}")
	Optional<Inscripcion> getCarreraById(@PathVariable int id) {
		return inscripcionService.getById(id);
	}
	
	@PutMapping("/{id}")
	public Inscripcion updateCarreraById(@RequestBody Inscripcion request,@PathVariable("id_inscripcion") int id) {
		return this.inscripcionService.updateById(request, id);
	}
	
	@DeleteMapping("/{id}")
	public String deleteCarreraById(@PathVariable int id) {
		boolean ok = inscripcionService.deleteInscripcion(id);
		if(ok) {
			return "La inscripcion con el id " + id + " a sido borrada"; 
		}else {
			return "No existe inscripcion con el id " + id;
		}
		
	}
}
