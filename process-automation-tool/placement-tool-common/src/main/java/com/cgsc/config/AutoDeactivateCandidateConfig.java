package com.cgsc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:sql/autoDeactivateCandidate.yml")
@ConfigurationProperties(prefix="autoDeactivateCandidate")
public class AutoDeactivateCandidateConfig {
	
	@Value("${fetchInactiveCandidates}")
	private String fetchInactiveCandidatesSql;
	
	@Value("${deactivateCandidates}")
	private String deactivateCandidatesSql;

	public String getFetchInactiveCandidatesSql() {
		return fetchInactiveCandidatesSql;
	}

	public void setFetchInactiveCandidatesSql(String fetchInactiveCandidatesSql) {
		this.fetchInactiveCandidatesSql = fetchInactiveCandidatesSql;
	}

	public String getDeactivateCandidatesSql() {
		return deactivateCandidatesSql;
	}

	public void setDeactivateCandidatesSql(String deactivateCandidatesSql) {
		this.deactivateCandidatesSql = deactivateCandidatesSql;
	}

}
