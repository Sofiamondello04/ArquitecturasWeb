package com.example.microusuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.microusuarios.dto.CarrerasPorInscriptosDTO;
import com.example.microusuarios.dto.ReporteCarrerasDTO;
import com.example.microusuarios.model.Inscripcion;

import java.util.List;

public interface InscripcionRepository extends JpaRepository<Inscripcion, Integer> {
	
	

	@Query("SELECT i FROM Inscripcion i where i.id_inscripcion = :id")
	public List<Inscripcion> findAllById(int id);

	@Query("SELECT new com.example.microusuarios.dto.CarrerasPorInscriptosDTO (c.nombre, COUNT(c	.id_carrera)) FROM Carrera c JOIN Inscripcion i ON i.carrera.id_carrera = c.id_carrera GROUP BY c.id_carrera ORDER BY COUNT(c.id_carrera) DESC")
	public List<CarrerasPorInscriptosDTO> getCarrerasPorInscriptos();



	
	@Query("SELECT new com.example.microusuarios.dto.ReporteCarrerasDTO(c.nombre, " +
            "i.anioInscripcion, " +
            "COUNT(DISTINCT CASE WHEN i.anioInscripcion IS NULL THEN e.dni ELSE NULL END) AS inscriptos, " +
            "COUNT(DISTINCT CASE WHEN i.anioGraduacion IS NOT NULL THEN e.dni ELSE NULL END) AS egresados) " +
            "FROM Carrera c " +
            "LEFT JOIN c.inscriptos i " +
            "LEFT JOIN i.estudiante e " +
            "GROUP BY c.nombre, i.anioInscripcion " +
            "ORDER BY c.nombre ASC, i.anioInscripcion ASC")
    public List<ReporteCarrerasDTO> reporteCarrerasInsyAnt();
	
}