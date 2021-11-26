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
@PropertySource("classpath:sql/createJobPost.yml")
@ConfigurationProperties(prefix="createJobPostings")
public class CreateJobPostConfig 
{
	@Value("${totalJobPostingCount}")
	private String totalJobPostingCount;

	@Value("${insertJobPost}")
	private String insertJobPost;
	
	public String getTotalJobPostingCount() {
		return totalJobPostingCount;
	}
	public void setTotalJobPostingCount(String totalJobPostingCount) {
		this.totalJobPostingCount = totalJobPostingCount;
	}
	public String getInsertJobPost() {
		return insertJobPost;
	}
	public void setInsertJobPost(String insertJobPost) {
		this.insertJobPost = insertJobPost;
	}
}
