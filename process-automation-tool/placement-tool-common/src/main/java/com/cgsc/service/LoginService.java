package com.cgsc.service;

import java.util.ArrayList;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cgsc.common.ReadApplicationConstants;
import com.cgsc.dao.LoginDao;
import com.cgsc.dto.SessionManagementDto;
import com.cgsc.utility.GenerateOtpUtility;
import com.cgsc.utility.SendOtpUtility;

@Service
public class LoginService implements UserDetailsService{

	private static final Logger Log = LoggerFactory.getLogger(LoginService.class);
	
	@Autowired
	private LoginDao loginDao;
	
	@Autowired
	private SendOtpUtility sendOtpUtility;
	
	@Override
	public SessionManagementDto loadUserByUsername(String mobileNumber) throws UsernameNotFoundException 
	{
		Log.debug("Request received in login service to authenticate the user with mobile number {}",mobileNumber);		
		Collection<SimpleGrantedAuthority>authorities = new ArrayList<SimpleGrantedAuthority>();
		Log.debug("Sending request to DAO to check if the user exists with mobile number {}",mobileNumber);
		long userMobileNumber = Long.parseLong(mobileNumber);
		int status = loginDao.checkMobileNumberExistence(userMobileNumber);
		
		
		if(status==0)
		{
			Log.debug("No user with mobile number {} exists in the database",userMobileNumber);
			authorities.add(new SimpleGrantedAuthority(null));
			Log.debug("Setting the session management Dto to null, returning null to controller");
			return new SessionManagementDto(null,null,null,null,null, null);
		}
		if(status == -25)
		{
			Log.error("An exception occurred in DAO while checking for the existence of the user with mobileNumber",userMobileNumber);
			authorities.add(new SimpleGrantedAuthority(null));
			Log.error("DTO set to null, returning null dto to the controller");
			return new SessionManagementDto(null,null,null,null,null,null);
		}
		else
		{
			Log.debug("User with mobile number {} found in the database",mobileNumber);
			SessionManagementDto sessionManagementDto = loginDao.getValidUserDetails(userMobileNumber);

			int userId = sessionManagementDto.getUserId();
			if(sessionManagementDto.getUserRole().equalsIgnoreCase(ReadApplicationConstants.getUserRoleCandidate()))
			{
				Log.debug("User role is candidate, sending request to update the poll count");
				int resetPollCountStatus = loginDao.resetCandidatePollCount(sessionManagementDto.getUserId());
				if(resetPollCountStatus==-25)
				{
					Log.debug("Could not reset poll count, session would not be set");
					authorities.add(new SimpleGrantedAuthority(null));
					Log.error("DTO set to null, returning null dto to the controller");
					return new SessionManagementDto(null,null,null,null,null,null);
				}
				Log.debug("Poll count successfully updated, returning session object to controller");
			}
			Log.debug("Sending request to dao to capture the last login time for id : {}",userId);
			int isCaptureLoginTimeSuccess = loginDao.captureLoginTime(userId);
			if(isCaptureLoginTimeSuccess==-25){
				return new SessionManagementDto(null,null,null,null,null,null);
			}
			return sessionManagementDto;
		}
	}
	
	/**
	 * @author Prateek Kapoor
	 * @since 08-10-2020
	 * This method performs the following steps - 
	 * 1. Checks if the user with mobile number exists
	 * 2. Checks if the user is active 
	 * 3. Sends request to generate the OTP
	 * 4. Updates the OTP corresponding to the mobile number
	 * 5. Sends request to utility method to send SMS to mobile number
	 * @param mobileNumber
	 * @return 1 in case of success; -25 in case of exception; -55 if mobile number does not exists; -66 if user is inactive
	 */
	public int generateOtp(long mobileNumber)
	{
		Log.debug("Request received in service to generate otp for mobile number {}. Checking if the user exists with the received mobile number",mobileNumber);
		int mobileNumberExistence = loginDao.checkMobileNumberExistence(mobileNumber);
		if(mobileNumberExistence==0)
		{
			Log.error("The mobile number {} does not exists in the database",mobileNumber);
			return -55;
		}
		else if(mobileNumberExistence==-25)
		{
			Log.error("Could not check mobile number existence");
			return -25;
		}
		Log.debug("Mobile number {} exists in the database",mobileNumber);
		
		Log.debug("Checking if the user is an active user");
		int userAccountStatus = loginDao.checkUserAccountStatus(mobileNumber);
		
		if(userAccountStatus==0)
		{
			Log.error("The account associated with mobile number {} is inactive",mobileNumber);
			return -66;
		}
		else if(userAccountStatus==-25)
		{
			Log.error("Could not check user existence status");
			return -25;
		}
	
		Log.debug("User account is active. Sending request to generate the OTP of the user",mobileNumber);
		String userOtp = GenerateOtpUtility.generateOtp();
		Log.debug("OTP generated, sending request to update the OTP for the candidate");
		int otpUpdateStatus = loginDao.updateUserOtp(mobileNumber, userOtp);
		if(otpUpdateStatus==-25)
		{
			Log.error("Could not update the user OTP, returning -25 to controller");
			return -25;
		}
		
		Log.debug("OTP successfully updated, constructing the message body and sending message to the user");
		String otpMessageBody = userOtp+" "+ReadApplicationConstants.getOtpMessage();
		return sendOtpUtility.sendOTP(otpMessageBody, mobileNumber);
	}


}
