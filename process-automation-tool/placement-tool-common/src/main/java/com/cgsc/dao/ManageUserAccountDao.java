package com.cgsc.dao;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cgsc.common.AbstractTransactionalDao;
import com.cgsc.config.ManageUserAccountConfig;

@Repository
public class ManageUserAccountDao extends AbstractTransactionalDao
{
	private static final Logger Log = LoggerFactory.getLogger(ManageUserAccountDao.class);
	
	@Autowired
	private ManageUserAccountConfig manageUserAccountConfig;
	
	/**
	 * @author Prateek Kapoor
	 * @since 29-10-2020
	 * This method updates the user account status (represented by is_active_flg) for the received userId
	 * @param userId
	 * @param updatedStatus
	 * @return 1 if success;
	 * -25 in case of exception
	 */
	public int updateUserAccountStatus(int userId, String updatedStatus)
	{
		Log.debug("Request received in dao to update the account status of the user with id {} to {}",userId, updatedStatus);
		Map<String,Object> params = new HashMap<>(2);
		params.put("userId", userId);
		params.put("updatedStatus", updatedStatus);
		Log.debug("Hashmap created and user id {} inserted into hashmap",params.get("userId"));
		try 
		{
			Log.debug("In try block to execute the query and update the user account status");
			return getJdbcTemplate().update(manageUserAccountConfig.getUpdateUserAccountStatus(), params);
		}
		catch (Exception e) 
		{
			Log.error("An exception occurred while updating the user account status - "+e);
			Log.error("Returning -25 to service");
			return -25;
		}
	}
}
