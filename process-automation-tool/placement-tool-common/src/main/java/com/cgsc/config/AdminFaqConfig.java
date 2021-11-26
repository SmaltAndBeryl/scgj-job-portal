package com.cgsc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:sql/adminFaq.yml")
@ConfigurationProperties(prefix="adminFaq")
public class AdminFaqConfig {
	
	@Value("${getTotalJobsPostedByEmployer}")
	private String getTotalJobsPostedByEmployerSql;
	
	@Value("${companyWiseHiredShortlistedCandidates}")
	private String companyWiseHiredShortlistedCandidatesSql;

	@Value("${employersDetailsWithJobCount}")
	private String employersDetailsWithJobCountSql;
	
	@Value("${countJobroleSpecificHiredCandidates}")
	private String countJobroleSpecificHiredCandidatesSql;
	
	@Value("${countStateWiseHiredCandidates}")
	private String countStateWiseHiredCandidatesSql;
	
	@Value("${countJobroleSpecificJobPostings}")
	private String countJobroleSpecificJobPostingsSql;
	
	@Value("${countHiredCGSCCertifiedCandidates}")
	private String countHiredCGSCCertifiedCandidatesSql;
	
	@Value("${countInactiveEmployers}")
	private String countInactiveEmployersSql;

	@Value("${getHiredCandidateCount}")
	private String getHiredCandidateCount;
	
	@Value("${countTotalVacanciesInState}")
	private String countTotalVacanciesInState;
	
	public String getCountInactiveEmployersSql() {
		return countInactiveEmployersSql;
	}
	public void setCountInactiveEmployersSql(String countInactiveEmployersSql) {
		this.countInactiveEmployersSql = countInactiveEmployersSql;
	}
	public String getCountHiredCGSCCertifiedCandidatesSql() {
		return countHiredCGSCCertifiedCandidatesSql;
	}
	public void setCountHiredCGSCCertifiedCandidatesSql(String countHiredCGSCCertifiedCandidatesSql) {
		this.countHiredCGSCCertifiedCandidatesSql = countHiredCGSCCertifiedCandidatesSql;
	}

	public String getCountJobroleSpecificJobPostingsSql() {
		return countJobroleSpecificJobPostingsSql;
	}
	public void setCountJobroleSpecificJobPostingsSql(String countJobroleSpecificJobPostingsSql) {
		this.countJobroleSpecificJobPostingsSql = countJobroleSpecificJobPostingsSql;
	}
	public String getCountStateWiseHiredCandidatesSql() {
		return countStateWiseHiredCandidatesSql;
	}
	public void setCountStateWiseHiredCandidatesSql(String countStateWiseHiredCandidatesSql) {
		this.countStateWiseHiredCandidatesSql = countStateWiseHiredCandidatesSql;
	}
	public String getCountJobroleSpecificHiredCandidatesSql() {
		return countJobroleSpecificHiredCandidatesSql;
	}
	public void setCountJobroleSpecificHiredCandidatesSql(String countJobroleSpecificHiredCandidatesSql) {
		this.countJobroleSpecificHiredCandidatesSql = countJobroleSpecificHiredCandidatesSql;
	}
	public String getEmployersDetailsWithJobCountSql() {
		return employersDetailsWithJobCountSql;
	}
	public void setEmployersDetailsWithJobCountSql(String employersDetailsWithJobCountSql) {
		this.employersDetailsWithJobCountSql = employersDetailsWithJobCountSql;
	}
	public String getCompanyWiseHiredShortlistedCandidatesSql() {
		return companyWiseHiredShortlistedCandidatesSql;
	}
	public void setCompanyWiseHiredShortlistedCandidatesSql(String companyWiseHiredShortlistedCandidatesSql) {
		this.companyWiseHiredShortlistedCandidatesSql = companyWiseHiredShortlistedCandidatesSql;
	}
	public String getGetTotalJobsPostedByEmployerSql() {
		return getTotalJobsPostedByEmployerSql;
	}
	public void setGetTotalJobsPostedByEmployerSql(String getTotalJobsPostedByEmployerSql) {
		this.getTotalJobsPostedByEmployerSql = getTotalJobsPostedByEmployerSql;
	}
	public String getCountTotalVacanciesInState() {
		return countTotalVacanciesInState;
	}
	public void setCountTotalVacanciesInState(String countTotalVacanciesInState) {
		this.countTotalVacanciesInState = countTotalVacanciesInState;
	}

	public String getGetHiredCandidateCount() {
		return getHiredCandidateCount;
	}

	public void setGetHiredCandidateCount(String getHiredCandidateCount) {
		this.getHiredCandidateCount = getHiredCandidateCount;
	}
}
