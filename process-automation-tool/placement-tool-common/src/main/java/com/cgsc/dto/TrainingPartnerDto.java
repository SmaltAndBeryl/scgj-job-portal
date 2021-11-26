package com.cgsc.dto;

public class TrainingPartnerDto 
{

	private int userId;
	private String trainingPartnerName;

	public int getUserId() {
		return userId;
	}
	public String getTrainingPartnerName() {
		return trainingPartnerName;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public void setTrainingPartnerName(String trainingPartnerName) {
		this.trainingPartnerName = trainingPartnerName;
	}
	
	/**
	 * 
	 * @param userId
	 * @param trainingPartnerName
	 */
	public TrainingPartnerDto(int userId, String trainingPartnerName) {
		super();
		this.userId = userId;
		this.trainingPartnerName = trainingPartnerName;
	}
	
	/**
	 * Default Constructor
	 */
	public TrainingPartnerDto() {
		super();
		// TODO Auto-generated constructor stub
	}
}
