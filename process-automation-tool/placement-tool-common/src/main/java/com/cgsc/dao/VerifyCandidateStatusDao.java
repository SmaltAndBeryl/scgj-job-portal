package com.cgsc.dao;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cgsc.common.AbstractTransactionalDao;
import com.cgsc.common.ReadApplicationConstants;
import com.cgsc.config.VerifyCandidateStatusConfig;


@Repository
public class VerifyCandidateStatusDao extends AbstractTransactionalDao{

	private static final Logger Log = LoggerFactory.getLogger(VerifyCandidateStatusDao.class);

	@Autowired
	private VerifyCandidateStatusConfig verifyCandidateStatusConfig;
	
	/**
	 * @author Jyoti Singh
	 * @since 21-10-2020
	 * 
	 * This method receives the candidateId(int) to update the poll count of candidate
	 * returns 1 on success,else -25 in case of exception
	 * 
	 * @param candidateId (int)
	 **/
	public int updatePollCount(int candidateId) {
		Log.debug("Request received in dao method to update the poll count of candidate with id - {}",candidateId);
		Map<String,Integer> params= new HashMap<>(2);
		params.put("candidateId", candidateId);
		params.put("pollCountZero", ReadApplicationConstants.getMinPollCount());
		
		Log.debug("The parameters inserted in hasmap include candidate Id -{}, poll count - {}", params.get("candidateId"),params.get("pollCountZero"));
		try {
			Log.debug("In try block to execute the query to update candidate poll count");
			return getJdbcTemplate().update(verifyCandidateStatusConfig.getUpdateCandidateVerificationStatus(), params);
		}
		catch(Exception e) {
			Log.debug("An exception occured while updating the poll count for the candidate {}",e);
			return - 25;
		}
		
	}
	
	
	/**
	 * @author Jyoti Singh
	 * @since 21-10-2020
	 * 
	 * This method receives the candidateId(int) to check the poll count of candidate
	 * returns whole number on success, else -25 in case of exception
	 * 
	 * @param candidateId (int)
	 **/
	public int checkCandidatePollCount(int candidateId) {
		Log.debug("Request received in dao method to check the poll count of candidate with id - {}",candidateId);
		Map<String,Object> params= new HashMap<>(1);
		params.put("candidateId", candidateId);
		
		Log.debug("The parameters inserted in hashmap include candidate Id - {}",params.get("candidateId"));
		
		try {
			Log.debug("In try block to execute the query to check the poll count of candidate with id - {}",candidateId);
			return getJdbcTemplate().queryForObject(verifyCandidateStatusConfig.getCheckCandidatePollCount(), params, Integer.class);
		}
		catch(Exception e) {
			Log.debug("An exception occured while fetching the poll count of candidate {}",e);
			return - 25;
		}
		
	}
	
}
