package com.cgsc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.cgsc.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.cgsc.common.AbstractTransactionalDao;
import com.cgsc.common.ReadApplicationConstants;
import com.cgsc.config.PopulateDropdownsConfig;

@Repository
public class PopulateDropdownsDao extends AbstractTransactionalDao
{

	private static final Logger Log = LoggerFactory.getLogger(PopulateDropdownsDao.class);
	private static StatesMapper statesMapper = new StatesMapper();
	private static IndustryTypesMapper industryTypeMapper = new IndustryTypesMapper();
	private static CompanyTypeMapper companyTypeMapper = new CompanyTypeMapper();
	private static CompanyScaleMapper companyScaleMapper = new CompanyScaleMapper();
	private static EducationQualificationMapper educationQualificationMapper = new EducationQualificationMapper();
	private static ProfessionalExperienceMapper professionalExperienceMapper = new ProfessionalExperienceMapper();
	private static JobRolesMapper jobRolesMapper = new JobRolesMapper();
	private static JobPostingsMapper jobPostingsMapper = new JobPostingsMapper();
	private static EmployerDetailsMapper employerDetailsMapper = new EmployerDetailsMapper();
	private static TrainingPartnerMapper trainingPartnerMapper = new TrainingPartnerMapper();
	private static OccupationMapper occupationMapper = new OccupationMapper();
	@Autowired
	private PopulateDropdownsConfig populateDropdownsConfig;
	
	/**
	 * @author Prateek Kapoor
	 * @since 06-10-2020
	 * this method returns the collection of all the states
	 * @return Collection of {@link GetStatesDto} if success; else returns null
	 */
	public Collection<GetStatesDto> getStates()
	{
		Log.debug("Request received in dao to fetch the collection of states");
		try 
		{
			Log.debug("In try block to fetch the collection of states");
			return getJdbcTemplate().query(populateDropdownsConfig.getGetAllStates(), statesMapper);
		}
		catch (Exception e) 
		{
			Log.error("An exception occurred while fetching the collection of states "+e);
			return null;
		}
	}
	
	/**
	 * @author Prateek Kapoor
	 * @since 06-10-2020
	 * this method returns the collection of all the industry types
	 * @return Collection of {@link GetIndustryTypes} if success; else returns null
	 */
	public Collection<GetIndustryTypes> getIndustryTypes()
	{
		Log.debug("Request received in dao to fetch all the industry types");
		try 
		{
			Log.debug("In try block to fetch all the industry types");
			return getJdbcTemplate().query(populateDropdownsConfig.getGetIndustryTypes(), industryTypeMapper);
		}
		catch (Exception e) 
		{
			Log.error("An exception occurred while fetching all the industry types "+e);
			return null;
		}
	}
	
	/**
	 * @author Prateek Kapoor
	 * @since 06-10-2020
	 * this method returns the collection of all the company types
	 * @return Collection of {@link GetCompanyTypeDto} if success; else returns null
	 */
	public Collection<GetCompanyTypeDto> getCompanyType()
	{
		Log.debug("Request received in dao to fetch all the company types");
		try 
		{
			Log.debug("In try block to fetch all the company types");
			return getJdbcTemplate().query(populateDropdownsConfig.getGetCompanyType(), companyTypeMapper);
		}
		catch (Exception e) 
		{
			Log.error("An exception occurred while fetching all the company types "+e);
			return null;
		}
	}
	
	/**
	 * @author Prateek Kapoor
	 * @since 06-10-2020
	 * this method returns the collection of all the company scale
	 * @return Collection of {@link GetCompanyScaleDto} if success; else returns null
	 */
	public Collection<GetCompanyScaleDto> getCompanyScale()
	{
		Log.debug("Request received in dao to fetch company scales");
		try 
		{
			Log.debug("In try block to fetch company scales");
			return getJdbcTemplate().query(populateDropdownsConfig.getGetCompanyScale(), companyScaleMapper);
		}
		catch (Exception e) 
		{
			Log.error("An exception occurred while fetching the company scales "+e);
			return null;
		}
	}
	
