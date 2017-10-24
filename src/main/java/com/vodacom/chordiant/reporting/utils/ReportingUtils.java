package com.vodacom.chordiant.reporting.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;

public class ReportingUtils {
	
	public static String getTodaysDate() {		
		return getDateMinus(0);
	}
	
	public static String getYesterdaysDate() {		
		return getDateMinus(1);
	}
	
	private static String getDateMinus(int daysToSubstract) {
		
		DateTime dateTime = new DateTime();
		
		
		dateTime = dateTime.minusDays(daysToSubstract);
		
		Date date = dateTime.toDate();
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		return format.format(date);
	}

	public static String getDateMinusDays(int days) {
		return getDateMinus(days);
	}
	
	public static Date convertToDateTime(String date) throws ParseException {
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		Date dateObj = format.parse(date);
		
		return dateObj;
		
	}

}
