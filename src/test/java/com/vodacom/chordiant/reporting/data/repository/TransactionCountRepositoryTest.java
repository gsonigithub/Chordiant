package com.vodacom.chordiant.reporting.data.repository;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.vodacom.chordiant.reporting.ReportingMainApplication;
import com.vodacom.chordiant.reporting.mvc.view.MDMCountView;
import com.vodacom.chordiant.reporting.mvc.view.TransactionCountView;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ReportingMainApplication.class)
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class TransactionCountRepositoryTest {


	
	@Autowired
	DefaultDataRepository dataRepository;
	
	@Test
	public void testDaily() {
		List<TransactionCountView> countViews = dataRepository.getTransactionCountStatisticsForPeriod("2014-10-21", "2014-11-10", "daily", "00:00", "00:00", "external");
		
		System.out.println("count:" + countViews);
	}
	
	@Test
	public void testHourly() {
		List<TransactionCountView> countViews = dataRepository.getTransactionCountStatisticsForPeriod("2014-11-26", "2014-11-28", "oneHour", "00:00", "00:00", "external");
		
		System.out.println("count:" + countViews);
	}
	
	

}
