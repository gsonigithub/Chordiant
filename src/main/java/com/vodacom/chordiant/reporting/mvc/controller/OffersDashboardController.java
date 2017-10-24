package com.vodacom.chordiant.reporting.mvc.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

import com.vodacom.chordiant.reporting.mvc.view.OfferView;
import com.vodacom.chordiant.reporting.mvc.view.OffersSelectionCriteria;
import com.vodacom.chordiant.reporting.service.ReportingService;

@Controller
public class OffersDashboardController {
	
	@Autowired
	ReportingService reportingService;

	@RequestMapping("/offers")
	public ModelAndView showOffersMainPage(
			@ModelAttribute @Valid OffersSelectionCriteria criteria,
			BindingResult result) {

		ModelAndView mav = new ModelAndView("dashboard/offers_dashboard");
		
		List<OfferView> offerViews = null;
		
		if(criteria != null && !StringUtils.isEmptyOrWhitespace(criteria.getDateReportingOn())) {			
			offerViews = reportingService.getOffers(criteria);
		}

		mav.addObject("criteriaView", criteria);
		mav.addObject("offerViews", offerViews);

		return mav;

	}

}
