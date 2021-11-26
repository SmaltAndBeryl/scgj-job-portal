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
import org.springframework.transaction.annotation.Transactional;

import com.cgsc.common.AbstractTransactionalDao;
import com.cgsc.config.CandidateProfileConfig;
import com.cgsc.dto.CandidateProfileDto;

@Repository
public class CandidateProfileDao extends AbstractTransactionalDao
{

	@Autowired
	private CandidateProfileConfig candidateProfileConfig;
	
	private static final Logger Log = LoggerFactory.getLogger(CandidateProfileDao.class);
	private static CandidateDetailsMapper candidateDetails = new CandidateDetailsMapper();
	private static DocumentsMapper documentsMapper = new DocumentsMapper();
	
	
	/**
	 * @author Prateek Kapoor
	 * @since 27-10-2020
	 * This method fetches the candidate details for the received userId
	 * @param userId
	 * @return {@link CandidateProfileDto} if success;
	 * Else returns null
	 */
	public CandidateProfileDto viewCandidateProfileDetails(int userId)
	{
		Log.debug("Request received in dao to fetch the candidate details for candidate id {}",userId);
		Map<String,Integer> params = new HashMap<>(1);
		params.put("userId", userId);
		Log.debug("Hashmap created and userId {} inserted into hashmap", params.get("userId"));
		try 
		{
			Log.debug("In try block to execute the query and fetch the candidate details");
			return getJdbcTemplate().queryForObject(candidateProfileConfig.getViewCandidateDetails(), params, candidateDetails);
		} 
		catch (Exception e) 
		{
			Log.error("An exception occurred while fetching the candidate details "+e);
			return null;
		}
	}
	
	
	/**
	 * @author Prateek Kapoor
	 * @since 27-10-2020
	 * This method updates the user details stored in the users table
	 * @param candidateProfileDto
	 * @return 1 if success;
	 * else throws an exception and rollsback the transaction
	 * @throws Exception
	 */
	@Transactional(rollbackFor=Exception.class)
	public int updateUserTable(CandidateProfileDto candidateProfileDto) throws Exception
	{
		Log.debug("Request received in dao to update the details stored in users table for user id {}",candidateProfileDto.getUserId());
		Map<String,Object> params = new HashMap<>(4);
		params.put("candidateName", candidateProfileDto.getCandidateName());
		params.put("state", candidateProfileDto.getState());
		params.put("pincode", candidateProfileDto.getPincode());
		params.put("userId", candidateProfileDto.getUserId());
		Log.debug("Hashmap created and parameters inserted into hashmap for userId {}",params.get("userId"));
		try 
		{
			Log.debug("In try block to execute the query and update the candidate details in user table");
			return getJdbcTemplate().update(candidateProfileConfig.getUpdateUserDetails(), params);
		} 
		catch (Exception e) 
		{
			Log.error("An exception occurred while updating the candidate details in user table - "+e);
			throw new Exception(e);
		}
	}
	
	
	/**
	 * @author Prateek Kapoor
	 * @since 27-10-2020
	 * This method updates the record in candidate table corresponding to received userId (PK of users table)
	 * @param candidateProfileDto
	 * @param age
	 * @param resumePath
	 * @param certificatesPath
	 * @return 1 if success;
	 * else throws and exception and rollsback the transaction
	 * @throws Exception  
	 * 
	 * @updatedBy Sarthak Bhutani
	 * @updatedOn 26/11/2020
	 * @update Added 3 more fields in hashmap -
	 *
	 * @updatedBy Sarthak Bhutani
	 * @updatedOn 01/11/2020
	 * @update Added param age &  more fields in hashmap - dateOfBirth,age,guardianName,aadhaarNumber,jobRoleId,address
	 */
	@Transactional(rollbackFor=Exception.class)
	public int updateCandidateDetails(CandidateProfileDto candidateProfileDto, int age, String resumePath, String certificatesPath) throws Exception
	{
		Log.debug("Request received in dao to update the candidate table for user with id {}",candidateProfileDto.getUserId());
		Map<String,Object> params = new HashMap<>(17);
		params.put("guardianMobileNumber", candidateProfileDto.getGuardianMobileNumber());
		params.put("gender", candidateProfileDto.getGender());
		params.put("defenceBackground", candidateProfileDto.getExArmyPersonnel());
		params.put("educationQualification", candidateProfileDto.getEducationQualification());
		params.put("professionalExperience", candidateProfileDto.getProfessionalExperience());
		params.put("resumePath", resumePath);
		params.put("certificatePath", certificatesPath);
//		params.put("isCgscCertified", candidateProfileDto.getIsCgscCertified());
//		params.put("certificateNumber", candidateProfileDto.getCertificateNumber());
//		params.put("trainingPartnerId",candidateProfileDto.getTpId());
		params.put("userId", candidateProfileDto.getUserId());
		params.put("dateOfBirth",candidateProfileDto.getDob());
		params.put("age",age);
		params.put("guardianName",candidateProfileDto.getGuardianName());
		params.put("aadhaarNumber",candidateProfileDto.getAadhaarNumber());
		params.put("jobRoleId",candidateProfileDto.getJobRoleId());
		params.put("address",candidateProfileDto.getAddress());
		Log.debug("Hashmap created and parameters inserted into hashmap for user id {}",params.get("userId"));
		
		try 
		{
			Log.debug("In try block to execute the query and update the records in candidate table");
			return getJdbcTemplate().update(candidateProfileConfig.getUpdateCandidateDetails(), params);	
		}
		catch (Exception e) 
		{
			Log.error("An exception occurred while updating the candidate details "+e);
			throw new Exception(e);
		}
	}
	
