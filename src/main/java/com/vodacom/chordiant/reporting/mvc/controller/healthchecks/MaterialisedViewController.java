package com.vodacom.chordiant.reporting.mvc.controller.healthchecks;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.vodacom.chordiant.reporting.mvc.view.MaterialisedView;
import com.vodacom.chordiant.reporting.service.ReportingService;

@Controller
public class MaterialisedViewController {
	
	@Autowired
	ReportingService service;
	
	@RequestMapping("/viewdetails")
	public ModelAndView showMaterialisedViewsDDL() {
		
		ModelAndView mav = new ModelAndView("healthcheck/materialised_view");
		
		int rechargeAndGetCount = service.getRechargeAndGetCount(4);
		
		List<MaterialisedView> reportingViews = service.getViewDetails("NBA_REPORTS_MAIN_MVIEW_ALL");
		
		List<MaterialisedView> rechargeAndGetViews = service.getViewDetails("CUST_REMAINING_EFFORT_");
		
		mav.addObject("reportingViews", reportingViews);
		mav.addObject("rechargeAndGetViews", rechargeAndGetViews);
		mav.addObject("rechargeAndGetCount", rechargeAndGetCount);
		
		return mav;
		
	}

}
