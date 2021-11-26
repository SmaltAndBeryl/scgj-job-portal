package com.cgsc.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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
import com.cgsc.config.ViewJobPostConfig;
import com.cgsc.dto.GetAllJobPostByEmployerDto;
import com.cgsc.dto.ViewJobPostDto;
import com.cgsc.dto.ViewPublishedJobPostDto;

@Repository
public class ViewJobPostDao extends AbstractTransactionalDao{

	@Autowired
	private ViewJobPostConfig viewJobPostConfig;
	
	private static final Logger Log = LoggerFactory.getLogger(ViewJobPostDao.class);
	private static JobPostRowMapper jobPostRowMapper = new JobPostRowMapper();
	private static GetAllJobPostByEmployerRowMapper getAllJobPostByEmployerRowMapper = new GetAllJobPostByEmployerRowMapper();
	private static ViewJobPostByStatusRowMapper viewJobPostByStatusRowMapper= new ViewJobPostByStatusRowMapper();
	private static ClosedOrPublishedJobMapper closedOrPublishedJobPosts = new ClosedOrPublishedJobMapper();

	/**
	 * @author Sarthak Bhutani
	 * @since 12-10-2020
	 * this method returns the collection of all job posts which are published & are active & whose application date is greater than equal to current date
	 * @return Collection of {@link ViewJobPostDto} if success; else returns null
	 *
	 * @updatedBy Sarthak Bhutani
	 * @updatedOn 18/12/2020
	 * @update - Adding jobStatusApproved in Hashmap
	 */
	public Collection<ViewJobPostDto> viewUpcomingJobs(){
		Log.debug("Request received in dao to fetch Job Posts which are Published & are active & whose application date is greater than equal to current date");
		Log.debug("Creating Hashmap in Dao");
		Map<String,String> params= new HashMap<>(4);
		Log.debug("Adding parameters in Hashmap");
		params.put("published", ReadApplicationConstants.getPublishedState());
		params.put("activeFlagTrue",ReadApplicationConstants.getTrueFlag());
		params.put("enrolmentStatusApproved",ReadApplicationConstants.getApprovedFlag());
		params.put("jobStatusApproved",ReadApplicationConstants.getJobApprovalStatusApproved());
		Log.debug("Hashmap generated with Published state as {} & Active Flag as {} & employer enrolment status as {}",params.get("published"),params.get("activeFlagTrue"),params.get("enrolmentStatusApproved"));
		try {
			Log.debug("In try block for fetching Job Posts");
			return getJdbcTemplate().query(viewJobPostConfig.viewUpcomingJobs(),params,jobPostRowMapper);
		}
		catch(Exception e) {
			Log.error("An exception occured while fetching upcoming Job Posts at dao, exception:" + e);
			return null;
		}
	}
	
