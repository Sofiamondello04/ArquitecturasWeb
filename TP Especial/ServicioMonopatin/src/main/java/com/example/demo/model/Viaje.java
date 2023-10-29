package com.example.demo.model;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Data
@Table(name= "viaje")
public class Viaje {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idViaje;
	
	@Column
	private LocalDate fechaYHoraInicio;
	
	@Column
	private LocalDate fechaYHoraFin;
	
	@Column
	private LocalDate fechaYHoraInicioPausa;
	
	@Column
	private LocalDate fechaYHoraFinPausa;

/*
	@OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "idTarifaNormal") // Nombre de la columna en la tabla Viaje y en la tabla Tarifa
    private Tarifa tarifaNormal;
	
	@OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "idTarifaDiferencial") // Nombre de la columna en la tabla Viaje y en la tabla Tarifa
    private Tarifa tarifaDiferencial;
	*/
	@ManyToOne
	@JoinColumn(name = "idMonopatin")
    private Monopatin monopatin;
	
	@Column
	private double kilometros;
	

	
}
	

	