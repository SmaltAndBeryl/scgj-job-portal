package com.cgsc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cgsc.common.Privilege;
import com.cgsc.service.ResetMobileNumberService;

@RestController
public class ResetMobileNumberController {
	
	private static final Logger Log = LoggerFactory.getLogger(ResetMobileNumberController.class);
	
	@Autowired
	private ResetMobileNumberService resetMobileNumberService;
	
	/**
	 * @author Sarthak Bhutani
	 * @since 27/10/2020
	 * @param oldMobileNumber
	 * @param newMobileNumber
	 * Method to Change user's mobile number from old(prev) to new
	 * @return if success : 1
	 * 			else if (old mobile number doesn't exist) : -55
	 * 			else if (new number already exists) : -88
	 * 			else if exception : -25
	 */
	@Privilege(value={"Admin"})
	@GetMapping("/resetMobileNumber")
	public int resetMobileNumber(@RequestParam("oldMobileNumber") long oldMobileNumber, @RequestParam("newMobileNumber") long newMobileNumber) {
		Log.debug("Request receieved in controller by frontend to change the mobile number of the user from :{} to {}",oldMobileNumber,newMobileNumber);
		return resetMobileNumberService.resetMobileNumber(oldMobileNumber, newMobileNumber);
	}
	
}
