package infra.repositorio;

import javax.persistence.EntityManager;

import dominio.modelo.Agrupador;

public class AgrupadorRepository implements IRepository<Agrupador>{
	
	private EntityManager manager;
	
	public AgrupadorRepository (EntityManager manager){
		this.manager = manager;
	}

	public void save(Agrupador agrupador) {		
		manager.getTransaction().begin();
		manager.persist(agrupador);
		manager.getTransaction().commit();
	}

	public void delete(Agrupador agrupador) {
		manager.getTransaction().begin();
		manager.remove(manager.getReference(agrupador.getClass(),agrupador.getCodigo()));
		manager.getTransaction().commit();
	}
	
	public void edit(Agrupador agrupador) {
		manager.getTransaction().begin();
		manager.merge(agrupador);
		manager.getTransaction().commit();
	}
	
	public Agrupador findById(int id) {
		manager.getTransaction().begin();
		Agrupador agrupadorEncontrado = manager.find(Agrupador.class, id);
		manager.getTransaction().rollback();
	
		return agrupadorEncontrado;
	}
}