package com.cgsc.dao;

import com.cgsc.common.ReadApplicationConstants;
import com.cgsc.config.SendNotificationConfig;
import com.cgsc.dto.SendNotificationDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.cgsc.common.AbstractTransactionalDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

@Repository
public class SendNotificationDao extends AbstractTransactionalDao{

    private static final Logger Log = LoggerFactory.getLogger(SendNotificationDao.class);

    @Autowired
    private SendNotificationConfig sendNotificationConfig;

    /**
     * @author Sarthak Bhutani
     * @since 16/12/2020
     * This is the method to retrieve mobile numbers of candidates against a job role
     * @param jobRoleId
     * @return Collection {@link ArrayList<Long>} if sucess, null in case of exception
     */
    public ArrayList<Long> getCandidateMobileNumbersForJobRole(int jobRoleId){
        Log.debug("Request received by dao to get mobile numbers of candidates against job role id : {}",jobRoleId);
        HashMap<String, Object> params = new HashMap<>(2);
        params.put("jobRoleId",jobRoleId);
        params.put("trueFlag", ReadApplicationConstants.getTrueFlag());
        Log.debug("Hashmap generated & values are inserted");
        try{
            Log.debug("In try block of dao to get mobile numbers of candidates");
                return (ArrayList<Long>) getJdbcTemplate().queryForList(sendNotificationConfig.getGetMobileNumbersAgainstJobRole(),params,Long.class);
        }
        catch (Exception e){
            Log.error("An exception occurred while getting mobile numbers of candidates against job role id : {}, exception : {} ",jobRoleId, e);
            return null;
        }
    }

    /**
     * @author Sarthak Bhutani
     * @since 16/12/2020
     * This is the method to create a log (in database) of the sms notification message sent to candidates via admin
     * @param sendNotificationDto
     * @param userId
     * @return 1 if success, -25 if exception
     */
    public int addLogForMessageInDB(SendNotificationDto sendNotificationDto, int userId){
        Log.debug("Request received by dao to store notification message in database which was sent to candidates by userId :"+userId);
        HashMap<String, Object> params = new HashMap<>(3);
        params.put("jobRoleId",sendNotificationDto.getJobRoleId());
        params.put("userId",userId);
        params.put("message",sendNotificationDto.getMessage());
        Log.debug("Hashmap generated & values are inserted");
        try{
            Log.debug("In try block of dao to store the notification message log in database");
            return getJdbcTemplate().update(sendNotificationConfig.getAddNotificationMessageLog(),params);
        }
        catch (Exception e){
            Log.error("An exception occurred while adding notification message in database which was sent to candidates, exception :"+ e);
            return -25;
        }
    }
}
