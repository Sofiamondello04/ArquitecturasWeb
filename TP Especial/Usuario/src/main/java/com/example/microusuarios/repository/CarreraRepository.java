package com.example.microusuarios.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.microusuarios.model.Carrera;


//ACA VAN LAS QUERYS

@Repository
public interface CarreraRepository extends JpaRepository<Carrera, Integer> {

	@Query("SELECT c FROM Carrera c where c.nombre = :nombre")
	public Carrera findByNombre(String nombre);

}
