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
import com.cgsc.config.GetUserDetailsByMobileNumberConfig;
import com.cgsc.dto.GetUserDetailsByMobileNumberDto; 


@Repository
public class GetUserDetailsByMobileNumberDao extends AbstractTransactionalDao{

	private static final Logger Log = LoggerFactory.getLogger(GetUserDetailsByMobileNumberDao.class);
	private static GetUserDetailsRowMapper getUserDetailsRowMapper = new GetUserDetailsRowMapper();
	@Autowired
	private GetUserDetailsByMobileNumberConfig getUserDetailsByMobileNumberConfig;
	
	/**
	 * @author Sarthak Bhutani
	 * @since 28/10/2020
	 * @param mobileNumber
	 * @return if success {@link GetUserDetailsByMobileNumberDto}, else if exception : null
	 * Fetch details of a user via mobile number
	 */
	public GetUserDetailsByMobileNumberDto getUserDetails(long mobileNumber) {
		Log.debug("Request received in dao to fetch user details for mobile number : {}",mobileNumber);
		Map<String,Object> params = new HashMap<>(3);
		params.put("mobileNumber",mobileNumber);
		params.put("userRoleEmployer",ReadApplicationConstants.getUserRoleEmployer());
		params.put("userRoleCandidate",ReadApplicationConstants.getUserRoleCandidate());
		Log.debug("Hashmap created with param, Mobile Number: {} ",params.get("mobileNumber"));
		try {
			Log.debug("In try block to fetch user details by mobile number : {}",mobileNumber);
			return getJdbcTemplate().queryForObject(getUserDetailsByMobileNumberConfig.getGetUserDetailsByMobileNumber(),params,getUserDetailsRowMapper);
		}
		catch(Exception e) {
			Log.error("An exception occurred while fetching user details for mobile number: {}, exception : {}", mobileNumber, e);
			return null;
		}	
	}
	
	/**
	 * @author Sarthak Bhutani
	 * RowMapper for {@link GetUserDetailsByMobileNumberDto} to fetch user details via mobile number
	 */
	private static class GetUserDetailsRowMapper implements RowMapper<GetUserDetailsByMobileNumberDto>{

		@Override
		public GetUserDetailsByMobileNumberDto mapRow(ResultSet rs, int rowNum) throws SQLException {
			String name = rs.getString("username");
			String state = rs.getString("state");
			long mobileNumber = rs.getLong("mobile_number");
			int pincode=rs.getInt("pincode");
			String userRole = rs.getString("user_role");
			String activeStatus = rs.getString("is_active_flg");
			return new GetUserDetailsByMobileNumberDto(name,state,mobileNumber,pincode,userRole,activeStatus);
		}
		
	}
}
