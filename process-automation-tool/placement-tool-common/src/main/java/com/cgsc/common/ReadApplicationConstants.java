package com.cgsc.common;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:applicationConstants.properties")
@ConfigurationProperties(prefix="constant")
public class ReadApplicationConstants
{

	private String localProfile;
	private static String temporaryDirectory;
	private static String contentTypePng;
	private static String contentTypeZip;
	private static String contentTypePdf;
	private static String pngFileExtension;
	private static String jpegFileExtension;
	private static String zipFileExtension;
	private static String pdfFileExtension;
	private static String userRoleEmployer;
	private static String userRoleAdmin;
	private static String userRoleCandidate;
	private static String trueFlag;
	private static String falseFlag;
	private static String candidateDocumentsFolder;
	private static String fileDelimeter;
	private static String resumeText;
	private static String certificateText;
	private static String spaceReplacementChar;
	private static String otpMessage;
	private static String jobIdPrefix;
	private static int jobIdInitialCount;
	private static String descriptionText;
	private static String jobPostingsFolderPath;
	private static String notPublishedState;
	private static String publishedState;
	private static String closedState;
	private static String dateWithMonthNameFormat;
	private static String dateTimeFormat;
	private static String dateFormat;
	private static String applicationStateInReview;
	private static String applicationStateShortlisted;
	private static String applicationStateRejected;
	private static String maleGender;
	private static String femaleGender;
	private static String otherGender;
	private static String cgscLogoPath;
	private static String jobApplicationSummaryReportName;
	private static String encryptionKey;
	private static String verificationLinkSMSBody;
	private static Integer minPollCount;
	private static String approvedFlag;
	private static String inReviewFlag;
	private static String rejectedFlag;
	private static String reportFormatPdf;
	private static String reportFormatExcel;
	private static String xlsFileExtension;
	private static String toConstantText;
	private static String placementReport;
	private static String enrolmentStatusApproved;
	private static String enrolmentStatusRejected;
	private static String enrolmentStatusInReview;
	private static String statusActive;
	private static String statusInActive;
	private static String commaChar;
	private static String hyphenChar;
	private static String notApplicableText;
	private static String exceptionText;
	private static String applicationStateHired;
	private static String dateFormatyyyyMMdd;
	private static String offerLetterPdfName;
	private static String hiringsFolderName;
	private static String offerLetterFolderName;
	private static String jobApprovalStatusApproved;
	private static String jobApprovalStatusRejected;
	private static String jobApprovalStatusInReview;
	private static String maxPollCount;
	private static String publishJobPostPage;
	private static String closeJobPostPage;
	private static String candidateReport;
	private static String companyReport;

	public static String getMaxPollCount() {
		return maxPollCount;
	}

	public static void setMaxPollCount(String maxPollCount) {
		ReadApplicationConstants.maxPollCount = maxPollCount;
	}

	public static String getJobApprovalStatusApproved() {
		return jobApprovalStatusApproved;
	}

	public static void setJobApprovalStatusApproved(String jobApprovalStatusApproved) {
		ReadApplicationConstants.jobApprovalStatusApproved = jobApprovalStatusApproved;
	}

	public static String getJobApprovalStatusRejected() {
		return jobApprovalStatusRejected;
	}

	public static void setJobApprovalStatusRejected(String jobApprovalStatusRejected) {
		ReadApplicationConstants.jobApprovalStatusRejected = jobApprovalStatusRejected;
	}

	public static String getJobApprovalStatusInReview() {
		return jobApprovalStatusInReview;
	}

	public static void setJobApprovalStatusInReview(String jobApprovalStatusInReview) {
		ReadApplicationConstants.jobApprovalStatusInReview = jobApprovalStatusInReview;
	}
	
	public static String getHiringsFolderName() {
		return hiringsFolderName;
	}

	public static void setHiringsFolderName(String hiringsFolderName) {
		ReadApplicationConstants.hiringsFolderName = hiringsFolderName;
	}

	public static String getOfferLetterFolderName() {
		return offerLetterFolderName;
	}

	public static void setOfferLetterFolderName(String offerLetterFolderName) {
		ReadApplicationConstants.offerLetterFolderName = offerLetterFolderName;
	}

	public static String getOfferLetterPdfName() {
		return offerLetterPdfName;
	}

	public static void setOfferLetterPdfName(String offerLetterPdfName) {
		ReadApplicationConstants.offerLetterPdfName = offerLetterPdfName;
	}

	public static String getApplicationStateHired() {
		return applicationStateHired;
	}

	public static void setApplicationStateHired(String applicationStateHired) {
		ReadApplicationConstants.applicationStateHired = applicationStateHired;
	}

