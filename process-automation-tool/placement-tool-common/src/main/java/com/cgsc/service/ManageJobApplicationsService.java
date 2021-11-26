package com.cgsc.service;

import java.io.File;
import java.util.Collection;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cgsc.common.ReadApplicationConstants;
import com.cgsc.dao.ManageJobApplicationsDao;
import com.cgsc.dto.ManageJobApplicationsDto;
import com.cgsc.dto.ViewJobApplicationsDto;
import com.cgsc.utility.ConvertBase64StringToFileUtility;
import com.cgsc.utility.FileUtility;
import com.cgsc.utility.GetJobIdUtility;

@Service
public class ManageJobApplicationsService 
{

	private static final Logger Log = LoggerFactory.getLogger(ManageJobApplicationsService.class);
	
	@Autowired
	private ManageJobApplicationsDao manageJobApplicationStatusDao;
	
	@Autowired 
	private ConvertBase64StringToFileUtility convertBase64StringToFileUtility;
	
	@Autowired
	private GetJobIdUtility getJobIdUtility;
	
	@Autowired
	private FileUtility fileUtility;
	
	/**
	 * @author Prateek Kapoor
	 * @since 16-10-2020
	 * This method fetches the collection of candidate details who applied for a specific jobId
	 * @param jobId
	 * @return Collection of {@link ViewJobApplicationsDto} if success; else returns null
	 */
	public Collection<ViewJobApplicationsDto> viewJobApplications(int jobId)
	{
		Log.debug("Request received in service to view list of applications for job with id {}",jobId);
		return manageJobApplicationStatusDao.viewJobApplications(jobId);
	}
	
