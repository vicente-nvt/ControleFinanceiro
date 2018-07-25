package infra.repositorio;

import javax.persistence.EntityManager;

import domain.business.IRepository;
import domain.model.Commitment;

public class CompromissoRepository implements IRepository<Commitment>{

	private EntityManager manager;
		
	public CompromissoRepository (EntityManager manager){
		this.manager = manager;
	}

	public void save(Commitment compromisso) {		
		manager.getTransaction().begin();
		manager.persist(compromisso);
		manager.getTransaction().commit();
	}

	public void delete(Commitment compromisso) {
		manager.getTransaction().begin();
		manager.remove(compromisso);
		manager.getTransaction().commit();			
	}

	public Commitment findById(int codigo) {
		manager.getTransaction().begin();
		Commitment compromisso = manager.find(Commitment.class, codigo);
		manager.getTransaction().rollback();
		
		return compromisso;
	}

	public void edit(Commitment compromisso) {
		manager.getTransaction().begin();
		manager.merge(compromisso);
		manager.getTransaction().commit();
	}	
}