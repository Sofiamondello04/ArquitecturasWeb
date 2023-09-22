package repositories;

import java.sql.SQLException;
import java.util.List;

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
	public void altaEstudiante(Estudiante e) throws java.sql.SQLIntegrityConstraintViolationException {
		
		this.em.getTransaction().begin();
		if(!em.contains(e)) {
			em.persist(e);
		} 
		else {
			em.merge(e);
		}
		this.em.getTransaction().commit();
		em.close();
	
	}

	@Override
	public List<Estudiante> listaEstudianteOdenadoPorNombre() throws SQLException {
		this.em.getTransaction().begin();	
		String jpql = "SELECT e FROM Estudiante e ORDER BY e.apellido DESC";
		TypedQuery<Estudiante> query = em.createQuery(jpql, Estudiante.class);
		List<Estudiante> estudiantes = query.getResultList();
		for (Estudiante estudiante : estudiantes) {
		    System.out.println(estudiante.getApellido() + ", " + estudiante.getNombre());
		}
		this.em.getTransaction().commit();
		em.close();
		return estudiantes;
	}

	@Override
	public Estudiante estudiantePorLibreta(int numLibretaUniversitaria) throws SQLException{
		this.em.getTransaction().begin();	
		String jpql = "SELECT e FROM Estudiante e ORDER BY e.apellido DESC";
		TypedQuery<Estudiante> query = em.createQuery(jpql, Estudiante.class);
		List<Estudiante> estudiantes = query.getResultList();
		for (Estudiante estudiante : estudiantes) {
		    System.out.println(estudiante.getApellido() + ", " + estudiante.getNombre());
		}
		this.em.getTransaction().commit();
		em.close();
		return estudiantes;
	}

	@Override
	public List<Estudiante> listaEstudiantePorGenero(String genero) {
		// TODO Auto-generated method stub
		return null;
	}
//poner en interface
	public Estudiante estudiantePorDni(int dni) {
		//this.em.getTransaction().begin();
		
		Estudiante e = em.find(Estudiante.class, dni);
		//this.em.getTransaction().commit();
		//em.close();
		return e;
	}
	

}
