package com.example.demo.model.mysql;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Entity
@Data
@Table(name= "viaje")

public class Viaje implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idViaje;
	
	@Column
	@NotNull
	private LocalDate fechaYHoraInicio;
	
	@Column
	private LocalDate fechaYHoraFin;
	
	@Column
	private LocalDate fechaYHoraInicioPausa;
	
	@Column
	private LocalDate fechaYHoraFinPausa;

	
	@ManyToOne
	@JsonIgnoreProperties("viajes")
	private Monopatin monopatin;
	
	@Column
	private double precio;

	@Column
	private double kilometros;
	

	
	
}
	

	