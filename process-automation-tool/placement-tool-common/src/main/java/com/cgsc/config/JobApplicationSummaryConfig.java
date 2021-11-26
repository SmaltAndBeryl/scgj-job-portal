package com.cgsc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:sql/viewJobApplicants.yml")
@ConfigurationProperties(prefix="viewJobApplicants")
public class JobApplicationSummaryConfig 
{
	@Value("${viewJobApplicationSummary}")
	private String viewJobApplicationSummary;
	
	@Value("${viewCandidateJobSummary}")
	private String viewCandidateJobSummary;

	public String getViewJobApplicationSummary() {
		return viewJobApplicationSummary;
	}
	public void setViewJobApplicationSummary(String viewJobApplicationSummary) {
		this.viewJobApplicationSummary = viewJobApplicationSummary;
	}
	public String getViewCandidateJobSummary() {
		return viewCandidateJobSummary;
	}
	public void setViewCandidateJobSummary(String viewCandidateJobSummary) {
		this.viewCandidateJobSummary = viewCandidateJobSummary;
	}
}
