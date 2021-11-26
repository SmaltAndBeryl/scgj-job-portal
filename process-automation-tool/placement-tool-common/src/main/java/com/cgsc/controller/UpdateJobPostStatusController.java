package com.cgsc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cgsc.common.Privilege;
import com.cgsc.service.UpdateJobPostStatusService;

@RestController
public class UpdateJobPostStatusController 
{

	private static final Logger Log = LoggerFactory.getLogger(UpdateJobPostStatusController.class);
	
	@Autowired
	private UpdateJobPostStatusService updateJobPostStatusService;
	
	
	/**
	 * @author Prateek Kapoor
	 * @since 15-10-2020
	 * This method updates the status of the job post against the received job Id to the received status
	 * @param jobId
	 * @param updatedJobStatus
	 * @return 1 if success; 
	 * -25 in case of exception;
	 *  -30 in case unknown status is received
	 */
	@Privilege(value= {"Employer"})
	@GetMapping("/updateJobPostStatus")
	public int updateJobPostStatus(@RequestParam("jobId") int jobId, @RequestParam("updatedStatus") String updatedJobStatus)
	{
		Log.debug("Request received in service to update the job post status for job id {} to status {}",jobId,updatedJobStatus);
		return updateJobPostStatusService.updateJobPostStatus(jobId, updatedJobStatus);
	}
	
}
