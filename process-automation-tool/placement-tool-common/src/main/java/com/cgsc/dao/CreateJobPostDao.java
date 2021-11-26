package com.cgsc.dao;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgsc.common.AbstractTransactionalDao;
import com.cgsc.common.ReadApplicationConstants;
import com.cgsc.config.CreateJobPostConfig;
import com.cgsc.dto.CreateJobPostDto;

@Service
public class CreateJobPostDao extends AbstractTransactionalDao
{

	private static final Logger Log = LoggerFactory.getLogger(CreateJobPostDao.class);
	
	@Autowired
	private CreateJobPostConfig createJobPostConfig;
	
	
	/**
	 * @author Prateek Kapoor
	 * @since 09-10-2020
	 * This method fetches the total number of job postings on the platform
	 * @return number of job postings if success or else returns -25
	 */
	public int getTotalJobPostings()
	{
		Log.debug("Request received in dao to fetch the total number of job postings");
		Map<String,Integer>params = new HashMap<>(1);
		try 
		{
			Log.debug("In try block to fetch the total number of job postings");
			return getJdbcTemplate().queryForObject(createJobPostConfig.getTotalJobPostingCount(), params, Integer.class);
		} 
		catch (Exception e) 
		{
			Log.error("An exception occurred while fetching the total number of job postings "+e);
			return -25;
		}
	}


	/**
	 * @author Prateek Kapoor
	 * @since 09-10-2020
	 * This method inserts the job postings into the database
	 * @param createJobPostdto
	 * @param jobId
	 * @param jobPostingsPath
	 * @param employerId
	 * @return 1 if success; -25 in case of exception
	 * 
	 * @updated by - Jyoti Singh
	 * @updated on - 20-11-2020
	 * @update - Added minimum, maximum salary keys in hashmap to insert minimum and maximum salary
	 * 
	 * 
	 * @updatedBy Sarthak Bhutani
	 * @updatedOn 25/11/2020
	 * @update - Added cgscCertificatePreference key in hashmap
	 *
	 * @updatedBy Sarthak Bhutani
	 * @updatedOn 03/11/2020
	 * @update - Replaced location with district in hashmap
	 *
	 * @updatedBy Sarthak Bhutani
	 * @updatedOn 04/11/2020
	 * @update - Added occupation in hashmap
	 */
	public int insertJobPosting(CreateJobPostDto createJobPostdto, String jobId, String jobPostingsPath, Integer employerId) 
	{
		Log.debug("Request received in dao to insert job postings corresponding to job id {}",jobId);
		
		Map<String,Object> params = new HashMap<>(26);
		params.put("jobId", jobId);
		params.put("jobTitle", createJobPostdto.getJobTitle());
		params.put("minSalary", createJobPostdto.getMinSalary());
		params.put("maxSalary", createJobPostdto.getMaxSalary());
		params.put("state", createJobPostdto.getState());
		params.put("district", createJobPostdto.getDistrict());
		params.put("totalVacancy", createJobPostdto.getTotalVacancy());
		params.put("experience", createJobPostdto.getMinimumExperience());
		params.put("qualification", createJobPostdto.getEducationQualification());
		params.put("jobRole", createJobPostdto.getJobRole());
		params.put("occupation",createJobPostdto.getOccupation());
		params.put("applicationDate", createJobPostdto.getApplicationDate());
		params.put("jobSummary", createJobPostdto.getJobSummary());
		params.put("jobDescriptionPath", jobPostingsPath);
		params.put("leavePolicy", createJobPostdto.getLeavePolicy());
		params.put("monthlyIncentives", createJobPostdto.getMonthlyIncentives());
		params.put("workTimmings", createJobPostdto.getWorkTimmings());
		params.put("contactNumber", createJobPostdto.getContactNumber());
		params.put("interviewStartDateTime", createJobPostdto.getInterviewStartDateTime());
		params.put("interviewEndDateTime", createJobPostdto.getInterviewEndDateTime());
		params.put("walkInInterviewFlag", createJobPostdto.getIsWalkinInterview());
		params.put("preferredGender", createJobPostdto.getPreferredGender());
		params.put("armyBackgroundPreference", createJobPostdto.getArmyBackgroundPreferred());
//		params.put("cgscCertificatePreference", createJobPostdto.getCgscCertificatePreferred());
		params.put("employerId", employerId);
		params.put("jobStatusNotPublished", ReadApplicationConstants.getNotPublishedState());
		params.put("activeFlagTrue", ReadApplicationConstants.getTrueFlag());
		
		Log.debug("Hashmap created and parameters inserted into hashmap for job id {}",params.get("jobId"));
		try 
		{
			Log.debug("In try block to insert the job posting details");
			return getJdbcTemplate().update(createJobPostConfig.getInsertJobPost(), params);
		}
		catch (Exception e) 
		{
			Log.error("An exception occurred while inserting the job postings "+e);
			return -25;
		}

	}
}
