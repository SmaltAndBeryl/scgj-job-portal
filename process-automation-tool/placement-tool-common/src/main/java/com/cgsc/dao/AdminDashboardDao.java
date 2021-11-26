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

import com.cgsc.common.AbstractTransactionalDao;
import com.cgsc.common.ReadApplicationConstants;
import com.cgsc.config.AdminDashboardConfig;
import com.cgsc.dto.CandidateReportDto;
import com.cgsc.dto.CandidatesPlacedDto;
import com.cgsc.dto.CompanyReportDto;

@Repository
public class AdminDashboardDao extends AbstractTransactionalDao{

	private static final Logger Log = LoggerFactory.getLogger(AdminDashboardDao.class);
	
	private static CandidatePlacementDetailsMapper placedCandidateDetails = new CandidatePlacementDetailsMapper();
		
	private static CandidateReportDetails candidate_report_mapper = new CandidateReportDetails();
	
	private static CompanyReportDetails companyReportDetails = new CompanyReportDetails();
	
	@Autowired
	private AdminDashboardConfig adminDashboardConfig;
	
	/**
	 * @author Sarthak Bhutani
	 * @since 29/10/2020
	 * Method to get number of active registered companies
	 * @return if success : registeredEmployersCount ,in case of exception : -25
	 */
	public int getRegisteredEmployersCount() {
		Log.debug("Request receieved in dao to get number of active registered companies");
		Map<String,String> params = new HashMap<>(3);
		params.put("userRoleEmployer", ReadApplicationConstants.getUserRoleEmployer());
		params.put("trueFlag", ReadApplicationConstants.getTrueFlag());
		params.put("approvedFlag", ReadApplicationConstants.getApprovedFlag());
		Log.debug("Hashmap created with values, userRoleEmployer : {},trueFlag :{}, approvedFlag :{}",params.get("userRoleEmployer"), params.get("trueFlag"),params.get("approvedFlag"));
		try {
			Log.debug("In try block of dao to get number of registered companies");
			return getJdbcTemplate().queryForObject(adminDashboardConfig.getRegisteredEmployersCount(), params, Integer.class);
		}
		catch(Exception e) {
			Log.error("An exception occured while getting number of registered companies: "+e);
			return -25;
		}
	}

	/**
	 * @author Sarthak Bhutani
	 * @since 29/10/2020
	 * Method to get number of active candidates
	 * @return if success : activeCandidatesCount ,in case of exception : -25
	 */
	public int getActiveCandidatesCount() {
		Log.debug("Request receieved in dao to get number of active candidates");
		Map<String,String> params = new HashMap<>(3);
		params.put("userRoleCandidate", ReadApplicationConstants.getUserRoleCandidate());
		params.put("trueFlag", ReadApplicationConstants.getTrueFlag());
		params.put("approvedFlag", ReadApplicationConstants.getApprovedFlag());
		Log.debug("Hashmap created with values, userRoleCandidate : {},trueFlag :{}, approvedFlag :{}",params.get("userRoleCandidate"), params.get("trueFlag"),params.get("approvedFlag"));
		try {
			Log.debug("In try block of dao to get number of candidates");
			return getJdbcTemplate().queryForObject(adminDashboardConfig.getActiveCandidatesCount(), params, Integer.class);
		}
		catch(Exception e) {
			Log.error("An exception occured while getting number Of candidates: "+e);
			return -25;
		}
	}
	
	/**
	 * @author Sarthak Bhutani
	 * @since 30/10/2020
	 * Method to get the number of placed candidates (status- 'Hired')
	 * @return if success : placedCandidatesCount ,in case of exception : -25
	 * 
	 * @updated by - Jyoti 
	 * @updated on - 2 -12-2020
	 */
	public int getPlacedCandidatesCount() {
		Log.debug("Request receieved in dao to get the number of placed candidates");
		Map<String,String> params = new HashMap<>(1);
		params.put("applicationStateHired", ReadApplicationConstants.getApplicationStateHired());
		Log.debug("Hashmap created with values, applicationStateShortlisted: {}",params.get("applicationStateHired"));
		try {
			Log.debug("In try block of dao to get the number of placed candidates");
			return getJdbcTemplate().queryForObject(adminDashboardConfig.getPlacedCandidatesCount(),params,Integer.class);
		}
		catch(Exception e) {
			Log.error("An exception occured while fetching the number of placed candidates: "+e);
			return -25;
		}
	}
	
