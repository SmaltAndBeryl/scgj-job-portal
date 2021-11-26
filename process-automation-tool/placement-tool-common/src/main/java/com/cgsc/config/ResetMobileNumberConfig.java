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
@PropertySource("classpath:sql/resetMobileNumber.yml")	
@ConfigurationProperties(prefix="resetMobileNumber")
public class ResetMobileNumberConfig {

	@Value("${resetMobileNumberFromOldToNew}")
	String resetMobileNumberFromOldToNew;

	public String getResetMobileNumberFromOldToNew() {
		return resetMobileNumberFromOldToNew;
	}

	public void setResetMobileNumberFromOldToNew(String resetMobileNumberFromOldToNew) {
		this.resetMobileNumberFromOldToNew = resetMobileNumberFromOldToNew;
	}
}
