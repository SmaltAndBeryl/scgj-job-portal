package com.cgsc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cgsc.common.Privilege;
import com.cgsc.service.DeleteJobPostService;

@RestController
public class DeleteJobPostController {

	private static final Logger Log = LoggerFactory.getLogger(DeleteJobPostController.class);
	
	@Autowired
	DeleteJobPostService deleteJobPostService;
	
	/**
	 * @author Sarthak Bhutani
	 * @since 14-10-2020
	 * @param id
	 * @return 1 if success, -25 if exception
	 * Method for Deleting a job post by an employer based on id
	 */
	@Privilege(value= {"Employer"})
	@GetMapping("/deleteJobPost")
	public int DeleteJobPostDao(@RequestParam("id") int jobId) {
		Log.debug("Request received in controller by employer to delete job post with id "+jobId);
		return deleteJobPostService.DeleteJobPost(jobId);
	}
}
