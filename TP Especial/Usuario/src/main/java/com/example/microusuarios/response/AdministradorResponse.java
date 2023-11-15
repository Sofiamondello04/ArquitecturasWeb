package com.example.microusuarios.response;

import java.util.List;

import com.example.microusuarios.model.Administrador;

import lombok.Data;


@SuppressWarnings("unused")
@Data
public class AdministradorResponse {
	
    private List<Administrador> administrador;

	public void setAdministrador(List<Administrador> administrador) {
        this.administrador = administrador;
    }

}