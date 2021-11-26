package com.cgsc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.HandlerMapping;

import com.cgsc.service.VerifyCandidateStatusService;

@Controller
public class VerifyCandidateStatusController {

	private static final Logger Log = LoggerFactory.getLogger(VerifyCandidateStatusController.class);
	
	@Autowired
	private VerifyCandidateStatusService verifyCandidateStatusService;
	
	/**
	 * @author Jyoti Singh
	 * @since 21-10-2020
	 * 
	 * This method receives the encrypted candidateId to update the poll count of candidate
	 * redirects to account verified html page on success,else account not verified html page
	 * 
	 * @param encrypted candidateId
	 */
	
	@GetMapping("/verifyUserAccount/{userId}/**")
	public String updatePollCount(@PathVariable String userId, HttpServletResponse response, HttpServletRequest request) 
	{
		Log.debug("Request received in controller to update the candidate's poll count");
		Log.debug("The encrypted candidate Id received from Url is - {}",userId);
		
		final String path =  request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE).toString();
	    final String bestMatchingPattern = request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE).toString();
	    String userIdSubstring = new AntPathMatcher().extractPathWithinPattern(bestMatchingPattern, path);
	    String encryptedCandidateId;
	    if (userIdSubstring != null && !userIdSubstring.isEmpty()) 
	    {
	    	encryptedCandidateId = userId + '/' + userIdSubstring;
	    }
	    else 
	    {
	    	encryptedCandidateId = userId;
	    }
		Log.debug("Sending request to service to update the poll count of candidate with encrypted Id - {}" , encryptedCandidateId);		
	    int isCandidateVerified = verifyCandidateStatusService.updatePollCount(encryptedCandidateId);
		
	    if(isCandidateVerified == 1) {
	    	Log.debug("The candidate poll count was successfully updated, returning account verified html page");
	    	return "redirect:/accountVerified";
	    }

	    Log.debug("An exception occured while updating candidate poll count, returning account not verified html page");
		return "redirect:/accountVerificationError";
	}
}
