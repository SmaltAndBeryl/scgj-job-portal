package com.cgsc.dto;

/**
 * 
 * @author Jyoti Singh
 * @since 17-12-2020
 *
 */
public class EmployerDetailsDto {
	private int id;
	private String employerName;
	private long mobileNumber;
	private String liasingAuthorityName;
	private String email;
	private int totalJobPostings;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmployerName() {
		return employerName;
	}
	public void setEmployerName(String employerName) {
		this.employerName = employerName;
	}
	public long getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getLiasingAuthorityName() {
		return liasingAuthorityName;
	}
	public void setLiasingAuthorityName(String liasingAuthorityName) {
		this.liasingAuthorityName = liasingAuthorityName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getTotalJobPostings() {
		return totalJobPostings;
	}
	public void setTotalJobPostings(int totalJobPostings) {
		this.totalJobPostings = totalJobPostings;
	}
	
	
	/**
	 * @author Jyoti Singh
	 * 
	 * @param id
	 * @param employerName
	 * @param mobileNumber
	 * @param liasingAuthorityName
	 * @param email
	 * @param totalJobPostings
	 */
	public EmployerDetailsDto(int id,String employerName, long mobileNumber, String liasingAuthorityName, String email,
			int totalJobPostings) {
		super();
		this.id=id;
		this.employerName = employerName;
		this.mobileNumber = mobileNumber;
		this.liasingAuthorityName = liasingAuthorityName;
		this.email = email;
		this.totalJobPostings = totalJobPostings;
	}
	public EmployerDetailsDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
