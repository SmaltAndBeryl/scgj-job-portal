package com.cgsc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cgsc.common.Privilege;
import com.cgsc.dto.EditJobPostsDto;
import com.cgsc.service.EditJobPostsService;

@RestController
public class EditJobPostsController 
{

	private static final Logger Log = LoggerFactory.getLogger(EditJobPostsController.class);
	
	@Autowired
	private EditJobPostsService editJobPostsService;
	
	/**
	 * @author Prateek Kapoor
	 * This method returns all the job details corresponding to a job id
	 * @since 14-10-2020
	 * @param jobId
	 * @return Object of EditJobPostsDto if success; else returns null
	 */
	@Privilege(value = {"Employer","Candidate"})
	@GetMapping("/fetchJobDetails")
	public EditJobPostsDto viewJobPostDetails(@RequestParam("jobId") int jobId)
	{
		Log.debug("Request received in controller to fetch the job post details for job with id {}",jobId);
		return editJobPostsService.viewJobPostDetails(jobId);
	}
	
	/**
	 * @author Prateek Kapoor
	 * @since 14-10-2020
	 * This method updates the job post details corresponding to a job id
	 * @param editJobPostsDto
	 * @param jobDescriptionPathFilePath
	 * @return 1 if success; 
	 * -25 in case of exception
	 */
	@Privilege(value= {"Employer"})
	@PostMapping(value="/editJobPost",consumes=MediaType.ALL_VALUE)
	public int updateJobPostDetails(@ModelAttribute EditJobPostsDto editJobPostsDto)
	{
		Log.debug("Request received in controller to update the job post details with id {}",editJobPostsDto.getJobId());
		return editJobPostsService.updateJobPostDetails(editJobPostsDto);
	}
}
