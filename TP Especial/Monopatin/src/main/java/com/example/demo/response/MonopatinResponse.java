package com.example.demo.response;

import java.util.List;

import com.example.demo.model.mysql.Monopatin;

import lombok.Data;


@Data
public class MonopatinResponse {
	
	private List<Monopatin> monopatin; 

}