	/**
	 * @author Sarthak Bhutani
	 * @since 13-10-2020
	 * this method returns the collection of all active job posts created by logged in employer
	 * @params userId
	 * @return Collection of {@link GetAllJobPostByEmployerDto} if success; else returns null
	 */
	public Collection<GetAllJobPostByEmployerDto> getAllJobPostByEmployer(int userId) {
		Log.debug("Request received in dao to fetch all ative Job Posts by Employer with userId : {}", userId);
		Map<String,Object> params = new HashMap<>(2);
		params.put("userId",userId);
		params.put("activeFlagTrue",ReadApplicationConstants.getTrueFlag());
		Log.debug("HashMap generated with userId : {}, active : {}",params.get("userId"),params.get("activeFlagTrue"));
		try {
			Log.debug("In try block of Dao for fetching all active Job Posts by Employer");
			return getJdbcTemplate().query(viewJobPostConfig.getGetAllJobPostByEmployer(),params,getAllJobPostByEmployerRowMapper);
		}
		catch(Exception e) {
			Log.error("Exception occured while fetching all active Job posts by employer in Dao, exception"+e);
			return null;
		}
	}
	
	
	/**
	 * @author Sarthak Bhutani
	 * @since 14-10-2020
	 * @param jobStatus
	 * @param userId
	 * this method returns the collection of all active job posts based on job status by logged in employer for Publish Job Post page
	 * @return Collection of {@link GetAllJobPostByEmployerDto} if success; else returns null
	 *
	 * @updatedBy Sarthak Bhutani
	 * @updatedOn 18/12/2020
	 * @update changed the name of the method, since another method of name viewJobPostByPublishedStatus is being added in the current commit
	 */
	public Collection<ViewPublishedJobPostDto> viewJobPostByStatusForPublishJobPostPage(String jobStatus, int userId){
		Log.debug("Request received in dao to fetch All Job Post of closed status by employer with user id : {} ", userId);
		Map<String, Object> params = new HashMap<>(3);
		params.put("jobStatus",jobStatus);
		params.put("userId",userId);
		params.put("trueFlag", ReadApplicationConstants.getTrueFlag());
		Log.debug("HashMap generated with values jobStatus: {},  userId: {}, trueFlag: {}",params.get("jobStatus"),params.get("userId"),params.get("trueFlag"));
		try {
			Log.debug("In try block of Dao to fetch job post by employer with jobStatus : {}", jobStatus);
			return getJdbcTemplate().query(viewJobPostConfig.getViewJobPostByStatusForPublishJobPostPage(),params,viewJobPostByStatusRowMapper);
		}
		catch(Exception e){
			Log.error("Exception error occured while fetching job post by employer for jobStatus: "+ jobStatus+ " Exception:"+e);
			return null;
		}
	}

	/**
	 * @author Sarthak Bhutani
	 * @since 18/12/2020
	 * @param jobStatus
	 * @param userId
	 * this method returns the collection of all active job posts based on status by logged in employer & are approved by admin for Close Job Post page
	 * @return Collection of {@link GetAllJobPostByEmployerDto} if success; else returns null
	 *
	 *
	 * NULL CHECK
	 */
	public Collection<ViewPublishedJobPostDto> viewJobPostByStatusForCloseJobPostPage(String jobStatus, int userId){
		Log.debug("Request received in dao to fetch All Job Post of published status by employer with user id : {} ",userId);
		Map<String, Object> params = new HashMap<>(4);
		params.put("jobStatus",jobStatus);
		params.put("userId",userId);
		params.put("trueFlag", ReadApplicationConstants.getTrueFlag());
		params.put("approvedStatus",ReadApplicationConstants.getJobApprovalStatusApproved());
		Log.debug("HashMap generated with values jobStatus: {},  userId: {}, trueFlag: {}, approvedStatus : {}",params.get("jobStatus"),params.get("userId"),params.get("trueFlag"),params.get("approvedStatus"));
		try {
			Log.debug("In try block of Dao to fetch job post by employer with jobStatus : {}", jobStatus);
			return getJdbcTemplate().query(viewJobPostConfig.getViewJobPostByStatusForCloseJobPostPage(),params,viewJobPostByStatusRowMapper);
		}
		catch(Exception e){
			Log.error("Exception error occured while fetching job post by employer for jobStatus: "+ jobStatus+ " Exception:"+e);
			return null;
		}
	}


	/**
	 * @author Sarthak Bhutani
	 * @since 21-10-2020
	 * @param userId
	 * @return Collection of {@link GetAllJobPostByEmployerDto} if success, else return null
	 * this method return the collection of all deleted(active_flg='N') job posts by an employer
	 */
	public Collection<GetAllJobPostByEmployerDto> getAllDeletedJobPostByEmployer(int userId){
		Log.debug("Request received in dao to get all deleted job post by employer with userId: {}",userId);
		Map<String,Object> params = new HashMap<>(2);
		params.put("falseFlag",ReadApplicationConstants.getFalseFlag());
		params.put("userId", userId);
		Log.debug("Hashmap generated with values falseFlag : {}, userId : {}",params.get("falseFlag"),params.get("userId"));
		try {
			Log.debug("In try block to get all deleted job post by employer with userId : {}",userId);
			return getJdbcTemplate().query(viewJobPostConfig.getGetAllJDeletedJobPostByEmployer(),params,getAllJobPostByEmployerRowMapper);
		}
		catch(Exception e) {
			Log.error("An excpetion occured while fetching all deleted job post by employer, exception: {}",e);
			return null;
		}
	}
	
