package infra.converters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import domain.model.Grouper;
import infra.converters.GrouperToJsonConverter;

public class GrouperToJsonConverterTest {

	JSONObject grouperJson;
	JSONObject grouperHeaderJson;
	JSONArray arrayJson;
	Grouper grouper;
	GrouperToJsonConverter converter;
	
	@Before
	public void criarObjetoJson()
	{		
		grouperJson = new JSONObject();
		grouperHeaderJson = new JSONObject();
		arrayJson = new JSONArray();
		converter = new GrouperToJsonConverter();
		
		grouperJson.put("description", "Test");
		grouperJson.put("expiryDate", "10/03/2018");	
		grouperHeaderJson.put("grouper", grouperJson);
		
		arrayJson.put(grouperHeaderJson);
		
		Calendar data = Calendar.getInstance();
		data.set(2018, 2, 10);

		grouper = new Grouper();
		grouper.setDescription("Test");
		grouper.setExpiryDate(data.getTime());
	}
	
	@Test
	public void deveTransformarJsonEmAgrupador() 
	{		
		Grouper grouper = converter.jsonToDomain(arrayJson);
		
		assertTrue(grouper.equals(this.grouper));		
	}
	
	@Test
	public void deveTransformarAgrupadorEmJson()
	{			
		String expectedDescription = "Test";
		String expectedExpiryDate = "10/03/2018";
		
		JSONObject grouperJson = (JSONObject) converter.domainToJson(grouper)
				.getJSONObject(0)
				.get("grouper");
				
		assertEquals(expectedDescription , grouperJson.get("description"));
		assertEquals(expectedExpiryDate, grouperJson.get("expiryDate"));
	}	
}