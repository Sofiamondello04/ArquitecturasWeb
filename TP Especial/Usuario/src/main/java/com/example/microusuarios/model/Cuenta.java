package com.example.microusuarios.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode
@Data
@Table(name= "cuenta")
public class Cuenta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCuenta;
	
	@Column
	private LocalDate fechaAlta;
	
	@Column
	private double saldo;
	
	@Column
	private boolean activa;
	
	@Column(name ="fechaAlta")
	private LocalDate fecha_alta;
	
	@ManyToMany
	private List<Usuario> usuarios;
}
