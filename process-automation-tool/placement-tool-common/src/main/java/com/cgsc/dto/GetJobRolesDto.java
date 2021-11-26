package com.cgsc.dto;

/**
 * 
 * @author Prateek Kapoor
 *
 */
public class GetJobRolesDto 
{

	private int jobRoleId;
	private String jobRole;
	
	/** Getters and Setters **/
	public int getJobRoleId() {
		return jobRoleId;
	}
	public String getJobRole() {
		return jobRole;
	}
	public void setJobRoleId(int jobRoleId) {
		this.jobRoleId = jobRoleId;
	}
	public void setJobRole(String jobRole) {
		this.jobRole = jobRole;
	}
	
	/**
	 * @author Prateek Kapoor
	 * @param jobRoleId
	 * @param jobRole
	 */
	public GetJobRolesDto(int jobRoleId, String jobRole) {
		super();
		this.jobRoleId = jobRoleId;
		this.jobRole = jobRole;
	}
	
	/**
	 * Default Constructor
	 */
	public GetJobRolesDto() {
		super();
		// TODO Auto-generated constructor stub
	}
}
