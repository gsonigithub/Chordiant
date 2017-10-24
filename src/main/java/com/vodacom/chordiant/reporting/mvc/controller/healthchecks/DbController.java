package com.vodacom.chordiant.reporting.mvc.controller.healthchecks;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.vodacom.chordiant.reporting.mvc.view.DatabaseStatusView;
import com.vodacom.chordiant.reporting.service.ReportingService;

@Controller
public class DbController {
	
	@Autowired
	ReportingService service;
	
	@RequestMapping("/databasestatus")
	public ModelAndView showDb() {

		ModelAndView mav = new ModelAndView("healthcheck/database_status");
		
		//List<DatabaseStatusView> prodDBViews = service.getCarDataAvailability("prod");
		
		List<DatabaseStatusView> standbyDBViews = service.getCarDataAvailability("standby");
		
		List<DatabaseStatusView> interimDBViews = service.getCarDataAvailability("interim");

		//mav.addObject("prodDBViews", prodDBViews);
		mav.addObject("standbyDBViews", standbyDBViews);
		mav.addObject("interimDBViews", interimDBViews);
		
		
		return mav;
	}

}
