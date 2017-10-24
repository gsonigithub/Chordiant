package com.vodacom.chordiant.reporting.data.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.vodacom.chordiant.reporting.ReportingMainApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ReportingMainApplication.class)
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class WeblogicFindingRepositoryTest {
	
	@Autowired
	ChordiantAvailabilityRepository repository;
	
	@Test
	public void testNull() {
		
		Assert.assertNotNull(repository);
		
	}

}
