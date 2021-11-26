package com.cgsc.dto;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

import com.cgsc.common.BaseDto;

public class CreateJobPostDto extends BaseDto
{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String jobTitle;
	public int totalVacancy;
	public int minSalary;
	public int maxSalary;
	public String state;
	public String district;
	public String minimumExperience;
	public String educationQualification;
	public String jobRole;
	public String occupation;
	public Date applicationDate;
	public String jobSummary;
	public MultipartFile descriptionDocument;
	public String leavePolicy;
	public String monthlyIncentives;
	public String workTimmings;
	public Long contactNumber;
	public String interviewStartDateTime;
	public String interviewEndDateTime;
	public String isWalkinInterview;
	public String preferredGender;
	public String armyBackgroundPreferred;
	public String cgscCertificatePreferred;
	
	/** Getters and Setters**/
	public String getJobTitle() {
		return jobTitle;
	}
	public int getTotalVacancy() {
		return totalVacancy;
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
	public String getMinimumExperience() {
		return minimumExperience;
	}
	public String getEducationQualification() {
		return educationQualification;
	}
	public String getJobRole() {
		return jobRole;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public Date getApplicationDate() {
		return applicationDate;
	}
	public String getJobSummary() {
		return jobSummary;
	}
	public MultipartFile getDescriptionDocument() {
		return descriptionDocument;
	}
	public String getLeavePolicy() {
		return leavePolicy;
	}
	public String getMonthlyIncentives() {
		return monthlyIncentives;
	}
	public String getWorkTimmings() {
		return workTimmings;
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
	public String getIsWalkinInterview() {
		return isWalkinInterview;
	}
	public String getPreferredGender() {
		return preferredGender;
	}
	public String getArmyBackgroundPreferred() {
		return armyBackgroundPreferred;
	}
	
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public void setTotalVacancy(int totalVacancy) {
		this.totalVacancy = totalVacancy;
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
	public void setMinimumExperience(String minimumExperience) {
		this.minimumExperience = minimumExperience;
	}
	public void setEducationQualification(String educationQualification) {
		this.educationQualification = educationQualification;
	}
	public void setJobRole(String jobRole) {
		this.jobRole = jobRole;
	}
	public void setApplicationDate(Date applicationDate) {
		this.applicationDate = applicationDate;
	}
	public void setJobSummary(String jobSummary) {
		this.jobSummary = jobSummary;
	}
	public void setDescriptionDocument(MultipartFile descriptionDocument) {
		this.descriptionDocument = descriptionDocument;
	}
	public void setLeavePolicy(String leavePolicy) {
		this.leavePolicy = leavePolicy;
	}
	public void setMonthlyIncentives(String monthlyIncentives) {
		this.monthlyIncentives = monthlyIncentives;
	}
	public void setWorkTimmings(String workTimmings) {
		this.workTimmings = workTimmings;
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
	public void setIsWalkinInterview(String isWalkinInterview) {
		this.isWalkinInterview = isWalkinInterview;
	}
	public void setPreferredGender(String preferredGender) {
		this.preferredGender = preferredGender;
	}
	public void setArmyBackgroundPreferred(String armyBackgroundPreferred) {
		this.armyBackgroundPreferred = armyBackgroundPreferred;
	}
	
	public String getCgscCertificatePreferred() {
		return cgscCertificatePreferred;
	}
	public void setCgscCertificatePreferred(String cgscCertificatePreferred) {
		this.cgscCertificatePreferred = cgscCertificatePreferred;
	}
	/**
	 * @author Prateek Kapoor
	 * @param jobTitle
	 * @param totalVacancy
	 * @param minSalary
	 * @param maxSalary
	 * @param state
	 * @param district
	 * @param minimumExperience
	 * @param educationQualification
	 * @param jobRole
	 * @param occupation
	 * @param applicationDate
	 * @param jobSummary
	 * @param descriptionDocument
	 * @param leavePolicy
	 * @param monthlyIncentives
	 * @param workTimmings
	 * @param contactNumber
	 * @param interviewStartDateTime
	 * @param interviewEndDateTime
	 * @param isWalkinInterview
	 * @param preferredGender
	 * @param armyBackgroundPreferred
	 * @param
	 *
	 * @updatedBy Sarthak Bhutani
	 * @updatedOn 04/11/2020
	 * @update Added field occupation in dto
	 */
	public CreateJobPostDto(String jobTitle, int totalVacancy, int minSalary, int maxSalary, String state, String district,
			String minimumExperience, String educationQualification, String jobRole, String occupation, Date applicationDate,
			String jobSummary, MultipartFile descriptionDocument, String leavePolicy, String monthlyIncentives,
			String workTimmings, Long contactNumber, String interviewStartDateTime, String interviewEndDateTime,
			String isWalkinInterview, String preferredGender, String armyBackgroundPreferred, String cgscCertificatePreferred) {
		super();
		this.jobTitle = jobTitle;
		this.totalVacancy = totalVacancy;
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
		this.leavePolicy = leavePolicy;
		this.monthlyIncentives = monthlyIncentives;
		this.workTimmings = workTimmings;
		this.contactNumber = contactNumber;
		this.interviewStartDateTime = interviewStartDateTime;
		this.interviewEndDateTime = interviewEndDateTime;
		this.isWalkinInterview = isWalkinInterview;
		this.preferredGender = preferredGender;
		this.armyBackgroundPreferred = armyBackgroundPreferred;
		this.cgscCertificatePreferred = cgscCertificatePreferred;
	}
	
	/**
	 * Default Constructor
	 */
	public CreateJobPostDto() {
		super();
		// TODO Auto-generated constructor stub
	}
}
