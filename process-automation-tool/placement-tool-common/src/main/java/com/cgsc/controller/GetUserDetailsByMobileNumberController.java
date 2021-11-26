package com.cgsc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cgsc.common.Privilege;
import com.cgsc.dto.GetUserDetailsByMobileNumberDto;
import com.cgsc.service.GetUserDetailsByMobileNumberService;

@RestController
public class GetUserDetailsByMobileNumberController {

	private static final Logger Log = LoggerFactory.getLogger(GetUserDetailsByMobileNumberController.class);
	@Autowired
	private GetUserDetailsByMobileNumberService getUserDetailsByMobileNumberService;
	
	/**
	 * @author Sarthak Bhutani
	 * @since 28/10/2020
	 * @param mobileNumber
	 * Method to fetch user's details via mobile number
	 * @return if success : {@link GetUserDetailsByMobileNumberDto}, else in case of exception : null
	 */
	@Privilege(value= {"Admin"})
	@GetMapping("/getUserDetailsByMobileNumber")
	public GetUserDetailsByMobileNumberDto getUserDetails(@RequestParam("mobileNumber") long mobileNumber) {
		Log.debug("Request received in controller from front end to fetch user details for Mobile Number : {}",mobileNumber);
		return getUserDetailsByMobileNumberService.getUserDetails(mobileNumber);
	}

}
