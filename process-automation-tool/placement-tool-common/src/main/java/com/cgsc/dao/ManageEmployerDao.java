package com.cgsc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.cgsc.common.AbstractTransactionalDao;
import com.cgsc.common.ReadApplicationConstants;
import com.cgsc.config.ManageEmployerConfig;
import com.cgsc.dto.ViewEmployerByActiveStatusDto;
import com.cgsc.dto.ViewEmployerDetailsDto;

@Repository
public class ManageEmployerDao extends AbstractTransactionalDao
{

	@Autowired
	private ManageEmployerConfig manageEmployerConfig;
	
	private static final Logger Log = LoggerFactory.getLogger(ManageEmployerDao.class);
	
	private static EmployerDetailsMapper employerDetails = new EmployerDetailsMapper();
	private static ViewEmployerByActiveStatusRowMapper viewEmployerByActiveStatusRowMapper= new ViewEmployerByActiveStatusRowMapper();
	
	/**
	 * @author Prateek Kapoor
	 * @since 28-10-2020
	 * This method updates the enrolment status for the user with received id to the received status
	 * @param userId
	 * @param updatedStatus
	 * @return 1 if success; 
	 * -25 in case of exception
	 */
	public int updateUserEnrolmentStatus(int userId, String updatedStatus)
	{
		Log.debug("Request received in dao to update the enrolment status of the user with id {} to {}",userId, updatedStatus);
		Map<String,Object> params = new HashMap<>(2);
		params.put("userId", userId);
		params.put("updatedStatus", updatedStatus);
		Log.debug("Hashmap created and parameters inserted for user with id {}",params.get("userId"));
		try
		{
			Log.debug("In try block to execute the query and update the user enrolment status");
			return getJdbcTemplate().update(manageEmployerConfig.getUpdateEmployerEnrolmentStatus(), params);
		}
		catch (Exception e) 
		{
			Log.error("An exception occurred while updating the user enrolment status - "+e);
			Log.error("Returning -25");
			return -25;
		}
	}
	
	
	/**
	 * @author Prateek Kapoor
	 * @since 28-10-2020
	 * This method fetches the collection of employer details whose account status is active and enrolment status is the received enrolment status
	 * @param enrolmentStatus
	 * @return Collection of {@link ViewEmployerDetailsDto} if success; else returns null
	 * 
	 */
	public Collection<ViewEmployerDetailsDto> viewEmployerDetailsWithStatus(String enrolmentStatus)
	{
		Log.debug("Request received in dao to fetch the employer details whose account status is active and enrolment status is {}",enrolmentStatus);
		Map<String,String> params = new HashMap<>(2);
		params.put("enrolmentStatus", enrolmentStatus);
		params.put("activeFlagTrue", ReadApplicationConstants.getTrueFlag());
		Log.debug("Hashmap created and parameters inserted into hashmap");
		try
		{
			Log.debug("In try block to execute the query and fetch the result");
			return getJdbcTemplate().query(manageEmployerConfig.getViewEmployerWithEnrolmentStatus(),params,employerDetails);
		}
		catch (Exception e) 
		{
			Log.error("An exception occurred while fetching the employer details - "+e);
			Log.error("Returning null");
			return null;
		}
	}
	
	/**
	 * @author Sarthak Bhutani 
	 * This method fetches the collection of employer details corresponding to the received account status
	 * @since 03/11/2020
	 * @param activeStatus
	 * @return Collection of {@link ViewEmployerByActiveStatusDto} if success, null if exception
	 */
	public Collection<ViewEmployerByActiveStatusDto> viewEmployerByActiveStatus(String activeStatus){
		Log.debug("Request received in Employer to fetch Employer details with active status: {}",activeStatus);
		Map<String,String> params = new HashMap<>(3);
		params.put("approvedFlag",ReadApplicationConstants.getApprovedFlag());
		params.put("userRoleEmployer",ReadApplicationConstants.getUserRoleEmployer());
		params.put("activeStatus",activeStatus);
		Log.debug("Hashmap created with values, approvedFlag:{}, userRoleEmployer:{}, activeStatus: {}", params.get("approvedFlag"), params.get("userRoleEmployer"), params.get("activeStatus"));
		try {
			Log.debug("In try block of dao to fetch Employer details with active status: {}",activeStatus);
			return getJdbcTemplate().query(manageEmployerConfig.getViewEmployerByActiveStatus(),params,viewEmployerByActiveStatusRowMapper);
		}
		catch(Exception e) {
			Log.error("An exception occured while fetching Employer details by active status. Exception : "+e);
			return null;
		}		
	}
	
