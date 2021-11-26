package com.cgsc.dto;

/**
 * 
 * @author Sarthak Bhutani
 *
 */
public class ViewEmployerByActiveStatusDto {

	private int userId;
	private String companyName;
	private String companyType;
	private String companyScale;
	private long mobileNumber;
	private String email;
	private String state;
	private String registeredOn;
	private String activationUpdatedOn;
	private String landlineNumber;
	private String employerWebsite;
	private String industryType;
	private String liaisingAuthority;
	private String designation;

	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyType() {
		return companyType;
	}
	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}
	public String getCompanyScale() {
		return companyScale;
	}
	public void setCompanyScale(String companyScale) {
		this.companyScale = companyScale;
	}
	public long getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getRegisteredOn() {
		return registeredOn;
	}
	public void setRegisteredOn(String registeredOn) {
		this.registeredOn = registeredOn;
	}
	public String getActivationUpdatedOn() {
		return activationUpdatedOn;
	}
	public void setActivationUpdatedOn(String activationUpdatedOn) {
		this.activationUpdatedOn = activationUpdatedOn;
	}
	
	public String getLandlineNumber() {
		return landlineNumber;
	}
	public void setLandlineNumber(String landlineNumber) {
		this.landlineNumber = landlineNumber;
	}
	public void setEmployerPoc(String employerPoc) {
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
	 * @author Sarthak Bhutani
	 * Default constructor
	 */
	public ViewEmployerByActiveStatusDto() {
		super();
	}
	/**
	 * @author Sarthak Bhutani
	 * Dto for fetching employer details by active status
	 * @param userId
	 * @param companyName
	 * @param companyType
	 * @param companyScale
	 * @param mobileNumber
	 * @param email
	 * @param state
	 * @param registeredOn
	 * @param activationUpdatedOn
	 * @param landlineNumber
	 * @param employerWebsite
	 * @param designation
	 * @param industryType
	 * @param liaisingAuthority
	 *
	 * @updatedBy Sarthak Bhutani
	 * @updatedOn 21/12/2020
	 * @update Added designation, industryType, liaisingAuthority; Removed employerPoc
	 */

	public ViewEmployerByActiveStatusDto(int userId, String companyName, String companyType, String companyScale,
										 long mobileNumber, String email, String state, String registeredOn, String activationUpdatedOn, String landlineNumber, String employerWebsite,
										 String industryType, String liaisingAuthority, String designation) {
		this.userId=userId;
		this.companyName=companyName;
		this.companyType=companyType;
		this.companyScale=companyScale;
		this.mobileNumber=mobileNumber;
		this.email=email;
		this.state=state;
		this.registeredOn=registeredOn;
		this.activationUpdatedOn=activationUpdatedOn;
		this.landlineNumber=landlineNumber;
		this.employerWebsite =employerWebsite;
		this.industryType = industryType;
		this.liaisingAuthority=liaisingAuthority;
		this.designation=designation;
	}
	
}
