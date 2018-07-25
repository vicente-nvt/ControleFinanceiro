package domain.business;

import org.json.JSONArray;

import domain.model.Grouper;
import infra.converters.IGrouperRepository;
import infra.converters.IJsonConverter;

public class AgrupadorBO 
{
	private IGrouperRepository repository; 	
	private IJsonConverter<Grouper> converter;	
	
	private AgrupadorBO(IJsonConverter<Grouper> conversor,
			IGrouperRepository repositorio)
	{
		this.converter = conversor;
		this.repository = repositorio;
	}
	
	public void add(JSONArray jsonArray)
	{
		Grouper grouper = converter.jsonToDomain(jsonArray);								
		repository.save(grouper);
	}

	public void edit(JSONArray jsonArray) 
	{		
		Grouper grouper = converter.jsonToDomain(jsonArray);								
		repository.edit(grouper);						
	}

	public void remove(int id) 
	{			
		repository.delete(repository.findById(id));
	}
}