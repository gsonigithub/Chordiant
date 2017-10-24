package com.vodacom.chordiant.reporting.mvc.controller.healthchecks;

import java.net.MalformedURLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.vodacom.chordiant.reporting.service.ReportingService;

/**
 * Controller class to show the status of the J4U webservices
 * 
 * @author Gaurav Soni
 * 
 */
@Controller
public class WebServicesStatusController {

	@Autowired
	ReportingService reportingService;

	/**
	 * Mapping to show the status of the web services deployed
	 * 
	 * @return
	 * @throws MalformedURLException
	 */
	@RequestMapping("/webservices")
	public ModelAndView showWebServicesStatus() throws MalformedURLException {

		ModelAndView modelAndView = new ModelAndView(
				"healthcheck/webservices_status");

		modelAndView.addObject("pcho001zatcrhWebServices", reportingService
				.getWebServiceStatus("pcho001zatcrh.vodacom.corp"));
		
		modelAndView.addObject("pcho002zafsrhWebServices", reportingService
				.getWebServiceStatus("pcho002zafsrh.vodacom.corp"));
		
		modelAndView.addObject("pcho003zatcrhWebServices", reportingService
				.getWebServiceStatus("pcho003zatcrh.vodacom.corp"));
		
		modelAndView.addObject("pcho004zafsrhWebServices", reportingService
				.getWebServiceStatus("pcho004zafsrh.vodacom.corp"));

		return modelAndView;

	}

}
