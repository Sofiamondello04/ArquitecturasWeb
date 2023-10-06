package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Carrera {
	
	@Id
	private int id_carrera;
	
	@Column(nullable=false)// indica que el campo no puede ser nulo
	private String nombre;
	@Column 
	private int duracion;
	
	/*La anotacion mappedBy corresponde al atributo carrera de la clase Inscripción.*/
	@OneToMany (mappedBy = "carrera", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Inscripcion> inscriptos;

	
	public Carrera() {

	}

	public Carrera(int id, String nombre, int duracion) {
		this.id_carrera = id;	
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
		return "Carrera [id_carrera=" + id_carrera + ", nombre=" + nombre + ", inscriptos=" + inscriptos + "]";
	}

	
}
