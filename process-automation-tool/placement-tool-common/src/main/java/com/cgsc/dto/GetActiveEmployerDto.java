package com.cgsc.dto;

/**
 * 
 * @author Prateek Kapoor
 *
 */
public class GetActiveEmployerDto 
{

	private int id;
	private String employerName;
	
	/** Getters and Setters **/
	public int getId() {
		return id;
	}
	public String getEmployerName() {
		return employerName;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setEmployerName(String employerName) {
		this.employerName = employerName;
	}
	
	/**
	 * @author Prateek Kapoor
	 * @param id
	 * @param employerName
	 */
	public GetActiveEmployerDto(int id, String employerName) {
		super();
		this.id = id;
		this.employerName = employerName;
	}
	
	/**
	 * Default Constructor
	 */
	public GetActiveEmployerDto() {
		super();
		// TODO Auto-generated constructor stub
	}
}
