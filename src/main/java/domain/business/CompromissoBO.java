package domain.business;

import org.json.JSONArray;

import domain.model.Commitment;
import infra.converters.IJsonConverter;

public class CompromissoBO 
{	
	private IJsonConverter<Commitment> converter;
	private ICommitmentRepository repository;
	
	public CompromissoBO(IJsonConverter<Commitment> converter, 
			ICommitmentRepository repository) 
	{
		this.converter = converter;
		this.repository = repository;
	}

	public void add(JSONArray jsonArray) 
	{	
		Commitment commitment = converter.jsonToDomain(jsonArray);
		repository.save(commitment);		
	}

	public void edit(JSONArray jsonArray) {
		Commitment commitment = converter.jsonToDomain(jsonArray);
		repository.edit(commitment);
	}

	public void remove(Commitment commitment) 
	{
		repository.delete(commitment);
	}
}
