package infra.converters;

import org.json.JSONArray;

public interface IJsonConverter<T> {
	
	T jsonToDomain(JSONArray jsonArray);
	JSONArray domainToJson (T t);
	
}