	/**
	 * @author Sarthak Bhutani
	 * @since 30/10/2020
	 * Method to get the number of active job posts
	 * @return if success : activeJobPostCount ,in case of exception : -25
	 *
	 * @updatedBy Sarthak Bhutani
	 * @updatedOn 24/12/2020
	 * @update - Added in hashmap - userApprovedFlag, jobStatusApprovedFlag, Removed fromm hashmap - approvedFlag
	 */
	public int getActiveJobPostCount() {
		Log.debug("Request receieved in dao to get the number of active job posts");
		Map<String, Object> params = new HashMap<>(2);
		params.put("trueFlag", ReadApplicationConstants.getTrueFlag());
		params.put("publishedState",ReadApplicationConstants.getPublishedState());
		params.put("userApprovedFlag",ReadApplicationConstants.getApprovedFlag());
		params.put("jobStatusApprovedFlag",ReadApplicationConstants.getJobApprovalStatusApproved());
		Log.debug("Hashmap created with values, trueFlag: {}, publishedState:{}, enrolment Status : {}",params.get("trueFlag"),params.get("publishedState"),params.get("approvedFlag"));
		try {
			Log.debug("In try block of dao to get the number of active job post");
			return getJdbcTemplate().queryForObject(adminDashboardConfig.getActiveJobPostCount(),params,Integer.class);
		}
		catch(Exception e) {
			Log.error("An exception occured while fetching the number of active job posts: "+e);
			return -25;
		}
	}
	
	/**
	 * @author Prateek Kapoor
	 * @since 21-12-2020
	 * @param startDate
	 * @param endDate
	 * Thsi method returns the details of all the candidates who have registered on the platform from startDate till endDate
	 * @return Collection of {@link CandidateReportDto} if success; else returns null
	 */
	public Collection<CandidateReportDto> fetchCandidateReportDetails(Date startDate, Date endDate)
	{
		Log.debug("Request received in dao to fetch the candidate details for candidates who have registered from {} to {}", startDate, endDate);
		Map<String,Date> params = new HashMap<>(2);
		params.put("startDate", startDate);
		params.put("endDate", endDate);
		Log.debug("Hashmap created and startdate {} and end date {} inserted into hashmap",params.get("startDate"), params.get("endDate"));
		try 
		{
			Log.debug("In try block to execute the query and fetch the result");
			return getJdbcTemplate().query(adminDashboardConfig.getCandidateReportDetails(), params, candidate_report_mapper);
		}
		catch (Exception e) 
		{
			Log.error("An exception occurred while fetching the candidate report details - "+e);
			return null;
		}
	}
	
	/**
	 * @author Prateek Kapoor
	 * @since 03-11-2020
	 * This method fetches the details of all the candidates who were placed (status set as Shortlisted) in different organisations between a specific time period for jobs with status Published or Closed
	 * @param startDate
	 * @param endDate
	 * @return Collection of CandidatesPlacedDto if success; 
	 * null in case of exception
	 * 
	 * @updated by - Jyoti Singh
	 * @updated on- 16-12-2020
	 * @update - 
	 * 1. Added jobApprval status in hashmap
	 */
	public Collection<CandidatesPlacedDto> candidatePlacementDetails(Date startDate, Date endDate)
	{
		Log.debug("Request received in dao to fetch the list of candidates placed in different organisations from {} till {}", startDate, endDate);
		Map<String,Object> params = new HashMap<>(5);
		params.put("jobStatusNotPublished", ReadApplicationConstants.getNotPublishedState());
		params.put("startDate", startDate);
		params.put("endDate", endDate);
		params.put("activeFlagTrue",ReadApplicationConstants.getTrueFlag());
		params.put("jobStatusApproved",ReadApplicationConstants.getJobApprovalStatusApproved());
		Log.debug("Hashmap created and start date {} and end date {} inserted into hashmap",params.get("startDate"), params.get("endDate"));
		try
		{
			Log.debug("In try block to execute the query and fetch the result");
			return getJdbcTemplate().query(adminDashboardConfig.getCandidatePlacementDetails(), params, placedCandidateDetails);
		}
		catch (Exception e) 
		{
			Log.error("An exception occurred while fetching the placed candidate details "+e);
			return null;
		}
	}

