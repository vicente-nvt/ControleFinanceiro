package infra.converters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import domain.builders.GrouperBuilder;
import domain.model.Grouper;
import infra.utilitary.JsonArrayCreator;

public class GrouperJsonConverterTest {

	JSONObject jsonGrouper;
	Grouper grouper;
	GrouperJsonConverter converter;
	private JSONArray jsonArray;
	
	@Before
	public void Setup()
	{				
		converter = new GrouperJsonConverter();
		
		jsonGrouper = new JSONObject();
		jsonGrouper.put("description", "Test");
		jsonGrouper.put("expiryDate", "10/03/2018");	
				
		jsonArray = JsonArrayCreator.Create("grouper", jsonGrouper);
		
		Calendar date = Calendar.getInstance();
		date.set(2018, 2, 10);		
		
		grouper = GrouperBuilder.newGrouper()
				.withDescription("Test")
				.withExpiryDate(date.getTime())
				.build();
	}
	
	@Test
	public void shouldTransformFromJsontToGrouper() 
	{		
		Grouper convertedGrouper = converter.jsonToDomain(jsonArray);
		
		assertTrue(convertedGrouper.equals(this.grouper));		
	}
	
	@Test
	public void shouldTransformFromGrouperToJson()
	{					
		JSONArray convertedGrouperJson = converter.domainToJson(grouper);
				
		assertEquals(convertedGrouperJson.toString(), jsonArray.toString());
	}	
}