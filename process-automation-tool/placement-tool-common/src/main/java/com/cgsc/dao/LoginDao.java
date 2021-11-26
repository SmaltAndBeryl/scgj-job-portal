package com.cgsc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.cgsc.common.AbstractTransactionalDao;
import com.cgsc.common.ReadApplicationConstants;
import com.cgsc.config.LoginConfig;
import com.cgsc.dto.SessionManagementDto;

@Repository
public class LoginDao extends AbstractTransactionalDao
{

	private static final Logger Log = LoggerFactory.getLogger(LoginDao.class);
	private static final LoginRowMapper Login_Row_Mapper = new LoginRowMapper();
	
	@Autowired
	private LoginConfig loginConfig;
	
	/**
	 * @author Prateek Kapoor
	 * @since 08-10-2020
	 * This method checks whether the mobile number exists in the database or not
	 * @param mobileNumber
	 * @return 1 if exists;
	 *  0 if not exists;
	 *  -25 in case of exception
	 */
	public int checkMobileNumberExistence(long mobileNumber)
	{
		Log.debug("Request received in dao to check the existence of mobile number {}",mobileNumber);
		Map<String,Long> params = new HashMap<>(1);
		params.put("mobileNumber", mobileNumber);
		Log.debug("Hashmap created and mobile number {} inserted into hashmap",params.get("mobileNumber"));
		try 
		{
			Log.debug("In try block to check the existence of mobile number");
			return getJdbcTemplate().queryForObject(loginConfig.getCheckMobileNumber(), params, Integer.class);
		}
		catch (Exception e) 
		{
			Log.error("An exception occurred while checking the mobile number existence - "+e);
			return -25;
		}
	}
	
	/**
	 * @author Prateek Kapoor
	 * @since 08-10-2020
	 * This method checks whether the user with mobile number has an active account or not
	 * @param mobileNumber
	 * @return 1 if active; 0 if inactive; -25 in case of exception
	 */
	public int checkUserAccountStatus(long mobileNumber)
	{
		Log.debug("Request received in dao to check if the user account with mobile number {} is active ",mobileNumber);
		Map<String,Object> params = new HashMap<>(3);
		params.put("mobileNumber", mobileNumber);
		params.put("activeFlagTrue", ReadApplicationConstants.getTrueFlag());
		params.put("enrolmentStatusApproved", ReadApplicationConstants.getApprovedFlag());
		Log.debug("Hashmap created and mobile number {} inserted into hashmap",params.get("mobileNumber"));
		try 
		{
			Log.debug("In try block to check the account status of user with mobile number");
			return getJdbcTemplate().queryForObject(loginConfig.getCheckAccountActivationStatus(), params, Integer.class);
		}
		catch (Exception e) 
		{
			Log.error("An exception occurred while checking the account status of user - "+e);
			return -25;
		}
	}
	
	/**
	 * @author Prateek Kapoor
	 * @since 08-10-2020
	 * This method updates the OTP of the user
	 * @param mobileNumber
	 * @param userOtp
	 * @return 1 if success; -25 in case of exception
	 */
	public int updateUserOtp(long mobileNumber, String userOtp)
	{
		Log.debug("Request received in dao to update the userOtp of user with mobile number {}",mobileNumber);
		Map<String,Object> params = new HashMap<>(2);
		params.put("mobileNumber", mobileNumber);
		params.put("userOtp", userOtp);
		Log.debug("Hashmap created and mobile number {} inserted into hashmap",params.get("mobileNumber"));
		try 
		{
			Log.debug("In try block to update the OTP of user with mobile number {}",params.get("mobileNumber"));
			return getJdbcTemplate().update(loginConfig.getUpdateUserOtp(), params);
		}
		catch (Exception e) 
		{
			Log.error("An exception occurred while updating the OTP oft the user - "+e);
			return -25;
		}		
	}
	
	/**
	 * @author Prateek Kapoor
	 * @since 08-20-2020
	 * This method returns the user details for the given mobile number
	 * @param mobileNumber
	 * @return {@link SessionManagementDto} object if success; else returns null
	 */
	public SessionManagementDto getValidUserDetails(long mobileNumber) 
	{
		Log.debug("Request received in dao to fetch the user details for mobile number {}",mobileNumber);
		Map<String,Long>params = new HashMap<>();
		params.put("mobileNumber", mobileNumber);
		Log.debug("Hashmap created and parameter inserted into hashmap");
		try 
		{
			Log.debug("In try block to fetch the user details");
			return getJdbcTemplate().queryForObject(loginConfig.getUserDetailsSql(), params, Login_Row_Mapper);
		}
		catch (Exception e) 
		{
			Log.debug("An exception occured while fetching the user details "+e);
			return null;
		}
		
	}
	
	/**
	 * @author Prateek Kapoor
	 * @since 08-10-2020
	 * This method captures the last login time of user
	 * @param candidateId
	 * @return 1 if success; 0 if last login is not captured; -25 in case of exception
	 */
	public int resetCandidatePollCount(int candidateId)
	{
		Log.debug("Request received in dao to reset the poll count of the candidate with id {}",candidateId);
		Map<String,Integer>params = new HashMap<>(1);
		params.put("candidateId", candidateId);
		Log.debug("Hashmap created and candidate id {} inserted into hashmap",params.get("candidateId"));
		try 
		{
			Log.debug("In try block to reset the candidate poll count");
			return getJdbcTemplate().update(loginConfig.getResetPollCount(), params);
		}
		catch (Exception e)
		{
			Log.error("An exception occurred while updating the candidate poll count - "+e);
			return -25;
		}
	}

	/**
	 * @author Sarthak Bhutani
	 * @since 19/12/2020
	 * This method is for capturing the last login time of any user
	 * @param userId
	 * @return 1 if success, -25 if exception
	 */
	public int captureLoginTime(int userId){
		Log.debug("Request received in dao to capture the last login time for id {}",userId);
		Map<String,Integer>params = new HashMap<>(1);
		params.put("userId", userId);
		Log.debug("Hashmap created and value is inserted");
		try
		{
			Log.debug("In try block to set the login time");
			return getJdbcTemplate().update(loginConfig.getCaptureLoginTimeForUser(), params);
		}
		catch (Exception e)
		{
			Log.error("An exception occurred while updating the last login time - "+e);
			return -25;
		}
	}


	/**
	 * Row Mapper for {@link SessionManagementDto}
	 * @author Prateek Kapoor
	 *
	 */
	public static class LoginRowMapper implements RowMapper<SessionManagementDto>
	{
		@Override
		public SessionManagementDto mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			boolean isEnabled = false;
			Integer userId = rs.getInt("userId");
			String name = rs.getString("username");
			long mobileNumber = rs.getLong("mobileNumber");
			String otp = rs.getString("otp");
			String userRole = rs.getString("userRole");
			boolean accountStatus = rs.getBoolean("isActive");
			String enrolmentStatus = rs.getString("enrolmentStatus");
			if(accountStatus && enrolmentStatus.equalsIgnoreCase(ReadApplicationConstants.getApprovedFlag()))
			{
				isEnabled=true;
			}
			return new SessionManagementDto(userId, name, mobileNumber, otp, userRole, isEnabled);
		}
	}
}
