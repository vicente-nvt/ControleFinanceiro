package infra.converters;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;

import domain.business.IRepository;
import domain.model.Commitment;
import domain.model.Grouper;
import domain.model.Movement;
import domain.model.Type;
import infra.utilitarios.DateTransformation;

public class CommitmentToJsonConverter implements IJsonConverter<Commitment> {

	private IRepository<Grouper> grouperRepository;
	
	public CommitmentToJsonConverter(IGrouperRepository grouperRepository) 
	{
		this.grouperRepository = grouperRepository;
	}

	@Override
	public Commitment jsonToDomain(JSONArray jsonArray) 
	{		
		JSONObject jsonCommitment = (JSONObject) jsonArray.getJSONObject(0).get("commitment");
		
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
		
		return new Commitment(description, movement, plot, type, totalPlots, 
				effectiveValue, expectedValue, grouper, expiryDate);
	}

	@Override
	public JSONArray domainToJson(Commitment commitment) 
	{
		return null;
	}
}