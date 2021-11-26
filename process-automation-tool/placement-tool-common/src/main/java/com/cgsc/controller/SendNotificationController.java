package com.cgsc.controller;

import com.cgsc.common.Privilege;
import com.cgsc.dto.SendNotificationDto;
import com.cgsc.service.SendNotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendNotificationController {

    private static final Logger Log = LoggerFactory.getLogger(SendNotificationController.class);

    @Autowired
    SendNotificationService sendNotificationService;

    /**
     * @author Sarthak Bhutani
     * @since 16/12/2020
     * Send custom notification to all the candidates who have enrolled in a particular job role
     * @param sendNotificationDto
     * @return 1 if sucess,-25 in case of exception, -65 in case no candidates are found for a job role
     */
    @Privilege(value={"Admin"})
    @PostMapping(value="/sendNotification",consumes= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public int sendNotification(@RequestBody SendNotificationDto sendNotificationDto){
        Log.debug("Request received by controller to send sms notifications to candidates against job role id : {}",sendNotificationDto.getJobRoleId());
        return sendNotificationService.sendNotification(sendNotificationDto);
    }

}
