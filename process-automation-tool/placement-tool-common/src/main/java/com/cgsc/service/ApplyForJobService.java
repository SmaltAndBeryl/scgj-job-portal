package com.cgsc.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgsc.dao.ApplyForJobDao;
import com.cgsc.utility.GetLoggedInUserDetailsUtility;

@Service
public class ApplyForJobService 
{

	private static final Logger Log = LoggerFactory.getLogger(ApplyForJobService.class);
	
	@Autowired
	private ApplyForJobDao applyForJobDao;
	
	/**
	 * @author Prateek Kapoor
	 * @since 15-10-2020
	 * This method checks if the candidate has already applied for the training
	 * 	if true 
	 * 		returns -66
	 *  else
	 *  	sends request to map candidate id with jobId and set the status as In Review
	 * @param jobId
	 * @param candidateId
	 * @return 1 if success; 
	 * 	-25 in case of exception; 
	 *  -60 if candidate has already applied for the job (represented by jobId)
	 */
	public int applyForJob(int jobId)
	{
		Log.debug("Request received in service to process the application of logged in candidate  in job with id {}",jobId);
		Integer candidateId = GetLoggedInUserDetailsUtility.getUserIdFromSession();
		Log.debug("User id of logged in candidate fetched from session is {}",candidateId);
		Log.debug("Sending request to check if the candidate with id {} has already applied for job with id {}",candidateId,jobId);
		int candidateApplicationStatus = applyForJobDao.getCheckJobApplicationExistence(jobId, candidateId);
		if(candidateApplicationStatus==-25)
		{
			Log.error("Could not check candidate application status for job with id {}. Returning -25 to controller",jobId);
			return -25;
		}
		else if(candidateApplicationStatus==1)
		{
			Log.debug("Candidate with id {} has already applied for job with id {}. Returning -60 to controller",candidateId,jobId);
			return -60;
		}
		else
		{
			Log.debug("Candidate with id {} has not applied for job with id {}",candidateId, jobId);
			Log.debug("Sending request to complete the job application process for the logged in candidate");
			return applyForJobDao.applyForJob(jobId, candidateId);
		}
	}
}
