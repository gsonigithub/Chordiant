package com.vodacom.chordiant.reporting.mvc.controller.healthchecks;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.vodacom.chordiant.reporting.mvc.view.MDMDailyView;
import com.vodacom.chordiant.reporting.service.ReportingService;

@Controller
public class MDMDailyDashboardController {

	@Autowired
	ReportingService reportingService;

	@RequestMapping("/mdmdaily")
	public ModelAndView showMDMDailyPage() {

		ModelAndView mav = new ModelAndView("healthcheck/mdm_daily_dashboard");

		// views = getMdmDailyViews();
		mav.addObject("mdmViews", reportingService.getMDMDailyViews());

		return mav;

	}

	/**
	 * To be used only for DEV purposes
	 * 
	 * @return
	 */
	private List<MDMDailyView> getMdmDailyViews() {
		List<MDMDailyView> views = new ArrayList<>();

		views.add(new MDMDailyView("CAR_ROLLING_USAGE", -1, 26513625, 26511908,
				26524087, 26544432));
		views.add(new MDMDailyView("CAR_CUST_DAILY", -1, 40828419, 40830660,
				40841394, 40879847));
		views.add(new MDMDailyView("CAR_RCHRGS_MTHLY", -1, -1, -1, -1, 26819357));
		views.add(new MDMDailyView("CAR_ROLLING_BUNDL_PURCH", -1, 15126846,
				15128629, 15120644, 15131269));
		views.add(new MDMDailyView("CAR_PROD_DAILY", -1, 387813348, 387454758,
				387114565, 387105836));
		views.add(new MDMDailyView("NBA_SUBS_CUSTM_CAR", -1, 28555707,
				28557512, 28517199, 28518443));
		return views;
	}
}
