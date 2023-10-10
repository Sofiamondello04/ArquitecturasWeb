package com.example.demo.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Estudiante {

	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int dni;

	@Column(nullable = false)
	private String nombre;
	@Column(nullable = false)
	private String apellido;
	@Column
	private int edad;
	@Column(nullable = false)
	private String genero;
	@Column(name = "ciudad")
	private String ciudadResidencia;
	@Column(name = "numLibretaUniversitaria")
	private int numLibretaUniversitaria;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "estudiante", cascade = CascadeType.ALL)
	@JsonManagedReference
	@JsonIgnore
	private List<Inscripcion> inscripciones;

	public Estudiante() {
		super();
	}

	public Estudiante(int dni, String nombre, String apellido, int edad, String genero, String ciudadResidencia,
			int numLibretaUniversitaria, List<Inscripcion> inscripciones) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
		this.genero = genero;
		this.ciudadResidencia = ciudadResidencia;
		this.numLibretaUniversitaria = numLibretaUniversitaria;
		this.inscripciones = inscripciones;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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
		return "Estudiante [dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido + ", edad=" + edad
				+ ", genero=" + genero + ", ciudadResidencia=" + ciudadResidencia + ", numLibretaUniversitaria="
				+ numLibretaUniversitaria + ", inscripciones=" + inscripciones + "]";
	}

	public Estudiante findById(int id_estudiante) {
		// TODO Auto-generated method stub
		return null;
	}

}
