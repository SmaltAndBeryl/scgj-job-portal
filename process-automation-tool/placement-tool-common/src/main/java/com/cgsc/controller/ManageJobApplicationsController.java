package com.cgsc.controller;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cgsc.common.Privilege;
import com.cgsc.dto.ManageJobApplicationsDto;
import com.cgsc.dto.ViewJobApplicationsDto;
import com.cgsc.service.ManageJobApplicationsService;

@Privilege(value= {"Employer"})
@RestController
public class ManageJobApplicationsController 
{

	private static final Logger Log = LoggerFactory.getLogger(ManageJobApplicationsController.class);
	
	@Autowired
	private ManageJobApplicationsService manageJobApplicationsService;
	
	/**
	 * @author Prateek Kapoor
	 * @since 16-10-2020
	 * This method fetches the collection of candidate details who applied for a specific jobId
	 * @param jobId
	 * @return Collection of {@link ViewJobApplicationsDto} if success; else returns null
	 */
	@Privilege(value= {"Employer"})
	@GetMapping("/viewJobApplications")
	public Collection<ViewJobApplicationsDto> viewJobApplications(@RequestParam("jobId") int jobId)
	{
		Log.debug("Request received in controller to view list of applications for job with id {}",jobId);
		return manageJobApplicationsService.viewJobApplications(jobId);
	}
	
	/**
	 * @author Prateek Kapoor
	 * @since 16-10-2020
	 * This method updates the application status of candidates for a job id
	 * @param manageJobApplicationsDto
	 * @return 1 if success; 
	 * -25 in case of exception; 
	 * -88 invalid status
	 * -11 if there is no vacancy and no more candidates can be hired
	 * -61 if the salary offered to candidate is less than the minimum salary of job posted
	 */
	@Privilege(value= {"Employer"})
	@PostMapping(value= "/updateCandidateApplicationStatus",consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public int updateCandidateApplicationStatus(@RequestBody ManageJobApplicationsDto manageJobApplicationsDto)
	{
		Log.debug("Request received in controller to update the job application status for candidates for jobId {} to {}",manageJobApplicationsDto.getJobId(),manageJobApplicationsDto.getUpdatedStatus());
		return manageJobApplicationsService.updateCandidateApplicationStatus(manageJobApplicationsDto);
	}
}
