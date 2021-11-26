package com.cgsc.dto;

/**
 * 
 * @author Prateek Kapoor
 *
 */
public class GetEducationQualificationDto 
{

	private int id;
	private String educationQualification;
	
	/** Getters and Setters **/
	public int getId() {
		return id;
	}
	public String getEducationQualification() {
		return educationQualification;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setEducationQualification(String educationQualification) {
		this.educationQualification = educationQualification;
	}
	
	/**
	 * @author Prateek Kapoor
	 * @param id
	 * @param educationQualification
	 */
	public GetEducationQualificationDto(int id, String educationQualification) {
		super();
		this.id = id;
		this.educationQualification = educationQualification;
	}
	
	/**
	 * Default Constructor
	 */
	public GetEducationQualificationDto() {
		super();
		// TODO Auto-generated constructor stub
	}
}
