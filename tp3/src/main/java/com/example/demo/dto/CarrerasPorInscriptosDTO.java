package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class CarrerasPorInscriptosDTO {
	
	private String nombreCarrera;
	private Long cantidadInscriptos;
	
	
	public CarrerasPorInscriptosDTO() {
		super();
	}



	public CarrerasPorInscriptosDTO(String nombreCarrera, Long cantidadInscriptos) {
		super();
		this.nombreCarrera = nombreCarrera;
		this.cantidadInscriptos = cantidadInscriptos;
	}



	public String getNombreCarrera() {
		return nombreCarrera;
	}


	public void setNombreCarrera(String nombreCarrera) {
		this.nombreCarrera = nombreCarrera;
	}




	public Long getCantidadInscriptos() {
		return cantidadInscriptos;
	}



	public void setCantidadInscriptos(Long cantidadInscriptos) {
		this.cantidadInscriptos = cantidadInscriptos;
	}



	@Override
	public String toString() {
		return "CarrerasPorInscriptosDTO [nombreCarrera=" + nombreCarrera + ", cantidadInscriptos=" + cantidadInscriptos
				+ "]";
	}
	
}
