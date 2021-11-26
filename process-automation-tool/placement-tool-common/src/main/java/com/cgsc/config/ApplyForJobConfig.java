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
@PropertySource("classpath:sql/applyForJobPost.yml")
@ConfigurationProperties(prefix="applyForJobPost")
public class ApplyForJobConfig 
{

	@Value("${checkJobApplicationExistence}")
	private String checkJobApplicationExistence;

	@Value("${applyForJobPost}")
	private String applyForJobPost;

	public String getCheckJobApplicationExistence() {
		return checkJobApplicationExistence;
	}

	public String getApplyForJobPost() {
		return applyForJobPost;
	}

	public void setCheckJobApplicationExistence(String checkJobApplicationExistence) {
		this.checkJobApplicationExistence = checkJobApplicationExistence;
	}

	public void setApplyForJobPost(String applyForJobPost) {
		this.applyForJobPost = applyForJobPost;
	}
	
}
