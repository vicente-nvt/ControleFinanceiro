package infra.utilitary;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonArrayCreator {
	
	public static JSONArray Create(String header, JSONObject jsonObject){
		
		JSONObject jsonHeader = new JSONObject();
		jsonHeader.put(header, jsonObject);
		
		return new JSONArray().put(jsonHeader);
	}
}
