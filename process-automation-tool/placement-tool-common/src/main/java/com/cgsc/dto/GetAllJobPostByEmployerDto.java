package com.cgsc.dto;


/**
 * 
 * @author Sarthak Bhutani
 *
 */
public class GetAllJobPostByEmployerDto {

	private int id;
	private String jobId;
	private String jobTitle;
	private int vacancy;
	private String applicationLastDate;
	private String descriptionDocumentPath;
	private String createdAt;
	private String publishedAt;
	private String jobStatus;
	private String updatedAt;
	private String jobRole;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getJobId() {
		return jobId;
	}
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public int getVacancy() {
		return vacancy;
	}
	public void setVacancy(int vacancy) {
		this.vacancy = vacancy;
	}
	public String getApplicationDate() {
		return applicationLastDate;
	}
	public void setApplicationDate(String applicationDate) {
		this.applicationLastDate = applicationDate;
	}
	public String getDescriptionDocumentPath() {
		return descriptionDocumentPath;
	}
	public void setDescriptionDocumentPath(String descriptionDocumentPath) {
		this.descriptionDocumentPath = descriptionDocumentPath;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public String getPublishedAt() {
		return publishedAt;
	}
	public void setPublishedAt(String publishedAt) {
		this.publishedAt = publishedAt;
	}
	public String getJobStatus() {
		return jobStatus;
	}
	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	public GetAllJobPostByEmployerDto() {
		super();
	}
	public String getJobRole() {
		return jobRole;
	}
	public void setJobRole(String jobRole) {
		this.jobRole = jobRole;
	}
	/**
	 * To view all active job post by employer
	 */
	public GetAllJobPostByEmployerDto(int id, String jobId, String jobTitle, int vacancy, String applicationLastDate,
			String descriptionDocumentPath, String createdAt,String publishedAt, String jobStatus, String updatedAt) {
		  this.id=id;
		  this.jobId=jobId;
		  this.jobTitle=jobTitle;
		  this.vacancy=vacancy;
		  this.applicationLastDate=applicationLastDate;
		  this.descriptionDocumentPath=descriptionDocumentPath;
		  this.createdAt=createdAt;
		  this.jobStatus=jobStatus;
		  this.publishedAt=publishedAt;
		  this.updatedAt=updatedAt;
	}
	
	/**
	 * To view all active job post based on job status by employer
	 */
	public GetAllJobPostByEmployerDto(int id,int vacancy,String jobId,String jobTitle,String descriptionDocumentPath, String applicationLastDate, String publishedAt, String createdAt, String updatedAt, String jobStatus) {
		  this.id=id;
		  this.jobId=jobId;
		  this.jobTitle=jobTitle;
		  this.vacancy=vacancy;
		  this.descriptionDocumentPath=descriptionDocumentPath;
		  this.applicationLastDate=applicationLastDate;
		  this.publishedAt=publishedAt;
		  this.createdAt=createdAt;
		  this.updatedAt=updatedAt;
		  this.jobStatus = jobStatus;
	}
	
	/**
	 * @author Prateek Kapoor
	 * @param id
	 * @param jobId
	 * @param jobTitle
	 * @param jobStatus
	 * @param applicationLastDate
	 */
	public GetAllJobPostByEmployerDto(int id, String jobId, String jobTitle, String jobStatus,String jobRole, String applicationLastDate) {
		super();
		this.id = id;
		this.jobId = jobId;
		this.jobTitle = jobTitle;
		this.jobStatus = jobStatus;
		this.jobRole = jobRole;
		this.applicationLastDate=applicationLastDate;
	}
	
	
}
