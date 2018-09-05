package infra.utilitary;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class DateTransformationTest {	
	
	@Test
	public void shouldConvertFromStringToDate() throws ParseException{
		
		String dateToConvert = "10/03/2018";
		Calendar expectedDate = Calendar.getInstance();
		expectedDate.clear();
		expectedDate.set(2018, 2, 10);		
		
		Date convertedDate = DateTransformation.StringToDate(dateToConvert);
		
		assertEquals(expectedDate.getTime(), convertedDate);
	}
	
	@Test
	public void shouldConvertFromDateToString(){
		
		Calendar dateToConvert = Calendar.getInstance();
		dateToConvert.clear();
		dateToConvert.set(2018, 02, 10);
		String expectedStringDate = "10/03/2018";
		
		String convertedStringDate = DateTransformation.DateToString(dateToConvert.getTime());
		
		assertEquals(expectedStringDate, convertedStringDate);
	}
}
