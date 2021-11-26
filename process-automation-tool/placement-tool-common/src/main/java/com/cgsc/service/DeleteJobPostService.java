package com.cgsc.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgsc.dao.DeleteJobPostDao;

@Service
public class DeleteJobPostService {

    private static final Logger Log = LoggerFactory.getLogger(DeleteJobPostService.class);

    @Autowired
    DeleteJobPostDao deleteJobPostDao;

    /**
     * @author Sarthak bhutani
     * @since 07-01-2020
     * @param jobId (id)
     * @return 1 if success, -25 if exception, -35 if any candidate has applied for published job post
     * Method for closing a job post by an employer based on id
     */
    public int DeleteJobPost(int jobId) {
        Log.debug("Request received in service to delete Job Post with id: " + jobId);
        Log.debug("Checking if the job post is published and if it has any applicants with job post id: " + jobId);
        int checkIfAnyCandidateHasAppliedForPublishedJobPost = deleteJobPostDao.checkIfAnyCandidateHasAppliedForPublishedJobPost(jobId);
        if (checkIfAnyCandidateHasAppliedForPublishedJobPost == 0) {
        	Log.debug("Job Post can be deleted (doesn't have any applied candidates with published status)");
            return deleteJobPostDao.deleteJobPost(jobId);
        } else if (checkIfAnyCandidateHasAppliedForPublishedJobPost == 1) {
			Log.debug("Job Post can't be deleted (have applied candidates with published status)");
			return -35;
        } else {
            return checkIfAnyCandidateHasAppliedForPublishedJobPost;
        }

    }
}
