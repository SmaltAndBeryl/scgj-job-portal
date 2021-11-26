package com.cgsc.controller;

import java.util.Objects;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cgsc.common.Privilege;
import com.cgsc.common.ReadApplicationConstants;
import com.cgsc.dto.JobApplicationSummaryDto;
import com.cgsc.service.JobApplicationService;
import com.cgsc.utility.DownloadFileUtility;

@RestController
public class JobApplicationSummaryController 
{

	private static final Logger Log = LoggerFactory.getLogger(JobApplicationSummaryController.class);
	
	@Autowired
	private JobApplicationService jobApplicationService;
	@Autowired
	private DownloadFileUtility downloadFileUtility;
	
	/**
	 * @author Prateek Kapoor
	 * @since 19-10-2020
	 * This method returns the job application summary for the received jobId
	 * @param jobId
	 * @return Object of {@link JobApplicationSummaryDto} if success;
	 * Else returns null
	 */
	@Privilege(value = {"Employer"})
	@GetMapping("/viewJobApplicants")
	public JobApplicationSummaryDto viewJobApplicationSummary(@RequestParam("jobId") int jobId)
	{
		Log.debug("Request received in controller to view the job application summary for jobId {}",jobId);
		return jobApplicationService.viewJobApplicationSummary(jobId);
	}
	
	/**
	 * This method downloads the job application summary report for the received jobId
	 * @author Prateek Kapoor
	 * @since 22-10-2020
	 * @param jobId
	 */
	@Privilege(value= {"Employer"})
	@GetMapping("/downloadAllApplicantDetails")
	public void downloadJobApplicationSummaryReport(@RequestParam("jobId") int jobId, @RequestParam("reportFormat") String reportType, HttpServletResponse response)
	{
		Log.debug("Request received in controller to download the job application summary report for jobId {} and report format {}",jobId, reportType);
		String reportPath = jobApplicationService.generateJobApplicationSummaryReport(jobId, reportType);
		if(Objects.isNull(reportPath))
		{
			Log.error("Could not generate the Job Application Summary Report");
			return;
		}
		Log.debug("The report path for Job Application Summary report is {}",reportPath);
		Log.debug("Sending request to download the file at path {}",reportPath);
		
		if(reportType.equalsIgnoreCase(ReadApplicationConstants.getReportFormatPdf()))
		{
			Log.debug("The report format is pdf, sending request to download the report in pdf format");
			downloadFileUtility.downloadReport(reportPath, response);	
		}
		else
		{
			Log.debug("The report format is excel, sending request to download the report in xls format");
			downloadFileUtility.downloadExcelFile(reportPath, response);
		}
	}
}
