package com.cgsc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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
import com.cgsc.config.EditJobPostConfig;
import com.cgsc.dto.EditJobPostsDto;

@Repository
public class EditJobPostDao extends AbstractTransactionalDao
{

	private static final Logger Log = LoggerFactory.getLogger(EditJobPostDao.class);
	
	private static JobDetailsMapper jobPostMapper = new JobDetailsMapper();
	
	@Autowired
	private EditJobPostConfig editJobPostConfig;
	
	/**
	 * @author Prateek Kapoor
	 * This method returns all the job details corresponding to a job id
	 * @since 14-10-2020
	 * @param jobId
	 * @return Object of EditJobPostsDto if success; else returns null
	 */
	public EditJobPostsDto viewJobPostDetails(int jobId)
	{
		Log.debug("Request received in dao to fetch the job post details for job with id {}",jobId);
		Map<String,Object> params = new HashMap<>(2);
		params.put("jobId", jobId);
		params.put("activeFlagTrue", ReadApplicationConstants.getTrueFlag());
		Log.debug("hashmap created and job id {} inserted into hashmap",params.get("jobId"));
		try 
		{
			Log.debug("In try block to fetch the job post details for job with id {}",params.get("jobId"));
			return getJdbcTemplate().queryForObject(editJobPostConfig.getViewJobPosts(), params, jobPostMapper);
		}	
		catch (Exception e) 
		{
			Log.error("An exception occurred while fetching the job post details - "+e);
			return null;
		}
	}
	
	
	/**
	 * @author Prateek Kapoor
	 * @since 14-10-2020
	 * This method updates the job post details corresponding to a job id
	 * @param editJobPostsDto
	 * @param jobDescriptionPathFilePath
	 * @return 1 if success; -25 in case of exception;
	 *
	 * @updated by - Jyoti Singh
	 * @updated on - 23-11-2020
	 * @update - Added minimum, maximum salary keys in hashmap to update minimum and maximum salary value
	 * 
	 * @updatedBy Sarthak Bhutani
	 * @updatedOn 25/11/2020
	 * @update - Added cgscCertificatePreference in hashmap to update cgsc_certificate_preference
	 *
	 * @updatedBy Sarthak Bhutani
	 * @updatedOn 03/11/2020
	 * @update - Replaced location with district
	 *
	 * @updatedBy Sarthak Bhutani
	 * @updatedOn 07/11/2020
	 * @update - Added occupation in hashmap
	 */
	public int updateJobPostDetails(EditJobPostsDto editJobPostsDto, String jobDescriptionPathFilePath)
	{
		Log.debug("Request received in dao to update job post details with job Id {}",editJobPostsDto.getJobId());
		Map<String,Object> params = new HashMap<>(23);
		params.put("jobId", editJobPostsDto.getId());
		params.put("jobTitle", editJobPostsDto.getJobTitle());
		params.put("minSalary", editJobPostsDto.getMinSalary());
		params.put("maxSalary", editJobPostsDto.getMaxSalary());
		params.put("state", editJobPostsDto.getState());
		params.put("district", editJobPostsDto.getDistrict());
		params.put("vacancy", editJobPostsDto.getVacancy());
		params.put("experienceRequired", editJobPostsDto.getMinimumExperience());
		params.put("qualificationRequired", editJobPostsDto.getEducationQualification());
		params.put("jobRole", editJobPostsDto.getJobRole());
		params.put("applicationDate", editJobPostsDto.getApplicationDate());
		params.put("jobSummary", editJobPostsDto.getJobSummary());
		params.put("jobDescriptionPath", jobDescriptionPathFilePath);
		params.put("leavePolicy", editJobPostsDto.getLeavePolicy());
		params.put("monthlyIncentive", editJobPostsDto.getMonthlyIncentives());
		params.put("workTiming", editJobPostsDto.getWorkTimings());
		params.put("contactNumber", editJobPostsDto.getContactNumber());
		params.put("interviewStartDateTime", editJobPostsDto.getInterviewStartDateTime());
		params.put("interviewEndDateTime", editJobPostsDto.getInterviewEndDateTime());
		params.put("walkInInterviewFlag", editJobPostsDto.getIsWalkInInterview());
		params.put("gender", editJobPostsDto.getPreferredGender());
		params.put("armyBackgroundPreference", editJobPostsDto.getArmyBackgroundPreferred());
		params.put("cgscCertificatePreference", editJobPostsDto.getCgscCertificatePreferred());
		params.put("occupation",editJobPostsDto.getOccupation());
		Log.debug("Hashmap created and parameters inserted into hashmap for jobId {}",editJobPostsDto.getJobId());
		try
		{
			Log.debug("In try block to execute the query and update the job details");
			return getJdbcTemplate().update(editJobPostConfig.getUpdateJobPostDetails(), params);
			
		} 
		catch (Exception e) 
		{
			Log.error("An exception occurred while updating the job post details "+e);
			return -25;
		}
		
	}
	
