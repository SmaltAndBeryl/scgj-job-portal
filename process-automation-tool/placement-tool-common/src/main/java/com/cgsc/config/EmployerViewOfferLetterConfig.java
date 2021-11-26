package com.cgsc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:sql/employerViewOfferLetter.yml")
@ConfigurationProperties(prefix="viewOfferLetter")
public class EmployerViewOfferLetterConfig {

	@Value("${fetchHiredCandidateDetails}")
	private String fetchHiredCandidateDetailsSql;

	public String getFetchHiredCandidateDetailsSql() {
		return fetchHiredCandidateDetailsSql;
	}

	public void setFetchHiredCandidateDetailsSql(String fetchHiredCandidateDetailsSql) {
		this.fetchHiredCandidateDetailsSql = fetchHiredCandidateDetailsSql;
	}
	
	
}
