package com.cgsc.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.cgsc.common.AbstractTransactionalDao;
import com.cgsc.common.ReadApplicationConstants;
import com.cgsc.config.AdminFaqConfig;
import com.cgsc.dto.EmployerDetailsDto;
import com.cgsc.dto.JobCandidateDetailsDto;

@Repository
public class AdminFaqDao extends AbstractTransactionalDao{
	
	private static final Logger Log = LoggerFactory.getLogger(AdminFaqDao.class);
	
	@Autowired
	private AdminFaqConfig adminFaqConfig;

	private static JobCandidateDetailsDtoRowMapper jobCandidateDetailsDtoRowMapper = new JobCandidateDetailsDtoRowMapper();
	private static EmployerDetailsDtoRowMapper employerDetailsDtoRowMapper = new EmployerDetailsDtoRowMapper();
	
	/**
	 * @author Jyoti Singh
	 * @since 17/12/2020
	 * @param employerUserId
	 * Method to get total count of jobs posted by the employer
	 * @return if success : jobs posted count ,in case of exception : -25
	 */
	public int getTotalJobsByEmployer(int employerUserId) {
		Log.debug("Request receieved in dao to fetch the count of total jobs posted by the employer");
		Map<String,Object> params = new HashMap<>(4);
		params.put("employerId",employerUserId);
		params.put("jobStatusNotPublished", ReadApplicationConstants.getNotPublishedState());
		params.put("jobStatusApproved", ReadApplicationConstants.getJobApprovalStatusApproved());
		params.put("activeFlagTrue", ReadApplicationConstants.getTrueFlag());
		Log.debug("Hashmap created with values, employer user Id : {}, job status approve :{}",params.get("employerUserId"), params.get("jobStatusApproved"));
		try {
			Log.debug("In try block of dao to get the count of total jobs posted by the employer");
			return getJdbcTemplate().queryForObject(adminFaqConfig.getGetTotalJobsPostedByEmployerSql(), params, Integer.class);
		}
		catch(Exception e) {
			Log.error("An exception occured while fetching the count of total jobs posted by the employer: "+e);
			return -25;
		}
	}
	
	
	
	/**
	 * @author Jyoti Singh
	 * @since 18/12/2020
	 * @param jobrole
	 * @param endDate
	 * @param startDate
	 * Method to get total count of candidates that were hired for a selected jobrole from given start date to end date
	 * @return if success : candidate count ,in case of exception : -25
	 */
	public int getJobroleSpecificHiredCandidateCount(String jobrole, Date startDate, Date endDate) {
		Log.debug("Request receieved in dao to total count of candidates that were hired for a selected jobrole -{} from {} to {}",jobrole,startDate,endDate);
		Map<String,Object> params = new HashMap<>(7);
		params.put("jobrole",jobrole);
		params.put("jobStatusNotPublished", ReadApplicationConstants.getNotPublishedState());
		params.put("jobStatusApproved", ReadApplicationConstants.getJobApprovalStatusApproved());
		params.put("activeFlagTrue", ReadApplicationConstants.getTrueFlag());
		params.put("jobApplicationStatusHired",ReadApplicationConstants.getApplicationStateHired());
		params.put("startDate",startDate);
		params.put("endDate",endDate);
		
		Log.debug("Hashmap created with values, jobrole: {}, job status approved: {}",params.get("jobrole"), params.get("jobStatusApproved"));
		try {
			Log.debug("In try block of dao to get the total count of candidates that were hired for the selected jobrole");
			return getJdbcTemplate().queryForObject(adminFaqConfig.getCountJobroleSpecificHiredCandidatesSql(), params, Integer.class);
		}
		catch(Exception e) {
			Log.error("An exception occured while fetching the count of total count of candidates that were hired for the selected jobrole: "+e);
			return -25;
		}
	}

	
	/**
	 * @author Jyoti Singh
	 * @since 18/12/2020
	 * 
	 * @param jobrole
	 * @param state
	 * @param endDate
	 * @param startDate
	 * Method to get total count of candidates that were hired for a selected jobrole and selected state
	 * @return if success : candidate count ,in case of exception : -25
	 */
	public int countStateJobroleSpecificHiredCandidate(String jobrole, String state, Date startDate, Date endDate) {
		Log.debug("Request receieved in dao to get the count of candidates that  were hired for jobrole {} and state {} from {} to {}",jobrole,state,startDate,endDate);
		Map<String,Object> params = new HashMap<>(8);
		params.put("jobrole",jobrole);
		params.put("state",state);
		params.put("jobStatusApproved", ReadApplicationConstants.getJobApprovalStatusApproved());
		params.put("activeFlagTrue", ReadApplicationConstants.getTrueFlag());
		params.put("jobStatusNotPublished", ReadApplicationConstants.getNotPublishedState());
		params.put("jobApplicationStatusHired",ReadApplicationConstants.getApplicationStateHired());
		params.put("startDate",startDate);
		params.put("endDate",endDate);
		
		Log.debug("Hashmap created with values, jobrole: {}, state: {}",params.get("jobrole"), params.get("state"));
		try {
			Log.debug("In try block of dao to get the count of candidates that were hired for jobrole {} and state {}",params.get("jobrole"), params.get("state"));
			return getJdbcTemplate().queryForObject(adminFaqConfig.getCountStateWiseHiredCandidatesSql(), params, Integer.class);
		}
		catch(Exception e) {
			Log.error("An exception occured while fetching the count of candidates that were hired : "+e);
			return -25;
		}
	}
	
	
	
