package factory;

import javax.persistence.Persistence;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

//LE DA CONEXION A LA BASE DE DATOS.
public class FactoryEntityManager {
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("TP2-ArqWeb");
	private static EntityManager em;
	
	private FactoryEntityManager () {
		
	}

	public static EntityManager getInstance() {
		if ((em == null) || (!em.isOpen())){
			em = emf.createEntityManager();
		}
		return em;
	}
}
