package com.cgsc.dto;

/**
 * 
 * @author Prateek Kapoor
 *
 */
public class ViewEmployerDetailsDto 
{

	private int userId;
	private String companyName;
	private String companyType;
	private String companyScale;
	private long mobileNumber;
	private String email;
	private String state;
	private String registeredOn;
	private String enrollmentUpdatedOn;
	private String landlineNumber;
	private String employerWebsite;
	private String industryType;
	private String liaisingAuthority;
	private String designation;

	/** Getters and Setters **/
	public int getUserId() {
		return userId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public String getCompanyType() {
		return companyType;
	}
	public String getCompanyScale() {
		return companyScale;
	}
	public long getMobileNumber() {
		return mobileNumber;
	}
	public String getEmail() {
		return email;
	}
	public String getState() {
		return state;
	}
	public String getRegisteredOn() {
		return registeredOn;
	}
	public String getEnrollmentUpdatedOn() {
		return enrollmentUpdatedOn;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}
	public void setCompanyScale(String companyScale) {
		this.companyScale = companyScale;
	}
	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setState(String state) {
		this.state = state;
	}
	public void setRegisteredOn(String registeredOn) {
		this.registeredOn = registeredOn;
	}
	public void setEnrollmentUpdatedOn(String enrollmentUpdatedOn) {this.enrollmentUpdatedOn=enrollmentUpdatedOn;}
	public String getLandlineNumber() {
		return landlineNumber;
	}
	public void setLandlineNumber(String landlineNumber) {
		this.landlineNumber = landlineNumber;
	}
	public String getEmployerWebsite() {
		return employerWebsite;
	}
	public void setEmployerWebsite(String employerWebsite) {
		this.employerWebsite = employerWebsite;
	}

	public String getIndustryType() {
		return industryType;
	}

	public void setIndustryType(String industryType) {
		this.industryType = industryType;
	}

	public String getLiaisingAuthority() {
		return liaisingAuthority;
	}

	public void setLiaisingAuthority(String liaisingAuthority) {
		this.liaisingAuthority = liaisingAuthority;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	/**
	 * @author Prateek Kapoor
	 * @param userId
	 * @param companyName
	 * @param companyType
	 * @param companyScale
	 * @param mobileNumber
	 * @param email
	 * @param state
	 * @param registeredOn
	 * @param enrollmentUpdatedOn
	 * @param landlineNumber
	 * @param employerWebsite
	 * @param industryType
	 * @param liaisingAuthority
	 * @param designation
	 *
	 *
	 * @updatedBy Sarthak Bhutani
	 * @updatedOn 21/12/2020
	 * @update Added designation, industryType, liaisingAuthority; Removed employerPoc
	 */
	public ViewEmployerDetailsDto(int userId, String companyName, String companyType, String companyScale,
			long mobileNumber, String email, String state, String registeredOn, String enrollmentUpdatedOn,
			String landlineNumber, String employerWebsite, String industryType, String liaisingAuthority, String designation) {
		super();
		this.userId = userId;
		this.companyName = companyName;
		this.companyType = companyType;
		this.companyScale = companyScale;
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.state = state;
		this.registeredOn = registeredOn;
		this.enrollmentUpdatedOn = enrollmentUpdatedOn;
		this.landlineNumber = landlineNumber;
		this.employerWebsite = employerWebsite;
		this.industryType = industryType;
		this.liaisingAuthority=liaisingAuthority;
		this.designation=designation;
	}
	
	/**
	 * Default Constructor
	 */
	public ViewEmployerDetailsDto() {
		super();
		// TODO Auto-generated constructor stub
	}

}
