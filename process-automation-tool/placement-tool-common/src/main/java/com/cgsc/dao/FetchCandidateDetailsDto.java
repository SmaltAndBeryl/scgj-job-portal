package com.cgsc.dao;

/**
 * 
 * @author Prateek Kapoor
 *
 */
public class FetchCandidateDetailsDto 
{

	private Integer id;
	private long mobileNumber;
	
	/** Getters and Setters **/
	public Integer getId() {
		return id;
	}
	public long getMobileNumber() {
		return mobileNumber;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
	/**
	 * @author Prateek Kapoor
	 * @param id
	 * @param mobileNumber
	 */
	public FetchCandidateDetailsDto(Integer id, long mobileNumber) {
		super();
		this.id = id;
		this.mobileNumber = mobileNumber;
	}
	/**
	 * @author Prateek Kapoor
	 * 
	 */
	public FetchCandidateDetailsDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
