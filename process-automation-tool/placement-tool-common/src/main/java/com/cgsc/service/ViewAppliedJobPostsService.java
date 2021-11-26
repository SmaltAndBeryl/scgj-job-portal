package com.cgsc.service;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgsc.dao.ViewAppliedJobsDao;
import com.cgsc.dto.ViewAppliedJobsDto;
import com.cgsc.dto.ViewJobPostDto;
import com.cgsc.utility.GetLoggedInUserDetailsUtility;

@Service
public class ViewAppliedJobPostsService {

	private static final Logger Log = LoggerFactory.getLogger(ViewAppliedJobPostsService.class);
	
	@Autowired
	private ViewAppliedJobsDao viewAppliedJobPostsDao;

	/**
	 * @author Jyoti Singh
	 * @since 16-10-2020
	 * The method fetches the details of jobs in which the logged-in candidate has applied
	 * @return Collection of {@link ViewJobPostDto} if success; else returns null in case of exception
	 */
	public Collection<ViewAppliedJobsDto> viewAppliedJobs() {
		Log.debug("Request recieved in service to fetch Job Posts in which the logged in candidate has applied");
		Log.debug("Fetching userId of logged-in candidate from the session");
		int candidateId = GetLoggedInUserDetailsUtility.getUserIdFromSession(); 
		Log.debug("User id of logged in candidate is - {}",candidateId);
		Log.debug("Sending request to dao method");
		return viewAppliedJobPostsDao.viewAppliedJobs(candidateId);
	}
}
