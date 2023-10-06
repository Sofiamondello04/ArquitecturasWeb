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

import com.example.demo.model.Carrera;
import com.example.demo.repository.CarreraRepository;

@RestController
@RequestMapping("carreras")
public class CarreraControllerJPA {
	@Qualifier("carreraRepository")
    @Autowired
    private final CarreraRepository repository;

    public CarreraControllerJPA(@Qualifier("carreraRepository") CarreraRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public List<Carrera> getCarreras() {
        return repository.findAll();
    }

    @GetMapping("/ByNombre/{nombre}")
    public List<Carrera> getCarrerasByNombre(@PathVariable String nombre) {
        return repository.findAllByNombre(nombre);
    }

   
    @PostMapping("/")
    public Carrera newCarrera(@RequestBody Carrera c) {
        return repository.save(c);
    }

    @GetMapping("/{id}")
    Optional<Carrera> one(@PathVariable int id) {
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
    void deleteCarrera(@PathVariable int id) {
        repository.deleteById(id);
    }
}
