package infra.converters;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Calendar;

import org.json.JSONArray;
import org.junit.Test;
import org.mockito.Mock;

import domain.business.IGrouperRepository;
import domain.model.Commitment;
import domain.model.Grouper;
import domain.model.Movement;
import domain.model.Type;
import infra.repository.GrouperRepository;

public class CommitmentToJsonConverterTest {

	@Mock
	GrouperRepository grouperRepository;
	
	@Test
	public void shouldConvertFromJsonToCommitment(){				
		Commitment expectedCommitment = new Commitment("Teste", Movement.ENTRADA,
				1, Type.FIXED, 10, new BigDecimal(10.0), new BigDecimal(10.00), 
				new Grouper(), Calendar.getInstance().getTime());		
		JSONArray jsonArray = null;
		grouperRepository = Mock(GrouperRepository.class);
		
		Commitment convertedCommitment = new CommitmentToJsonConverter(grouperRepository).jsonToDomain(jsonArray);
		
		assertTrue(expectedCommitment.equals(convertedCommitment));
	}
	
	@Test
	public void shouldConvertFromCommitmentToJson(){
		
		JSONArray expectedJsonArray = new JSONArray();
		
		assertEquals(expectedJsonArray, convertedJsonArray);
	}
}
