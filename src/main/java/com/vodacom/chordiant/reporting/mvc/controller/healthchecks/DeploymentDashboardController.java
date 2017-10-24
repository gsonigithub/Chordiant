package com.vodacom.chordiant.reporting.mvc.controller.healthchecks;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.vodacom.chordiant.reporting.mvc.view.DeploymentView;
import com.vodacom.chordiant.reporting.service.ReportingService;

@Controller
public class DeploymentDashboardController {

	/*
	 * Main Service class bean
	 */
	@Autowired
	ReportingService reportingService;

	/**
	 * Shows the page for number of offer logic deployments available in db.
	 * TODO: Page to be changed to show Chordiant DB configs
	 * 
	 * @return
	 */
	@RequestMapping("/deployment")
	public ModelAndView showOfferLogicDeployments() {

		ModelAndView modelAndView = new ModelAndView(
				"healthcheck/deployment_status");

		modelAndView.addObject("deploymentViews",
				reportingService.getOfferLogicDeployments());

		return modelAndView;

	}

	/**
	 * Returns a stubbed list of deployment Views - Used for DEV purposes only
	 * 
	 * @return
	 */
	private List<DeploymentView> getDeploymentViews() {

		List<DeploymentView> views = new ArrayList<>();

		views.add(new DeploymentView(1013, 1080, "RA", 205, 1042,
				"undeployable"));
		views.add(new DeploymentView(1014, 1085, "RA", 207, 1044, "deployable"));
		views.add(new DeploymentView(1015, 1087, "RA", 208, 1046,
				"undeployable"));
		return views;
	}
}
