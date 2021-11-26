package com.cgsc.dto;

import org.springframework.web.multipart.MultipartFile;

public class EditJobPostsDto
{

	
	private int id;
	private String jobId;
	private String jobTitle;
	private int minSalary;
	private int maxSalary;
	private String state;
	private String district;
	private boolean jobDescriptionUpdated;
	private int vacancy;
	private String minimumExperience;
	private String educationQualification;
	private String jobRole;
	private String applicationDate;
	private String jobSummary;
	private String descriptionDocument;
	private MultipartFile descriptionDocumentFile;
	private String additionalInformation;
	private String leavePolicy;
	private String monthlyIncentives;
	private String workTimings;
	private Long contactNumber;
	private String interviewStartDateTime;
	private String interviewEndDateTime;
	private String isWalkInInterview;
	private String preferredGender;
	private String armyBackgroundPreferred;
	private String cgscCertificatePreferred;
	private String occupation;
	
	public int getId() {
		return id;
	}
	public String getJobId() {
		return jobId;
	}
	public int getVacancy() {
		return vacancy;
	}
	public void setVacancy(int vacancy) {
		this.vacancy = vacancy;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public int getMinSalary() {
		return minSalary;
	}
	public int getMaxSalary() {
		return maxSalary;
	}
	
	public String getState() {
		return state;
	}
	public String getDistrict() {
		return district;
	}

	public boolean getJobDescriptionUpdated() {
		return jobDescriptionUpdated;
	}
	
	public String getMinimumExperience() {
		return minimumExperience;
	}


	public String getEducationQualification() {
		return educationQualification;
	}


	public String getJobRole() {
		return jobRole;
	}


	public String getApplicationDate() {
		return applicationDate;
	}


	public String getJobSummary() {
		return jobSummary;
	}


	public String getDescriptionDocument() {
		return descriptionDocument;
	}


	public String getAdditionalInformation() {
		return additionalInformation;
	}


	public String getLeavePolicy() {
		return leavePolicy;
	}


	public String getMonthlyIncentives() {
		return monthlyIncentives;
	}


	public String getWorkTimings() {
		return workTimings;
	}


	public Long getContactNumber() {
		return contactNumber;
	}


	public String getInterviewStartDateTime() {
		return interviewStartDateTime;
	}


	public String getInterviewEndDateTime() {
		return interviewEndDateTime;
	}


	public String getIsWalkInInterview() {
		return isWalkInInterview;
	}

	public String getPreferredGender() {
		return preferredGender;
	}


	public String getArmyBackgroundPreferred() {
		return armyBackgroundPreferred;
	}


	public void setId(int id) {
		this.id = id;
	}


	public void setJobId(String jobId) {
		this.jobId = jobId;
	}


	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}


	public void setMinSalary(int minSalary) {
		this.minSalary = minSalary;
	}

	public void setMaxSalary(int maxSalary) {
		this.maxSalary = maxSalary;
	}
	
	public void setState(String state) {
		this.state = state;
	}


	public void setDistrict(String district) {
		this.district = district;
	}

	public void setJobDescriptionUpdated(boolean jobDescriptionUpdated) {
		this.jobDescriptionUpdated = jobDescriptionUpdated;
	}

	public void setMinimumExperience(String minimumExperience) {
		this.minimumExperience = minimumExperience;
	}


	public void setEducationQualification(String educationQualification) {
		this.educationQualification = educationQualification;
	}


	public void setJobRole(String jobRole) {
		this.jobRole = jobRole;
	}


	public void setApplicationDate(String applicationDate) {
		this.applicationDate = applicationDate;
	}


	public void setJobSummary(String jobSummary) {
		this.jobSummary = jobSummary;
	}


	public void setDescriptionDocument(String descriptionDocument) {
		this.descriptionDocument = descriptionDocument;
	}


	public void setAdditionalInformation(String additionalInformation) {
		this.additionalInformation = additionalInformation;
	}


	public void setLeavePolicy(String leavePolicy) {
		this.leavePolicy = leavePolicy;
	}


	public void setMonthlyIncentives(String monthlyIncentives) {
		this.monthlyIncentives = monthlyIncentives;
	}


	public void setWorkTimings(String workTimings) {
		this.workTimings = workTimings;
	}


	public void setContactNumber(Long contactNumber) {
		this.contactNumber = contactNumber;
	}


	public void setInterviewStartDateTime(String interviewStartDateTime) {
		this.interviewStartDateTime = interviewStartDateTime;
	}


	public void setInterviewEndDateTime(String interviewEndDateTime) {
		this.interviewEndDateTime = interviewEndDateTime;
	}


	public void setIsWalkInInterview(String isWalkInInterview) {
		this.isWalkInInterview = isWalkInInterview;
	}
	public void setPreferredGender(String preferredGender) {
		this.preferredGender = preferredGender;
	}


	public void setArmyBackgroundPreferred(String armyBackgroundPreferred) {
		this.armyBackgroundPreferred = armyBackgroundPreferred;
	}
	
	public MultipartFile getDescriptionDocumentFile() {
		return descriptionDocumentFile;
	}


