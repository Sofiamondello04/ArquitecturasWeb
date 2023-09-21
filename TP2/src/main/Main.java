package main;

import java.io.FileReader;

import javax.persistence.EntityManager;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

import factory.FactoryEntityManager;
import entities.Estudiante;
import entities.Carrera;
import entities.Inscripcion;
import repositories.EstudianteRepositoryImpl;
import java.io.FileReader;
import utils.Helper;

public class Main {

	public static void main(String[] args) throws SQLException, IOException  {
		EntityManager em = FactoryEntityManager.getInstance();
		
		/*EstudianteRepositoryImpl eri = new EstudianteRepositoryImpl(em);
		Estudiante e = new Estudiante (70, "sofia", "mondello", 28, "fem", "tandil", 548541);
		eri.altaEstudiante(e);
		System.out.println(e);*/
		
	}

}
