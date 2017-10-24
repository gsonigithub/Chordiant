package com.vodacom.chordiant.reporting.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Security configs for the application - Determines which user's will have
 * access to the application.
 * 
 * @author Gaurav Soni
 * 
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	AuthenticationProvider authenticationProvider;
	
	/**
	 * Configuration that will define the login process and application access
	 * rights for users attempting to logon or browsing the system
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable();
		http.authenticationProvider(authenticationProvider).formLogin().loginPage("/login")
				.loginProcessingUrl("/login").defaultSuccessUrl("/wls").permitAll().and().logout().logoutUrl("/logout")
				.and().authorizeRequests().antMatchers("/login","/wlsGraphViews", "/fonts/**", "/content/**").permitAll()
				.anyRequest().authenticated().and().httpBasic();
	}

	/**
	 * Overridden method to prevent default configure to be called.
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.config.annotation.web.configuration.
	 * WebSecurityConfigurerAdapter#authenticationManagerBean()
	 */
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}
