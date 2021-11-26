package com.cgsc.dao;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cgsc.common.AbstractTransactionalDao;
import com.cgsc.common.ReadApplicationConstants;
import com.cgsc.config.DeleteJobPostConfig;

@Repository
public class DeleteJobPostDao extends AbstractTransactionalDao{

	private static final Logger Log = LoggerFactory.getLogger(DeleteJobPostDao.class);
	
	@Autowired
	DeleteJobPostConfig deleteJobPostConfig;
	
	/**
	 * 
	 * @author Sarthak Bhutani
	 * @param jobId (int)
	 * @return 1 if success, -25 if exception
	 * Method for Closing a job post by an Employer
	 */
	public int deleteJobPost(int jobId) {
		Log.debug("Request Received by Dao to delete job post with id: {}",jobId);
		Map<String, Object> params = new HashMap<>(2);
		params.put("jobId",jobId);
		params.put("falseFlag",ReadApplicationConstants.getFalseFlag());
		Log.debug("Hashmap generated with id : {}, falseFlag : {}",params.get("jobId"),params.get("falseFlag") );
		try {
			Log.debug("Inside try block of Dao, trying to delete job post with id :{}",jobId);
			return getJdbcTemplate().update(deleteJobPostConfig.getDeleteJobPost(),params);
		}
		catch(Exception e){
			Log.error("Error occured while deleting the job post with id : {}, Exception : {}",jobId, e);
			return -25;	
		}
	}

	/**
	 *
	 * @author Sarthak Bhutani
	 * @since 07-01-2021
	 * @param jobId (int)
	 * @return 1 if any candidate has applied for job post; 0 if no candidate has applied; -25 if exception
	 * Method for checking if the job post status is published and if any candidate has applied on it
	 */
	public int checkIfAnyCandidateHasAppliedForPublishedJobPost(int jobId) {
		Log.debug("Request received by dao to check if the job post status is published and if any candidate has applied on it with job post id: {}",jobId);
		Map<String, Object> params = new HashMap<>(2);
		params.put("jobId",jobId);
		params.put("publishedStatus",ReadApplicationConstants.getPublishedState());
		Log.debug("Hashmap generated with id : {}, publishedStatus : {}",params.get("jobId"),params.get("publishedStatus") );
		try {
			Log.debug("Inside try block of Dao, trying to check if the job post status is published and if any candidate has applied on it with job post id: {}",jobId);
			return getJdbcTemplate().queryForObject(deleteJobPostConfig.getCheckIfAnyCandidateHasAppliedForPublishedJobPost(),params, Integer.class);
		}
		catch(Exception e){
			Log.error("Error occured while checking if the job post status is published and if any candidate has applied on it, Exception : {}", e);
			return -25;
		}
	}
}
