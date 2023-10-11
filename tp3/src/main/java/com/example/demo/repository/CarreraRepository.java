package com.example.demo.repository;

import com.example.demo.dto.ReporteCarrerasDTO;
import com.example.demo.model.Carrera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//ACA VAN LAS QUERYS

@Repository
public interface CarreraRepository extends JpaRepository<Carrera, Integer> {

	@Query("SELECT c FROM Carrera c where c.nombre = :nombre")
	public Carrera findByNombre(String nombre);

}
