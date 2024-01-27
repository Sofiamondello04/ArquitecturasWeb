package com.example.demo.repository.mysql;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.mysql.Viaje;


public interface ViajeRepository extends CrudRepository<Viaje, Long> {
	
	

	
}