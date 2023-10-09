package com.example.demo.crud;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Estudiante;

@Repository
public interface EstudianteCrud extends CrudRepository<Estudiante, Integer> {

	List<Estudiante> findByApellido(String apellido);
}
