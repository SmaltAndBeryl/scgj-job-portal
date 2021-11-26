package com.cgsc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 
 * @author Sarthak Bhutani
 *
 */

@Component
@PropertySource("classpath:sql/deleteJobPost.yml")
@ConfigurationProperties(prefix="deleteJobPost")
public class DeleteJobPostConfig {

	@Value("${deleteJobPost}")
	private String deleteJobPost;

	@Value("${checkIfAnyCandidateHasAppliedForPublishedJobPost}")
	private String checkIfAnyCandidateHasAppliedForPublishedJobPost;

	public String getDeleteJobPost() {
		return deleteJobPost;
	}

	public void setDeleteJobPost(String deleteJobPost) {
		this.deleteJobPost = deleteJobPost;
	}

	public String getCheckIfAnyCandidateHasAppliedForPublishedJobPost() {
		return checkIfAnyCandidateHasAppliedForPublishedJobPost;
	}

	public void setCheckIfAnyCandidateHasAppliedForPublishedJobPost(String checkIfAnyCandidateHasAppliedForPublishedJobPost) {
		this.checkIfAnyCandidateHasAppliedForPublishedJobPost = checkIfAnyCandidateHasAppliedForPublishedJobPost;
	}
}
