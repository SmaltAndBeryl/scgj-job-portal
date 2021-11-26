package com.cgsc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 
 * @author Jyoti Singh
 *
 */
@Component
@PropertySource("classpath:sql/getJobId.yml")
@ConfigurationProperties(prefix="fetchJobId")
public class GetJobIdConfig {

	@Value("${getJobPostId}")
	String getJobPostId;

	public String getGetJobPostId() {
		return getJobPostId;
	}

	public void setGetJobPostId(String getJobPostId) {
		this.getJobPostId = getJobPostId;
	}
	
	
}