	/**
	 * @author Jyoti Singh
	 * @since 18/12/2020
	 * 
	 * @param jobrole
	 * Method to get total count of job listings posted for selected job role
	 * @return if success : job postings count ,in case of exception : -25
	 */
	public int countJobroleSpecificJobPostings(String jobrole) {
		Log.debug("Request receieved in dao to get the count of job listings posted for jobrole {} ",jobrole);
		Map<String,Object> params = new HashMap<>(5);
		params.put("jobrole",jobrole);
		params.put("enrolmentStatusApproved", ReadApplicationConstants.getApprovedFlag());
		params.put("jobApprovalStatusApproved", ReadApplicationConstants.getJobApprovalStatusApproved());
		params.put("activeFlagTrue", ReadApplicationConstants.getTrueFlag());
		params.put("jobStatusNotPublished", ReadApplicationConstants.getNotPublishedState());
		
		Log.debug("Hashmap created with values, jobrole - {}",params.get("jobrole"));
		try {
			Log.debug("In try block of dao to get to get the count of job listings posted for jobrole {} ",params.get("jobrole"));
			return getJdbcTemplate().queryForObject(adminFaqConfig.getCountJobroleSpecificJobPostingsSql(), params, Integer.class);
		}
		catch(Exception e) {
			Log.error("An exception occured while fetching the count of job postings : "+e);
			return -25;
		}
	}
	
	
	/**
	 * @author Jyoti Singh
	 * @since 18/12/2020
	 * 
	 * @param startDate
	 * @param endDate
	 * Method to get total count hired CGSC certified candidates within the selected start date and end date
	 * @return if success : candidate count ,in case of exception : -25
	 */
	public int countOfPlacedCGSCCertifiedCandidates(Date startDate, Date endDate) {
		Log.debug("Request receieved in dao to get total count hired CGSC certified candidates from {} to {}",startDate,endDate);
		Map<String,Object> params = new HashMap<>(4);
		params.put("startDate",startDate);
		params.put("endDate",endDate);
		params.put("statusHired", ReadApplicationConstants.getApplicationStateHired());
		params.put("trueFlag", ReadApplicationConstants.getTrueFlag());
		
		Log.debug("Hashmap created and parameters successfully inserted");
		try {
			Log.debug("In try block of dao to get total count hired CGSC certified candidates from {} to {}",params.get("startDate"),params.get("endDate"));
			return getJdbcTemplate().queryForObject(adminFaqConfig.getCountHiredCGSCCertifiedCandidatesSql(), params, Integer.class);
		}
		catch(Exception e) {
			Log.error("An exception occured while fetching total count hired CGSC certified candidates : "+e);
			return -25;
		}
	}
	
	
	/**
	 * @author Jyoti Singh
	 * @since 18/12/2020
	 * 
	 * @param startDate
	 * @param endDate
	 * Method to get total count inactive employers within the selected start date and end date
	 * Inactive employers - the employers whose account status is active and approved but have not published any job post 
	 * within the selected time that was approved by admin
	 * @return if success : candidate count ,in case of exception : -25
	 */
	public int countInactiveEmployer(Date startDate, Date endDate) {
		Log.debug("Request receieved in dao to get count of inactive employers from {} to {}",startDate,endDate);
		Map<String,Object> params = new HashMap<>(6);
		params.put("startDate",startDate);
		params.put("endDate",endDate);
		params.put("userRoleEmployer", ReadApplicationConstants.getUserRoleEmployer());
		params.put("enrolmentStatusApproved", ReadApplicationConstants.getApprovedFlag());
		params.put("jobApprovalStatusApproved", ReadApplicationConstants.getJobApprovalStatusApproved());
		params.put("activeFlagTrue", ReadApplicationConstants.getTrueFlag());
		
		Log.debug("Hashmap created and parameters successfully inserted");
		try {
			Log.debug("In try block of dao to get count of inactive employers from {} to {}",params.get("startDate"),params.get("endDate"));
			return getJdbcTemplate().queryForObject(adminFaqConfig.getCountInactiveEmployersSql(), params, Integer.class);
		}
		catch(Exception e) {
			Log.error("An exception occured while fetching total count of inactive employers: "+e);
			return -25;
		}
	}
	

