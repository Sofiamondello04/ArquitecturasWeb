package com.example.demo.repository;

import com.example.demo.model.Inscripcion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InscripcionRepository extends JpaRepository<Inscripcion, Integer> {

	@Query("SELECT i FROM Inscripcion i where i.id_inscripcion = :id")
	public List<Inscripcion> findAllById(int id);

}