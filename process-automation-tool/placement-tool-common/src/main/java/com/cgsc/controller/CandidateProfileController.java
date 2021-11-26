package com.cgsc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cgsc.common.Privilege;
import com.cgsc.dto.CandidateProfileDto;
import com.cgsc.service.CandidateProfileService;

@RestController
public class CandidateProfileController 
{

	private static final Logger Log = LoggerFactory.getLogger(CandidateProfileController.class);
	
	@Autowired
	private CandidateProfileService candidateProfileService;
	
	/**
	 * @author Prateek Kapoor
	 * @since 27-10-2020
	 * This method fetches the candidate details for the logged-in candidate
	 * @return {@link CandidateProfileDto} if success;
	 * Else returns null
	 */
	@Privilege(value= {"Candidate"})
	@GetMapping("/getCandidateProfileDetails")
	public CandidateProfileDto viewCandidateProfileDetails()
	{
		Log.debug("Request received in controller to fetch the candidate details for logged in candidate");
		return candidateProfileService.viewCandidateProfileDetails();
	}
	
	
	/**
	 * @author Prateek Kapoor
	 * @since 27-10-2020
	 * This method updates the candidate profile details
	 * @param candidateProfileDto
	 * @return 1 if success;
	 * else returns -25
	 */
	@Privilege(value= {"Candidate"})
	@PostMapping(value="/updateCandidateProfile",consumes=MediaType.ALL_VALUE)
	public int updateCandidateDetails(@ModelAttribute CandidateProfileDto candidateProfileDto)
	{
		try
		{
			Log.debug("Request received in controller to update the candidate details for user with id {}",candidateProfileDto.getUserId());
			return candidateProfileService.updateCandidateDetails(candidateProfileDto);
		}
		catch(Exception e)
		{
			Log.error("An exception occurred while updating the candidate details - "+e);
			Log.error("Returning -25 to front end");
			return -25;
		}
		
	}
}