	/**
	 * @author Prateek Kapoor
	 * @since 06-10-2020
	 * This method populates the dropdown of education qualification
	 * @return Collection of {@link GetEducationQualificationDto} if success; else returns null
	 */
	public Collection<GetEducationQualificationDto> getEducationQualification()
	{
		Log.debug("Request recieved in dao to populate the education qualification drop down");
		try 
		{
			Log.debug("In try block to populate the education qualification dropdown");
			return getJdbcTemplate().query(populateDropdownsConfig.getGetEducationQualification(), educationQualificationMapper);
		} 
		catch (Exception e) 
		{
			Log.error("An exception occurred while populating the education qualification dropdown "+e);
			return null;
		}
	}
	
	/**
	 * @author Prateek Kapoor
	 * @since 06-10-2020
	 * This method populates the dropdown of professional experience
	 * @return Collection of {@link GetExperienceDto} if success; else returns null
	 */
	public Collection<GetExperienceDto> getProfessionalExperience()
	{
		Log.debug("Request received in dao to populate the working experience dropdown");
		try 
		{
			Log.debug("In try block to populate the working experience dropdown");
			return getJdbcTemplate().query(populateDropdownsConfig.getGetProfessionalExperience(), professionalExperienceMapper);
		}
		catch (Exception e)
		{
			Log.error("An exception occurred while populating the working experience dropdown "+e);
			return null;
		}
	}
	
	/**
	 * @author Prateek Kapoor
	 * @since 09-10-2020
	 * This method returns all the active job roles
	 * @return Collection of {@link GetJobRolesDto} if success; else returns null
	 */
	public Collection<GetJobRolesDto> getJobRoles()
	{
		Log.debug("Request received in dao to fetch all the active job roles");
		Map<String,String> params = new HashMap<>(1);
		params.put("activeFlagTrue", ReadApplicationConstants.getTrueFlag());
		Log.debug("Hashmap created and parameters inserted into hashmap");
		try 
		{
			Log.debug("In try block to fetch all the active job roles");
			return getJdbcTemplate().query(populateDropdownsConfig.getGetJobRoles(), params, jobRolesMapper);
		}
		catch (Exception e) 
		{
			Log.error("An exception occurred while fetching the active job roles - "+e);
			return null;
		}
	}
	
	/**
	 * @author Prateek Kapoor
	 * @since 13-10-2020
	 * @param status
	 * @param employerId
	 * This method fetches the list of all the active job postings made by the logged in employer with a particular status
	 * @return Collection of {@link GetJobPostingIdDto} if success; else returns null;
	 */
	public Collection<GetJobPostingIdDto> getJobIdsForStatus(String status, int employerId)
	{
		Log.debug("Request received in dao to fetch all the job Id, names created by employer with id {} with status {}",employerId,status);
		Map<String,Object>params = new HashMap<>(3);
		params.put("status", status);
		params.put("employerId", employerId);
		params.put("activeFlagTrue", ReadApplicationConstants.getTrueFlag());
		Log.debug("Hashmap created and employer id {}, status {} inserted into hashmap",params.get("employerId"),params.get("status"));
		try 
		{
			Log.debug("In try block to execute the query and return the result");
			return getJdbcTemplate().query(populateDropdownsConfig.getGetJobIdsWithStatus(), params,jobPostingsMapper);
		} 
		catch (Exception e) 
		{
			Log.error("An exception occurred while fetching the list of job Ids "+e);
			return null;
		}
	}
	
