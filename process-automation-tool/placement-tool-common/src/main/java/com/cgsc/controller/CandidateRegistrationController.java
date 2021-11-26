package com.cgsc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cgsc.dto.RegisterCandidateDto;
import com.cgsc.service.CandidateRegistrationService;

@RestController
public class CandidateRegistrationController 
{

	private static final Logger Log = LoggerFactory.getLogger(CandidateRegistrationController.class);
	
	@Autowired
	private CandidateRegistrationService registerCandidateService;
	
	/**
	 * @author Prateek Kapoor
	 * @since 07-10-2020
	 * Method to generate candidate credentials
	 * @param registerCandidateDto
	 * @return 1 if success; 
	 * -25 in case of exception;
	 * -55 in case of duplicate mobile number
	 */
	@PostMapping(value="/registerCandidates",consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
	public int generateCandidateCredentials(@ModelAttribute RegisterCandidateDto registerCandidateDto)
	{
		try 
		{
			Log.debug("Request received in controller to generate the candidate credentials for candidate with mobile number {}",registerCandidateDto.getMobileNumber());
			return registerCandidateService.generateCandidateCredentials(registerCandidateDto);
		}
		catch(Exception e)
		{
			Log.error("An exception occurred while generating candidate credentials "+e);
			return -25;
		}
	}

}
