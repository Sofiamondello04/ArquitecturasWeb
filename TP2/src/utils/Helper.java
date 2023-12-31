package utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entities.Carrera;
import entities.Estudiante;
import entities.Inscripcion;
import factory.FactoryEntityManager;
import repositories.CarreraRepositoryImpl;
import repositories.EstudianteRepository;
import repositories.EstudianteRepositoryImpl;

import org.apache.commons.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;


public class Helper {
	private EntityManager em;
	
	
	public Helper(EntityManager em) {
		this.em =em;
	}
	
	//aca se cargan los csv. Asegurarse que los archivos tengan los mismo nombres


	public void fillTableEstudiante() throws SQLException, FileNotFoundException, IOException {
		try {
	        this.em.getTransaction().begin();
	        CSVParser datosEstudiantes;
	        datosEstudiantes = CSVFormat.DEFAULT.withHeader().parse(new FileReader("./src/csv/estudiantes.csv"));
	        
	        for (CSVRecord row : datosEstudiantes) {
	            int dni = Integer.parseInt(row.get("dni"));
	            String nombre = row.get("nombre");
	            String apellido = row.get("apellido");
	            int edad = Integer.parseInt(row.get("edad"));
	            String genero = row.get("genero");
	            String ciudad = row.get("ciudad");
	            int numLibretaUniversitaria = Integer.parseInt(row.get("numLibretaUniversitaria"));
	            
	            Estudiante e = new Estudiante(dni, nombre, apellido, edad, genero, ciudad, numLibretaUniversitaria);
	            
	            em.persist(e);
	        }

	        // Commit una vez que todas las operaciones de persistencia est�n completas.
	        em.getTransaction().commit();
	    } catch (Exception e) {
	        // En caso de error, realiza un rollback de la transacci�n.
	        if (em.getTransaction() != null && em.getTransaction().isActive()) {
	            em.getTransaction().rollback();
	        }
	        // Maneja el error de alguna manera (registra, lanza una excepci�n, etc.).
	        e.printStackTrace();
	    } 
	}
		
		
		public void fillTableCarrera() throws SQLException, FileNotFoundException, IOException {
			   try {
				   this.em.getTransaction().begin();
			        CSVParser datosCarrera;
			        datosCarrera = CSVFormat.DEFAULT.withHeader().parse(new FileReader("./src/csv/carreras.csv"));
			        
			        for (CSVRecord row : datosCarrera) {
			            int id_carrera = Integer.parseInt(row.get("id_carrera"));
			            String nombre = row.get("carrera");
			            int duracion = Integer.parseInt(row.get("duracion"));
			            
			            Carrera c = new Carrera(id_carrera, nombre, duracion);
			            
			            em.persist(c);
			        }

			        // Commit una vez que todas las operaciones de persistencia est�n completas.
			        em.getTransaction().commit();
			    } catch (Exception e) {
			        // En caso de error, realiza un rollback de la transacci�n.
			        if (em.getTransaction() != null && em.getTransaction().isActive()) {
			            em.getTransaction().rollback();
			        }
			        // Maneja el error de alguna manera (registra, lanza una excepci�n, etc.).
			        e.printStackTrace();
			    } 
		}
		
		public void fillTableInscripcion() throws SQLException, FileNotFoundException, IOException {
			try {
				this.em.getTransaction().begin();
		        CSVParser datosInscripcion;
		        datosInscripcion = CSVFormat.DEFAULT.withHeader().parse(new FileReader("./src/csv/inscripcion.csv"));
		        
		        EstudianteRepositoryImpl eri = new EstudianteRepositoryImpl(em);
		        CarreraRepositoryImpl cri = new CarreraRepositoryImpl(em);

		        for (CSVRecord row : datosInscripcion) {
		            int id_estudiante = Integer.parseInt(row.get("id_estudiante"));
		            int id_carrera = Integer.parseInt(row.get("id_carrera"));
		            int anioInscripcion = Integer.parseInt(row.get("inscripcion"));
		            int anioGraduacion = Integer.parseInt(row.get("graduacion"));
		            int antiguedad = Integer.parseInt(row.get("antiguedad"));

		            Estudiante e = eri.estudiantePorDNI(id_estudiante);
		            Carrera c = cri.carreraPorId(id_carrera);

		            Inscripcion i = new Inscripcion(e, c, anioInscripcion, anioGraduacion, antiguedad);

		            em.persist(i);
		        }

		        // Commit una vez que todas las operaciones de persistencia est�n completas.
		        em.getTransaction().commit();
		    } catch (Exception e) {
		        // En caso de error, realiza un rollback de la transacci�n.
		        if (em.getTransaction() != null && em.getTransaction().isActive()) {
		            em.getTransaction().rollback();
		        }
		        // Maneja el error de alguna manera (registra, lanza una excepci�n, etc.).
		        e.printStackTrace();
		    } 
		}

}

