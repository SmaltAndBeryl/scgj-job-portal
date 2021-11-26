package com.cgsc.dto;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class SessionManagementDto implements UserDetails
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer userId;
	private String name;
	private Long mobileNumber;
	private String otp;
	private String userRole;
	private Boolean isEnabled;
	private Collection<SimpleGrantedAuthority> authorities;
	
	/**
	 * @author Prateek Kapoor
	 * @param userId
	 * @param emailAddress
	 * @param mobileNumber
	 * @param otp
	 * @param userRole
	 * @param isEnabled
	 * @param authorities
	 */
	public SessionManagementDto(Integer userId, String name, Long mobileNumber, String otp, String userRole,
			Boolean isEnabled) 
	{
		super();
		this.userId = userId;
		this.name = name;
		this.mobileNumber = mobileNumber;
		this.otp = otp;
		this.userRole = userRole;
		this.isEnabled = isEnabled;
		authorities = new ArrayList<SimpleGrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(userRole));
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return otp;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return isEnabled;
	}
	
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return name;
	}

	public Integer getUserId() {
		return userId;
	}

	public String getOtp() {
		return otp;
	}

	public String getUserRole() {
		return userRole;
	}

	public boolean getIsEnabled() {
		return isEnabled;
	}

	public long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
}
