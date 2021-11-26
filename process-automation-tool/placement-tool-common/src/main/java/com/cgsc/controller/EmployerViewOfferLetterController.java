package com.cgsc.controller;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cgsc.common.Privilege;
import com.cgsc.dto.HiredCandidateDetailsDto;
import com.cgsc.service.EmployerViewOfferLetterService;

@RestController
public class EmployerViewOfferLetterController {

	private static final Logger Log = LoggerFactory.getLogger(EmployerViewOfferLetterController.class);
	
	@Autowired
	private EmployerViewOfferLetterService employerViewOfferLetterService;
	
	
	/**
	 * @author Jyoti Singh
	 * @since 23-12-2020
	 * This method fetches the collection of candidate details who were hired for the job post with given id
	 * @param id (int)
	 * @return Collection of {@link HiredCandidateDetailsDto} if success; else returns null
	 */
	@Privilege(value= {"Employer"})
	@GetMapping("/getHiredCandidatesForJobId")
	public Collection<HiredCandidateDetailsDto> viewHiredCandidates(@RequestParam("id") int jobId)
	{
		Log.debug("Request received in controller to fetch the list of all candidates that were hired for the job post with id - {}",jobId);
		return employerViewOfferLetterService.viewHiredCandidates(jobId);
	}
}
