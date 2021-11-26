package com.cgsc.dto;

/**
 * 
 * @author Prateek Kapoor
 *
 */
public class GetSkillsDto 
{

	private int id;
	private String skill;
	
	/** Getters and Setters **/
	public int getId() {
		return id;
	}
	public String getSkill() {
		return skill;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setSkill(String skill) {
		this.skill = skill;
	}
	
	/**
	 * @author Prateek Kapoor
	 * @param id
	 * @param skill
	 */
	public GetSkillsDto(int id, String skill) {
		super();
		this.id = id;
		this.skill = skill;
	}
	
	/**
	 * Default Constructor
	 */
	public GetSkillsDto() {
		super();
		// TODO Auto-generated constructor stub
	}
}
