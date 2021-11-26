package com.cgsc.dao;

import java.sql.Date;
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
import org.springframework.transaction.annotation.Transactional;

import com.cgsc.common.AbstractTransactionalDao;
import com.cgsc.common.ReadApplicationConstants;
import com.cgsc.config.AdminReviewJobPostConfig;
import com.cgsc.dto.UpdateJobPostApprovalStatusDto;
import com.cgsc.dto.ViewJobPostByApprovalStatusDto;
import com.cgsc.utility.GetLoggedInUserDetailsUtility;

@Repository
public class AdminReviewJobPostDao extends AbstractTransactionalDao{
	
	private static final Logger Log = LoggerFactory.getLogger(AdminReviewJobPostDao.class);

	@Autowired
	private AdminReviewJobPostConfig adminReviewJobPostConfig;
	
	
	private static JobPostDetailsMapper jobPostDetailsMapper = new JobPostDetailsMapper();
	
	/**
	 * @author Jyoti Singh
	 * @since 08-12-2020
	 * @param - job approval status (String)
	 * This Method to fetch the list of published job post details with the approval status received
	 * 
	 * @return Collection {@link ViewJobPostByApprovalStatusDto} if success; else null
	 */
	public Collection<ViewJobPostByApprovalStatusDto> fetchJobPostWithApprovalStatus(String status) {
		Log.debug("Request recieved in dao method to fetch all published job post with status - {}",status);
		Map<String,Object> params = new HashMap<>(3);
		params.put("activeFlagTrue", ReadApplicationConstants.getTrueFlag());
		params.put("publishedStatus", ReadApplicationConstants.getPublishedState());
		params.put("approvalStatus", status);
		params.put("enrolmentStatusApproved",ReadApplicationConstants.getApprovedFlag());
		
		Log.debug("Hashmap created and approvalStatus {} inserted into hashmap", params.get("approvalStatus"));
		try 
		{
			Log.debug("In try block to execute the query and fetch all published job post with status");
			return getJdbcTemplate().query(adminReviewJobPostConfig.getGetJobPostByApprovalStatusSql(), params, jobPostDetailsMapper);
		} 
		catch (Exception e) 
		{
			Log.error("An exception occurred while fetching the job post details "+e);
			return null;
		}
	}
	
	
	
	/**
	 * @author Jyoti Singh
	 * @since 08-12-2020
	 * This method updates the job post status of a published job post in job_postings table
	 * 
	 * @return 1 if success; 
	 *  else throws and exception and rollsback the transaction
	 * @throws Exception 
	 */
	@Transactional(rollbackFor=Exception.class)
	public int updateJobPostApprovalStatus(UpdateJobPostApprovalStatusDto updateJobPostApprovalStatusDto) throws Exception {
		Log.debug("Request recieved in dao to update the job post with status - {}",updateJobPostApprovalStatusDto.getUpdatedStatus());
		Map<String,Object> params = new HashMap<>(2);
		params.put("id", updateJobPostApprovalStatusDto.getJobId());
		params.put("approvalStatus", updateJobPostApprovalStatusDto.getUpdatedStatus());
		Log.debug("Hashmap created and parameters inserted into hashmap");
		try 
		{
			Log.debug("In try block to execute the query to update the job post status to - {}",params.get("approvalStatus"));
			return getJdbcTemplate().update(adminReviewJobPostConfig.getUpdateJobPostApprovalStatusSql(), params);
		} 
		catch (Exception e) 
		{
			Log.error("An exception occurred while updating the job post status to "+e);
			throw new Exception(e);
		}
	}
	
	
	/**
	 * @author Jyoti Singh
	 * @since 08-12-2020
	 * @param - UpdateJobPostApprovalStatusDto
	 * This method inserts the admin comments for rejecting the job post
	 * 
	 * @return 1 if success; 
	 *  else throws and exception and rollsback the transaction
	 * @throws Exception 
	 */
	
