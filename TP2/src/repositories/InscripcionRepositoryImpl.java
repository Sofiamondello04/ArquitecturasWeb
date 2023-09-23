package repositories;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import dto.DtoCantidadInscriptosPorCarrera;
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
			System.out.println("El estudiante fue matriculado con exito en la carrera");

			
		}
		catch (Exception ex) {
			System.out.println("La inscripcion ya existe");
		}
	}


	public List<DtoCantidadInscriptosPorCarrera> carrerasConInscriptos() {
		    this.em.getTransaction().begin();
		    Query query = em.createQuery("SELECT NEW dto.DtoCantidadInscriptosPorCarrera(c.nombre, COUNT(i))\r\n"
		            + "FROM Carrera c\r\n"
		            + "LEFT JOIN c.inscriptos i\r\n"
		            + "GROUP BY c.nombre\r\n"
		            + "ORDER BY COUNT(i) DESC", DtoCantidadInscriptosPorCarrera.class);

		    List<DtoCantidadInscriptosPorCarrera> results = query.getResultList();
		    em.getTransaction().commit();
		    return results;
		}
	}

