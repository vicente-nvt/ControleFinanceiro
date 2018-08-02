package infra.repository;

import javax.persistence.EntityManager;

import domain.business.IRepository;
import domain.model.Commitment;

public class CommitmentRepository implements IRepository<Commitment>{

	private EntityManager manager;
		
	public CommitmentRepository (EntityManager manager){
		this.manager = manager;
	}

	public void add(Commitment commitment) {		
		manager.getTransaction().begin();
		manager.persist(commitment);
		manager.getTransaction().commit();
	}

	public void delete(Commitment commitment) {
		manager.getTransaction().begin();
		manager.remove(commitment);
		manager.getTransaction().commit();			
	}

	public Commitment findById(int id) {
		manager.getTransaction().begin();
		Commitment commitment = manager.find(Commitment.class, id);
		manager.getTransaction().rollback();
		
		return commitment;
	}

	public void edit(Commitment commitment) {
		manager.getTransaction().begin();
		manager.merge(commitment);
		manager.getTransaction().commit();
	}	
}