package com.cgsc.controller;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cgsc.common.Privilege;
import com.cgsc.dto.CandidateDetailsDto;
import com.cgsc.service.ManageCandidatesService;

@RestController
public class ManageCandidatesController 
{
	private static final Logger Log = LoggerFactory.getLogger(ManageCandidatesController.class);
	
	@Autowired
	private ManageCandidatesService manageCandidatesService;
	
	
	/**
	 * @author Prateek Kapoor
	 * @since 29-10-2020
	 * This method fetches the collection of all the candidates whose poll count is greater than 2
	 * @return Collection of {@link CandidateDetailsDto} if success; else returns null
	 */
	@Privilege(value= {"Admin"})
	@GetMapping("/getInactiveCandidates")
	public Collection<CandidateDetailsDto> viewInactiveCandidates()
	{
		Log.debug("Request received in controller to fetch the list of candidates whose poll count > 2");
		return manageCandidatesService.viewInactiveCandidates();
	}
}
