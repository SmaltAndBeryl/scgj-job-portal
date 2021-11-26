package com.cgsc.service;

import java.sql.Date;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgsc.dao.AdminFaqDao;
import com.cgsc.dto.EmployerDetailsDto;
import com.cgsc.dto.JobCandidateDetailsDto;

@Service
public class AdminFaqService {
	private static final Logger Log = LoggerFactory.getLogger(AdminFaqService.class);

	@Autowired
	private AdminFaqDao adminFaqDao;
	
	/**
	 * @author Jyoti Singh
	 * @since 17/12/2020
	 * @param employerUserId
	 * Method to get total count of jobs posted by the employer
	 * @return if success : jobs posted count ,in case of exception : -25
	 */
	public int getTotalJobsByEmployer(int employerUserId) {
		Log.debug("Request receieved in service to fetch the count of total jobs posted by the employer");
		return adminFaqDao.getTotalJobsByEmployer(employerUserId);
	}
	
	
	/**
	 * @author Jyoti Singh
	 * @since 18/12/2020
	 * 
	 * @param jobrole
	 * @param endDate
	 * @param startDate
	 * Method to get total count of candidates that were hired for a selected jobrole from given start date to end date
	 * @return if success : candidate count ,in case of exception : -25
	 */
	public int getJobroleSpecificHiredCandidateCount(String jobrole, Date startDate, Date endDate) {
		Log.debug("Request receieved in service to get total count of candidates that were hired for a selected jobrole -{} from {} to {}",jobrole,startDate,endDate);
		return adminFaqDao.getJobroleSpecificHiredCandidateCount(jobrole,startDate,endDate);
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
		Log.debug("Request receieved in service to get the count of candidates that were hired for jobrole {} and state {} from {} to {}",jobrole,state,startDate,endDate);
		return adminFaqDao.countStateJobroleSpecificHiredCandidate(jobrole,state,startDate,endDate);
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
		Log.debug("Request receieved in service to get the count of job listings posted for jobrole {} ",jobrole);
		return adminFaqDao.countJobroleSpecificJobPostings(jobrole);
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
		Log.debug("Request received in service to fetch the total vacancies created in state of {} from {} to {}", state, startDate, endDate);
		return adminFaqDao.fetchStateWiseVacancyCreated(startDate, endDate, state);
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
		Log.debug("Request receieved in service to get total count hired CGSC certified candidates from {} to {}",startDate,endDate);
		return adminFaqDao.countOfPlacedCGSCCertifiedCandidates(startDate,endDate);
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
		Log.debug("Request receieved in service to get total count of inactive employers from {} to {}",startDate,endDate);
		return adminFaqDao.countInactiveEmployer(startDate,endDate);
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
		Log.debug("Request received in service to get candidate details for jobs posted by employer with id - {} with jobrole - {}, from {} to {}",employerUserId,jobrole,startDate,endDate);
		return adminFaqDao.getCompanyWiseCandidatesForJobrole(employerUserId,jobrole,startDate,endDate);
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
		Log.debug("Request receieved in service to get employer list with job postings greater than - {} for jobrole {} and occupation {} between {} and {}",minJobCount,jobrole,occupation,startDate, endDate);
		Log.debug("Sending request to dao method to fetch employer list");
		return adminFaqDao.getEmployersWithMinimumJobPostings(minJobCount,jobrole,occupation,startDate, endDate);
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
		Log.debug("Request received in service to get hired candidate count in time duration : {} to {}",startDate,endDate);
		return adminFaqDao.getHiredCandidateCount(startDate,endDate);
	}
}