	public void setDescriptionDocumentFile(MultipartFile descriptionDocumentFile) {
		this.descriptionDocumentFile = descriptionDocumentFile;
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
	 * @author Prateek Kapoor
	 * @param id
	 * @param jobId
	 * @param jobTitle
	 * @param minSalary
	 * @param maxSalary
	 * @param state
	 * @param district
	 * @param minimumExperience
	 * @param educationQualification
	 * @param jobRole
	 * @param applicationDate
	 * @param jobSummary
	 * @param descriptionDocument
	 * @param additionalInformation
	 * @param leavePolicy
	 * @param monthlyIncentives
	 * @param workTimings
	 * @param contactNumber
	 * @param interviewStartDateTime
	 * @param interviewEndDateTime
	 * @param isWalkInInterview
	 * @param preferredGender
	 * @param armyBackgroundPreferred
	 * @param cgscCertificatePreferred
	 * @param vacancy
	 * @param occupation
	 */
	public EditJobPostsDto(int id, String jobId, String jobTitle, int vacancy,int minSalary,int maxSalary, String state, String district,
			String minimumExperience, String educationQualification, String jobRole, String applicationDate,
			String jobSummary, String descriptionDocument, String additionalInformation, String leavePolicy,
			String monthlyIncentives, String workTimings, Long contactNumber, String interviewStartDateTime,
			String interviewEndDateTime, String isWalkInInterview, String preferredGender,
			String armyBackgroundPreferred, String cgscCertificatePreferred, String occupation) {
		super();
		this.id = id;
		this.jobId = jobId;
		this.jobTitle = jobTitle;
		this.vacancy = vacancy;
		this.minSalary = minSalary;
		this.maxSalary = maxSalary;
		this.state = state;
		this.district = district;
		this.minimumExperience = minimumExperience;
		this.educationQualification = educationQualification;
		this.jobRole = jobRole;
		this.applicationDate = applicationDate;
		this.jobSummary = jobSummary;
		this.descriptionDocument = descriptionDocument;
		this.additionalInformation = additionalInformation;
		this.leavePolicy = leavePolicy;
		this.monthlyIncentives = monthlyIncentives;
		this.workTimings = workTimings;
		this.contactNumber = contactNumber;
		this.interviewStartDateTime = interviewStartDateTime;
		this.interviewEndDateTime = interviewEndDateTime;
		this.isWalkInInterview = isWalkInInterview;
		this.preferredGender = preferredGender;
		this.armyBackgroundPreferred = armyBackgroundPreferred;
		this.cgscCertificatePreferred =cgscCertificatePreferred;
		this.occupation=occupation;
	}

	/**
	 * @author Prateek Kapoor
	 * @param id
	 * @param jobId
	 * @param jobTitle
	 * @param minSalary
	 * @param maxSalary
	 * @param vacancy
	 * @param state
	 * @param district
	 * @param minimumExperience
	 * @param educationQualification
	 * @param jobRole
	 * @param applicationDate
	 * @param jobSummary
	 * @param jobDescriptionUpdated
	 * @param descriptionDocumentFile
	 * @param leavePolicy
	 * @param monthlyIncentives
	 * @param workTimings
	 * @param contactNumber
	 * @param interviewStartDateTime
	 * @param interviewEndDateTime
	 * @param isWalkInInterview
	 * @param preferredGender
	 * @param armyBackgroundPreferred
	 * @param cgscCertificatePreferred
	 * @param occupation
	 */
	public EditJobPostsDto(int id, String jobId, String jobTitle,int vacancy, int minSalary, int maxSalary, String state, String district,
			String minimumExperience, String educationQualification, String jobRole, String applicationDate,
			String jobSummary,boolean jobDescriptionUpdated, MultipartFile descriptionDocumentFile, String leavePolicy, String monthlyIncentives,
			String workTimings, Long contactNumber, String interviewStartDateTime, String interviewEndDateTime,
			String isWalkInInterview, String preferredGender, String armyBackgroundPreferred, String cgscCertificatePreferred) {
		super();
		this.id = id;
		this.jobId = jobId;
		this.jobTitle = jobTitle;
		this.vacancy = vacancy;
		this.minSalary = minSalary;
		this.maxSalary = maxSalary;
		this.state = state;
		this.district = district;
		this.minimumExperience = minimumExperience;
		this.educationQualification = educationQualification;
		this.jobRole = jobRole;
		this.applicationDate = applicationDate;
		this.jobSummary = jobSummary;
		this.jobDescriptionUpdated = jobDescriptionUpdated;
		this.descriptionDocumentFile = descriptionDocumentFile;
		this.leavePolicy = leavePolicy;
		this.monthlyIncentives = monthlyIncentives;
		this.workTimings = workTimings;
		this.contactNumber = contactNumber;
		this.interviewStartDateTime = interviewStartDateTime;
		this.interviewEndDateTime = interviewEndDateTime;
		this.isWalkInInterview = isWalkInInterview;
		this.preferredGender = preferredGender;
		this.armyBackgroundPreferred = armyBackgroundPreferred;
		this.cgscCertificatePreferred = cgscCertificatePreferred;
		this.occupation = occupation;
	}


	/**
	 * Default Constructor
	 */
	public EditJobPostsDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
