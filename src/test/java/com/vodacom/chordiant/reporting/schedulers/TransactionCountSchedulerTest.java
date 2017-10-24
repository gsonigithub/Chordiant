package com.vodacom.chordiant.reporting.schedulers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.mail.MessagingException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.vodacom.chordiant.reporting.test.BaseTest;

public class TransactionCountSchedulerTest extends BaseTest {
	
	@Autowired
	TransactionCountScheduler scheduler;
	
	@Test
	public void testSendDailyUpdate () throws MessagingException {
		scheduler.sendDailyUpdate();
	}
	
	@Test
	public void testScheduleDailyUpdate () throws ParseException {
		scheduler.scheduleDailyStatsUpdate();
		
	}
	
	@Test
	public void testScheduleHourlyStatsUpdate () throws ParseException {
		scheduler.scheduleHourlyStatsUpdate();
	}
	
	@Test
	public void testSchedule30MinStatsUpdate () throws ParseException {
		scheduler.schedule30MinStatsUpdate();
	}

	
	@Test
	public void testScheduleTpsStatsUpdate () throws ParseException {
		scheduler.scheduleTpsUpdate();
	}
	
	@Test
	public void testGetDate () throws ParseException {
		
		String unformattedValue = "20150209";
		
		System.out.println("formatted date:" + getDate(unformattedValue));
		
	}
	
	
	
	private String getDate(String unformattedValue) throws ParseException {

		DateFormat fromFormat = new SimpleDateFormat("yyyyMMdd");
		
		Date date = fromFormat.parse(unformattedValue);

		DateFormat toFormat = new SimpleDateFormat("yyyy-MM-dd");

		return toFormat.format(date);
	}

}
