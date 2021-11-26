package com.cgsc.utility;

import java.io.File;
import java.util.Base64;

import org.apache.commons.io.FileUtils;
import org.apache.tika.Tika;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cgsc.common.ReadApplicationConstants;

@Service
public class ConvertBase64StringToFileUtility {

	private static final Logger Log = LoggerFactory.getLogger(ConvertBase64StringToFileUtility.class);
	
	/**
	 * @author Jyoti Singh
	 * @since 03-12-2020
	 * @param base64String
	 * @return file object converted from encoded string if success; else returns null
	 *
	 * @author Sarthak Bhutani
	 * @updatedOn 07-01-2020
	 * @update saving file as pdf instead of image(jpg, jpeg, png)
	 */
	@Transactional(rollbackFor=Exception.class)
	public File convertBase64ToFile(String base64String,String fileName) throws Exception
	{
		Log.debug("Request received in method to convert base64 encoded string to file and return the file object");
		byte[] decodedBytes = Base64.getDecoder().decode(base64String);
		Log.debug("Base64 string decoded into bytes");
		try 
		{
			String contentType = new Tika().detect(decodedBytes);
			Log.debug("The content type is {}",contentType);
			String temporaryFileDirectory = ReadApplicationConstants.getTemporaryDirectory();
			if(contentType.equalsIgnoreCase(ReadApplicationConstants.getContentTypePdf())) {
				Log.debug("The content Type is application/pdf");
				temporaryFileDirectory = temporaryFileDirectory + fileName + ReadApplicationConstants.getPdfFileExtension();
			}
			Log.debug("In try block, writing the decoded bytes into directory {}",temporaryFileDirectory);
			 FileUtils.writeByteArrayToFile(new File(temporaryFileDirectory), decodedBytes);
			 File file = new File(temporaryFileDirectory);
			 return file;
		}
		catch (Exception e) 
		{
			Log.error("An exception occured while writting the bytes at temporary file directory "+e);
			throw new Exception(e);
		}
	}
}
