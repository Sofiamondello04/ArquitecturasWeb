package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class EstudiantesPorCarrerayCiudadDTO {
	private int dni;
	private String nombre;
	private String apellido;
	public EstudiantesPorCarrerayCiudadDTO(int dni, String nombre, String apellido) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
	}
	public EstudiantesPorCarrerayCiudadDTO() {
		super();
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
	@Override
	public String toString() {
		return "EstudiantesPorCarrerayCiudadDto [dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido + "]";
	}
	
	
}

