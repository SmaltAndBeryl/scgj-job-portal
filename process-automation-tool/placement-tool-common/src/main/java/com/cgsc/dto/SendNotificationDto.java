package com.cgsc.dto;

import com.cgsc.common.BaseDto;


public class SendNotificationDto extends BaseDto {

    private static final long serialVersionUID = 1L;
    private int jobRoleId;
    private String message;

    public int getJobRoleId() {
        return jobRoleId;
    }

    public void setJobRoleId(int jobRoleId) {
        this.jobRoleId = jobRoleId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
