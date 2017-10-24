package com.vodacom.chordiant.reporting.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

import com.vodacom.chordiant.reporting.mvc.view.GetOffersCriteria;
import com.vodacom.chordiant.reporting.mvc.view.J4UOffersView;
import com.vodacom.chordiant.reporting.service.ReportingService;

/**
 * Class to show offers coming back from a specific web service
 * 
 * @author Gaurav Soni
 * 
 */
@Controller
public class GetOffersDashboardController {

	@Autowired
	ReportingService reportingService;

	@RequestMapping("/getOffers")
	public ModelAndView getOffers(@ModelAttribute GetOffersCriteria criteriaView) {

		ModelAndView mav = new ModelAndView("dashboard/get_offers_dashboard");
		
		List<J4UOffersView> j4uOffers = null;
		
		if(!StringUtils.isEmptyOrWhitespace(criteriaView.getServer()) && !StringUtils.isEmptyOrWhitespace(criteriaView.getMsisdn())
				&& !StringUtils.isEmptyOrWhitespace(criteriaView.getPorts())) {
			j4uOffers = reportingService.getOffersForCriteria(criteriaView);
			
		}

		mav.addObject("criteriaView", criteriaView);
		mav.addObject("j4uOffers", j4uOffers);

		return mav;

	}

}
