package dto;

import java.util.List;

import entities.Inscripcion;

public class DtoReporte {

	private String nombreCarrera;
    private int anio;
    private long inscriptos;
    private long egresados;

    // Constructor sin argumentos (constructor por defecto)
    public DtoReporte() {
    }

    // Constructor con argumentos
    public DtoReporte(String nombreCarrera, int anio, long inscriptos, long egresados) {
        this.nombreCarrera = nombreCarrera;
        this.anio = anio;
        this.inscriptos = inscriptos;
        this.egresados = egresados;
    }

	public String getNombreCarrera() {
		return nombreCarrera;
	}

	public void setNombreCarrera(String nombreCarrera) {
		this.nombreCarrera = nombreCarrera;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
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

	@Override
	public String toString() {
		return "[nombreCarrera=" + nombreCarrera + ", anio=" + anio + ", inscriptos=" + inscriptos
				+ ", egresados=" + egresados + "]\n";
	}
    
    
}
