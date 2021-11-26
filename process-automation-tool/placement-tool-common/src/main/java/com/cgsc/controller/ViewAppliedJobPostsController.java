package com.cgsc.controller;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cgsc.common.Privilege;
import com.cgsc.dto.ViewAppliedJobsDto;
import com.cgsc.dto.ViewJobPostDto;
import com.cgsc.service.ViewAppliedJobPostsService;

@RestController
public class ViewAppliedJobPostsController {

	private static final Logger Log = LoggerFactory.getLogger(ViewAppliedJobPostsController.class);
	
	@Autowired
	private ViewAppliedJobPostsService viewAppliedJobPostsService;
	
	/**
	 * @author Jyoti Singh
	 * @since 16-10-2020
	 * The method fetches the details of jobs in which the logged-in candidate has applied
	 * @return Collection of {@link ViewJobPostDto} if success; else returns null in case of exception
	 */
	@Privilege(value= {"Candidate"})
	@GetMapping("/candidateViewAppliedJobs")
	public Collection<ViewAppliedJobsDto> viewAppliedJobs(){
		Log.debug("Request recieved in controller to fetch Job Posts in which the logged in candidate has applied");
		return viewAppliedJobPostsService.viewAppliedJobs();
	}
	
}
