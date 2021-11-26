package com.cgsc.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cgsc.common.AbstractTransactionalDao;
import com.cgsc.config.SendVerificationLinkConfig;

@Repository
public class SendVerificationLinkDao extends AbstractTransactionalDao
{
	
	@Autowired
	private SendVerificationLinkConfig sendVerificationLinkConfig;
	
	private static final Logger Log = LoggerFactory.getLogger(SendVerificationLinkDao.class);
	
	/**
	 * @author Prateek Kapoor
	 * @Since 21-10-2020
	 * @param candidateDetails
	 * @return 1 if success; -25 in case of exception
	 */
	public int updateCandidatePollStatus(Collection<FetchCandidateDetailsDto> candidateDetails)
	{
		Log.debug("Request received in dao to update the candidate polling table for the received collection of candidate details");
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		  for (FetchCandidateDetailsDto candidateInformation : candidateDetails)
		    {
		    	Map<String,Object>params = new HashMap<>();
		    	Log.debug("Inserting candidateId {} into hashmap",candidateInformation.getId());
		    	params.put("userId", candidateInformation.getId());
		    	list.add(params);
		    }
		  Log.debug("Created List of Hashmap and inserted the hashmap objects into the list");
		  @SuppressWarnings("rawtypes")
			Map[] batchUpdate = list.toArray(new HashMap[list.size()]); //Casted the list to array of Map
		  try 
		  {
			 Log.debug("In try block to execute batch update for the candidates");
			 getJdbcTemplate().batchUpdate(sendVerificationLinkConfig.getIncrementCandidatePollCount(), batchUpdate);
			 return 1;
		  } 
		  catch (Exception e)
		  {
			Log.error("An exception occurred while updating the candidate account polling status - "+e);
			return -25;
		  }
	}
}
