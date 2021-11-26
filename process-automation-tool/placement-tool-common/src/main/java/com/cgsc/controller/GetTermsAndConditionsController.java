package com.cgsc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cgsc.common.ReadProfileProperties;
import com.cgsc.dto.GetTermsAndConditionsDto;

@RestController
public class GetTermsAndConditionsController {

	private static final Logger Log = LoggerFactory.getLogger(GetTermsAndConditionsController.class);

	@Autowired
	private ReadProfileProperties readProfileProperties;
	
	/**
	 * @author Sarthak Bhutani
	 * @date 23/11/2020
	 * @return (String) relative path of T&C document in S3 bucket
	 * 
	 * This method returns the path for T&C Document in S3 bucket
	 */
	@GetMapping("/getTermsAndConditions")
	public GetTermsAndConditionsDto getTermsAndConditions() {
		Log.debug("Request receieved in controller to fetch terms & conditions document file path");
		return new GetTermsAndConditionsDto(readProfileProperties.getTermsAndConditions());
	}
}
