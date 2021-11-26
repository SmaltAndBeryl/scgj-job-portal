package com.cgsc.service;

import java.util.Collection;

import com.cgsc.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgsc.dao.PopulateDropdownsDao;
import com.cgsc.utility.GetLoggedInUserDetailsUtility;

@Service
public class PopulateDropdownsService 
{

	private static final Logger Log = LoggerFactory.getLogger(PopulateDropdownsService.class);
	
	@Autowired
	private PopulateDropdownsDao populateDropdownsDao;
	
	/**
	 * @author Prateek Kapoor
	 * @since 06-10-2020
	 * this method returns the list of all the states
	 * @return Collection of {@link GetStatesDto} if success; else returns null
	 */
	public Collection<GetStatesDto> getStates()
	{
		Log.debug("Request received in service to fetch the list of states");
		return populateDropdownsDao.getStates();
	}
	
	
	/**
	 * @author Prateek Kapoor
	 * @since 06-10-2020
	 * this method returns the collection of all the industry types
	 * @return Collection of {@link GetIndustryTypes} if success; else returns null
	 */
	public Collection<GetIndustryTypes> getIndustryTypes()
	{
		Log.debug("Request received in service to fetch all the industry types");
		return populateDropdownsDao.getIndustryTypes();
	}
	
	/**
	 * @author Prateek Kapoor
	 * @since 06-10-2020
	 * this method returns the collection of all the company scale
	 * @return Collection of {@link GetCompanyScaleDto} if success; else returns null
	 */
	public Collection<GetCompanyScaleDto> getCompanyScale()
	{
		Log.debug("Request received in service to fetch company scales");
		return populateDropdownsDao.getCompanyScale();
	}
	
	/**
	 * @author Prateek Kapoor
	 * @since 06-10-2020
	 * this method returns the collection of all the company types
	 * @return Collection of {@link GetCompanyTypeDto} if success; else returns null
	 */
	public Collection<GetCompanyTypeDto> getCompanyType()
	{
		Log.debug("Request received in service to fetch all the company types");
		return populateDropdownsDao.getCompanyType();
	}
	
	/**
	 * @author Prateek Kapoor
	 * @since 06-10-2020
	 * This method populates the dropdown of education qualification
	 * @return Collection of {@link GetEducationQualificationDto} if success; else returns null
	 */
	public Collection<GetEducationQualificationDto> getEducationQualification()
	{
		Log.debug("Request recieved in service to populate the education qualification drop down");
		return populateDropdownsDao.getEducationQualification();
	}
	
	/**
	 * @author Prateek Kapoor
	 * @since 06-10-2020
	 * This method populates the dropdown of professional experience
	 * @return Collection of {@link GetExperienceDto} if success; else returns null
	 */
	public Collection<GetExperienceDto> getProfessionalExperience()
	{
		Log.debug("Request received in service to populate the working experience dropdown");
		return populateDropdownsDao.getProfessionalExperience();
	}

	/**
	 * @author Prateek Kapoor
	 * @since 09-10-2020
	 * This method returns all the active job roles
	 * @return Collection of GetJobRolesDto if success; else returns null
	 */
	public Collection<GetJobRolesDto> getJobRoles()
	{
		Log.debug("Request received in service to fetch all the active job roles");
		return populateDropdownsDao.getJobRoles();
	}
	
	/**
	 * @author Prateek Kapoor
	 * @since 13-10-2020
	 * @param status
	 * This method fetches the list of all the active job postings made by the logged in employer with a particular status
	 * @return Collection of {@link GetJobPostingIdDto} if success; else returns null;
	 */
	public Collection<GetJobPostingIdDto> getJobIdsForStatus(String status)
	{
		Log.debug("Request received in service to fetch all the job Id, names created by logged in employer with status {}",status);
		Integer employerId = GetLoggedInUserDetailsUtility.getUserIdFromSession();
		Log.debug("The employerId fetched from session is {}",employerId);
		return populateDropdownsDao.getJobIdsForStatus(status, employerId);
	}
	
	/**
	 * @author Prateek Kapoor
	 * @since 15-10-2020
	 * This method fetches the list of all the active employers on the platform
	 * @return Collection of {@link GetActiveEmployerDto} if success; else returns null
	 */
	public Collection<GetActiveEmployerDto> getActiveEmployers()
	{
		Log.debug("Request received in service to fetch all the active employers on the platform");
		return populateDropdownsDao.getActiveEmployers();
	}
	

	/**
	 * @author Prateek Kapoor
	 * @since 23-11-2020
	 * This method fetches the list of active training partners
	 * @return Collection of {@link TrainingPartnerDto} if success; else returns null
	 */
	public Collection<TrainingPartnerDto> showTrainingPartnerDetails()
	{
		Log.debug("Request received in service to fetch the list of active training partners");
		return populateDropdownsDao.showTrainingPartnerDetails();
	}

	/**
	 * @author Sarthak Bhutani
	 * @since 04-12-2020
	 * This method fetches the list of occpuations against a job role
	 * @param jobRole
	 * @return Collection of {@link GetOccupationDto} if success, else returns null
	 */
	public Collection<GetOccupationDto> getOccupations(String jobRole){
		Log.debug("Request received in service to fetch occupations against jobRole : {}",jobRole);
		return populateDropdownsDao.getOccupations(jobRole);
	}
}
