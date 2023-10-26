package com.example.demo.repository;


import com.example.demo.model.Parada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


//ACA VAN LAS QUERYS

@Repository
public interface ParadaRepository extends JpaRepository<Parada, Integer> {

	@Query("SELECT c FROM Carrera c where c.nombre = :nombre")
	public Parada findByNombre(String nombre);

}
