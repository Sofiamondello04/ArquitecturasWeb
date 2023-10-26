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
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode
public class Monopatin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_inscripcion;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "fk_estudiante")
	private Viaje estudiante;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "fk_carrera")
	private Parada carrera;

	@Column
	private int anioInscripcion;

	@Column
	private int anioGraduacion;// ver si la necesitamos, quizas con la fecha actual no haga falta

	@Column
	private int antiguedad;

	public Monopatin() {

	}

	public Monopatin(Viaje estudiante, Parada carrera, int anioInscripcion, int anioGraduacion, int antiguedad) {
		this.estudiante = estudiante;
		this.carrera = carrera;
		this.anioInscripcion = anioInscripcion;
		this.anioGraduacion = anioGraduacion;
		this.antiguedad = antiguedad;
	}
	
	
	
	public Monopatin(Viaje estudiante, Parada carrera) {
		this.estudiante = estudiante;
		this.carrera = carrera;
		this.anioInscripcion = LocalDate.now().getYear();	
	
	}

	public Viaje getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(Viaje estudiante) {
		this.estudiante = estudiante;
	}

	public Parada getCarrera() {
		return carrera;
	}

	public void setCarrera(Parada carrera) {
		this.carrera = carrera;
	}

	public int getAnioInscripcion() {
		return anioInscripcion;
	}

	public void setAnioInscripcion(int anioInscripcion) {
		this.anioInscripcion = anioInscripcion;
	}

	public int getAnioGraduacion() {
		return anioGraduacion;
	}

	public void setAnioGraduacion(int anioGraduacion) {
		this.anioGraduacion = anioGraduacion;
	}

	public int getAntiguedad() {
		return antiguedad;
	}

	public void setAntiguedad(int antiguedad) {
		this.antiguedad = antiguedad;
	}

	public int getId_inscripcion() {
		return id_inscripcion;
	}

	@Override
	public String toString() {
		return "EstudianteCarrera [id_ec=" + id_inscripcion + ", estudiante=" + estudiante + ", carrera=" + carrera
				+ ", anioInscripcion=" + anioInscripcion + ", anioGraduacion=" + anioGraduacion + ", antiguedad="
				+ antiguedad + "]";
	}
}
