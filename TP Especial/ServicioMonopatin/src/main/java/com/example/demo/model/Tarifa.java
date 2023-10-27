package com.example.demo.model;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;


@Entity
@Data
public class Tarifa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTarifa;

	@Column
	private double tarifaNormal;
	
	@Column
	private double tarifaDiferencial;
	
	@Column
	private LocalDate fechaVigencia;

	

}
