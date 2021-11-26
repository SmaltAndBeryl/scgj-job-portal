package com.cgsc.service;

import java.io.File;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;

import com.cgsc.common.ReadApplicationConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cgsc.dao.CandidateRegistrationDao;
import com.cgsc.dao.LoginDao;
import com.cgsc.dto.RegisterCandidateDto;
import com.cgsc.utility.FileUtility;
import com.cgsc.utility.GenerateFilePathUtility;

@Service
public class CandidateRegistrationService 
{

	@Autowired
	private CandidateRegistrationDao registerCandidateDao;
	@Autowired
	private FileUtility fileUtility;
	@Autowired
	private LoginDao loginDao;
	
	@Autowired
	private GenerateFilePathUtility generateFilePathUtility;
	
	private static final Logger Log = LoggerFactory.getLogger(CandidateRegistrationService.class);
	
	
	/**
	 * @author Prateek Kapoor
	 * @since 07-10-2020
	 * This method sends request to DAO to generate candidate credentials and then uploads the resume/certificates uploaded by the candidate
	 * Once the certificates/resume are uploaded, the method sends request to dao to insert the candidate details into database
	 * @param registerCandidateDto
	 * @return 1 if success;
	 * -25 in case of exception;
	 * -55 in case of duplicate mobile number;
	 * @throws Exception
	 *
	 * @updatedBy Sarthak Bhutani
	 * @updatedOn 01-12-2020
	 * @update - to calculate the age via dob & send it as a param to dao
	 */
	@Transactional(rollbackFor=Exception.class)
	public int generateCandidateCredentials(RegisterCandidateDto registerCandidateDto) throws Exception
	{
		Log.debug("Request received in service to generate the candidate credentials for candidate with mobile number {}",registerCandidateDto.getMobileNumber());
		
		try 
		{
			Log.debug("Sending request to check if the mobile number - {} exists in the database",registerCandidateDto.getMobileNumber());
			int mobileNumberExistence = loginDao.checkMobileNumberExistence(registerCandidateDto.getMobileNumber());
			if(mobileNumberExistence==1)
			{
				Log.debug("Mobile number - {} already exists in the database",registerCandidateDto.getMobileNumber());
				Log.debug("Returning -55 to the controller");
				return -55;
			}
			else if(mobileNumberExistence==-25)
			{
				Log.error("Could not fetch the mobile number existence in the database for {}",registerCandidateDto.getMobileNumber());
				Log.error("Returning -25 to controller");
				return -25;
			}
			Log.debug("Mobile number - {} does not exists in the database, processing request to generate credentials for the candidate",registerCandidateDto.getMobileNumber());
			Log.debug("Finding out candidate's age via the entered date of birth");
			DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(ReadApplicationConstants.getDateFormatyyyyMMdd(), Locale.ENGLISH);
			LocalDate dateOfBirth = LocalDate.parse(registerCandidateDto.getDob(), dateFormat);
			Period period = Period.between(dateOfBirth,LocalDate.now());
			int age = period.getYears();
			Log.debug("Candidate's age is calculated : {}",age);
			Log.debug("Converting the resume multipart file to file object");
			File candidateResume = fileUtility.convertMultiPartToFile(registerCandidateDto.getResume());
			File candidateCertificates = null;

			if(Objects.isNull(registerCandidateDto.getCertificates())||registerCandidateDto.getCertificates().isEmpty())
			{
				Log.debug("Candidate has not uploaded the certificates");
			}
			else
			{
				Log.debug("Converting the candidate certificates to file object");
				candidateCertificates = fileUtility.convertMultiPartToFile(registerCandidateDto.getCertificates());
			}
			int candidateId = registerCandidateDao.generateCandidateCredentials(registerCandidateDto);
			if(candidateId==-25)
			{
				Log.error("Could not generate candidate credentials, returning -25 to controller");
				return -25;
			}
			else if(candidateId==-55)
			{
				Log.error("Could not generate candidate credentials, returning -25 to controller");
				return -55;
			}
			Log.debug("Converted the multipart files into file objects, Sending request to generate candidate credentials by inserting into users table");
			Log.debug("The generated id for the candidate with mobile number {} is {}",registerCandidateDto.getMobileNumber(),candidateId);
			Log.debug("Sending request to upload the resume and certificates on S3 bucket");
			String candidateCertificateFilePath;
			String candidateResumeFilePath = generateFilePathUtility.generatePathForResume(candidateId, registerCandidateDto.getCandidateName());
			Log.debug("Sending request to upload resume to S3 bucket");
			fileUtility.uploadFileTos3bucket(candidateResumeFilePath, candidateResume);
			candidateResume.delete();
			Log.debug("Candidate resume uploaded on S3 bucket at path {}",candidateResumeFilePath);
			
			if(Objects.isNull(candidateCertificates))
			{
				Log.debug("The candidate has not uploaded the certificates, setting the certificate path as null");
				candidateCertificateFilePath=null;
			}
			else
			{
				Log.debug("Candidate has uploaded the certificate, sending request to upload certificates on S3 bucket");
				candidateCertificateFilePath = generateFilePathUtility.generatePathForCertificates(candidateId, registerCandidateDto.getCandidateName());
				Log.debug("Sending request to upload certificate to S3 bucket");
				fileUtility.uploadFileTos3bucket(candidateCertificateFilePath, candidateCertificates);
				candidateCertificates.delete();
				Log.debug("Candidate certificate uploaded on S3 bucket at path {}",candidateCertificateFilePath);
			}
			
			Log.debug("Files uploaded by candidate uploaded on S3 bucket, sending request to insert the candidate details into database");
			return registerCandidateDao.insertCandidateDetails(registerCandidateDto, candidateId, age, candidateResumeFilePath, candidateCertificateFilePath);
		} 
		catch (Exception e) 
		{
			Log.error("An exception occurred while generating candidate credentials - " +e);
			throw new Exception(e);
		}
		
	}
}
