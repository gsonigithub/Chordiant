package com.vodacom.chordiant.reporting.schedulers;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;

public class DatabaseTableCountsScheduler {
	
	@Scheduled(cron = "0 */10 * * * *")
	public void takeOffersSnapshot() {
		System.out.println("Cron running at:" + new Date());
	}

}
