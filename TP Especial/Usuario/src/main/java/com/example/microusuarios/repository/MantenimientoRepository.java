package com.example.microusuarios.repository;



import org.springframework.data.repository.CrudRepository;

import com.example.microusuarios.model.Mantenimiento;



public interface MantenimientoRepository extends CrudRepository<Mantenimiento, Long> {
	
	
}