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
@PropertySource("classpath:sql/manageCandidates.yml")
@ConfigurationProperties(prefix="manageCandidates")
public class ManageCandidatesConfig
{

	@Value("${viewInactiveCandidates}")
	private String viewInactiveCandidates;

	public String getViewInactiveCandidates() {
		return viewInactiveCandidates;
	}

	public void setViewInactiveCandidates(String viewInactiveCandidates) {
		this.viewInactiveCandidates = viewInactiveCandidates;
	}

}
