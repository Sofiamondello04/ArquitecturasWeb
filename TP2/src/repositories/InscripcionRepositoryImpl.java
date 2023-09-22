package repositories;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import entities.Carrera;
import entities.Estudiante;
import entities.Inscripcion;


public class InscripcionRepositoryImpl implements InscripcionRepository {
	
	private EntityManager em;
	
	public InscripcionRepositoryImpl(EntityManager em) {
		this.em = em;
	}
	
	
	@Override
	public void matricularEstudiante(Inscripcion i) {
		try {
			this.em.getTransaction().begin();
			if(!em.contains(i)) {
				em.persist(i);
			} 
			else {
				em.merge(i);		
			}
			this.em.getTransaction().commit();
			
		}
		catch (Exception ex) {
			System.out.println("La inscripcion ya existe");
		}
	}


	public List<Carrera> carrerasConInscriptos() {
		//this.em.getTransaction().begin();
		@SuppressWarnings("unchecked")
		List<Carrera> carrerasConInscriptosPorCantInsc = em.createQuery("SELECT c.nombre, COUNT(i.id_inscripcion) AS cantidadInscritos\r\n"
				+ "FROM Carrera c\r\n"
				+ "LEFT JOIN c.inscripciones i\r\n"
				+ "GROUP BY c.idCarrera, c.nombre\r\n"
				+ "ORDER BY cantidadInscritos DESC").getResultList(); 
		//em.close();
		return carrerasConInscriptosPorCantInsc;
	}

}
