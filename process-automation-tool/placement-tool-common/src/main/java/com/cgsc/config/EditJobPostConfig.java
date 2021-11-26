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
@PropertySource("classpath:sql/editJobPost.yml")
@ConfigurationProperties(prefix="editJobPosts")
public class EditJobPostConfig 
{

	@Value("${viewJobPosts}")
	private String viewJobPosts;

	@Value("${updateJobPostDetails}")
	private String updateJobPostDetails;
	
	@Value("${fetchDescriptionDocumentPath}")
	private String fetchDescriptionDocumentPath;
	
	
	public String getUpdateJobPostDetails() {
		return updateJobPostDetails;
	}

	public String getFetchDescriptionDocumentPath() {
		return fetchDescriptionDocumentPath;
	}

	public void setUpdateJobPostDetails(String updateJobPostDetails) {
		this.updateJobPostDetails = updateJobPostDetails;
	}

	public void setFetchDescriptionDocumentPath(String fetchDescriptionDocumentPath) {
		this.fetchDescriptionDocumentPath = fetchDescriptionDocumentPath;
	}

	public String getViewJobPosts() {
		return viewJobPosts;
	}

	public void setViewJobPosts(String viewJobPosts) {
		this.viewJobPosts = viewJobPosts;
	}	
}
