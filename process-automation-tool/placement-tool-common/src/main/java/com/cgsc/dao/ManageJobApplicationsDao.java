package com.cgsc.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cgsc.common.AbstractTransactionalDao;
import com.cgsc.common.ReadApplicationConstants;
import com.cgsc.config.ManageJobApplicationsConfig;
import com.cgsc.dto.ManageJobApplicationsDto;
import com.cgsc.dto.ViewJobApplicationsDto;

@Repository
public class ManageJobApplicationsDao extends AbstractTransactionalDao
{

	private static final Logger Log = LoggerFactory.getLogger(ManageJobApplicationsDao.class);
	
	private static ViewJobApplicationsMapper jobApplicationsMapper = new ViewJobApplicationsMapper();
	
	@Autowired
	private ManageJobApplicationsConfig manageJobApplicationsConfig;
	
	
	/**
	 * @author Prateek Kapoor
	 * @since 16-10-2020
	 * This method fetches the collection of candidate details who applied for a specific jobId
	 * @param jobId
	 * @return Collection of {@link ViewJobApplicationsDto} if success; else returns null
	 */
	public Collection<ViewJobApplicationsDto> viewJobApplications(int jobId)
	{
		Log.debug("Request received in dao to view list of applications for job with id {}",jobId);
		Map<String,Object> params = new HashMap<>(2);
		params.put("jobId", jobId);
		params.put("activeFlagTrue", ReadApplicationConstants.getTrueFlag());
		Log.debug("Hashmap created and job id {} inserted into hashmap",params.get("jobId"));
		try 
		{
			Log.debug("In try block to fetch all the applications for jobId {}",jobId);
			return getJdbcTemplate().query(manageJobApplicationsConfig.getViewJobApplicants(),params, jobApplicationsMapper);
		} 
		catch (Exception e) 
		{
			Log.error("An exception occurred while fetching the job applications "+e);
			return null;
		}
	}
	
	
	/**
	 * @author Jyoti Singh
	 * @since 03-12-2020
	 * This method fetches the remaining vacancy for the job post with given Id
	 * @param JobId (int)
	 * @return count of vacancy remaining if success, -11 in case of exception; 
	 * 
	 */
	public int checkRemainingVacancy(int jobId) {
		Log.debug("Request received in dao to fetch the count of vacancy remaining for job post with Id - {}",jobId);
		Map<String,Object> params = new HashMap<>(2);
		params.put("jobId",jobId);
		params.put("applicationStatusHired", ReadApplicationConstants.getApplicationStateHired());
		Log.debug("Hashmap created with job id- {}, application status - {} ",params.get("jobId"),params.get("applicationStatusHired"));
		try 
		{
			Log.debug("In try block to fetch remaining vacancy for job post with Id {}",jobId);
			return getJdbcTemplate().queryForObject(manageJobApplicationsConfig.getCheckRemainingVacancy(), params, Integer.class);
		} 
		catch (Exception e) 
		{
			Log.error("An exception occurred while fetching the remaining vacancy "+e);
			return -11;
		}
	}
	
	
	/**
	 * @author Jyoti
	 * @since 09-12-2020
	 * This method fetches the minimum salary for a job post with given Id
	 * @param jobId
	 * @return minimum salary (int) if success, else -25 in case of exception
	 */
	public int fetchMinimumSalaryForJobPost(int jobId) {
		Log.debug("Request received in dao to fetch the minimum salary offered for the job post with Id - {}",jobId);	
		Map<String,Integer> params = new HashMap<>(1);
		params.put("jobId",jobId);
		Log.debug("Hashmap created with job id- {}",params.get("jobId"));
		try 
		{
			Log.debug("In try block to fetch the minimum salary offered for the job post with Id  {}",params.get("jobId"));
			return getJdbcTemplate().queryForObject(manageJobApplicationsConfig.getFetchMinimumSalarySql(), params, Integer.class);
		} 
		catch (Exception e) 
		{
			Log.error("An exception occurred while fetching the minimum salary "+e);
			return -25;
		}
	}
	
	
	/**
	 * @author Prateek Kapoor
	 * @since 16-10-2020
	 * This method updates the application status of candidates for a job id
	 * @param manageJobApplicationsDto
	 * @param offerLetterPath
	 * @return 1 if success; 
	 * else throws an exception and rollsback the transaction
	 * 
	 * @updated by - Jyoti Singh
	 * @updated on - 02-12-2020
	 * @update - added salary, offerLetter, joiningDate in hashmap to execute update job post status query
	 */
	@Transactional(rollbackFor=Exception.class)
	public int updateCandidateApplicationStatus(ManageJobApplicationsDto manageJobApplicationsDto, String offerLetterPath) throws Exception
	{
		Log.debug("Request received in dao to update the job application status for candidates for jobId {} to {}",manageJobApplicationsDto.getJobId(),manageJobApplicationsDto.getUpdatedStatus());
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		  for (Integer candidateId : manageJobApplicationsDto.getCandidateId())
		    {
		    	Map<String,Object>params = new HashMap<>();
		    	Log.debug("Inserting candidateId {} into hashmap",candidateId);
		    	params.put("candidateId", candidateId);
		    	params.put("jobId", manageJobApplicationsDto.getJobId());
		    	params.put("updatedStatus", manageJobApplicationsDto.getUpdatedStatus());
		    	params.put("salary", manageJobApplicationsDto.getSalary());
		    	params.put("offerLetter", offerLetterPath);
		    	params.put("joiningDate", manageJobApplicationsDto.getJoiningDate());
		    	list.add(params);
		    }
		  Log.debug("Created List of Hashmap and inserted the hashmap objects into the list");
		  @SuppressWarnings("rawtypes")
			Map[] batchUpdate = list.toArray(new HashMap[list.size()]); //Casted the list to array of Map
		  try 
		  {
			 Log.debug("In try block to execute batch update for the candidates");
			 getJdbcTemplate().batchUpdate(manageJobApplicationsConfig.getUpdateJobApplicationStatus(), batchUpdate);
			 return 1;
		  } 
		  catch (Exception e)
		  {
			Log.error("An exception occurred while updating the candidate job application status - "+e);
			Log.error("Throwing an exception and rolling back the transaction");
			throw new Exception(e);
		  }
		
	}
	
