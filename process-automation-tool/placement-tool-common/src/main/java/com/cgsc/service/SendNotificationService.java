package com.cgsc.service;

import com.cgsc.controller.PopulateDropdownsController;
import com.cgsc.dao.SendNotificationDao;
import com.cgsc.dto.SendNotificationDto;
import com.cgsc.utility.GetLoggedInUserDetailsUtility;
import com.cgsc.utility.SendOtpUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

@Service
public class SendNotificationService {

    private static final Logger Log = LoggerFactory.getLogger(SendNotificationService.class);

    @Autowired
    private SendOtpUtility sendOtpUtility;

    @Autowired
    private SendNotificationDao sendNotificationDao;

    @Autowired
    private GetLoggedInUserDetailsUtility getLoggedInUserDetailsUtility;

    /**
     * @param sendNotificationDto
     * @return 1 if sucess,-25 in case of exception, -65 in case no candidates are found for a job role
     * @author Sarthak Bhutani
     * @since 16/12/2020
     * This is the method to send custom notification (SMS) to all candidates against a job role
     */
    public int sendNotification(SendNotificationDto sendNotificationDto) {
        Log.debug("Request received by controller to send sms notifications to candidates against job role id : {}", sendNotificationDto.getJobRoleId());

        //getting message
        String message = sendNotificationDto.getMessage();

        //getting user id from session
        Log.debug("Getting user id from session");
        int userId = GetLoggedInUserDetailsUtility.getUserIdFromSession();

        //adding message log in database
        Log.debug("Adding log for sms notification sent in db");
        int isAddingMessageLogInDBSuccess = sendNotificationDao.addLogForMessageInDB(sendNotificationDto,userId);
        if (isAddingMessageLogInDBSuccess != 1)
            return -25;
        Log.debug("Log for sms notification added in db");

        //fetching Arraylist of mobile number of candidates
        Log.debug("Fetching Arraylist of mobile number of candidates against job role id : {}", sendNotificationDto.getJobRoleId());
        ArrayList<Long> mobileNumbers = sendNotificationDao.getCandidateMobileNumbersForJobRole(sendNotificationDto.getJobRoleId());
        if (Objects.isNull(mobileNumbers)) {
            Log.error("Mobile Number list of candidates is empty");
            return -25;
        }
        if (mobileNumbers.size() == 0) {
            Log.debug("No candidates found against this job role");
            return -65;
        }

        //sending messages
        Log.debug("SMS notification messages will be sent in another thread");
        new Thread(new Runnable() {
            public void run() {
                Log.debug("SMS notification messages are being sent asynchronously");
                int deliveredCount = 0, notDeliveredCount = 0, deliverySuccess = 0;
                for (long mobileNumber : mobileNumbers) {
                    deliverySuccess = sendOtpUtility.sendOTP(message, mobileNumber);
                    //counting delivered messages v/s undelivered messages
                    if (deliverySuccess == 1)
                        deliveredCount++;
                    else
                        notDeliveredCount++;
                }
                Log.debug("Notification SMS Messages sent. Total count of messages : {}, delivered : {}, undelivered : {} ", mobileNumbers.size(), deliveredCount, notDeliveredCount);
            }
        }).start();

        Log.debug("Returning success to frontend, while messages are still being sent");
        return 1;

    }
}
