package com.example.microusuarios.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.microusuarios.dto.CarrerasPorInscriptosDTO;
import com.example.microusuarios.dto.ReporteCarrerasDTO;
import com.example.microusuarios.model.Carrera;
import com.example.microusuarios.model.Estudiante;
import com.example.microusuarios.model.Inscripcion;
import com.example.microusuarios.repository.CarreraRepository;
import com.example.microusuarios.repository.EstudianteRepository;
import com.example.microusuarios.repository.InscripcionRepository;

import jakarta.transaction.Transactional;

@Service
public class InscripcionService {
	
	@Autowired
	InscripcionRepository inscripcionRepository;
	@Autowired
	private CarreraRepository carreraRepository;
	@Autowired
	private EstudianteRepository estudianteRepository;
	
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

	@Transactional
	public Inscripcion matricular(int e, int c) {
		Optional<Estudiante> estudianteOptional = estudianteRepository.findById(e);
		Optional<Carrera> carreraOptional = carreraRepository.findById(c);
		if (carreraOptional.isEmpty() || estudianteOptional.isEmpty() ) {
	        System.out.println("No se encontró la carrera con el ID especificado.");
	    }
		Estudiante estudiante = estudianteOptional.get();
		Carrera carrera = carreraOptional.get();
		Inscripcion i = new Inscripcion(estudiante, carrera);

		return this.inscripcionRepository.save(i);
	}

	@Transactional
	public List<CarrerasPorInscriptosDTO> getCarrerasPorCantidadInscriptos() {
		return this.inscripcionRepository.getCarrerasPorInscriptos();
	}

	

	public List<ReporteCarrerasDTO> reporteCarrerasInscriptosyAntiguedad() {
		return this.inscripcionRepository.reporteCarrerasInsyAnt();
	}
}
