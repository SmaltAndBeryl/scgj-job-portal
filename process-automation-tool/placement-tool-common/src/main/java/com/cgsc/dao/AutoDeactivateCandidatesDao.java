package com.cgsc.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cgsc.common.AbstractTransactionalDao;
import com.cgsc.common.ReadApplicationConstants;
import com.cgsc.config.AutoDeactivateCandidateConfig;

@Repository
public class AutoDeactivateCandidatesDao  extends AbstractTransactionalDao {

	private static final Logger Log = LoggerFactory.getLogger(AutoDeactivateCandidatesDao.class);
	@Autowired
	private AutoDeactivateCandidateConfig autoDeactivateCandidateConfig;
	
	
	/**
	 * @author Jyoti Singh
	 * @Since 17-12-2020
	 * 
	 * This method is fetches the user Id of inactive candidates (candidates with poll count greater than max poll count) and account status = active('Y')
	 * @return List of candidate Id (int), for which the poll count is greater than max poll count, null in case of exception
	 */
	
	public List<Integer> fetchInactiveCandidates(){
		Log.debug("Request received in dao method to fetch the user id of inactive candidates");
		Map<String,String> params = new HashMap<>(2);
		params.put("maxPollCount", ReadApplicationConstants.getMaxPollCount());
		params.put("activeFlagTrue", ReadApplicationConstants.getTrueFlag());
		Log.debug("Created hashmap with max poll count - {} and user active flag - {}",params.get("maxPollCount"),params.get("activeFlagTrue"));
		try
		{
			Log.debug("In try block to execute the query and fetch the list of user id of inactive candidates");
			return getJdbcTemplate().queryForList(autoDeactivateCandidateConfig.getFetchInactiveCandidatesSql(), params, Integer.class);
		}
		catch (Exception e) 
		{
			Log.error("An exception occurred while fetching the list of user id of inactive candidates - "+e);
			return null;
		}
	}
	
	
	/**
	 * @author Jyoti Singh
	 * @Since 17-12-2020
	 * @param candidate Id list (List<Integer>)
	 * This method deactivates the status of candidates given list of candidate user id
	 * @return 1 if success; -25 in case of exception
	 */
	public void deactivateCandidateStatus(List<Integer> candidateIdList)
	{
		Log.debug("Request received in dao to deactivate the candidates with given list of candidate Id");
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		  for (int candidateInformation : candidateIdList)
		    {
		    	Map<String,Object>params = new HashMap<>();
		    	Log.debug("Inserting user Id -  {} into hashmap",candidateInformation);
		    	params.put("userId",candidateInformation);
		    	params.put("activeFlagFalse", ReadApplicationConstants.getFalseFlag());
		    	list.add(params);
		    }
		  Log.debug("Created List of Hashmap and inserted the hashmap objects into the list");
		  @SuppressWarnings("rawtypes")
			Map[] batchUpdate = list.toArray(new HashMap[list.size()]); //Casted the list to array of Map
		  try 
		  {
			 Log.debug("In try block to execute batch update to deactivate candidates");
			 getJdbcTemplate().batchUpdate(autoDeactivateCandidateConfig.getDeactivateCandidatesSql(), batchUpdate);
			 Log.debug("The inactive candidates deactivated successfully");
		  } 
		  catch (Exception e)
		  {
			Log.error("An exception occurred while deactivating the inactive candidates - "+e);
		  }
	}
	
}
