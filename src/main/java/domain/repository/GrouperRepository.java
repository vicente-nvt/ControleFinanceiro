package domain.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import domain.business.IGrouperRepository;
import domain.model.Grouper;

public class GrouperRepository implements IGrouperRepository{
	
	private EntityManager manager;
	private EntityTransaction transaction;
	
	public GrouperRepository (EntityManager manager){
		this.manager = manager;
		transaction = manager.getTransaction();
	}

	public void add(Grouper agrupador) {				
		transaction.begin();
		manager.persist(agrupador);
		transaction.commit();
	}

	public void delete(Grouper agrupador) {		
		transaction.begin();
		manager.remove(manager.getReference(agrupador.getClass(),agrupador.getId()));
		transaction.commit();
	}
	
	public void edit(Grouper agrupador) {
		transaction.begin();
		manager.merge(agrupador);
		transaction.commit();
	}
	
	public Grouper findById(int id) {
		transaction.begin();
		Grouper agrupadorEncontrado = manager.find(Grouper.class, id);
		transaction.rollback();
	
		return agrupadorEncontrado;
	}
}