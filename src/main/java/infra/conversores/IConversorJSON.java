package infra.conversores;

import org.json.JSONArray;

public interface IConversorJSON<T> {
	
	T jsonToDomain(JSONArray jsonArray);
	JSONArray domainToJson (T t);
	
}
