package com.cgsc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


/**
 * 
 * @author Sarthak Bhutani
 *
 */
@Component
@PropertySource("classpath:sql/getUserDetailsByMobileNumber.yml")
@ConfigurationProperties(prefix="getUserDetails")
public class GetUserDetailsByMobileNumberConfig {

	@Value("${getUserDetailsByMobileNumber}")
	String getUserDetailsByMobileNumber;

	public String getGetUserDetailsByMobileNumber() {
		return getUserDetailsByMobileNumber;
	}

	public void setGetUserDetailsByMobileNumber(String getUserDetailsByMobileNumber) {
		this.getUserDetailsByMobileNumber = getUserDetailsByMobileNumber;
	}
	
	
}
