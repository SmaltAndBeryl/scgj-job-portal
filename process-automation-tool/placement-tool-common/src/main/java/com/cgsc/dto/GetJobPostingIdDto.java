package com.cgsc.dto;

/**
 * 
 * @author Prateek Kapoor
 *
 */
public class GetJobPostingIdDto
{

	private int id;
	private String jobId;
	private String jobTitle;
	
	/** Getters and Setters **/
	public int getId() {
		return id;
	}
	public String getJobId() {
		return jobId;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	
	/** 
	 * @author Prateek Kapoor
	 * @param id
	 * @param jobId
	 * @param jobTitle
	 */
	public GetJobPostingIdDto(int id, String jobId, String jobTitle) {
		super();
		this.id = id;
		this.jobId = jobId;
		this.jobTitle = jobTitle;
	}
	
	/** Default Constructor **/
	public GetJobPostingIdDto() {
		super();
		// TODO Auto-generated constructor stub
	}
}
