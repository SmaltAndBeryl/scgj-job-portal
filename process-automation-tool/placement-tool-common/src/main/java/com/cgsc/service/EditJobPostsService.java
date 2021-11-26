package com.cgsc.service;

import java.io.File;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgsc.common.ReadApplicationConstants;
import com.cgsc.dao.EditJobPostDao;
import com.cgsc.dto.EditJobPostsDto;
import com.cgsc.utility.FileUtility;

@Service
public class EditJobPostsService 
{

	private static final Logger Log = LoggerFactory.getLogger(EditJobPostsService.class);
	
	@Autowired
	private EditJobPostDao editJobPostDao;
	@Autowired
	private FileUtility fileUtility;
	
	/**
	 * @author Prateek Kapoor
	 * This method returns all the job details corresponding to a job id
	 * @since 14-10-2020
	 * @param jobId
	 * @return Object of EditJobPostsDto if success; else returns null
	 */
	public EditJobPostsDto viewJobPostDetails(int jobId)
	{
		Log.debug("Request received in service to fetch the job post details for job with id {}",jobId);
		return editJobPostDao.viewJobPostDetails(jobId);
	}
	
	

	/**
	 * @author Prateek Kapoor
	 * This method updates the job post details corresponding to a job id by performing the following activities - 
	 * 
	 * 1. Checks if the jobDescriptionDocument was uploaded - 
	 * If true - uploads on S3
	 * Else - fetches the existing path of job description document and updates the details of job post
	 * @since 14-10-2020
	 * @param jobId
	 * @return 1  if success; 
	 * -25 in case of exception
	 * 
	 * @updated by - Jyoti Singh
	 * @updated on - 25-11-2020
	 * @update - Added a check to upload job description document only if the document was uploaded by employer, else not
	 * 
	 */
	public int updateJobPostDetails(EditJobPostsDto editJobPostsDto)
	{
		Log.debug("Request received in service to update the job post details with id {}",editJobPostsDto.getJobId());
		String jobDescriptionPathFilePath;
		
		//if file is not uploaded
		if(!editJobPostsDto.getJobDescriptionUpdated())
		{
			Log.debug("Description document is not uploaded by the employer");
			jobDescriptionPathFilePath=editJobPostDao.fetchDescriptionDocumentPath(editJobPostsDto.getId());
			if(Objects.isNull(jobDescriptionPathFilePath))
			{
				Log.debug("The job post with Id {} does not have a job decsription document",editJobPostsDto.getJobId());
			}
			else if(jobDescriptionPathFilePath.equalsIgnoreCase(ReadApplicationConstants.getExceptionText()))
			{
				Log.debug("An Exception occured while fetching the job description path for job post with Id {}, returning -25 to the controller",editJobPostsDto.getJobId());
				return -25;
			}
			
			Log.debug("The job description path is fetched is {} ",jobDescriptionPathFilePath);
			Log.debug("Sending request to update the job post details for job id {}",editJobPostsDto.getJobId());
			return editJobPostDao.updateJobPostDetails(editJobPostsDto, jobDescriptionPathFilePath);
		}
		
		//if file is deleted
		else if(editJobPostsDto.getJobDescriptionUpdated() && (Objects.isNull(editJobPostsDto.getDescriptionDocumentFile()) || editJobPostsDto.getDescriptionDocumentFile().isEmpty()) ) {
			
			//fetch the path of existing job description document
			String jobDescriptionDocumentPath=editJobPostDao.fetchDescriptionDocumentPath(editJobPostsDto.getId());
			if(jobDescriptionDocumentPath.equalsIgnoreCase(ReadApplicationConstants.getExceptionText()))
			{
				Log.debug("An Exception occured while fetching the job description path for job post with Id {}, returning -25 to the controller",editJobPostsDto.getJobId());
				return -25;
			}
			//Delete existing job description document
			jobDescriptionPathFilePath=null;
			int jobDescriptionDeleted=editJobPostDao.updateJobPostDetails(editJobPostsDto, jobDescriptionPathFilePath);
			if(jobDescriptionDeleted == 1) 
			{
				try 
				{
					Log.debug("The existing job description document was deleted by the employer");
					Log.debug("In try block to delete the job description document from s3 bucket stored at path {}",jobDescriptionDocumentPath);
					fileUtility.deleteFilesFromS3Bucket(jobDescriptionDocumentPath);
					return jobDescriptionDeleted;
				} 
				catch (Exception e)
				{
					Log.error("An exception occured while deleting the existing job description document {}",e);
					return -25;
				}
			}
			else 
			{
				Log.debug("An exception occurred while updating the job post details, returning -25 to the controller");
				return jobDescriptionDeleted;
			}
		}
		
		//else file is uploaded
		else
		{
			Log.debug("The employer has uploaded the job description document, sending request to upload the file on S3 bucket");
			try
			{
				File jobDescriptionDocument = fileUtility.convertMultiPartToFile(editJobPostsDto.getDescriptionDocumentFile());
				Log.debug("Converted multipart file into file object, renaming the file and uploading on S3 bucket");
				jobDescriptionPathFilePath = ReadApplicationConstants.getJobPostingsFolderPath()+editJobPostsDto.getJobId()+ReadApplicationConstants.getFileDelimeter()+editJobPostsDto.getJobId()+ReadApplicationConstants.getSpaceReplacementChar()+ReadApplicationConstants.getDescriptionText();
				Log.debug("The path for saving job description document for job id {} is {}",editJobPostsDto.getJobId(), jobDescriptionPathFilePath);
				fileUtility.uploadFileTos3bucket(jobDescriptionPathFilePath, jobDescriptionDocument);
				jobDescriptionDocument.delete();
				Log.debug("File successfilly uploaded on S3 bucket, sending request to dao to update the job post details for job id {}",editJobPostsDto.getJobId());
				return editJobPostDao.updateJobPostDetails(editJobPostsDto, jobDescriptionPathFilePath);
			}
			catch(Exception e)
			{
				Log.error("An exception occurred while updating the job post details {}",e);
				return -25;
			}
		}
	}
}
