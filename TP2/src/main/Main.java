package main;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.sql.SQLException;

import factory.FactoryEntityManager;
import entities.Estudiante;
import entities.Carrera;
import entities.Inscripcion;
import repositories.EstudianteRepositoryImpl;
import utils.Helper;

public class Main {

	public static void main(String[] args) throws SQLException, IOException  {
		EntityManager em = FactoryEntityManager.getInstance();
		
		/*EstudianteRepositoryImpl est = new EstudianteRepositoryImpl(em);
		Estudiante e = new Estudiante (33444131, "nicolas", "nanti", 35, "masculino", "olavarria", 548571);
		est.altaEstudiante(e);
		System.out.println(e);*/
		
		EstudianteRepositoryImpl est = new EstudianteRepositoryImpl(em);
		
		est.listaEstudianteOdenadoPorNombre();		
	}

}
