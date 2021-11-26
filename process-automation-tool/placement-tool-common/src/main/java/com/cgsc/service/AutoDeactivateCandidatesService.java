package com.cgsc.service;


import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.cgsc.common.ReadApplicationConstants;
import com.cgsc.dao.AutoDeactivateCandidatesDao;

@Service
@PropertySources(value = { 
		@PropertySource("classpath:applicationOverride.properties")
		})
public class AutoDeactivateCandidatesService {
	
	private static final Logger Log = LoggerFactory.getLogger(AutoDeactivateCandidatesService.class);
	
	@Autowired
	private AutoDeactivateCandidatesDao autoDeactivateCandidatesDao;
	
	/**
	 * @author Jyoti Singh
	 * @since 17-12-2020
	 * This method deactivates the candidates with poll count greater than 2 and account status = active ('Y')
	 * @Schedule - 15th day of every month at 00:01 minute
	 */
	@Scheduled(cron = "${deactivateCandidates.polling.frequency}")
	public void autoDeactivateCandidates()
	{
		Log.debug("Executing the scheduled method to deactivate candidates with poll count greater than - {} and whose account status is active",ReadApplicationConstants.getMaxPollCount());
		List<Integer> candidateId = autoDeactivateCandidatesDao.fetchInactiveCandidates();
		if(Objects.isNull(candidateId)) {
			Log.error("An exception occured while fetching the list of user Id of inactive candidates, inactive candidates could not be deactivated");
			return;
		}
		else if(candidateId.isEmpty()) {
			Log.debug("The user id list of inactive candidates received is empty, No inactive candidates available");
			return;
		}
		Log.debug("The number of inactive candidates received are {}",candidateId.size());
		Log.debug("Sending request to dao method to deactivate the inactive candidates");
		autoDeactivateCandidatesDao.deactivateCandidateStatus(candidateId);	
	}
}
