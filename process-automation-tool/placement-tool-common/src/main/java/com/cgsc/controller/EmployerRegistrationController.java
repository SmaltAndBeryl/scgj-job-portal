package com.cgsc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cgsc.dto.EmployerRegistrationDto;
import com.cgsc.service.EmployerRegistrationService;

@RestController
public class EmployerRegistrationController 
{

	private static final Logger Log = LoggerFactory.getLogger(EmployerRegistrationController.class);
	
	@Autowired
	private EmployerRegistrationService employerRegistrationService;
	
	/**
	 * @author Prateek Kapoor
	 * This method calls the service method to generate the credentials for the employer
	 * @since 06-10-2020
	 * @param employerRegistrationDto
	 * @return 1 if success; -25 in case of exception; -55 if the employer mobile number already exists; -66 in case the email of employer already exists
	 */
	@PostMapping(value="/registerEmployer",consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public int registerEmployer(@RequestBody EmployerRegistrationDto employerRegistrationDto)
	{
		Log.debug("Request recieved in dao to generate credentials for the employer with email address {}, name {} and mobile number {}",employerRegistrationDto.getEmail(),employerRegistrationDto.getCompanyName(),employerRegistrationDto.getMobileNumber());
		try 
		{
			return employerRegistrationService.registerEmployer(employerRegistrationDto);
		}
		catch(DuplicateKeyException duplicateKey)
		{
			Log.error("Email address for the employer already exists, employer details cannot be inserted. Returning -66 to front end");
			return -66;
		}
		catch (Exception e) 
		{
			Log.error("Could not generate the employer credentials, returning -25 to frontend");
			return -25;
		}
	}
}
