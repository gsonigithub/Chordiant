package com.vodacom.chordiant.reporting.schedulers;

import java.net.MalformedURLException;

import javax.mail.MessagingException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.vodacom.chordiant.reporting.test.BaseTest;

public class HealthCardSchedulerTest extends BaseTest{
	
	@Autowired
	HealthCardScheduler scheduler;
	
	@Test
	public void testSendOutHealthCard() throws MessagingException, MalformedURLException {
		scheduler.sendOutHealthCard();
	}

}
