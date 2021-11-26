package com.cgsc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class AccountVerifiedController {

	private static final Logger Log = LoggerFactory.getLogger(AccountVerifiedController.class);
	/**
	 * @author - Jyoti Singh
	 * @since - 21 Oct,2020
	 * @apiNote Method to return Account verified page
	 * @return HTML file name for account verified success
	 */
	@RequestMapping("/accountVerified")
	public String ViewAccountVerifiedPage() {
		Log.debug("Request received in controller to return the account verified page");
		return "accountVerified";
	}
}
