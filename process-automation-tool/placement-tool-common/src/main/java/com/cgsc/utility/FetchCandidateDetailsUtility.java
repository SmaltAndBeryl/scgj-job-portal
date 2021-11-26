package com.cgsc.utility;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.cgsc.common.AbstractTransactionalDao;
import com.cgsc.common.ReadApplicationConstants;
import com.cgsc.config.FetchCandidateDetailsUtilityConfig;
import com.cgsc.dao.FetchCandidateDetailsDto;

@Repository
public class FetchCandidateDetailsUtility extends AbstractTransactionalDao
{

	private static final Logger Log = LoggerFactory.getLogger(FetchCandidateDetailsUtility.class);
	
	private static CandidateDetailsMapper candidateDetailsMapper = new CandidateDetailsMapper();
	
	@Autowired
	private FetchCandidateDetailsUtilityConfig fetchCandidateDetailsUtilityConfig;
	
	
	/**
	 * @author Prateek Kapoor
	 * @since 21-10-2020
	 * This method fetches the collection of active candidate details
	 * @return Collection {@link FetchCandidateDetailsDto} if success; else returns null
	 */
	public Collection<FetchCandidateDetailsDto> fetchCandidateDetails()
	{
		Log.debug("Request received in utility method to fetch id and mobile number of all the active candidates");
		Map<String,String> params = new HashMap<>(2);
		params.put("userRoleCandidate", ReadApplicationConstants.getUserRoleCandidate());
		params.put("activeFlagTrue", ReadApplicationConstants.getTrueFlag());
		Log.debug("Hashmap created and parameters inserted into hashmap");
		try
		{
			Log.debug("In try block to execute the query and fetch the result");
			return getJdbcTemplate().query(fetchCandidateDetailsUtilityConfig.getViewCandidateInformation(), params, candidateDetailsMapper);
		}
		catch (Exception e) 
		{
			Log.error("An exception occurred while fetching the candidate details "+e);
			return null;
		}
	}
	
	/**
	 * Row Mapper for {@link FetchCandidateDetailsDto}
	 * @author Prateek Kapoor
	 *
	 */
	public static class CandidateDetailsMapper implements RowMapper<FetchCandidateDetailsDto>
	{
		public FetchCandidateDetailsDto mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int id = rs.getInt("id");
			long mobileNumber = rs.getLong("mobile_number");
			return new FetchCandidateDetailsDto(id, mobileNumber);
		}	
	}
	
}
