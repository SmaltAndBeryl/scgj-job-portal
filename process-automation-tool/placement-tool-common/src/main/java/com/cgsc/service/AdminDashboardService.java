package com.cgsc.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.model.S3Object;
import com.cgsc.common.AmazonClient;
import com.cgsc.common.ReadApplicationConstants;
import com.cgsc.common.ReadProfileProperties;
import com.cgsc.dao.AdminDashboardDao;
import com.cgsc.dto.CandidateReportDto;
import com.cgsc.dto.CandidatesPlacedDto;
import com.cgsc.dto.CompanyReportDto;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

@Service
public class AdminDashboardService {

    private static final Logger Log = LoggerFactory.getLogger(AdminDashboardService.class);

    @Autowired
    private AdminDashboardDao adminDashboardDao;

    @Autowired
    private ReadProfileProperties readProfileProperties;

    @Autowired
    private AmazonClient amazonClient;

    /**
     * @return if success : registeredEmployersCount ,in case of exception : -25
     * @author Sarthak Bhutani
     * @since 29/10/2020
     * Method to get number of active registered companies
     */
    public int getRegisteredEmployersCount() {
        Log.debug("Request recieved in service to get number of active registered companies");
        return adminDashboardDao.getRegisteredEmployersCount();
    }

    /**
     * @return if success : activeCandidatesCount ,in case of exception : -25
     * @author Sarthak Bhutani
     * @since 29/10/2020
     * Method to get number of active candidates
     */
    public int getActiveCandidatesCount() {
        Log.debug("Request recieved in service to get number of active candidates");
        return adminDashboardDao.getActiveCandidatesCount();
    }

    /**
     * @return if success : placedCandidatesCount ,in case of exception : -25
     * @author Sarthak Bhutani
     * @since 30/10/2020
     * Method to get the number of placed candidates
     */
    public int getPlacedCandidatesCount() {
        Log.debug("Request receieved in service to get the number of placed candidates");
        return adminDashboardDao.getPlacedCandidatesCount();
    }

    /**
     * @return if success : activeJobPostCount ,in case of exception : -25
     * @author Sarthak Bhutani
     * @since 30/10/2020
     * Method to get the number of active job posts
     */
    public int getActiveJobPostCount() {
        Log.debug("Request receieved in service to get the number of active job posts");
        return adminDashboardDao.getActiveJobPostCount();
    }

