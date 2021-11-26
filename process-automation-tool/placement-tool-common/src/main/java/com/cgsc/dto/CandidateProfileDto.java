package com.cgsc.dto;

import org.springframework.web.multipart.MultipartFile;

import com.cgsc.common.BaseDto;

public class CandidateProfileDto extends BaseDto
{

	
	private static final long serialVersionUID = 1L;
	private int userId;
	private String candidateName;
	private long mobileNumber;
	private long guardianMobileNumber;
	private String professionalExperience;
	private String educationQualification;
	private String gender;
	private String state;
	private int pincode;
	private String resumePath;
	private String certificatesPath;
	private String exArmyPersonnel;
	private MultipartFile candidateResume;
	private MultipartFile candidateCertificates;
	private String certificateDeleteFlg;
	private String isCgscCertified;
	private String certificateNumber;
	private String tpName;
	private Integer tpId;
	private String dob;
	private String guardianName;
	private long aadhaarNumber;
	private int jobRoleId;
	private String jobRole;
	private String address;
	private int age;

	/** Getters and Setters **/
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
	public String getProfessionalExperience() {
		return professionalExperience;
	}
	public String getEducationQualification() {
		return educationQualification;
	}
	public String getGender() {
		return gender;
	}
	public String getState() {
		return state;
	}
	public int getPincode() {
		return pincode;
	}
	public String getResumePath() {
		return resumePath;
	}
	public String getCertificatesPath() {
		return certificatesPath;
	}
	public String getExArmyPersonnel() {
		return exArmyPersonnel;
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
	public void setProfessionalExperience(String professionalExperience) {
		this.professionalExperience = professionalExperience;
	}
	public void setEducationQualification(String educationQualification) {
		this.educationQualification = educationQualification;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public void setState(String state) {
		this.state = state;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	public void setResumePath(String resumePath) {
		this.resumePath = resumePath;
	}
	public void setCertificatesPath(String certificatesPath) {
		this.certificatesPath = certificatesPath;
	}
	public void setExArmyPersonnel(String exArmyPersonnel) {
		this.exArmyPersonnel = exArmyPersonnel;
	}
	public MultipartFile getCandidateResume() {
		return candidateResume;
	}
	public MultipartFile getCandidateCertificates() {
		return candidateCertificates;
	}
	public void setCandidateResume(MultipartFile candidateResume) {
		this.candidateResume = candidateResume;
	}
	public void setCandidateCertificates(MultipartFile candidateCertificates) {
		this.candidateCertificates = candidateCertificates;
	}
	
	public String getCertificateDeleteFlg() {
		return certificateDeleteFlg;
	}
	public void setCertificateDeleteFlg(String certificateDeleteFlg) {
		this.certificateDeleteFlg = certificateDeleteFlg;
	}
	public String getIsCgscCertified() {
		return isCgscCertified;
	}
	public void setIsCgscCertified(String isCgscCertified) {
		this.isCgscCertified = isCgscCertified;
	}
	public String getCertificateNumber() {
		return certificateNumber;
	}
	public void setCertificateNumber(String certificateNumber) {
		this.certificateNumber = certificateNumber;
	}
	public String getTpName() {
		return tpName;
	}
	public void setTpName(String tpName) {
		this.tpName = tpName;
	}
	public Integer getTpId() {
		return tpId;
	}
	public void setTpId(int tpId) {
		this.tpId = tpId;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * @author Prateek Kapoor
	 * This constructor is for getting candidate details from database
	 * @param userId
	 * @param candidateName
	 * @param mobileNumber
	 * @param guardianMobileNumber
	 * @param professionalExperience
	 * @param educationQualification
	 * @param gender
	 * @param state
	 * @param pincode
	 * @param resumePath
	 * @param certificatesPath
	 * @param exArmyPersonnel
	 * @param isCgscCertified
	 * @param certificateNumber
	 * @param tpName
	 * @param tpId
	 * @param dob
	 * @param guardianName
	 * @param aadhaarNumber
	 * @param jobRoleId
	 * @param jobRole
	 * @param address
	 */ 
	 public CandidateProfileDto(int userId, String candidateName, long mobileNumber, long guardianMobileNumber,
			String professionalExperience, String educationQualification, String gender, String state, int pincode,
			String resumePath, String certificatesPath, String exArmyPersonnel, String isCgscCertified, String certificateNumber, 
			String tpName, Integer tpId, String dob, String guardianName, long aadhaarNumber, int jobRoleId, String jobRole, String address, int age) {
		super();
		this.userId = userId;
		this.candidateName = candidateName;
		this.mobileNumber = mobileNumber;
		this.guardianMobileNumber = guardianMobileNumber;
		this.professionalExperience = professionalExperience;
		this.educationQualification = educationQualification;
		this.gender = gender;
		this.state = state;
		this.pincode = pincode;
		this.resumePath = resumePath;
		this.certificatesPath = certificatesPath;
		this.exArmyPersonnel = exArmyPersonnel;
		this.isCgscCertified = isCgscCertified;
		this.certificateNumber = certificateNumber;
		this.tpName = tpName;
		this.tpId = tpId;
		this.dob=dob;
		this.guardianName = guardianName;
		this.aadhaarNumber = aadhaarNumber;
		this.jobRoleId = jobRoleId;
		this.jobRole = jobRole;
		this.address = address;
		this.age = age;
	}

	/**
	 * @author Prateek Kapoor
	 * This constructor is used to get candidate details from front end
	 * @param userId
	 * @param candidateName
	 * @param mobileNumber
	 * @param guardianMobileNumber
	 * @param professionalExperience
	 * @param educationQualification
	 * @param gender
	 * @param state
	 * @param pincode
	 * @param exArmyPersonnel
	 * @param candidateResume
	 * @param candidateCertificates
	 * @param tpId
	 */
	public CandidateProfileDto(int userId, String candidateName, long mobileNumber, long guardianMobileNumber,
			String professionalExperience, String educationQualification, String gender, String state, int pincode,
			String exArmyPersonnel, MultipartFile candidateResume, MultipartFile candidateCertificates, String certificateDeleteFlg, int tpId) {
		super();
		this.userId = userId;
		this.candidateName = candidateName;
		this.mobileNumber = mobileNumber;
		this.guardianMobileNumber = guardianMobileNumber;
		this.professionalExperience = professionalExperience;
		this.educationQualification = educationQualification;
		this.gender = gender;
		this.state = state;
		this.pincode = pincode;
		this.exArmyPersonnel = exArmyPersonnel;
		this.candidateResume = candidateResume;
		this.candidateCertificates = candidateCertificates;
		this.certificateDeleteFlg = certificateDeleteFlg;
		this.tpId=tpId;
	}
	
	/**
	 * @author Prateek Kapoor
	 * @param resumePath
	 * @param certificatesPath
	 */
	public CandidateProfileDto(String resumePath, String certificatesPath) {
		super();
		this.resumePath = resumePath;
		this.certificatesPath = certificatesPath;
	}
	/** 
	 * Default Constructor
	 */
	public CandidateProfileDto() {
		super();
		// TODO Auto-generated constructor stub
	}
}