	/**
	 * @author Prateek Kapoor
	 * @since 27-10-2020
	 * This method fetches the resume and candidate certificates path corresponding to userId
	 * @param userId
	 * @return CandidateProfileDto if success;
	 * else returns null
	 */
	public CandidateProfileDto fetchCandidateDocuments(int userId)
	{
		Log.debug("Request received in dao to fetch the document paths for user with id {}",userId);
		Map<String,Integer> params = new HashMap<>(1);
		params.put("userId", userId);
		Log.debug("Hashmap created and userId {} into hashmap",params.get("userId"));
		try 
		{
			Log.debug("In try block to execute the query and fetch the result");
			return getJdbcTemplate().queryForObject(candidateProfileConfig.getDocumentPaths(), params, documentsMapper);
		}
		catch (Exception e) 
		{
			Log.error("An exception occurred while fetching the document details "+e);
			return null;
		}
	}
	
	
	/**
	 * Row Mapper for {@link CandidateProfileDto}
	 * @author Prateek Kapoor
	 *
	 */
	public static class DocumentsMapper implements RowMapper<CandidateProfileDto>
	{
		public CandidateProfileDto mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			String resumePath = rs.getString("resume_path");
			String certificatesPath = rs.getString("certificate_path");
			return new CandidateProfileDto(resumePath, certificatesPath);
		}
	}
	/**
	 * Row Mapper for {@link CandidateProfileDto}
	 * @author Prateek Kapoor
	 *
	 * @updatedBy Sarthak Bhutani
	 * @update Added fields - dob, guardianName, aadhaarNumber, jobRoleId, jobRole, address
	 */
	public static class CandidateDetailsMapper implements RowMapper<CandidateProfileDto>
	{
		public CandidateProfileDto mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int userId = rs.getInt("id");
			long mobileNumber = rs.getLong("mobile_number");
			String candidateName = rs.getString("username");
			String state = rs.getString("state");
			int pincode = rs.getInt("pincode");
			long guardianMobileNumber = rs.getLong("guardian_mobile_number");
			String gender = rs.getString("gender");
			String exArmyPersonnel = rs.getString("defence_background");
			String educationQualification = rs.getString("qualification");
			String professionalExperience = rs.getString("working_experience");
			String resumePath = rs.getString("resume_path");
			String certificatesPath = rs.getString("certificate_path");
//			String isCgscCertified = rs.getString("is_cgsc_certified");
//			String certificateNumber = rs.getString("certificate_number");
//			Integer tpId = rs.getInt("tp_id");
//			String tpName = rs.getString("tp_name");
			String dob = rs.getString("dob");
			String guardianName = rs.getString("guardian_name");
			long aadhaarNumber = rs.getLong("aadhaar_number");
			int jobRoleId = rs.getInt("job_role_id");
			String jobRole = rs.getString("job_role");
			String address = rs.getString("address");
			int age = rs.getInt("age");
			return new CandidateProfileDto(userId, candidateName, mobileNumber, guardianMobileNumber, professionalExperience, educationQualification, gender, state, pincode, resumePath, certificatesPath, exArmyPersonnel, null, null, null, null,
					dob, guardianName, aadhaarNumber, jobRoleId, jobRole, address,age);
		}
	}
}