	/**
	 * @author Prateek Kapoor
	 * @since 22-10-2020
	 * This method fetches all the active job posts created by the logged in user with status as closed or published
	 * @param userId
	 * @return Collection of {@link GetAllJobPostByEmployerDto} if success; else returns null
	 *
	 * @updatedBy Sarthak Bhutani
	 * @updatedOn 18/12/2020
	 * @update Added field approvedStatus in Hashmap
	 */
	public Collection<GetAllJobPostByEmployerDto> viewClosedOrPublishedJobPosts(int userId)
	{
		Log.debug("Request received in dao to fetch the closed or published job posts created by user with id {}",userId);
		Map<String,Object> params = new HashMap<>(5);
		params.put("userId", userId);
		params.put("activeFlagTrue", ReadApplicationConstants.getTrueFlag());
		params.put("jobStatusPublished", ReadApplicationConstants.getPublishedState());
		params.put("jobStatusClosed", ReadApplicationConstants.getClosedState());
		params.put("approvedStatus",ReadApplicationConstants.getJobApprovalStatusApproved());
		Log.debug("Hashmap created and parameters inserted into hashmap for user id {}",params.get("userId"));
		try 
		{
			Log.debug("In try block to execute the query and fetch the result");
			return getJdbcTemplate().query(viewJobPostConfig.getViewClosedOrPublishedJobPosts(), params, closedOrPublishedJobPosts);
		} 
		catch (Exception e) 
		{
			Log.error("An exception occurred while fetching the closed or published job posts - "+e);
			return null;
		}
	}
	
	
	/**
	 * Row Mapper for {@link ViewJobPostDto}
	 * @author Prateek Kapoor
	 *
	 *@updated by - Jyoti Singh
	 *@update - added application last date field in rowmapper
	 *@updated on - 7-01-2021
	 */
	static class ClosedOrPublishedJobMapper implements RowMapper<GetAllJobPostByEmployerDto>{

		@Override
		public GetAllJobPostByEmployerDto mapRow(ResultSet rs, int rowNum) throws SQLException 
		{
			SimpleDateFormat dateFormatter=new SimpleDateFormat(ReadApplicationConstants.getDateWithMonthNameFormat());
			int id = rs.getInt("id");
			String jobId = rs.getString("job_id");
			String jobTitle = rs.getString("job_title");
			String jobStatus = rs.getString("job_status");
			String jobRole = rs.getString("job_role");
			String applicationLastDate = Objects.isNull(rs.getDate("application_date"))?null:dateFormatter.format(rs.getDate("application_date"));
			return new GetAllJobPostByEmployerDto(id, jobId, jobTitle, jobStatus, jobRole,applicationLastDate);
		}
	}
	
	/**
	 * Row Mapper for {@link ViewJobPostDto}
	 * @author Sarthak Bhutani
	 * 
	 * @updated by - Jyoti Singh
	 * @updated on - 22-11-2020
	 * @update - Added minSalary and maxSalary values in rowmapper
	 * 
	 * @updatedBy Sarthak Bhutani
	 * @updatedOn 27/11/2020
	 * @update Added field cgscCertificatePreferred in rowmapper
	 *
	 * @updatedBy Sarthak Bhutani
	 * @updatedOn 03/11/2020
	 * @update - Replaced location with district in rowmapper
	 *
	 * @updatedBy Sarthak Bhutani
	 * @updatedOn 04/11/2020
	 * @update - Added field occupation in rowmapper
	 */
	static class JobPostRowMapper implements RowMapper<ViewJobPostDto>{

