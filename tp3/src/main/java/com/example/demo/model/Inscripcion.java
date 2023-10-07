package com.example.demo.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Inscripcion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_inscripcion;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "dni")
	private Estudiante dni;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "id_carrera")
	private Carrera carrera;
	
	@Column
	private int anioInscripcion;
	
	@Column
	private int anioGraduacion;//ver si la necesitamos, quizas con la fecha actual no haga falta
	
	@Column
	private int antiguedad;

	public Inscripcion() {
		
	}

	public Inscripcion(Estudiante estudiante, Carrera carrera, int anioInscripcion, int anioGraduacion,int antiguedad) {	
		this.dni = estudiante;
		this.carrera = carrera;
		this.anioInscripcion = anioInscripcion;
		this.anioGraduacion = anioGraduacion;
		this.antiguedad = antiguedad;
	}

	public Estudiante getEstudiante() {
		return dni;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.dni = estudiante;
	}

	public Carrera getCarrera() {
		return carrera;
	}

	public void setCarrera(Carrera carrera) {
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
		return "EstudianteCarrera [id_ec=" + id_inscripcion+ ", estudiante=" + dni + ", carrera=" + carrera
				+ ", anioInscripcion=" + anioInscripcion + ", anioGraduacion=" + anioGraduacion + ", antiguedad="
				+ antiguedad + "]";
	}
}
