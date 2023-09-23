package repositories;

import java.util.List;

import javax.persistence.EntityManager;

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

	//hacer en interfaz
	public Carrera carreraPorId(int id) {
		//this.em.getTransaction().begin();
		
		Carrera c = em.find(Carrera.class, id);
		//this.em.getTransaction().commit();
		//em.close();
		return c;
	}

}
