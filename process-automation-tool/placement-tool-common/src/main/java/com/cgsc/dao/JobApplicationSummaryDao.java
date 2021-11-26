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
import com.cgsc.config.JobApplicationSummaryConfig;
import com.cgsc.dto.JobApplicationSummaryDto;

@Repository
public class JobApplicationSummaryDao extends AbstractTransactionalDao
{

	private static final Logger Log = LoggerFactory.getLogger(JobApplicationSummaryDao.class);
	private static JobSummaryMapper jobSummaryMapper = new JobSummaryMapper();
	private static CandidateDetailsMapper candidateDetailsMapper = new CandidateDetailsMapper();
	
	@Autowired
	private JobApplicationSummaryConfig jobApplicationSummaryConfig;
	
	
	/**
	 * @author Prateek Kapoor
	 * @since 19-10-2020
	 * This method returns the job application summary for the received jobId
	 * @param jobId
	 * @return Object of {@link JobApplicationSummaryDto} if success;
	 * Else returns null
	 *
	 * @updated by Sarthak Bhutani
	 * @updated on 09/12/2020
	 * @update - Added statusHired in params Hashmap
	 */
	public JobApplicationSummaryDto viewJobApplicationSummary(int jobId)
	{
		Log.debug("Request received in dao to view the job application summary for jobId {}",jobId);
		Map<String,Object> params = new HashMap<>(9);
		params.put("uniqueJobId", jobId);
		params.put("genderMale", ReadApplicationConstants.getMaleGender());
		params.put("genderFemale", ReadApplicationConstants.getFemaleGender());
		params.put("otherGender", ReadApplicationConstants.getOtherGender());
		params.put("trueFlag", ReadApplicationConstants.getTrueFlag());
		params.put("statusHired",ReadApplicationConstants.getApplicationStateHired());
		params.put("statusInReview", ReadApplicationConstants.getApplicationStateInReview());
		params.put("statusShortlisted", ReadApplicationConstants.getApplicationStateShortlisted());
		params.put("statusRejected", ReadApplicationConstants.getApplicationStateRejected());
		Log.debug("Hashmap created and parameters inserted into hashmap for job Id {}",params.get("uniqueJobId"));
		try 
		{
			Log.debug("In try block to execute the query and fetch the job application summary");
			return getJdbcTemplate().queryForObject(jobApplicationSummaryConfig.getViewJobApplicationSummary(), params, jobSummaryMapper);
		}
		catch (Exception e) 
		{
			Log.error("An exception occurred while fetching the job application summary "+e);
			return null;
		}
	}
	
	/**
	 * @author Prateek Kapoor
	 * @since 20-10-2020
	 * This method fetches the list of candidate who have applied for a job posts along with their application status
	 * @param jobId
	 * @return Collection of {@link JobApplicationSummaryDto} if success
	 * else returns null
	 */
	public Collection<JobApplicationSummaryDto> viewCandidateDetails(int jobId)
	{
		Log.debug("Request received in dao to fetch the candidate details for job with id {}",jobId);
		Map<String,Integer> params = new HashMap<>(1);
		params.put("jobId", jobId);
		Log.debug("Hashmap created and job id {} inserted into hashmap",params.get("jobId"));
		try 
		{
			Log.debug("In try block to execute the query and fetch the result");
			return getJdbcTemplate().query(jobApplicationSummaryConfig.getViewCandidateJobSummary(),params, candidateDetailsMapper);
		}
		catch (Exception e) 
		{
			Log.error("An exception occurred while fetching the candidate details "+e);
			return null;
		}
	}
	
	/**
	 * Row Mapper to map result set into JobApplicationSummaryDto
	 * @author Prateek Kapoor
	 *
	 * @updated by Sarthak Bhutani
	 * @updated on 09/12/2020
	 * @update - Added hired in RowMapper
	 */
	public static class JobSummaryMapper implements RowMapper<JobApplicationSummaryDto>
	{
		public JobApplicationSummaryDto mapRow(ResultSet rs, int rowNum) throws SQLException
		{

			SimpleDateFormat dateFormatter = new SimpleDateFormat(ReadApplicationConstants.getDateWithMonthNameFormat());
			int uniqueId = rs.getInt("uniqueId");
			String jobId = rs.getString("jobId");
			String jobTitle = rs.getString("jobTitle");
			int vacancy = rs.getInt("vacancy");
			String applicationLastDate = Objects.isNull(rs.getDate("applicationLastDate"))?null:dateFormatter.format(rs.getDate("applicationLastDate"));
			int totalCandidates = rs.getInt("totalCandidates");
			int maleCandidates = rs.getInt("maleCandidates");
			int femaleCandidates = rs.getInt("femaleCandidates");
			int otherCandidates = rs.getInt("otherCandidates");
			int exArmyPersonnel = rs.getInt("exArmyPersonnel");
			int hired = rs.getInt("hired");
			int selected = rs.getInt("selected");
			int rejected = rs.getInt("rejected");
			int inReview = rs.getInt("inReview");
			String jobDescriptionDocument = rs.getString("jobDescriptionPath");
			return new JobApplicationSummaryDto(uniqueId, jobId, jobTitle, totalCandidates, maleCandidates, femaleCandidates, otherCandidates, exArmyPersonnel, hired,selected, rejected, inReview, vacancy, applicationLastDate, jobDescriptionDocument);
		}
		
	}
	
	

	/**
	 * Row Mapper to map result set into JobApplicationSummaryDto
	 * @author Prateek Kapoor
	 *
	 *@updated by - Jyoti Singh
	 *@updated on- 25-11-2020
	 *@update - added gender value in rowmapper
	 */
	public static class CandidateDetailsMapper implements RowMapper<JobApplicationSummaryDto>
	{
		public JobApplicationSummaryDto mapRow(ResultSet rs, int rowNum) throws SQLException
		{

			SimpleDateFormat dateFormatter = new SimpleDateFormat(ReadApplicationConstants.getDateWithMonthNameFormat());
			int userId = rs.getInt("userId");
			String candidateName = rs.getString("candidateName");
			long mobileNumber = rs.getLong("mobileNumber");
			String state = rs.getString("state");
			String appliedOn = Objects.isNull(rs.getDate("applied_on"))?null:dateFormatter.format(rs.getDate("applied_on"));
			String status = rs.getString("status");
			String gender = rs.getString("gender");
			return new JobApplicationSummaryDto(userId, candidateName, gender,appliedOn, mobileNumber, state, status);
		}
		
	}
	
	
}
