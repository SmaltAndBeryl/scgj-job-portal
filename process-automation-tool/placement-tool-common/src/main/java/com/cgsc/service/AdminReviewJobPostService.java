package com.cgsc.service;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cgsc.common.ReadApplicationConstants;
import com.cgsc.dao.AdminReviewJobPostDao;
import com.cgsc.dto.UpdateJobPostApprovalStatusDto;
import com.cgsc.dto.ViewJobPostByApprovalStatusDto;

@Service
public class AdminReviewJobPostService {

	private static final Logger Log = LoggerFactory.getLogger(AdminReviewJobPostService.class);
	
	@Autowired
	private AdminReviewJobPostDao adminReviewJobPostDao;
	
	/**
	 * @author Jyoti Singh
	 * @since 08-12-2020
	 * @param - job approval status (String)
	 * This Method to fetch the list of published job post details with the approval status received
	 * 
	 * @return Collection {@link ViewJobPostByApprovalStatusDto} if success; else null
	 */
	public Collection<ViewJobPostByApprovalStatusDto> viewJobPostWithApprovalStatus(String status) {
		Log.debug("Request recieved in service to fetch all published job post with status - {}",status);
		return adminReviewJobPostDao.fetchJobPostWithApprovalStatus(status);
	}

	
	/**
	 * @author Jyoti Singh
	 * @since 08-12-2020
	 * This method updates the approval status of job post with the status received and if the job post is rejected, inserts the admin comments
	 * 
	 * @return 1 if success;  else -25 exception and rollsback the transaction
	 */
	@Transactional(rollbackFor=Exception.class)
	public int updateJobPostApprovalStatus(UpdateJobPostApprovalStatusDto updateJobPostApprovalStatusDto){
		Log.debug("Request recieved in service to update the job post with id- {}",updateJobPostApprovalStatusDto.getJobId());
		try {
			
		
		if(updateJobPostApprovalStatusDto.getUpdatedStatus().equalsIgnoreCase(ReadApplicationConstants.getJobApprovalStatusApproved())) {
			Log.error("The updated received from front end is - {}",updateJobPostApprovalStatusDto.getUpdatedStatus());
			return adminReviewJobPostDao.updateJobPostApprovalStatus(updateJobPostApprovalStatusDto);
		}
		else if(updateJobPostApprovalStatusDto.getUpdatedStatus().equalsIgnoreCase(ReadApplicationConstants.getJobApprovalStatusRejected())) {
			Log.error("The updated received from front end is - {}",updateJobPostApprovalStatusDto.getUpdatedStatus());
			
			Log.debug("Sending request to dao method to update job post status");
			adminReviewJobPostDao.updateJobPostApprovalStatus(updateJobPostApprovalStatusDto);
			
			Log.debug("Sending request to dao method to insert the admin comments for rejecting job post");
			return adminReviewJobPostDao.insertAdminComments(updateJobPostApprovalStatusDto);
		}
		else
		{
			Log.error("Invalid status received from front end - {}",updateJobPostApprovalStatusDto.getUpdatedStatus());
			Log.error("Returning -88 to controller");
			return -88;
		}
		}catch(Exception e) {
			Log.debug("An exception occured while updating job post status, returning -25 to controller");
			return -25;
		}
	}

}