	public static String getExceptionText() {
		return exceptionText;
	}

	public static void setExceptionText(String exceptionText) {
		ReadApplicationConstants.exceptionText = exceptionText;
	}

	public static String getStatusActive() {
		return statusActive;
	}

	public static void setStatusActive(String statusActive) {
		ReadApplicationConstants.statusActive = statusActive;
	}

	public static String getStatusInActive() {
		return statusInActive;
	}

	public static void setStatusInActive(String statusInActive) {
		ReadApplicationConstants.statusInActive = statusInActive;
	}

	public static String getEnrolmentStatusApproved() {
		return enrolmentStatusApproved;
	}

	public static void setEnrolmentStatusApproved(String enrolmentStatusApproved) {
		ReadApplicationConstants.enrolmentStatusApproved = enrolmentStatusApproved;
	}

	public static String getEnrolmentStatusRejected() {
		return enrolmentStatusRejected;
	}

	public static void setEnrolmentStatusRejected(String enrolmentStatusRejected) {
		ReadApplicationConstants.enrolmentStatusRejected = enrolmentStatusRejected;
	}

	public static String getEnrolmentStatusInReview() {
		return enrolmentStatusInReview;
	}

	public static void setEnrolmentStatusInReview(String enrolmentStatusInReview) {
		ReadApplicationConstants.enrolmentStatusInReview = enrolmentStatusInReview;
	}

	public static String getToConstantText() {
		return toConstantText;
	}

	public static void setToConstantText(String toConstantText) {
		ReadApplicationConstants.toConstantText = toConstantText;
	}

	public static String getXlsFileExtension() {
		return xlsFileExtension;
	}

	public static void setXlsFileExtension(String xlsFileExtension) {
		ReadApplicationConstants.xlsFileExtension = xlsFileExtension;
	}

	public static String getReportFormatPdf() {
		return reportFormatPdf;
	}

	public static String getReportFormatExcel() {
		return reportFormatExcel;
	}

	public static void setReportFormatPdf(String reportFormatPdf) {
		ReadApplicationConstants.reportFormatPdf = reportFormatPdf;
	}

	public static void setReportFormatExcel(String reportFormatExcel) {
		ReadApplicationConstants.reportFormatExcel = reportFormatExcel;
	}

	public static Integer getMinPollCount() {
		return minPollCount;
	}

	public static void setMinPollCount(Integer minPollCount) {
		ReadApplicationConstants.minPollCount = minPollCount;
	}

	public static String getVerificationLinkSMSBody() {
		return verificationLinkSMSBody;
	}

	public static void setVerificationLinkSMSBody(String verificationLinkSMSBody) {
		ReadApplicationConstants.verificationLinkSMSBody = verificationLinkSMSBody;
	}

	public static String getEncryptionKey() {
		return encryptionKey;
	}

	public static void setEncryptionKey(String encryptionKey) {
		ReadApplicationConstants.encryptionKey = encryptionKey;
	}

	public static String getJobApplicationSummaryReportName() {
		return jobApplicationSummaryReportName;
	}

	public static void setJobApplicationSummaryReportName(String jobApplicationSummaryReportName) {
		ReadApplicationConstants.jobApplicationSummaryReportName = jobApplicationSummaryReportName;
	}

	public static String getCgscLogoPath() {
		return cgscLogoPath;
	}

	public static void setCgscLogoPath(String cgscLogoPath) {
		ReadApplicationConstants.cgscLogoPath = cgscLogoPath;
	}

	public static String getMaleGender() {
		return maleGender;
	}

	public static String getFemaleGender() {
		return femaleGender;
	}

	public static String getOtherGender() {
		return otherGender;
	}

	public static void setMaleGender(String maleGender) {
		ReadApplicationConstants.maleGender = maleGender;
	}

	public static void setFemaleGender(String femaleGender) {
		ReadApplicationConstants.femaleGender = femaleGender;
	}

	public static void setOtherGender(String otherGender) {
		ReadApplicationConstants.otherGender = otherGender;
	}

	public static String getNotPublishedState() {
		return notPublishedState;
	}

	public static String getPublishedState() {
		return publishedState;
	}

	public static String getClosedState() {
		return closedState;
	}

	public static void setNotPublishedState(String notPublishedState) {
		ReadApplicationConstants.notPublishedState = notPublishedState;
	}

	public static void setPublishedState(String publishedState) {
		ReadApplicationConstants.publishedState = publishedState;
	}

	public static void setClosedState(String closedState) {
		ReadApplicationConstants.closedState = closedState;
	}

	public static String getJobPostingsFolderPath() {
		return jobPostingsFolderPath;
	}

