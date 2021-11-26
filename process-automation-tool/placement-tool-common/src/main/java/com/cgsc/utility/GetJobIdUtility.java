package com.cgsc.utility;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cgsc.common.AbstractTransactionalDao;
import com.cgsc.config.GetJobIdConfig;

@Repository
public class GetJobIdUtility extends AbstractTransactionalDao{
	
	private static final Logger Log = LoggerFactory.getLogger(GetJobIdUtility.class);
	
	@Autowired
	private GetJobIdConfig getJobIdConfig;
	
	/**
	 * @author Jyoti Singh
	 * @since 03-12-2020
	 * @param jobId (int)
	 * This method returns the unique job Id (string) for a given integer type job Id
	 * @return jobId (String) if success, else null
	 */
	public String fetchJobId(int jobId) {
		Log.debug("Request received in utility method to fetch job post Id with id - {}",jobId);
		Map<String,Integer> params = new HashMap<>(1);
		params.put("jobId",jobId);
		Log.debug("Hashmap created with job id- {}",params.get("jobId"));
		try 
		{
			Log.debug("In try block to fetch job post Id {}",jobId);
			return getJdbcTemplate().queryForObject(getJobIdConfig.getGetJobPostId(), params, String.class);
		} 
		catch (Exception e) 
		{
			Log.error("An exception occurred while fetching job post Id "+e);
			return null;
		}
		
	}

}
