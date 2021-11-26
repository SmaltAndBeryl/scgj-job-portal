package com.cgsc.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgsc.dao.CloseJobPostDao;

/**
 * @author Sarthak bhutani
 * @since 14-10-2020
 * @param jobId
 * @return 1 if success, -25 if exception
 * Method for closing a job post by an employer based on id
 */
@Service
public class CloseJobPostService {

	private static final Logger Log = LoggerFactory.getLogger(CloseJobPostService.class);
	
	@Autowired
	CloseJobPostDao closeJobPostDao;
	
	public int closeJobPost(int jobId) {
		Log.debug("Request recieved in service for closing the job post with id: {}",jobId);
		return closeJobPostDao.closeJobPost(jobId);
	}
}