	public static void setJobPostingsFolderPath(String jobPostingsFolderPath) {
		ReadApplicationConstants.jobPostingsFolderPath = jobPostingsFolderPath;
	}

	public static String getDescriptionText() {
		return descriptionText;
	}

	public static void setDescriptionText(String descriptionText) {
		ReadApplicationConstants.descriptionText = descriptionText;
	}

	public static String getJobIdPrefix() {
		return jobIdPrefix;
	}

	public static int getJobIdInitialCount() {
		return jobIdInitialCount;
	}

	public static void setJobIdPrefix(String jobIdPrefix) {
		ReadApplicationConstants.jobIdPrefix = jobIdPrefix;
	}

	public static void setJobIdInitialCount(int jobIdInitialCount) {
		ReadApplicationConstants.jobIdInitialCount = jobIdInitialCount;
	}

	public static String getOtpMessage() {
		return otpMessage;
	}

	public static void setOtpMessage(String otpMessage) {
		ReadApplicationConstants.otpMessage = otpMessage;
	}

	public static String getSpaceReplacementChar() {
		return spaceReplacementChar;
	}

	public static void setSpaceReplacementChar(String spaceReplacementChar) {
		ReadApplicationConstants.spaceReplacementChar = spaceReplacementChar;
	}

	public static String getFileDelimeter() {
		return fileDelimeter;
	}

	public static void setFileDelimeter(String fileDelimeter) {
		ReadApplicationConstants.fileDelimeter = fileDelimeter;
	}

	public static String getCandidateDocumentsFolder() {
		return candidateDocumentsFolder;
	}

	public static void setCandidateDocumentsFolder(String candidateDocumentsFolder) {
		ReadApplicationConstants.candidateDocumentsFolder = candidateDocumentsFolder;
	}

	public static String getUserRoleEmployer() {
		return userRoleEmployer;
	}

	public static String getUserRoleAdmin() {
		return userRoleAdmin;
	}

	public static String getUserRoleCandidate() {
		return userRoleCandidate;
	}

	public static String getTrueFlag() {
		return trueFlag;
	}

	public static String getFalseFlag() {
		return falseFlag;
	}

	public static void setUserRoleEmployer(String userRoleEmployer) {
		ReadApplicationConstants.userRoleEmployer = userRoleEmployer;
	}

	public static void setUserRoleAdmin(String userRoleAdmin) {
		ReadApplicationConstants.userRoleAdmin = userRoleAdmin;
	}

	public static void setUserRoleCandidate(String userRoleCandidate) {
		ReadApplicationConstants.userRoleCandidate = userRoleCandidate;
	}

	public static void setTrueFlag(String trueFlag) {
		ReadApplicationConstants.trueFlag = trueFlag;
	}

	public static void setFalseFlag(String falseFlag) {
		ReadApplicationConstants.falseFlag = falseFlag;
	}

	public static String getContentTypePng() {
		return contentTypePng;
	}

	public static String getContentTypeZip() {
		return contentTypeZip;
	}

	public static String getContentTypePdf() {
		return contentTypePdf;
	}

	public static String getPngFileExtension() {
		return pngFileExtension;
	}

	public static String getJpegFileExtension() {
		return jpegFileExtension;
	}

	public static String getZipFileExtension() {
		return zipFileExtension;
	}

	public static String getPdfFileExtension() {
		return pdfFileExtension;
	}

	public static void setContentTypePng(String contentTypePng) {
		ReadApplicationConstants.contentTypePng = contentTypePng;
	}

	public static void setContentTypeZip(String contentTypeZip) {
		ReadApplicationConstants.contentTypeZip = contentTypeZip;
	}

	public static void setContentTypePdf(String contentTypePdf) {
		ReadApplicationConstants.contentTypePdf = contentTypePdf;
	}

	public static void setPngFileExtension(String pngFileExtension) {
		ReadApplicationConstants.pngFileExtension = pngFileExtension;
	}

	public static void setJpegFileExtension(String jpegFileExtension) {
		ReadApplicationConstants.jpegFileExtension = jpegFileExtension;
	}

	public static void setZipFileExtension(String zipFileExtension) {
		ReadApplicationConstants.zipFileExtension = zipFileExtension;
	}

	public static void setPdfFileExtension(String pdfFileExtension) {
		ReadApplicationConstants.pdfFileExtension = pdfFileExtension;
	}

	public String getLocalProfile() {
		return this.localProfile;
	}

	public void setLocalProfile(String localProfile) {
		this.localProfile = localProfile;
	}

	public static String getTemporaryDirectory() {
		return temporaryDirectory;
	}

	public static void setTemporaryDirectory(String temporaryDirectory) {
		ReadApplicationConstants.temporaryDirectory = temporaryDirectory;
	}

