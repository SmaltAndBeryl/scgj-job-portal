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
@PropertySource("classpath:sql/adminDashboard.yml")
@ConfigurationProperties(prefix="adminDashboard")
public class AdminDashboardConfig {

	@Value("${registeredEmployersCount}")
	private String registeredEmployersCount;
	
	@Value("${activeCandidatesCount}")
	private String activeCandidatesCount;

	@Value("${placedCandidatesCount}")
	private String placedCandidatesCount;
	
	@Value("${activeJobPostCount}")
	private String activeJobPostCount;
	
	@Value("${candidatePlacementDetails}")
	private String candidatePlacementDetails;
	
	@Value("${candidateReportDetails}")
	private String candidateReportDetails;
	
	@Value("${companyReportDetails}")
	private String companyReportDetails;
	

	public String getPlacedCandidatesCount() {
		return placedCandidatesCount;
	}

	public void setPlacedCandidatesCount(String placedCandidatesCount) {
		this.placedCandidatesCount = placedCandidatesCount;
	}

	public String getActiveJobPostCount() {
		return activeJobPostCount;
	}

	public void setActiveJobPostCount(String activeJobPostCount) {
		this.activeJobPostCount = activeJobPostCount;
	}

	public String getRegisteredEmployersCount() {
		return registeredEmployersCount;
	}

	public void setRegisteredEmployersCount(String registeredEmployersCount) {
		this.registeredEmployersCount = registeredEmployersCount;
	}

	public String getActiveCandidatesCount() {
		return activeCandidatesCount;
	}

	public void setActiveCandidatesCount(String activeCandidatesCount) {
		this.activeCandidatesCount = activeCandidatesCount;
	}


	public String getCandidatePlacementDetails() {
		return candidatePlacementDetails;
	}

	public void setCandidatePlacementDetails(String candidatePlacementDetails) {
		this.candidatePlacementDetails = candidatePlacementDetails;
	}

	public String getCandidateReportDetails() {
		return candidateReportDetails;
	}

	public void setCandidateReportDetails(String candidateReportDetails) {
		this.candidateReportDetails = candidateReportDetails;
	}

	public String getCompanyReportDetails() {
		return companyReportDetails;
	}

	public void setCompanyReportDetails(String companyReportDetails) {
		this.companyReportDetails = companyReportDetails;
	}

}