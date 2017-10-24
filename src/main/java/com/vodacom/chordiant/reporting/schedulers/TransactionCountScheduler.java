package com.vodacom.chordiant.reporting.schedulers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;

import com.vodacom.chordiant.reporting.mvc.view.TransactionCountView;
import com.vodacom.chordiant.reporting.service.ReportingService;
import com.vodacom.chordiant.reporting.service.impl.DefaultEmailService;

//@Component
public class TransactionCountScheduler {
	
	@Autowired
	ReportingService service;
	
	@Autowired
	DefaultEmailService emailService;
	
	@Value("${email.transactions.recipients}")
	String transactionsStatsRecipients;
	
	
	@Value("${email.transactions.subject}")
	String transactionsStatsSubject;
	
	
	@Value("${email.transactions.template}")
	String transactionsStatsTemplate;
	
	//@Scheduled(cron = "0 0 10 * * *")
	public void scheduleDailyStatsUpdate() throws ParseException {
		System.out.println("running scheduleDailyStatsUpdate:" + new Date());
		
		
		//Perform the J4U update first		
		List<TransactionCountView> views = 	service.getTransactionCountStatisticsForPeriod(getYesterdaysDate(), getYesterdaysDate(), "daily", "00:00:00", "23:59:59", "DecisionController");
		
		for(TransactionCountView view: views) {
			service.saveDailyView(view);
		}
		

		//ccr stats now
		List<TransactionCountView> ccrViews = 	service.getTransactionCountStatisticsForPeriod(getYesterdaysDate(), getYesterdaysDate(), "daily", "00:00:00", "23:59:59", "external");
		
		for(TransactionCountView view: ccrViews) {
			service.saveDailyView(view);
		}
		
		//assessment stats now
		List<TransactionCountView> assessmentViews = 	service.getTransactionCountStatisticsForPeriod(getYesterdaysDate(), getYesterdaysDate(), "daily", "00:00:00", "23:59:59", "assessment");
				
		for(TransactionCountView view: assessmentViews) {
			service.saveDailyView(view);
		}
		
		System.out.println("ended scheduleDailyStatsUpdate:" + new Date());
		
		
		
	}
	
	//@Scheduled(cron = "0 0 10 * * *")
	public void scheduleHourlyStatsUpdate() throws ParseException {
		System.out.println("running scheduleHourlyStatsUpdate:" + new Date());
		
		
		List<TransactionCountView> views = 	service.getTransactionCountStatisticsForPeriod(getYesterdaysDate(), getYesterdaysDate(), "hourly", "00:00:00", "23:59:59", "DecisionController");
		
		for(TransactionCountView view: views) {
			
			String[] array = view.getInterval().split(" ");
			
			view.setRequestDate(getDate(array[0]));
			view.setRequestTime(array[1]);
			
			service.saveHourlyView(view);
		}
	
		
		List<TransactionCountView> ccrViews = 	service.getTransactionCountStatisticsForPeriod(getYesterdaysDate(), getYesterdaysDate(), "hourly", "00:00:00", "23:59:59", "external");
		
		for(TransactionCountView view: ccrViews) {
			
			String[] array = view.getInterval().split(" ");
			
			view.setRequestDate(getDate(array[0]));
			view.setRequestTime(array[1]);
			
			
			service.saveHourlyView(view);
			
		}

		List<TransactionCountView> assessmentViews = 	service.getTransactionCountStatisticsForPeriod(getYesterdaysDate(), getYesterdaysDate(), "hourly", "00:00:00", "23:59:59", "assessment");
		
		for(TransactionCountView view: assessmentViews) {
			
			String[] array = view.getInterval().split(" ");
			
			view.setRequestDate(getDate(array[0]));
			view.setRequestTime(array[1]);
			
			
			service.saveHourlyView(view);
			
		}		
		
		
		
		System.out.println("ended scheduleHourlyStatsUpdate:" + new Date());
	}
	
