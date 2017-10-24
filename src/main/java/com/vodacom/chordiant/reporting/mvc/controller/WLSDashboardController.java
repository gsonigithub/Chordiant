package com.vodacom.chordiant.reporting.mvc.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

import com.vodacom.chordiant.reporting.mvc.view.AddAvailabilityForm;
import com.vodacom.chordiant.reporting.mvc.view.TransactionCountReportingCriteriaView;
import com.vodacom.chordiant.reporting.mvc.view.TransactionCountView;
import com.vodacom.chordiant.reporting.mvc.view.WLSFindingView;
import com.vodacom.chordiant.reporting.service.ReportingService;

@Controller
public class WLSDashboardController {

	@Autowired
	ReportingService reportingService;

	@RequestMapping("/wls")
	public ModelAndView showWLSTransactionsMainPage(
			@ModelAttribute @Valid TransactionCountReportingCriteriaView criteriaView,
			BindingResult result) {

		List<TransactionCountView> ccrViews = null;
		List<TransactionCountView> ppfeViews = null;
		List<TransactionCountView> ussdViews = null;
		
		ModelAndView modelAndView = new ModelAndView("dashboard/wls_dashboard");

		if (!StringUtils.isEmpty(criteriaView.getStartDate()) 
				&& !StringUtils.isEmpty(criteriaView.getEndDate())) {
			
			ccrViews = reportingService.getAggregatedStats(criteriaView,"CCR");
			ppfeViews = reportingService.getAggregatedStats(criteriaView,"PPFE");
			ussdViews = reportingService.getAggregatedStats(criteriaView,"USSD");
			
		}
		
		modelAndView.addObject("criteriaView", criteriaView);
		modelAndView.addObject("ccrTransactionViews", ccrViews);
		modelAndView.addObject("ppfeTransactionViews", ppfeViews);
		modelAndView.addObject("ussdTransactionViews", ussdViews);
		

		return modelAndView;

	}
	
	@RequestMapping("/wls/availability/new")
	public ModelAndView showAddFindingPage() {
		
		ModelAndView mav = new ModelAndView("dashboard/wls_dashboard_finding");
		
		mav.addObject("findingForm", new WLSFindingView());
		
		return mav;
		
	}
	
	@RequestMapping("/wls/availability/edit/{id}")
	public ModelAndView showAddFindingPage(@PathVariable("id") Long id) {
		
		ModelAndView mav = new ModelAndView("dashboard/wls_dashboard_finding");
		
		mav.addObject("findingForm", new WLSFindingView());
		
		return mav;
		
	}
	
	@RequestMapping("/wls/availability/save")
	public ModelAndView saveFindings(
			@ModelAttribute WLSFindingView findingForm) {
		
		ModelAndView mav = new ModelAndView("redirect:/wls");
		
		return mav;
		
	}

}
