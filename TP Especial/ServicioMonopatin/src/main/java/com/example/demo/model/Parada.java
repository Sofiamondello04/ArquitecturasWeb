package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;


@Entity
@Data

public class Parada {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idParada;

	@Column
	private Long ubicacion;
	

}
