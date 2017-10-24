package com.vodacom.chordiant.reporting.mvc.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.vodacom.chordiant.reporting.mvc.view.AddAvailabilityForm;
import com.vodacom.chordiant.reporting.mvc.view.AvailabilityCriteriaView;
import com.vodacom.chordiant.reporting.service.ReportingService;

/**
 * Controller class for the Chordiant Availability
 * 
 * @author Gaurav Soni
 * 
 */
@Controller
public class ChordiantAvailabilityController {

	@Autowired
	ReportingService reportingService;

	@RequestMapping("/availability")
	public ModelAndView showAvailabilityDashboard(
			@ModelAttribute("criteriaView") @Valid AvailabilityCriteriaView criteriaView,
			BindingResult result) {
		ModelAndView mav = new ModelAndView("dashboard/chordiant_availability");

		mav.addObject("availabilityForms", reportingService
				.getChordiantAvailabilityForPeriod(criteriaView));

		return mav;

	}

	@RequestMapping("/availability/add")
	public ModelAndView addAvailabilityReport() {
		ModelAndView mav = new ModelAndView(
				"dashboard/add_chordiant_availability");

		mav.addObject("addAvailabilityForm", new AddAvailabilityForm());

		return mav;

	}

	@RequestMapping("/availability/{id}/edit")
	public ModelAndView editAvailabilityReport(@PathVariable("id") Long id) {
		ModelAndView mav = new ModelAndView(
				"dashboard/add_chordiant_availability");

		mav.addObject("addAvailabilityForm",
				reportingService.findAvailabilityReportById(id));

		return mav;

	}

	@RequestMapping("/availability/{id}/delete")
	public ModelAndView deleteAvailabilityReport(@PathVariable("id") String id) {

		System.out.println("deleting for:" + id + "mm");
		
		reportingService.deleteAvailabilityReportById(Long.parseLong(id));

		return new ModelAndView("redirect:/availability");

	}

	@RequestMapping("/availability/save")
	public ModelAndView saveAvailabilityReport(
			@ModelAttribute @Valid AddAvailabilityForm addAvailabilityForm) {

		reportingService.saveAvailabilityForm(addAvailabilityForm);

		return new ModelAndView("redirect:/availability");

	}

}
