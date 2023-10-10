package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Carrera;
import com.example.demo.model.Inscripcion;
import com.example.demo.repository.InscripcionRepository;

@Service
public class InscripcionService {
	
	@Autowired
	InscripcionRepository inscripcionRepository;
	
	public List<Inscripcion> getInscripciones(){
		return inscripcionRepository.findAll();
	}
	
	public Inscripcion saveInscripcion(Inscripcion i) {
		return this.inscripcionRepository.save(i);
	}
	
	public Optional<Inscripcion> getById(int id){
		return inscripcionRepository.findById(id);
	}
	
	public Inscripcion updateById(Inscripcion request, int id){
		Inscripcion inscripcion = inscripcionRepository.findById(id).get();
		inscripcion.setCarrera(request.getCarrera());
		inscripcion.setAnioGraduacion(request.getAnioGraduacion());
		inscripcion.setAnioInscripcion(request.getAnioInscripcion());
		inscripcion.setAntiguedad(request.getAntiguedad());
		inscripcion.setEstudiante(request.getEstudiante());
		return inscripcion;
	}
	
	public Boolean deleteInscripcion(int id) {
		try {
			inscripcionRepository.deleteById(id);
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
}
