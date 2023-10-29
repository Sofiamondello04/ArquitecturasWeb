package com.example.demo.model;


import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode
@Data
@Table(name= "monopatin")
public class Monopatin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idMonopatin;

	/*@OneToMany(mappedBy = "monopatin", cascade = CascadeType.ALL)*/
    /*private List<Viaje> viajes;*/


	@Column
	private Long ubicacion;

	@Column
	private String estado;


	

}
