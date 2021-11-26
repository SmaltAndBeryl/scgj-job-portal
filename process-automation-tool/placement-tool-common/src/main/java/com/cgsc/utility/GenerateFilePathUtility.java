package com.cgsc.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.cgsc.common.ReadApplicationConstants;

@Component
public class GenerateFilePathUtility 
{

	private static final Logger Log = LoggerFactory.getLogger(GenerateFilePathUtility.class);
	
	/**
	 * @author Prateek Kapoor
	 * @since 27-10-2020
	 * This method returns the path at which the certificates for the candidate will be stored
	 * @param userId
	 * @param candidateName
	 * @return path where candidate certificate will be stored
	 */
	public String generatePathForCertificates(int userId, String candidateName)
	{
		Log.debug("Request received in utility method to generate path at which certificates would be uploaded for user with id {}",userId);
		return ReadApplicationConstants.getCandidateDocumentsFolder()+userId+ReadApplicationConstants.getSpaceReplacementChar()
		 +candidateName+ReadApplicationConstants.getFileDelimeter()+ userId+ReadApplicationConstants.getSpaceReplacementChar()+candidateName+
		 ReadApplicationConstants.getSpaceReplacementChar()+ReadApplicationConstants.getCertificateText();
	}
	
	/**
	 * @author Prateek Kapoor
	 * @since 27-10-2020
	 * This method returns the path at which the resume for the candidate will be stored
	 * @param userId
	 * @param candidateName
	 * @return path where candidate resume will be stored
	 */
	public String generatePathForResume(int userId, String candidateName)
	{
		Log.debug("Request received in utility method to generate path at which resume would be uploaded for user with id {}",userId);
		return ReadApplicationConstants.getCandidateDocumentsFolder()+userId+ReadApplicationConstants.getSpaceReplacementChar()
		 +candidateName+ReadApplicationConstants.getFileDelimeter()+ userId+ReadApplicationConstants.getSpaceReplacementChar()+candidateName+
		 ReadApplicationConstants.getSpaceReplacementChar()+ReadApplicationConstants.getResumeText();
	}
}
