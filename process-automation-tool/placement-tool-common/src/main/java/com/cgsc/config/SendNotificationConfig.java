package com.cgsc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:sql/sendNotification.yml")
@ConfigurationProperties(prefix="sendNotification")
public class SendNotificationConfig {

    @Value("${getMobileNumbersAgainstJobRole}")
    private String getMobileNumbersAgainstJobRole;

    @Value("${addNotificationMessageLog}")
    private String addNotificationMessageLog;


    public String getGetMobileNumbersAgainstJobRole() {
        return getMobileNumbersAgainstJobRole;
    }

    public void setGetMobileNumbersAgainstJobRole(String getMobileNumbersAgainstJobRole) {
        this.getMobileNumbersAgainstJobRole = getMobileNumbersAgainstJobRole;
    }

    public String getAddNotificationMessageLog() {
        return addNotificationMessageLog;
    }

    public void setAddNotificationMessageLog(String addNotificationMessageLog) {
        this.addNotificationMessageLog = addNotificationMessageLog;
    }
}
