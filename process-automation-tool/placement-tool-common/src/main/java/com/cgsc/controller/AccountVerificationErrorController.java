package com.cgsc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class AccountVerificationErrorController {

	private static final Logger Log = LoggerFactory.getLogger(AccountVerificationErrorController.class);
	
	/**
	 * @author - Jyoti Singh
	 * @since - 21 Oct,2020
	 * @apiNote Method to return Account verification error page
	 * @return HTML file name for account verification error
	 */
	@RequestMapping("/accountVerificationError")
	public String ViewAccountVerifiedPage() {
		Log.debug("Request received in controller to return the account could not be verified error page");
		return "accountVerificationError";
	}
}
