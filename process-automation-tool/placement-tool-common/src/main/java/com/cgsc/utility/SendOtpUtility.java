package com.cgsc.utility;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.cgsc.common.AmazonClient;
import com.cgsc.common.ReadProfileProperties;

@RestController
public class SendOtpUtility 
{
	
	private static final Logger Log = LoggerFactory.getLogger(SendOtpUtility.class);
	@Autowired
	private AmazonClient amazonClient;
	@Autowired
	private ReadProfileProperties readProfileProperties;
	/**
	 * @author Prateek Kapoor
	 * This method sends SMS to the mobile number received from the calling method through AWS SNS
	 * @since 28-09-2020
	 * @param messageBody
	 * @param mobileNumber.toString
	 * @return 1 in case of success; 
	 * -25 in case of exception
	 */
	public int sendOTP(String messageBody, long mobileNumber)
	{
		Log.debug("Request received in utility method to send sms to mobile number {}",mobileNumber);
		Map<String, MessageAttributeValue> smsAttributes = new HashMap<String, MessageAttributeValue>();
		Log.debug("Setting the mesasge attributes");
		try 
		{
			smsAttributes.put("AWS.SNS.SMS.SenderID",new MessageAttributeValue().withStringValue(readProfileProperties.getSenderId()).withDataType("String"));
			smsAttributes.put("AWS.SNS.SMS.SMSType",new MessageAttributeValue().withStringValue("Transactional").withDataType("String"));
			PublishResult publishResult = amazonClient.snsClient.publish(new PublishRequest().withMessage(messageBody).withPhoneNumber(Long.toString(mobileNumber)).withMessageAttributes(smsAttributes));
			Log.debug("The message id of published message is {}",publishResult.getMessageId());
			return 1;
		}
		catch (Exception e) 
		{
			Log.error("Could not send OTP to the mobile number {}, the exception is - ",mobileNumber,e);
			Log.error("Returning -25 to the controller");
			return -25;
		}
	}
}
