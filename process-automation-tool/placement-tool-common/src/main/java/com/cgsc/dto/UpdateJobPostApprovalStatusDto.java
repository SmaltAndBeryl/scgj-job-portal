package com.cgsc.dto;

/**
 * Author Jyoti Singh
 */
public class UpdateJobPostApprovalStatusDto {

	private int jobId;
	private String updatedStatus;
	private String adminComments;
	
	

	public int getJobId() {
		return jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	public String getUpdatedStatus() {
		return updatedStatus;
	}
	public void setUpdatedStatus(String updatedStatus) {
		this.updatedStatus = updatedStatus;
	}
	public String getAdminComments() {
		return adminComments;
	}
	public void setAdminComments(String adminComments) {
		this.adminComments = adminComments;
	}
	
	/**
	 * Default constructor
	 */
	public UpdateJobPostApprovalStatusDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
