package com.vodacom.chordiant.reporting.mvc.controller.healthchecks;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.vodacom.chordiant.reporting.mvc.view.OfferDailyView;
import com.vodacom.chordiant.reporting.service.ReportingService;

/**
 * Controller class to show number of offers presented per each channel for the
 * last 15 min
 * 
 * @author Gaurav Soni
 * 
 */
@Controller
public class OfferDailyDashboardController {
	
	@Autowired
	ReportingService reportingService;

	/**
	 * Shows offers presented during the last minute for all 3 channels
	 * 
	 * @return
	 */
	@RequestMapping("/offerdaily")
	public ModelAndView showOfferDaily() {

		ModelAndView offerView = new ModelAndView(
				"healthcheck/offer_count");
		
		offerView.addObject("offerView", reportingService.getOfferDailyView());

		return offerView;

	}

}
