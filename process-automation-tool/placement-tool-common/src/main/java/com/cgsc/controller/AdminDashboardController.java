package com.cgsc.controller;

import java.sql.Date;
import java.util.Objects;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cgsc.common.Privilege;
import com.cgsc.service.AdminDashboardService;
import com.cgsc.utility.DownloadFileUtility;

@RestController
public class AdminDashboardController {

	private static final Logger Log = LoggerFactory.getLogger(AdminDashboardController.class);
	
	@Autowired
	private AdminDashboardService adminDashboardService;
	
	@Autowired
	private DownloadFileUtility downloadFileUtility;
	
	/**
	 * @author Sarthak Bhutani
	 * @since 29/10/2020
	 * Method to get number of active registered companies
	 * @return if success : registeredEmployersCount ,in case of exception : -25
	 */
	@Privilege(value={"Admin"})
	@GetMapping("/getRegisteredEmployersCount")
	public int getRegisteredEmployersCount() {
		Log.debug("Request receieved in controller from front end to get number of active registered companies");
		return adminDashboardService.getRegisteredEmployersCount();
	}
	
	/**
	 * @author Sarthak Bhutani
	 * @since 29/10/2020
	 * Method to get number of active candidates
	 * @return if success : activeCandidatesCount ,in case of exception : -25
	 */
	@Privilege(value={"Admin"})
	@GetMapping("/getActiveCandidatesCount")
	public int getActiveCandidatesCount() {
		Log.debug("Request receieved in controller from front end to get number of active candidates");
		return adminDashboardService.getActiveCandidatesCount();
	}
	
	/**
	 * @author Sarthak Bhutani
	 * @since 30/10/2020
	 * Method to get the number of placed candidates
	 * @return if success : placedCandidatesCount ,in case of exception : -25
	 */
	@Privilege(value={"Admin"})
	@GetMapping("/getPlacedCandidatesCount")
	public int getPlacedCandidatesCount() {
		Log.debug("Request receieved in controller from front end to get the number of placed candidates");
		return adminDashboardService.getPlacedCandidatesCount();
	}
	
	/**
	 * @author Sarthak Bhutani
	 * @since 30/10/2020
	 * Method to get the number of active job posts
	 * @return if success : activeJobPostCount ,in case of exception : -25
	 */
	@Privilege(value={"Admin"})
	@GetMapping("/getTotalJobsCount")
	public int getActiveJobPostCount() {
		Log.debug("Request receieved in controller from front end to get the number of active job posts");
		return adminDashboardService.getActiveJobPostCount();
	}
	
	/**
	 * This method downloads the candidate placement report within the startDate and endDate
	 * @author Prateek Kapoor
	 * @since 22-10-2020
	 * @param startDate
	 * @param endDate
	 * 
	 * @updated by - Jyoti Singh
	 * @updated on- 16-12-2020
	 * @update - 
	 * 1. removed expected parameter report format in API request
	 */
	@Privilege(value= {"Admin"})
	@GetMapping("/downloadCandidatePlacementReport")
	public void downloadJobApplicationSummaryReport(@RequestParam("startDate") Date startDate, @RequestParam("endDate") Date endDate, HttpServletResponse response)
	{
		Log.debug("Request received in controller to generate the candidate placement report for date range - {} to {}",startDate, endDate);
		if(Objects.isNull(startDate) || Objects.isNull(endDate))
		{
			Log.error("The start date {} or end date {} is null",startDate, endDate);
			return;
		}
		String reportPath = adminDashboardService.generatePlacementReport(startDate, endDate);
		if(Objects.isNull(reportPath))
		{
			Log.error("Could not generate the candidate placement report");
			return;
		}
		Log.debug("The report path for candidate placement report is {}",reportPath);
		Log.debug("Sending request to download the file at path {}",reportPath);
		downloadFileUtility.downloadExcelFile(reportPath, response);
	}
	
	/**
	 * @author Prateek Kapoor
	 * @since 21-12-2020
	 * @param startDate
	 * @param endDate
	 * This method generates the candidate report which contains all the details of candidates who have registered on the platform from startDate & endDate
	 * @return path of generated report if success; else returns null
	 */
	@Privilege(value= {"Admin"})
	@GetMapping("/downloadCandidateReport")
	public void generateCandidateReport(@RequestParam("startDate") Date startDate,@RequestParam("endDate") Date endDate, HttpServletResponse response)
	{
		Log.debug("Request received in controller to generate the candidate report having details from startDate to endDate", startDate, endDate);
		String reportPath = adminDashboardService.generateCandidateReport(startDate, endDate);
		if(Objects.isNull(reportPath) || reportPath.isEmpty())
		{
			Log.error("Could not fetch candidate report path, report will not be downloaded");
		}
		else
		{
			Log.debug("Report generated at path {}",reportPath);
			downloadFileUtility.downloadExcelFile(reportPath, response);
		}	
	}
	
	
	/**
	 * @author Prateek Kapoor
	 * @since 22-12-2020
	 * This method downloads the company report having list of companies and the jobs posted (total vacancy, open vacancy and closed vacancy) for a specific time duration and returns the path of the generated report
	 * @param startDate
	 * @param endDate
	 */
	@Privilege(value= {"Admin"})
	@GetMapping("/downloadCompanyReport")
	public void generateCompanyReport(@RequestParam("startDate") Date startDate,@RequestParam("endDate") Date endDate, HttpServletResponse response)
	{
		Log.debug("Request received in controller to download the company report for date range - {} to {}",startDate, endDate);
		String companyReportPath = adminDashboardService.generateCompanyReport(startDate, endDate);
		if(Objects.isNull(companyReportPath) || companyReportPath.isEmpty())
		{
			Log.error("Could not generate the company report, report will not be downloaded");
		}
		else
		{
			Log.debug("Company report generated at path {}", companyReportPath);
			downloadFileUtility.downloadExcelFile(companyReportPath, response);
		}
	}

}
