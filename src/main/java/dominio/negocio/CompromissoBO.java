package dominio.negocio;

import org.json.JSONArray;

import dominio.modelo.Compromisso;
import infra.conversores.IConversorJSON;
import infra.repositorio.IRepository;

public class CompromissoBO {

	
	private IConversorJSON<Compromisso> conversor;
	private IRepository<Compromisso> repositorio;
	
	public CompromissoBO(IConversorJSON<Compromisso> conversor, 
			IRepository<Compromisso> repositorio) 
	{
		this.conversor = conversor;
		this.repositorio = repositorio;
	}

	public void inserir(JSONArray jsonArray) 
	{	
		Compromisso compromisso = conversor.jsonToDomain(jsonArray);
		repositorio.save(compromisso);		
	}

	public void editar(JSONArray jsonArray) {
		Compromisso compromisso = conversor.jsonToDomain(jsonArray);
		repositorio.edit(compromisso);
	}

	public void remover(Compromisso compromisso) {
		repositorio.delete(compromisso);
	}
	
	public Compromisso findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
