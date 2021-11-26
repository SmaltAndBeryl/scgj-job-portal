package com.cgsc.dto;

public class GetIndustryTypes 
{

	private int id;
	private String industryType;
	
	/** Getters and Setters **/
	public int getId() {
		return id;
	}
	public String getIndustryType() {
		return industryType;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setIndustryType(String industryType) {
		this.industryType = industryType;
	}
	
	/**
	 * @author Prateek Kapoor
	 * @param id
	 * @param industryType
	 */
	public GetIndustryTypes(int id, String industryType) {
		super();
		this.id = id;
		this.industryType = industryType;
	}

	/**
	 * Default Constructor
	 */
	public GetIndustryTypes() {
		super();
		// TODO Auto-generated constructor stub
	}

}