	/**
	 * @author Prateek Kapoor
	 * This method fetches the job description document path for the received jobId
	 * @param jobId
	 * @return job description path if success; 
	 * else returns "Exception" text in case of exception
	 */
	public String fetchDescriptionDocumentPath(int jobId)
	{
		Log.debug("Request received in dao to fetch the job description document path for job with id {}",jobId);
		Map<String,Integer> params = new HashMap<>(1);
		params.put("jobId", jobId);
		Log.debug("Hashmap created and jobId {} inserted into hashmap",params.get("jobId"));
		try 
		{
			Log.debug("In try block to fetch the description document path for job id {}",jobId);
			return getJdbcTemplate().queryForObject(editJobPostConfig.getFetchDescriptionDocumentPath(), params,String.class);
		}
		catch (Exception e) 
		{
			Log.error("An exception occurred while fetching the job description path "+e);
			return ReadApplicationConstants.getExceptionText();
		}
	}
	
	/**
	 * Row Mapper for {@link EditJobPostsDto}
	 * @author Prateek Kapoor
	 * 
	 * @updatedBy Sarthak Bhutani
	 * @updatedOn 27/11/2020
	 * @update Added field - cgscCertificatePreferred in rowmapper
	 *
	 * @updatedBy Sarthak Bhutani
	 * @updatedOn 03/11/2020
	 * @update - Replaced location with district
	 *
	 * @updatedBy Sarthak Bhutani
	 * @updatedOn 07/11/2020
	 * @update - Added occupation in RowMapper for dto
	 */
	public static class JobDetailsMapper implements RowMapper<EditJobPostsDto>
	{
		public EditJobPostsDto mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			SimpleDateFormat dateTimeFormatter = new SimpleDateFormat(ReadApplicationConstants.getDateTimeFormat());
			SimpleDateFormat dateFormatter = new SimpleDateFormat(ReadApplicationConstants.getDateWithMonthNameFormat());
			
			String additionalInformation = ReadApplicationConstants.getTrueFlag();
			int id = rs.getInt("id");
			String jobId = rs.getString("job_id");
			String jobTitle = rs.getString("job_title");
			int minSalary = rs.getInt("min_salary");
			int maxSalary = rs.getInt("max_salary");
			String state = rs.getString("state");
			String district = rs.getString("district");
			String experienceRequired = rs.getString("experience_required");
			String qualificationRequired = rs.getString("qualification_required");
			String jobRole = rs.getString("job_role");
			int vacancy = rs.getInt("total_vacancy");
			String applicationDate = dateFormatter.format(rs.getDate("application_date"));
			String jobSummary = rs.getString("job_summary");
			String jobDescription = rs.getString("job_description_path");
			String leavePolicy = rs.getString("leave_policy");
			String monthlyIncentives = rs.getString("monthly_incentives");
			String workTimings = rs.getString("work_timings");
			Long contactNumber = rs.getLong("contact_number")==0?null:rs.getLong("contact_number");
			Timestamp interviewStartDateTimeTs = rs.getTimestamp("interview_start_date_time");
			Timestamp interviewEndDateTimeTs = rs.getTimestamp("interview_end_date_time");
			String interviewStartDateTime = null;
			String interviewEndDateTime = null;
			if(!Objects.isNull(interviewStartDateTimeTs))
			{
				interviewStartDateTime = dateTimeFormatter.format(interviewStartDateTimeTs);
			}
			if(!Objects.isNull(interviewEndDateTimeTs))
			{
				interviewEndDateTime = dateTimeFormatter.format(interviewEndDateTimeTs);
			}
			String walkInInterviewFlag = rs.getString("walk_in_interview_flg");
			String preferredGender = rs.getString("preferred_gender");
			String armyBackgroundPreferred = rs.getString("army_background_preference");
			String cgscCertificatePreferred = rs.getString("cgsc_certificate_preference");
			String occupation = rs.getString("occupation");
			if(Objects.isNull(leavePolicy) && Objects.isNull(monthlyIncentives) && Objects.isNull(workTimings) && Objects.isNull(contactNumber) && Objects.isNull(interviewStartDateTime)
					&& Objects.isNull(interviewEndDateTime) && Objects.isNull(walkInInterviewFlag) && Objects.isNull(preferredGender) && Objects.isNull(armyBackgroundPreferred))
				{
					additionalInformation = ReadApplicationConstants.getFalseFlag();
				}
			return new EditJobPostsDto(id, jobId, jobTitle, vacancy, minSalary, maxSalary, state, district, experienceRequired, qualificationRequired, jobRole, applicationDate, jobSummary, jobDescription, additionalInformation, leavePolicy, monthlyIncentives, workTimings, contactNumber, interviewStartDateTime, interviewEndDateTime, walkInInterviewFlag, preferredGender, armyBackgroundPreferred, cgscCertificatePreferred, occupation);
			
		}
	}
}
