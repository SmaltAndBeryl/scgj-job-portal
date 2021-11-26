package com.cgsc.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgsc.common.ReadApplicationConstants;
import com.cgsc.dao.UpdateJobPostStatusDao;

@Service
public class UpdateJobPostStatusService 
{

	private static final Logger Log = LoggerFactory.getLogger(UpdateJobPostStatusService.class);
	
	@Autowired
	private UpdateJobPostStatusDao updateJobPostStatusDao;
	
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
	public int updateJobPostStatus(int jobId, String updatedJobStatus)
	{
		Log.debug("Request received in service to update the job post status for job id {} to status {}",jobId,updatedJobStatus);
		if(updatedJobStatus.equalsIgnoreCase(ReadApplicationConstants.getPublishedState()))
		{
			Log.debug("The updated status is published, sending request to dao to update the job post status with id {} to published",jobId);
			return updateJobPostStatusDao.publishJobPost(jobId);
		}
		else if(updatedJobStatus.equalsIgnoreCase(ReadApplicationConstants.getNotPublishedState()) || updatedJobStatus.equalsIgnoreCase(ReadApplicationConstants.getClosedState()))
		{
			Log.debug("Sending request to update the job staus of job id {} to {}",jobId,updatedJobStatus);
			return updateJobPostStatusDao.updateJobPostStatus(jobId, updatedJobStatus);
		}
		else
		{
			Log.debug("Invalid job status received from controller - {}, returning -30 to the controller",updatedJobStatus);
			return -30;
		}
	}
}
