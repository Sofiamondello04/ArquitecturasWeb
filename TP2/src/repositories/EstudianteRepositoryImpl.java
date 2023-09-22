package repositories;

import java.sql.SQLException;
import java.util.List;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;


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
			
		}
		catch (Exception ex) {
			System.out.println("El estudiante ya existe");
		}
	}

	@Override
	public List<Estudiante> listaEstudianteOdenadoPorApellido() throws SQLException {
		this.em.getTransaction().begin();	
		String jpql = "SELECT e FROM Estudiante e ORDER BY e.apellido DESC";
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
		this.em.getTransaction().begin();	
		String jpql = "SELECT e FROM Estudiante e WHERE e.numLibretaUniversitaria = :numLibretaUniversitaria";
		 TypedQuery<Estudiante> query = em.createQuery(jpql, Estudiante.class);
	        query.setParameter("numLibretaUniversitaria", numLibretaUniversitaria);
	        Estudiante estudiante = query.getSingleResult();
	        //se podria mejorar con el toString
	        if (estudiante != null) {
	            System.out.println("Estudiante encontrado:");
	            System.out.println("Numero de Libreta: " + estudiante.getNumLibretaUniversitaria());
	            System.out.println("Nombre: " + estudiante.getNombre());
	            System.out.println("Apellido: " + estudiante.getApellido());
	        } 
	        
	        else {
	            System.out.println("Estudiante no encontrado para el numero de libreta: " + numLibretaUniversitaria);
	        }
	this.em.getTransaction().commit();
	
	return estudiante;
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
		
		return estudiante;
	}
	

}
