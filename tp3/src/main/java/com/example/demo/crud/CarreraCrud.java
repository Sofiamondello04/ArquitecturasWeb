package com.example.demo.crud;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Carrera;

@Repository
public
interface CarreraCrud extends CrudRepository<Carrera, Integer> {

    List<Carrera> findByNombre(String nombre);
}
