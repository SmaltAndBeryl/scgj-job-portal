package com.cgsc.dto;

public class GetCompanyTypeDto
{

	private int id;
	private String companyType;
	
	/** Getters and Setters **/
	public int getId() {
		return id;
	}
	public String getCompanyType() {
		return companyType;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}
	
	/**
	 * @author Prateek Kapoor
	 * @param id
	 * @param companyType
	 */
	public GetCompanyTypeDto(int id, String companyType) {
		super();
		this.id = id;
		this.companyType = companyType;
	}
	
	/**
	 * Default Constructor
	 */
	public GetCompanyTypeDto() {
		super();
		// TODO Auto-generated constructor stub
	}
}