		@Override
		public ViewJobPostDto mapRow(ResultSet rs, int rowNum) throws SQLException {
			 SimpleDateFormat dateTimeFormatter = new SimpleDateFormat(ReadApplicationConstants.getDateTimeFormat());
			 SimpleDateFormat dateFormatter=new SimpleDateFormat(ReadApplicationConstants.getDateWithMonthNameFormat());
			 
			 int id = rs.getInt("id");
			 String jobId = rs.getString("job_id");
			 String jobTitle = rs.getString("job_title");
			 int totalVacancy= rs.getInt("total_vacancy");
			 int minSalary = rs.getInt("min_salary");
			 int maxSalary = rs.getInt("max_salary");
			 String state = rs.getString("state");
			 String district = rs.getString("district");
			 String occupation = rs.getString("occupation");
			 String minimumExperience = rs.getString("experience_required");
			 String educationQualification = rs.getString("qualification_required");
			 String jobRole = rs.getString("job_role");
			 Date applicationDate = rs.getDate("application_date");
			 String jobSummary = rs.getString("job_summary");
			 String descriptionDocumentPath = rs.getString("job_description_path");
			 String leavePolicy = rs.getString("leave_policy");
			 String monthlyIncentives = rs.getString("monthly_incentives");
			 String workTimings = rs.getString("work_timings");
			 Long contactNumber = rs.getLong("contact_number")==0?null:rs.getLong("contact_number");
			 Timestamp interviewStartDateTimeTs = rs.getTimestamp("interview_start_date_time");	
			 Timestamp interviewEndDateTimeTs = rs.getTimestamp("interview_end_date_time");
			 String isWalkinInterview = rs.getString("walk_in_interview_flg");
			 String preferredGender = rs.getString("preferred_gender");
			 String armyBackgroundPreferred = rs.getString("army_background_preference");
			 String postedBy = rs.getString("posted_by");
			 Date publishedAt = rs.getDate("published_at");
//			 String cgscCertificatePreferred = rs.getString("cgsc_certificate_preference");
			 
			 String interviewStartDateTimeFormatted=null,interviewEndDateTimeFormatted=null,applicationDateFormatted=null, publishedAtFormatted=null; 
			 if(!(Objects.isNull(interviewStartDateTimeTs)))
				  interviewStartDateTimeFormatted = dateTimeFormatter.format(new Date(interviewStartDateTimeTs.getTime()));
			 if(!(Objects.isNull(interviewEndDateTimeTs))) 
				 interviewEndDateTimeFormatted = dateTimeFormatter.format(new Date(interviewEndDateTimeTs.getTime()));
			 if(!(Objects.isNull(applicationDate))) 
				 applicationDateFormatted = dateFormatter.format(applicationDate);
			 if(!(Objects.isNull(publishedAt))) 
				 publishedAtFormatted = dateFormatter.format(publishedAt);
			 
			return new ViewJobPostDto(id,jobId,jobTitle,totalVacancy,minSalary,maxSalary,state,district,occupation,minimumExperience,educationQualification,jobRole,applicationDateFormatted,jobSummary,
					descriptionDocumentPath,leavePolicy,monthlyIncentives,workTimings,contactNumber,interviewStartDateTimeFormatted,interviewEndDateTimeFormatted,
					isWalkinInterview,preferredGender,armyBackgroundPreferred,null,postedBy,publishedAtFormatted);
		}
	}
	
	/**
	 * Row Mapper for {@link GetAllJobPostByEmployerDto} to view all job post by employer & to view deleted job posts
	 * @author Sarthak Bhutani
	 *
	 */
	static class GetAllJobPostByEmployerRowMapper implements RowMapper<GetAllJobPostByEmployerDto>{

		@Override
		public GetAllJobPostByEmployerDto mapRow(ResultSet rs, int rowNum) throws SQLException {
			 SimpleDateFormat formatter=new SimpleDateFormat(ReadApplicationConstants.getDateWithMonthNameFormat());
				
			 int id = rs.getInt("id");
			 String jobId = rs.getString("job_id");
			 String jobTitle = rs.getString("job_title");
			 int vacancy = rs.getInt("total_vacancy");
			 String descriptionDocumentPath = rs.getString("job_description_path");
			 String jobStatus = rs.getString("job_status");
			 String applicationLastDateFormatted = Objects.isNull(rs.getDate("application_date"))?null:formatter.format(rs.getDate("application_date"));
			 String createdAFormatted = Objects.isNull(rs.getDate("created_at"))?null:formatter.format(rs.getDate("created_at"));
			 String publishedAtFormatted = Objects.isNull(rs.getDate("published_at"))?null:formatter.format(rs.getDate("published_at"));
			 String updatedAtFormatted = Objects.isNull(rs.getDate("updated_at"))?null:formatter.format(rs.getDate("updated_at"));

			return new GetAllJobPostByEmployerDto(id,jobId,jobTitle,vacancy,applicationLastDateFormatted,descriptionDocumentPath,createdAFormatted,publishedAtFormatted,jobStatus,updatedAtFormatted);
		}
		
	}
	
