package com.cgsc.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cgsc.dao.EmployerRegistrationDao;
import com.cgsc.dto.EmployerRegistrationDto;

@Service
public class EmployerRegistrationService
{

	private static final Logger Log = LoggerFactory.getLogger(EmployerRegistrationService.class);
	
	@Autowired
	private EmployerRegistrationDao employerRegistrationDao;
	
	/**
	 * @author Prateek Kapoor
	 * This method inserts the employer mobile number, address into users table and then calls the insertEmployerDetails method to insert employer 
	 * details into employer table
	 * @since 06-10-2020
	 * @param employerRegistrationDto
	 * @return 1 if success; -25 in case of exception; -55 if the employer mobile number already exists; -66 in case the email of employer already exists
	 */
	@Transactional(rollbackFor=Exception.class)
	public int registerEmployer(EmployerRegistrationDto employerRegistrationDto) throws Exception
	{
		Log.debug("Request recieved in dao to generate credentials for the employer with email address {}, name {} and mobile number {}",employerRegistrationDto.getEmail(),employerRegistrationDto.getCompanyName(),employerRegistrationDto.getMobileNumber());
		Log.debug("Sending request to generate employer credentials and return the employer id");
		int employerId = employerRegistrationDao.generateEmployerCredentials(employerRegistrationDto);
		if(employerId==-25)
		{
			Log.error("Could not generate the employer credentials, returning -25 to controller");
			return -25;
		}
		else if(employerId==-55)
		{
			Log.error("The mobile number entered by the employer already exists, returning -55 to controller");
			return -55;
		}
		else
		{
			Log.debug("Employer user credentials generated successfully with generated key {}, sending request to insert employer details",employerId);
			try 
			{
				return employerRegistrationDao.insertEmployerDetails(employerRegistrationDto, employerId);
			}
			catch(DuplicateKeyException duplicateKey)
			{
				Log.error("Email address for the employer already exists, employer details cannot be inserted");
				throw new DuplicateKeyException("Duplicate Key Exception",duplicateKey);
			}
			catch (Exception e) 
			{
				Log.error("Could not insert employer details, returning -25 to the controller");
				throw new Exception(e);
			}
		}
	}
}
