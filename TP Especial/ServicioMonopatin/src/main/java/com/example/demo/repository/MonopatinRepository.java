package com.example.demo.repository;


import com.example.demo.model.Monopatin;



import org.springframework.data.repository.CrudRepository;


//ACA VAN LAS QUERYS ESPECIFICAS- extends JpaRepository<Monopatin, Integer>. Lo cambie para poder usar optional
public interface MonopatinRepository extends CrudRepository<Monopatin, Long> {
	
	

	
}