package com.vodacom.chordiant.reporting.service;

import javax.mail.MessagingException;

import org.thymeleaf.context.Context;

public interface EmailService {
	
	public void sendEmail(String recipients,String subject, String template, Context ctx) throws MessagingException ;

}
