package com.cgsc.dto;

/**
 * 
 * @author Prateek Kapoor
 * 
 * @updated by -Jyoti Singh
 * @updated on - 16-12-2020
 * @param jobId
 * @param applicationStatus
 * @param joiningDate
 * @param offerLetterPath
 * @param salaryOffered
 *
 */
public class CandidatesPlacedDto 
{

	private String companyName;
	private String candidateName;
	private long mobileNumber;
	private String guardianName;
	private String dob;
	private long aadhaarNumber;
	private String isCGSCCertified;
	private String jobRole;
	private String jobId;
	private String applicationStatus;
	private String joiningDate;
	private String offerLetterPath;
	private int salaryOffered;
	private String jobDistrict;
	private String jobState;
	
	/** Getters and Setters **/
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCandidateName() {
		return candidateName;
	}

	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}

	public long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getGuardianName() {
		return guardianName;
	}

	public void setGuardianName(String guardianName) {
		this.guardianName = guardianName;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public long getAadhaarNumber() {
		return aadhaarNumber;
	}

	public void setAadhaarNumber(long aadhaarNumber) {
		this.aadhaarNumber = aadhaarNumber;
	}

	public String getJobRole() {
		return jobRole;
	}

	public void setJobRole(String jobRole) {
		this.jobRole = jobRole;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getApplicationStatus() {
		return applicationStatus;
	}

	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}

	public String getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(String joiningDate) {
		this.joiningDate = joiningDate;
	}

	public String getOfferLetterPath() {
		return offerLetterPath;
	}

	public void setOfferLetterPath(String offerLetterPath) {
		this.offerLetterPath = offerLetterPath;
	}

	public int getSalaryOffered() {
		return salaryOffered;
	}

	public void setSalaryOffered(int salaryOffered) {
		this.salaryOffered = salaryOffered;
	}

	public String getJobDistrict() {
		return jobDistrict;
	}

	public String getJobState() {
		return jobState;
	}

	public void setJobDistrict(String jobDistrict) {
		this.jobDistrict = jobDistrict;
	}

	public void setJobState(String jobState) {
		this.jobState = jobState;
	}
	

	public String getIsCGSCCertified() {
		return isCGSCCertified;
	}

	public void setIsCGSCCertified(String isCGSCCertified) {
		this.isCGSCCertified = isCGSCCertified;
	}

	/**
	 * @author Prateek Kapoor
	 * @param companyName
	 * @param candidateName
	 * @param mobileNumber
	 * @param guardianName
	 * @param dob
	 * @param aadhaarNumber
	 * @param jobRole
	 * 
	 * 
	 * @updated by - Jyoti Singh
	 * @updated on - 16-12-2020
	 * @Update - Added the following - 
	 * @param jobId
	 * @param applicationStatus
	 * @param joiningDate
	 * @param offerLetterPath
	 * @param salaryOffered
	 * 
	 * @author Prateek Kapoor
	 * @updatedOn 28-12-2020
	 * added jobDistrict and jobState in the DTO
	 * @param jobDistrict
	 * @param jobState
	 * 
	 * @updated by - Jyoti Singh
	 * @updated on - 07-01-2021
	 * @Update - Added the following - 
	 * @param isCGSCCertified
	 */
	
	public CandidatesPlacedDto(String companyName, String candidateName, long mobileNumber, String guardianName,
			String dob, long aadhaarNumber,String isCGSCCertified ,String jobRole, String jobId, String applicationStatus, String joiningDate,
			String offerLetterPath, int salaryOffered, String jobDistrict, String jobState) {
		super();
		this.companyName = companyName;
		this.candidateName = candidateName;
		this.mobileNumber = mobileNumber;
		this.guardianName = guardianName;
		this.dob = dob;
		this.aadhaarNumber = aadhaarNumber;
		this.jobRole = jobRole;
		this.jobId = jobId;
		this.applicationStatus = applicationStatus;
		this.joiningDate = joiningDate;
		this.offerLetterPath = offerLetterPath;
		this.salaryOffered = salaryOffered;
		this.jobDistrict = jobDistrict;
		this.jobState = jobState;
		this.isCGSCCertified=isCGSCCertified;
	}

	/**
	 * Default Constructor
	 */
	public CandidatesPlacedDto() {
		super();
		// TODO Auto-generated constructor stub
	}

}
