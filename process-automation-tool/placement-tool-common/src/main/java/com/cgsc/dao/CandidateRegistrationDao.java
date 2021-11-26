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
import com.cgsc.config.CandidateRegistrationConfig;
import com.cgsc.dto.RegisterCandidateDto;

@Repository
public class CandidateRegistrationDao extends AbstractTransactionalDao
{

	@Autowired
	private CandidateRegistrationConfig registerCandidateConfig;
	
	private static final Logger Log = LoggerFactory.getLogger(CandidateRegistrationDao.class);
	
	
	/**
	 * @author Prateek Kapoor
	 * This method generates the candidate credentials and sets the isActive flag of the candidate as true
	 * @since 07-10-2020
	 * @param registerCandidateDto
	 * @return generated key if success; -55 in case mobile number of the candidate is duplicate; -25 in case of exception
	 */
	@Transactional(rollbackFor=Exception.class)
	public int generateCandidateCredentials(RegisterCandidateDto registerCandidateDto)
	{
		Log.debug("Request received in dao to generate the candidate credentials with mobile number {}",registerCandidateDto.getMobileNumber());
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("mobileNumber", registerCandidateDto.getMobileNumber());
		params.addValue("candidateName", registerCandidateDto.getCandidateName());
		params.addValue("state",registerCandidateDto.getState());
		params.addValue("pincode", registerCandidateDto.getPincode());
		params.addValue("userRoleCandidate", ReadApplicationConstants.getUserRoleCandidate());
		Log.debug("Parameter source created and parameters inserted into hashmap");
		try 
		{
			Log.debug("In try block to generate the candidate credentials with mobile number {}",params.getValue("mobileNumber"));
			KeyHolder key = new GeneratedKeyHolder();
			getJdbcTemplate().update(registerCandidateConfig.getGenerateCandidateCredentials(), params, key);
			return key.getKey().intValue();
		}
		catch (DuplicateKeyException duplicateKey) 
		{
			Log.error("The mobile number for the candidate already exists in the database - "+duplicateKey);
			return -55;
		}
		catch (Exception e) 
		{
			Log.error("An exception occurred while inserting the user details "+e);
			return -25;
		}
	}
	
	/**
	 * @author Prateek Kapoor
	 * @since 07-10-2020
	 * This method inserts the candidate details into database and throws an exception in case the insert operation fails to rollback the transaction
	 * @param registerCandidateDto
	 * @param candidateId
	 * @param resumePath
	 * @param certificatesPath
	 * @param age
	 * @return 1 if success; throws an exception in case the insert operation fails
	 * @throws Exception
	 *
	 * @updatedBy Sarthak Bhutani
	 * @updatedOn 01/12/2020
	 * @update Added fields - dateOfBirth,age,guardianName,aadhaarNumber,jobRoleId,address in hashmap params
	 */
	@Transactional(rollbackFor=Exception.class)
	public int insertCandidateDetails(RegisterCandidateDto registerCandidateDto, int candidateId, int age, String resumePath, String certificatesPath) throws Exception
	{
		Log.debug("Request received in dao insert the candidate details into database for candidate id {}",candidateId);
		Map<String,Object>params = new HashMap<>(17);
		params.put("candidateId", candidateId);
		params.put("guardianMobileNumber",registerCandidateDto.getGuardianMobileNumber());
		params.put("qualification", registerCandidateDto.getQualification());
		params.put("workExperience", registerCandidateDto.getProfessionalExperience());
		params.put("resumePath", resumePath);
		params.put("certificatePath", certificatesPath);
		params.put("gender", registerCandidateDto.getGender());
		params.put("defenceBackground", registerCandidateDto.getDefenceBackground());
//		params.put("cgscCertificationStatus", registerCandidateDto.getCgscCertifiedCandidate());
//		params.put("certificateNumber", registerCandidateDto.getCertificateNumber());
//		params.put("trainingPartnerId", registerCandidateDto.getTrainingPartnerId());
		params.put("dateOfBirth",registerCandidateDto.getDob());
		params.put("age",age);
		params.put("guardianName",registerCandidateDto.getGuardianName());
		params.put("aadhaarNumber",registerCandidateDto.getAadhaarNumber());
		params.put("jobRoleId",registerCandidateDto.getJobRoleId());
		params.put("address",registerCandidateDto.getAddress());

		Log.debug("Hashmap created and parameters inserted into hashmap for candidate with candidate id {}",params.get("candidateId"));
		try
		{
			Log.debug("In try block to insert candidate details into database");
			return getJdbcTemplate().update(registerCandidateConfig.getInsertCandidateDetails(), params);
		}
		catch (DuplicateKeyException duplicateKey)
		{
			Log.error("The aadhaar number of the candidate already exists on the platform");
			throw new DuplicateKeyException("Duplicate Key Exception", duplicateKey);
		}
		catch (Exception e) 
		{
			Log.error("An exception occurred while inserting candidate details into database "+e);
			throw new Exception(e);
		}
		
	}
}
