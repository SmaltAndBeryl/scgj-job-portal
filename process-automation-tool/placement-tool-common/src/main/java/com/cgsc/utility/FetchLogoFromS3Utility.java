package com.cgsc.utility;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.cgsc.common.AmazonClient;
import com.cgsc.common.ReadProfileProperties;


@Component
public class FetchLogoFromS3Utility 
{
	private static final Logger Log = LoggerFactory.getLogger(FetchLogoFromS3Utility.class);
	
	@Autowired
	private AmazonClient amazonClient;
	@Autowired
	private FileUtility fileUtility;
	@Autowired
	private ReadProfileProperties readProfileProperties;
	
	
	/**
	 * This method downloads the image from S3 bucket, sends request to file utility to write the object contents on temp folder and returns the path
	 * @author Prateek Kapoor
	 * @since 28-07-2020
	 * @param logoPath
	 * @return path of the logo written at temp directory if success; else returns null
	 */
	public String fetchLogoFromS3Bucket(String logoPath)
	{
		Log.debug("Request received in utility method to fetch image from S3 bucket at relative path {}",logoPath);
		
		try 
		{
			Log.debug("Fetching the input stream of image from s3 bucket {}",readProfileProperties.getBucketName());
			S3ObjectInputStream stream = amazonClient.s3client.getObject(readProfileProperties.getBucketName(),logoPath).getObjectContent();
			byte[] bytes;
			bytes = IOUtils.toByteArray(stream);
			Log.debug("Fetched bytes from the input stream");
			String tempLogoPath = fileUtility.writeFilesOnTempDirectory(bytes);
			logoPath=tempLogoPath;
			Log.debug("The logo exists at {}", logoPath);
			stream.close();
			return logoPath;
		}
		catch (IOException e) {
			Log.error("Could not fetch bytes from input stream. Returning null");
			return null;
		}
	}
}