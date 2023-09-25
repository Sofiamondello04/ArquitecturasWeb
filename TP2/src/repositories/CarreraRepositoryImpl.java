package repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import dto.DtoCantidadInscriptosPorCarrera;
import dto.DtoEstudiante;
import dto.DtoReporte;
import entities.Carrera;
import entities.Estudiante;

public class CarreraRepositoryImpl implements CarreraRepository{
	
	private EntityManager em;
	
	public CarreraRepositoryImpl(EntityManager em) {
		this.em = em;
	}

	
	@Override
	public void insertarCarrera(Carrera c) {
		try {
			this.em.getTransaction().begin();
			if(!em.contains(c)) {
				em.persist(c);
			} 
			else {
				em.merge(c);		
			}
			this.em.getTransaction().commit();
			
		}
		catch (Exception ex) {
			System.out.println("La carrera ya existe");
		}
	}
	
//////////////////////////////////////NO SE PUDO CREAR LA QUERY ADECUADA PARA RESOLVER LA CONSIGNA.
	public List<DtoReporte> reporte() {
		this.em.getTransaction().begin();
		Query query = em.createQuery(" SELECT NEW dto.DtoReporte(\r\n"
				+ "    c.nombre,\r\n"
				+ "    i.anioInscripcion,\r\n"
				+ "    COUNT(DISTINCT CASE WHEN i.anioInscripcion IS NULL THEN e.dni ELSE NULL END) AS inscriptos,\r\n"
				+ "    COUNT(DISTINCT CASE WHEN i.anioGraduacion IS NOT NULL THEN e.dni ELSE NULL END) AS egresados\r\n"
				+ ")\r\n"
				+ "FROM Carrera c\r\n"
				+ "LEFT JOIN c.inscriptos i\r\n"
				+ "LEFT JOIN i.estudiante e\r\n"
				+ "GROUP BY c.nombre, i.anioInscripcion\r\n"
				+ "ORDER BY c.nombre ASC, i.anioInscripcion ASC", DtoReporte.class);
		System.out.println("Reporte de todas las carreras indicando año, inscriptos y egresados");
		List<DtoReporte> results = query.getResultList();
	    em.getTransaction().commit();
		return results;		 
	}
//////////////////////////////////////
	
	
	//hacer en interfaz
	public Carrera carreraPorId(int id) {

		if (!em.getTransaction().isActive()) {
		    em.getTransaction().begin();
		}
		
		Carrera c = em.find(Carrera.class, id);
		//this.em.getTransaction().commit();
		//em.close();
		return c;
	}

}
