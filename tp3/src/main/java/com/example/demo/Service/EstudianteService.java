package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Estudiante;
import com.example.demo.repository.EstudianteRepository;

@Service
public class EstudianteService {
	
	@Autowired
	EstudianteRepository estudianteRepository;
	
	public List<Estudiante> getEstudiantes(){
		return estudianteRepository.findAll();
	}
	
	public Estudiante saveEstudiante(Estudiante e) {
		return this.estudianteRepository.save(e);
	}
	
	public Optional<Estudiante> getById(int id){
		return estudianteRepository.findById(id);
	}
	
	public Estudiante updateById(Estudiante request, int id){
		Estudiante estudiante = estudianteRepository.findById(id).get();
		estudiante.setDni(request.getDni());
		estudiante.setNombre(request.getNombre());
		estudiante.setApellido(request.getApellido());
		estudiante.setCiudadResidencia(request.getCiudadResidencia());
		estudiante.setEdad(request.getEdad());
		estudiante.setGenero(request.getGenero());
		estudiante.setNumLibretaUniversitaria(request.getNumLibretaUniversitaria());
		return estudiante;
	}
	
	public Boolean deleteEstudiante(int id) {
		try {
			estudianteRepository.deleteById(id);
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
}
