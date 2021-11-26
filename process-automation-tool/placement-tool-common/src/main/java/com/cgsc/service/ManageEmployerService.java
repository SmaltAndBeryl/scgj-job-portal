package com.cgsc.service;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgsc.common.ReadApplicationConstants;
import com.cgsc.dao.ManageEmployerDao;
import com.cgsc.dto.ViewEmployerByActiveStatusDto;
import com.cgsc.dto.ViewEmployerDetailsDto;

@Service
public class ManageEmployerService 
{

	private static final Logger Log = LoggerFactory.getLogger(ManageEmployerService.class);
	
	@Autowired
	private ManageEmployerDao manageEmployerDao;
	
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
	public int updateUserEnrolmentStatus(int userId, String updatedStatus)
	{
		Log.debug("Request received in service to update the enrolment status of the user with id {} to {}",userId, updatedStatus);
		if(!(updatedStatus.equalsIgnoreCase(ReadApplicationConstants.getInReviewFlag()) || updatedStatus.equalsIgnoreCase(ReadApplicationConstants.getApprovedFlag()) || (updatedStatus.equalsIgnoreCase(ReadApplicationConstants.getRejectedFlag()))))
		{
			Log.error("Invalid status received from controller, returning -66 to controller");
			return -66;
		}
		else
		{
			Log.debug("Sending request to update the user enrolment status");
			return manageEmployerDao.updateUserEnrolmentStatus(userId, updatedStatus);
		}
	}
	
	/**
	 * @author Prateek Kapoor
	 * @since 28-10-2020
	 * This method fetches the collection of employer details whose account status is active and enrolment status is the received enrolment status
	 * @param enrolmentStatus
	 * @return Collection of {@link ViewEmployerDetailsDto} if success; else returns null
	 */
	public Collection<ViewEmployerDetailsDto> viewEmployerDetailsWithStatus(String enrolmentStatus)
	{
		Log.debug("Request received in service to fetch the employer details whose account status is active and enrolment status is {}",enrolmentStatus);
		return manageEmployerDao.viewEmployerDetailsWithStatus(enrolmentStatus);
	}
	
	/**
	 * @author Sarthak Bhutani 
	 * This method fetches the collection of employer details corresponding to the received account status
	 * @since 03/11/2020
	 * @param activeStatus
	 * @return Collection of {@link ViewEmployerByActiveStatusDto} if success, null if exception
	 */
	public Collection<ViewEmployerByActiveStatusDto> viewEmployerByActiveStatus(String activeStatus){
		Log.debug("Request received in service to fetch employer details by active status: {}",activeStatus);
		return manageEmployerDao.viewEmployerByActiveStatus(activeStatus);
	}
}
