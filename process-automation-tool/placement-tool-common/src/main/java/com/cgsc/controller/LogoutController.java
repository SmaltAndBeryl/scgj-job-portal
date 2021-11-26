package com.cgsc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cgsc.common.Privilege;
import com.cgsc.service.LogoutService;

@RestController
public class LogoutController 
{

	@Autowired
	private LogoutService logoutService;
	
	private static final Logger Log = LoggerFactory.getLogger(LogoutController.class);
	
	/**
	* @author Prateek Kapoor
	* @since 08-10-2020
	* @param request
	* @param response
	* This method resets the OTP of the user when they logout of the system
	* @return 1 if success; -25 in case of exception
	*/
	@Privilege(value= {"Candidate","Employer","Admin"})
	@GetMapping("/logout")
	public int logout(HttpServletRequest request, HttpServletResponse response)
	{ 
		Log.debug("Request received in controller to invalidate session and logout the logged in user");
		return logoutService.resetUserOtp(request, response);
	}
}
