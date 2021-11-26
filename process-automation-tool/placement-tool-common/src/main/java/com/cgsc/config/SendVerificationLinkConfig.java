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
@PropertySource("classpath:sql/sendVerificationLink.yml")
@ConfigurationProperties(prefix="candidateVerification")
public class SendVerificationLinkConfig 
{
	
	@Value("${incrementCandidatePollCount}")
	private String incrementCandidatePollCount;

	public String getIncrementCandidatePollCount() {
		return incrementCandidatePollCount;
	}
	public void setIncrementCandidatePollCount(String incrementCandidatePollCount) {
		this.incrementCandidatePollCount = incrementCandidatePollCount;
	}
	

}
