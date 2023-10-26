package com.example.demo.Service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.demo.model.Parada;

import com.example.demo.repository.ParadaRepository;



@Service
public class ParadaService {
	
	@Autowired
	private ParadaRepository carreraRepository;
	@Autowired

	
	public List<Parada> getCarreras(){
		return carreraRepository.findAll();
	}
	
	public Parada saveCarrera(Parada c) {
		return this.carreraRepository.save(c);
	}
	
	public Optional<Parada> getById(int id){
		return carreraRepository.findById(id);
	}
	
	public Parada updateById(Parada request, int id){
		Parada carrera = carreraRepository.findById(id).get();
		carrera.setNombre(request.getNombre());
		return carrera;
	}
	
	public Boolean deleteCarrera(int id) {
		try {
			carreraRepository.deleteById(id);
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}

}
