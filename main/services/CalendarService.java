package main.services;
import java.util.*;
import java.lang.*;

public class CalendarService{
	private static final Map<Integer,Integer> CenturyCode = new HashMap<>();
	private static final Map<String,Integer> MonthCode = new HashMap<>();
	private static final Map<Integer,String>  WeekDays = new HashMap<>();
	
	static{
					//Initializing Century code, month code and day codes
			String[] Days = {"Saturday","Sunday","Monday","Tuesday","Wednesday","Thursday","Friday"};
		
			CenturyCode.put(1600, 6);
			CenturyCode.put(1700, 4);
			CenturyCode.put(1800, 2);
			CenturyCode.put(1900, 0);
			CenturyCode.put(2000, 6);
			CenturyCode.put(2100, 4);
			CenturyCode.put(2200, 2);
			CenturyCode.put(2300, 0);
	
	
			MonthCode.put("01", 1);
			MonthCode.put("02", 4);
			MonthCode.put("03", 4);
			MonthCode.put("04", 0);
			MonthCode.put("05", 2);
			MonthCode.put("06", 5);
			MonthCode.put("07", 0);
			MonthCode.put("08", 3);
			MonthCode.put("09", 6);
			MonthCode.put("10", 1);
			MonthCode.put("11", 4);
			MonthCode.put("12", 6);
	
			for(int i = 0; i<7; i++)
			{
				WeekDays.put(i,Days[i]);
			}
	}
	
	
	public static String dateToDay(String date)
	{
		String[] dateParts = date.split("/");
		
		//Splits the given date into day,month and year
		int day = Integer.parseInt(dateParts[0]);
		String month = dateParts[1];
		int year = Integer.parseInt(dateParts[2]);
		int lastTwoDigitsOfcentury = year%100;
		int century = year - lastTwoDigitsOfcentury;
		int centuryCode = 0;
		int monthCode = 0;
		StringBuilder Day = new StringBuilder();
		if(CenturyCode.containsKey(century))
		{
			centuryCode = CenturyCode.get(century);
		}
		//  Handles leapyear exceptions
		if(MonthCode.containsKey(month))
		{
			monthCode = MonthCode.get(month);
			if(IsLeapYear(year) == "yes")
			{
				if ( month.equals("01") || month.equals("02") )
					monthCode = MonthCode.get(month) - 1;
			}
			
			
		}
		// Implements formula for predicting day from a given date
		int remainder = lastTwoDigitsOfcentury/4;
		
		int sum = day + monthCode + centuryCode + lastTwoDigitsOfcentury + remainder;
		
		int result = sum % 7;
		
		if(WeekDays.containsKey(result))
		{
			Day.append(WeekDays.get(result));
		}
		return Day.toString().trim();
	}
		
	// Checking whether the given year is leap year or not
	public static String IsLeapYear(int Year)
	{
		if(Year%4 == 0)
		{
			if(Year%100 ==0)
			{
				if(Year%400 == 0)
				{
					return "yes";
				}
				else {
					return "no";
				}
			}
			else {
				return "yes";
			}
		}
		else {
			return "no";
		}
	}
	
	//determines the next 3 same calender years
	public static int SameCalendar(int Year) 
	{
		int Value = 0;
		if(IsLeapYear(Year) == "yes")
			Value = Year + 28;
		if(IsLeapYear(Year - 1) == "yes")
			Value = Year + 6;
		if(IsLeapYear(Year - 2) == "yes")
			Value = Year + 11;
		if(IsLeapYear(Year - 3) == "yes")
			Value = Year + 11;
		return Value;
	}
		
}
		
		