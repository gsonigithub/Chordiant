package com.vodacom.chordiant.reporting.data.repository;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.vodacom.chordiant.reporting.data.mapping.TransactionCountStatisticalDataHalfHourEntity;
import com.vodacom.chordiant.reporting.test.BaseTest;

public class TransactionCountHalfHourlyStatsRepositoryTest extends BaseTest {

	@Autowired
	TransactionCountHalfHourlyStatsRepository repository;

	@Test
	public void testUpdateRequestDateAndTime() throws ParseException {

		
		  List<TransactionCountStatisticalDataHalfHourEntity> entities =  repository.findAll();
		  
		  for(TransactionCountStatisticalDataHalfHourEntity entity:entities) {
			  
			  String[] array = entity.getInterval().split(" ");
			  
			  entity.setRequestDate(getDate(array[0]));
			  entity.setRequestTime(array[1]);
			  
			  System.out.println("saving for date[" + array[0] +  " and time [" + array[1]);
			  
			  repository.saveAndFlush(entity);
		  
		  }
		 

	}

	private String getDate(String unformattedValue) throws ParseException {

		DateFormat fromFormat = new SimpleDateFormat("yyyyMMdd");

		DateFormat toFormat = new SimpleDateFormat("yyyy-MM-dd");

		return toFormat.format(fromFormat.parse(unformattedValue));
	}

}
