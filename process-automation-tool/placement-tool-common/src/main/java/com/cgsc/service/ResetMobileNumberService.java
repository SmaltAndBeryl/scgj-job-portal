package com.cgsc.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgsc.dao.LoginDao;
import com.cgsc.dao.ResetMobileNumberDao;

@Service
public class ResetMobileNumberService {

	public static final Logger Log = LoggerFactory.getLogger(ResetMobileNumberService.class);
	
	@Autowired
	private ResetMobileNumberDao resetMobileNumberDao;
	@Autowired
	LoginDao loginDao;
	
	/**
	 * @author Sarthak Bhutani
	 * @since 27/10/2020
	 * Method to Change user's mobile number from old(prev) to new
	 * 		if old number doesn't exist in db
	 * 			return -55
	 *      if new number is duplicate/already exists in db
	 *          return -88
	 * 		if(old number exists & new number doesn't exist)
	 * 			proceed to Dao to change the number
	 * @param oldMobileNumber
	 * @param newMobileNumber
	 * @return if success 1, in case of exception -25
	 */
	public int resetMobileNumber(long oldMobileNumber, long newMobileNumber) {
		Log.debug("Request received in service to change user's mobile number from :{} to {}",oldMobileNumber,newMobileNumber);
		Log.debug("Checking if old mobile number exists");
		int oldMobileNumberFlag = loginDao.checkMobileNumberExistence(oldMobileNumber);
		if(oldMobileNumberFlag!=1)
			return -55;
		Log.debug("Checking if new mobile number exists");
		int newMobileNumberFlag = loginDao.checkMobileNumberExistence(newMobileNumber);
		if(newMobileNumberFlag!=0)
			return -88;
		Log.debug("Condition satisfied: Old Mobile Number exists, New Mobile Number doesn't exist");
		Log.debug("Sending request to dao to update the mobile number of the user from {} to {}",oldMobileNumber,newMobileNumber);
		return resetMobileNumberDao.resetMobileNumber(oldMobileNumber,newMobileNumber);
	}
}
