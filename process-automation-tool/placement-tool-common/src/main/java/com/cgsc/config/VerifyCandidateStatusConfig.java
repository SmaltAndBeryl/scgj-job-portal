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
@PropertySource("classpath:sql/verifyCandidateStatus.yml")
@ConfigurationProperties(prefix="verifyCandidateStatus")
public class VerifyCandidateStatusConfig {

	
	@Value("${updateCandidateVerificationStatus}")
	private String updateCandidateVerificationStatus;
	
	
	@Value("${checkCandidatePollCount}")
	private String checkCandidatePollCount;

	public String getCheckCandidatePollCount() {
		return checkCandidatePollCount;
	}

	public void setCheckCandidatePollCount(String checkCandidatePollCount) {
		this.checkCandidatePollCount = checkCandidatePollCount;
	}

	public String getUpdateCandidateVerificationStatus() {
		return updateCandidateVerificationStatus;
	}

	public void setUpdateCandidateVerificationStatus(String updateCandidateVerificationStatus) {
		this.updateCandidateVerificationStatus = updateCandidateVerificationStatus;
	}
	
	
	
}
