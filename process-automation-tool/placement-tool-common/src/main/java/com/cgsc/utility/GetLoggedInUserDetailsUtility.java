package com.cgsc.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.cgsc.common.SessionUserUtility;

@Component
public class GetLoggedInUserDetailsUtility
{

	private static final Logger Log = LoggerFactory.getLogger(GetLoggedInUserDetailsUtility.class);
	
	public static Integer getUserIdFromSession()
	{
		Integer userId = SessionUserUtility.getSessionMangementfromSession().getUserId();
		Log.debug("Fetched the user id - {} of the logged in user",userId);
		return userId;
	}
	
	public static String getNameFromSession()
	{
		Log.debug("Request received to fetch the email of logged in user");
		return SessionUserUtility.getSessionMangementfromSession().getUsername();
	}
}
