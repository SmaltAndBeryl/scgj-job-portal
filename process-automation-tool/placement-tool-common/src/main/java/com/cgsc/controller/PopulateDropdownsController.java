package com.cgsc.controller;

import java.util.Collection;

import com.cgsc.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cgsc.common.Privilege;
import com.cgsc.service.PopulateDropdownsService;

@RestController
public class PopulateDropdownsController
{

	private static final Logger Log = LoggerFactory.getLogger(PopulateDropdownsController.class);
	
	@Autowired
	private PopulateDropdownsService populateDropdownService;
	
	/**
	 * @author Prateek Kapoor
	 * @since 06-10-2020
	 * this method returns the list of all the states
	 * @return Collection of {@link GetStatesDto} if success; else returns null
	 */
	@GetMapping("/getStates")
	public Collection<GetStatesDto> getStates()
	{
		Log.debug("Request received in controller to fetch the list of states");
		return populateDropdownService.getStates();
	}
	
	/**
	 * @author Prateek Kapoor
	 * @since 06-10-2020
	 * this method returns the collection of all the industry types
	 * @return Collection of {@link GetIndustryTypes} if success; else returns null
	 */
	@GetMapping("/getIndustryTypes")
	public Collection<GetIndustryTypes> getIndustryTypes()
	{
		Log.debug("Request received in controller to fetch all the industry types");
		return populateDropdownService.getIndustryTypes();
	}

	/**
	 * @author Prateek Kapoor
	 * @since 06-10-2020
	 * this method returns the collection of all the company types
	 * @return Collection of {@link GetCompanyTypeDto} if success; else returns null
	 */
	@GetMapping("/getCompanyType")
	public Collection<GetCompanyTypeDto> getCompanyType()
	{
		Log.debug("Request received in controller to fetch all the company types");
		return populateDropdownService.getCompanyType();
	}
	
	/**
	 * @author Prateek Kapoor
	 * @since 06-10-2020
	 * this method returns the collection of all the company scale
	 * @return Collection of {@link GetCompanyScaleDto} if success; else returns null
	 */
	@GetMapping("/getCompanyScales")
	public Collection<GetCompanyScaleDto> getCompanyScale()
	{
		Log.debug("Request received in controller to fetch company scales");
		return populateDropdownService.getCompanyScale();
	}
	
	/**
	 * @author Prateek Kapoor
	 * @since 06-10-2020
	 * This method populates the dropdown of education qualification
	 * @return Collection of {@link GetEducationQualificationDto} if success; else returns null
	 */
	@GetMapping("/getEducationQualification")
	public Collection<GetEducationQualificationDto> getEducationQualification()
	{
		Log.debug("Request recieved in controller to populate the education qualification drop down");
		return populateDropdownService.getEducationQualification();
	}
	
	/**
	 * @author Prateek Kapoor
	 * @since 06-10-2020
	 * This method populates the dropdown of professional experience
	 * @return Collection of {@link GetExperienceDto} if success; else returns null
	 */
	@GetMapping("/getProfessionalExperience")
	public Collection<GetExperienceDto> getProfessionalExperience()
	{
		Log.debug("Request received in controller to populate the working experience dropdown");
		return populateDropdownService.getProfessionalExperience();
	}
	
	/**
	 * @author Prateek Kapoor
	 * @since 09-10-2020
	 * This method returns all the active job roles
	 * @return Collection of GetJobRolesDto if success; else returns null
	 */
	@GetMapping("/getJobRoles")
	public Collection<GetJobRolesDto> getJobRoles()
	{
		Log.debug("Request received in controller to fetch all the active job roles");
		return populateDropdownService.getJobRoles();
	}
	
	/**
	 * @author Prateek Kapoor
	 * @since 13-10-2020
	 * @param status
	 * This method fetches the list of all the active job postings made by the logged in employer with a particular status
	 * @return Collection of {@link GetJobPostingIdDto} if success; else returns null;
	 */
	@Privilege(value = {"Employer"})
	@GetMapping("/getJobIdWithStatus")
	public Collection<GetJobPostingIdDto> getJobIdsForStatus(@RequestParam("status") String status)
	{
		Log.debug("Request received in controller to fetch all the job Id, names created by logged in employer with status {}",status);
		return populateDropdownService.getJobIdsForStatus(status);
	}
	
	/**
	 * @author Prateek Kapoor
	 * @since 15-10-2020
	 * This method fetches the list of all the active employers on the platform
	 * @return Collection of {@link GetActiveEmployerDto} if success; else returns null
	 */
	@GetMapping("/getEmployers")
	public Collection<GetActiveEmployerDto> getActiveEmployers()
	{
		Log.debug("Request received in controller to fetch all the active employers on the platform");
		return populateDropdownService.getActiveEmployers();
	}
	
	/**
	 * @author Prateek Kapoor
	 * @since 23-11-2020
	 * This method fetches the list of active training partners
	 * @return Collection of {@link TrainingPartnerDto} if success; else returns null
	 */
	@GetMapping("/viewTrainingPartners")
	public Collection<TrainingPartnerDto> showTrainingPartnerDetails()
	{
		Log.debug("Request received in controller to fetch the list of active training partners");
		return populateDropdownService.showTrainingPartnerDetails();
	}

	/**
	 * @author Sarthak Bhutani
	 * @since 04-12-2020
	 * This method fetches the list of occupations against a job role
	 * @param jobRole
	 * @return Collection of {@link GetOccupationDto} if success, else returns null
	 */
	@GetMapping("/getOccupationByJobRole")
	public Collection<GetOccupationDto> getOccupations(@RequestParam ("jobRole") String jobRole){
		Log.debug("Request received in controller to fetch occupations against jobRole : {}",jobRole);
		return populateDropdownService.getOccupations(jobRole);
	}
}
