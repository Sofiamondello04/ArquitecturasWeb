package com.example.demo.model;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;


@Entity
@Data

public class Viaje {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idViaje;
	
	@Column
	private LocalDate fechaYHoraInicio;
	
	@Column
	private LocalDate fechaYHoraFin;
	
	@Column
	private LocalDate fechaYHoraInicioPausa;
	
	@Column
	private LocalDate fechaYHoraFinPausa;

	
	@OneToOne
	private Tarifa tarifaNormal;
	
	
	@OneToOne
	private Tarifa tarifaDiferencial;
}
	

	