	/**
	 * @author Prateek Kapoor
	 * @since 15-10-2020
	 * This method fetches the list of all the active employers on the platform
	 * @return Collection of {@link GetActiveEmployerDto} if success; else returns null
	 */
	public Collection<GetActiveEmployerDto> getActiveEmployers()
	{
		Log.debug("Request received in dao to fetch all the active employers on the platform");
		Map<String,String> params = new HashMap<>(3);
		params.put("userRoleEmployer", ReadApplicationConstants.getUserRoleEmployer());
		params.put("activeFlagTrue", ReadApplicationConstants.getTrueFlag());
		params.put("approvedFlag", ReadApplicationConstants.getApprovedFlag());
		Log.debug("Hashmap created and parameters inserted into hashmap with values, userRoleEmployer : {}, activeFlagTrue : {}, approvedFlag : {}", params.get("userRoleEmployer"), params.get("activeFlagTrue"), params.get("approvedFlag"));
		try 
		{
			Log.debug("In try block to execute the query and fetch the result");
			return getJdbcTemplate().query(populateDropdownsConfig.getGetActiveEmployers(),params, employerDetailsMapper);
		} 
		catch (Exception e) 
		{
			Log.error("An exception occurred while fetching the list of active employers "+e);
			return null;
		}
	}
	
	/**
	 * @author Prateek Kapoor
	 * @since 23-11-2020
	 * This method fetches the list of active training partners
	 * @return Collection of {@link TrainingPartnerDto} if success; else returns null
	 */
	public Collection<TrainingPartnerDto> showTrainingPartnerDetails()
	{
		Log.debug("Request received in dao to fetch the list of active training partners");
		Map<String,String> params = new HashMap<>(1);
		params.put("activeFlagTrue", ReadApplicationConstants.getTrueFlag());
		Log.debug("Hashmap created and active flag true inserted into hashmap");
		try 
		{
			Log.debug("In try block to execute the query and fetch the result");
			return getJdbcTemplate().query(populateDropdownsConfig.getGetActiveTrainingPartners(), params, trainingPartnerMapper);
		} 
		catch (Exception e) 
		{
			Log.error("An exception occurred while fetching the list of active training partners "+e);
			return null;
		}
	}

	/**
	 * @author Sarthak Bhutani
	 * @since 04-12-2020
	 * This method fetches the list of occupations against a job role
	 * @param jobRole
	 * @return Collection of {@link GetOccupationDto} if success, else returns null
	 */
	public Collection<GetOccupationDto> getOccupations(String jobRole){
		Log.debug("Request received in dao to fetch occupations against jobRole : {}",jobRole);
		HashMap<String,String> params = new HashMap<>(2);
		params.put("jobRole",jobRole);
		params.put("activeFlagTrue",ReadApplicationConstants.getTrueFlag());
		Log.debug("Hashmap created and active flag true inserted into hashmap");
		try{
			Log.debug("In try block to execute the query and fetch the result");
			return getJdbcTemplate().query(populateDropdownsConfig.getGetOccupationByJobRole(),params,occupationMapper);
		}
		catch (Exception e){
			Log.error("An exception occurred while fetching the list of occupations against a jobRole "+e);
			return null;
		}
	}