	@Transactional(rollbackFor=Exception.class)
	public int insertAdminComments(UpdateJobPostApprovalStatusDto updateJobPostApprovalStatusDto) throws Exception {
		Log.debug("Request recieved in dao to insert the admin comments for job post with id - {}",updateJobPostApprovalStatusDto.getJobId());
		Map<String,Object> params = new HashMap<>(3);
		params.put("id",updateJobPostApprovalStatusDto.getJobId());
		params.put("adminComment",updateJobPostApprovalStatusDto.getAdminComments());
		params.put("commentedBy", GetLoggedInUserDetailsUtility.getUserIdFromSession());
		Log.debug("Hashmap created and parameters inserted into hashmap");
		try 
		{
			Log.debug("In try block to execute the query to insert the admin comment - {} for job post with id - {}",params.get("adminComment"),params.get("approvalStatus"));
			return getJdbcTemplate().update(adminReviewJobPostConfig.getInsertAdminComments(), params);
		} 
		catch (Exception e) 
		{
			Log.error("An exception occurred while updating the job post status to "+e);
			throw new Exception(e);
		}
	}
	
	
	/**
	 * Row mapper for {@link ViewJobPostByApprovalStatusDto}
	 * 
	 */
	
	public static class JobPostDetailsMapper implements RowMapper<ViewJobPostByApprovalStatusDto>
	{
		@Override
		public ViewJobPostByApprovalStatusDto mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			SimpleDateFormat formatter=new SimpleDateFormat(ReadApplicationConstants.getDateWithMonthNameFormat());
			SimpleDateFormat dateTimeFormatter = new SimpleDateFormat(ReadApplicationConstants.getDateTimeFormat());
			int id = rs.getInt("id");
			String jobId = rs.getString("jobId");
			String jobTitle = rs.getString("jobTitle");
			int vacancy = rs.getInt("vacancy");
			String createdBy = rs.getString("createdBy");
			String status = rs.getString("approvalStatus");
			String jobRole = rs.getString("jobRole");
			String occupation = rs.getString("occupation");
			String district = rs.getString("district");
			String state = rs.getString("state");
			String jobSummary=rs.getString("jobSummary");
			String isCGSCCertified=rs.getString("isCGSCCertified");
			String minimumExperience=rs.getString("minimumExperience");
			String educationQualification=rs.getString("educationQualification");
			int minSalary=rs.getInt("minSalary");
			int maxSalary=rs.getInt("maxSalary");
			String jobDescriptionDocumentPath=rs.getString("jobDescriptionDocumentPath");
			String adminComments=rs.getString("adminComments");
			String applicationLastDate=Objects.isNull(rs.getDate("applicationLastDate"))?null:formatter.format(rs.getDate("applicationLastDate"));
			String publishedOn=Objects.isNull(rs.getDate("publishedOn"))?null:formatter.format(rs.getDate("publishedOn"));
			String approvedRejectedOn=Objects.isNull(rs.getDate("approvedRejectedOn"))?null:formatter.format(rs.getDate("approvedRejectedOn"));
			String leavePolicy =rs.getString("leave_policy");
			String monthlyIncentives = rs.getString("monthly_incentives");
			String workTimings = rs.getString("work_timings");
			Long contactNumber = rs.getLong("contact_number");
			String isWalkinInterview = rs.getString("walk_in_interview_flg");
			String preferredGender = rs.getString("preferred_gender");
			String armyBackgroundPreferred = rs.getString("army_background_preference");
			
			String interviewStartDateTime = Objects.isNull(rs.getTimestamp("interview_start_date_time"))?null:dateTimeFormatter.format(new Date(rs.getTimestamp("interview_start_date_time").getTime()));
			String interviewEndDateTime= Objects.isNull(rs.getTimestamp("interview_end_date_time"))?null:dateTimeFormatter.format(new Date(rs.getTimestamp("interview_end_date_time").getTime()));
			
			return new ViewJobPostByApprovalStatusDto(id,jobId,jobTitle,vacancy,createdBy, publishedOn, status, jobRole, occupation, district, state, jobSummary, applicationLastDate, isCGSCCertified, adminComments,
					educationQualification, minimumExperience, minSalary, maxSalary,jobDescriptionDocumentPath,approvedRejectedOn,leavePolicy, monthlyIncentives,workTimings, contactNumber,isWalkinInterview, preferredGender,
					armyBackgroundPreferred, interviewStartDateTime, interviewEndDateTime);
		}
	}

}