	/**
	 * Row Mapper for {@link GetAllJobPostByEmployerDto} for view job post by status by employer
	 * @author Sarthak Bhutani
	 * 
	 * @updated by - Jyoti Singh
	 * @updated on - 10-12-2020
	 */
	static class ViewJobPostByStatusRowMapper implements RowMapper<ViewPublishedJobPostDto>{

		@Override
		public ViewPublishedJobPostDto mapRow(ResultSet rs, int rowNum) throws SQLException {
			 
			 SimpleDateFormat formatter=new SimpleDateFormat(ReadApplicationConstants.getDateWithMonthNameFormat());
			 SimpleDateFormat dateTimeFormatter = new SimpleDateFormat(ReadApplicationConstants.getDateTimeFormat());
			 int id = rs.getInt("id");
			 String jobId = rs.getString("job_id");
			 String jobTitle = rs.getString("job_title");
			 int vacancy = rs.getInt("total_vacancy");
			 String descriptionDocumentPath = rs.getString("job_description_path");
			 String jobStatus = rs.getString("job_status");
			 String createdBy = rs.getString("createdBy");
			 String approvalStatus = rs.getString("approvalStatus");
			 String jobRole = rs.getString("jobRole");
			 String occupation = rs.getString("occupation");
			 String district = rs.getString("district");
			 String state = rs.getString("state");
			 String jobSummary=rs.getString("jobSummary");
//			 String isCGSCCertified=rs.getString("isCGSCCertified");
			 String minimumExperience=rs.getString("minimumExperience");
			 String educationQualification=rs.getString("educationQualification");
			 int minSalary=rs.getInt("minSalary");
			 int maxSalary=rs.getInt("maxSalary");
			 String adminComments=rs.getString("adminComments");
			 String applicationLastDate=Objects.isNull(rs.getDate("applicationLastDate"))?null:formatter.format(rs.getDate("applicationLastDate"));
			 
			 String publishedOn=Objects.isNull(rs.getDate("publishedOn"))?null:formatter.format(rs.getDate("publishedOn"));
			 String createdOn=Objects.isNull(rs.getDate("created_at"))?null:formatter.format(rs.getDate("created_at"));
			 String updatedOn=Objects.isNull(rs.getDate("updatedOn"))?null:formatter.format(rs.getDate("updatedOn"));
			 String leavePolicy =rs.getString("leave_policy");
			 String monthlyIncentives = rs.getString("monthly_incentives");
			 String workTimings = rs.getString("work_timings");
			 Long contactNumber = rs.getLong("contact_number");
			 String isWalkinInterview = rs.getString("walk_in_interview_flg");
			 String preferredGender = rs.getString("preferred_gender");
			 String armyBackgroundPreferred = rs.getString("army_background_preference");
			 
			 String interviewStartDateTime = Objects.isNull(rs.getTimestamp("interview_start_date_time"))?null:dateTimeFormatter.format(new Date(rs.getTimestamp("interview_start_date_time").getTime()));
			 String interviewEndDateTime= Objects.isNull(rs.getTimestamp("interview_end_date_time"))?null:dateTimeFormatter.format(new Date(rs.getTimestamp("interview_end_date_time").getTime()));
				
			 return new ViewPublishedJobPostDto(id,jobId,jobTitle,vacancy,applicationLastDate,descriptionDocumentPath,createdOn,publishedOn,jobStatus,updatedOn,
						 jobRole,createdBy,approvalStatus,occupation,district,state,
						 jobSummary,null,adminComments,educationQualification,
						 minimumExperience,minSalary,maxSalary,leavePolicy,monthlyIncentives,
						 workTimings,contactNumber,isWalkinInterview,preferredGender,
						 armyBackgroundPreferred,interviewStartDateTime,interviewEndDateTime);
		}
		
	}
}
