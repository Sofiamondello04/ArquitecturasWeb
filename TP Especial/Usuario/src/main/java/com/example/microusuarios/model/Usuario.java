package com.example.microusuarios.model;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
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
@Table(name= "usuario")
public class Usuario implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; //agregado 10/11
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUsuario;
	@Column
	private String nombre;
	@Column
	private String apellido;
	@Column
	private Long nroCelular;
	@Column ( nullable = false )
	private String email;
	
	
	@Column( nullable = false )
    private String password;
	
	@Column
	private LocalDate fechaAlta;
	

	@ManyToMany(mappedBy = "usuarios", fetch = FetchType.LAZY)
	@JsonIgnoreProperties("usuarios") // Ignora la propiedad "cuentas" al serializar
	private List<Cuenta> cuentas;
	
	@ManyToMany(mappedBy = "usuarios", fetch = FetchType.LAZY)
	@JsonIgnoreProperties("usuarios")
    private List<Rol> roles;
	

	public Usuario() {
		this.fechaAlta = LocalDate.now();
		this.cuentas = new ArrayList<>();
		this.roles = new ArrayList<>();
	}
	
	/*Usuarios monopatines*/
	public Usuario(String nombre, String apellido, Long nroCelular, String email, String password) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.nroCelular = nroCelular;
		this.email = email;
		this.password = password;
		this.fechaAlta = LocalDate.now();
		this.cuentas = new ArrayList<>();
		this.roles = new ArrayList<>();
		
	}
	
	
}
