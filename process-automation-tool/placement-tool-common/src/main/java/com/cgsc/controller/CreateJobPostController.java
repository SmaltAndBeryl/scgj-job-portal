package com.cgsc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cgsc.common.Privilege;
import com.cgsc.dto.CreateJobPostDto;
import com.cgsc.service.CreateJobPostService;

@RestController
public class CreateJobPostController 
{

	private static final Logger Log = LoggerFactory.getLogger(CreateJobPostController.class);
	
	@Autowired
	private CreateJobPostService createJobPostService;
	
	/**
	 * @author Prateek Kapoor
	 * @since 09-10-2020
	 * This method inserts the job postings into the database
	 * @param createJobPostdto
	 * @param jobId
	 * @param jobPostingsPath
	 * @param employerId
	 * @return 1 if success; -25 in case of exception
	 */
	@Privilege(value= {"Employer"})
	@PostMapping(value="/createJobPost",consumes=MediaType.ALL_VALUE)
	public int generateJobPosts(@ModelAttribute CreateJobPostDto createJobPostdto)
	{
		Log.debug("Request received in controller to create job post with job title {}",createJobPostdto.getJobTitle());
		return createJobPostService.generateJobPosts(createJobPostdto);
	}
}
