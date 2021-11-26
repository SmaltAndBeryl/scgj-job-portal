package com.cgsc.service;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgsc.dao.EmployerViewOfferLetterDao;
import com.cgsc.dto.HiredCandidateDetailsDto;

@Service
public class EmployerViewOfferLetterService {

	private static final Logger Log = LoggerFactory.getLogger(EmployerViewOfferLetterService.class);
	
	@Autowired
	private EmployerViewOfferLetterDao employerViewOfferLetterDao;
	
	/**
	 * @author Jyoti Singh
	 * @since 23-12-2020
	 * This method fetches the collection of candidate details who were hired for the job post with given id
	 * @param id (int)
	 * @return Collection of {@link HiredCandidateDetailsDto} if success; else returns null
	 */
	public Collection<HiredCandidateDetailsDto> viewHiredCandidates(int jobId)
	{
		Log.debug("Request received in service to fetch candidate details who were hired for the job post with id - {}",jobId);
		return employerViewOfferLetterDao.viewHiredCandidates(jobId);
	}
}
