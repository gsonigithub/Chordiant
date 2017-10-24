package com.vodacom.chordiant.reporting.mvc.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

import com.vodacom.chordiant.reporting.mvc.view.TransactionCountReportingCriteriaView;
import com.vodacom.chordiant.reporting.mvc.view.TransactionCountView;
import com.vodacom.chordiant.reporting.mvc.view.UserLoginView;
import com.vodacom.chordiant.reporting.service.ReportingService;

/** 
 *
 * @author Gaurav Soni
 */
@Controller
public class WLSGraphController {

	@Autowired
	ReportingService reportingService;

	@RequestMapping("/wlsGraphViews")
	public ModelAndView showWLSTransactionsMainPage(
			@ModelAttribute @Valid TransactionCountReportingCriteriaView criteriaView,
			BindingResult result) {

		List<TransactionCountView> ccrViews = null;
		List<TransactionCountView> ccrHourlyViews = null;
		
		List<TransactionCountView> ppfeViews = null;
		List<TransactionCountView> ppfeHourlyViews = null;
		
		List<TransactionCountView> ussdViews = null;
		List<TransactionCountView> ussdHourlyViews = null;
		
		TransactionCountReportingCriteriaView dailyCriteriaView = null;
		TransactionCountReportingCriteriaView hourlyCriteriaView = null;
		dailyCriteriaView = getDailyCriteria();
		
		ModelAndView modelAndView = new ModelAndView("dashboard/wls_dashboard_graph_views");
			
		if (!StringUtils.isEmpty(dailyCriteriaView.getStartDate()) 
				&& !StringUtils.isEmpty(dailyCriteriaView.getEndDate())) {
			ccrViews = reportingService.getAggregatedStats(dailyCriteriaView,"CCR");
			ppfeViews = reportingService.getAggregatedStats(dailyCriteriaView,"PPFE");
			ussdViews = reportingService.getAggregatedStats(dailyCriteriaView,"USSD");
			
		}
		
		hourlyCriteriaView = getHourlyCriteria();
		if (!StringUtils.isEmpty(hourlyCriteriaView.getStartDate()) 
				&& !StringUtils.isEmpty(hourlyCriteriaView.getEndDate())) {
			
			ccrHourlyViews = reportingService.getAggregatedStats(hourlyCriteriaView,"CCR");
			ppfeHourlyViews = reportingService.getAggregatedStats(hourlyCriteriaView,"PPFE");
			ussdHourlyViews = reportingService.getAggregatedStats(hourlyCriteriaView,"USSD");
			
		}		
		
		modelAndView.addObject("ussdTransactionViews", ussdViews);
		modelAndView.addObject("ussdTransactionViewsHour", ussdHourlyViews);
		
		modelAndView.addObject("ccrTransactionViews", ccrViews);
		modelAndView.addObject("ccrTransactionHourViews", ccrHourlyViews);
		
		modelAndView.addObject("ppfeTransactionViews", ppfeViews);
		modelAndView.addObject("ppfeTransactionHourViews", ppfeHourlyViews);
		
		return modelAndView;

	}

	private TransactionCountReportingCriteriaView getHourlyCriteria() {
		TransactionCountReportingCriteriaView criteria = new TransactionCountReportingCriteriaView();
		criteria.setStartDate(getDate(1));
		criteria.setStartTime("00:00");
		criteria.setEndDate(getDate(1));
		criteria.setEndTime("23:59");
		criteria.setIntervals("hourly");
		return criteria;
	}

	private TransactionCountReportingCriteriaView getDailyCriteria() {
		TransactionCountReportingCriteriaView criteria = new TransactionCountReportingCriteriaView();
		criteria.setStartDate(getDate(8));
		criteria.setStartTime("00:00");
		criteria.setEndDate(getDate(1));
		criteria.setEndTime("23:59");
		criteria.setIntervals("daily");
		return criteria;
	}
	
private String getDate(int daysToSubstract) {
		
		DateTime dateTime = new DateTime();				
		dateTime = dateTime.minusDays(daysToSubstract);		
		Date date = dateTime.toDate();		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");		
		return format.format(date);		
	}
}
