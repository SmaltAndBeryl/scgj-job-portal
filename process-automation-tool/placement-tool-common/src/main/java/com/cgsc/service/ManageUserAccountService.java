package com.cgsc.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgsc.common.ReadApplicationConstants;
import com.cgsc.dao.ManageUserAccountDao;

@Service
public class ManageUserAccountService 
{

	private static final Logger Log = LoggerFactory.getLogger(ManageUserAccountService.class);
	
	@Autowired
	private ManageUserAccountDao manageUserAccountDao;
	
	/**
	 * @author Prateek Kapoor
	 * @since 29-10-2020
	 * This method updates the user account status (represented by is_active_flg) for the received userId
	 * @param userId
	 * @param updatedStatus
	 * @return 1 if success;
	 * -66 in case of invalid status
	 * -25 in case of exception
	 */
	public int updateUserAccountStatus(int userId, String updatedStatus)
	{
		Log.debug("Request received in service to update the account status of the user with id {} to {}",userId, updatedStatus);
		if(updatedStatus.equalsIgnoreCase(ReadApplicationConstants.getTrueFlag()) || updatedStatus.equalsIgnoreCase(ReadApplicationConstants.getFalseFlag()))
		{
			Log.debug("Valid account update status received, sending request to update the user account");
			return manageUserAccountDao.updateUserAccountStatus(userId, updatedStatus);
		}
		else
		{
			Log.error("Invalid account update status {} received. Returning -66 to controller");
			return -66;
		}
	}
}
