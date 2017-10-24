package com.vodacom.chordiant.reporting.service;

import javax.mail.MessagingException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.IWebContext;
import org.thymeleaf.spring4.context.SpringWebContext;

import com.vodacom.chordiant.reporting.ReportingMainApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ReportingMainApplication.class)
public class DefaultEmailServiceTest {
	
	@Autowired
	EmailService emailService;
	
	//final Context ctx = new Context();
	//ctx.setVariable("name", recipientName);
	//ctx.setVariable("subscriptionDate", new Date());
	//ctx.setVariable("hobbies", Arrays.asList("Cinema", "Sports", "Music"));
	//ctx.setVariable("imageResourceName", imageResourceName); // so that we can reference it from HTML
	
	
	@Value("${email.offerstatus.recipients}")
	String offerStatusRecipients;
	
	
	@Value("${email.offerstatus.subject}")
	String offerStatusSubject;
	
	
	@Value("${email.offerstatus.template}")
	String offerStatusTemplate;
	 
	@Test
	public void testSend() throws MessagingException  {
		
		Context ctx = new Context();
		
		emailService.sendEmail(offerStatusRecipients, offerStatusSubject, offerStatusTemplate, ctx);
		
	}
	
	

}
