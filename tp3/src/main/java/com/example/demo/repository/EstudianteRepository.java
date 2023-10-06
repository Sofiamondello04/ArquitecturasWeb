package com.example.demo.repository;

import com.example.demo.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EstudianteRepository extends JpaRepository<Estudiante, Integer> {


	@Query("SELECT e FROM Estudiante e where e.apellido = :apellido")
    public List<Estudiante> findAllByApellido(String apellido);

    @Query("SELECT e FROM Estudiante e where e.nombre = :nombre")
    public List<Estudiante> findAllByNombre(String nombre);
}

