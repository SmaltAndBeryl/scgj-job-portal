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
@PropertySource("classpath:sql/login.yml")
@ConfigurationProperties(prefix="userLogin")
public class LoginConfig 
{
	@Value("${checkMobileNumber}")
	private String checkMobileNumber;
	
	@Value("${checkAccountActivationStatus}")
	private String checkAccountActivationStatus;
	
	@Value("${updateUserOtp}")
	private String updateUserOtp;
	
	@Value("${resetPollCount}")
	private String resetPollCount;

	@Value("${userDetailsSql}")
	private String userDetailsSql;

	@Value("${captureLoginTimeForUser}")
	private String captureLoginTimeForUser;
	

	public String getUserDetailsSql() {
		return userDetailsSql;
	}

	public void setUserDetailsSql(String userDetailsSql) {
		this.userDetailsSql = userDetailsSql;
	}

	public String getResetPollCount() {
		return resetPollCount;
	}

	public void setResetPollCount(String resetPollCount) {
		this.resetPollCount = resetPollCount;
	}

	public String getUpdateUserOtp() {
		return updateUserOtp;
	}

	public void setUpdateUserOtp(String updateUserOtp) {
		this.updateUserOtp = updateUserOtp;
	}

	public String getCheckMobileNumber() {
		return checkMobileNumber;
	}

	public String getCheckAccountActivationStatus() {
		return checkAccountActivationStatus;
	}

	public void setCheckMobileNumber(String checkMobileNumber) {
		this.checkMobileNumber = checkMobileNumber;
	}

	public void setCheckAccountActivationStatus(String checkAccountActivationStatus) {
		this.checkAccountActivationStatus = checkAccountActivationStatus;
	}

	public String getCaptureLoginTimeForUser() {
		return captureLoginTimeForUser;
	}

	public void setCaptureLoginTimeForUser(String captureLoginTimeForUser) {
		this.captureLoginTimeForUser = captureLoginTimeForUser;
	}
}
