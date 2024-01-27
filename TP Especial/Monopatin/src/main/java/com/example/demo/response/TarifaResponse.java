package com.example.demo.response;

import java.util.List;

import com.example.demo.model.mysql.Tarifa;

import lombok.Data;


@Data
public class TarifaResponse {
	
	private List<Tarifa> tarifa; 

}