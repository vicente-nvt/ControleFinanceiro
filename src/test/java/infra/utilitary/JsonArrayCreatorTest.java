package infra.utilitary;

import static org.junit.Assert.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

public class JsonArrayCreatorTest {

	@Test
	public void shouldCreateJsonArrayProperly(){
		
		String headerDescription = "expected";
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("test", "testing");
		JSONObject jsonArrayHeader = new JSONObject();
		jsonArrayHeader.put(headerDescription, jsonObject);		
		JSONArray jsonArrayExpected = new JSONArray();
		jsonArrayExpected.put(jsonArrayHeader);
		
		JSONArray jsonArrayCreated = JsonArrayCreator.Create(headerDescription, jsonObject);
		
		assertEquals(jsonArrayExpected.toString(), jsonArrayCreated.toString());
	}
}