package com.cgsc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:sql/logout.yml")
@ConfigurationProperties(prefix="logout")
public class LogoutConfig 
{

	@Value("${resetUserOtp}")
	private String resetUserOtp;

	public String getResetUserOtp() {
		return resetUserOtp;
	}

	public void setResetUserOtp(String resetUserOtp) {
		this.resetUserOtp = resetUserOtp;
	}

}
