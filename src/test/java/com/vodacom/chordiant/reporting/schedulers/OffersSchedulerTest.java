package com.vodacom.chordiant.reporting.schedulers;

import javax.mail.MessagingException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.vodacom.chordiant.reporting.test.BaseTest;

public class OffersSchedulerTest  extends BaseTest {
	
	@Autowired
	OffersScheduler scheduler;
	
	@Test
	public void testTakeSnapShot() throws MessagingException {
		scheduler.takeOffersSnapshot();
	}

}
