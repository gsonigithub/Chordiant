package com.vodacom.chordiant.reporting.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.vodacom.chordiant.reporting.mvc.view.UserLoginView;

/**
 * This will load the initial login page / base entry point for a user to access the application
 *
 * @author Gaurav Soni
 */
@Controller
@RequestMapping("/login")
public class LoginController {

	private static final String LOGIN_FORM = "loginForm";

	/**
	 * Load the application login page
     *
	 * @return
	 */
	@RequestMapping
	public ModelAndView showLoginPage() {

		ModelAndView mav = new ModelAndView("login/login");

		mav.addObject(LOGIN_FORM, new UserLoginView());

		return mav;
	}
}
