package infra.converters;

import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;

import domain.business.IJsonConverter;
import domain.model.Grouper;
import infra.utilitary.DateTransformation;

public class GrouperJsonConverter implements IJsonConverter<Grouper> 
{
	@Override
	public Grouper jsonToDomain(JSONArray jsonArray) 
	{					
		JSONObject grouperJson = (JSONObject) jsonArray.getJSONObject(0).get("grouper");
		
		Date date;
		try {
			date = DateTransformation.StringToDate(grouperJson.getString("expiryDate"));
		}
		catch (Exception e) { 
			date = null; 
		}
		
		return new Grouper(grouperJson.getString("description"), date);
	}
	
	@Override
	public JSONArray domainToJson(Grouper grouper) 
	{
		JSONObject grouperJson = new JSONObject();
		JSONObject grouperHeader = new JSONObject();
		
		grouperJson.put("description", grouper.getDescription());
		grouperJson.put("expiryDate", DateTransformation.DateToString(grouper.getExpiryDate()));
		grouperHeader.put("grouper", grouperJson);
				
		return new JSONArray().put(grouperHeader);
	}
}