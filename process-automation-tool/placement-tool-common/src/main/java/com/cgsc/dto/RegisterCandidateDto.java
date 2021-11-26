package com.cgsc.dto;

import org.springframework.web.multipart.MultipartFile;

import com.cgsc.common.BaseDto;

public class RegisterCandidateDto extends BaseDto
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String candidateName;
	private long mobileNumber;
	private long guardianMobileNumber;
	private String professionalExperience;
	private String qualification;
	private String gender;
	private String defenceBackground;
	private String state;
	private int pincode;
	private MultipartFile resume;
	private MultipartFile certificates;
	private String cgscCertifiedCandidate;
	private String certificateNumber;
	private Integer trainingPartnerId;
	private String dob;
	private String guardianName;
	private long aadhaarNumber;
	private int jobRoleId;
	private String address;

	/** Getters and Setters**/
	public String getCandidateName() {
		return candidateName;
	}
	public long getMobileNumber() {
		return mobileNumber;
	}
	public long getGuardianMobileNumber() {
		return guardianMobileNumber;
	}
	public String getProfessionalExperience() {
		return professionalExperience;
	}
	public String getQualification() {
		return qualification;
	}
	public String getGender() {
		return gender;
	}
	public String getDefenceBackground() {
		return defenceBackground;
	}
	public String getState() {
		return state;
	}
	public int getPincode() {
		return pincode;
	}
	public MultipartFile getResume() {
		return resume;
	}
	public MultipartFile getCertificates() {
		return certificates;
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
	public void setProfessionalExperience(String professionalExperience) {
		this.professionalExperience = professionalExperience;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public void setDefenceBackground(String defenceBackground) {
		this.defenceBackground = defenceBackground;
	}
	public void setState(String state) {
		this.state = state;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	public void setResume(MultipartFile resume) {
		this.resume = resume;
	}
	public void setCertificates(MultipartFile certificates) {
		this.certificates = certificates;
	}	
	public String getCgscCertifiedCandidate() {
		return cgscCertifiedCandidate;
	}
	public String getCertificateNumber() {
		return certificateNumber;
	}
	public void setCgscCertifiedCandidate(String cgscCertifiedCandidate) {
		this.cgscCertifiedCandidate = cgscCertifiedCandidate;
	}
	public void setCertificateNumber(String certificateNumber) {
		this.certificateNumber = certificateNumber;
	}
	public Integer getTrainingPartnerId() {
		return trainingPartnerId;
	}
	public void setTrainingPartnerId(Integer trainingPartnerId) {
		this.trainingPartnerId = trainingPartnerId;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getGuardianName() {
		return guardianName;
	}
	public void setGuardianName(String guardianName) {
		this.guardianName = guardianName;
	}
	public long getAadhaarNumber() {
		return aadhaarNumber;
	}
	public void setAadhaarNumber(long aadhaarNumber) {
		this.aadhaarNumber = aadhaarNumber;
	}
	public int getJobRoleId() {
		return jobRoleId;
	}
	public void setJobRoleId(int jobRoleId) {
		this.jobRoleId = jobRoleId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @author Prateek Kapoor
	 * @param mobileNumber
	 * @param guardianMobileNumber
	 * @param professionalExperience
	 * @param qualification
	 * @param gender
	 * @param defenceBackground
	 * @param state
	 * @param pincode
	 * @param resume
	 * @param certificates
	 * @param cgscCertifiedCandidate
	 * @param certificateNumber
	 * @param trainingPartnerId
	 * @param dob
	 * @param guardianName
	 * @param aadhaarNumber
	 * @param jobRoleId
	 * @param address
	 */
	public RegisterCandidateDto(long mobileNumber, long guardianMobileNumber, String professionalExperience,
								String qualification, String gender, String defenceBackground, String state, int pincode,
								MultipartFile resume, MultipartFile certificates, String cgscCertifiedCandidate, String certificateNumber, Integer trainingPartnerId,
								String dob, String guardianName, long aadhaarNumber, int jobRoleId, String address )
	{
		super();
		this.mobileNumber = mobileNumber;
		this.guardianMobileNumber = guardianMobileNumber;
		this.professionalExperience = professionalExperience;
		this.qualification = qualification;
		this.gender = gender;
		this.defenceBackground = defenceBackground;
		this.state = state;
		this.pincode = pincode;
		this.resume = resume;
		this.certificates = certificates;
		this.cgscCertifiedCandidate = cgscCertifiedCandidate;
		this.certificateNumber = certificateNumber;
		this.trainingPartnerId = trainingPartnerId;
		this.dob=dob;
		this.guardianName = guardianName;
		this.aadhaarNumber = aadhaarNumber;
		this.jobRoleId = jobRoleId;
		this.address = address;
	}
	
	
	/**
	 * Default Constructor
	 */
	public RegisterCandidateDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
