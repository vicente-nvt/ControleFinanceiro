package utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Transform {

	
	public static Date StringToDate(String data) throws Exception {

		try {
			Calendar dt = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			dt.setTime(sdf.parse(data));
			
			return dt.getTime();
			
		} catch (Exception e) {
			throw new Exception("Falha ao converter data (String -> Date): " + e.getMessage());
		}
		
	}
	
	public static String DateToString(Date data) throws Exception{
		
		try {			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			
			return sdf.format(data);		
		} catch (Exception e) {			
			throw new Exception("Falha ao converter data (Date -> String): " + e.getMessage());			
		}
		
	}
	
	
	
}
