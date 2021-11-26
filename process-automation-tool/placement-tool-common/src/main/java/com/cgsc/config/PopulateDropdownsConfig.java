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
@PropertySource("classpath:sql/populateDropdowns.yml")
@ConfigurationProperties(prefix="populateDropdowns")
public class PopulateDropdownsConfig
{

	@Value("${getAllStates}")
	private String getAllStates;
	
	@Value("${getIndustryTypes}")
	private String getIndustryTypes;
	
	@Value("${getCompanyType}")
	private String getCompanyType;
	
	@Value("${getCompanyScale}")
	private String getCompanyScale;
	
	@Value("${getEducationQualification}")
	private String getEducationQualification;
	
	@Value("${getProfessionalExperience}")
	private String getProfessionalExperience;
	
	@Value("${getJobRoles}")
	private String getJobRoles;

	@Value("${getJobIdsWithStatus}")
	private String getJobIdsWithStatus;
	
	@Value("${getActiveEmployers}")
	private String getActiveEmployers;
	
	@Value("${getActiveTrainingPartners}")
	private String getActiveTrainingPartners;

	@Value("${getOccupationByJobRole}")
	private String getOccupationByJobRole;
	

	public String getGetActiveEmployers() {
		return getActiveEmployers;
	}
	public void setGetActiveEmployers(String getActiveEmployers) {
		this.getActiveEmployers = getActiveEmployers;
	}
	public String getGetJobIdsWithStatus() {
		return getJobIdsWithStatus;
	}
	public void setGetJobIdsWithStatus(String getJobIdsWithStatus) {
		this.getJobIdsWithStatus = getJobIdsWithStatus;
	}
	public String getGetEducationQualification() {
		return getEducationQualification;
	}
	public String getGetProfessionalExperience() {
		return getProfessionalExperience;
	}
	public void setGetEducationQualification(String getEducationQualification) {
		this.getEducationQualification = getEducationQualification;
	}
	public void setGetProfessionalExperience(String getProfessionalExperience) {
		this.getProfessionalExperience = getProfessionalExperience;
	}
	public String getGetCompanyType() {
		return getCompanyType;
	}
	public void setGetCompanyType(String getCompanyType) {
		this.getCompanyType = getCompanyType;
	}
	public String getGetIndustryTypes() {
		return getIndustryTypes;
	}
	public void setGetIndustryTypes(String getIndustryTypes) {
		this.getIndustryTypes = getIndustryTypes;
	}
	public String getGetAllStates() {
		return getAllStates;
	}
	public void setGetAllStates(String getAllStates) {
		this.getAllStates = getAllStates;
	}
	public String getGetCompanyScale() {
		return getCompanyScale;
	}
	public void setGetCompanyScale(String getCompanyScale) {
		this.getCompanyScale = getCompanyScale;
	}
	public String getGetJobRoles() {
		return getJobRoles;
	}
	public void setGetJobRoles(String getJobRoles) {
		this.getJobRoles = getJobRoles;
	}
	public String getGetActiveTrainingPartners() {
		return getActiveTrainingPartners;
	}
	public void setGetActiveTrainingPartners(String getActiveTrainingPartners) {
		this.getActiveTrainingPartners = getActiveTrainingPartners;
	}
	public String getGetOccupationByJobRole() {
		return getOccupationByJobRole;
	}
	public void setGetOccupationByJobRole(String getOccupationByJobRole) {
		this.getOccupationByJobRole = getOccupationByJobRole;
	}
}