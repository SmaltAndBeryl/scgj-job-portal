package com.cgsc.dao;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cgsc.common.AbstractTransactionalDao;
import com.cgsc.common.ReadApplicationConstants;
import com.cgsc.config.UpdateJobPostStatusConfig;

@Repository
public class UpdateJobPostStatusDao extends AbstractTransactionalDao
{

	private static final Logger Log = LoggerFactory.getLogger(UpdateJobPostStatusDao.class);
	
	@Autowired
	private UpdateJobPostStatusConfig updateJobPostStatusConfig;
	
	
	/**
	 * @author Prateek Kapoor
	 * @since 15-10-2020
	 * This method updates the status of the job post against the received job Id to the received status
	 * @param jobId
	 * @param updatedJobStatus
	 * @return 1 if success; 
	 * -25 in case of exception
	 */
	public int updateJobPostStatus(int jobId, String updatedJobStatus)
	{
		Log.debug("Request received in dao to update the job post status for job id {} to status {}",jobId,updatedJobStatus);
		Map<String,Object> params = new HashMap<>(2);
		params.put("updatedJobStatus", updatedJobStatus);
		params.put("jobId", jobId);
		Log.debug("Hashmap created and jobId {}, updatedStatus {} inserted into hashmap", params.get("jobId"), params.get("jobStatusPublished"));
		try 
		{
			Log.debug("In try block to execute the query and update the status of job post with id {} to updated status {}",jobId, updatedJobStatus);
			return getJdbcTemplate().update(updateJobPostStatusConfig.getUpdateJobPostsStatus(),params);
		}
		catch (Exception e) 
		{
			Log.error("An exception occurred while updating the status of job post - "+e);
			return -25;
		}
	}
	
	/**
	 * @author Prateek Kapoor
	 * @since 15-10-2020
	 * This method updates the status of the job post to published for the received jobId
	 * @param jobId
	 * @return 1 if success;
	 *  -25 in case of exception
	 *  
	 *  @updated by - Jyoti Singh
	 *  @updated on - 09-12-2020
	 */
	public int publishJobPost(int jobId)
	{
		Log.debug("Request received in dao to set the status of job post with id {} to published",jobId);
		Map<String,Object> params = new HashMap<>(3);
		params.put("jobId", jobId);
		params.put("jobApprovalStatusInReview",ReadApplicationConstants.getJobApprovalStatusInReview());
		params.put("jobStatusPublished", ReadApplicationConstants.getPublishedState());
		Log.debug("Hashmap created and job id {} and status {} inserted into hashmap", params.get("jobId"),params.get("jobStatusPublished"));
		try 
		{
			Log.debug("In try block to execute the query and update the status of job post to published");
			return getJdbcTemplate().update(updateJobPostStatusConfig.getPublishJobPost(), params);
		}
		catch (Exception e) 
		{
			Log.error("An exception occurred while publishing the job post "+e);
			return -25;
		}
	}
	
}
