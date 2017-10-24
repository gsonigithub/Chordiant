package com.vodacom.chordiant.reporting.mvc.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

import com.vodacom.chordiant.reporting.mvc.view.MDMCountView;
import com.vodacom.chordiant.reporting.mvc.view.MDMStatsCriteriaView;
import com.vodacom.chordiant.reporting.mvc.view.TransactionCountView;
import com.vodacom.chordiant.reporting.service.ReportingService;

@Controller
public class MDMStatsController {
	
	@Autowired
	ReportingService reportingService;
	
	@Value("${mdm.processList}")
	String processListAsString;

	@RequestMapping("/mdm")
	public ModelAndView showMDMStatsDashboard(
			@ModelAttribute @Valid MDMStatsCriteriaView criteriaView,
			BindingResult result) {

		List<MDMCountView> views = null;
		ModelAndView modelAndView = new ModelAndView("dashboard/mdm_dashboard");

		if (!StringUtils.isEmpty(criteriaView.getStartDate())
				&& !StringUtils.isEmpty(criteriaView.getEndDate())) {
			views = reportingService.getMDMStatisticsForPeriod(criteriaView);
		}
		
		modelAndView.addObject("processList", getProcessList());
		modelAndView.addObject("criteriaView", criteriaView);
		modelAndView.addObject("mdmViews", views);

		return modelAndView;

	}

	private String[] getProcessList() {
		
		String[] processList = StringUtils.split(processListAsString, ",");
		
		return processList;
	}

	private List<MDMCountView> getMDMCountViews() {
		List<MDMCountView>  list = new ArrayList<>();
		
		list.add(new MDMCountView("xx", "2014-02-12", 400000, 20L));
		list.add(new MDMCountView("xx", "2014-02-13", 450000, 25L));
		list.add(new MDMCountView("xx", "2014-02-14", 406000, 45L));
		list.add(new MDMCountView("xx", "2014-02-15", 466000, 20L));
		list.add(new MDMCountView("xx", "2014-02-16", 400000, 25L));
		list.add(new MDMCountView("xx", "2014-02-17", 340000, 60L));
		list.add(new MDMCountView("xx", "2014-02-18", 240000, 62L));
		list.add(new MDMCountView("xx", "2014-02-19", 400000, 30L));
		list.add(new MDMCountView("xx", "2014-02-20", 400000, 33L));
		list.add(new MDMCountView("xx", "2014-02-21", 400000, 42L));
		
		return list;
	}

}
