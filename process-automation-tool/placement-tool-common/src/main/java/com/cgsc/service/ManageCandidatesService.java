package com.cgsc.service;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgsc.dao.ManageCandidatesDao;
import com.cgsc.dto.CandidateDetailsDto;

@Service
public class ManageCandidatesService 
{

	private static final Logger Log = LoggerFactory.getLogger(ManageCandidatesService.class);
	
	@Autowired
	private ManageCandidatesDao manageCandidatesDao;
	
	/**
	 * @author Prateek Kapoor
	 * @since 29-10-2020
	 * This method fetches the collection of all the candidates whose poll count is greater than 2
	 * @return Collection of {@link CandidateDetailsDto} if success; else returns null
	 */
	public Collection<CandidateDetailsDto> viewInactiveCandidates()
	{
		Log.debug("Request received in service to fetch the list of candidates whose poll count > 2");
		return manageCandidatesDao.viewInactiveCandidates();
	}
}
