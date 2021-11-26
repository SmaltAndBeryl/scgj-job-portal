package com.cgsc.service;

import java.io.File;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgsc.common.ReadApplicationConstants;
import com.cgsc.dao.CreateJobPostDao;
import com.cgsc.dto.CreateJobPostDto;
import com.cgsc.utility.FileUtility;
import com.cgsc.utility.GetLoggedInUserDetailsUtility;

@Service
public class CreateJobPostService 
{

	private static final Logger Log = LoggerFactory.getLogger(CreateJobPostService.class);
	
	@Autowired
	private CreateJobPostDao createJobPostDao;
	@Autowired
	private FileUtility fileUtility;

	
	/**
	 * @author Prateek Kapoor
	 * @since 09-10-2020
	 * This method inserts the job postings into the database
	 * 
	 * @updated by - Jyoti Singh
	 * @updated on - 19-11-2020
	 * @update - Added a check to upload job description document only if the document was uploaded by employer, else not
	 * 
	 * @param createJobPostdto
	 * @param jobId
	 * @param jobPostingsPath
	 * @param employerId
	 * @return 1 if success; -25 in case of exception
	 */
	public int generateJobPosts(CreateJobPostDto createJobPostdto)
	{
		Log.debug("Request received in service to create job post with job title {}",createJobPostdto.getJobTitle());
		Log.debug("Sending request to generate the job id");
		String jobId = this.createJobId();
		String jobPostingsPath;
		if(Objects.isNull(jobId))
		{
			Log.error("Job id could not be generated, returning response to the controller");
			return -25;
		}
		Log.debug("The job id generated is {}",jobId);
		if(!(Objects.isNull(createJobPostdto.getDescriptionDocument()) || createJobPostdto.getDescriptionDocument().isEmpty())) {
			try 
			{
				Log.debug("The job description document was uploaded for the job post");
				Log.debug("Processing request to upload job description document on S3 bucket");
				File jobDescriptionDocument = fileUtility.convertMultiPartToFile(createJobPostdto.getDescriptionDocument());
				Log.debug("Converted multipart file into file object, renaming the file and uploading on S3 bucket");
				jobPostingsPath = ReadApplicationConstants.getJobPostingsFolderPath()+jobId+ReadApplicationConstants.getFileDelimeter()+jobId+ReadApplicationConstants.getSpaceReplacementChar()+ReadApplicationConstants.getDescriptionText();
				Log.debug("The path for saving job description document for job id {} is {}",jobId, jobPostingsPath);
				fileUtility.uploadFileTos3bucket(jobPostingsPath, jobDescriptionDocument);
				jobDescriptionDocument.delete();
				Log.debug("Successfuly uploaded file on S3 bucket. Sending request to insert job posting details into database");
				return createJobPostDao.insertJobPosting(createJobPostdto, jobId,jobPostingsPath,GetLoggedInUserDetailsUtility.getUserIdFromSession());
			} 
			catch (Exception e) 
			{
				Log.error("An exception occurred while generating a job posting "+e);
				return -25;
			}
		}
		else {
			Log.debug("The job description document for the job post was not uploaded by the employer");
			Log.debug("Setting job description document path to null");
			jobPostingsPath=null;
			return createJobPostDao.insertJobPosting(createJobPostdto, jobId,jobPostingsPath,GetLoggedInUserDetailsUtility.getUserIdFromSession());
		}
		
	}
	
	
	/**
	 * @author Prateek Kapoor
	 * @since 09-10-2020
	 * Method to generate a new jobId <JP(Count)>
	 * @return generated job id if success; else returns null 
	 */
	public String createJobId()
	{
		Log.debug("Request received to generate the job id");
		int totalJobCount = createJobPostDao.getTotalJobPostings();
		if(totalJobCount==-25)
		{
			Log.error("Could not fetch the total job count, job id could not be generated");
			return null;
		}
		Log.debug("The total number of job postings on the platform is {}",totalJobCount);
		String jobId = ReadApplicationConstants.getJobIdPrefix()+(ReadApplicationConstants.getJobIdInitialCount()+totalJobCount);
		Log.debug("The new job id is {}",jobId);
		return jobId;
	}
	
	
	
}
