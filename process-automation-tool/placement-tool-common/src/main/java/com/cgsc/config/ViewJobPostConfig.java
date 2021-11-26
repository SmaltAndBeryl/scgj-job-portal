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
@PropertySource("classpath:sql/viewJobPost.yml")
@ConfigurationProperties(prefix="viewJobPost")
public class ViewJobPostConfig {

	@Value("${viewUpcomingJobs}")
	private String viewUpcomingJobs;
	
	@Value("${getAllJobPostByEmployer}")
	private String getAllJobPostByEmployer;
	
	@Value("${getAllJDeletedJobPostByEmployer}")
	private String getAllJDeletedJobPostByEmployer;
	
	@Value("${viewClosedOrPublishedJobPosts}")
	private String viewClosedOrPublishedJobPosts;

	@Value("${viewJobPostByStatusForPublishJobPostPage}")
	private String viewJobPostByStatusForPublishJobPostPage;

	@Value("${viewJobPostByStatusForCloseJobPostPage}")
	private String viewJobPostByStatusForCloseJobPostPage;
	
	public String getViewClosedOrPublishedJobPosts() {
		return viewClosedOrPublishedJobPosts;
	}

	public void setViewClosedOrPublishedJobPosts(String viewClosedOrPublishedJobPosts) {
		this.viewClosedOrPublishedJobPosts = viewClosedOrPublishedJobPosts;
	}

	public String getGetAllJobPostByEmployer() {
		return getAllJobPostByEmployer;
	}

	public void setGetAllJobPostByEmployer(String getAllJobPostByEmployer) {
		this.getAllJobPostByEmployer = getAllJobPostByEmployer;
	}

	public String viewUpcomingJobs() {
		return viewUpcomingJobs;
	}

	public void setViewUpcomingJobs(String viewUpcomingJobs) {
		this.viewUpcomingJobs = viewUpcomingJobs;
	}

	public String getGetAllJDeletedJobPostByEmployer() {
		return getAllJDeletedJobPostByEmployer;
	}

	public void setGetAllJDeletedJobPostByEmployer(String getAllJDeletedJobPostByEmployer) {
		this.getAllJDeletedJobPostByEmployer = getAllJDeletedJobPostByEmployer;
	}

	public String getViewJobPostByStatusForPublishJobPostPage() {
		return viewJobPostByStatusForPublishJobPostPage;
	}

	public void setViewJobPostByStatusForPublishJobPostPage(String viewJobPostByStatusForPublishJobPostPage) {
		this.viewJobPostByStatusForPublishJobPostPage = viewJobPostByStatusForPublishJobPostPage;
	}

	public String getViewJobPostByStatusForCloseJobPostPage() {
		return viewJobPostByStatusForCloseJobPostPage;
	}

	public void setViewJobPostByStatusForCloseJobPostPage(String viewJobPostByStatusForCloseJobPostPage) {
		this.viewJobPostByStatusForCloseJobPostPage = viewJobPostByStatusForCloseJobPostPage;
	}
}
