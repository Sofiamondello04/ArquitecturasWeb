package com.example.demo.repository;


import com.example.demo.model.Monopatin;


import org.springframework.data.jpa.repository.JpaRepository;


//ACA VAN LAS QUERYS ESPECIFICAS
public interface MonopatinRepository extends JpaRepository<Monopatin, Integer> {
	
	

	
}