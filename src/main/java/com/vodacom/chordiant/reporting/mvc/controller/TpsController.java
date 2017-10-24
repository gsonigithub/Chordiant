package com.vodacom.chordiant.reporting.mvc.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

import com.vodacom.chordiant.reporting.mvc.view.TPSCountView;
import com.vodacom.chordiant.reporting.mvc.view.TpsReportingCriteriaView;
import com.vodacom.chordiant.reporting.mvc.view.TransactionCountReportingCriteriaView;
import com.vodacom.chordiant.reporting.mvc.view.TransactionCountView;
import com.vodacom.chordiant.reporting.service.ReportingService;

@Controller
public class TpsController {

	@Autowired
	ReportingService reportingService;

	@RequestMapping("/tps")
	public ModelAndView showTpsDashboard(
			@ModelAttribute @Valid TransactionCountReportingCriteriaView criteriaView) {

		ModelAndView mav = new ModelAndView("dashboard/tps_dashboard");


		List<TPSCountView> ccrViews = null;
		List<TPSCountView> ppfeViews = null;
		List<TPSCountView> ussdViews = null;

		
		if (!StringUtils.isEmpty(criteriaView.getStartDate())
				&& !StringUtils.isEmpty(criteriaView.getEndDate())) {
			/*
			externalViews = reportingService.getAggregatedStatsForPeriod(
					criteriaView.getStartDate(), criteriaView.getEndDate(),
					"tps", criteriaView.getStartTime(),
					criteriaView.getEndTime(), "external");

			assessmentViews = reportingService.getAggregatedStatsForPeriod(
					criteriaView.getStartDate(), criteriaView.getEndDate(),
					"tps", criteriaView.getStartTime(),
					criteriaView.getEndTime(), "assessment");

			j4uViews = reportingService.getAggregatedStatsForPeriod(
					criteriaView.getStartDate(), criteriaView.getEndDate(),
					"tps", criteriaView.getStartTime(),
					criteriaView.getEndTime(), "Decision");
				*/
			
			
			ccrViews = reportingService.getTPSStats(criteriaView,"CCR");
			ppfeViews = reportingService.getTPSStats(criteriaView,"PPFE");
			ussdViews = reportingService.getTPSStats(criteriaView,"USSD");
			
		}

		mav.addObject("criteriaView", criteriaView);
		mav.addObject("ccrViews", ccrViews);
		mav.addObject("ppfeViews", ppfeViews);
		mav.addObject("ussdViews", ussdViews);

		return mav;

	}

}
