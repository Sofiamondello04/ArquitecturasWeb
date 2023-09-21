package entities;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;



@Entity
public class Estudiante {
	@Id
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
	@Column
	private int numLibretaUniversitaria;
	/*esto se puede hacer asi o hacer una relacion ManyToMany con carreras directamente.
	el mappedBy corresponde al atributo estudiante de la clase Inscripción.*/
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "estudiante")
	private List<Inscripcion> inscripciones;
	
	public Estudiante(int dni, String nombre, String apellido, int edad, String genero, String ciudad, int numLibretaUniversitaria) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
		this.genero = genero;
		this.dni = dni;
		this.ciudadResidencia = ciudad;
		this.numLibretaUniversitaria = numLibretaUniversitaria;
		this.inscripciones = new ArrayList<Inscripcion>();
	}

	public String getNombres() {
		return nombre;
	}

	public void setNombres(String nombres) {
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
