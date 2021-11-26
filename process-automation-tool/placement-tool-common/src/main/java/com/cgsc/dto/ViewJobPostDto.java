package com.cgsc.dto;

/**
 * 
 * @author Sarthak Bhutani
 *
 */
public class ViewJobPostDto {

	public int id;
	public String jobId;
	public String jobTitle;
	public int totalVacancy; 
	public int minSalary;
	public int maxSalary;
	public String state;
	public String district;
	public String minimumExperience;
	public String educationQualification;
	public String jobRole;
	public String applicationDate;
	public String jobSummary;
	public String descriptionDocumentPath;
	public String leavePolicy;
	public String monthlyIncentives;
	public String workTimings;
	public Long contactNumber;
	public String interviewStartDateTime;
	public String interviewEndDateTime;
	public String isWalkinInterview;
	public String preferredGender;
	public String armyBackgroundPreferred;
	public String cgscCertificatePreferred;
	public String postedBy;
	public String publishedAt;
	public String occupation;


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
	public int getTotalVacancy() {
		return totalVacancy;
	}
	public void setTotalVacancy(int totalVacancy) {
		this.totalVacancy = totalVacancy;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getMinimumExperience() {
		return minimumExperience;
	}
	public void setMinimumExperience(String minimumExperience) {
		this.minimumExperience = minimumExperience;
	}
	public String getEducationQualification() {
		return educationQualification;
	}
	public void setEducationQualification(String educationQualification) {
		this.educationQualification = educationQualification;
	}
	public String getJobRole() {
		return jobRole;
	}
	public void setJobRole(String jobRole) {
		this.jobRole = jobRole;
	}
	public String getApplicationDate() {
		return applicationDate;
	}
	public void setApplicationDate(String applicationDate) {
		this.applicationDate = applicationDate;
	}
	public String getJobSummary() {
		return jobSummary;
	}
	public void setJobSummary(String jobSummary) {
		this.jobSummary = jobSummary;
	}
	public String getDescriptionDocumentPath() {
		return descriptionDocumentPath;
	}
	public void setDescriptionDocumentPath(String descriptionDocumentPath) {
		this.descriptionDocumentPath = descriptionDocumentPath;
	}
	public String getLeavePolicy() {
		return leavePolicy;
	}
	public void setLeavePolicy(String leavePolicy) {
		this.leavePolicy = leavePolicy;
	}
	public String getMonthlyIncentives() {
		return monthlyIncentives;
	}
	public void setMonthlyIncentives(String monthlyIncentives) {
		this.monthlyIncentives = monthlyIncentives;
	}
	public String getWorkTimings() {
		return workTimings;
	}
	public void setWorkTimings(String workTimings) {
		this.workTimings = workTimings;
	}
	public Long getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(Long contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getInterviewStartDateTime() {
		return interviewStartDateTime;
	}
	public void setInterviewStartDateTime(String interviewStartDateTime) {
		this.interviewStartDateTime = interviewStartDateTime;
	}
	public String getInterviewEndDateTime() {
		return interviewEndDateTime;
	}
	public void setInterviewEndDateTime(String interviewEndDateTime) {
		this.interviewEndDateTime = interviewEndDateTime;
	}
	public String getIsWalkinInterview() {
		return isWalkinInterview;
	}
	public void setIsWalkinInterview(String isWalkinInterview) {
		this.isWalkinInterview = isWalkinInterview;
	}
	public String getPreferredGender() {
		return preferredGender;
	}
	public void setPreferredGender(String preferredGender) {
		this.preferredGender = preferredGender;
	}
	public String getArmyBackgroundPreferred() {
		return armyBackgroundPreferred;
	}
	public void setArmyBackgroundPreferred(String armyBackgroundPreferred) {
		this.armyBackgroundPreferred = armyBackgroundPreferred;
	}
	public String getPostedBy() {
		return postedBy;
	}
	public void setPostedBy(String postedBy) {
		this.postedBy = postedBy;
	}
	public String getPublishedAt() {
		return publishedAt;
	}
	public void setPublishedAt(String publishedAt) {
		this.publishedAt = publishedAt;
	}
	public int getMinSalary() {
		return minSalary;
	}
	public void setMinSalary(int minSalary) {
		this.minSalary = minSalary;
	}
	public int getMaxSalary() {
		return maxSalary;
	}
	public void setMaxSalary(int maxSalary) {
		this.maxSalary = maxSalary;
	}
	public String getCgscCertificatePreferred() {
		return cgscCertificatePreferred;
	}
	public void setCgscCertificatePreferred(String cgscCertificatePreferred) {
		this.cgscCertificatePreferred = cgscCertificatePreferred;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	/**
	 * @author Sarthak Bhutani
	 * @since 12-10-2020
	 * Default Constructor
	 */
	public ViewJobPostDto() {
		super();
	}
	
	/**
	 * @author Sarthak Bhutani
	 * @since 12-10-2020
	 * Constructor for filling the data via Rowmapper - view all Published & Active job post
	 *
	 * @param id
	 * @param jobId
	 * @param jobTitle
	 * @param totalVacancy
	 * @param minSalary
	 * @param maxSalary
	 * @param state
	 * @param district
	 * @param minimumExperience
	 * @param educationQualification
	 * @param jobRole
	 * @param applicationDate
	 * @param jobSummary
	 * @param descriptionDocumentPath
	 * @param leavePolicy
	 * @param monthlyIncentives
	 * @param workTimings
	 * @param contactNumber
	 * @param interviewStartDateTime
	 * @param interviewEndDateTime
	 * @param isWalkinInterview
	 * @param preferredGender
	 * @param armyBackgroundPreferred
	 * @param postedBy
	 * @param publishedAt
	 * @param cgscCertificatePreferred
	 * 
	 * 
	 * @updated by - Jyoti Singh
	 * @updated on - 22-11-2020
	 * @update - added minSalary, maxSalary in dto
	 * 
	 * @updatedBy Sarthak Bhutani
	 * @updatedOn 27/11/2020
	 * @update added cgscCertificatePreferred in dto
	 *
	 * @updatedBy Sarthak Bhutani
	 * @updatedOn 03/12/2020
	 * @update replaced location with district in dto
	 *
	 * @updatedBy Sarthak Bhutani
	 * @updatedOn 04/12/2020
	 * @update added occupation in dto
	 */
	
	public ViewJobPostDto(int id, String jobId, String jobTitle, int totalVacancy, int minSalary, int maxSalary,
						  String state, String district, String occupation, String minimumExperience, String educationQualification, String jobRole,
						  String applicationDate, String jobSummary, String descriptionDocumentPath, String leavePolicy,
						  String monthlyIncentives, String workTimings, Long contactNumber, String interviewStartDateTime,
						  String interviewEndDateTime, String isWalkinInterview, String preferredGender,
						  String armyBackgroundPreferred, String cgscCertificatePreferred, String postedBy, String publishedAt) {
		 this.id=id;
		 this.jobId=jobId;
		 this.jobTitle=jobTitle;
		 this.totalVacancy=totalVacancy; 
		 this.minSalary = minSalary;
		 this.maxSalary = maxSalary;
		 this.state=state;
		 this.district = district;
		 this.occupation = occupation;
		 this.minimumExperience=minimumExperience;
		 this.educationQualification=educationQualification;
		 this.jobRole=jobRole;
		 this.applicationDate=applicationDate;
		 this.jobSummary=jobSummary;
		 this.descriptionDocumentPath=descriptionDocumentPath;
		 this.leavePolicy=leavePolicy;
		 this.monthlyIncentives=monthlyIncentives;
		 this.workTimings=workTimings;
		 this.contactNumber=contactNumber;
		 this.interviewStartDateTime=interviewStartDateTime;
		 this.interviewEndDateTime=interviewEndDateTime;
		 this.isWalkinInterview=isWalkinInterview;
		 this.preferredGender=preferredGender;
		 this.armyBackgroundPreferred=armyBackgroundPreferred;
		 this.cgscCertificatePreferred=cgscCertificatePreferred;
		 this.postedBy=postedBy;
		 this.publishedAt=publishedAt;
	}
	
}
