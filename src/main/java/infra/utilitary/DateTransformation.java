package infra.utilitary;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTransformation {
	
	public static Date StringToDate(String date) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar dt = Calendar.getInstance();
		dt.setTime(sdf.parse(date));
		
		return dt.getTime();			
	}
	
	public static String DateToString(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		return sdf.format(date);		
	}
}
