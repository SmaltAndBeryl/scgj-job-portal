package com.cgsc.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import com.cgsc.dao.LogoutDao;
import com.cgsc.utility.GetLoggedInUserDetailsUtility;

@Service
public class LogoutService 
{

	private static final Logger Log = LoggerFactory.getLogger(LogoutService.class);
	
	@Autowired
	private LogoutDao logoutDao;
	
	/**
	 * @author Prateek Kapoor
	 * @since 06-07-2020
	 * @param request
	 * @param response
	 * This method resets the OTP of the user when they logout of the system
	 * @return 1 if success; -25 in case of exception
	 */
	public int resetUserOtp(HttpServletRequest request, HttpServletResponse response)
	{
		Log.debug("Request received in service to logout the logged in user and reset the OTP");
		Integer userId = GetLoggedInUserDetailsUtility.getUserIdFromSession();
		Log.debug("Sending request to reset the user OTP for user with id {}",userId);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		int resetStatus = logoutDao.resetUserOtp(userId);
		if(resetStatus==1)
		{
			Log.debug("OTP reset for user with userId {}. Invalidating session for the user",userId);
	        if (auth != null)
	        {      
	           new SecurityContextLogoutHandler().logout(request, response, auth);  
	           Log.debug("Session invalidated, returning 1 to controller");
	           return 1;
	        }	
		}
        Log.error("Could not reset user otp, session will not be invalidated. Returning -25 to controller");
        return -25;
	}
}
