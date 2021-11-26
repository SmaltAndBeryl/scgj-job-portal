	package com.cgsc.dao;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cgsc.common.AbstractTransactionalDao;
import com.cgsc.config.LogoutConfig;

@Repository
public class LogoutDao extends AbstractTransactionalDao
{

	private static final Logger Log = LoggerFactory.getLogger(LogoutDao.class);
	
	@Autowired
	private LogoutConfig logoutConfig;
	
	/**
	 * @author Prateek Kapoor
	 * @since 06-07-2020
	 * This method resets the OTP of the user whem they logout of the system
	 * @param userId
	 * @return 1 if success; -25 in case of exception
	 */
	public int resetUserOtp(int userId)
	{
		Log.debug("Request received in dao to reset user OTP for user with id {}",userId);
		Map<String,Integer> params = new HashMap<>(1);
		params.put("userId", userId);
		Log.debug("Hashmap created and user id {} inserted into hashmap",params.get("userId"));
		try 
		{
			Log.debug("In try block to reset user OTP");
			return getJdbcTemplate().update(logoutConfig.getResetUserOtp(), params);
			
		} catch (Exception e)
		{
			Log.error("An exception occured while resetting user OTP "+e);
			return -25;
		}
	}
}