    /**
     * @param startDate
     * @param endDate
     * @return Path of generated report if success;
     * else returns null
     * @author Prateek Kapoor
     * @updated by - Jyoti Singh
     * @updated on- 16-12-2020
     * @update -
     * 1. removed expected parameter report format in API request
     * 2. Update to add the updated placement report details required in the report
     * 3. Removed code to fetch candidate placement summary (total placement, unique placement)
     * @since 03-11-2020
     * This method generates the candidate placement report for a given date range for jobs having status 'Published' or 'Closed'
     *
     * @updatedBy Sarthak Bhutani
     * @updatedOn 25/12/2020
     * @update Setting offerLetterPath as s3 bucked location + file path, when it is not null. Else, set offerLetterPath as null
     */
    public String generatePlacementReport(Date startDate, Date endDate) {
        Log.debug("Request received in service to generate the candidate placement report for date range - {} to {}", startDate, endDate);
        Log.debug("Sending request to fetch the candidate placement details");
        Collection<CandidatesPlacedDto> placementDetails = adminDashboardDao.candidatePlacementDetails(startDate, endDate);
        if (Objects.isNull(placementDetails)) {
            Log.error("Could not fetch the placement details, returning null to controller");
            return null;
        }
        if (CollectionUtils.isEmpty(placementDetails)) {
            Log.debug("No candidates have been placed in the given date range, returning null to controller");
            return null;
        }

        for (CandidatesPlacedDto placementDetailsDto : placementDetails) {
            if (!Objects.isNull(placementDetailsDto.getOfferLetterPath())) {
                placementDetailsDto.setOfferLetterPath(readProfileProperties.getBucketFqdn() + placementDetailsDto.getOfferLetterPath());
            }
            else {
                placementDetailsDto.setOfferLetterPath(null);
            }
        }

        Log.debug("Fetched the candidate placement details. Processing request to generate the candidate placement report");
        Log.debug("Inserting details into JR Bean Collection");
        JRBeanCollectionDataSource candidatePlacementDetails = new JRBeanCollectionDataSource(placementDetails);
        Map<String, Object> reportParameters = new HashMap<String, Object>(1);
        reportParameters.put("candidatePlacementDetails", candidatePlacementDetails);
        Log.debug("Hashmap created and parameters inserted into hashmap");
        Log.debug("Reading Jasper report file");
        String outputFile;
        try {
            Log.debug("Report format to be generated is xlsx. Processing request to generate the excel format for placement report");
            reportParameters.put(JRParameter.IS_IGNORE_PAGINATION, Boolean.TRUE); //Ignores pagination so that the table headers are not repeated in excel sheet
            outputFile = ReadApplicationConstants.getTemporaryDirectory() + ReadApplicationConstants.getPlacementReport() + ReadApplicationConstants.getXlsFileExtension();
            Log.debug("Getting input stream");

            S3Object s3Object = amazonClient.s3client.getObject(readProfileProperties.getBucketName(), readProfileProperties.getCandidatePlacementReportExcel());
            InputStream inputStream = s3Object.getObjectContent();
            JasperPrint printFileName = JasperFillManager.fillReport(inputStream, reportParameters, new JREmptyDataSource());
            OutputStream outputStream = new FileOutputStream(new File(outputFile));

            JRXlsxExporter exporter = new JRXlsxExporter();
            exporter.setExporterInput(new SimpleExporterInput(printFileName));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputFile));
            SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
            configuration.setRemoveEmptySpaceBetweenRows(true);
            configuration.setCollapseRowSpan(false);
            exporter.setConfiguration(configuration);
            exporter.exportReport();
            outputStream.close();
            inputStream.close();
            s3Object.close();
            Log.debug("Returning path with output file - {}", outputFile);
            return outputFile;
        } catch (Exception e) {
            Log.error("An exception handled while printing jasper report - Candidate placement Report " + e);
            return null;
        }

    }

    /**
     * @param startDate
     * @param endDate   This method generates the candidate report which contains all the details of candidates who have registered on the platform from startDate & endDate
     * @return path of generated report if success; else returns null
     * @author Prateek Kapoor
     * @since 21-12-2020
     */
    public String generateCandidateReport(Date startDate, Date endDate) {
        Log.debug("Request received in service to generate the candidate report having details from startDate {} to endDate {} ", startDate, endDate);
        Collection<CandidateReportDto> candidateDetails = adminDashboardDao.fetchCandidateReportDetails(startDate, endDate);
        if (Objects.isNull(candidateDetails)) {
            Log.error("An exception occurred while fetching the candidate details, returning null");
            return null;
        } else if (candidateDetails.isEmpty()) {
            Log.debug("No records found for candidate report in the selected time duration, returning null");
            return null;
        } else {
            JRBeanCollectionDataSource candidateReportDetails = new JRBeanCollectionDataSource(candidateDetails);
            Map<String, Object> reportParameters = new HashMap<String, Object>(1);
            reportParameters.put("candidateDetails", candidateReportDetails);
            Log.debug("Hashmap created and parameters inserted into hashmap");
            String outputFile;
            try {
                Log.debug("Processing request to generate the excel format for candidate report");
                outputFile = ReadApplicationConstants.getTemporaryDirectory() + ReadApplicationConstants.getCandidateReport() + ReadApplicationConstants.getXlsFileExtension();
                Log.debug("Getting input stream");

                S3Object s3Object = amazonClient.s3client.getObject(readProfileProperties.getBucketName(), readProfileProperties.getCandidateReport());
                InputStream inputStream = s3Object.getObjectContent();
                JasperPrint printFileName = JasperFillManager.fillReport(inputStream, reportParameters, new JREmptyDataSource());
                OutputStream outputStream = new FileOutputStream(new File(outputFile));

                JRXlsxExporter exporter = new JRXlsxExporter();
                exporter.setExporterInput(new SimpleExporterInput(printFileName));
                exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputFile));
                SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
                configuration.setRemoveEmptySpaceBetweenRows(true);
                configuration.setCollapseRowSpan(false);
                exporter.setConfiguration(configuration);
                exporter.exportReport();
                outputStream.close();
                inputStream.close();
                s3Object.close();
                Log.debug("Returning path with output file - {}", outputFile);
                return outputFile;
            } catch (Exception e) {
                Log.error("An exception handled while printing jasper report - Candidate Report " + e);
                return null;
            }

        }
    }


    /**
     * @param startDate
     * @param endDate
     * @return path of generated company report if success;
     * else = returns null
     * @author Prateek Kapoor
     * @since 22-12-2020
     * This method generates the company report for a specific time duration and returns the path of the generated report
     */
    public String generateCompanyReport(Date startDate, Date endDate) {
        Log.debug("Request received in service to generate the company report for date range {} - {}", startDate, endDate);
        Collection<CompanyReportDto> companyReportInformation = adminDashboardDao.fetchCompanyReportDetails(startDate, endDate);
        if (Objects.isNull(companyReportInformation)) {
            Log.error("An exception occurred while fetching the company report information, returning null");
            return null;
        } else if (companyReportInformation.isEmpty()) {
            Log.debug("No information present for the selected date range");
            return null;
        } else {
            Log.debug("Received the company report information from {} to {}", startDate, endDate);
            JRBeanCollectionDataSource companyReportDetails = new JRBeanCollectionDataSource(companyReportInformation);
            Map<String, Object> reportParameters = new HashMap<String, Object>(1);
            reportParameters.put("companyReportDetails", companyReportDetails);
            Log.debug("Hashmap created and parameters inserted into hashmap");
            String outputFile;
            try {
                Log.debug("Processing request to generate the excel format for company report");
                outputFile = ReadApplicationConstants.getTemporaryDirectory() + ReadApplicationConstants.getCompanyReport() + ReadApplicationConstants.getXlsFileExtension();
                Log.debug("Getting input stream");
                S3Object s3Object = amazonClient.s3client.getObject(readProfileProperties.getBucketName(), readProfileProperties.getCompanyReport());
                InputStream inputStream = s3Object.getObjectContent();
                JasperPrint printFileName = JasperFillManager.fillReport(inputStream, reportParameters, new JREmptyDataSource());
                OutputStream outputStream = new FileOutputStream(new File(outputFile));

                JRXlsxExporter exporter = new JRXlsxExporter();
                exporter.setExporterInput(new SimpleExporterInput(printFileName));
                exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputFile));
                SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
                configuration.setRemoveEmptySpaceBetweenRows(true);
                configuration.setCollapseRowSpan(false);
                exporter.setConfiguration(configuration);
                exporter.exportReport();
                outputStream.close();
                inputStream.close();
                s3Object.close();
                Log.debug("Returning path with output file - {}", outputFile);
                return outputFile;
            } catch (Exception e) {
                Log.error("An exception handled while printing jasper report - Company Report " + e);
                return null;
            }
        }
    }
}
