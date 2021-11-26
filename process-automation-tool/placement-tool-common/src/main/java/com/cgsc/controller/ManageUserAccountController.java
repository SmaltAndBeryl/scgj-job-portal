package com.cgsc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cgsc.common.Privilege;
import com.cgsc.service.ManageUserAccountService;

@RestController
public class ManageUserAccountController 
{

	private static final Logger Log = LoggerFactory.getLogger(ManageUserAccountController.class);
	
	@Autowired
	private ManageUserAccountService manageUserAccountService;
	
	/**
	 * @author Prateek Kapoor
	 * @since 29-10-2020
	 * This method updates the user account status (represented by is_active_flg) for the received userId
	 * @param userId
	 * @param updatedStatus
	 * @return 1 if success;
	 * -66 in case of invalid status
	 * -25 in case of exception
	 */
	@Privilege(value = {"Admin"})
	@GetMapping("/updateCandidateStatus")
	public int updateUserAccountStatus(@RequestParam("userId") int userId, @RequestParam("updatedStatus") String updatedStatus)
	{
		Log.debug("Request received in controller to update the account status of the user with id {} to {}",userId, updatedStatus);
		return manageUserAccountService.updateUserAccountStatus(userId, updatedStatus);
	}
}
