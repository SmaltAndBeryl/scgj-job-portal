package com.cgsc.dao;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cgsc.common.AbstractTransactionalDao;
import com.cgsc.common.ReadApplicationConstants;
import com.cgsc.config.ResetMobileNumberConfig;

@Repository
public class ResetMobileNumberDao extends AbstractTransactionalDao{

	private static final Logger Log = LoggerFactory.getLogger(ResetMobileNumberDao.class);
	
	@Autowired
	private ResetMobileNumberConfig resetMobileNumberConfig;
	/**
	 * @author Sarthak Bhutani
	 * @since 27/10/2020
	 * @param oldMobileNumber
	 * @param newMobileNumber
	 * Method to Change user's mobile number from old(prev) to new
	 * @return if success: 1, else if exception: -25
	 */
	public int resetMobileNumber(long oldMobileNumber, long newMobileNumber) {
		Log.debug("Request recieved in dao to change mobile number from {} to {}",oldMobileNumber,newMobileNumber);
		Map<String, Object> params = new HashMap<>(2);
		params.put("oldMobileNumber",oldMobileNumber);
		params.put("newMobileNumber",newMobileNumber);
		params.put("userRoleCandidate",ReadApplicationConstants.getUserRoleCandidate());
		params.put("userRoleEmployer",ReadApplicationConstants.getUserRoleEmployer());
		Log.debug("Hashmap created with values, oldMobileNumber : {}, newMobileNumber : {}, userRoleCandidate : {}, userRoleEmployer : {} ",params.get("oldMobileNumber"),params.get("newMobileNumber"),params.get("userRoleCandidate"),params.get("userRoleEmployer"));
		try {
			Log.debug("In try block of dao to change mobile number of the user");
			return getJdbcTemplate().update(resetMobileNumberConfig.getResetMobileNumberFromOldToNew(),params);
		}
		catch(Exception e) {
			Log.error("An exception occurred while changing mobile number of user with Mobile Number : {}, exception : {} ",oldMobileNumber, e);
			return -25;
		}
	}
}
