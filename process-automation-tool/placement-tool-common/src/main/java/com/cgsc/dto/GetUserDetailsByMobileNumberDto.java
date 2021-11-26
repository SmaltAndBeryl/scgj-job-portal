package com.cgsc.dto;


public class GetUserDetailsByMobileNumberDto {
	
	private String name;
	private String state;
	private Long mobileNumber;
	private int pincode;
	private String userRole;
	private String activeStatus;

	public String getName(){
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Long getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public String getActiveStatus() {
		return activeStatus;
	}
	public void setActiveStatus(String activeStatus) {
		this.activeStatus = activeStatus;
	}
	
	
	/**
	 * @author Sarthak Bhutani
	 * @param name
	 * @param state
	 * @param mobileNumber
	 * @param pincode
	 * @param userRole
	 * @param activeStatus
	 */
	public GetUserDetailsByMobileNumberDto(String name, String state, Long mobileNumber, int pincode,
			String userRole, String activeStatus) {
		this.name=name;
		this.state=state;
		this.mobileNumber=mobileNumber;
		this.pincode=pincode;
		this.userRole=userRole;
		this.activeStatus=activeStatus;
	}
	
	/**
	 * @author Sarthak Bhutani
	 * Default Constructor
	 */
	public GetUserDetailsByMobileNumberDto() {
		super();
	}
}
