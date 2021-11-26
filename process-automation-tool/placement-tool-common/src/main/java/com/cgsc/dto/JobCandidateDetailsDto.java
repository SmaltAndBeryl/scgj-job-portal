package com.cgsc.dto;

/**
 * 
 * @author Jyoti Singh
 * @since 17-12-2020
 *
 */
public class JobCandidateDetailsDto {
	private int id;
	private String jobTitle;
	private String jobId;
	private String candidateName;
	private long mobileNumber;
	private long guardianNumber;
	private String placementStatus;
	private String descriptionDocumentPath;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescriptionDocumentPath() {
		return descriptionDocumentPath;
	}
	public void setDescriptionDocumentPath(String descriptionDocumentPath) {
		this.descriptionDocumentPath = descriptionDocumentPath;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public String getJobId() {
		return jobId;
	}
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	public String getCandidateName() {
		return candidateName;
	}
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	public long getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public long getGuardianNumber() {
		return guardianNumber;
	}
	public void setGuardianNumber(long guardianNumber) {
		this.guardianNumber = guardianNumber;
	}	
	public String getPlacementStatus() {
		return placementStatus;
	}
	public void setPlacementStatus(String placementStatus) {
		this.placementStatus = placementStatus;
	}
	
	
	/**
	 * @author Jyoti Singh
	 * @param id
	 * @param jobTitle
	 * @param jobId
	 * @param candidateName
	 * @param mobileNumber
	 * @param guardianNumber
	 * @param descriptionDocumentPath
	 * @param jobStatus
	 */
	public JobCandidateDetailsDto(int id,String jobTitle, String jobId, String candidateName, long mobileNumber,
			long guardianNumber,String descriptionDocumentPath, String placementStatus) {
		super();
		this.id=id;
		this.jobTitle = jobTitle;
		this.jobId = jobId;
		this.candidateName = candidateName;
		this.mobileNumber = mobileNumber;
		this.guardianNumber = guardianNumber;
		this.descriptionDocumentPath=descriptionDocumentPath;
		this.placementStatus = placementStatus;
	}
	
	public JobCandidateDetailsDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
