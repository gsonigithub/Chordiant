package com.vodacom.chordiant.reporting.schedulers;

import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.util.StringUtils;

import com.vodacom.chordiant.reporting.mvc.view.TPSCountView;
import com.vodacom.chordiant.reporting.mvc.view.TransactionCountReportingCriteriaView;
import com.vodacom.chordiant.reporting.service.ReportingService;
import com.vodacom.chordiant.reporting.service.impl.DefaultEmailService;

@Component
public class TransactionGraphScheduler {
	
	@Autowired
	ReportingService service;

	@Autowired
	DefaultEmailService emailService;

	@Value("${email.TransactionGraphs.recipients}")
	String tranGraphRecipients;

	@Value("${email.TransactionGraphs.subject}")
	String tranGraphSubject;

	@Value("${email.TransactionGraphs.template}")
	String tranGraphTemplate;
	
	//@Scheduled(cron = "0 */5 * * * *")
	public void hourlyHealthCheck() throws MessagingException{
		
		Date now = new Date();

		Context ctx = new Context();
		
		ctx.setVariable("dateReported", now);
		System.out.println("Transaction Graphs");
		TransactionCountReportingCriteriaView criteriaView = new TransactionCountReportingCriteriaView();
		
		
		List<TPSCountView> ccrViews = null;
		List<TPSCountView> ppfeViews = null;
		List<TPSCountView> ussdViews = null;

		
		/*if (!StringUtils.isEmpty(criteriaView.getStartDate())
				&& !StringUtils.isEmpty(criteriaView.getEndDate())) {			
			
			ccrViews = service.getTPSStats(criteriaView,"CCR");
			ppfeViews = service.getTPSStats(criteriaView,"PPFE");
			ussdViews = service.getTPSStats(criteriaView,"USSD");		
		}
		ctx.setVariable("dateReported", now);
		ctx.setVariable("ccrViews", ccrViews);
		ctx.setVariable("ppfeViews", ppfeViews);
		ctx.setVariable("ussdViews", ussdViews);		
		*/
		emailService.sendEmail(tranGraphRecipients, tranGraphSubject,
				tranGraphTemplate, ctx);	
		System.out.println("Sent");
		
	}
}
