package infra.converters;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;

import java.math.BigDecimal;
import java.text.ParseException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import domain.builders.CommitmentBuilder;
import domain.builders.GrouperBuilder;
import domain.model.Commitment;
import domain.model.Grouper;
import domain.model.Movement;
import domain.model.Type;
import domain.repository.GrouperRepository;
import infra.utilitary.DateTransformation;
import infra.utilitary.JsonArrayCreator;

public class CommitmentJsonConverterTest {
	
	private GrouperRepository grouperRepository;
	private Grouper grouper;
	private Commitment commitment;
	private JSONArray jsonArray;

	@Before
	public void Setup() throws ParseException {
		grouper = GrouperBuilder.newGrouper()
				.withId(9)
				.withDescription("Test")
				.withExpiryDate(DateTransformation.StringToDate("10/03/2018"))
				.build();
		grouperRepository = Mockito.mock(GrouperRepository.class);
		Mockito.when(grouperRepository.findById(anyInt())).thenReturn(grouper);
		
		commitment = CommitmentBuilder.newCommitment()
				.withId(99)
				.withDescription("Test")
				.withMovement(Movement.INPUT)
				.withType(Type.FIXED)
				.withPlot(1)
				.withTotalPlots(10)
				.withExpectedValue(new BigDecimal(10.00))
				.withEffectiveValue(new BigDecimal(10.00))
				.withExpiryDate(DateTransformation.StringToDate("10/03/2018"))
				.withGrouper(grouper)
				.build();
		
		JSONObject jsonCommitment = new JSONObject();
		jsonCommitment.put("id", 99);
		jsonCommitment.put("description", "Test");
		jsonCommitment.put("movement", "INPUT");
		jsonCommitment.put("type", "FIXED");
		jsonCommitment.put("plot", 1);
		jsonCommitment.put("totalPlots", 10);
		jsonCommitment.put("expectedValue", 10.00);
		jsonCommitment.put("effectiveValue", 10.00);
		jsonCommitment.put("expiryDate", "10/03/2018");
		jsonCommitment.put("grouper", 9);
				
		jsonArray = JsonArrayCreator.Create("commitment", jsonCommitment);		
	}
	
	@Test
	public void shouldConvertFromJsonToCommitment(){		
		Commitment convertedCommitment = new CommitmentJsonConverter(grouperRepository)
				.jsonToDomain(jsonArray);
		
		assertTrue(commitment.equals(convertedCommitment));
	}
	
	@Test
	public void shouldConvertFromCommitmentToJson(){

		JSONArray convertedJsonArray = new CommitmentJsonConverter(grouperRepository)
				.domainToJson(commitment);
		
		assertEquals(jsonArray.toString(), convertedJsonArray.toString());
	}
}