	/**
	 * @author Prateek Kapoor
	 * Row Mapper to map result set into ManageJobApplicationsDto
	 * 
	 * @updatedBy Sarthak Bhutani
	 * @updatedOn 26/11/2020
	 * @update - Added additional fields to be mapped - cgscCertificateNumber, isCgscCertified
	 * 
	 * @updated by - Jyoti Singh
	 * @updated on- 7-12-2020
	 * @update Added additional fields to be mapped- address, adharNumber, jobRole
	 *
	 * @updatedBy Sarthak Bhutani
	 * @updatedOn 23/12/2020
	 * @update Added additional field - trainingPartner
	 */
	static class ViewJobApplicationsMapper implements RowMapper<ViewJobApplicationsDto>{

		@Override
		public ViewJobApplicationsDto mapRow(ResultSet rs, int rowNum) throws SQLException 
		{		
			SimpleDateFormat dateFormatter = new SimpleDateFormat(ReadApplicationConstants.getDateWithMonthNameFormat());
			int candidateId = rs.getInt("candidateId");
			String candidateName = rs.getString("candidateName");
			long candidateMobileNumber = rs.getLong("candidateMobileNumber");
			String certificatePath = rs.getString("certificatePath");
			String resumePath = rs.getString("resumePath");
			String state = rs.getString("state");
			String educationQualification = rs.getString("educationQualification");
			String professionalExperience = rs.getString("workingExperience");
			String gender = rs.getString("gender");
			String defenceBackground = rs.getString("defenceBackground");
			long guardianMobileNumber = rs.getLong("guardianMobileNumber");
			Date applicationDate = rs.getDate("appliedOn");
			String isCgscCertified = rs.getString("isCgscCertified");
			String address = rs.getString("address");
			long adharNumber = rs.getLong("adharNumber");
			String jobRole = rs.getString("jobRole");
			String trainingPartner = rs.getString("trainingPartner");
			String cgscCertificateNumber = Objects.isNull(rs.getString("cgscCertificateNumber"))?ReadApplicationConstants.getNotApplicableText():rs.getString("cgscCertificateNumber");
			String appliedOn = null;
			String updatedOn = null;
			if(!Objects.isNull(applicationDate))
			{
				appliedOn = dateFormatter.format(applicationDate);
			}
			String applicationStatus = rs.getString("applicationStatus");
			Date updatedOnDate = rs.getDate("updatedOn");
			if(!Objects.isNull(updatedOnDate))
			{
				updatedOn = dateFormatter.format(updatedOnDate);
			}
			return new ViewJobApplicationsDto(candidateId, candidateName, candidateMobileNumber, state, certificatePath, resumePath, appliedOn, applicationStatus, educationQualification, professionalExperience, gender, guardianMobileNumber, defenceBackground, updatedOn,
					isCgscCertified,cgscCertificateNumber,address,jobRole,adharNumber,trainingPartner);
		}
		
	}

	

	
}
