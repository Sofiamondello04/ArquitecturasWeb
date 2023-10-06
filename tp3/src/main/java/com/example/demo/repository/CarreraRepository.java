package com.example.demo.repository;

import com.example.demo.model.Carrera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarreraRepository extends JpaRepository<Carrera, Integer> {


	@Query("SELECT c FROM Carrera c where c.nombre = :nombre")
    public List<Carrera> findAllByNombre(String nombre);

}
