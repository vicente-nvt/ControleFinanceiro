package infra.repositorio;

import javax.persistence.EntityManager;

import dominio.modelo.Compromisso;

public class CompromissoRepository implements IRepository<Compromisso>{

	private EntityManager manager;
		
	public CompromissoRepository (EntityManager manager){
		this.manager = manager;
	}

	public void save(Compromisso compromisso) {		
		manager.getTransaction().begin();
		manager.persist(compromisso);
		manager.getTransaction().commit();
	}

	public void delete(Compromisso compromisso) {
		manager.getTransaction().begin();
		manager.remove(compromisso);
		manager.getTransaction().commit();			
	}

	public Compromisso findById(int codigo) {
		manager.getTransaction().begin();
		Compromisso compromisso = manager.find(Compromisso.class, codigo);
		manager.getTransaction().rollback();
		
		return compromisso;
	}

	public void edit(Compromisso compromisso) {
		manager.getTransaction().begin();
		manager.merge(compromisso);
		manager.getTransaction().commit();
	}	
}