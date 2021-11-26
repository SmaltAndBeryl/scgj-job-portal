package com.cgsc.dto;

/**
 * 
 * @author Prateek Kapoor
 *
 */
public class CandidateDetailsDto 
{

	private int userId;
	private String candidateName;
	private long mobileNumber;
	private long guardianMobileNumber;
	private String gender;
	private String state;
	private String activeFlag;
	private int pollCount;
	private String lastPolledOn;
	private String trainingPartnerName;
	
	/** Getters and Setters**/
	public int getUserId() {
		return userId;
	}
	public String getCandidateName() {
		return candidateName;
	}
	public long getMobileNumber() {
		return mobileNumber;
	}
	public long getGuardianMobileNumber() {
		return guardianMobileNumber;
	}
	public String getGender() {
		return gender;
	}
	public String getState() {
		return state;
	}
	public String getActiveFlag() {
		return activeFlag;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public void setGuardianMobileNumber(long guardianMobileNumber) {
		this.guardianMobileNumber = guardianMobileNumber;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public void setState(String state) {
		this.state = state;
	}
	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}
	public int getPollCount() {
		return pollCount;
	}
	public String getLastPolledOn() {
		return lastPolledOn;
	}
	public void setPollCount(int pollCount) {
		this.pollCount = pollCount;
	}
	public void setLastPolledOn(String lastPolledOn) {
		this.lastPolledOn = lastPolledOn;
	}
	public String getTrainingPartnerName() {
		return trainingPartnerName;
	}
	public void setTrainingPartnerName(String trainingPartnerName) {
		this.trainingPartnerName = trainingPartnerName;
	}
	/**
	 * @author Prateek Kapoor
	 * @param userId
	 * @param candidateName
	 * @param mobileNumber
	 * @param guardianMobileNumber
	 * @param gender
	 * @param state
	 * @param activeFlag
	 * @param pollCount
	 * @param lastPolledOn
	 */
	public CandidateDetailsDto(int userId, String candidateName, long mobileNumber, long guardianMobileNumber,
			String gender, String state, String activeFlag, int pollCount, String lastPolledOn, String trainingPartnerName) {
		super();
		this.userId = userId;
		this.candidateName = candidateName;
		this.mobileNumber = mobileNumber;
		this.guardianMobileNumber = guardianMobileNumber;
		this.gender = gender;
		this.state = state;
		this.activeFlag = activeFlag;
		this.pollCount = pollCount;
		this.lastPolledOn = lastPolledOn;
		this.trainingPartnerName = trainingPartnerName;
	}
	
	/**
	 * Default Constructor
	 */
	public CandidateDetailsDto() {
		super();
		// TODO Auto-generated constructor stub
	}
}
