package com.cgsc.dto;

public class HiredCandidateDetailsDto {
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
	private String hiredOn;
	private String isCgscCertified;
	private String cgscCertificateNumber;
	private long adharNumber;
	private String jobRole;
	private String address;
	private String joiningDate;
	private int salaryOffered;
	private String offerLetterPath;
	private String trainingPartner;
	
	public int getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(int candidateId) {
		this.candidateId = candidateId;
	}
	public String getCandidateName() {
		return candidateName;
	}
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	public long getCandidateMobileNumber() {
		return candidateMobileNumber;
	}
	public void setCandidateMobileNumber(long candidateMobileNumber) {
		this.candidateMobileNumber = candidateMobileNumber;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCertificatePath() {
		return certificatePath;
	}
	public void setCertificatePath(String certificatePath) {
		this.certificatePath = certificatePath;
	}
	public String getResumePath() {
		return resumePath;
	}
	public void setResumePath(String resumePath) {
		this.resumePath = resumePath;
	}
	public String getAppliedOn() {
		return appliedOn;
	}
	public void setAppliedOn(String appliedOn) {
		this.appliedOn = appliedOn;
	}
	public String getApplicationStatus() {
		return applicationStatus;
	}
	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}
	public String getEducationQualification() {
		return educationQualification;
	}
	public void setEducationQualification(String educationQualification) {
		this.educationQualification = educationQualification;
	}
	public String getProfessionalExperience() {
		return professionalExperience;
	}
	public void setProfessionalExperience(String professionalExperience) {
		this.professionalExperience = professionalExperience;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public long getGuardianMobileNumber() {
		return guardianMobileNumber;
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
	public String getHiredOn() {
		return hiredOn;
	}
	public void setHiredOn(String hiredOn) {
		this.hiredOn = hiredOn;
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
	public String getJoiningDate() {
		return joiningDate;
	}
	public void setJoiningDate(String joiningDate) {
		this.joiningDate = joiningDate;
	}
	public int getSalaryOffered() {
		return salaryOffered;
	}
	public void setSalaryOffered(int salaryOffered) {
		this.salaryOffered = salaryOffered;
	}
	public String getOfferLetterPath() {
		return offerLetterPath;
	}
	public void setOfferLetterPath(String offerLetterPath) {
		this.offerLetterPath = offerLetterPath;
	}
	
	
	public String getTrainingPartner() {
		return trainingPartner;
	}
	public void setTrainingPartner(String trainingPartner) {
		this.trainingPartner = trainingPartner;
	}
	/**
	 * @author Jyoti Singh
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
	 * @param hiredOn
	 * @param isCgscCertified
	 * @param cgscCertificateNumber
	 * @param adharNumber
	 * @param jobRole
	 * @param address
	 * @param joiningDate
	 * @param salaryOffered
	 * @param offerLetterPath
	 * @param trainingPartner
	 */
	
	public HiredCandidateDetailsDto(int candidateId, String candidateName, long candidateMobileNumber, String state,
			String certificatePath, String resumePath, String appliedOn, String applicationStatus,
			String educationQualification, String professionalExperience, String gender, long guardianMobileNumber,
			String defenceBackground, String hiredOn, String isCgscCertified, String cgscCertificateNumber,
			long adharNumber, String jobRole, String address, String joiningDate, int salaryOffered,
			String offerLetterPath, String trainingPartner) {
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
		this.hiredOn = hiredOn;
		this.isCgscCertified = isCgscCertified;
		this.cgscCertificateNumber = cgscCertificateNumber;
		this.adharNumber = adharNumber;
		this.jobRole = jobRole;
		this.address = address;
		this.joiningDate = joiningDate;
		this.salaryOffered = salaryOffered;
		this.offerLetterPath = offerLetterPath;
		this.trainingPartner=trainingPartner;
	}
	
	/**
	 * Default constructor
	 */
	public HiredCandidateDetailsDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
}
