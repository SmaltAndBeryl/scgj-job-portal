package com.cgsc.dto;

public class ViewJobApplicationsDto 
{

	private int candidateId;
	private String candidateName;
	private long candidateMobileNumber;
	private String state;
	private String certificatePath;
	private String resumePath;
	private String appliedOn;
	private String applicationStatus;
	private String educationQualification;
	private String professionalExperience;
	private String gender;
	private long guardianMobileNumber;
	private String defenceBackground;
	private String updatedOn;
	private String isCgscCertified;
	private String cgscCertificateNumber;
	private long adharNumber;
	private String jobRole;
	private String address;
	private String trainingPartner;
	
	
	public long getAdharNumber() {
		return adharNumber;
	}
	public void setAdharNumber(long adharNumber) {
		this.adharNumber = adharNumber;
	}
	public String getJobRole() {
		return jobRole;
	}
	public void setJobRole(String jobRole) {
		this.jobRole = jobRole;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	/** Getters and Setters **/
	public int getCandidateId() {
		return candidateId;
	}
	public String getCandidateName() {
		return candidateName;
	}
	public long getCandidateMobileNumber() {
		return candidateMobileNumber;
	}
	public String getState() {
		return state;
	}
	public String getCertificatePath() {
		return certificatePath;
	}
	public String getResumePath() {
		return resumePath;
	}
	public String getAppliedOn() {
		return appliedOn;
	}
	public String getApplicationStatus() {
		return applicationStatus;
	}
	public String getEducationQualification() {
		return educationQualification;
	}
	public String getProfessionalExperience() {
		return professionalExperience;
	}
	public String getGender() {
		return gender;
	}
	public long getGuardianMobileNumber() {
		return guardianMobileNumber;
	}
	public void setCandidateId(int candidateId) {
		this.candidateId = candidateId;
	}
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	public void setCandidateMobileNumber(long candidateMobileNumber) {
		this.candidateMobileNumber = candidateMobileNumber;
	}
	public void setState(String state) {
		this.state = state;
	}
	public void setCertificatePath(String certificatePath) {
		this.certificatePath = certificatePath;
	}
	public void setResumePath(String resumePath) {
		this.resumePath = resumePath;
	}
	public void setAppliedOn(String appliedOn) {
		this.appliedOn = appliedOn;
	}
	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}
	public void setEducationQualification(String educationQualification) {
		this.educationQualification = educationQualification;
	}
	public void setProfessionalExperience(String professionalExperience) {
		this.professionalExperience = professionalExperience;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public void setGuardianMobileNumber(long guardianMobileNumber) {
		this.guardianMobileNumber = guardianMobileNumber;
	}	
	public String getDefenceBackground() {
		return defenceBackground;
	}
	public void setDefenceBackground(String defenceBackground) {
		this.defenceBackground = defenceBackground;
	}
	public String getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(String updatedOn) {
		this.updatedOn = updatedOn;
	}
	public String getIsCgscCertified() {
		return isCgscCertified;
	}
	public void setIsCgscCertified(String isCgscCertified) {
		this.isCgscCertified = isCgscCertified;
	}
	public String getCgscCertificateNumber() {
		return cgscCertificateNumber;
	}
	public void setCgscCertificateNumber(String cgscCertificateNumber) {
		this.cgscCertificateNumber = cgscCertificateNumber;
	}

	public String getTrainingPartner() {
		return trainingPartner;
	}

	public void setTrainingPartner(String trainingPartner) {
		this.trainingPartner = trainingPartner;
	}

	/**
	 * @author Prateek Kapoor
	 * @param candidateId
	 * @param candidateName
	 * @param candidateMobileNumber
	 * @param state
	 * @param certificatePath
	 * @param resumePath
	 * @param appliedOn
	 * @param applicationStatus
	 * @param educationQualification
	 * @param professionalExperience
	 * @param gender
	 * @param guardianMobileNumber
	 * @param defenceBackground
	 * @param updatedOn
	 * @param isCgscCertified
	 * @param cgscCertificateNumber
	 * 
	 * @updated by - Jyoti Singh
	 * @updated on- 7-12-2020
	 * @added params -
	 * @param address
	 * @param adharNumber
	 * @param jobRole
	 *
	 * @updatedBy Sarthak Bhtani
	 * @updatedOn 23/12/2020
	 * @update Added param trainingPartner
	 * @param trainingPartner
	 */
	public ViewJobApplicationsDto(int candidateId, String candidateName, long candidateMobileNumber, String state,
			String certificatePath, String resumePath, String appliedOn, String applicationStatus,
			String educationQualification, String professionalExperience, String gender, long guardianMobileNumber, String defenceBackground, String updatedOn,
			String isCgscCertified, String cgscCertificateNumber,String address,String jobRole, long adharNumber,String trainingPartner) {
		super();
		this.candidateId = candidateId;
		this.candidateName = candidateName;
		this.candidateMobileNumber = candidateMobileNumber;
		this.state = state;
		this.certificatePath = certificatePath;
		this.resumePath = resumePath;
		this.appliedOn = appliedOn;
		this.applicationStatus = applicationStatus;
		this.educationQualification = educationQualification;
		this.professionalExperience = professionalExperience;
		this.gender = gender;
		this.guardianMobileNumber = guardianMobileNumber;
		this.defenceBackground = defenceBackground;
		this.updatedOn = updatedOn;
		this.isCgscCertified = isCgscCertified;
		this.cgscCertificateNumber = cgscCertificateNumber;
		this.address = address;
		this.adharNumber = adharNumber;
		this.jobRole = jobRole;
		this.trainingPartner=trainingPartner;
	}
	
	/** Default Constructor **/
	public ViewJobApplicationsDto()
	{
		super();
		// TODO Auto-generated constructor stub
	}
	
}
