package business;

import javax.persistence.Persistence;

import org.json.JSONArray;

import model.Agrupador;
import repository.Repository;

public class AgrupadorBusiness {
	
	private Repository repository; 
	private String dataBaseString = "ControleFinanceiro";
	
	public Agrupador lancarAgrupador(JSONArray arrayAgrupador) {

		Agrupador agrupador;
		try {
			agrupador = new AgrupadorTranslator().transformarJsonEmAgrupador(arrayAgrupador);
			
			repository = criarRepository();
			
			return repository.save(agrupador);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}						
	}

	public Agrupador editarAgrupador(JSONArray arrayAgrupador) {
		
		Agrupador agrupadorEditado;
		try {
			agrupadorEditado = new AgrupadorTranslator().transformarJsonEmAgrupador(arrayAgrupador);
			
			repository = criarRepository();
			
			return repository.edit(agrupadorEditado);			
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	public boolean removerAgrupador(int codigo) {
		
		try {
			repository = criarRepository();
			return repository.delete(repository.findById(codigo));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	private Repository criarRepository() {
		return new Repository(Persistence.createEntityManagerFactory(dataBaseString).createEntityManager());
	}

}
