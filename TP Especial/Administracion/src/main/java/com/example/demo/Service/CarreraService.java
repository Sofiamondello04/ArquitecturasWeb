package com.example.demo.Service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.demo.model.Carrera;

import com.example.demo.repository.CarreraRepository;



@Service
public class CarreraService {
	
	@Autowired
	private CarreraRepository carreraRepository;
	@Autowired

	
	public List<Carrera> getCarreras(){
		return carreraRepository.findAll();
	}
	
	public Carrera saveCarrera(Carrera c) {
		return this.carreraRepository.save(c);
	}
	
	public Optional<Carrera> getById(int id){
		return carreraRepository.findById(id);
	}
	
	public Carrera updateById(Carrera request, int id){
		Carrera carrera = carreraRepository.findById(id).get();
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
