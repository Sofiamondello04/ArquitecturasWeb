package com.example.microusuarios.model;



import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@Table(name= "administrador")
public class Administrador {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idAdministrador;
	@Column
	private String nombre;
	

	
}
