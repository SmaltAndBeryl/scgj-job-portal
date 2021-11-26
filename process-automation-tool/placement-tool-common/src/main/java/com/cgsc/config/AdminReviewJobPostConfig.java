package com.cgsc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:sql/adminReviewJobPost.yml")
@ConfigurationProperties(prefix="reviewJobPost")
public class AdminReviewJobPostConfig {
	
	@Value("${getJobPostByApprovalStatus}")
	private String getJobPostByApprovalStatusSql;

	@Value("${updateJobPostApprovalStatus}")
	private String updateJobPostApprovalStatusSql;
	
	@Value("${insertAdminComments}")
	private String insertAdminComments;
	
	
	
	public String getInsertAdminComments() {
		return insertAdminComments;
	}

	public void setInsertAdminComments(String insertAdminComments) {
		this.insertAdminComments = insertAdminComments;
	}

	public String getGetJobPostByApprovalStatusSql() {
		return getJobPostByApprovalStatusSql;
	}

	public void setGetJobPostByApprovalStatusSql(String getJobPostByApprovalStatusSql) {
		this.getJobPostByApprovalStatusSql = getJobPostByApprovalStatusSql;
	}

	public String getUpdateJobPostApprovalStatusSql() {
		return updateJobPostApprovalStatusSql;
	}

	public void setUpdateJobPostApprovalStatusSql(String updateJobPostApprovalStatusSql) {
		this.updateJobPostApprovalStatusSql = updateJobPostApprovalStatusSql;
	}

	
	
	
}
