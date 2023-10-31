package com.example.demo.repository;


import com.example.demo.model.Administrador;

import org.springframework.data.repository.CrudRepository;


//ACA VAN LAS QUERYS ESPECIFICAS- extends JpaRepository<Administrador, Integer>. Lo cambie para poder usar optional
public interface AdministradorRepository extends CrudRepository<Administrador, Long> {
	
	

	
}