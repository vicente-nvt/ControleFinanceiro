package repository;

import javax.persistence.EntityManager;

import model.Compromisso;

public class CompromissoRepository {

	private EntityManager manager;
	
	public CompromissoRepository (EntityManager manager){
		this.manager = manager;
	}

	public boolean save(Compromisso compromisso) {		
		try {
			manager.getTransaction().begin();
			manager.persist(compromisso);
			manager.getTransaction().commit();
			
			return true;
		} catch (Exception e) {
			System.out.println("Erro ao persistir Compromisso: " + e.getMessage());
			return false;
		}
	}

	public boolean delete(Compromisso compromisso) {
		try {
			manager.getTransaction().begin();
			manager.remove(compromisso);
			manager.getTransaction().commit();
			
			return true;
		} catch (Exception e) {
			System.out.println("Erro ao deletar Compromisso: " + e.getMessage());
			return false;
		}
	}

	public Compromisso findById(int codigo) {
		try {
			manager.getTransaction().begin();
			Compromisso compromisso = manager.find(Compromisso.class, codigo);
			manager.getTransaction().rollback();
			return compromisso;
		} catch (Exception e) {
			System.out.println("Falha ao pesquisar compromisso por Id: " + e.getMessage());
			return null;
		}
	}

	public boolean edit(Compromisso compromisso) {

		try {
			manager.getTransaction().begin();
			manager.merge(compromisso);
			manager.getTransaction().commit();
			
			return true;
		} catch (Exception e) {
			System.out.println("Falha ao editar compromisso: " + e.getMessage());
			return false;
		}
	}
	
	
}
