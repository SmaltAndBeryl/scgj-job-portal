package com.cgsc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.cgsc.common.AbstractTransactionalDao;
import com.cgsc.common.ReadApplicationConstants;
import com.cgsc.config.EmployerViewOfferLetterConfig;
import com.cgsc.dto.HiredCandidateDetailsDto;

@Repository
public class EmployerViewOfferLetterDao extends AbstractTransactionalDao{

	private static final Logger Log = LoggerFactory.getLogger(EmployerViewOfferLetterDao.class);
	
	@Autowired 
	private EmployerViewOfferLetterConfig employerViewOfferLetterConfig;

	private static HiredCandidateDetailsMapper candidateDetailsMapper = new HiredCandidateDetailsMapper();
	
	/**
	 * @author Jyoti Singh
	 * @since 23-12-2020
	 * This method fetches the collection of candidate details who were hired for the job post with given id
	 * @param id (int)
	 * @return Collection of {@link HiredCandidateDetailsDto} if success; else returns null
	 */
	public Collection<HiredCandidateDetailsDto> viewHiredCandidates(int jobId) {
		Log.debug("Request received in dao method to fetch candidate details who were hired for the job post with id - {}",jobId);
		Map<String,Object> params = new HashMap<>(2);
		params.put("jobId", jobId);
		params.put("applicationStatusHired", ReadApplicationConstants.getApplicationStateHired());
		Log.debug("Created hashmap with job id {} and candidate application status - {}",params.get("jobId"),params.get("applicationStatusHired"));
		
		try 
		{
			Log.debug("In try block to fetch candidate details who were hired for the job post with id - {}",jobId);
			return getJdbcTemplate().query(employerViewOfferLetterConfig.getFetchHiredCandidateDetailsSql(),params, candidateDetailsMapper);
		} 
		catch (Exception e) 
		{
			Log.error("An exception occurred while fetching candidate details who were hired for selected job post {}",e);
			return null;
		}
	}
	
	
	
	/**
	 * @author Jyoti Singh
	 * Row Mapper to map result set into HiredCandidateDetailsDto
	 */
	static class HiredCandidateDetailsMapper implements RowMapper<HiredCandidateDetailsDto>{

		@Override
		public HiredCandidateDetailsDto mapRow(ResultSet rs, int rowNum) throws SQLException 
		{		
			SimpleDateFormat dateFormatter = new SimpleDateFormat(ReadApplicationConstants.getDateWithMonthNameFormat());
			int candidateId = rs.getInt("candidateId");
			String candidateName = rs.getString("candidateName");
			long candidateMobileNumber = rs.getLong("candidateMobileNumber");
			String certificatePath = rs.getString("certificatePath");
			String resumePath = rs.getString("resumePath");
			String state = rs.getString("state");
			String educationQualification = rs.getString("educationQualification");
			String professionalExperience = rs.getString("workingExperience");
			String gender = rs.getString("gender");
			String defenceBackground = rs.getString("defenceBackground");
			long guardianMobileNumber = rs.getLong("guardianMobileNumber");
			String isCgscCertified = rs.getString("isCgscCertified");
			String address = rs.getString("address");
			long adharNumber = rs.getLong("adharNumber");
			String jobRole = rs.getString("jobRole");
			String cgscCertificateNumber = Objects.isNull(rs.getString("cgscCertificateNumber"))?ReadApplicationConstants.getNotApplicableText():rs.getString("cgscCertificateNumber");
			
			String hiredOn=Objects.isNull(rs.getDate("updatedOn"))?null:dateFormatter.format(rs.getDate("updatedOn"));
			String appliedOn=Objects.isNull(rs.getDate("appliedOn"))?null:dateFormatter.format(rs.getDate("appliedOn"));
			String joiningDate=Objects.isNull(rs.getDate("joiningDate"))?null:dateFormatter.format(rs.getDate("joiningDate"));
			int salaryOffered = rs.getInt("salaryOffered");
			String offerLetterPath = rs.getString("offerLetterPath");
			String applicationStatus = rs.getString("applicationStatus");
			String trainingPartner = rs.getString("trainingPartner");

			return new HiredCandidateDetailsDto(candidateId,candidateName,candidateMobileNumber,state,
					certificatePath,resumePath,appliedOn,applicationStatus,educationQualification,professionalExperience,
					gender,guardianMobileNumber,defenceBackground,hiredOn,isCgscCertified,cgscCertificateNumber,adharNumber,jobRole, 
					address,joiningDate,salaryOffered,offerLetterPath,trainingPartner);
		}
		
	}
	

	
}
