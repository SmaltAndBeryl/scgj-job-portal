package com.cgsc.service;

import java.io.File;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cgsc.common.ReadApplicationConstants;
import com.cgsc.dao.CandidateProfileDao;
import com.cgsc.dto.CandidateProfileDto;
import com.cgsc.utility.FileUtility;
import com.cgsc.utility.GenerateFilePathUtility;
import com.cgsc.utility.GetLoggedInUserDetailsUtility;

@Service
public class CandidateProfileService 
{

	@Autowired
	private CandidateProfileDao candidateProfileDao;
	
	@Autowired
	private FileUtility fileUtility;
	
	@Autowired
	private GenerateFilePathUtility generateFilePathUtility;
	
	private static final Logger Log = LoggerFactory.getLogger(CandidateProfileService.class);
	
	/**
	 * @author Prateek Kapoor
	 * @since 27-10-2020
	 * This method fetches the candidate details for the logged-in candidate
	 * @return {@link CandidateProfileDto} if success;
	 * Else returns null
	 */
	public CandidateProfileDto viewCandidateProfileDetails()
	{
		Log.debug("Request received in service to fetch the candidate details for logged in candidate");
		Integer userId = GetLoggedInUserDetailsUtility.getUserIdFromSession();
		Log.debug("The user id retrieved from session is {}",userId);
		Log.debug("Sending request to dao to fetch the profile details for user id {}",userId);
		return candidateProfileDao.viewCandidateProfileDetails(userId);
	}
	
	/**
	 * @author Prateek Kapoor
	 * @since 27-10-2020
	 * This method updates the candidate details, uploads new resume/certificates if uploaded by the candidates
	 * @param candidateProfileDto
	 * @return 1 if success;
	 * else throws and exception and rollsback the transaction
	 * @throws Exception 
	 *
	 * @updatedBy Sarthak Bhutani
	 * @updatedOn 01-12-2020
	 * @update - to calculate the age via dob & send it as a param to dao
	 */
	@Transactional(rollbackFor=Exception.class)
	public int updateCandidateDetails(CandidateProfileDto candidateProfileDto) throws Exception
	{
		Log.debug("Request received in service to update the candidate details for user with id {}",candidateProfileDto.getUserId());
		Log.debug("Sending request to dao to fetch the candidate document path");
		String resumePath = null;
		String certificatesPath= null;
		boolean certificateDeleteFlag = false;
		try
		{
			Log.debug("Finding out candidate's age via the entered date of birth");
			DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(ReadApplicationConstants.getDateFormatyyyyMMdd(), Locale.ENGLISH);
			LocalDate dateOfBirth = LocalDate.parse(candidateProfileDto.getDob(), dateFormat);
			Period period = Period.between(dateOfBirth,LocalDate.now());
			int age = period.getYears();
			Log.debug("Candidate's age is calculated : {}",age);
			CandidateProfileDto documentPath = candidateProfileDao.fetchCandidateDocuments(candidateProfileDto.getUserId());
			if(Objects.isNull(documentPath))
			{
				Log.error("Could not fetch the document path for user id {}",candidateProfileDto.getUserId());
				Log.error("Returning -25 to controller");
				return -25;
			}
			Log.debug("Fetched the candidate documents from database for user id {}",candidateProfileDto.getUserId());
			if(Objects.isNull(candidateProfileDto.getCandidateResume()) || candidateProfileDto.getCandidateResume().isEmpty())
			{
				Log.debug("The user has not updated the resume, resume will not be updated");
				resumePath = documentPath.getResumePath();
			}
			else
			{
				Log.debug("Candidate has updated the resume, sending request to convert the multipart file into file object");
				File resumeFile = fileUtility.convertMultiPartToFile(candidateProfileDto.getCandidateResume());
				Log.debug("Resume multipart file object converted into file object. Sending request to upload the file on S3 bucket");
				resumePath = generateFilePathUtility.generatePathForResume(candidateProfileDto.getUserId(), candidateProfileDto.getCandidateName());
				fileUtility.uploadFileTos3bucket(resumePath, resumeFile);
				resumeFile.delete();
				Log.debug("Resume successfully updated on S3 bucket at path - {}",resumePath);
			}
			if((Objects.isNull(candidateProfileDto.getCandidateCertificates()) || candidateProfileDto.getCandidateCertificates().isEmpty()) && !(candidateProfileDto.getCertificateDeleteFlg().equalsIgnoreCase(ReadApplicationConstants.getTrueFlag())))
			{
				Log.debug("The candidate has not updated the certificate");
				certificatesPath = documentPath.getCertificatesPath();
			}
			else if(!(Objects.isNull(candidateProfileDto.getCandidateCertificates()) || candidateProfileDto.getCandidateCertificates().isEmpty()) && !(candidateProfileDto.getCertificateDeleteFlg().equalsIgnoreCase(ReadApplicationConstants.getTrueFlag())))
			{
				Log.debug("The candidate has updated the certificate, sending request to convert certificate multipart file to file object");
				File certificateFile = fileUtility.convertMultiPartToFile(candidateProfileDto.getCandidateCertificates());
				Log.debug("Certificate multipart file converted into file object, sending request to upload the certificates on S3 bucket");
				certificatesPath = generateFilePathUtility.generatePathForCertificates(candidateProfileDto.getUserId(), candidateProfileDto.getCandidateName());
				fileUtility.uploadFileTos3bucket(certificatesPath, certificateFile);
				certificateFile.delete();
				Log.debug("Certificate successfully updated on S3 bucket at path {}",certificatesPath);
			}
			else if(candidateProfileDto.getCertificateDeleteFlg().equalsIgnoreCase(ReadApplicationConstants.getTrueFlag()) && (Objects.isNull(candidateProfileDto.getCandidateCertificates()) || candidateProfileDto.getCandidateCertificates().isEmpty()))
			{
				Log.debug("Candidate has deleted the certificates, setting the certificate path as null");
				Log.debug("Certificate will be deleted once the DB transaction is complete");
				certificatesPath=null;
				certificateDeleteFlag=true;
			}
			
			Log.debug("Sending request to update the candidate information stored in users table");
			candidateProfileDao.updateUserTable(candidateProfileDto);
			Log.debug("Candidate details stored in users table updated. Sending request to update the details stored in candidates table");
			candidateProfileDao.updateCandidateDetails(candidateProfileDto,age,resumePath, certificatesPath);
			Log.debug("Candidate details stored in candidates table successfully updated");
			if(certificateDeleteFlag)
			{
				Log.debug("Certificate was deleted by the candidate, sending request to delete the certificate stored at path {}",documentPath.getCertificatesPath());
				fileUtility.deleteFilesFromS3Bucket(documentPath.getCertificatesPath());
				Log.debug("Certificates successfully deleted from S3 bucket, returning 1 to controller");
			}
			return 1;
		}
		catch(DuplicateKeyException duplicateKey)
		{
			Log.error("The aadhaar number of the candidate already exists on the platform");
			throw new DuplicateKeyException("Duplicate Key Exception", duplicateKey);
		}
		catch(Exception e)
		{
			Log.error("An exception occurred while updating the candidate details "+e);
			Log.error("Returning -25 to controller");
			throw new Exception(e);
		}
	}
}