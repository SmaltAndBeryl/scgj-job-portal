package com.cgsc.controller;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cgsc.common.Privilege;
import com.cgsc.dto.ViewEmployerByActiveStatusDto;
import com.cgsc.dto.ViewEmployerDetailsDto;
import com.cgsc.service.ManageEmployerService;

@RestController
public class ManageEmployerController 
{

	private static final Logger Log = LoggerFactory.getLogger(ManageEmployerService.class);
	
	@Autowired
	private ManageEmployerService manageEmployerService;
	
	/**
	 * @author Prateek Kapoor
	 * @since 28-10-2020
	 * This method updates the enrolment status for the user with received id to the received status
	 * @param userId
	 * @param updatedStatus
	 * @return 1 if success; 
	 * -66 in case of invalid status
	 * -25 in case of exception
	 */
	@Privilege(value= {"Admin"})
	@GetMapping("/updateEmployerStatus")
	public int updateUserEnrolmentStatus(@RequestParam("employerId") int userId, @RequestParam("approvalStatus") String updatedStatus)
	{
		Log.debug("Request received in controller to update the enrolment status of the user with id {} to {}",userId, updatedStatus);
		return manageEmployerService.updateUserEnrolmentStatus(userId, updatedStatus);
	}
	
	/**
	 * @author Prateek Kapoor
	 * @since 28-10-2020
	 * This method fetches the collection of employer details whose account status is active and enrolment status is the received enrolment status
	 * @param enrolmentStatus
	 * @return Collection of {@link ViewEmployerDetailsDto} if success; else returns null
	 */
	@Privilege(value= {"Admin"})
	@GetMapping("/viewEmployers")
	public Collection<ViewEmployerDetailsDto> viewEmployerDetailsWithStatus(@RequestParam("approvalStatus") String enrolmentStatus)
	{
		Log.debug("Request received in controller to fetch the employer details whose account status is active and enrolment status is {}",enrolmentStatus);
		return manageEmployerService.viewEmployerDetailsWithStatus(enrolmentStatus);
	}
	
	/**
	 * @author Sarthak Bhutani 
	 * This method fetches the collection of employer details corresponding to the received account status
	 * @since 03/11/2020
	 * @param activeStatus
	 * @return Collection of {@link ViewEmployerByActiveStatusDto} if success, null if exception
	 */
	@Privilege(value= {"Admin"})
	@GetMapping("/viewEmployerByActiveStatus")
	public Collection<ViewEmployerByActiveStatusDto> viewEmployerByActiveStatus(@RequestParam("activeStatus") String activeStatus){
		Log.debug("Request received in controller to fetch employer details with acccount active status as {}",activeStatus);
		return manageEmployerService.viewEmployerByActiveStatus(activeStatus);
	}

}
