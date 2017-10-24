package com.vodacom.chordiant.reporting.data.repository;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.vodacom.chordiant.reporting.data.mapping.TransactionCountStatisticalDataDailyEntity;
import com.vodacom.chordiant.reporting.test.BaseTest;

public class TransactionCountDailyStatsRepositoryTest extends BaseTest {

	@Autowired
	TransactionCountDailyStatsRepository transactionCountDailyStatsRepository;

	@Test
	public void testSave() {

		Assert.assertNotNull(transactionCountDailyStatsRepository);
		//transactionCountDailyStatsRepository
				//.save(new TransactionCountStatisticalDataDailyEntity(
						//"2015-02-07", "8980083", "53.85", "0.77", "J4U"));
		// scheduler.scheduleJ4UDailyStatsUpdate();

	}

}
