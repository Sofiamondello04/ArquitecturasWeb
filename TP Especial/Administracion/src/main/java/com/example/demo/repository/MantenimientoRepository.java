package com.example.demo.repository;



import com.example.demo.model.Mantenimiento;

import org.springframework.data.repository.CrudRepository;



public interface MantenimientoRepository extends CrudRepository<Mantenimiento, Long> {
	
	
}