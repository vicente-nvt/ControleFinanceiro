package infra.converters;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;

import domain.business.IGrouperRepository;
import domain.business.IJsonConverter;
import domain.business.IRepository;
import domain.model.Commitment;
import domain.model.Grouper;
import domain.model.Movement;
import domain.model.Type;
import infra.utilitary.DateTransformation;
import infra.utilitary.JsonArrayCreator;

public class CommitmentJsonConverter implements IJsonConverter<Commitment> {

	private IRepository<Grouper> grouperRepository;
	
	public CommitmentJsonConverter(IGrouperRepository grouperRepository) 
	{
		this.grouperRepository = grouperRepository;
	}

	@Override
	public Commitment jsonToDomain(JSONArray jsonArray) 
	{		
		JSONObject jsonCommitment = (JSONObject) jsonArray.getJSONObject(0).get("commitment");
		
		int id = jsonCommitment.getInt("id");
		String description = jsonCommitment.getString("description");
		Movement movement = Movement.valueOf(jsonCommitment.getString("movement"));
		int plot = jsonCommitment.getInt("plot");
		int totalPlots = jsonCommitment.getInt("totalPlots");
		Type type = Type.valueOf(jsonCommitment.getString("type"));
		BigDecimal effectiveValue = jsonCommitment.getBigDecimal("effectiveValue");
		BigDecimal expectedValue = jsonCommitment.getBigDecimal("expectedValue");
		Grouper grouper = grouperRepository.findById(jsonCommitment.getInt("grouper"));
		Date expiryDate;
		try {
			expiryDate = DateTransformation.StringToDate(jsonCommitment.getString("expiryDate"));
		} catch (Exception e) {
			expiryDate = Calendar.getInstance().getTime();
		}
		
		return new Commitment(id, description, movement, type, plot, totalPlots, 
				effectiveValue, expectedValue, grouper, expiryDate);
	}

	@Override
	public JSONArray domainToJson(Commitment commitment) 
	{
		JSONObject jsonCommitment = new JSONObject();		
		jsonCommitment.put("id", commitment.getId());		
		jsonCommitment.put("description", commitment.getDescription());
		jsonCommitment.put("movement", commitment.getMovement().toString());
		jsonCommitment.put("type", commitment.getType().toString());
		jsonCommitment.put("plot", commitment.getPlot());
		jsonCommitment.put("totalPlots", commitment.getTotalPlots());
		jsonCommitment.put("effectiveValue", commitment.getEffectiveValue());
		jsonCommitment.put("expectedValue", commitment.getExpectedValue());
		jsonCommitment.put("expiryDate", DateTransformation.DateToString(commitment.getExpiryDate()));
		jsonCommitment.put("grouper", commitment.getGrouper().getId());
		
		return  JsonArrayCreator.Create("commitment", jsonCommitment);
	}
}