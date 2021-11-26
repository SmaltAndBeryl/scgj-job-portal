package com.cgsc.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cgsc.dto.SessionManagementDto;
import com.cgsc.dto.UserDetailsDto;
import com.cgsc.service.LoginService;

@RestController	
public class LoginController 
{

	private static final Logger Log = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private LoginService loginService;
	
	
	/**
	 *@author Prateek Kapoor
	 *@since 08-10-2020
	 *@param user
	 *@return {@link UserDetailsDto} object
	 */
	@RequestMapping("/user")
	public UserDetailsDto loginAuthentication(Principal user)
	{
		Log.debug("Request received from frontend to authenticate the user");
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		try
		{
			Log.debug("In try block to authenticate the user");
			 SessionManagementDto sessionDto = (SessionManagementDto)(((UsernamePasswordAuthenticationToken)user).getPrincipal());
			 for(GrantedAuthority auth : sessionDto.getAuthorities())
			 {
				 authorities.add((SimpleGrantedAuthority)auth);
			 }
			 UserDetailsDto userDetailsDto = new UserDetailsDto(sessionDto.getUsername(),authorities,sessionDto.isEnabled(),sessionDto.getUserId(),sessionDto.getUserRole(), sessionDto.getMobileNumber());
			 Log.debug("Returning the user object to the front end {} ",userDetailsDto);
			 return userDetailsDto;
		}
		catch(Exception e)
		{
			Log.error("An exception occured while trying to authenticate the user: " +e);
			return null;
		}
	}
	
	/**
	 * @author Prateek Kapoor
	 * @since 08-10-2020
	 * Method to generate and store OTP for the user who is trying to login
	 * @param mobileNumber
	 * @return 1 in case of success; -25 in case of exception; -55 if mobile number does not exists; -66 if user is inactive
	 */
	@GetMapping("/requestOtp")
	public int generateOtp(@RequestParam("mobileNumber") long mobileNumber)
	{ 
		Log.debug("Request received in controller to generate the otp for user with mobile number {}",mobileNumber);
		return loginService.generateOtp(mobileNumber);
	}
	
}

