package com.cgsc.dto;

public class CompanyReportDto 
{

	private int employerId;
	private String companyName;
	private String panNumber;
	private String liaisiningAuthorityName;
	private String emailAddress;
	private long mobileNumber;
	private String companyAddress;
	private String companyState;
	private String companyPinCode;
	private String profileStatus;
	private String lastLogin;
	private String profileCreatedOn;
	private String jobOccupation;
	private int totalJobsPosted;
	private int openJobs;
	private int closedJobs;
	private String jobDistrict;
	private String jobState;

	
	public int getEmployerId() {
		return employerId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public String getPanNumber() {
		return panNumber;
	}
	public String getLiaisiningAuthorityName() {
		return liaisiningAuthorityName;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public long getMobileNumber() {
		return mobileNumber;
	}
	public String getCompanyAddress() {
		return companyAddress;
	}
	public String getCompanyState() {
		return companyState;
	}
	public String getCompanyPinCode() {
		return companyPinCode;
	}
	public String getProfileStatus() {
		return profileStatus;
	}
	public String getLastLogin() {
		return lastLogin;
	}
	public String getProfileCreatedOn() {
		return profileCreatedOn;
	}
	public String getJobOccupation() {
		return jobOccupation;
	}
	public int getTotalJobsPosted() {
		return totalJobsPosted;
	}
	public int getOpenJobs() {
		return openJobs;
	}
	public int getClosedJobs() {
		return closedJobs;
	}
	public String getJobDistrict() {
		return jobDistrict;
	}
	public String getJobState() {
		return jobState;
	}
	public void setEmployerId(int employerId) {
		this.employerId = employerId;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}
	public void setLiaisiningAuthorityName(String liaisiningAuthorityName) {
		this.liaisiningAuthorityName = liaisiningAuthorityName;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
	public void setCompanyState(String companyState) {
		this.companyState = companyState;
	}
	public void setCompanyPinCode(String companyPinCode) {
		this.companyPinCode = companyPinCode;
	}
	public void setProfileStatus(String profileStatus) {
		this.profileStatus = profileStatus;
	}
	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}
	public void setProfileCreatedOn(String profileCreatedOn) {
		this.profileCreatedOn = profileCreatedOn;
	}
	public void setJobOccupation(String jobOccupation) {
		this.jobOccupation = jobOccupation;
	}
	public void setTotalJobsPosted(int totalJobsPosted) {
		this.totalJobsPosted = totalJobsPosted;
	}
	public void setOpenJobs(int openJobs) {
		this.openJobs = openJobs;
	}
	public void setClosedJobs(int closedJobs) {
		this.closedJobs = closedJobs;
	}
	public void setJobDistrict(String jobDistrict) {
		this.jobDistrict = jobDistrict;
	}
	public void setJobState(String jobState) {
		this.jobState = jobState;
	}
	
	/**
	 * @author Prateek Kapoor
	 * @param employerId
	 * @param companyName
	 * @param panNumber
	 * @param liaisiningAuthorityName
	 * @param emailAddress
	 * @param mobileNumber
	 * @param companyAddress
	 * @param companyState
	 * @param companyPinCode
	 * @param profileStatus
	 * @param lastLogin
	 * @param profileCreatedOn
	 * @param jobOccupation
	 * @param totalJobsPosted
	 * @param openJobs
	 * @param closedJobs
	 * @param jobDistrict
	 * @param jobState
	 */
	public CompanyReportDto(int employerId, String companyName, String panNumber, String liaisiningAuthorityName,
			String emailAddress, long mobileNumber, String companyAddress, String companyState, String companyPinCode,
			String profileStatus, String lastLogin, String profileCreatedOn, String jobOccupation, int totalJobsPosted,
			int openJobs, int closedJobs, String jobDistrict, String jobState) {
		super();
		this.employerId = employerId;
		this.companyName = companyName;
		this.panNumber = panNumber;
		this.liaisiningAuthorityName = liaisiningAuthorityName;
		this.emailAddress = emailAddress;
		this.mobileNumber = mobileNumber;
		this.companyAddress = companyAddress;
		this.companyState = companyState;
		this.companyPinCode = companyPinCode;
		this.profileStatus = profileStatus;
		this.lastLogin = lastLogin;
		this.profileCreatedOn = profileCreatedOn;
		this.jobOccupation = jobOccupation;
		this.totalJobsPosted = totalJobsPosted;
		this.openJobs = openJobs;
		this.closedJobs = closedJobs;
		this.jobDistrict = jobDistrict;
		this.jobState = jobState;
	}
	
	/**
	 * Default Constructor
	 */
	public CompanyReportDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
