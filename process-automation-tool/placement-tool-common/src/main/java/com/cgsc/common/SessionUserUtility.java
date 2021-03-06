package com.cgsc.common;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.cgsc.dto.SessionManagementDto;



@Service
public class SessionUserUtility {

	private static final Logger LOGGER = LoggerFactory.getLogger(SessionUserUtility.class);
	
	 public static SessionManagementDto getSessionMangementfromSession()
	 {
		 	LOGGER.debug("Request Received to get Session details of Logged In user");
		 	LOGGER.debug("Returning Session in a SessionManagementDto of Logged in User");
		 	return (SessionManagementDto)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	 }

}
