package com.cgsc.service;

import java.util.Collection;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.cgsc.dao.FetchCandidateDetailsDto;
import com.cgsc.dao.SendVerificationLinkDao;
import com.cgsc.utility.FetchCandidateDetailsUtility;
import com.cgsc.utility.GenerateVerificationLinkUtility;
import com.cgsc.utility.SendOtpUtility;


@Service
@PropertySources(value = { 
		@PropertySource("classpath:applicationOverride.properties")
		})
public class SendVerificationLinkService 
{

	private static final Logger Log = LoggerFactory.getLogger(SendVerificationLinkService.class);
	
	@Autowired
	private FetchCandidateDetailsUtility fetchCandidateDetailsUtility;
	
	@Autowired
	private SendVerificationLinkDao sendVerficationLinkDao;
	
	@Autowired
	private SendOtpUtility sendOtpUtility;
	
	@Autowired
	private GenerateVerificationLinkUtility generateVerificationLink;
	
	
	/**
	 * @author Prateek Kapoor
	 * @since 22-10-2020
	 * This method polls the mobile number of all the active candidates with a verification link and increments the poll count by 1
	 * @Schedule - 1st day of every month at 00:01 minute
	 */
	@Scheduled(cron = "${polling.frequency}")
	public void sendVerificationLink()
	{
		Log.debug("Initiating the process to send verification link to all the active candidates");
		Collection<FetchCandidateDetailsDto> candidateDetails = fetchCandidateDetailsUtility.fetchCandidateDetails();
		if(Objects.isNull(candidateDetails) || candidateDetails.isEmpty())
		{
			Log.error("No active candidates present in the database, verification link will not be sent");
			return;
		}
		else
		{
			Log.debug("Received collection of active candidates, sending request to update candidate poll status");
			int updatePollStatus = sendVerficationLinkDao.updateCandidatePollStatus(candidateDetails);
			if(updatePollStatus==-25)
			{
				Log.error("Could not update candidate poll status, sms to candidates will not be sent");
				return ;
			}
			else
			{
				Log.debug("Candidate poll status updated, sending request to generate verification link message for each candidate id");
				for(FetchCandidateDetailsDto candidateInformation : candidateDetails)
				{
					Log.debug("Generating verification link along with message for candidate ID {}",candidateInformation.getId());
					String verificationLinkMessage = generateVerificationLink.generateVerificationLink(candidateInformation.getId());
					Log.debug("Verification link message generated, sending the message to mobile number {}",candidateInformation.getMobileNumber());
					sendOtpUtility.sendOTP(verificationLinkMessage, candidateInformation.getMobileNumber());
				}
			}
		}
	}
}
