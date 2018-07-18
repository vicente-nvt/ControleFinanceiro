package dominio.negocio;

import org.json.JSONArray;

import dominio.modelo.Agrupador;
import infra.conversores.IConversorJSON;
import infra.repositorio.AgrupadorRepository;
import infra.repositorio.IRepository;

public class AgrupadorBO 
{
	private AgrupadorRepository repository; 	
	private IConversorJSON<Agrupador> conversor;
	private IRepository<Agrupador> repositorio;
	
	private AgrupadorBO(IConversorJSON<Agrupador> conversor,
			IRepository<Agrupador> repositorio)
	{
		this.conversor = conversor;
		this.repositorio = repositorio;
	}
	
	public void inserir(JSONArray arrayAgrupador)
	{
		Agrupador agrupador = conversor.jsonToDomain(arrayAgrupador);								
		repositorio.save(agrupador);
	}

	public void editar(JSONArray arrayAgrupador) 
	{		
		Agrupador agrupadorEditado = conversor.jsonToDomain(arrayAgrupador);								
		repositorio.edit(agrupadorEditado);						
	}

	public void remover(int codigo) 
	{			
		repositorio.delete(repository.findById(codigo));
	}
}