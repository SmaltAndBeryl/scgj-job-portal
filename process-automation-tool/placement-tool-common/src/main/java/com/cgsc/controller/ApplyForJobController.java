package com.cgsc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cgsc.common.Privilege;
import com.cgsc.service.ApplyForJobService;

@RestController
public class ApplyForJobController 
{

	private static final Logger Log = LoggerFactory.getLogger(ApplyForJobController.class);
	
	@Autowired
	private ApplyForJobService applyForJobService;
	
	/**
	 * @author Prateek Kapoor
	 * @since 15-10-2020
	 * This method handles the request of the logged in candidate to map the candidate Id to a jobId and set the status to ('In Review')
	 * @param jobId
	 * @param candidateId
	 * @return 1 if success;
	 * -25 in case of exception
	 * -60 if candidate has already applied for the job (represented by jobId)
	 */
	@Privilege(value= {"Candidate"})
	@GetMapping("/applyForJobPost")
	public int applyForJob(@RequestParam("jobId") int jobId)
	{
		Log.debug("Request received in service to process the application of logged in candidate  in job with id {}",jobId);
		return applyForJobService.applyForJob(jobId);
	}

}
