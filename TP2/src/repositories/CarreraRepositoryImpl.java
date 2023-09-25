package repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

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
	public List<DtoReporte> Reporte() {
		 this.em.getTransaction().begin();
		 
		    Query query = em.createQuery(
		    	   " SELECT NEW dto.DtoReporte(c.nombre, i.anioInscripcion, COALESCE(i.inscriptos, 0), COALESCE(e.egresados, 0)) \r\n" +
		    	   " FROM Carrera c " +
		    	   " LEFT JOIN Inscripcion i ON c.id_carrera = i.carrera " +
		    	   " LEFT JOIN Estudiante e ON i.estudiante = e.numLibretaUniversitaria " +
		    	   " ORDER BY c.nombre ",
		    	    DtoReporte.class);
   
		    Query query1 = em.createQuery(
		    		"SELECT NEW dto.DtoReporte(c.nombreCarrera, a.anioInscripcion, COALESCE(i.inscriptos, 0), COALESCE(e.egresados, 0))\r\n"
		    		+ "FROM Carrera c, Anio a\r\n"
		    		+ "LEFT JOIN (SELECT fk_carrera, anioInscripcion AS anio, COUNT(*) AS inscriptos FROM Inscripcion\r\n"
		    		+ "           GROUP BY fk_carrera, anioInscripcion) i\r\n"
		    		+ "           ON c.id_carrera = i.fk_carrera AND a.anio = i.anio\r\n"
		    		+ "LEFT JOIN (SELECT fk_carrera, anioGraduacion AS anio, COUNT(*) AS egresados FROM Inscripcion\r\n"
		    		+ "           WHERE anioGraduacion IS NOT NULL GROUP BY fk_carrera, anioGraduacion) e\r\n"
		    		+ "           ON c.id_carrera = e.fk_carrera AND a.anio = e.anio\r\n"
		    		+ "ORDER BY c.nombre ASC, a.anio ASC",
			    	    DtoReporte.class);
		    
		    Query query2= em.createQuery(
		    		"SELECT NEW dto.DtoReporte(c.nombreCarrera, a.anioInscripcion, COALESCE(i.inscriptos, 0), COALESCE(e.egresados, 0))\\r\\n" 
		    		+ "FROM \r\n"
		    		+ "    carrera AS Carrera\r\n"
		    		+ "LEFT JOIN (\r\n"
		    		+ "    SELECT \r\n"
		    		+ "        id_carrera,\r\n"
		    		+ "        anioInscripcion AS anio,\r\n"
		    		+ "        COUNT(*) AS inscriptos\r\n"
		    		+ "    FROM \r\n"
		    		+ "        inscripcion\r\n"
		    		+ "    GROUP BY \r\n"
		    		+ "        fk_carrera,\r\n"
		    		+ "        anioInscripcion\r\n"
		    		+ ") AS InscripcionesPorAnio\r\n"
		    		+ "ON \r\n"
		    		+ "    Carrera.id_carrera = InscripcionesPorAnio.fk_carrera\r\n"
		    		+ "LEFT JOIN (\r\n"
		    		+ "    SELECT \r\n"
		    		+ "        fk_carrera,\r\n"
		    		+ "        anioGraduacion AS anio,\r\n"
		    		+ "        COUNT(*) AS egresados\r\n"
		    		+ "    FROM \r\n"
		    		+ "        inscripcion\r\n"
		    		+ "    WHERE \r\n"
		    		+ "        anioGraduacion IS NOT NULL\r\n"
		    		+ "    GROUP BY \r\n"
		    		+ "        fk_carrera,\r\n"
		    		+ "        anioGraduacion\r\n"
		    		+ ") AS EgresadosPorAnio\r\n"
		    		+ "ON \r\n"
		    		+ "    Carrera.id_carrera = EgresadosPorAnio.fk_carrera\r\n"
		    		+ "RIGHT JOIN (\r\n"
		    		+ "    SELECT DISTINCT \r\n"
		    		+ "        anioInscripcion AS anio\r\n"
		    		+ "    FROM \r\n"
		    		+ "        inscripcion\r\n"
		    		+ "    UNION \r\n"
		    		+ "    SELECT DISTINCT \r\n"
		    		+ "        anioGraduacion AS anio\r\n"
		    		+ "    FROM \r\n"
		    		+ "        inscripcion\r\n"
		    		+ "    WHERE \r\n"
		    		+ "        anioGraduacion IS NOT NULL\r\n"
		    		+ ") AS Anio\r\n"
		    		+ "ON \r\n"
		    		+ "    1=1\r\n"
		    		+ "ORDER BY \r\n"
		    		+ "    NombreCarrera ASC,\r\n"
		    		+ "    Anio ASC;",
			    	    DtoReporte.class);
		    
			List<DtoReporte> CarreraDTO = query.getResultList();
		    em.getTransaction().commit();
		    em.close();
		    
			return CarreraDTO;
	}
//////////////////////////////////////
	
	
	//hacer en interfaz
	public Carrera carreraPorId(int id) {
		//this.em.getTransaction().begin();
		
		Carrera c = em.find(Carrera.class, id);
		//this.em.getTransaction().commit();
		//em.close();
		return c;
	}

}
