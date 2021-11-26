package com.cgsc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.cgsc.common.AbstractTransactionalDao;
import com.cgsc.common.ReadApplicationConstants;
import com.cgsc.config.ManageCandidatesConfig;
import com.cgsc.dto.CandidateDetailsDto;

@Repository
public class ManageCandidatesDao extends AbstractTransactionalDao
{

	private static final Logger Log = LoggerFactory.getLogger(ManageCandidatesDao.class);
	private CandidateDetailsMapper candidateDetails = new CandidateDetailsMapper();
	
	@Autowired
	private ManageCandidatesConfig manageCandidatesConfig;
	
	/**
	 * @author Prateek Kapoor
	 * @since 29-10-2020
	 * This method fetches the collection of all the candidates whose poll count is greater than 2
	 * @return Collection of {@link CandidateDetailsDto} if success; else returns null
	 *
	 * @update Sarthak Bhutani
	 * @updatedOn 18/12/2020
	 * @update Added field - falseFlag in Hashmap & changed the behaviour of method
	 * This method fetches the collection of all candidates whose active_flag is false
	 */
	public Collection<CandidateDetailsDto> viewInactiveCandidates()
	{
		Log.debug("Request received in dao to fetch the list of inactive candidates");
		HashMap<String,String> params = new HashMap<>(1);
		params.put("falseFlag",ReadApplicationConstants.getFalseFlag());
		Log.debug("Hashmap created & value inserted");
		try 
		{
			Log.debug("In try block to execute the query and fetch the result");
			return getJdbcTemplate().query(manageCandidatesConfig.getViewInactiveCandidates(),params, candidateDetails);
		}
		catch (Exception e) 
		{
			Log.error("An exception occurred while fetching the list of inactive candidate "+e);
			return null;
		}
	}
	
	/**
	 * Row Mapper to map result set into CandidateDetailsDto
	 * @author Prateek Kapoor
	 *
	 * @updatedBy Sarthak Bhutani
	 * @updatedOn 22/11/2020
	 */
	public static class CandidateDetailsMapper implements RowMapper<CandidateDetailsDto>
	{
		@Autowired
		private ReadApplicationConstants readApplicationConstants;
		
		public CandidateDetailsDto mapRow(ResultSet rs, int rowNum) throws SQLException
		{

			SimpleDateFormat dateTimeFormatter = new SimpleDateFormat(ReadApplicationConstants.getDateTimeFormat());
			int userId = rs.getInt("userId");
			long mobileNumber = rs.getLong("mobile_number");
			String candidateName = rs.getString("username");
			String state = rs.getString("state");
			String isActive = rs.getString("is_active_flg");
			long guardianMobileNumber = rs.getLong("guardian_mobile_number");
			int pollCount = rs.getInt("poll_count");	
			String gender = rs.getString("gender");
			String lastPolledOn = Objects.isNull(rs.getTimestamp("polled_at"))?null:dateTimeFormatter.format(rs.getTimestamp("polled_at"));
			String trainingPartnerName = Objects.isNull(rs.getString("tp_name"))?readApplicationConstants.getNotApplicableText():rs.getString("tp_name");
			return new CandidateDetailsDto(userId, candidateName, mobileNumber, guardianMobileNumber, gender, state, isActive, pollCount, lastPolledOn, trainingPartnerName);
		}
	}
}
