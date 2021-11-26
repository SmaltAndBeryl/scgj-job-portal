package com.cgsc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:sql/fetchCandidateDetails.yml")
@ConfigurationProperties(prefix="fetchCandidateDetails")
public class FetchCandidateDetailsUtilityConfig 
{

	@Value("${viewCandidateInformation}")
	private String viewCandidateInformation;

	public String getViewCandidateInformation() {
		return viewCandidateInformation;
	}
	public void setViewCandidateInformation(String viewCandidateInformation) {
		this.viewCandidateInformation = viewCandidateInformation;
	}
}
