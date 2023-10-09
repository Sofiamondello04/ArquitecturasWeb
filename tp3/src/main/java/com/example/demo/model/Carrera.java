package com.example.demo.model;

import java.util.ArrayList;
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
public class Carrera {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_carrera;

	@Column(nullable = false) // indica que el campo no puede ser nulo
	private String nombre;
	@Column
	private int duracion;

	/*
	 * La anotacion mappedBy corresponde al atributo carrera de la clase
	 * Inscripción.
	 */
	@OneToMany(mappedBy = "carrera", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	@JsonIgnore
	private List<Inscripcion> inscriptos;

	public Carrera() {

	}

	public Carrera(int id_carrera, String nombre, int duracion) {
		super();
		this.id_carrera = id_carrera;
		this.nombre = nombre;
		this.duracion = duracion;
		this.inscriptos = new ArrayList<Inscripcion>();
	}

	public Carrera(String nombre, int duracion) {
		this.nombre = nombre;
		this.duracion = duracion;
		this.inscriptos = new ArrayList<Inscripcion>();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Inscripcion> getInscriptos() {
		return inscriptos;
	}

	public void setInscriptos(List<Inscripcion> inscriptos) {
		this.inscriptos = inscriptos;
	}

	public int getId_carrera() {
		return id_carrera;
	}

	@Override
	public String toString() {
		return "Carrera [id_carrera=" + id_carrera + ", nombre=" + nombre + "]";
	}

}
