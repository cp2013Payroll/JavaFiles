package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static boolean IsInPayPeriod(Date theDate, Date startDate, Date endDate) {
		return (theDate.after(startDate) && theDate.before(endDate));
	}
	
	public static Date todaysDate(){
		Date d = new Date();
		return d;
	}
	
	public static Date newDate(String dateString) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
		Date date = null;
		
		try {
			date = sdf.parse(dateString);
			System.out.println(date); //Tue Aug 31 10:20:56 SGT 1982
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
		
		
		
	}

}
