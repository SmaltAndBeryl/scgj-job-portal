package com.cgsc.controller;

import java.sql.Date;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cgsc.common.Privilege;
import com.cgsc.dto.EmployerDetailsDto;
import com.cgsc.dto.JobCandidateDetailsDto;
import com.cgsc.service.AdminFaqService;

@RestController
public class AdminFaqController {
	
	private static final Logger Log = LoggerFactory.getLogger(AdminFaqController.class);
	
	@Autowired
	private AdminFaqService adminFaqService;
	
	/**
	 * @author Jyoti Singh
	 * @since 17/12/2020
	 * @param employer user id
	 * Method to get total count of jobs posted by the employer
	 * @return if success : jobs posted count(int) ,in case of exception : -25
	 */
	@Privilege(value={"Admin"})
	@GetMapping("/getTotalJobsByEmployer")
	public int getTotalJobsByEmployer(@RequestParam("employerUserId") int employerUserId) {
		Log.debug("Request receieved in controller to fetch the count of total jobs posted by the employer");
		return adminFaqService.getTotalJobsByEmployer(employerUserId);
	}
	
	
	/**
	 * @author Jyoti Singh
	 * @since 18/12/2020
	 * Method to get total count of candidates hired for a selected jobrole from given start date to end date
	 * 
	 * @param jobrole (String)
	 * @param startDate (Date)
	 * @param endDate (Date)
	 * 
	 * @return if success : candidate count ,in case of exception : -25
	 */
	@Privilege(value={"Admin"})
	@GetMapping("/countOfJobroleSpecificHiredCandidates")
	public int getJobroleSpecificHiredCandidateCount(@RequestParam("jobrole") String jobrole,@RequestParam("startDate") Date startDate,@RequestParam("endDate") Date endDate) {
		Log.debug("Request receieved in controller to fetch the count of candidates that were hired for jobrole - {} from - {} to - {}",jobrole,startDate,endDate);
		return adminFaqService.getJobroleSpecificHiredCandidateCount(jobrole,startDate,endDate);
	}
	
	
	/**
	 * @author Jyoti Singh
	 * @since 18/12/2020
	 * 
	 * @param jobrole
	 * @param state
	 * @param endDate
	 * @param startDate
	 * Method to get total count of candidates that were hired for a selected jobrole and selected state within the given start date and end date
	 * @return if success : candidate count ,in case of exception : -25
	 */
	@Privilege(value={"Admin"})
	@GetMapping("/countStateWiseHiredCandidates")
	public int countStateJobroleSpecificHiredCandidate(@RequestParam("jobrole") String jobrole,@RequestParam("state") String state,@RequestParam("startDate") Date startDate,@RequestParam("endDate") Date endDate) {
		Log.debug("Request receieved in controller to get the count of candidates that were hired for jobrole {} and state {} from {} to {}",jobrole,state,startDate,endDate);
		return adminFaqService.countStateJobroleSpecificHiredCandidate(jobrole,state,startDate,endDate);
	}
	
	
	/**
	 * @author Jyoti Singh
	 * @since 18/12/2020
	 * 
	 * @param jobrole
	 * Method to get total count of job listings that were posted for selected job role
	 * @return if success : job postings count ,in case of exception : -25
	 */
	@Privilege(value={"Admin"})
	@GetMapping("/countOfJobroleSpecificJobs")
	public int countJobroleSpecificJobPostings(@RequestParam("jobrole") String jobrole) {
		Log.debug("Request receieved in controller to get the count of jobs listings that were posted for jobrole {} ",jobrole);
		return adminFaqService.countJobroleSpecificJobPostings(jobrole);
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
	@Privilege(value={"Admin"})
	@GetMapping("/getCountOfPlacedCGSCCertifiedCandidates")
	public int countOfPlacedCGSCCertifiedCandidates(@RequestParam("startDate") Date startDate,@RequestParam("endDate") Date endDate){
		Log.debug("Request receieved in controller to get total count hired CGSC certified candidates from {} to {}",startDate,endDate);
		return adminFaqService.countOfPlacedCGSCCertifiedCandidates(startDate,endDate);
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
	 * 
	 * @return if success : candidate count ,in case of exception : -25
	 */
	@Privilege(value={"Admin"})
	@GetMapping("/countOfInactiveEmployer")
	public int countInactiveEmployer(@RequestParam("startDate") Date startDate,@RequestParam("endDate") Date endDate){
		Log.debug("Request receieved in controller to get total count of inactive employers from {} to {}",startDate,endDate);
		return adminFaqService.countInactiveEmployer(startDate,endDate);
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
	@Privilege(value={"Admin"})
	@GetMapping("/companyWiseHiredShortlistedCandidates")
	public Collection<JobCandidateDetailsDto> getCompanyWiseCandidatesForJobrole(@RequestParam("employerUserId") int employerUserId,@RequestParam("jobrole") String jobrole,
			@RequestParam("startDate") Date startDate,@RequestParam("endDate") Date endDate ) {
		Log.debug("Request receieved in controller to fetch candidate details for jobs posted by employer with id - {} with job role {}",employerUserId,jobrole);
		return adminFaqService.getCompanyWiseCandidatesForJobrole(employerUserId,jobrole,startDate,endDate);
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
	@Privilege("Admin")
	@GetMapping("/fetchStateWiseVacancy")
	public int fetchStateWiseVacancyCreated(@RequestParam("startDate") Date startDate,@RequestParam("endDate") Date endDate,@RequestParam("state") String state)
	{
		Log.debug("Request received in controller to fetch the total vacancies created in state of {} from {} to {}", state, startDate, endDate);
		return adminFaqService.fetchStateWiseVacancyCreated(startDate, endDate, state);
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
	@Privilege(value={"Admin"})
	@GetMapping("/getEmployerListWithJobCount")
	public Collection<EmployerDetailsDto> getEmployersWithMinimumJobPostings(@RequestParam("minJobCount") int minJobCount,@RequestParam("jobrole") String jobrole,
			@RequestParam("occupation") String occupation, @RequestParam("startDate") Date startDate,@RequestParam("endDate") Date endDate ) {
		Log.debug("Request receieved in controller to fetch employer list with job postings count greater than - {} for  job role {} and occupation {} between {} and {}",minJobCount,jobrole,occupation,startDate, endDate);
		return adminFaqService.getEmployersWithMinimumJobPostings(minJobCount,jobrole,occupation,startDate, endDate);
	}

	/**
	 * @author Sarthak Bhutani
	 * @since 28/12/2020
	 * This method returns the total number of distinct candidates that got hired within the specified time duration on the platform
	 * @param startDate
	 * @param endDate
	 * @return (int)HiredCandidatesCount, if success. -25, if exception
	 */
	@Privilege(value={"Admin"})
	@GetMapping("/hiredCandidatesCount")
	public int getHiredCandidateCount(@RequestParam("startDate") Date startDate,@RequestParam("endDate") Date endDate){
		Log.debug("Request received in controller to get hired candidate count in time duration : {} to {}",startDate,endDate);
		return adminFaqService.getHiredCandidateCount(startDate,endDate);
	}
}
