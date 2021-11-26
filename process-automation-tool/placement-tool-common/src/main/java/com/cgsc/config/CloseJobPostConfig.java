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
@PropertySource("classpath:sql/closeJobPost.yml")
@ConfigurationProperties(prefix="closeJobPost")
public class CloseJobPostConfig {
	
	@Value("${closeJobPost}")
	private String closeJobPost;

	public String getCloseJobPost() {
		return closeJobPost;
	}

	public void setCloseJobPost(String closeJobPost) {
		this.closeJobPost = closeJobPost;
	}
}
