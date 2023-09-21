package factory;

import javax.persistence.Persistence;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

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
