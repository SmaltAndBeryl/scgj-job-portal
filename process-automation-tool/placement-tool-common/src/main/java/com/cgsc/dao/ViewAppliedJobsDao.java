package com.cgsc.dao;

import java.sql.Date;
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
import com.cgsc.config.ViewAppliedJobsConfig;
import com.cgsc.dto.ViewAppliedJobsDto;
import com.cgsc.dto.ViewJobPostDto;

@Repository
public class ViewAppliedJobsDao extends AbstractTransactionalDao{
	
	private static final Logger Log = LoggerFactory.getLogger(ViewAppliedJobsDao.class);
	
	private static GetCandidateAppliedJobsRowMapper viewAppliedJobsRowMapper = new GetCandidateAppliedJobsRowMapper();
	
	@Autowired
	private ViewAppliedJobsConfig viewAppliedJobsConfig;

	/**
	 * @author Jyoti Singh
	 * @since 16-10-2020
	 * The method fetches the details of jobs in which the logged-in candidate has applied
	 * @param candidateId(int)
	 * @return Collection of {@link ViewJobPostDto} if success; else returns null in case of exception
	 */
	public Collection<ViewAppliedJobsDto> viewAppliedJobs(int candidateId) {
		Log.debug("Request recieved in dao to fetch Job Posts in which the candidate with id - {} has applied",candidateId);
		Map<String,Object> params= new HashMap<>(4);
		params.put("candidateId", candidateId);
		params.put("statusPublished", ReadApplicationConstants.getPublishedState());
		params.put("statusClosed", ReadApplicationConstants.getClosedState());
		params.put("activeFlagTrue",ReadApplicationConstants.getTrueFlag());
		params.put("approvedFlag",ReadApplicationConstants.getApprovedFlag());
		
		Log.debug("Created hashmap with status- {} & {} and Active Flag - {}",params.get("statusPublished"),params.get("statusClosed"),params.get("activeFlagTrue"));
		try {
			Log.debug("In try block for fetching job post details in which candidate with id- {} has applied", candidateId);
			return getJdbcTemplate().query(viewAppliedJobsConfig.getViewAppliedJobs(), params, viewAppliedJobsRowMapper);
		}
		catch(Exception e) {
			Log.debug("An exception occured while fetching applied job details {}",e);
			return null;
		}
	}
	
	


	/**
	 * Row Mapper for {@link ViewAppliedJobsDto} to view all job posts in which the logged in candidate has applied
	 * @author Jyoti Singh
	 *
	 * @updated by Sarthak Bhutani
	 * @updated on 07/12/2020
	 * @update added fields salary, joiningdate, offerLetterPath in RowMapper to be mapped
	 */
	private static class GetCandidateAppliedJobsRowMapper implements RowMapper<ViewAppliedJobsDto>{
		
		SimpleDateFormat dateFormatter =new SimpleDateFormat(ReadApplicationConstants.getDateWithMonthNameFormat());
		@Override
		public ViewAppliedJobsDto mapRow(ResultSet rs, int rowNum) throws SQLException {
			 int id = rs.getInt("id");
			 String jobId = rs.getString("jobId");
			 String jobTitle = rs.getString("jobTitle");
			 String descriptionDocumentPath = rs.getString("descriptionDocumentPath");
			 String applicationStatus = rs.getString("applicationStatus");
			 String postedBy = rs.getString("postedBy");
			 String jobStatus = rs.getString("jobStatus");
			 String salary = rs.getString("salary");
			 String offerLetterPath = rs.getString("offerLetterPath");
			 String formattedAppliedOn = Objects.isNull(rs.getDate("appliedOn"))?null:dateFormatter.format(rs.getDate("appliedOn"));
			 String formattedUpdatedOn = Objects.isNull(rs.getDate("updatedOn"))?null:dateFormatter.format(rs.getDate("updatedOn"));
			 String formattedPublishedAt = Objects.isNull(rs.getDate("publishedAt"))?null:dateFormatter.format(rs.getDate("publishedAt"));
			 String formattedJoiningDate = Objects.isNull(rs.getDate("joiningDate"))?null:dateFormatter.format(rs.getDate("joiningDate"));

			return new ViewAppliedJobsDto(id,jobId,jobTitle,formattedAppliedOn,descriptionDocumentPath,applicationStatus,postedBy,jobStatus,formattedPublishedAt,formattedUpdatedOn, salary, formattedJoiningDate,offerLetterPath);
		}
		
	}	
	
	
}
