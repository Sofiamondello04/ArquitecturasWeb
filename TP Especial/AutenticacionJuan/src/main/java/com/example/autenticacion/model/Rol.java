package com.example.autenticacion.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;

import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serial;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@EqualsAndHashCode
@Data
@Table(name= "rol")
public class Rol implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id 
    @Column(length = 50)
    private String nombre;
    
    @ManyToMany(fetch = FetchType.LAZY)
	@JsonIgnoreProperties("roles")
    private List<Usuario> usuarios;
    
    public Rol () {
    	this.usuarios = new ArrayList<>();
    }
    
    public Rol(String nombre) {
		this.nombre = nombre;
		this.usuarios = new ArrayList<>();
		
	}
}
