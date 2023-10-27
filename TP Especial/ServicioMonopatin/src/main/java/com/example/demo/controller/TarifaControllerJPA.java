/*package com.example.demo.controller;

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

import com.example.demo.Service.ParadaService;
import com.example.demo.model.Parada;
import com.example.demo.repository.ParadaRepository;

//ACA SE MAPEAN LOS METODOS QUE MAPEAN CON LAS QUERYS PRA CADA CONSULTA REST

@RestController
@RequestMapping("carreras")

public class TarifaControllerJPA {
	
	@Autowired
	private ParadaService carreraService;
	
	@Qualifier("carreraRepository")
	@Autowired
	private final ParadaRepository repository;

	public TarifaControllerJPA(@Qualifier("carreraRepository") ParadaRepository repository) {
		this.repository = repository;
	}

	@GetMapping("")
	public List<Parada> getCarreras() {
		return this.carreraService.getCarreras();
	}
	
	@PostMapping(" /")
	public Parada newCarrera(@RequestBody Parada c) {
		return carreraService.saveCarrera(c);
	}
	
	@GetMapping("/{id}")
	Optional<Parada> getCarreraById(@PathVariable int id) {
		return carreraService.getById(id);
	}
	
	@GetMapping("/ByNombre/{nombre}")
	public Parada getCarrerasByNombre(@PathVariable String nombre) {
		return repository.findByNombre(nombre);
	}

	@PutMapping("/{id}")
	public Parada updateCarreraById(@RequestBody Parada request,@PathVariable("id_carrera") int id) {
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
*/