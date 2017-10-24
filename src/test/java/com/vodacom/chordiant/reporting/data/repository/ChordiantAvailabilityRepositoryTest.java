package com.vodacom.chordiant.reporting.data.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.vodacom.chordiant.reporting.test.BaseTest;

public class ChordiantAvailabilityRepositoryTest extends BaseTest {

	@Autowired
	ChordiantAvailabilityRepository repository;

	@Test
	public void testDelete() {
		
		repository.delete(50L);

	}

	public void testSave() {

	}

}
