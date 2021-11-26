package com.cgsc.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgsc.common.PasswordUtils;
import com.cgsc.common.ReadApplicationConstants;
import com.cgsc.common.ReadProfileProperties;

@Service
public class GenerateVerificationLinkUtility  
{

	@Autowired
	private ReadProfileProperties profileProperties;
	
	private static final Logger Log = LoggerFactory.getLogger(GenerateVerificationLinkUtility.class);

	public String generateVerificationLink(Integer id)
	{
		Log.debug("Request received in utility method to generate the verification link for user with id {}",id);
		String encryptedKey = PasswordUtils.encrypt(ReadApplicationConstants.getEncryptionKey(), id.toString());
		Log.debug("The encrypted key is {}",encryptedKey);
		String userVerificationLink = profileProperties.getVerifyAccountFqdn()+"/verifyUserAccount/"+encryptedKey;
		return ReadApplicationConstants.getVerificationLinkSMSBody()+userVerificationLink;
	}
}