	/**
	 * @author Prateek Kapoor
	 * @since 22-12-2020
	 * @param startDate
	 * @param endDate
	 * This method returns the collection of company report dto which contains the company details and the details of vacancies for different job roles between a specific time duration
	 * @return Collection of {@link CompanyReportDto} if success; else returns null
	 */
	public Collection<CompanyReportDto> fetchCompanyReportDetails(Date startDate, Date endDate)
	{
		Log.debug("Request received in dao to fetch the company report details from {} to {}",startDate, endDate);
		Map<String,Object> params = new HashMap<>(7);
		params.put("startDate", startDate);
		params.put("endDate", endDate);
		params.put("activeFlagTrue", ReadApplicationConstants.getTrueFlag());
		params.put("jobStatusNotPublished", ReadApplicationConstants.getNotPublishedState());
		params.put("approvalStatusApproved", ReadApplicationConstants.getJobApprovalStatusApproved());
		params.put("enrolmentStatusApproved", ReadApplicationConstants.getApprovedFlag());
		params.put("jobStatusHired", ReadApplicationConstants.getApplicationStateHired());
		Log.debug("Hashmap created and parameters inserted into hashmap for start date {} and end date {}", params.get("startDate"), params.get("endDate"));
		
		try 
		{
			Log.debug("In try block to execute the query and fetch the result");
			return getJdbcTemplate().query(adminDashboardConfig.getCompanyReportDetails(), params, companyReportDetails);
		} 
		catch (Exception e) 
		{
			Log.error("An exception occurred while fetching the company report details "+e);
			return null;
		}
	}
	
