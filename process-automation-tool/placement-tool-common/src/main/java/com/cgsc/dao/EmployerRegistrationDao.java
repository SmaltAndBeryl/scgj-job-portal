package com.cgsc.dao;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cgsc.common.AbstractTransactionalDao;
import com.cgsc.common.ReadApplicationConstants;
import com.cgsc.config.EmployerRegistrationConfig;
import com.cgsc.dto.EmployerRegistrationDto;

@Repository
public class EmployerRegistrationDao extends AbstractTransactionalDao
{
	@Autowired
	private EmployerRegistrationConfig employerRegistrationConfig;
	
	private static final Logger Log = LoggerFactory.getLogger(EmployerRegistrationDao.class);
	
	/**
	 * @author Prateek Kapoor
	 * @since 06-10-2020
	 * This method inserts the user details into database and returns the generated key
	 * @param employerRegistrationDto
	 * @return generated key if success; else returns -25
	 */
	@Transactional(rollbackFor=Exception.class)
	public int generateEmployerCredentials(EmployerRegistrationDto employerRegistrationDto)
	{
		Log.debug("Request recieved in dao to generate credentials for the employer with email address {}, name {} and mobile number {}",employerRegistrationDto.getEmail(),employerRegistrationDto.getCompanyName(),employerRegistrationDto.getMobileNumber());
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("mobileNumber", employerRegistrationDto.getMobileNumber());
		params.addValue("companyName", employerRegistrationDto.getCompanyName());
		params.addValue("state", employerRegistrationDto.getState());
		params.addValue("pincode", employerRegistrationDto.getPincode());
		params.addValue("userRoleEmployer", ReadApplicationConstants.getUserRoleEmployer());
		params.addValue("enrolmentStatus", ReadApplicationConstants.getInReviewFlag());
		
		Log.debug("Param source created and parameters inserted into hashmap");
		try
		{
			Log.debug("In try block to generate credentials of the employer");
			KeyHolder key = new GeneratedKeyHolder();
			getJdbcTemplate().update(employerRegistrationConfig.getInsertUserCredentialDetails(), params, key);
			return key.getKey().intValue();
		}
		catch (DuplicateKeyException duplicateKey) 
		{
			Log.error("The mobile number for the employer already exists in the database - "+duplicateKey);
			Log.error("Returning -55 to service");
			return -55;
		}
		catch (Exception e) 
		{
			Log.error("An exception occurred while inserting the user details "+e);
			Log.error("Returning -25 to service");
			return -25;
		}
	}
	
	/**
	 * @author Prateek Kapoor
	 * @since 06-10-2020
	 * This method inserts the details of the employer into employer table
	 * @param employerRegistrationDto
	 * @param employerId
	 * @return 1 if success; else throws an exception and rollsback the transaction
	 * @throws Exception
	 * 
	 * @updateBy Sarthak Bhutani
	 * @updatedOn 19-11-2020
	 *
	 * @updatedBy Sarthak Bhutani
	 * @updatedOn 15/12/2020
	 * @update Added companyAddress in hashmap
	 *
	 * @updatedBy Sarthak Bhutani
	 * @updatedOn 21/12/2020
	 * @update Removed employerPoc from hashmap
	 * @update Changed log.error -> log.debug before the hashmap creation
	 */
	
	@Transactional(rollbackFor = Exception.class)
	public int insertEmployerDetails(EmployerRegistrationDto employerRegistrationDto, int employerId) throws Exception
	{
		Log.debug("Request received in dao to insert the employer details into database for employer with id {} and name {}",employerId, employerRegistrationDto.getCompanyName());
		Map<String,Object>params = new HashMap<>(11);
		params.put("userId", employerId);
		params.put("emailAddress", employerRegistrationDto.getEmail());
		params.put("industryType", employerRegistrationDto.getIndustryType());
		params.put("companyType", employerRegistrationDto.getCompanyType());
		params.put("companyScale", employerRegistrationDto.getCompanyScale());
		params.put("panNumber", employerRegistrationDto.getPanNumber());
		params.put("liasingAuthority", employerRegistrationDto.getLiasingAuthorityName());
		params.put("designation", employerRegistrationDto.getDesignation());
		params.put("landlineNumber", employerRegistrationDto.getLandlineNumber());
		params.put("employerWebsite", employerRegistrationDto.getEmployerWebsite());
		params.put("companyAddress",employerRegistrationDto.getCompanyAddress());

		Log.debug("Hashmap created and parameters inserted into hashmap");
		
		try 
		{
			Log.debug("In try block to insert details for employer with name {}",employerRegistrationDto.getCompanyName());
			return getJdbcTemplate().update(employerRegistrationConfig.getInsertEmployerDetails(), params);
		}
		catch(DuplicateKeyException duplicateKey)
		{
			Log.error("The email address already exists for this employer, records cannot be created - "+duplicateKey);
			throw new DuplicateKeyException("Duplicate Key Exception", duplicateKey);
		}
		catch (Exception e) 
		{
			Log.error("An exception occurred while inserting the employer details into database - "+e);
			throw new Exception(e);
		}
	
	
	}

}
