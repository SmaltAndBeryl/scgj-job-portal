package com.cgsc.service;

import java.util.Collection;

import com.cgsc.common.ReadApplicationConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgsc.dao.ViewJobPostDao;
import com.cgsc.dto.GetAllJobPostByEmployerDto;
import com.cgsc.dto.ViewJobPostDto;
import com.cgsc.dto.ViewPublishedJobPostDto;
import com.cgsc.utility.GetLoggedInUserDetailsUtility;

@Service
public class ViewJobPostService {

	@Autowired
	private ViewJobPostDao viewJobPostDao;
	

	private static final Logger Log = LoggerFactory.getLogger(ViewJobPostService.class);
	
	/**
	 * @author Sarthak Bhutani
	 * @since 12-10-2020
	 * this method returns the collection of all job posts which are published & are active & whose application date is greater than equal to current date
	 * @return Collection of {@link ViewJobPostDto} if success; else returns null
	 */
	public Collection<ViewJobPostDto> viewJobPost(){
		Log.debug("Request recieved in service to fetch Job Posts which are Published & are active & whose application date is greater than equal to current date");
		return viewJobPostDao.viewUpcomingJobs();
	}

	
	/**
	 * @author Sarthak Bhutani
	 * @since 13-10-2020
	 * this method returns the collection of all active job posts created by logged in employer
	 * @return Collection of {@link GetAllJobPostByEmployerDto} if success; else returns null
	 */
	public Collection<GetAllJobPostByEmployerDto> getAllJobPostByEmployer() {
		Log.debug("Request recieved in service to fetch all active Job Posts active job post created by logged in employer");
		int userId = GetLoggedInUserDetailsUtility.getUserIdFromSession(); 
		Log.debug("User Id retrieved from session is {}",userId);
		return viewJobPostDao.getAllJobPostByEmployer(userId);

	}
	
	/**
	 * @author Sarthak Bhutani
	 * @since 14-10-2020
	 * @param jobStatus
	 * this method returns the collection of all active job posts based on status by logged in Employer
	 * @return Collection of {@link GetAllJobPostByEmployerDto} if success; else returns null
	 *
	 * @updatedOn Sarthak Bhutani
	 * @updatedBy 18/12/2020
	 * @update Calling dao based on the request recieved from which page
	 */
	public Collection<ViewPublishedJobPostDto> viewJobPostByStatus(String jobStatus, String page){
		Log.debug("Request received in service to fetch job posts by employer with jobStatus : {}",jobStatus);
		int userId = GetLoggedInUserDetailsUtility.getUserIdFromSession(); //static  method
		Log.debug("User Id retrieved from session is: {}",userId);
		if(page.equals(ReadApplicationConstants.getCloseJobPostPage())){
			return viewJobPostDao.viewJobPostByStatusForCloseJobPostPage(jobStatus, userId);
		}
		else if(page.equals(ReadApplicationConstants.getPublishJobPostPage())){
			return viewJobPostDao.viewJobPostByStatusForPublishJobPostPage(jobStatus, userId);
		}
		else{
			return null;
		}
	}
	
	/**
	 * @author Prateek Kapoor
	 * @since 22-10-2020
	 * This method fetches all the active job posts created by the logged in user with status as closed or published
	 * @return Collection of {@link GetAllJobPostByEmployerDto} if success; else returns null
	 */
	public Collection<GetAllJobPostByEmployerDto> viewClosedOrPublishedJobPosts()
	{
		Log.debug("Request received in service to fetch the closed or published job posts created by the logged in user");
		Integer userId = GetLoggedInUserDetailsUtility.getUserIdFromSession();
		return viewJobPostDao.viewClosedOrPublishedJobPosts(userId);
	}
	
	/**
	 * @author Sarthak Bhutani
	 * @since 21-10-2020
	 * @return Collection of {@link GetAllJobPostByEmployerDto} if success, else return null
	 * this method return the collection of all deleted(active_flg='N') job posts by an employer
	 */
	public Collection<GetAllJobPostByEmployerDto> getAllDeletedJobPostByEmployer(){
		Log.debug("Request received in service to get all deleted job posts by an employer");
		int userId = GetLoggedInUserDetailsUtility.getUserIdFromSession();
		Log.debug("User Id retrieved from session is: {}", userId);
		return viewJobPostDao.getAllDeletedJobPostByEmployer(userId);
	}
}
