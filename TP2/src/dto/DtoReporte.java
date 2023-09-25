package dto;

import java.util.List;

import entities.Inscripcion;

public class DtoReporte {

	private String nombreCarrera;
	private int anioInscripcion;
	private int inscriptos;
	private int egresados;
	
	

	public DtoReporte(String nombreCarrera, int anioInscripcion, int inscriptos, int egresados) {
		super();
		this.nombreCarrera = nombreCarrera;
		this.anioInscripcion = anioInscripcion;
		this.inscriptos = inscriptos;
		this.egresados = egresados;
	}



	public String getNombreCarrera() {
		return nombreCarrera;
	}



	public void setNombreCarrera(String nombreCarrera) {
		this.nombreCarrera = nombreCarrera;
	}



	public int getAnioInscripcion() {
		return anioInscripcion;
	}



	public void setAnioInscripcion(int anioInscripcion) {
		this.anioInscripcion = anioInscripcion;
	}



	public int getInscriptos() {
		return inscriptos;
	}



	public void setInscriptos(int inscriptos) {
		this.inscriptos = inscriptos;
	}



	public int getEgresados() {
		return egresados;
	}



	public void setEgresados(int egresados) {
		this.egresados = egresados;
	}



	@Override
	public String toString() {
		return "\n nombre carrera : " + nombreCarrera + "; anio : " + anioInscripcion  + "inscriptos = " + inscriptos + "egresados = " + egresados ;
	}
	
	
}
