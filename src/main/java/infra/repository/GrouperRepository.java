package infra.repository;

import javax.persistence.EntityManager;

import domain.business.IGrouperRepository;
import domain.model.Grouper;

public class GrouperRepository implements IGrouperRepository{
	
	private EntityManager manager;
	
	public GrouperRepository (EntityManager manager){
		this.manager = manager;
	}

	public void add(Grouper agrupador) {		
		manager.getTransaction().begin();
		manager.persist(agrupador);
		manager.getTransaction().commit();
	}

	public void delete(Grouper agrupador) {
		manager.getTransaction().begin();
		manager.remove(manager.getReference(agrupador.getClass(),agrupador.getId()));
		manager.getTransaction().commit();
	}
	
	public void edit(Grouper agrupador) {
		manager.getTransaction().begin();
		manager.merge(agrupador);
		manager.getTransaction().commit();
	}
	
	public Grouper findById(int id) {
		manager.getTransaction().begin();
		Grouper agrupadorEncontrado = manager.find(Grouper.class, id);
		manager.getTransaction().rollback();
	
		return agrupadorEncontrado;
	}
}