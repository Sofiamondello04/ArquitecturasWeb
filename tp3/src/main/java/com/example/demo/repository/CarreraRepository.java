package com.example.demo.repository;

import com.example.demo.model.Carrera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

//ACA VAN LAS QUERYS

public interface CarreraRepository extends JpaRepository<Carrera, Integer> {

	@Query("SELECT c FROM Carrera c where c.nombre = :nombre")
	public List<Carrera> findByNombre(String nombre);

	@Query("SELECT c FROM Carrera c where c.id_carrera = :id_carrera")
    public Optional<Carrera> findById(int id_carrera);
}
