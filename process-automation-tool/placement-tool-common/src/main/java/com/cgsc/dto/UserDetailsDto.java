package com.cgsc.dto;

import java.util.Collection;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.cgsc.common.BaseDto;

public class UserDetailsDto extends BaseDto
{

	private static final long serialVersionUID = 1L;
	private String userName;
	private Collection<SimpleGrantedAuthority> authorities;
	private Boolean isEnabled;
	private Integer userId;
	private String role;
	private Long mobileNumber;
	

	public String getUserName() {
		return userName;
	}
	public Collection<SimpleGrantedAuthority> getAuthorities() {
		return authorities;
	}
	public boolean isEnabled() {
		return isEnabled;
	}
	public Integer getUserId() {
		return userId;
	}
	public String getRole() {
		return role;
	}
	public long getMobileNumber() {
		return mobileNumber;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setAuthorities(Collection<SimpleGrantedAuthority> authorities) {
		this.authorities = authorities;
	}
	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	/**
	 * @author Prateek Kapoor
	 * @param userEmail
	 * @param authorities
	 * @param isEnabled
	 * @param userId
	 * @param role
	 * @param mobileNumber
	 * @param name
	 */
	public UserDetailsDto(String userName, Collection<SimpleGrantedAuthority> authorities, Boolean isEnabled,
			Integer userId, String role, Long mobileNumber) {
		super();
		this.userName = userName;
		this.authorities = authorities;
		this.isEnabled = isEnabled;
		this.userId = userId;
		this.role = role;
		this.mobileNumber = mobileNumber;
	}
	public UserDetailsDto() {
		super();
		// TODO Auto-generated constructor stub
	}
}
