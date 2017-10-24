package com.vodacom.chordiant.reporting.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ReportsController {

	@RequestMapping("/")
	public ModelAndView showReportsMainPage() {
		//return showSpecificReportsPage("daily");
		return new ModelAndView("redirect:/wls");
	}

	@RequestMapping("/reports/{reportsType}")
	public ModelAndView showSpecificReportsPage(
			@PathVariable("reportsType") String reportsType) {

		ModelAndView modelAndView = new ModelAndView(
				"dashboard/reports_dashboard");
		

		modelAndView.addObject("reportType", reportsType);

		return modelAndView;

	}

}
