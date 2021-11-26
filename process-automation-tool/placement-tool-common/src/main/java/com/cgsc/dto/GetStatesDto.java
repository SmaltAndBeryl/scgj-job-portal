package com.cgsc.dto;

/**
 * 
 * @author Prateek Kapoor
 *
 */
public class GetStatesDto 
{

	private int id;
	private String stateName;
	
	/** Getter and Setters **/
	public int getId() {
		return id;
	}
	public String getStateName() {
		return stateName;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	
	/**
	 * @author Prateek Kapoor
	 * @param id
	 * @param stateName
	 */
	public GetStatesDto(int id, String stateName) {
		super();
		this.id = id;
		this.stateName = stateName;
	}
	
	/**
	 * Default Constructor
	 */
	public GetStatesDto() {
		super();
		// TODO Auto-generated constructor stub
	}
}
