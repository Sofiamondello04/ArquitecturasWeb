package com.example.demo.dto;


import com.fasterxml.jackson.annotation.JsonAutoDetect;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ReporteCarrerasDTO {
    private String nombreCarrera;
    private int anioInscripcion;
    private long inscriptos;
    private long egresados;
    

    public ReporteCarrerasDTO() {
		super();
	}

	public ReporteCarrerasDTO(String nombreCarrera, int anioInscripcion, long inscriptos, long egresados) {
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

	public long getInscriptos() {
		return inscriptos;
	}

	public void setInscriptos(long inscriptos) {
		this.inscriptos = inscriptos;
	}

	public long getEgresados() {
		return egresados;
	}

	public void setEgresados(long egresados) {
		this.egresados = egresados;
	}
	
	
}