package infra.utilitarios;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTransformation {
	
	public static Date StringToDate(String data) throws ParseException{
		Calendar dt = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		dt.setTime(sdf.parse(data));
		return dt.getTime();			
	}
	
	public static String DateToString(Date data){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");			
		return sdf.format(data);		
	}
}
