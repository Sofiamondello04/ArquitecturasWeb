package com.example.demo.model;


import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode
@Data
public class Monopatin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idMonopatin;

	@OneToMany(cascade = CascadeType.MERGE)
	@JoinColumn(name = "idViaje")
	private List<Viaje> viajes;


	@Column
	private Long ubicacion;

	@Column
	private String estado;


	

}
