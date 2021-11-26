package com.cgsc.dto;

import com.cgsc.common.BaseDto;

public class EmployerRegistrationDto extends BaseDto
{

	private static final long serialVersionUID = 1L;
	private String companyName;
	private String companyType;
	private String industryType;
	private String companyScale;
	private String panNumber;
	private String state;
	private int pincode;
	private String liasingAuthorityName;
	private String designation;
	private String email;
	private long mobileNumber;
	private String landlineNumber;
	private String employerWebsite;
	private String companyAddress;
	
	public String getCompanyName() {
		return companyName;
	}
	public String getCompanyType() {
		return companyType;
	}
	public String getIndustryType() {
		return industryType;
	}
	public String getCompanyScale() {
		return companyScale;
	}
	public String getPanNumber() {
		return panNumber;
	}
	public String getState() {
		return state;
	}
	public int getPincode() {
		return pincode;
	}
	public String getLiasingAuthorityName() {
		return liasingAuthorityName;
	}
	public String getDesignation() {
		return designation;
	}
	public String getEmail() {
		return email;
	}
	public long getMobileNumber() {
		return mobileNumber;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}
	public void setIndustryType(String industryType) {
		this.industryType = industryType;
	}
	public void setCompanyScale(String companyScale) {
		this.companyScale = companyScale;
	}
	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}
	public void setState(String state) {
		this.state = state;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	public void setLiasingAuthorityName(String liasingAuthorityName) {
		this.liasingAuthorityName = liasingAuthorityName;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
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
	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	/**
	 * @author Prateek Kapoor
	 * @param companyName
	 * @param companyType
	 * @param industryType
	 * @param companyScale
	 * @param panNumber
	 * @param state
	 * @param pincode
	 * @param liasingAuthorityName
	 * @param designation
	 * @param email
	 * @param mobileNumber
	 * @param landlineNumber
	 * @param employerWebsite
	 * @param companyAddress
	 *
	 * @updatedBy Sarthak Bhutani
	 * @updatedOn 29/11/2020
	 * @update Added fields - landlineNumber, employerWebsite, employerPoc
	 *
	 * @updatedBy Sarthak Bhutani
	 * @updatedOn 15/12/2020
	 * @update Added field - companyAddress
	 *
	 @updatedBy Sarthak Bhutani
	 @updatedOn 21/12/2020
	 * @update Removed field - employerPoc
	 *
	 */
	public EmployerRegistrationDto(String companyName, String companyType, String industryType, String companyScale,
			String panNumber, String state, int pincode, String liasingAuthorityName, String designation, String email,
			long mobileNumber, String landlineNumber, String employerWebsite, String companyAddress) {
		super();
		this.companyName = companyName;
		this.companyType = companyType;
		this.industryType = industryType;
		this.companyScale = companyScale;
		this.panNumber = panNumber;
		this.state = state;
		this.pincode = pincode;
		this.liasingAuthorityName = liasingAuthorityName;
		this.designation = designation;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.landlineNumber=landlineNumber;
		this.employerWebsite = employerWebsite;
		this.companyAddress = companyAddress;
	}
	
	/**
	 * Default Constructor
	 */
	public EmployerRegistrationDto() {
		super();
	}
}
