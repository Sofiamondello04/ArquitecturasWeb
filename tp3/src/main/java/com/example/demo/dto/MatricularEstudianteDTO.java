package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
 
public class MatricularEstudianteDTO {
    private int idEstudiante;
    private int idCarrera;
    

    public MatricularEstudianteDTO() {
		super();
	}

	public MatricularEstudianteDTO(int idEstudiante, int idCarrera) {
        this.idEstudiante = idEstudiante;
        this.idCarrera = idCarrera;
       
    }

	public int getIdCarrera() {
		return idCarrera;
	}

	public void setIdCarrera(int idCarrera) {
		this.idCarrera = idCarrera;
	}

	public int getIdEstudiante() {
		return idEstudiante;
	}

	public void setIdEstudiante(int idEstudiante) {
		this.idEstudiante = idEstudiante;
	}

	
	
}
