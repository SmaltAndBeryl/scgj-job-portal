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
@PropertySource("classpath:sql/manageEmployer.yml")
@ConfigurationProperties(prefix="manageEmployers")
public class ManageEmployerConfig 
{

	@Value("${updateEmployerEnrolmentStatus}")
	private String updateEmployerEnrolmentStatus;
	
	@Value("${viewEmployerWithEnrolmentStatus}")
	private String viewEmployerWithEnrolmentStatus;
	
	@Value("${viewEmployerByActiveStatus}")
	private String viewEmployerByActiveStatus;

	public String getViewEmployerByActiveStatus() {
		return viewEmployerByActiveStatus;
	}

	public void setViewEmployerByActiveStatus(String viewEmployerByActiveStatus) {
		this.viewEmployerByActiveStatus = viewEmployerByActiveStatus;
	}

	public String getUpdateEmployerEnrolmentStatus() {
		return updateEmployerEnrolmentStatus;
	}

	public void setUpdateEmployerEnrolmentStatus(String updateEmployerEnrolmentStatus) {
		this.updateEmployerEnrolmentStatus = updateEmployerEnrolmentStatus;
	}

	public String getViewEmployerWithEnrolmentStatus() {
		return viewEmployerWithEnrolmentStatus;
	}

	public void setViewEmployerWithEnrolmentStatus(String viewEmployerWithEnrolmentStatus) {
		this.viewEmployerWithEnrolmentStatus = viewEmployerWithEnrolmentStatus;
	}

}
