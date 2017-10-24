package com.vodacom.chordiant.reporting.schedulers;

import java.util.Date;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;

import com.vodacom.chordiant.reporting.service.ReportingService;
import com.vodacom.chordiant.reporting.service.impl.DefaultEmailService;

@Component
public class HourlyHealthCardScheduler {
	
	@Autowired
	ReportingService service;

	@Autowired
	DefaultEmailService emailService;

	@Value("${email.hourlyhealthcard.recipients}")
	String healthCardRecipients;

	@Value("${email.hourlyhealthcard.subject}")
	String healthCardSubject;

	@Value("${email.hourlyhealthcard.template}")
	String healthCardTemplate;
	
	//@Scheduled(cron = "0 0 * * * *")
	public void hourlyHealthCheck() throws MessagingException{
		
		Date now = new Date();

		Context ctx = new Context();
		
		System.out.println("sendOutHourlyHealthCard");
		ctx.setVariable("dateReported", now);
		
		//Hourly KPI Count ACCESS_EVENT
		ctx.setVariable("accessEventDBViews", service.getKPIHourlyCount("ACCESS_EVENT"));
		System.out.println("Access Event Done");
		//Hourly KPI Count EVENT
		ctx.setVariable("eventDBViews", service.getKPIHourlyCount("EVENT"));
		System.out.println("Event Done");
		//Max TPS USSD
		ctx.setVariable("tpsUSSDViews", service.getTPSHourlyCount("USSD"));
		System.out.println("USSD TPS");
		//Max TPS PPFE
		ctx.setVariable("tpsPPFEViews", service.getTPSHourlyCount("PPFE"));
		System.out.println("PPFE TPS");
		//Last 15 min offers count
		ctx.setVariable("offerCountView", service.getOfferCount15min());
		System.out.println("Offer Count");
		//Max DB Count per 10 mins
		ctx.setVariable("offerDBCountView", service.getOfferDBCount());
		
		//send email
		emailService.sendEmail(healthCardRecipients, healthCardSubject,
				healthCardTemplate, ctx);
		System.out.println("HourlyHealthCard Sent");
	}
	
}