	//@Scheduled(cron = "0 0 10 * * *")
	public void schedule30MinStatsUpdate() throws ParseException {
		System.out.println("running schedule30MinStatsUpdate:" + new Date());

		List<TransactionCountView> views = 	service.getTransactionCountStatisticsForPeriod(getYesterdaysDate(), getYesterdaysDate(), "halfHour", "00:00:00", "23:59:59", "DecisionController");
		
		for(TransactionCountView view: views) {
			
			String[] array = view.getInterval().split(" ");
			
			view.setRequestDate(getDate(array[0]));
			view.setRequestTime(array[1]);
			
			service.saveHalfHourlyView(view);
		}
	
		List<TransactionCountView> ccrViews = 	service.getTransactionCountStatisticsForPeriod(getYesterdaysDate(), getYesterdaysDate(), "halfHour", "00:00:00", "23:59:59", "external");
		
		for(TransactionCountView view: ccrViews) {
			
			String[] array = view.getInterval().split(" ");
			
			view.setRequestDate(getDate(array[0]));
			view.setRequestTime(array[1]);
			
			service.saveHalfHourlyView(view);
			
		}
		
		List<TransactionCountView> assessmentViews = 	service.getTransactionCountStatisticsForPeriod(getYesterdaysDate(), getYesterdaysDate(), "halfHour", "00:00:00", "23:59:59", "assessment");
		
		for(TransactionCountView view: assessmentViews) {
			
			String[] array = view.getInterval().split(" ");
			
			view.setRequestDate(getDate(array[0]));
			view.setRequestTime(array[1]);
			
			service.saveHalfHourlyView(view);
			
		}		
		
		System.out.println("ended schedule30MinStatsUpdate:" + new Date());
		
		
		
	}
	
	//@Scheduled(cron = "0 0 10 * * *")
	public void scheduleTpsUpdate() throws ParseException {
		System.out.println("running scheduleTpsUpdate:" + new Date());
		
	
		//Perform the J4U update first		
		List<TransactionCountView> views = 	service.getTransactionCountStatisticsForPeriod(getYesterdaysDate(), getYesterdaysDate(), "tps", "00:00:00", "23:59:59", "DecisionController");
		
		for(TransactionCountView view: views) {
			
			String[] array = view.getInterval().split(" ");
			
			view.setRequestDate(getDate(array[0]));
			view.setRequestTime(array[1]);
			
			service.saveTpsView(view, "J4U");
		}		
		
		
		//Perform external now	
		List<TransactionCountView> ccrViews = 	service.getTransactionCountStatisticsForPeriod(getYesterdaysDate(), getYesterdaysDate(), "tps", "00:00:00", "23:59:59", "external");
				
		for(TransactionCountView view: ccrViews) {
					
			String[] array = view.getInterval().split(" ");
					
			view.setRequestDate(getDate(array[0]));
			view.setRequestTime(array[1]);
					
			service.saveTpsView(view, "CCR-external");
		}
		
		
		
		//Perform assessment now	
		List<TransactionCountView> assessmentViews = 	service.getTransactionCountStatisticsForPeriod(getYesterdaysDate(), getYesterdaysDate(), "tps", "00:00:00", "23:59:59", "assessment");
						
		for(TransactionCountView view: assessmentViews) {
							
			String[] array = view.getInterval().split(" ");
							
			view.setRequestDate(getDate(array[0]));
			view.setRequestTime(array[1]);
							
			service.saveTpsView(view, "CCR-assessment");
		}
		
		System.out.println("ended scheduleTpsUpdate:" + new Date());
		
	}
	
	//@Scheduled(cron = "0 0 11 * * *")
	public void sendDailyUpdate () throws MessagingException {
		
		Context ctx = new Context();
		
		List<TransactionCountView> views = service.getStatsForPeriod(getYesterdaysDate(), getYesterdaysDate());
		
		ctx.setVariable("dateReported", getYesterdaysDate());
		ctx.setVariable("transactionViews", views);
		
		emailService.sendEmail(transactionsStatsRecipients, transactionsStatsSubject, transactionsStatsTemplate, ctx);
		
		
	}
	
	
	public void scheduleMonthlyUpdate() {
		
		
		
		
	}

	private String getYesterdaysDate() {
		
		DateTime dateTime = new DateTime();
		
		
		dateTime = dateTime.minusDays(1);
		
		Date date = dateTime.toDate();
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		return format.format(date);
	}
	
	private String getDate(String unformattedValue) throws ParseException {

		DateFormat fromFormat = new SimpleDateFormat("yyyyMMdd");

		DateFormat toFormat = new SimpleDateFormat("yyyy-MM-dd");

		return toFormat.format(fromFormat.parse(unformattedValue));
	}
	
	
	
}
