package com.cgsc.dto;

/**
 * 
 * @author Prateek Kapoor
 *
 */
public class GetExperienceDto {

	private int id;
	private String experience;
	
	/** Getters and Setters **/
	public int getId() {
		return id;
	}
	public String getExperience() {
		return experience;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setExperience(String experience) {
		this.experience = experience;
	}
	
	/**
	 * @author Prateek Kapoor
	 * @param id
	 * @param experience
	 */
	public GetExperienceDto(int id, String experience) {
		super();
		this.id = id;
		this.experience = experience;
	}
	
	/**
	 * Default Constructor
	 */
	public GetExperienceDto() {
		super();
		// TODO Auto-generated constructor stub
	}
}