	/**
	 * @author Jyoti Singh
	 * @since 17/12/2020
	 * @param employerUserId
	 * @param startDate
	 * @param endDate
	 * @param jobrole
	 * 
	 * Method to get the employer wise candidate details with status hired or shortlisted for a selected job role
	 * @return if success : Collection {@link JobCandidateDetailsDto},in case of exception : null
	 */
	public Collection<JobCandidateDetailsDto> getCompanyWiseCandidatesForJobrole(int employerUserId, String jobrole,
			Date startDate, Date endDate) {
		Log.debug("Request receieved in controller to fetch candidate details for jobs posted by employer with id {} with job role {}",employerUserId,jobrole);
		Map<String,Object> params = new HashMap<>(9);
		params.put("employerUserId",employerUserId);
		params.put("jobrole", jobrole);
		params.put("jobApprovalStatusApproved",ReadApplicationConstants.getJobApprovalStatusApproved());
		params.put("activeFlagTrue",ReadApplicationConstants.getTrueFlag());
		params.put("jobStatusNotPublished",ReadApplicationConstants.getNotPublishedState());
		params.put("startDate", startDate);
		params.put("endDate", endDate);
		params.put("jobStatusShorlisted", ReadApplicationConstants.getApplicationStateShortlisted());
		params.put("jobStatusHired", ReadApplicationConstants.getApplicationStateHired());
		Log.debug("Hashmap created with values, employer user Id- {}, jobrole- {}, start date- {}, end date- {}",params.get("employerUserId"), params.get("jobrole"),params.get("startDate"),params.get("endDate"));
		try {
			Log.debug("In try block of dao to fetch candidate details");
			return getJdbcTemplate().query(adminFaqConfig.getCompanyWiseHiredShortlistedCandidatesSql(), params, jobCandidateDetailsDtoRowMapper);
		}
		catch(Exception e) {
			Log.error("An exception occured while fetching the count of total jobs posted by the employer: "+e);
			return null;
		}
	}
	


	/**
	 * @author Jyoti Singh
	 * @since 17/12/2020
	 * 
	 * @param minJobCount
	 * @param jobrole
	 * @param occupation
	 * @param startDate
	 * @param endDate
	 * 
	 * Method to get the list of employers with job postings count greater than the min job count for the selected jobrole and occupation
	 * within the selected startDate and endDate 
	 * @return if success : Collection {@link EmployerDetailsDto},in case of exception : null
	 */
	public Collection<EmployerDetailsDto> getEmployersWithMinimumJobPostings(int minJobCount, String jobrole,
			String occupation, Date startDate, Date endDate) {
		Log.debug("Request receieved in dao method to fetch employer list with job postings count greater than - {} for  job role {} and occupation {} between {} and {}",minJobCount,jobrole,occupation,startDate, endDate);
		Map<String,Object> params = new HashMap<>(9);
		params.put("minJobCount",minJobCount);
		params.put("jobrole", jobrole);
		params.put("occupation", occupation);
		params.put("startDate", startDate);
		params.put("endDate", endDate);
		params.put("employerEnrolmentStatus", ReadApplicationConstants.getApprovedFlag());
		params.put("jobStatusNotPublished", ReadApplicationConstants.getNotPublishedState());
		params.put("jobStatusApproved", ReadApplicationConstants.getJobApprovalStatusApproved());
		params.put("activeFlagTrue",ReadApplicationConstants.getTrueFlag());
		
		Log.debug("Hashmap created with values, minimum job count- {}, jobrole - {}, occupation - {},start date- {}, end date- {}",params.get("minJobCount"), params.get("jobrole"),params.get("occupation"),params.get("startDate"),params.get("endDate"));
		try {
			Log.debug("In try block of dao to fetch employer details");
			return getJdbcTemplate().query(adminFaqConfig.getEmployersDetailsWithJobCountSql(), params,employerDetailsDtoRowMapper);
		}
		catch(Exception e) {
			Log.error("An exception occured while fetching the employer details: "+e);
			return null;
		}
	}