	/**
	 * @author Prateek Kapoor
	 * @since 16-10-2020
	 * This method updates the application status of candidates for a job id
	 * 1. If updated application status is hired, 
	 *      a. checks if the job post has vacancy
	 *      b. checks the salary offered to the candidate is greater than or equal to the minimum salary offered in the job post
	 *      c. uploads offer letter on s3 bucket
	 * 
	 * @param manageJobApplicationsDto
	 * @return 1 if success; 
	 * -25 in case of exception; 
	 * -88 invalid status
	 * -11 if there is no vacancy and no more candidates can be hired
	 * -61 if the salary offered to candidate is less than the minimum salary for the job posted
	 * 
	 * @updated by - Jyoti Singh
	 * @updated on - 02-12-2020
	 * @update - 
	 * 1. If candidate is hired, 
	 *      a. check if the job post has vacancy
	 *      b. check the salary offered to the candidate is greater than or equal to the minimum salary offered in the job post
	 *      c. upload offer letter on s3 bucket
	 *
	 * @author Sarthak Bhutani
	 * @updatedOn 07-01-2020
	 * @update saving offer letter file as pdf instead of image(jpg, jpeg, png) in S3 bucket
	 */
	@Transactional(rollbackFor=Exception.class)
	public int updateCandidateApplicationStatus(ManageJobApplicationsDto manageJobApplicationsDto)
	{
		Log.debug("Request received in service to update the job application status for candidates for jobId {} to {}",manageJobApplicationsDto.getJobId(),manageJobApplicationsDto.getUpdatedStatus());
		if(!(manageJobApplicationsDto.getUpdatedStatus().equalsIgnoreCase(ReadApplicationConstants.getApplicationStateInReview()) || manageJobApplicationsDto.getUpdatedStatus().equalsIgnoreCase(ReadApplicationConstants.getApplicationStateRejected()) || manageJobApplicationsDto.getUpdatedStatus().equalsIgnoreCase(ReadApplicationConstants.getApplicationStateShortlisted()) || manageJobApplicationsDto.getUpdatedStatus().equalsIgnoreCase(ReadApplicationConstants.getApplicationStateHired())))
		{
			Log.error("Invalid status received from front end - {}",manageJobApplicationsDto.getUpdatedStatus());
			Log.error("Returning -88 to controller");
			return -88;
		}
		
		try
		{
			String offerLetterPath = null;
			if(manageJobApplicationsDto.getUpdatedStatus().equalsIgnoreCase(ReadApplicationConstants.getApplicationStateHired())) {
				//check for vacancy
				Log.debug("Sending request to dao method to fetch the vacancy remaining for the job post with Id - {}",manageJobApplicationsDto.getJobId());
				int remainingVacancy = manageJobApplicationStatusDao.checkRemainingVacancy(manageJobApplicationsDto.getJobId());
				Log.debug("The remaining vacancy for job post with Id - {} is {}",manageJobApplicationsDto.getJobId(), remainingVacancy);

				if (remainingVacancy == 0) {
					Log.debug("The remaining vacancy for job post with id -{} is {}, returning -11 to controller",manageJobApplicationsDto.getJobId(),remainingVacancy);
					return -11;
				}
				else if(remainingVacancy == -11) {
					Log.debug("An exception occured while fetching the remaining vacancy, returning -25 to controller");
					return -25;
				}
				
				//check for salary greater or equal to minimum salary offered
				Log.debug("Sending request to dao method to fetch the minimum salary offered for the job post with Id - {}",manageJobApplicationsDto.getJobId());
				int minimumSalary = manageJobApplicationStatusDao.fetchMinimumSalaryForJobPost(manageJobApplicationsDto.getJobId());
				Log.debug("Minimum salary for job post with id -{} is {}",manageJobApplicationsDto.getJobId(), minimumSalary);
				if(minimumSalary == -25) {
					Log.debug("An exception occured while fetching the minimum salary for job post, returning -25 to controller");
					return -25;
				}
				else if(manageJobApplicationsDto.getSalary()<minimumSalary) {
					Log.debug("The salary offered is less than the minimum salary of the job post, returning -61 to the controller");
					return -61;
				}
				
				//Upload offer letter on s3 bucket
				if(manageJobApplicationsDto.getUpdatedStatus().equalsIgnoreCase(ReadApplicationConstants.getApplicationStateHired())) {
					Log.debug("The updated status received from front end is - {}",manageJobApplicationsDto.getUpdatedStatus());
					Log.debug("Creating Offer letter file object from the base 64 encoded pdf string received");
					String fileName = manageJobApplicationsDto.getJobId()+ReadApplicationConstants.getSpaceReplacementChar()+manageJobApplicationsDto.getCandidateId()+ReadApplicationConstants.getSpaceReplacementChar()+ReadApplicationConstants.getOfferLetterPdfName();
					File offerLetter = convertBase64StringToFileUtility.convertBase64ToFile(manageJobApplicationsDto.getOfferLetter(), fileName);
					String jobId = getJobIdUtility.fetchJobId(manageJobApplicationsDto.getJobId());
					Log.debug("The job Id (string) received is {}",jobId);
					if(!Objects.isNull(jobId)) {
						offerLetterPath = ReadApplicationConstants.getJobPostingsFolderPath()+jobId+ReadApplicationConstants.getFileDelimeter()+ReadApplicationConstants.getHiringsFolderName()+
								ReadApplicationConstants.getFileDelimeter()+ReadApplicationConstants.getOfferLetterFolderName()+ReadApplicationConstants.getFileDelimeter()+
								manageJobApplicationsDto.getCandidateId().get(0)+ReadApplicationConstants.getSpaceReplacementChar()+ReadApplicationConstants.getOfferLetterPdfName();
						fileUtility.uploadPublicFileTos3bucket(offerLetterPath, offerLetter);
						offerLetter.delete();
					}
					else {
						Log.debug("An exception occured while fetching job Id, returning -25 to controller");
						return -25;
					}
				}
			}
				Log.debug("Sending request to dao to update the application status of candidates for job with id {}",manageJobApplicationsDto.getJobId());
			return manageJobApplicationStatusDao.updateCandidateApplicationStatus(manageJobApplicationsDto,offerLetterPath);
		}
		catch(Exception e) {
			Log.error("An exception was caught while updating the candidate application status - "+e);
			Log.error("Returning -25 to controller");
			return -25;
		}
		
	}
	
}
