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

import com.example.demo.model.Estudiante;
import com.example.demo.repository.EstudianteRepository;

@RestController
@RequestMapping("estudiantes")
public class EstudianteControllerJPA {
	@Qualifier("estudianteRepository")
    @Autowired
    private final EstudianteRepository repository;

    public EstudianteControllerJPA(@Qualifier("estudianteRepository") EstudianteRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public List<Estudiante> getEstudiantes() {
        return repository.findAll();
    }

    @GetMapping("/ByApellido/{apellido}")
    public List<Estudiante> getEstudiantesPorApellido(@PathVariable String apellido) {
        return repository.findAllByApellido(apellido);
    }

    @GetMapping("/ByNombre/{nombre}")
    public List<Estudiante> getPersonsByNombre(@PathVariable String nombre) {
        return repository.findAllByNombre(nombre);
    }

    @PostMapping("/")
    public Estudiante newEstudiante(@RequestBody Estudiante e) {
        return repository.save(e);
    }

   
    @RequestMapping(value = "/getEstudiante/{name}")
    public Estudiante getEstudiante(@PathVariable(value = "nombre") String nombre) {
        return repository.findAllByNombre(nombre).get(0);
    }
    @GetMapping("/{id}")
    Optional<Estudiante> one(@PathVariable int id) {
        return repository.findById(id);
    }
/*
    @PutMapping("/{id}")
    Person replacePerson(@RequestBody Person newPerson, @PathVariable Long id) {

        return repository.findById(id)
                .map(person -> {
                    person.setName(newPerson.getName());
                    person.setSurname(newPerson.getSurname());
                    return repository.save(person);
                })
                .orElseGet(() -> {
                    newPerson.setDni(id);
                    return repository.save(newPerson);
                });
    }
*/
    @DeleteMapping("/{id}")
    void deleteEstudiante(@PathVariable int id) {
        repository.deleteById(id);
    }
}
