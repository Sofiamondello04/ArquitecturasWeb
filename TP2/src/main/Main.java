package main;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import factory.FactoryEntityManager;
import entities.Estudiante;
import entities.Carrera;
import entities.Inscripcion;
import repositories.CarreraRepositoryImpl;
import repositories.EstudianteRepositoryImpl;
import repositories.InscripcionRepositoryImpl;
import utils.Helper;

public class Main {

	public static void main(String[] args) throws SQLException, IOException  {
		
		/*para que no muestre el log en la consola*/
		
		Logger log = Logger.getLogger("org.hibernate");
	    log.setLevel(Level.OFF); 
		
		EntityManager em = FactoryEntityManager.getInstance();
		
		/*
		 * UTILIZACION DEL HELPER PARA POBLAR LAS TABLAS
		 * 
		 *Helper helper = new Helper(em);
		 *helper.fillTables();
		 * 
		 * 
		*/

		
		/*
		System.out.println("--------------------------");
		//2.A DAR DE ALTA A UN ESTUDIANTE
		EstudianteRepositoryImpl est = new EstudianteRepositoryImpl(em);
		Estudiante e = new Estudiante (35418667, "Bernardo", "Gonzalez", 32, "masculino", "Tandil", 95000);
		est.altaEstudiante(e);
		
		System.out.println("--------------------------");
		// DAR DE ALTA UNA CARRERA
		CarreraRepositoryImpl car = new CarreraRepositoryImpl(em);
		Carrera c = new Carrera(16, "Diseño Grafico", 5);
		car.insertarCarrera(c);
		
		//2.B MATRICULAR A UN ESTUDIANTE EN UNA CARRERA
		InscripcionRepositoryImpl iri = new InscripcionRepositoryImpl(em);
		Inscripcion ins = new Inscripcion(e, c, 2021, 2026, 5);
		iri.matricularEstudiante(ins);

		System.out.println("--------------------------");
		//2.C RECUPERAR TODOS LOS ESTUDIANTES CON UN ORDENAMIENTO SIMPLE (POR APELLIDO).
		est.listaEstudianteOdenadoPorApellido();
		
		System.out.println("--------------------------");
		//2.D RECUPERAR UN ESTUDIANTE EN BASE A SU NUMERO DE LIBRETA UNIVERSITARIA.
		est.estudiantePorLibreta(95000);
		
		System.out.println("--------------------------");
		//2.E RECUPERAR TODOS LOS ESTUDIANTES EN BASE A SU GENERO.
		est.listaEstudiantePorGenero("femenino"); //GENERO SOLICITADO
		est.listaEstudianteByGenre(); //ORDENADOS POR GENERO
		*/
		InscripcionRepositoryImpl iri = new InscripcionRepositoryImpl(em);
		System.out.println("--------------------------");
		// 2.F RECUPERAR LAS CARRERAS CON ESTUDIANTES INSCRIPTOS Y ORDENAR POR CANTIDAD DE INSCRIPTOS
		List<Carrera> carrerasConInscriptos= iri.carrerasConInscriptos();
		System.out.println("Carreras con inscriptos ordenados por cantidad de inscriptos");
		carrerasConInscriptos.forEach( p-> System.out.println(p));
	}

}
