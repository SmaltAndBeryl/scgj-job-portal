package com.cgsc.dao;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cgsc.common.AbstractTransactionalDao;
import com.cgsc.common.ReadApplicationConstants;
import com.cgsc.config.CloseJobPostConfig;

@Repository
public class CloseJobPostDao extends AbstractTransactionalDao{

	private static final Logger Log = LoggerFactory.getLogger(CloseJobPostDao.class);
	
	@Autowired
	CloseJobPostConfig closeJobPostConfig;

	/**
	 * @author Sarthak bhutani
	 * @since 14-10-2020
	 * @param jobId
	 * @return 1 if success, -25 if exception
	 * Method for Closing a job post by an employer based on id
	 */
	public int closeJobPost(int jobId){
		Log.debug("Request recieved by dao to close job post with id : {} ",jobId);
		Map<String,Object> params = new HashMap<>(2);
		params.put("closedState",ReadApplicationConstants.getClosedState());
		params.put("jobId",jobId);
		Log.debug("Hashmap created with closedState : {} , jobId : {}",params.get("closedState"),params.get("jobId"));
		try {
			Log.debug("In try block of Dao, closing the job post with jobId : {}",jobId);
			return getJdbcTemplate().update(closeJobPostConfig.getCloseJobPost(),params);
		}
		catch(Exception e) {
			Log.error("Exception occured while closing the job post with jobId : {}, Exception : {}",jobId, e);
			return -25;			
		}
		

	}
	
}
