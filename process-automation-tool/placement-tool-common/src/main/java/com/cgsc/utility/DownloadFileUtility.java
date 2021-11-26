package com.cgsc.utility;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerMapping;

import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.cgsc.common.AmazonClient;
import com.cgsc.common.Privilege;
import com.cgsc.common.ReadProfileProperties;

@RestController
public class DownloadFileUtility 
{

	@Autowired
	private AmazonClient amazonClient;
	@Autowired
	private ReadProfileProperties readProfileProperties;
	
	private static final Logger Log = LoggerFactory.getLogger(DownloadFileUtility.class);
	
	/**
	 * This method receives the file path from the front end and then returns zip the file object to the front end controller
	 * @param filePath
	 * @param response
	 * @throws IOException 
	 */
	@Privilege(value= {"Employer","Candidate"})
	@RequestMapping("/downloadZipFile/{filePath}/**")
	public void downloadZipFile(@PathVariable String filePath, HttpServletResponse response, HttpServletRequest request) throws IOException 
	{
		final String path =  request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE).toString();
	    final String bestMatchingPattern = request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE).toString();
	    String arguments = new AntPathMatcher().extractPathWithinPattern(bestMatchingPattern, path);
	    String moduleName;
	    if (null != arguments && !arguments.isEmpty()) 
	    {
	        moduleName = filePath + '/' + arguments;
	    }
	    else 
	    {
	        moduleName = filePath;
	    }
		Log.debug("Request received to download file at location: "+moduleName);
	    response.setContentType("application/zip");
		S3Object s3object = amazonClient.s3client.getObject(readProfileProperties.getBucketName(), moduleName);
		S3ObjectInputStream inputStream = s3object.getObjectContent();
		BufferedOutputStream outStream = new BufferedOutputStream(response.getOutputStream());
		byte[] buffer = new byte[1024];
		int bytesRead = 0;
		while ((bytesRead = inputStream.read(buffer)) != -1)
		{
			outStream.write(buffer, 0, bytesRead);
		}
		Log.debug("File downloaded successfully");
		outStream.flush();
		inputStream.close();
		s3object.close();
	}

	/**
	 * This method receives the file path from the front end and then returns the pdf file object to the front end controller
	 * @param filePath
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping("/downloadPdfFile/{filePath}/**")
	public void pdfFile(@PathVariable String filePath, HttpServletResponse response, HttpServletRequest request) throws IOException 
	{
		final String path =  request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE).toString();
	    final String bestMatchingPattern = request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE).toString();
	    String arguments = new AntPathMatcher().extractPathWithinPattern(bestMatchingPattern, path);
	    String completeFilePath;
	    if (null != arguments && !arguments.isEmpty()) 
	    {
	        completeFilePath = filePath + '/' + arguments;
	    }
	    else 
	    {
	        completeFilePath = filePath;
	    }
		Log.debug("Request received to download file at location: "+completeFilePath);
	    response.setContentType("application/pdf");
		S3Object s3object = amazonClient.s3client.getObject(readProfileProperties.getBucketName(), completeFilePath);
		S3ObjectInputStream inputStream = s3object.getObjectContent();
		BufferedOutputStream outStream = new BufferedOutputStream(response.getOutputStream());
		byte[] buffer = new byte[1024];
		int bytesRead = 0;
		while ((bytesRead = inputStream.read(buffer)) != -1)
		{
			outStream.write(buffer, 0, bytesRead);
		}
		Log.debug("File downloaded successfully");
		outStream.flush();
		inputStream.close();
		s3object.close();
	}
	/**
	 * This method will be used to download the certificate
	 * @author Prateek Kapoor
	 * @since 22-10-2020
	 * @param filePath
	 * @param response
	 */
	public void downloadReport(String filePath, HttpServletResponse response) 
	{
		Log.debug("Request received to download file at location: "+filePath);

		try {
			Log.debug("Checking if the file returned is empty or not");
			if(filePath!=null) {
			
				Log.debug("In try block to download the file at path - {}",filePath);
				File file = new File(filePath);
				Log.debug("Setting the content type of response");
				response.setContentType("application/pdf");

				Log.debug("Setting the header of response as attachment along with the filename");
				response.setHeader("Content-Disposition", "attachment;filename=" + file.getName());
				Log.debug("Creating buffered input stream to read file from: {} ",filePath);
				BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file));
				
				Log.debug("Creating buffered output stream to append file in the response {}",file.getName());
				BufferedOutputStream outStream = new BufferedOutputStream(response.getOutputStream());
			
				byte[] buffer = new byte[1024];
				int bytesRead = 0;
				while ((bytesRead = inputStream.read(buffer)) != -1)
				{
					outStream.write(buffer, 0, bytesRead);
				}
				
				Log.debug("File downloaded successfully");
				outStream.flush();
				inputStream.close();
				file.delete();
			}
			else 
			{
				Log.error("Path not found");
				Log.error("File is empty");
				Log.error("File not downloaded");
			}
		}
		catch(Exception e) {
			
			Log.error("An exception occured while generating File - "+e);
		}
	}

	/**
	 * @author Prateek Kapoor
	 * @since 02-11-2020
	 * @param filePath
	 * @param response
	 */
	public void downloadExcelFile(String filePath, HttpServletResponse response)
	{
		Log.debug("Request received in utility method to download the file in xls format");
		try {
			Log.debug("Checking if the file returned is empty or not");
			if(filePath!=null) {
			
				Log.debug("In try block to download the file at path - {}",filePath);
				File file = new File(filePath);
				Log.debug("Setting the content type of response");
				response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

				Log.debug("Setting the header of response as attachment along with the filename");
				response.setHeader("Content-Disposition", "attachment;filename=" + file.getName());
				Log.debug("Creating buffered input stream to read file from: {} ",filePath);
				BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file));
				
				Log.debug("Creating buffered output stream to append file in the response {}",file.getName());
				BufferedOutputStream outStream = new BufferedOutputStream(response.getOutputStream());
			
				byte[] buffer = new byte[1024];
				int bytesRead = 0;
				while ((bytesRead = inputStream.read(buffer)) != -1)
				{
					outStream.write(buffer, 0, bytesRead);
				}
				
				Log.debug("File downloaded successfully");
				outStream.flush();
				inputStream.close();
				file.delete();
			}
			else 
			{
				Log.error("Path not found");
				Log.error("File is empty");
				Log.error("File not downloaded");
			}
		}
		catch(Exception e) {
			
			Log.error("An exception occured while generating File - "+e);
		}
	}

	/*
	 * @author Sarthak Bhutani
	 * @since 10/12/2020
	 * This method receives the file path from the front end and then returns the image file object to the front end controller
	 * @param filePath
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/downloadImage/{filePath}/**")
	public void downloadImage(@PathVariable String filePath, HttpServletResponse response, HttpServletRequest request) throws IOException
	{
		Log.debug("Request received by download file utility to download an image");
		final String path =  request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE).toString();
		final String bestMatchingPattern = request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE).toString();
		String arguments = new AntPathMatcher().extractPathWithinPattern(bestMatchingPattern, path);
		String completeFilePath;
		if (null != arguments && !arguments.isEmpty())
		{
			completeFilePath = filePath + '/' + arguments;
		}
		else
		{
			completeFilePath = filePath;
		}
		Log.debug("Request received to download file at location: "+completeFilePath);
		S3Object s3object = amazonClient.s3client.getObject(readProfileProperties.getBucketName(), completeFilePath);
		S3ObjectInputStream inputStream = s3object.getObjectContent();
		Log.debug("content-type for image:"+ s3object.getObjectMetadata().getContentType());
		response.setContentType(s3object.getObjectMetadata().getContentType());
		BufferedOutputStream outStream = new BufferedOutputStream(response.getOutputStream());
		byte[] buffer = new byte[1024];
		int bytesRead = 0;
		while ((bytesRead = inputStream.read(buffer)) != -1)
		{
			outStream.write(buffer, 0, bytesRead);
		}
		Log.debug("File downloaded successfully");
		outStream.flush();
		inputStream.close();
		s3object.close();
	}

}