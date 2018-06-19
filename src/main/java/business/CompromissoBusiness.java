package business;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.json.JSONObject;

import model.Agrupador;
import model.Compromisso;
import repository.Repository;
import repository.CompromissoRepository;

public class CompromissoBusiness {

	public String mensagens;
	private EntityManager manager;
	private CompromissoRepository repository;		
	
	public CompromissoBusiness(EntityManager manager){
		this.manager = manager;
		repository = new CompromissoRepository(manager);
	}
	
	public boolean lancarCompromisso(JSONObject objetoJson) {
		try {		
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean removerLancamento(Compromisso compromisso) {

		try {			
			return true;
		} catch (Exception e) {						
			return false;
		}		
	}

}