	/**
	 * Row Mapper for {@link GetStatesDto}
	 * @author Prateek Kapoor
	 *
	 */
	public static class StatesMapper implements RowMapper<GetStatesDto>
	{
		@Override
		public GetStatesDto mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int id = rs.getInt("id");
			String stateName = rs.getString("states");
			return new GetStatesDto(id, stateName);
		}
	}
	
	/**
	 * Row Mapper for {@link GetStatesDto}
	 * @author Prateek Kapoor
	 *
	 */
	public static class CompanyScaleMapper implements RowMapper<GetCompanyScaleDto>
	{
		@Override
		public GetCompanyScaleDto mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int id = rs.getInt("id");
			String companyScale = rs.getString("company_scale");
			return new GetCompanyScaleDto(id, companyScale);
		}
	}
	
	/**
	 * Row Mapper for {@link GetIndustryTypes}
	 * @author Prateek Kapoor
	 *
	 */
	public static class IndustryTypesMapper implements RowMapper<GetIndustryTypes>
	{
		@Override
		public GetIndustryTypes mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int id = rs.getInt("id");
			String industryType = rs.getString("industry_type");
			return new GetIndustryTypes(id, industryType);
		}
	}
	
	/**
	 * Row Mapper for {@link GetCompanyTypeDto}
	 * @author Prateek Kapoor
	 *
	 */
	public static class CompanyTypeMapper implements RowMapper<GetCompanyTypeDto>
	{
		@Override
		public GetCompanyTypeDto mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int id = rs.getInt("id");
			String companyType = rs.getString("company_type");
			return new GetCompanyTypeDto(id, companyType);
		}
	}
	
	/**
	 * Row Mapper for {@link GetEducationQualificationDto}
	 * @author Prateek Kapoor
	 *
	 */
	public static class EducationQualificationMapper implements RowMapper<GetEducationQualificationDto>
	{
		@Override
		public GetEducationQualificationDto mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int id = rs.getInt("id");
			String educationQualification = rs.getString("education_qualification");
			return new GetEducationQualificationDto(id, educationQualification);
		}
	}
	
	/**
	 * Row Mapper for {@link GetExperienceDto}
	 * @author Prateek Kapoor
	 *
	 */
	public static class ProfessionalExperienceMapper implements RowMapper<GetExperienceDto>
	{
		@Override
		public GetExperienceDto mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int id = rs.getInt("id");
			String experience = rs.getString("experience");
			return new GetExperienceDto(id, experience);
		}
	}
	
	/**
	 * Row Mapper for {@link GetJobRolesDto}
	 * @author Prateek Kapoor
	 *
	 */
	public static class JobRolesMapper implements RowMapper<GetJobRolesDto>
	{
		@Override
		public GetJobRolesDto mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int jobRoleId = rs.getInt("id");
			String jobRole = rs.getString("job_role");
			return new GetJobRolesDto(jobRoleId, jobRole);
		}
	}
	
	/**
	 * Row Mapper for {@link GetJobPostingIdDto}
	 * @author Prateek Kapoor
	 *
	 */
	public static class JobPostingsMapper implements RowMapper<GetJobPostingIdDto>
	{
		@Override
		public GetJobPostingIdDto mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int uniqueId = rs.getInt("id");
			String jobId = rs.getString("job_id");
			String jobTitle = rs.getString("job_title");
			return new GetJobPostingIdDto(uniqueId, jobId, jobTitle);
		}
	}
	
	/**
	 * Row Mapper for {@link GetActiveEmployerDto}
	 * @author Prateek Kapoor
	 *
	 */
	public static class EmployerDetailsMapper implements RowMapper<GetActiveEmployerDto>
	{
		@Override
		public GetActiveEmployerDto mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int employerId = rs.getInt("id");
			String employerName = rs.getString("username");
			return new GetActiveEmployerDto(employerId, employerName);
		}
	}
	
	/**
	 * Row Mapper for {@link TrainingPartnerDto}
	 * @author Prateek Kapoor
	 *
	 */
	public static class TrainingPartnerMapper implements RowMapper<TrainingPartnerDto>
	{
		@Override
		public TrainingPartnerDto mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int userId = rs.getInt("id");
			String trainingPartnerName = rs.getString("tp_name");
			return new TrainingPartnerDto(userId, trainingPartnerName);
			
		}
	}

	/**
	 * Row Mapper for {@link TrainingPartnerDto}
	 * @author Prateek Kapoor
	 *
	 */
	static class OccupationMapper implements RowMapper<GetOccupationDto>{
		@Override
		public GetOccupationDto mapRow(ResultSet rs, int rowNum) throws SQLException {
			int occupationId = rs.getInt("id");
			String occupationName = rs.getString("occupation_name");
			return new GetOccupationDto(occupationId,occupationName);
		}
	}
}
