package com.vodacom.chordiant.reporting.service.impl;

import javax.mail.Address;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.TemplateResolver;
import org.thymeleaf.util.ArrayUtils;

import com.vodacom.chordiant.reporting.service.EmailService;

@Component
public class DefaultEmailService implements EmailService {

	@Autowired
	JavaMailSender mailSender;
	
	/**
	 * DB connection details
	 */
	@Value("${email.from}")
	String fromEmailAdd;

	@Autowired
	private TemplateEngine templateEngine;

	public void sendEmail(String recipients,String subject, String template, Context ctx)
			throws MessagingException {

		// Prepare message using a Spring helper
		final MimeMessage mimeMessage = this.mailSender.createMimeMessage();

		final MimeMessageHelper message = new MimeMessageHelper(mimeMessage,
				true, "UTF-8"); // true = multipart
		message.setSubject(subject);
		message.setFrom(fromEmailAdd);

		// Create the HTML body using Thymeleaf
		final String htmlContent = this.templateEngine.process(template,
				ctx);
		message.setText(htmlContent, true); // true = isHtml

		// Add the inline image, referenced from the HTML code as
		// "cid:${imageResourceName}"
		// final InputStreamSource imageSource = new
		// ByteArrayResource(imageBytes);
		// message.addInline(imageResourceName, imageSource, imageContentType);
		
		//mimeMessage.setRecipients(RecipientType.TO, getAddresses(recipients));
		mimeMessage.setRecipients(RecipientType.TO, recipients);

		// Send mail
		this.mailSender.send(mimeMessage);

	}

	private Address[] getAddresses(String[] recipients) throws AddressException {
		Assert.notNull(recipients, "Recipients cannot be null or empty");
		
		Address[] addresses = new Address[recipients.length];
		
		for(int i=0;i<recipients.length;i++){
			addresses[i] = new InternetAddress(recipients[i]);
		}
		
		return addresses;
	}

}
