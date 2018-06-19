package repository;

import javax.persistence.EntityManager;
import model.IPersistent;

public class Repository {
	
	private EntityManager manager;
	
	public Repository (EntityManager manager){
		this.manager = manager;
	}

	public IPersistent save(IPersistent persistent) throws Exception {
		
		try {
			manager.getTransaction().begin();
			manager.persist(persistent);
			manager.getTransaction().commit();
			
			return persistent;
		}
		catch (Exception e){			
			throw new Exception ("Falha ao persistir " + persistent.getClass().toString() +  ": " + e.getMessage());		
		}				
	}

	public boolean delete(IPersistent persistent) throws Exception {

		try {
			manager.getTransaction().begin();
			manager.remove(manager.getReference(persistent.getClass(), persistent.getCodigo()));
			manager.getTransaction().commit();
			
			return true;
		} 
		catch(Exception e){
			throw new Exception ("Falha ao deletar " + persistent.getClass().toString() +  ": " + e.getMessage());			
		}		
	}

	public IPersistent findById(IPersistent persistent) throws Exception {

		try {
			manager.getTransaction().begin();
			IPersistent persistentEncontrado = manager.find(persistent.getClass(), persistent.getCodigo());
			manager.getTransaction().rollback();
		
			return persistentEncontrado;
		} catch (Exception e) {
			throw new Exception ("Falha ao pesquisar " + persistent.getClass().toString() +  " por ID: " + e.getMessage());			
		}
	
	}

	public IPersistent edit(IPersistent persistent) throws Exception {
		try {
			manager.getTransaction().begin();
			IPersistent persisted = manager.merge(persistent);
			manager.getTransaction().commit();
		
			return persisted;
		} catch (Exception e){
			throw new Exception ("Falha ao editar " + persistent.getClass().toString() + ": " + e.getMessage());			
		}
	}
}
