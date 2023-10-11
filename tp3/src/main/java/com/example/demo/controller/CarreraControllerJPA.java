package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.CarreraService;
import com.example.demo.dto.ReporteCarrerasDTO;
import com.example.demo.model.Carrera;
import com.example.demo.repository.CarreraRepository;

//ACA SE MAPEAN LOS METODOS QUE MAPEAN CON LAS QUERYS PRA CADA CONSULTA REST

@RestController
@RequestMapping("carreras")

public class CarreraControllerJPA {
	
	@Autowired
	private CarreraService carreraService;
	
	@Qualifier("carreraRepository")
	@Autowired
	private final CarreraRepository repository;

	public CarreraControllerJPA(@Qualifier("carreraRepository") CarreraRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/")
	public List<Carrera> getCarreras() {
		return this.carreraService.getCarreras();
	}
	
	@PostMapping(" /")
	public Carrera newCarrera(@RequestBody Carrera c) {
		return carreraService.saveCarrera(c);
	}
	
	@GetMapping("/{id}")
	Optional<Carrera> getCarreraById(@PathVariable int id) {
		return carreraService.getById(id);
	}
	
	@GetMapping("/ByNombre/{nombre}")
	public Carrera getCarrerasByNombre(@PathVariable String nombre) {
		return repository.findByNombre(nombre);
	}

	@PutMapping("/{id}")
	public Carrera updateCarreraById(@RequestBody Carrera request,@PathVariable("id_carrera") int id) {
		return this.carreraService.updateById(request, id);
	}
	
	@DeleteMapping("/{id}")
	public String deleteCarreraById(@PathVariable int id) {
		boolean ok = carreraService.deleteCarrera(id);
		if(ok) {
			return "La carrera con el id " + id + " a sido borrada"; 
		}else {
			return "No existe carrera con el id " + id;
		}
		
	}
	
}
