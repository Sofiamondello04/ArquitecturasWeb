package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.EstudiantesPorCarrerayCiudadDTO;
import com.example.demo.model.Viaje;
import com.example.demo.repository.ViajeRepository;

import jakarta.transaction.Transactional;

@Service
public class ViajeService {
	
	@Autowired
	ViajeRepository estudianteRepository;
	
	public List<Viaje> getEstudiantes(){
		return estudianteRepository.findAll();
	}
	
	public Viaje saveEstudiante(Viaje e) {
		return this.estudianteRepository.save(e);
	}
	
	public Optional<Viaje> getById(int id){
		return estudianteRepository.findById(id);
	}
	
	public Viaje updateById(Viaje request, int id){
		Viaje estudiante = estudianteRepository.findById(id).get();
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
	
	@Transactional
	public List<EstudiantesPorCarrerayCiudadDTO> estudiantesPorCarrerayCiudad(String ciudadResidencia, String carrera) {
		return this.estudianteRepository.estudiantesPorCarrerayCiudad(ciudadResidencia, carrera);
	}
}