	/**
	 * Row Mapper to map result set into ViewEmployerDetailsDto
	 * @author Prateek Kapoor
	 * 
	 * @updatedBy Sarthak Bhutani
	 * @updatedOn 22/11/2020
	 */
	public static class EmployerDetailsMapper implements RowMapper<ViewEmployerDetailsDto>
	{
		public ViewEmployerDetailsDto mapRow(ResultSet rs, int rowNum) throws SQLException
		{

			SimpleDateFormat dateFormatter = new SimpleDateFormat(ReadApplicationConstants.getDateWithMonthNameFormat());
			int userId = rs.getInt("userId");
			String companyName = rs.getString("username");
			long mobileNumber = rs.getLong("mobile_number");
			String companyType = rs.getString("company_type");
			String companyScale = rs.getString("company_scale");
			String email = rs.getString("email_address");
			String state = rs.getString("state");
			String registeredOn = Objects.isNull(rs.getDate("registered_on"))?null:dateFormatter.format(rs.getDate("registered_on"));
			String enrollmentUpdatedOn = Objects.isNull(rs.getDate("enrolment_updated_on"))?null:dateFormatter.format(rs.getDate("enrolment_updated_on"));
			String landlineNumber = rs.getString("landline_number");
			String employerWebsite = rs.getString("website");
			String industryType = rs.getString("industry_type");
			String liaisingAuthority = rs.getString("liasing_authority");
			String designation = rs.getString("designation");
			return new ViewEmployerDetailsDto(userId, companyName, companyType, companyScale, mobileNumber, email, state, registeredOn, enrollmentUpdatedOn,landlineNumber,employerWebsite,industryType,liaisingAuthority,designation);
		}
		
	}

	/*
	 * RowMapper for {@link ViewEmployerByActiveStatusDto} to fetch employer details corresponding to the received account status
	 * @author Sarthak Bhutani
	 * 
	 * @updatedBy Sarthak Bhutani
	 * @updatedOn 22/11/2020
	 */
	
	public static class ViewEmployerByActiveStatusRowMapper implements RowMapper<ViewEmployerByActiveStatusDto>{

		@Override
		public ViewEmployerByActiveStatusDto mapRow(ResultSet rs, int rowNum) throws SQLException {
			 SimpleDateFormat dateFormatter = new SimpleDateFormat(ReadApplicationConstants.getDateWithMonthNameFormat());
			 int userId = rs.getInt("userId");
			 String companyName = rs.getString("companyName");
			 String companyType = rs.getString("companyType");
			 String companyScale = rs.getString("companyScale");
			 long mobileNumber = rs.getLong("mobileNumber");
			 String email = rs.getString("email");
			 String state = rs.getString("state");
			 String registeredOn = Objects.isNull(rs.getDate("registeredOn"))?null:dateFormatter.format(rs.getDate("registeredOn"));
			 String activationUpdatedOn = Objects.isNull(rs.getDate("activationUpdatedOn"))?null:dateFormatter.format(rs.getDate("activationUpdatedOn"));
			 String landlineNumber = rs.getString("landline_number");
			 String employerWebsite = rs.getString("website");
			 String industryType = rs.getString("industry_type");
			 String liaisingAuthority = rs.getString("liasing_authority");
			 String designation = rs.getString("designation");
			return new ViewEmployerByActiveStatusDto(userId,companyName,companyType,companyScale,mobileNumber,email,state,registeredOn,activationUpdatedOn,landlineNumber,employerWebsite,industryType,liaisingAuthority,designation);
		}
		
	}
}
