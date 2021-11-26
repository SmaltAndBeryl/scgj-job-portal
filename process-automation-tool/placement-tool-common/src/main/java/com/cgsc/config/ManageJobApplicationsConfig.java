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
@PropertySource("classpath:sql/manageJobApplications.yml")
@ConfigurationProperties(prefix="manageJobApplications")
public class ManageJobApplicationsConfig 
{

	@Value("${viewJobApplicants}")
	private String viewJobApplicants;
	@Value("${updateJobApplicationStatus}")
	private String updateJobApplicationStatus;
	@Value("${checkRemainingVacancy}")
	private String checkRemainingVacancy;
	@Value("${fetchMinimumSalary}")
	private String fetchMinimumSalarySql;
	
	
	public String getFetchMinimumSalarySql() {
		return fetchMinimumSalarySql;
	}
	public void setFetchMinimumSalarySql(String fetchMinimumSalarySql) {
		this.fetchMinimumSalarySql = fetchMinimumSalarySql;
	}
	public String getCheckRemainingVacancy() {
		return checkRemainingVacancy;
	}
	public void setCheckRemainingVacancy(String checkRemainingVacancy) {
		this.checkRemainingVacancy = checkRemainingVacancy;
	}
	public String getViewJobApplicants() {
		return viewJobApplicants;
	}
	public String getUpdateJobApplicationStatus() {
		return updateJobApplicationStatus;
	}
	public void setViewJobApplicants(String viewJobApplicants) {
		this.viewJobApplicants = viewJobApplicants;
	}
	public void setUpdateJobApplicationStatus(String updateJobApplicationStatus) {
		this.updateJobApplicationStatus = updateJobApplicationStatus;
	}
	
}
