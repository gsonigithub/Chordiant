package com.vodacom.chordiant.reporting.data.repository;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.vodacom.chordiant.reporting.data.mapping.J4UOfferStatsEntity;
import com.vodacom.chordiant.reporting.test.BaseTest;

public class J4UOfferStatsRepositoryTest extends BaseTest{
	
	@Autowired
	J4UOfferStatsRepository repository;
	
	@Test
	public void testSave() {
		J4UOfferStatsEntity entity = new J4UOfferStatsEntity();
		
		entity.setDateCreated(new Date());
		entity.setFulfilmentInd("Y");
		entity.setNumRecords(1234560798);
		entity.setOfferExistsInd("Offer Exists");
		entity.setResponse("ACCEPTED");
		entity.setUsername("PPFE");
		
		repository.save(entity);
	
		
	}
	
	@Test
	public void testFindAll() {
		
		List<J4UOfferStatsEntity> offfers = repository.findAll();
		
		System.out.println(offfers);

	
		
	}

}