	/**
	 * Row Mapper for {@link CandidatesPlacedDto}
	 * @author Prateek Kapoor
	 * 
	 * @updated by  - Jyoti Singh
	 * @update - Added Is CGSC certified field in rowmapper
	 *
	 */
	public static class CandidatePlacementDetailsMapper implements RowMapper<CandidatesPlacedDto>
	{
		public CandidatesPlacedDto mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			SimpleDateFormat dateFormatter = new SimpleDateFormat(ReadApplicationConstants.getDateWithMonthNameFormat());
			String candidateName = rs.getString("candidateName");
			String companyName = rs.getString("companyName");
			long mobileNumber = rs.getLong("mobile_number");
			String guardianName = rs.getString("guardian_name");
			long aadhaarNumber =rs.getLong("aadhaar_number");
			String jobRole = rs.getString("job_role");
			String jobId = rs.getString("job_id");
			String offerLetterPath = rs.getString("offer_letter_path");	
			String joiningDate =  Objects.isNull(rs.getDate("joining_date"))?null:dateFormatter.format(rs.getDate("joining_date"));
			String dob =  Objects.isNull(rs.getDate("dob"))?null:dateFormatter.format(rs.getDate("dob"));
			String applicationStatus=rs.getString("applicationStatus");
			int salaryOffered=rs.getInt("salary");
			String jobDistrict = rs.getString("jobDistrict");
			String jobState = rs.getString("jobState");
//			String isCGSCCertified = rs.getString("is_cgsc_certified");
			return new CandidatesPlacedDto(companyName,candidateName,mobileNumber,guardianName,dob,aadhaarNumber,null,jobRole,jobId, 
					 applicationStatus, joiningDate, offerLetterPath,salaryOffered, jobDistrict, jobState);
		}
	}
	
	
	/**
	 * Row Mapper for {@link CandidateReportDto}
	 * @author Prateek Kapoor
	 */
	public static class CandidateReportDetails implements RowMapper<CandidateReportDto>
	{
		public CandidateReportDto mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			SimpleDateFormat dateFormatter = new SimpleDateFormat(ReadApplicationConstants.getDateWithMonthNameFormat());
			SimpleDateFormat dateTimeFormatter = new SimpleDateFormat(ReadApplicationConstants.getDateTimeFormat());
			
			String candidateName = rs.getString("username");
			String state = rs.getString("state");
			String pincode = rs.getString("pincode");
			String profileStatus = rs.getString("is_active_flg").equalsIgnoreCase(ReadApplicationConstants.getTrueFlag())?ReadApplicationConstants.getStatusActive():ReadApplicationConstants.getStatusInActive();
			String profileCreatedOn = Objects.isNull(rs.getDate("registered_on"))?null:dateFormatter.format(rs.getDate("registered_on"));
			String lastLogin = Objects.isNull(rs.getTimestamp("last_login"))?ReadApplicationConstants.getNotApplicableText():dateTimeFormatter.format(rs.getTimestamp("last_login"));
			long mobileNumber = rs.getLong("mobile_number");
			String gender = rs.getString("gender");
			String qualification = rs.getString("qualification");
			int age = rs.getInt("age");
			String address = rs.getString("address");
			String experience = rs.getString("working_experience");
			long aadharNumber = rs.getLong("aadhaar_number");
			String jobRole = rs.getString("job_role");
			
			return new CandidateReportDto(candidateName, state, pincode, profileStatus, aadharNumber, mobileNumber, jobRole, address, lastLogin, profileCreatedOn, qualification, age, gender, experience);
		}
	}
	
	/**
	 * Row Mapper for {@link CompanyReportDto}
	 * @author Prateek Kapoor
	 */
	public static class CompanyReportDetails implements RowMapper<CompanyReportDto>
	{
		public CompanyReportDto mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			SimpleDateFormat dateFormatter = new SimpleDateFormat(ReadApplicationConstants.getDateWithMonthNameFormat());
			SimpleDateFormat dateTimeFormatter = new SimpleDateFormat(ReadApplicationConstants.getDateTimeFormat());
			int employerId = rs.getInt("employerId");
			String jobOccupation = Objects.isNull(rs.getString("occupation"))?ReadApplicationConstants.getNotApplicableText():rs.getString("occupation");
			String profileStatus = rs.getString("profileStatus");
			int totalJobsPosted = rs.getInt("totalVacancy");
			long mobileNumber = rs.getLong("mobile_number");
			String jobDistrict = Objects.isNull(rs.getString("jobDistrict"))?ReadApplicationConstants.getNotApplicableText():rs.getString("jobDistrict");
			String jobState = Objects.isNull(rs.getString("jobState"))?ReadApplicationConstants.getNotApplicableText():rs.getString("jobState");
			String emailAddress = rs.getString("email_address");
			String companyAddress = rs.getString("address");
			String liaisiningAuthorityName = rs.getString("liasing_authority");
			String panNumber = rs.getString("pan_number");
			String companyState = rs.getString("companyState");
			String companyPinCode = rs.getString("pincode");
			String lastLogin = Objects.isNull(rs.getTimestamp("last_login"))?ReadApplicationConstants.getNotApplicableText():dateTimeFormatter.format(rs.getTimestamp("last_login"));
			String profileCreatedOn = Objects.isNull(rs.getDate("registered_on"))?ReadApplicationConstants.getNotApplicableText():dateFormatter.format(rs.getDate("registered_on"));
			String companyName = rs.getString("username");
			int openJobs = rs.getInt("openJobs");
			int closedJobs = rs.getInt("closedJobs");
			
			return new CompanyReportDto(employerId, companyName, panNumber, liaisiningAuthorityName, emailAddress, mobileNumber, companyAddress, companyState, companyPinCode, profileStatus, lastLogin, profileCreatedOn, jobOccupation, totalJobsPosted, openJobs, closedJobs, jobDistrict, jobState);			
		}
	}
	
}
