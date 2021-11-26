package com.cgsc.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class FetchActiveProfileUtility 
{

	private static final Logger Log = LoggerFactory.getLogger(FetchActiveProfileUtility.class);
	
	@Autowired
	private Environment environment;
	
	/**
	 * @author Prateek Kapoor
	 * @since 03-10-2020
	 * @return Current active profile
	 */
	public String getActiveProfile()
	{
		Log.debug("Request received in utility method to fetch the current active profile");
		return environment.getActiveProfiles()[0].toString();
	}
}
