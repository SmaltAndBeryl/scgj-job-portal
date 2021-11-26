package com.cgsc.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.model.S3Object;
import com.cgsc.common.AmazonClient;
import com.cgsc.common.ReadApplicationConstants;
import com.cgsc.common.ReadProfileProperties;
import com.cgsc.dao.JobApplicationSummaryDao;
import com.cgsc.dto.JobApplicationSummaryDto;
import com.cgsc.utility.FetchLogoFromS3Utility;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

@Service
public class JobApplicationService
{

	private static final Logger Log = LoggerFactory.getLogger(JobApplicationService.class);
	
	@Autowired
	private JobApplicationSummaryDao jobApplicationSummaryDao;
	@Autowired
	private FetchLogoFromS3Utility fetchLogoFromS3Utility;
	@Autowired
	private ReadProfileProperties readProfileProperties;
	@Autowired
	private AmazonClient amazonClient;
	
	
	/**
	 * @author Prateek Kapoor
	 * @since 19-10-2020
	 * This method returns the job application summary for the received jobId
	 * @param jobId
	 * @return Object of {@link JobApplicationSummaryDto} if success;
	 * Else returns null
	 */
	public JobApplicationSummaryDto viewJobApplicationSummary(int jobId)
	{
		Log.debug("Request received in service to view the job application summary for jobId {}",jobId);
		return jobApplicationSummaryDao.viewJobApplicationSummary(jobId);
	}
	
	/**
	 * @author Prateek Kapoor
	 * @since 22-10-2020
	 * This method generates the jobApplicationSummary report for a given jobId
	 * @param jobId
	 * @return path of generated file if success;
	 * else returns null
	 *
	 * @updated by Sarthak Bhutani
	 * @updated on 09/12/2020
	 * @update - Added totalHired in reportParameters Hashmap
	 */
	public String generateJobApplicationSummaryReport(int jobId, String reportType)
	{
		Log.debug("Request received in service to generate the job application summary report for jobId {} and report type {}",jobId, reportType);
		JobApplicationSummaryDto jobApplicationSummary = jobApplicationSummaryDao.viewJobApplicationSummary(jobId);
		if(Objects.isNull(jobApplicationSummary))
		{
			Log.error("Could not fetch the job application summary details, returning null");
			return null;
		}
		Log.debug("Fetched the job application summary details for jobId {}. Sending request to fetch candidate details",jobId);
		Collection<JobApplicationSummaryDto> candidateDetails = jobApplicationSummaryDao.viewCandidateDetails(jobId);
		if(Objects.isNull(candidateDetails))
		{
			Log.error("An exception occurred while fetching the candidate details for job Id {}",jobId);
			return null;
		}
		if(candidateDetails.isEmpty())
		{
			Log.debug("No candidate has applied for job post id {}",jobId);
			return null;
		}
		Log.debug("Fetched the candidate details for jobId {}",jobId);
		Log.debug("Inserting details into JR Bean Collection");
		JRBeanCollectionDataSource candidateApplicationDetails = new JRBeanCollectionDataSource(candidateDetails);
		Map<String,Object> reportParameters=new HashMap<String,Object>();
		reportParameters.put("candidateApplicationDetails",candidateApplicationDetails);
		reportParameters.put("jobId", jobApplicationSummary.getJobId());
		reportParameters.put("jobTitle", jobApplicationSummary.getJobTitle());
		reportParameters.put("applicationLastDate", jobApplicationSummary.getApplicationLastDate());
		reportParameters.put("totalApplications", jobApplicationSummary.getTotalNumberOfApplicants());
		reportParameters.put("totalVacancy", jobApplicationSummary.getVacancy());
		reportParameters.put("totalHired",jobApplicationSummary.getHired());
		reportParameters.put("totalShortListed", jobApplicationSummary.getSelected());
		reportParameters.put("totalInReview", jobApplicationSummary.getInReview());
		reportParameters.put("totalRejected", jobApplicationSummary.getRejected());
		String cgscLogoPath = fetchLogoFromS3Utility.fetchLogoFromS3Bucket(readProfileProperties.getCgscLogoPath());
		reportParameters.put("capitalGoodsLogo", cgscLogoPath);
		Log.debug("Report parameters inserted into hashmap");
		Log.debug("Reading Jasper report file");
		String outputFile =null;
			try 
			{				
				if(reportType.equalsIgnoreCase(ReadApplicationConstants.getReportFormatPdf()))
				{
					Log.debug("The report format is .pdf, the report will be generated as a pdf document");
					outputFile = ReadApplicationConstants.getTemporaryDirectory()+ReadApplicationConstants.getJobApplicationSummaryReportName()+ReadApplicationConstants.getSpaceReplacementChar()+jobId+ReadApplicationConstants.getPdfFileExtension();
					Log.debug("Getting input stream");
					S3Object s3Object = amazonClient.s3client.getObject(readProfileProperties.getBucketName(),readProfileProperties.getJobApplicationSummaryReportPath()); 
					InputStream inputStream = s3Object.getObjectContent();
					JasperPrint printFileName = JasperFillManager.fillReport(inputStream, reportParameters, new JREmptyDataSource());
					OutputStream outputStream = new FileOutputStream(new File(outputFile));
					
				    Log.debug("The output file path -  " + outputFile);
					if (printFileName != null)
					{
						Log.debug("Creating the JR print file");
					    JasperExportManager.exportReportToPdfStream(printFileName, outputStream);
					    Log.debug("Successfully created the jrprint file for job application summary report");
					    Log.debug("PDF generated successfully");
					}
					else 
					{
					    Log.error("JRprint file is empty. Returning null");
					    outputFile = null;
					}
					outputStream.close();
					inputStream.close();
					s3Object.close();
				}
			else
			{
				Log.debug("The format of report is xls, processing request to generate the report in .xlsx format");
				outputFile = ReadApplicationConstants.getTemporaryDirectory()+ReadApplicationConstants.getJobApplicationSummaryReportName()+ReadApplicationConstants.getSpaceReplacementChar()+jobId+ReadApplicationConstants.getXlsFileExtension();
				Log.debug("Getting input stream");
				S3Object s3Object = amazonClient.s3client.getObject(readProfileProperties.getBucketName(),readProfileProperties.getJobApplicationSummaryExcelReportPath()); 
				InputStream inputStream = s3Object.getObjectContent();
				JasperPrint printFileName = JasperFillManager.fillReport(inputStream, reportParameters, new JREmptyDataSource());
				OutputStream outputStream = new FileOutputStream(new File(outputFile));

				 JRXlsxExporter exporter = new JRXlsxExporter();
	             exporter.setExporterInput(new SimpleExporterInput(printFileName));
	             exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputFile));
	             SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration(); 
	             configuration.setRemoveEmptySpaceBetweenRows(true);
	             configuration.setCollapseRowSpan(false);
	             exporter.setConfiguration(configuration);
	             exporter.exportReport();
	             outputStream.close();
	             inputStream.close();
	             s3Object.close();
	             
			}
				File logoFile = new File(cgscLogoPath);
				logoFile.delete();
			}
			catch (Exception e) 
			{
				Log.error(e+" An exception handled while printing jasper report - Job Application Summary Report");
				outputFile = null;
			}     
				Log.debug("Returning path with output file - {}", outputFile);
				return outputFile;
		}
}
