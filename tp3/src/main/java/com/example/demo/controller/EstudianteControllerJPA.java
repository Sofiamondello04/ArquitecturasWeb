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

import com.example.demo.Service.EstudianteService;
import com.example.demo.model.Estudiante;
import com.example.demo.repository.EstudianteRepository;

//ACA SE MAPEAN LOS METODOS QUE MAPEAN CON LAS QUERYS PRA CADA CONSULTA REST

@RestController
@RequestMapping("estudiantes")
public class EstudianteControllerJPA {
	
	@Autowired
	private EstudianteService estudianteService;
	
	@Qualifier("estudianteRepository")
	@Autowired
	private final EstudianteRepository repository;

	public EstudianteControllerJPA(@Qualifier("estudianteRepository") EstudianteRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping("/")
	public List<Estudiante> getEstudiantes() {
		return this.estudianteService.getEstudiantes();
	}
	
	// Solucion al inciso 2.a.
	@PostMapping("/")
	public Estudiante newEstudiante(@RequestBody Estudiante e) {
		return estudianteService.saveEstudiante(e);
	}
	
	@GetMapping("/{id}")
	public Optional<Estudiante> getEstudianteById(@PathVariable int id) {
		return estudianteService.getById(id);
	}
	
	@PutMapping("/{id}")
	public Estudiante updateEstudianteById(@RequestBody Estudiante request,@PathVariable("dni") int id) {
		return this.estudianteService.updateById(request, id);
	}
	
	@DeleteMapping("/{id}")
	public String deleteEstudianteById(@PathVariable int id) {
		boolean ok = estudianteService.deleteEstudiante(id);
		if(ok) {
			return "El estudiante con el id " + id + " a sido borrado"; 
		}else {
			return "No existe estudiante con el id " + id;
		}
		
	}

	// Solucion al inciso 2.c.
	@GetMapping("/ByNombreAsc")
	public List<Estudiante> getEstudiantesByNombreAsc() {
		return repository.findAllAsc();
	}

	// Solucion la inciso 2.d.
	@GetMapping("/ByNumLibretaUniversitaria/{numLibretaUniversitaria}")
	public Estudiante getPersonsByNroLibreta(@PathVariable int numLibretaUniversitaria) {
		return repository.findAllByNroLibreta(numLibretaUniversitaria);

	}

	// Solucion la inciso 2.e.
	@GetMapping("/ByGenero/{genero}")
	public List<Estudiante> getPersonsByGenero(@PathVariable String genero) {
		return repository.findAllByGenero(genero);

	}

	@GetMapping("/ByApellido/{apellido}")
	public List<Estudiante> getEstudiantesPorApellido(@PathVariable String apellido) {
		return repository.findAllByApellido(apellido);
	}

	@GetMapping("/ByNombre/{nombre}")
	public List<Estudiante> getPersonsByNombre(@PathVariable String nombre) {
		return repository.findAllByNombre(nombre);

	}

}
