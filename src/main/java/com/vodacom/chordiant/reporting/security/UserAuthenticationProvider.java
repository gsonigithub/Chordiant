package com.vodacom.chordiant.reporting.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;

/**
 * AuthenticationProvider Implementation that will connect to Company's remote
 * Authentication system and authenticate a user.
 * 
 * @author Gaurav Soni
 * 
 */
@Component
public class UserAuthenticationProvider implements
		AuthenticationProvider {

	@Value("${admin.userId}")
	private String defaultAdminUserId;

	@Value("${admin.password}")
	private String defaultAdminPassword;

	/**
	 * Defines type of Authentication supported by this
	 */
	@Override
	public boolean supports(Class<?> authentication) {
		return (UsernamePasswordAuthenticationToken.class
				.isAssignableFrom(authentication));
	}

	/**
	 * Authenticates a user against credentials that are stored in db or default
	 * admin credentials in properties file. User roles are extracted for those
	 * that exist in db.
	 */
	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {

		// we will create a list to contain all their application roles and
		// project roles
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

		// user id as supplied in login form
		String userEnterpriseId = authentication.getPrincipal().toString();
		// password retrieved as object. null check required before we take
		// string value
		Object credentials = authentication.getCredentials();
		String password = credentials == null ? null : credentials.toString();
		
		
		if(StringUtils.equals(userEnterpriseId, defaultAdminUserId) && StringUtils.equals(password, defaultAdminPassword)) {
			grantedAuthorities.add(new SimpleGrantedAuthority("ADMIN"));
			return new UsernamePasswordAuthenticationToken(userEnterpriseId,
					password, grantedAuthorities);
		}

	
		// user login is not valid
		return new AnonymousAuthenticationToken(!StringUtils.isEmpty(userEnterpriseId) ? userEnterpriseId:"ANONYMOUS", !StringUtils.isEmpty(password) ? password:"ANONYMOUS",
				Arrays.asList(new SimpleGrantedAuthority("ANONYMOUS")));
	}

}
