package com.example.demo.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Entity
@Data
public class Estudiante {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int dni;
	
	@Column(nullable=false)
	private String nombre;
	@Column(nullable=false)
	private String apellido;
	@Column
	private int edad;
	@Column(nullable=false)
	private String genero;
	@Column (name="ciudad")
	private String ciudadResidencia;
	@Column (name="numLibretaUniversitaria")
	private int numLibretaUniversitaria;
	
	//@OneToMany(fetch = FetchType.LAZY, mappedBy = "estudiante", cascade = CascadeType.ALL)
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable(
		    name = "Inscripcion",
		    joinColumns = @JoinColumn(name = "fk_estudiante", referencedColumnName = "dni"),
		    inverseJoinColumns = @JoinColumn(name = "fk_carrera", referencedColumnName = "id_carrera")
		)

	private List<Inscripcion> inscripciones;

	
	
	public Estudiante() {
		super();
	}
	
	public Estudiante(String nombre, String apellido, int edad, String genero, String ciudadResidencia,
			int numLibretaUniversitaria, List<Inscripcion> inscripciones) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
		this.genero = genero;
		this.ciudadResidencia = ciudadResidencia;
		this.numLibretaUniversitaria = numLibretaUniversitaria;
		this.inscripciones = inscripciones;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombres) {
		this.nombre = nombres;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public int getDni() {
		return dni;
	}


	public String getCiudadResidencia() {
		return ciudadResidencia;
	}

	public void setCiudadResidencia(String ciudadResidencia) {
		this.ciudadResidencia = ciudadResidencia;
	}

	public int getNumLibretaUniversitaria() {
		return numLibretaUniversitaria;
	}

	public void setNumLibretaUniversitaria(int numLibretaUniversitaria) {
		this.numLibretaUniversitaria = numLibretaUniversitaria;
	}

	public List<Inscripcion> getInscripciones() {
		return inscripciones;
	}

	public void setInscripciones(List<Inscripcion> inscripciones) {
		this.inscripciones = inscripciones;
	}


	@Override
	public String toString() {
		return "Estudiante [nombres=" + nombre + ", apellido=" + apellido
				+ ", edad=" + edad + ", genero=" + genero + ", dni=" + dni + ", ciudadResidencia=" + ciudadResidencia
				+ ", numLibretaUniversitaria=" + numLibretaUniversitaria + ", carreras=" + inscripciones + "]";
	}

	
	
}
