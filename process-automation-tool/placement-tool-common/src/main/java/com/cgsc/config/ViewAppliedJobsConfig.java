package com.cgsc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 
 * @author Jyoti Singh
 * @since 16-10-2020
 *
 */
@Component
@PropertySource("classpath:sql/candidateViewAppliedJobs.yml")
@ConfigurationProperties(prefix="viewAppliedJobs")
public class ViewAppliedJobsConfig {
	@Value("${viewAppliedJobs}")
	private String viewAppliedJobs;

	public String getViewAppliedJobs() {
		return viewAppliedJobs;
	}

	public void setViewAppliedJobs(String viewAppliedJobs) {
		this.viewAppliedJobs = viewAppliedJobs;
	}
	
}
