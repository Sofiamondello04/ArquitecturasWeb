package com.example.demo.repository;

import com.example.demo.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//ACA VAN LAS QUERYS

public interface EstudianteRepository extends JpaRepository<Estudiante, Integer> {

	@Query("SELECT e FROM Estudiante e ORDER BY nombre ASC")
	public List<Estudiante> findAllAsc();

	@Query("SELECT e FROM Estudiante e where e.apellido = :apellido")
	public List<Estudiante> findAllByApellido(String apellido);

	@Query("SELECT e FROM Estudiante e where e.nombre = :nombre")
	public List<Estudiante> findAllByNombre(String nombre);

	@Query("SELECT e FROM Estudiante e where e.genero = :genero")
	public List<Estudiante> findAllByGenero(String genero);

	@Query("SELECT e FROM Estudiante e where e.numLibretaUniversitaria = :numLibretaUniversitaria")
	public Estudiante findAllByNroLibreta(int numLibretaUniversitaria);
}
