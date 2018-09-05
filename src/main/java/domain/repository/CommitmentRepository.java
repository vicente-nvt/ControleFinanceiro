package domain.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import domain.business.ICommitmentRepository;
import domain.model.Commitment;

public class CommitmentRepository implements ICommitmentRepository{

	private EntityManager manager;
	private EntityTransaction transaction;
		
	public CommitmentRepository (EntityManager manager){
		this.manager = manager;
		transaction = manager.getTransaction();
	}

	public void add(Commitment commitment) {		
		transaction.begin();
		manager.persist(commitment);
		transaction.commit();
	}

	public void delete(Commitment commitment) {
		transaction.begin();
		manager.remove(commitment);
		transaction.commit();			
	}

	public Commitment findById(int id) {
		transaction.begin();
		Commitment commitment = manager.find(Commitment.class, id);
		transaction.rollback();
		
		return commitment;
	}

	public void edit(Commitment commitment) {
		transaction.begin();
		manager.merge(commitment);
		transaction.commit();
	}	
}