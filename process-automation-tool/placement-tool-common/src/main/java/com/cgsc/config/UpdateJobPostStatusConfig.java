package com.cgsc.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:sql/updateJobPostStatus.yml")
@ConfigurationProperties(prefix="updateJobPosts")
public class UpdateJobPostStatusConfig 
{

	@Value("${publishJobPost}")
	private String publishJobPost;
	@Value("${updateJobPostsStatus}")
	private String updateJobPostsStatus;

	public String getPublishJobPost() {
		return publishJobPost;
	}

	public String getUpdateJobPostsStatus() {
		return updateJobPostsStatus;
	}

	public void setPublishJobPost(String publishJobPost) {
		this.publishJobPost = publishJobPost;
	}

	public void setUpdateJobPostsStatus(String updateJobPostsStatus) {
		this.updateJobPostsStatus = updateJobPostsStatus;
	}
}
