package com.cgsc.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgsc.dao.GetUserDetailsByMobileNumberDao;
import com.cgsc.dto.GetUserDetailsByMobileNumberDto;

@Service
public class GetUserDetailsByMobileNumberService {

	private static final Logger Log = LoggerFactory.getLogger(GetUserDetailsByMobileNumberService.class);
	
	@Autowired
	private GetUserDetailsByMobileNumberDao getUserDetailsByMobileNumberDao;
	
	/**
	 * @author Sarthak Bhutani
	 * @since 28/10/2020
	 * @param mobileNumber
	 * Method to fetch user details via mobile number
	 * @return if success : {@link GetUserDetailsByMobileNumberDto}, else if exception : null
	 */
	public GetUserDetailsByMobileNumberDto getUserDetails(long mobileNumber) {
		Log.debug("Request received in service to fetch user details for mobile number :{}",mobileNumber);
		return getUserDetailsByMobileNumberDao.getUserDetails(mobileNumber);
	}
}
