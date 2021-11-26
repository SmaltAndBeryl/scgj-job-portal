package com.cgsc.dto;

import java.sql.Date;
import java.util.ArrayList;

import com.cgsc.common.BaseDto;

public class ManageJobApplicationsDto extends BaseDto
{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int jobId;
	private String updatedStatus;
	private int salary;
	private Date joiningDate;
	private String offerLetter;
	private ArrayList<Integer> candidateId;
	
	/** Getters and Setters **/
	
	
	public int getJobId() {
		return jobId;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public Date getJoiningDate() {
		return joiningDate;
	}
	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}
	public String getOfferLetter() {
		return offerLetter;
	}
	public void setOfferLetter(String offerLetter) {
		this.offerLetter = offerLetter;
	}
	public String getUpdatedStatus() {
		return updatedStatus;
	}
	public ArrayList<Integer> getCandidateId() {
		return candidateId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	public void setUpdatedStatus(String updatedStatus) {
		this.updatedStatus = updatedStatus;
	}
	public void setCandidateId(ArrayList<Integer> candidateId) {
		this.candidateId = candidateId;
	}
	
	/**
	 * @author Prateek Kapoor
	 * @param jobId
	 * @param updatedStatus
	 * @param candidateId
	 * @param salary
	 * @param joiningDate
	 * @param offerLetter
	 * 
	 * @updated by - Jyoti Singh
	 * @updated on - 2-12-2020
	 * 
	 */
	public ManageJobApplicationsDto(int jobId, String updatedStatus, ArrayList<Integer> candidateId,int salary, Date joiningDate, String offerLetter) {
		super();
		this.jobId = jobId;
		this.updatedStatus = updatedStatus;
		this.candidateId = candidateId;
		this.salary = salary;
		this.joiningDate = joiningDate;
		this.offerLetter = offerLetter;
	}
	
	/**
	 * Default Constructor
	 */
	public ManageJobApplicationsDto() {
		super();
		// TODO Auto-generated constructor stub
	}
}
