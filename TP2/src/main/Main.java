package main;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import factory.FactoryEntityManager;
import entities.Estudiante;
import entities.Carrera;
import entities.Inscripcion;
import repositories.EstudianteRepositoryImpl;
import utils.Helper;

public class Main {

	public static void main(String[] args) throws SQLException, IOException  {
		
		/*para que no muestre el log en la consola*/
		
		Logger log = Logger.getLogger("org.hibernate");
	    log.setLevel(Level.OFF); 
		
		EntityManager em = FactoryEntityManager.getInstance();
		
		EstudianteRepositoryImpl est = new EstudianteRepositoryImpl(em);
		Estudiante e = new Estudiante (14570749, "Gladys", "Carbajal", 88, "femenino", "olavarria", 558581);
		
		
		est.altaEstudiante(e);
		
		
		est.listaEstudianteOdenadoPorNombre();
		
		est.estudiantePorLibreta(548541);
		
		est.listaEstudiantePorGenero("femenino");
	}

}
