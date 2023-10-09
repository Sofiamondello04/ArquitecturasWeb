package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Inscripcion;
import com.example.demo.repository.InscripcionRepository;

//ACA SE MAPEAN LOS METODOS QUE MAPEAN CON LAS QUERYS PRA CADA CONSULTA REST

@RestController
@RequestMapping("inscripciones")
public class InscripcionControllerJPA {

	@Qualifier("inscripcionRepository")
	@Autowired
	private final InscripcionRepository repository;

	public InscripcionControllerJPA(@Qualifier("inscripcionRepository") InscripcionRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/")
	public List<Inscripcion> getInscripciones() {
		return repository.findAll();
	}

	@PostMapping("/")
	public Inscripcion newInscripcion(@RequestBody Inscripcion i) {
		return repository.save(i);
	}

	@GetMapping("/{id}")
	Optional<Inscripcion> one(@PathVariable int id) {
		return repository.findById(id);
	}

	/*
	 * @PutMapping("/{id}") Person replacePerson(@RequestBody Person
	 * newPerson, @PathVariable Long id) {
	 * 
	 * return repository.findById(id) .map(person -> {
	 * person.setName(newPerson.getName());
	 * person.setSurname(newPerson.getSurname()); return repository.save(person); })
	 * .orElseGet(() -> { newPerson.setDni(id); return repository.save(newPerson);
	 * }); }
	 */
	@DeleteMapping("/{id}")
	void deleteCarrera(@PathVariable int id) {
		repository.deleteById(id);
	}
}