	/**
	 * @author Sarthak Bhutani
	 * @since 28/12/2020
	 * This method returns the total number of distinct candidates that got hired within the specified time duration on the platform
	 * @param startDate
	 * @param endDate
	 * @return (int)HiredCandidatesCount, if success. -25, if exception
	 */
	public int getHiredCandidateCount(Date startDate, Date endDate){
		Log.debug("Request received in dao to get hired candidate count in time duration : {} to {}",startDate,endDate);
		HashMap<String,Object> params = new HashMap<>(7);
		params.put("startDate", startDate);
		params.put("endDate",endDate);
		params.put("hiredStatus",ReadApplicationConstants.getApplicationStateHired());
		params.put("trueFlag",ReadApplicationConstants.getTrueFlag());
		params.put("approvedStatus",ReadApplicationConstants.getJobApprovalStatusApproved());
		params.put("publishedStatus",ReadApplicationConstants.getPublishedState());
		params.put("closedStatus",ReadApplicationConstants.getClosedState());
		Log.debug("Hashmap created & inserted values - startDate : {}, endDate : {}, hiredStatus : {}, trueFlag : {}, approvedStatus : {}, publishedStatus : {}, closedStatus : {}",
				params.get("startDate"),params.get("endDate"),params.get("hiredStatus"),params.get("trueFlag"),params.get("approvedStatus"),params.get("publishedStatus"),params.get("closedStatus"));
		try{
			Log.debug("In try to block to execute the query");
			return getJdbcTemplate().queryForObject(adminFaqConfig.getGetHiredCandidateCount(),params,Integer.class);
		}
		catch (Exception e){
			Log.error("An exception occurred while fetching the hired candidate count : "+e);
			return -25;
		}
	}
	
	
	/**
	 * @author Prateek Kapoor
	 * @since 28-12-2020
	 * This method fetches the total job vacancies created by different employers in a particular state during a specific time duration
	 * @param startDate
	 * @param endDate
	 * @param state
	 * @return count of total vacancies created if success; else returns null
	 */
	public int fetchStateWiseVacancyCreated(Date startDate, Date endDate, String state)
	{
		Log.debug("Request received in dao to fetch the total vacancies created in state of {} from {} to {}", state, startDate, endDate);
		Map<String,Object> params = new HashMap<>(8);
		params.put("state", state);
		params.put("startDate", startDate);
		params.put("endDate", endDate);
		params.put("jobStatuApproved", ReadApplicationConstants.getJobApprovalStatusApproved());
		params.put("jobStatusPublished", ReadApplicationConstants.getPublishedState());
		params.put("jobStatusClosed", ReadApplicationConstants.getClosedState());
		params.put("activeFlagTrue", ReadApplicationConstants.getTrueFlag());
		params.put("enrollmentStatusApproved", ReadApplicationConstants.getApprovedFlag());
		Log.debug("Hashmap created and state - {} insereted into hashmap", params.get("state"));
		try 
		{
			Log.debug("In try block to execute the query and fetch the result");
			return getJdbcTemplate().queryForObject(adminFaqConfig.getCountTotalVacanciesInState(), params, Integer.class);
		}
		catch (Exception e) 
		{
			Log.error("An exception occurred while fetching the state wise vacancies created - "+e);
			return -25;
		}
		
	}
	
	
	/**
	 * Row Mapper for {@link jobCandidateDetailsDto} to view candidate details for a job post
	 * @author Jyoti Singh
	 *
	 */
	static class JobCandidateDetailsDtoRowMapper implements RowMapper<JobCandidateDetailsDto>{

		@Override
		public JobCandidateDetailsDto mapRow(ResultSet rs, int rowNum) throws SQLException {				
			 int id = rs.getInt("id");
			 String jobId = rs.getString("job_id");
			 String jobTitle = rs.getString("job_title");
			 String candidateName = rs.getString("candidateName");
			 long mobileNumber = rs.getLong("mobileNumber");
			 long guardianNumber = rs.getLong("guardianNumber");
			 String descriptionDocumentPath = rs.getString("job_description_path");
			 String jobStatus = rs.getString("jobStatus");

			return new JobCandidateDetailsDto(id,jobTitle,jobId,candidateName,mobileNumber,
					guardianNumber,descriptionDocumentPath,jobStatus);
		}
		
	}
	
	
	/**
	 * Row Mapper for {@link EmployerDetailsDto} to view candidate details for a job post
	 * @author Jyoti Singh
	 *
	 */
	static class EmployerDetailsDtoRowMapper implements RowMapper<EmployerDetailsDto>{

		@Override
		public EmployerDetailsDto mapRow(ResultSet rs, int rowNum) throws SQLException {				
			int id = rs.getInt("id");
			String employerName = rs.getString("username");
			String liasingAuthorityName = rs.getString("liasing_authority");
			String email = rs.getString("email_address");
			int totalJobPostings = rs.getInt("totalJobListings");
			long mobileNumber = rs.getLong("mobile_number");
			return new EmployerDetailsDto(id,employerName,mobileNumber,liasingAuthorityName,email,
					totalJobPostings);
		}
		
	}
	
	
}
