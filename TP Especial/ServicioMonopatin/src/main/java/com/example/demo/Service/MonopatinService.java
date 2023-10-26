package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CarrerasPorInscriptosDTO;

import com.example.demo.dto.ReporteCarrerasDTO;
import com.example.demo.model.Parada;
import com.example.demo.model.Viaje;
import com.example.demo.model.Monopatin;
import com.example.demo.repository.ParadaRepository;
import com.example.demo.repository.ViajeRepository;
import com.example.demo.repository.MonopatinRepository;

import jakarta.transaction.Transactional;

@Service
public class MonopatinService {
	
	@Autowired
	MonopatinRepository inscripcionRepository;
	@Autowired
	private ParadaRepository carreraRepository;
	@Autowired
	private ViajeRepository estudianteRepository;
	
	public List<Monopatin> getInscripciones(){
		return inscripcionRepository.findAll();
	}
	
	public Monopatin saveInscripcion(Monopatin i) {
		return this.inscripcionRepository.save(i);
	}
	
	public Optional<Monopatin> getById(int id){
		return inscripcionRepository.findById(id);
	}
	
	public Monopatin updateById(Monopatin request, int id){
		Monopatin inscripcion = inscripcionRepository.findById(id).get();
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
	public Monopatin matricular(int e, int c) {
		Optional<Viaje> estudianteOptional = estudianteRepository.findById(e);
		Optional<Parada> carreraOptional = carreraRepository.findById(c);
		if (carreraOptional.isEmpty() || estudianteOptional.isEmpty() ) {
	        System.out.println("No se encontró la carrera con el ID especificado.");
	    }
		Viaje estudiante = estudianteOptional.get();
		Parada carrera = carreraOptional.get();
		Monopatin i = new Monopatin(estudiante, carrera);

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
