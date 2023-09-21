package repositories;

import javax.persistence.EntityManager;

public class InscripcionRepositoryImpl implements InscripcionRepository {
	private EntityManager em;
	
	public InscripcionRepositoryImpl(EntityManager em) {
		this.em = em;
	}
	
	

	@Override
	public void altaEstudianteCarrera() {
		
		
	}

}
