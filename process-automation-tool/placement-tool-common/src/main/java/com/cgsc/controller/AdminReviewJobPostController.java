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
import com.cgsc.dto.UpdateJobPostApprovalStatusDto;
import com.cgsc.dto.ViewJobPostByApprovalStatusDto;
import com.cgsc.service.AdminReviewJobPostService;

@RestController
public class AdminReviewJobPostController {

	private static final Logger Log = LoggerFactory.getLogger(AdminReviewJobPostController.class);
	
	@Autowired
	private AdminReviewJobPostService adminReviewJobPostService;
	
	/**
	 * @author Jyoti Singh
	 * @since 08-12-2020
	 * @param - job approval status (String)
	 * @apiNote - Method to fetch the list of published job post details with the approval status received
	 * 
	 * @return Collection {@link ViewJobPostByApprovalStatusDto} if success; else null
	 */
	@Privilege(value= {"Admin"})
	@GetMapping("/fetchJobPostDetailsWithStatus")
	public Collection<ViewJobPostByApprovalStatusDto> viewJobPostWithApprovalStatus(@RequestParam("status") String status){
		Log.debug("Request recieved in controller to fetch all published job post with status - {}",status);
		return adminReviewJobPostService.viewJobPostWithApprovalStatus(status);
	}
	
	
	/**
	 * @author Jyoti Singh
	 * @since 08-12-2020
	 * @apiNote - This method updates the job post approval status also if the job post is rejected inserts the admin comments for rejecting the job post 
	 * 
	 * @return 1 if success;  else -25 in case of exception
	 */
	@Privilege(value= {"Admin"})
	@PostMapping(value= "/updateJobPostApprovalStatus",consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public int updateJobPostApprovalStatus(@RequestBody UpdateJobPostApprovalStatusDto updateJobPostApprovalStatusDto){
		Log.debug("Request recieved in controller to update the job post with status - {}",updateJobPostApprovalStatusDto.getUpdatedStatus());
		try 
		{
			return adminReviewJobPostService.updateJobPostApprovalStatus(updateJobPostApprovalStatusDto);
		} catch (Exception e) {
			Log.debug("An exception occured while updating job post status, returning -25");
			return -25;
		}
		
	}
	
	
	
}
