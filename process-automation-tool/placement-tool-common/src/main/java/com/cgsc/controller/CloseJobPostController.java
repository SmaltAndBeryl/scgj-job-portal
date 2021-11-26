package com.cgsc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cgsc.common.Privilege;
import com.cgsc.service.CloseJobPostService;

@RestController
public class CloseJobPostController {

	private static final Logger Log = LoggerFactory.getLogger(CloseJobPostController.class);
	
	@Autowired
	CloseJobPostService closeJobPostService;
	
	/**
	 * @author Sarthak bhutani
	 * @since 14-10-2020
	 * @param id
	 * @return 1 if success, -25 if exception
	 * Method for closing a job post by an employer based on id
	 */
	@Privilege(value= {"Employer"})
	@GetMapping("/closeJobPost")
	public int CloseJobPostService(@RequestParam("id") int jobId){
		Log.debug("Request received in controller to close job post with id: {}",jobId);
		return closeJobPostService.closeJobPost(jobId);
	}
}
