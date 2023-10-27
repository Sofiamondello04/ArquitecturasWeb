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
	MonopatinRepository monopatinRepository;
	
	
	public List<Monopatin> getMonopatines(){
		return monopatinRepository.findAll();
	}
	@Transactional
	public Monopatin saveInscripcion(Monopatin m) {
		return this.monopatinRepository.save(m);
	}
	
	public Optional<Monopatin> getById(int id){
		return monopatinRepository.findById(id);
	}
	@Transactional
	public Monopatin updateById(Monopatin request, int id){
		Monopatin monopatin = monopatinRepository.findById(id).get();
		monopatin.setViajes(request.getViajes());
		monopatin.setUbicacion(request.getUbicacion());
		monopatin.setEstado(request.getEstado());
		return monopatin;
	}
	
	
	public Boolean deleteInscripcion(int id) {
		try {
			monopatinRepository.deleteById(id);
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}

	/*@Transactional
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
	}*/


}
