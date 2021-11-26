package com.cgsc.controller;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cgsc.common.Privilege;
import com.cgsc.dto.GetAllJobPostByEmployerDto;
import com.cgsc.dto.ViewJobPostDto;
import com.cgsc.dto.ViewPublishedJobPostDto;
import com.cgsc.service.ViewJobPostService;

@RestController
public class ViewJobPostController {

	@Autowired
	private ViewJobPostService viewJobPostService;
	
	private static final Logger Log = LoggerFactory.getLogger(ViewJobPostController.class);
	
	/**
	 * @author Sarthak Bhutani
	 * @since 12-10-2020
	 * this method returns the collection of all job posts which are published & are active & whose application date is greater than equal to current date
	 * @return Collection of {@link ViewJobPostDto} if success; else returns null
	 */
	@GetMapping("/viewJobPost")
	public Collection<ViewJobPostDto> viewJobPost(){
		Log.debug("Request recieved in controller to fetch job posts which are published & are active & whose application date is greater than equal to current date");
		return viewJobPostService.viewJobPost();
	}
	
	/**
	 * @author Sarthak Bhutani
	 * @since 13-10-2020
	 * this method returns the collection of all active job posts created by logged in employer
	 * @return Collection of {@link GetAllJobPostByEmployerDto} if success; else returns null
	 */
	@Privilege(value= {"Employer"})
	@GetMapping("/getAllJobPostByEmployer")
	public Collection<GetAllJobPostByEmployerDto> getAllJobPostByEmployer(){
		Log.debug("Request recieved in controller to fetch all active job post created by logged in employer");
		return viewJobPostService.getAllJobPostByEmployer();
	}
	
	/**
	 * @author Sarthak Bhutani
	 * @since 14-10-2020
	 * @param jobStatus
	 * @param page
	 * this method returns the collection of all active job posts based on status by logged in employer
	 * @return Collection of {@link ViewPublishedJobPostDto} if success; else returns null
	 *
	 * @updatedBy Sarthak Bhutani
	 * @updatedOn 18/12/2020
	 * @update - Added param page in Controller, and passing it as a param in Service
	 */
	@Privilege(value= {"Employer"})
	@GetMapping("/viewJobPostByStatus")
	public Collection<ViewPublishedJobPostDto> viewJobPostByStatus(@RequestParam(name="jobStatus") String jobStatus, @RequestParam(name="page") String page){
		Log.debug("Request received in controller to fetch all job post created by logged in employer with job status : {} for page : {}",jobStatus,page);
		return viewJobPostService.viewJobPostByStatus(jobStatus,page);
	}
	
	/**
	 * @author Sarthak Bhutani
	 * @since 21-10-2020
	 * @return Collection of {@link GetAllJobPostByEmployerDto} if success, else return null
	 * this method return the collection of all deleted(active_flg='N') job posts by an employer
	 */
	@Privilege(value={"Employer"})
	@GetMapping("/getAllJDeletedJobPostByEmployer")
	public Collection<GetAllJobPostByEmployerDto> getAllDeletedJobPostByEmployer(){
		Log.debug("Request received in controller to get all deleted job posts by an employer");
		return viewJobPostService.getAllDeletedJobPostByEmployer();
	}
	
	/**
	 * @author Prateek Kapoor
	 * @since 22-10-2020
	 * This method fetches all the active job posts created by the logged in user with status as closed or published
	 * @return Collection of {@link GetAllJobPostByEmployerDto} if success; else returns null
	 */
	@Privilege(value={"Employer"})
	@GetMapping("/viewPublishedOrClosedJobs")
	public Collection<GetAllJobPostByEmployerDto> viewClosedOrPublishedJobPosts()
	{
		Log.debug("Request received in controller to fetch the closed or published job posts created by the logged in user");
		return viewJobPostService.viewClosedOrPublishedJobPosts();
	}
}
