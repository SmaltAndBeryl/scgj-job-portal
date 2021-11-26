package com.cgsc.common;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// @formatter:off
		http
				.httpBasic().and()
				.authorizeRequests()
				.antMatchers("/","/js/**","/css/**","/getEmployers", "/viewTrainingPartners", "/requestOtp","/getJobRoles","/registerCandidates","/getProfessionalExperience","/getCompanyScales","/getIndustryTypes","/getEducationQualification","/getStates","/getCompanyType","/getOccupationByJobRole","/registerEmployer","/viewJobPost","/downloadPdfFile/{filePath}/**","/index","/favicon.ico","/js/**","/static","/images/**","/verifyUserAccount/{userId}/**","/accountVerified","/accountVerificationError","/getTermsAndConditions").permitAll()
				.anyRequest().authenticated()
				.and()
				.csrf()
				.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
		// @formatter:on
	}


//
// @Override
//    protected void configure(HttpSecurity http) throws Exception {
//     http.csrf().disable();   
//	 http.authorizeRequests().anyRequest().permitAll();
//    }
//

}
