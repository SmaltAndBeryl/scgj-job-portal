package com.cgsc.dto;

public class JobApplicationSummaryDto 
{

	private int uniqueId;
	private String jobId;
	private String jobTitle;
	private String gender;
	private int totalNumberOfApplicants;
	private int maleCandidates;
	private int femaleCandidates;
	private int otherCandidates;
	private int exArmyPersonnel;
	private int selected;
	private int rejected;
	private int inReview;
	private int hired;
	private int vacancy;
	private String applicationLastDate;
	private String jobDescriptionDocument;
	private int candidateId;
	private String candidateName;
	private long mobileNumber;
	private String appliedOn;
	private String state;
	private String jobStatus;
	
	
	/** Getters and Setters **/
	public int getUniqueId() {
		return uniqueId;
	}
	public String getJobId() {
		return jobId;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public int getTotalNumberOfApplicants() {
		return totalNumberOfApplicants;
	}
	public int getMaleCandidates() {
		return maleCandidates;
	}
	public int getFemaleCandidates() {
		return femaleCandidates;
	}
	public int getOtherCandidates() {
		return otherCandidates;
	}
	public int getExArmyPersonnel() {
		return exArmyPersonnel;
	}
	public int getSelected() {
		return selected;
	}
	public int getRejected() {
		return rejected;
	}
	public int getInReview() {
		return inReview;
	}
	public int getVacancy() {
		return vacancy;
	}
	public String getApplicationLastDate() {
		return applicationLastDate;
	}
	public String getAppliedOn() {
		return appliedOn;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public void setAppliedOn(String appliedOn) {
		this.appliedOn = appliedOn;
	}
	public String getJobDescriptionDocument() {
		return jobDescriptionDocument;
	}
	public void setUniqueId(int uniqueId) {
		this.uniqueId = uniqueId;
	}
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public void setTotalNumberOfApplicants(int totalNumberOfApplicants) {
		this.totalNumberOfApplicants = totalNumberOfApplicants;
	}
	public void setMaleCandidates(int maleCandidates) {
		this.maleCandidates = maleCandidates;
	}
	public void setFemaleCandidates(int femaleCandidates) {
		this.femaleCandidates = femaleCandidates;
	}
	public void setOtherCandidates(int otherCandidates) {
		this.otherCandidates = otherCandidates;
	}
	public void setExArmyPersonnel(int exArmyPersonnel) {
		this.exArmyPersonnel = exArmyPersonnel;
	}
	public void setSelected(int selected) {
		this.selected = selected;
	}
	public void setRejected(int rejected) {
		this.rejected = rejected;
	}
	public void setInReview(int inReview) {
		this.inReview = inReview;
	}
	public void setVacancy(int vacancy) {
		this.vacancy = vacancy;
	}
	public void setApplicationLastDate(String applicationLastDate) {
		this.applicationLastDate = applicationLastDate;
	}
	public void setJobDescriptionDocument(String jobDescriptionDocument) {
		this.jobDescriptionDocument = jobDescriptionDocument;
	}
	
	public int getCandidateId() {
		return candidateId;
	}
	public String getCandidateName() {
		return candidateName;
	}
	public long getMobileNumber() {
		return mobileNumber;
	}
	public String getState() {
		return state;
	}
	public String getJobStatus() {
		return jobStatus;
	}
	public void setCandidateId(int candidateId) {
		this.candidateId = candidateId;
	}
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public void setState(String state) {
		this.state = state;
	}
	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}

	public int getHired() {
		return hired;
	}

	public void setHired(int hired) {
		this.hired = hired;
	}

	/**
	 * @author Prateek Kapoor
	 * @param uniqueId
	 * @param jobId
	 * @param jobTitle
	 * @param totalNumberOfApplicants
	 * @param maleCandidates
	 * @param femaleCandidates
	 * @param otherCandidates
	 * @param exArmyPersonnel
	 * @param selected
	 * @param rejected
	 * @param inReview
	 * @param vacancy
	 * @param applicationLastDate
	 * @param jobDescriptionDocument
	 * @param hired
	 * @updatedBy Sarthak Bhutani
	 * @updatedOn 19/12/2020
	 * @update - added data member - hired
	 */
	public JobApplicationSummaryDto(int uniqueId, String jobId, String jobTitle, int totalNumberOfApplicants,
			int maleCandidates, int femaleCandidates, int otherCandidates, int exArmyPersonnel,int hired, int selected,
			int rejected, int inReview, int vacancy, String applicationLastDate, String jobDescriptionDocument) {
		super();
		this.uniqueId = uniqueId;
		this.jobId = jobId;
		this.jobTitle = jobTitle;
		this.totalNumberOfApplicants = totalNumberOfApplicants;
		this.maleCandidates = maleCandidates;
		this.femaleCandidates = femaleCandidates;
		this.otherCandidates = otherCandidates;
		this.exArmyPersonnel = exArmyPersonnel;
		this.hired = hired;
		this.selected = selected;
		this.rejected = rejected;
		this.inReview = inReview;
		this.vacancy = vacancy;
		this.applicationLastDate = applicationLastDate;
		this.jobDescriptionDocument = jobDescriptionDocument;
	}
	
	/**
	 * @author Prateek Kapoor
	 * @param candidateId
	 * @param candidateName
	 * @param gender
	 * @param mobileNumber
	 * @param state
	 * @param jobStatus
	 */
	public JobApplicationSummaryDto(int candidateId, String candidateName,String gender ,String appliedOn ,long mobileNumber, String state, String jobStatus) 
	{
		super();
		this.candidateId = candidateId;
		this.candidateName = candidateName;
		this.gender=gender;
		this.appliedOn = appliedOn;
		this.mobileNumber = mobileNumber;
		this.state = state;
		this.jobStatus = jobStatus;
	}
	
	
	/** Default Constructor **/
	public JobApplicationSummaryDto() {
		super();
		// TODO Auto-generated constructor stub
	}

}
