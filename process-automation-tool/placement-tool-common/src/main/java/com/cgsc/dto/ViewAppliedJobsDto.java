package com.cgsc.dto;

/**
 * 
 * @author Jyoti Singh
 * @since 16-10-2020
 *
 * @updatedBy Sarthak Bhutani
 * @updatedOn 08/12/2020
 * @update - Added variables - salary, joining, offerLetterPath
 */
public class ViewAppliedJobsDto {
	private int id;
	private String jobId;
	private String jobTitle;
	private String appliedOn;
	private String descriptionDocumentPath;
	private String applicationStatus;
	private String postedBy;
	private String jobStatus;
	private String publishedAt;
	private String updatedOn;
	private String salary;
	private String joiningDate;
	private String offerLetterPath;
	
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
	public String getAppliedOn() {
		return appliedOn;
	}
	public void setAppliedOn(String appliedOn) {
		this.appliedOn = appliedOn;
	}
	public String getDescriptionDocumentPath() {
		return descriptionDocumentPath;
	}
	public void setDescriptionDocumentPath(String descriptionDocumentPath) {
		this.descriptionDocumentPath = descriptionDocumentPath;
	}
	public String getApplicationStatus() {
		return applicationStatus;
	}
	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}
	public String getPostedBy() {
		return postedBy;
	}
	public void setPostedBy(String postedBy) {
		this.postedBy = postedBy;
	}
	public String getJobStatus() {
		return jobStatus;
	}
	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}
	public String getPublishedAt() {
		return publishedAt;
	}
	public void setPublishedAt(String publishedAt) {
		this.publishedAt = publishedAt;
	}
	public String getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(String updatedOn) {
		this.updatedOn = updatedOn;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(String joiningDate) {
		this.joiningDate = joiningDate;
	}

	public String getOfferLetterPath() {
		return offerLetterPath;
	}

	public void setOfferLetterPath(String offerLetterPath) {
		this.offerLetterPath = offerLetterPath;
	}

	/**
	 * @author Jyoti Singh
	 * @param id
	 * @param jobId
	 * @param jobTitle
	 * @param appliedOn
	 * @param descriptionDocumentPath
	 * @param applicationStatus
	 * @param postedBy
	 * @param jobStatus
	 * @param publishedAt
	 * @param updatedOn
	 * @param salary
	 * @param joiningDate
	 * @param offerLetterPath
	 *
	 * @updatedBy Sarthak Bhutani
	 * @updatedOn 08/12/2020
	 * @update - Added fields - salary, joining, offerLetterPath
	 */
	public ViewAppliedJobsDto(int id, String jobId, String jobTitle, String appliedOn, String descriptionDocumentPath,
			String applicationStatus, String postedBy, String jobStatus, String publishedAt, String updatedOn, String salary,
			String joiningDate, String offerLetterPath) {
		super();
		this.id = id;
		this.jobId = jobId;
		this.jobTitle = jobTitle;
		this.appliedOn = appliedOn;
		this.descriptionDocumentPath = descriptionDocumentPath;
		this.applicationStatus = applicationStatus;
		this.postedBy = postedBy;
		this.jobStatus = jobStatus;
		this.publishedAt = publishedAt;
		this.updatedOn = updatedOn;
		this.salary = salary;
		this.joiningDate = joiningDate;
		this.offerLetterPath = offerLetterPath;
	}
	
	
	/**
	 * Default constructor
	 */
	public ViewAppliedJobsDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
