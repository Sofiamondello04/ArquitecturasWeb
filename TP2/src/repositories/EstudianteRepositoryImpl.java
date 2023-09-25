package repositories;

import java.sql.SQLException;
import java.util.List;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import dto.DtoCantidadInscriptosPorCarrera;
import dto.DtoEstudiante;
import entities.Carrera;
import entities.Estudiante;

public class EstudianteRepositoryImpl implements EstudianteRepository {
	private EntityManager em;
		
	public EstudianteRepositoryImpl(EntityManager em) {
		this.em = em;
	}

	@Override
	public void altaEstudiante(Estudiante e) throws SQLIntegrityConstraintViolationException {
		try {
			this.em.getTransaction().begin();
			if(!em.contains(e)) {
				em.persist(e);
			} 
			else {
				em.merge(e);		
			}
			this.em.getTransaction().commit();			
			System.out.println("El estudiante fue dado de alta con exito");

		}
		catch (Exception ex) {
			System.out.println("El estudiante ya existe");
		}

	}

	@Override
	public List<Estudiante> listaEstudianteOdenadoPorApellido() throws SQLException {
		this.em.getTransaction().begin();	
		String jpql = "SELECT e FROM Estudiante e ORDER BY e.apellido ASC";
		TypedQuery<Estudiante> query = em.createQuery(jpql, Estudiante.class);
		List<Estudiante> estudiantes = query.getResultList();
		if(!estudiantes.isEmpty()) {
			System.out.println("Estudiantes ordenados por apellido:");
			for (Estudiante estudiante : estudiantes) {
			    System.out.println(estudiante.getApellido() + ", " + estudiante.getNombre());
			}		
		}
		else {
			System.out.println("No se encontraron estudiantes");
		}
		this.em.getTransaction().commit();
		return estudiantes;
	}

	@Override
	public Estudiante estudiantePorLibreta(int numLibretaUniversitaria) throws SQLException{
		// MANEJAR EXCEPCIONES
		try {
			this.em.getTransaction().begin();	
			String jpql = "SELECT e FROM Estudiante e WHERE e.numLibretaUniversitaria = :numLibretaUniversitaria";
			TypedQuery<Estudiante> query = em.createQuery(jpql, Estudiante.class);
	        query.setParameter("numLibretaUniversitaria", numLibretaUniversitaria);
	        Estudiante estudiante = query.getSingleResult();
	        if (estudiante!=null) {
	        	System.out.println(estudiante);
	            this.em.getTransaction().commit();
	        }
	    }
		catch (Exception exc) {
			System.out.println("Estudiante no encontrado para el numero de libreta: " + numLibretaUniversitaria);
		}

		return null;
	}

	@Override
	public List<Estudiante> listaEstudiantePorGenero(String genero) {
		this.em.getTransaction().begin();	
		String jpql = "SELECT e FROM Estudiante e WHERE e.genero = :genero";
		TypedQuery<Estudiante> query = em.createQuery(jpql, Estudiante.class);
		query.setParameter("genero", genero);
		List<Estudiante> estudiantesPorGenero = query.getResultList();
		if (!estudiantesPorGenero.isEmpty()) {
			System.out.println("Estudiantes encontrados con el género "+ genero + ":");
			for (Estudiante estudiante : estudiantesPorGenero) {
			    System.out.println(estudiante.getApellido() + ", " + estudiante.getNombre());
			}
		}
		else {
			System.out.println("No se encontraron estudiantes con este género");
		}
		
		this.em.getTransaction().commit();

		return estudiantesPorGenero;
	}
	
	public Estudiante estudiantePorDNI(int dni) throws SQLException{
		this.em.getTransaction().begin();	
		String jpql = "SELECT e FROM Estudiante e WHERE e.dni = :dni";
		 TypedQuery<Estudiante> query = em.createQuery(jpql, Estudiante.class);
	        query.setParameter("dni", dni);
	        Estudiante estudiante = query.getSingleResult();
		this.em.getTransaction().commit();
	    em.close();

		return estudiante;
	}
	
	public List<Estudiante> listaEstudianteByGenre() throws SQLException {
		this.em.getTransaction().begin();	
		String jpql = "SELECT e FROM Estudiante e ORDER BY e.genero";
		TypedQuery<Estudiante> query = em.createQuery(jpql, Estudiante.class);
		List<Estudiante> estudiantes = query.getResultList();
		if(!estudiantes.isEmpty()) {
			System.out.println("Estudiantes ordenados por genero:");
			for (Estudiante estudiante : estudiantes) {
			    System.out.println(estudiante.getApellido() + ", " + estudiante.getNombre() + "," + estudiante.getGenero());
			}		
		}
		else {
			System.out.println("No se encontraron estudiantes");
		}
		this.em.getTransaction().commit();

		return estudiantes;
	}
	
	public List<DtoEstudiante> listaEstudiantePorCarrerayCiudad(String carrera, String ciudad) {
		 this.em.getTransaction().begin();
		    Query query = em.createQuery("SELECT NEW dto.DtoEstudiante(e.dni, e.nombre, e.apellido) " +
		    	    "FROM Estudiante e " +
		    	    "INNER JOIN e.inscripciones i " +
		    	    "INNER JOIN i.carrera c " +
		    	    "WHERE c.nombre = :nombreCarrera " +
		    	    "AND e.ciudadResidencia = :ciudadResidencia",
		    	    DtoEstudiante.class);

		    query.setParameter("nombreCarrera", carrera); // Establece el valor del par�metro "nombreCarrera"
		    query.setParameter("ciudadResidencia", ciudad); // Establece el valor del par�metro "ciudadResidencia"

		    List<DtoEstudiante> estudiantesDTO = query.getResultList();
		    em.getTransaction().commit();
		    em.close();
		    
			return estudiantesDTO;
	}
}
