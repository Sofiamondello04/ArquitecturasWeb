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

import com.example.demo.Service.ViajeService;
import com.example.demo.dto.EstudiantesPorCarrerayCiudadDTO;
import com.example.demo.model.Viaje;
import com.example.demo.repository.ViajeRepository;

//ACA SE MAPEAN LOS METODOS QUE MAPEAN CON LAS QUERYS PRA CADA CONSULTA REST

@RestController
@RequestMapping("estudiantes")
public class ViajeControllerJPA {
	
	@Autowired
	private ViajeService estudianteService;
	
	@Qualifier("estudianteRepository")
	@Autowired
	private final ViajeRepository repository;

	public ViajeControllerJPA(@Qualifier("estudianteRepository") ViajeRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping("")
	public List<Viaje> getEstudiantes() {
		return this.estudianteService.getEstudiantes();
	}
	
	// Solucion al inciso 2.a.
	@PostMapping("/")
	public Viaje newEstudiante(@RequestBody Viaje e) {
		return estudianteService.saveEstudiante(e);
	}
	
	@GetMapping("/{id}")
	public Optional<Viaje> getEstudianteById(@PathVariable int id) {
		return estudianteService.getById(id);
	}
	
	@PutMapping("/{id}")
	public Viaje updateEstudianteById(@RequestBody Viaje request,@PathVariable("dni") int id) {
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
	public List<Viaje> getEstudiantesByNombreAsc() {
		return repository.findAllAsc();
	}

	// Solucion la inciso 2.d.
	@GetMapping("/ByNumLibretaUniversitaria/{numLibretaUniversitaria}")
	public Viaje getPersonsByNroLibreta(@PathVariable int numLibretaUniversitaria) {
		return repository.findAllByNroLibreta(numLibretaUniversitaria);

	}

	// Solucion la inciso 2.e.
	@GetMapping("/ByGenero/{genero}")
	public List<Viaje> getPersonsByGenero(@PathVariable String genero) {
		return repository.findAllByGenero(genero);

	}

	@GetMapping("/ByApellido/{apellido}")
	public List<Viaje> getEstudiantesPorApellido(@PathVariable String apellido) {
		return repository.findAllByApellido(apellido);
	}

	@GetMapping("/ByNombre/{nombre}")
	public List<Viaje> getPersonsByNombre(@PathVariable String nombre) {
		return repository.findAllByNombre(nombre);

	}
	
	@GetMapping("/carrera/{carrera}/ciudad/{ciudadResidencia}")
	public List<EstudiantesPorCarrerayCiudadDTO> estudiantesPorCarrerayCiudad(@PathVariable String ciudadResidencia,@PathVariable String carrera){
		return this.estudianteService.estudiantesPorCarrerayCiudad(ciudadResidencia, carrera);
    		  
}

}*/
