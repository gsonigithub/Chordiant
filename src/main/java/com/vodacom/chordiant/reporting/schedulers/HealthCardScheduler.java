package com.vodacom.chordiant.reporting.schedulers;

import java.net.MalformedURLException;
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
public class HealthCardScheduler {
	
	@Autowired
	ReportingService service;

	@Autowired
	DefaultEmailService emailService;

	@Value("${email.healthcard.recipients}")
	String healthCardRecipients;

	@Value("${email.healthcard.subject}")
	String healthCardSubject;

	@Value("${email.healthcard.template}")
	String healthCardTemplate;
	
	@Value("${weblogic.serverList}")
	String serverListAsString;
	
	//@Scheduled(cron = "0 15 */4 * * *")
	//@Scheduled(cron = "0 5 * * * *")
	@Scheduled(cron = "0 10 * * * *")
	public void sendOutHealthCard() throws MessagingException, MalformedURLException {
		
		System.out.println("sendOutHealthCard");
		
		Date now = new Date();

		Context ctx = new Context();

		ctx.setVariable("dateReported", now);
		
		//DB health
		ctx.setVariable("standbyDBViews", service.getCarDataAvailability("standby"));
		ctx.setVariable("interimDBViews", service.getCarDataAvailability("interim"));
		System.out.println("DB Available");
		//Offer logic deployments
		ctx.setVariable("deploymentViews", service.getOfferLogicDeployments());
		System.out.println("Logic Deployments");
		//Materialised Views
		//ctx.setVariable("reportingViews", service.getViewDetails("NBA_REPORTS_MAIN_MVIEW_ALL"));
		ctx.setVariable("rechargeAndGetViews", service.getViewDetails("CUST_REMAINING_EFFORT_"));
		//ctx.setVariable("rechargeAndGetCount", service.getRechargeAndGetCount(4));
		
		//J4UWebServices		
		//ctx.setVariable("pcho001zatcrhWebServices", service.getWebServiceStatus("pcho001zatcrh.vodacom.corp"));
		//ctx.setVariable("pcho002zafsrhWebServices", service.getWebServiceStatus("pcho002zafsrh.vodacom.corp"));
		//ctx.setVariable("pcho003zatcrhWebServices", service.getWebServiceStatus("pcho003zatcrh.vodacom.corp"));
		//ctx.setVariable("pcho004zafsrhWebServices", service.getWebServiceStatus("pcho004zafsrh.vodacom.corp"));

		//Offer count for last hour
		ctx.setVariable("offerViews", service.getOffersForLastHour());
		
		//Port monitors
		ctx.setVariable("portStatusViews", service.getPortStatus(serverListAsString) );
		
		emailService.sendEmail(healthCardRecipients, healthCardSubject,
				healthCardTemplate, ctx);

	}

}
