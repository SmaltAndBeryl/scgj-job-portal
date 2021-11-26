package com.cgsc.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Component;

@Component
@PropertySources(value = { 
		@PropertySource("classpath:application.properties"),
		@PropertySource("classpath:applicationOverride.properties")
		})
public class ReadProfileProperties {

	/**S3 Information**/
	@Value("${s3.bucket.fqdn}")
	private String bucketFqdn;
	@Value("${s3.bucket.name}")
	private String bucketName;	
	@Value("${sns.senderId}")
	private String senderId;
	@Value("${server.fqdn}")
	private String verifyAccountFqdn;
	@Value("${polling.frequency}")
	private String pollingFrequency;
	@Value("${reports.cgscLogo}")
	private String cgscLogoPath;
	@Value("${reports.jobApplicationSummary}")
	private String jobApplicationSummaryReportPath;
	@Value("${reports.jobApplicationSummaryExcel}")
	private String jobApplicationSummaryExcelReportPath;
	@Value("${termsAndConditions}")
	private String termsAndConditions;
	@Value("${reports.candidatePlacementReportExcel}")
	private String candidatePlacementReportExcel;
	@Value("${deactivateCandidates.polling.frequency}")
	private String deactivateCandidatesFrequency;
	@Value("${reports.candidateReport}")
	private String candidateReport;
	@Value("${reports.companyReport}")
	private String companyReport;
	
	
	public String getDeactivateCandidatesFrequency() {
		return deactivateCandidatesFrequency;
	}
	public void setDeactivateCandidatesFrequency(String deactivateCandidatesFrequency) {
		this.deactivateCandidatesFrequency = deactivateCandidatesFrequency;
	}
	public String getCgscLogoPath() {
		return cgscLogoPath;
	}
	public String getJobApplicationSummaryReportPath() {
		return jobApplicationSummaryReportPath;
	}
	public void setCgscLogoPath(String cgscLogoPath) {
		this.cgscLogoPath = cgscLogoPath;
	}
	public void setJobApplicationSummaryReportPath(String jobApplicationSummaryReportPath) {
		this.jobApplicationSummaryReportPath = jobApplicationSummaryReportPath;
	}
	public String getPollingFrequency() {
		return pollingFrequency;
	}
	public void setPollingFrequency(String pollingFrequency) {
		this.pollingFrequency = pollingFrequency;
	}
	public String getVerifyAccountFqdn() {
		return verifyAccountFqdn;
	}
	public void setVerifyAccountFqdn(String verifyAccountFqdn) {
		this.verifyAccountFqdn = verifyAccountFqdn;
	}
	public String getSenderId() {
		return senderId;
	}
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}
	public String getBucketFqdn() {
		return bucketFqdn;
	}
	public String getBucketName() {
		return bucketName;
	}
	public void setBucketFqdn(String bucketFqdn) {
		this.bucketFqdn = bucketFqdn;
	}
	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}
	public String getJobApplicationSummaryExcelReportPath() {
		return jobApplicationSummaryExcelReportPath;
	}
	public void setJobApplicationSummaryExcelReportPath(String jobApplicationSummaryExcelReportPath) {
		this.jobApplicationSummaryExcelReportPath = jobApplicationSummaryExcelReportPath;
	}
	public String getTermsAndConditions() {
		return termsAndConditions;
	}
	public void setTermsAndConditions(String termsAndConditions) {
		this.termsAndConditions = termsAndConditions;
	}
	public String getCandidatePlacementReportExcel() {
		return candidatePlacementReportExcel;
	}
	public void setCandidatePlacementReportExcel(String candidatePlacementReportExcel) {
		this.candidatePlacementReportExcel = candidatePlacementReportExcel;
	}
	public String getCandidateReport() {
		return candidateReport;
	}
	public void setCandidateReport(String candidateReport) {
		this.candidateReport = candidateReport;
	}
	public String getCompanyReport() {
		return companyReport;
	}
	public void setCompanyReport(String companyReport) {
		this.companyReport = companyReport;
	}
	
}
