package com.cgsc.utility;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Prateek Kapoor
 *
 */
public class GenerateOtpUtility 
{

	private static final Logger Log = LoggerFactory.getLogger(GenerateOtpUtility.class);
	
	/**
	 * Method to generate OTP when user logs in on the platform
	 * @since 08-10-2020
	 * @return generatedOtp
	 */
	public static String generateOtp() 
	{
		Log.debug("Request received in utility method to generate a 6 digit OTP");
		Random randomNumber = new Random();
		Integer generatedOtp = randomNumber.nextInt(899999)+100000; //Setting the upper limit of OTP
		Log.debug("The generated OTP is {}",generatedOtp);
		return generatedOtp.toString();
	}
}
