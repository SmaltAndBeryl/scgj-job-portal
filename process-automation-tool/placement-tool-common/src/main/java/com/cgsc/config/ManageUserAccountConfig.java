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
@PropertySource("classpath:sql/manageUserAccount.yml")
@ConfigurationProperties(prefix="manageUserAccounts")
public class ManageUserAccountConfig 
{

	@Value("${updateUserAccountStatus}")
	private String updateUserAccountStatus;

	public String getUpdateUserAccountStatus() {
		return updateUserAccountStatus;
	}

	public void setUpdateUserAccountStatus(String updateUserAccountStatus) {
		this.updateUserAccountStatus = updateUserAccountStatus;
	}

}
