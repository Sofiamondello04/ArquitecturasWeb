package com.example.demo.repository;

import com.example.demo.dto.CarrerasPorInscriptosDTO;
import com.example.demo.dto.EstudiantesPorCarrerayCiudadDto;
import com.example.demo.model.Inscripcion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InscripcionRepository extends JpaRepository<Inscripcion, Integer> {
	
	

	@Query("SELECT i FROM Inscripcion i where i.id_inscripcion = :id")
	public List<Inscripcion> findAllById(int id);

	@Query("SELECT new com.example.demo.dto.CarrerasPorInscriptosDTO (c.nombre, COUNT(c	.id_carrera)) FROM Carrera c JOIN Inscripcion i ON i.carrera.id_carrera = c.id_carrera GROUP BY c.id_carrera ORDER BY COUNT(c.id_carrera) DESC")
	public List<CarrerasPorInscriptosDTO> getCarrerasPorInscriptos();

	@Query("SELECT new com.example.demo.dto.EstudiantesPorCarrerayCiudadDto(e.dni, e.nombre, e.apellido) FROM Estudiante e " +
		       "JOIN e.inscripciones i " +
		       "WHERE i.carrera.nombre = :carrera AND e.ciudadResidencia = :ciudadResidencia")
		public List<EstudiantesPorCarrerayCiudadDto> estudiantesPorCarrerayCiudad(String ciudadResidencia, String carrera);
	
}