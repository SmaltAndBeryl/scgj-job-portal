package com.cgsc.dto;

import org.springframework.context.annotation.Bean;

/*
 * @author Sarthak Bhutani
 */
public class GetTermsAndConditionsDto {

	private String filepath;

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	
	public GetTermsAndConditionsDto() {
		super();
	}
	
	public GetTermsAndConditionsDto(String filepath) {
		this.filepath = filepath;
	}

}
