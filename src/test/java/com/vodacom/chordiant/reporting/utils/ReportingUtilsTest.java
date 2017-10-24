package com.vodacom.chordiant.reporting.utils;

import java.text.ParseException;
import java.util.Date;

import org.junit.Test;

import com.vodacom.chordiant.reporting.test.BaseTest;

public class ReportingUtilsTest extends BaseTest{
	
	@Test
	public void testConvertDate() throws ParseException {
		
		Date date = ReportingUtils.convertToDateTime("2015-03-26");
		System.out.println("date");
		
	}

}
