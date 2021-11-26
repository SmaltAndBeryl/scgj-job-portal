package com.cgsc.dto;

public class CandidateReportDto 
{

	private String candidateName;
	private String state;
	private String pincode;
	private String profileStatus;
	private long aadharNumber;
	private long mobileNumber;
	private String jobRole;
	private String address;
	private String lastLogin;
	private String profileCreatedOn;
	private String qualification;
	private int age;
	private String gender;
	private String experience;
	
	public String getCandidateName() {
		return candidateName;
	}
	public String getState() {
		return state;
	}
	public String getPincode() {
		return pincode;
	}
	public String getProfileStatus() {
		return profileStatus;
	}
	public long getAadharNumber() {
		return aadharNumber;
	}
	public long getMobileNumber() {
		return mobileNumber;
	}
	public String getJobRole() {
		return jobRole;
	}
	public String getAddress() {
		return address;
	}
	public String getLastLogin() {
		return lastLogin;
	}
	public String getProfileCreatedOn() {
		return profileCreatedOn;
	}
	public String getQualification() {
		return qualification;
	}
	public int getAge() {
		return age;
	}
	public String getGender() {
		return gender;
	}
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	public void setState(String state) {
		this.state = state;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public void setProfileStatus(String profileStatus) {
		this.profileStatus = profileStatus;
	}
	public void setAadharNumber(long aadharNumber) {
		this.aadharNumber = aadharNumber;
	}
	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public void setJobRole(String jobRole) {
		this.jobRole = jobRole;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}
	public void setProfileCreatedOn(String profileCreatedOn) {
		this.profileCreatedOn = profileCreatedOn;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getExperience() {
		return experience;
	}
	public void setExperience(String experience) {
		this.experience = experience;
	}
	
	/**
	 * @author Prateek Kapoor
	 * @param candidateName
	 * @param state
	 * @param pincode
	 * @param profileStatus
	 * @param aadharNumber
	 * @param mobileNumber
	 * @param jobRole
	 * @param address
	 * @param lastLogin
	 * @param profileCreatedOn
	 * @param qualification
	 * @param age
	 * @param gender
	 * @param experience
	 */
	public CandidateReportDto(String candidateName, String state, String pincode, String profileStatus,
			long aadharNumber, long mobileNumber, String jobRole, String address, String lastLogin,
			String profileCreatedOn, String qualification, int age, String gender, String experience) {
		super();
		this.candidateName = candidateName;
		this.state = state;
		this.pincode = pincode;
		this.profileStatus = profileStatus;
		this.aadharNumber = aadharNumber;
		this.mobileNumber = mobileNumber;
		this.jobRole = jobRole;
		this.address = address;
		this.lastLogin = lastLogin;
		this.profileCreatedOn = profileCreatedOn;
		this.qualification = qualification;
		this.age = age;
		this.gender = gender;
		this.experience = experience;
	}

	/** 
	 * Default Constructor
	 */
	public CandidateReportDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
