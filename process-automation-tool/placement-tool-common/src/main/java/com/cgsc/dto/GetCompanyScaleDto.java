package com.cgsc.dto;

public class GetCompanyScaleDto {

	private int id;
	private String companyScale;
	
	/** Getters and Setters **/
	public int getId() {
		return id;
	}
	public String getCompanyScale() {
		return companyScale;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setCompanyScale(String companyScale) {
		this.companyScale = companyScale;
	}
	
	/**
	 * @author Prateek Kapoor
	 * @param id
	 * @param companyScale
	 */
	public GetCompanyScaleDto(int id, String companyScale) {
		super();
		this.id = id;
		this.companyScale = companyScale;
	}
	
	/**
	 * Default Constructor
	 */
	public GetCompanyScaleDto() {
		super();
		// TODO Auto-generated constructor stub
	}
}