	public static String getResumeText() {
		return resumeText;
	}

	public static String getCertificateText() {
		return certificateText;
	}

	public static void setResumeText(String resumeText) {
		ReadApplicationConstants.resumeText = resumeText;
	}

	public static void setCertificateText(String certificateText) {
		ReadApplicationConstants.certificateText = certificateText;
	}

	public static String getDateWithMonthNameFormat() {
		return dateWithMonthNameFormat;
	}

	public static void setDateWithMonthNameFormat(String dateWithMonthNameFormat) {
		ReadApplicationConstants.dateWithMonthNameFormat = dateWithMonthNameFormat;
	}

	public static String getDateTimeFormat() {
		return dateTimeFormat;
	}

	public static void setDateTimeFormat(String dateTimeFormat) {
		ReadApplicationConstants.dateTimeFormat = dateTimeFormat;
	}

	public static String getDateFormat() {
		return dateFormat;
	}
	public static void setDateFormat(String dateFormat) {
		ReadApplicationConstants.dateFormat = dateFormat;
	}

	public static String getApplicationStateInReview() {
		return applicationStateInReview;
	}

	public static String getApplicationStateShortlisted() {
		return applicationStateShortlisted;
	}

	public static String getApplicationStateRejected() {
		return applicationStateRejected;
	}

	public static void setApplicationStateInReview(String applicationStateInReview) {
		ReadApplicationConstants.applicationStateInReview = applicationStateInReview;
	}

	public static void setApplicationStateShortlisted(String applicationStateShortlisted) {
		ReadApplicationConstants.applicationStateShortlisted = applicationStateShortlisted;
	}

	public static void setApplicationStateRejected(String applicationStateRejected) {
		ReadApplicationConstants.applicationStateRejected = applicationStateRejected;
	}
	public static String getApprovedFlag() {
		return approvedFlag;
	}

	public static String getInReviewFlag() {
		return inReviewFlag;
	}

	public static String getRejectedFlag() {
		return rejectedFlag;
	}

	public static void setApprovedFlag(String approvedFlag) {
		ReadApplicationConstants.approvedFlag = approvedFlag;
	}

	public static void setInReviewFlag(String inReviewFlag) {
		ReadApplicationConstants.inReviewFlag = inReviewFlag;
	}

	public static void setRejectedFlag(String rejectedFlag) {
		ReadApplicationConstants.rejectedFlag = rejectedFlag;
	}

	public static String getPlacementReport() {
		return placementReport;
	}

	public static void setPlacementReport(String placementReport) {
		ReadApplicationConstants.placementReport = placementReport;
	}

//	public static String getJobsGeneratedReportName() {
//		return jobsGeneratedReportName;
//	}
//
//	public static void setJobsGeneratedReportName(String jobsGeneratedReportName) {
//		ReadApplicationConstants.jobsGeneratedReportName = jobsGeneratedReportName;
//	}

	public static String getCommaChar() {
		return commaChar;
	}

	public static void setCommaChar(String commaChar) {
		ReadApplicationConstants.commaChar = commaChar;
	}

	public static String getHyphenChar() {
		return hyphenChar;
	}

	public static void setHyphenChar(String hyphenChar) {
		ReadApplicationConstants.hyphenChar = hyphenChar;
	}

	public static String getNotApplicableText() {
		return notApplicableText;
	}

	public static void setNotApplicableText(String notApplicableText) {
		ReadApplicationConstants.notApplicableText = notApplicableText;
	}

	public static String getDateFormatyyyyMMdd() {
		return dateFormatyyyyMMdd;
	}

	public static void setDateFormatyyyyMMdd(String dateFormatyyyyMMdd) {
		ReadApplicationConstants.dateFormatyyyyMMdd = dateFormatyyyyMMdd;
	}

	public static String getPublishJobPostPage() {
		return publishJobPostPage;
	}

	public static void setPublishJobPostPage(String publishJobPostPage) {
		ReadApplicationConstants.publishJobPostPage = publishJobPostPage;
	}

	public static String getCloseJobPostPage() {
		return closeJobPostPage;
	}

	public static void setCloseJobPostPage(String closeJobPostPage) {
		ReadApplicationConstants.closeJobPostPage = closeJobPostPage;
	}

	public static String getCandidateReport() {
		return candidateReport;
	}

	public static void setCandidateReport(String candidateReport) {
		ReadApplicationConstants.candidateReport = candidateReport;
	}

	public static String getCompanyReport() {
		return companyReport;
	}

	public static void setCompanyReport(String companyReport) {
		ReadApplicationConstants.companyReport = companyReport;
	}

}
