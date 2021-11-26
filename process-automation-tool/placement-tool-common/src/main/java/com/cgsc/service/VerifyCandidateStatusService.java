package com.cgsc.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgsc.common.PasswordUtils;
import com.cgsc.common.ReadApplicationConstants;
import com.cgsc.dao.VerifyCandidateStatusDao;

@Service
public class VerifyCandidateStatusService {
	private static final Logger Log = LoggerFactory.getLogger(VerifyCandidateStatusService.class);
	
	@Autowired
	private VerifyCandidateStatusDao verifyCandidateStatusDao;
	
	
	/**
	 * @author Jyoti Singh
	 * @since 21-10-2020
	 * 
	 * This method receives the encrypted candidateId to update the poll count of candidate
	 * returns 1 on success, -11 in case the candidate poll count is already 0, else -25 in case of exception
	 * 
	 * @param encrypted candidateId
	 */
	public int updatePollCount(String encryptedCandidateId) {
		
		Log.debug("Request received in service to update the poll count of candidate with encrypted candidate Id - {}", encryptedCandidateId);
		Log.debug("Decrypting the candidate Id");
		
		String decryptedValue = PasswordUtils.decrypt(ReadApplicationConstants.getEncryptionKey(), encryptedCandidateId);
		Integer decryptedCandidateId = -1;	
		try {
			Log.debug("Converting the decrypted candidate Id from string to integer type");
			decryptedCandidateId = Integer.parseInt(decryptedValue);	
		}
		catch(Exception e) {
			Log.debug("An exception occured while converting the candidate Id from string to integer {}",e);
			return -25;
		}
		Log.debug("The decrypted candidate Id is - {}",decryptedCandidateId);
		Log.debug("Sending request to dao method to check candidate's existing polling count");
		
		int pollCount = verifyCandidateStatusDao.checkCandidatePollCount(decryptedCandidateId);
		Log.debug("Candidate poll count is - {}",pollCount);
		if(pollCount > 0) {
			Log.debug("Sending request to dao method to update the poll count of candidate with id -{}", decryptedCandidateId);
			return verifyCandidateStatusDao.updatePollCount(decryptedCandidateId);
		}
		if(pollCount == 0) {
			Log.debug("Candidate's poll count is already 0, returning -11 to controller");
			return -11;
		}
		else {
			Log.debug("An exception occured while fetching the poll count for candidate with id - {}",decryptedCandidateId);
			return -25;
		}
	}
}
