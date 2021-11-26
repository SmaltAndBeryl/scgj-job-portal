package com.cgsc.dao;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgsc.common.AbstractTransactionalDao;
import com.cgsc.common.ReadApplicationConstants;
import com.cgsc.config.ApplyForJobConfig;

@Service
public class ApplyForJobDao extends AbstractTransactionalDao
{

	private static final Logger Log = LoggerFactory.getLogger(ApplyForJobDao.class);
	
	@Autowired
	private ApplyForJobConfig applyForJobConfig;
	
	
	/**
	 * @author Prateek Kapoor
	 * @since 15-10-2020
	 * This method checks if the candidate has already applied for a job post
	 * @param jobId
	 * @param candidateId
	 * @return 1 if already applied; 0 if not applied; -25 in case of exception
	 */
	public int getCheckJobApplicationExistence(int jobId, int candidateId)
	{
		Log.debug("Request received in dao to check if the candidate with id {} has already applied for job with id {}",candidateId, jobId);
		Map<String,Integer> params = new HashMap<>(2);
		params.put("jobId", jobId);
		params.put("candidateId", candidateId);
		Log.debug("Hashmap created, jobId {} and candidateId {} inserted into hashmap",params.get("jobId"), params.get("candidateId"));
		try 
		{
			Log.debug("In try block to execute the query and check if the candidate has already applied for a job post");
			return getJdbcTemplate().queryForObject(applyForJobConfig.getCheckJobApplicationExistence(), params, Integer.class);
		}
		catch (Exception e) 
		{
			Log.error("An exception occurred while checking the candidate application status - "+e);
			Log.error("Returning -25 to service");
			return -25;
		}
	}
	
	/**
	 * @author Prateek Kapoor
	 * @since 15-10-2020
	 * This method maps the candidate with a job id and sets the status ('In Review')
	 * @param jobId
	 * @param candidateId
	 * @return 1 if success; else -25 in case of exception
	 */
	public int applyForJob(int jobId, int candidateId)
	{
		Log.debug("Request received in dao to process the application of candidate with id {} in job with id {}",jobId, candidateId);
		Map<String,Object> params = new HashMap<>(3);
		params.put("jobId", jobId);
		params.put("candidateId", candidateId);
		params.put("status", ReadApplicationConstants.getApplicationStateInReview());
		Log.debug("Hashmap created hashmap and candidate id {} and job id {} inserted into hashmap",params.get("candidateId"),params.get("jobId"));
		try 
		{
			Log.debug("In try block to execute query and complete the application process");
			return getJdbcTemplate().update(applyForJobConfig.getApplyForJobPost(), params);
		}
		catch (Exception e) 
		{
			Log.error("An exception occurred while mapping the candidate id with job id "+e);
			Log.error("Returning -25 to service");
			return -25;
		}
	}
}
