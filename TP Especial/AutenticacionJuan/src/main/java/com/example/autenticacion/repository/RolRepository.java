package com.example.autenticacion.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.autenticacion.model.Rol;


//ACA VAN LAS QUERYS ESPECIFICAS- extends JpaRepository<Monopatin, Integer>. Lo cambie para poder usar optional
public interface RolRepository extends CrudRepository<Rol, String> {
		

}
