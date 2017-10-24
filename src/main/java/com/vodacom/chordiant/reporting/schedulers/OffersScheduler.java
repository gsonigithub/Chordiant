package com.vodacom.chordiant.reporting.schedulers;

import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;

import com.vodacom.chordiant.reporting.mvc.view.OfferView;
import com.vodacom.chordiant.reporting.mvc.view.OffersSelectionCriteria;
import com.vodacom.chordiant.reporting.service.ReportingService;
import com.vodacom.chordiant.reporting.service.impl.DefaultEmailService;
import com.vodacom.chordiant.reporting.utils.ReportingUtils;

//@Component
public class OffersScheduler {

	@Autowired
	ReportingService service;

	@Autowired
	DefaultEmailService emailService;

	@Value("${email.offers.recipients}")
	String offersStatsRecipients;

	@Value("${email.offers.subject}")
	String offersStatsSubject;

	@Value("${email.offers.template}")
	String offersStatsTemplate;

	//@Scheduled(cron = "0 0 */1 * * *")
	public void takeOffersSnapshot() throws MessagingException {

		System.out.println("extracting offers statistics..");

		Date now = new Date();

		List<OfferView> offers = service.getOffersForLastHour();

		if (offers != null && offers.size() > 0) {
			service.saveOffers(offers, now);
			System.out.println("offers saved for:" + now);
		}

	}

	private void sendJ4UOfferStatsEmail(List<OfferView> offers, Date now) throws MessagingException {
		System.out.println("sending email");

		Context ctx = new Context();

		ctx.setVariable("dateReported", now);
		ctx.setVariable("offerViews", offers);

		emailService.sendEmail(offersStatsRecipients, offersStatsSubject,
				offersStatsTemplate, ctx);

	}
	
	//@Scheduled(cron = "0 30 0 * * *")
	public void sendOffersDailyReport() throws MessagingException {

		System.out.println("extracting daily offers statistics..");

		Date now = new Date();

		List<OfferView> offers = service.getOffers(new OffersSelectionCriteria(ReportingUtils.getYesterdaysDate()));

		sendJ4UOfferStatsEmail(offers, now);

	}

}
