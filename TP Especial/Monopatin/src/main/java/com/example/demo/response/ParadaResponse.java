package com.example.demo.response;

import java.util.List;

import com.example.demo.model.mongo.Parada;

import lombok.Data;


@Data
public class ParadaResponse {
	
	private List<Parada> parada; 

}