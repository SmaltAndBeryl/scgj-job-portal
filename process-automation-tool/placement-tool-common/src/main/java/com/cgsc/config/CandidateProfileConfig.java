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
@PropertySource("classpath:sql/candidateProfile.yml")
@ConfigurationProperties(prefix="candidateProfile")
public class CandidateProfileConfig 
{

	@Value("${viewCandidateDetails}")
	private String viewCandidateDetails;
	
	@Value("${updateCandidateDetails}")
	private String updateCandidateDetails;
	
	@Value("${documentPaths}")
	private String documentPaths;
	
	@Value("${updateUserDetails}")
	private String updateUserDetails;
	
	
	public String getUpdateUserDetails() {
		return updateUserDetails;
	}

	public void setUpdateUserDetails(String updateUserDetails) {
		this.updateUserDetails = updateUserDetails;
	}

	public String getDocumentPaths() {
		return documentPaths;
	}

	public void setDocumentPaths(String documentPaths) {
		this.documentPaths = documentPaths;
	}

	public String getViewCandidateDetails() {
		return viewCandidateDetails;
	}

	public void setViewCandidateDetails(String viewCandidateDetails) {
		this.viewCandidateDetails = viewCandidateDetails;
	}

	public String getUpdateCandidateDetails() {
		return updateCandidateDetails;
	}

	public void setUpdateCandidateDetails(String updateCandidateDetails) {
		this.updateCandidateDetails = updateCandidateDetails;
	}

}
