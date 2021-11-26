package com.cgsc.common;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.cgsc.utility.FetchActiveProfileUtility;

@Component
public class AmazonClient 
{
	private static final Logger Log = LoggerFactory.getLogger(AmazonClient.class);

	@Autowired
	private FetchActiveProfileUtility fetchActiveProfileUtility;
	@Autowired
	private ReadApplicationConstants applicationConstants;
	
	public AmazonS3 s3client;
	public AmazonSNS snsClient;

	@PostConstruct
    private void initializeAmazon() 
	{
		if(fetchActiveProfileUtility.getActiveProfile().equalsIgnoreCase(applicationConstants.getLocalProfile()))
		{
			System.out.println("The active profile is local, setting up connection with the dev bucket and sns client "+System.getProperty("awsSecretKey"));
			AWSCredentials awsCredentials = new BasicAWSCredentials(System.getProperty("awsAccessId"), System.getProperty("awsSecretKey"));
			this.s3client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCredentials)).withRegion(Regions.AP_SOUTH_1).build();
			this.snsClient =  AmazonSNSClient.builder().withRegion(Regions.AP_SOUTH_1)
					 .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
					 .build();
			Log.debug("Connection with S3 and SNS client successfull");
		}
		else
		{
			Log.debug("The profile is not local, connections to S3 bucket and SNS client will be made through IAM Roles attached to EC2 instances");
			InstanceProfileCredentialsProvider provider = new InstanceProfileCredentialsProvider(false);
		    this.s3client=AmazonS3ClientBuilder.standard().withCredentials(provider).withRegion(Regions.AP_SOUTH_1).build();
		    this.snsClient = AmazonSNSClientBuilder.standard().withCredentials(provider).withRegion(Regions.AP_SOUTH_1).build();
		    Log.debug("Connection with S3 and SNS client successful");
		}
	}
	
}
