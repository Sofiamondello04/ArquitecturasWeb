package com.example.demo.response;

import java.util.List;

import com.example.demo.model.mysql.Viaje;

import lombok.Data;


@Data
public class ViajeResponse {
	
	private List<Viaje> viaje; 

}