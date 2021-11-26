package com.cgsc.utility;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLConnection;
import java.sql.Timestamp;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.cgsc.common.AmazonClient;
import com.cgsc.common.ReadApplicationConstants;
import com.cgsc.common.ReadProfileProperties;

@Component
public class FileUtility 
{

	private static final Logger Log = LoggerFactory.getLogger(FileUtility.class);
	@Autowired
	private AmazonClient amazonClient;
	@Autowired
	private ReadProfileProperties readProfileProperties;
	
	/**
	 * @author Prateek Kapoor
	 * @param file
	 * @return Returns the File Object after converting Multipart File into File
	 * @throws Exception
	 */
	@Transactional(rollbackFor = Exception.class)
	public File convertMultiPartToFile(MultipartFile file) throws Exception 
	{
	    try{
	    	File convFile = new File(file.getOriginalFilename());
	    	FileOutputStream fos = new FileOutputStream(convFile);
		    fos.write(file.getBytes());
		    fos.close();
		    return convFile;
	    }
	    catch(Exception e)
	    {
	    	throw new Exception(e);
	    }
	    
	}
	
	/**
	 * @author Prateek Kapoor
	 * @apiNote This api performs the following operations - 
	 * 1. Establishes connection with the S3 Client
	 * 2. Sets the access type of object to private
	 * @param key
	 * @param file
	 */
	@Transactional(rollbackFor=Exception.class)
	public void uploadFileTos3bucket(String key, File file) throws Exception
	{	
		try 
		{
			Log.debug("In try block to put the object on S3 bucket with name - {}",readProfileProperties.getBucketName());
			amazonClient.s3client.putObject(new PutObjectRequest(readProfileProperties.getBucketName(), key, file).withCannedAcl(CannedAccessControlList.Private));
		}
		catch (Exception e)
		{
			Log.error("An exception occurred while uploading files to S3 bucket "+e);
			throw new Exception(e);
		}
		
	}
	
	
	/**
	 * @author Jyoti Singh
	 * @since 03-12-2020
	 * @apiNote This api performs the following operations - 
	 * 1. Establishes connection with the S3 Client and uploads the file object
	 * 2. Sets the access type of object to public
	 * @param key
	 * @param file
	 */
	@Transactional(rollbackFor=Exception.class)
	public void uploadPublicFileTos3bucket(String key, File file) throws Exception
	{	
		try 
		{
			Log.debug("In try block to put the object on S3 bucket with name - {}",readProfileProperties.getBucketName());
			amazonClient.s3client.putObject(new PutObjectRequest(readProfileProperties.getBucketName(), key, file).withCannedAcl(CannedAccessControlList.PublicRead));
		}
		catch (Exception e)
		{
			Log.error("An exception occurred while uploading files to S3 bucket "+e);
			throw new Exception(e);
		}
		
	}
	
	/**
	 * @author Prateek Kapoor
	 * @apiNote This api performs the following operations - 
	 * 1. Establishes connection with the S3 Client
	 * 3. Deletes the received key from the S3 bucket
	 * @param key
	 */
	@Transactional(rollbackFor=Exception.class)
	public void deleteFilesFromS3Bucket(String key) throws Exception
	{	
		try 
		{
			Log.debug("In try block to delete the object from S3 bucket with name - {}",readProfileProperties.getBucketName());
			amazonClient.s3client.deleteObject(readProfileProperties.getBucketName(), key);
		}
		catch (Exception e)
		{
			Log.error("An exception occurred while deleting the files from S3 bucket "+e);
			throw new Exception(e);
		}
		
	}
	
	/**
	 * This method writes the images on temporary file directory and returns the path
	 * @author Prateek Kapoor
	 * @since 28-07-2020
	 * @param bytes
	 * @return file path if success; else throws an exceptionw
	 * @throws IOException
	 */
	public String writeFilesOnTempDirectory(byte[] bytes) throws IOException
	{
		String contentType = URLConnection.guessContentTypeFromStream(new ByteArrayInputStream(bytes));
		String temporaryFileDirectory = ReadApplicationConstants.getTemporaryDirectory();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		if(contentType.equalsIgnoreCase(ReadApplicationConstants.getContentTypePng()))
		{
			Log.debug("The content Type is image/png");
			temporaryFileDirectory = temporaryFileDirectory+timestamp.getTime()+ReadApplicationConstants.getPngFileExtension();
		}
		else
		{
			Log.debug("The content type is jpeg format");
			temporaryFileDirectory = temporaryFileDirectory+timestamp.getTime()+ReadApplicationConstants.getJpegFileExtension();
		}
		 Log.debug("In try block, writing the decoded bytes into directory {}",temporaryFileDirectory);
		 FileUtils.writeByteArrayToFile(new File(temporaryFileDirectory), bytes);
		 return temporaryFileDirectory;
	}
}
