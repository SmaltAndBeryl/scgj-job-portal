package com.cgsc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 
 * @author Prateek Kapoor
 *
 */
@Component
@PropertySource("classpath:sql/employerRegistration.yml")
@ConfigurationProperties(prefix="employerRegistration")
public class EmployerRegistrationConfig 
{

	@Value("${insertUserCredentialDetails}")
	private String insertUserCredentialDetails;
	@Value("${insertEmployerDetails}")
	private String insertEmployerDetails;
	
	public String getInsertUserCredentialDetails() {
		return insertUserCredentialDetails;
	}
	public String getInsertEmployerDetails() {
		return insertEmployerDetails;
	}
	public void setInsertUserCredentialDetails(String insertUserCredentialDetails) {
		this.insertUserCredentialDetails = insertUserCredentialDetails;
	}
	public void setInsertEmployerDetails(String insertEmployerDetails) {
		this.insertEmployerDetails = insertEmployerDetails;
	}
}
