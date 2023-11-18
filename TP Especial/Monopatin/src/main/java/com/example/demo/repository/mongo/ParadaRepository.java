package com.example.demo.repository.mongo;



import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.mongo.Parada;


@Repository
public interface ParadaRepository extends MongoRepository<Parada, Long> {
	
	

	
}