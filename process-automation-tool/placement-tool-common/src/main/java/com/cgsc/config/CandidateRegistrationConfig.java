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
@PropertySource("classpath:sql/registerCandidate.yml")
@ConfigurationProperties(prefix="candidateRegistration")
public class CandidateRegistrationConfig
{

	@Value("${generateCandidateCredentials}")
	private String generateCandidateCredentials;
	
	@Value("${insertCandidateDetails}")
	private String insertCandidateDetails;

	public String getGenerateCandidateCredentials() {
		return generateCandidateCredentials;
	}

	public String getInsertCandidateDetails() {
		return insertCandidateDetails;
	}

	public void setGenerateCandidateCredentials(String generateCandidateCredentials) {
		this.generateCandidateCredentials = generateCandidateCredentials;
	}

	public void setInsertCandidateDetails(String insertCandidateDetails) {
		this.insertCandidateDetails = insertCandidateDetails;
	}	
}
