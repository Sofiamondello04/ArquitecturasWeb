package com.example.microusuarios.model;

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
@Table(name= "usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUsuario;
	@Column
	private String nombre;
	@Column
	private String apellido;
	@Column
	private int nroCelular;
	@Column
	private String email;

	@ManyToMany(mappedBy = "usuarios")
	private List<Cuenta> cuentas;

}
