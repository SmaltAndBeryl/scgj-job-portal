var manageJobApplications = angular.module("app");
manageJobApplications.controller("manageJobApplicationsController",function($scope,$http,$timeout){

	$scope.downloading=false;
	$scope.error=false;
	$scope.viewAllApplicationsGridSection=true;
	$scope.showCandidateApplicationDetails=false;
	$scope.modalActionButton=false;
	$scope.updateStatusGif = false;
	
	$scope.jobPostList=[];
	$scope.jobPostInfo={};
	
	var errorMessageUrl ="js/controllers/errorMessages.json";	
	var staticContentUrl ="js/controllers/staticContent.json";
	
	var rowHeight=30;
	var headerHeight=55;
	var minHeight = 90+"px";
	var maxGridHeight = 350;
	

	// ui-grid to view candidate application details
	$scope.viewCandidateApplications = {
		enableGridMenus: false,
		enableSorting: false,
		enableFiltering: true,
		enableCellEdit: false,
		enableColumnMenus: false,
		enableHorizontalScrollbar: 0,
		enableVerticalScrollbar: 1,
		columnDefs: [{
			name: 'candidateName',
			displayName: "Candidate Name",
			width: '25%',
			cellTooltip: function(row, col) {
				return 'Candidate Name: ' + row.entity.candidateName;
			}
		},
		{
			name: 'educationQualification',
			displayName: "Education",
			width: '15%',
			cellTooltip: function(row, col) 
			{
				return 'Education: ' + row.entity.educationQualification;
			}
		},
		{
			name: 'professionalExperience',
			displayName: "Experience",
			width: '13%',
			cellTooltip: function(row, col) 
			{
				return 'Experience: ' + row.entity.professionalExperience;
			}
		},
//		{
//			name: 'isCgscCertified',
//			displayName: "CGSC Certified",
//			cellTemplate : '<div class="applicationStatusGridText text-center"><p title="CGSC Certified Candidate" class="hiredText" ng-show="row.entity.isCgscCertified ==\'Y\'">Yes</p><p title="Not CGSC Certified" class="rejectedText" ng-show="row.entity.isCgscCertified ==\'N\'">No</p></div>',
//			width: '10%',
//			cellTooltip: function(row, col) 
//			{
//				return 'CGSC Certified: ' + row.entity.isCgscCertified;
//			}
//		},
		{
			name: 'resumePath',
			displayName: 'Resume',
			width: '7%',
			cellTemplate:'<div class="text-center"><img src="../images/pdfIcon.png" class="downloadResumeGridIcon" alt="Resume Pdf Icon" ng-click="grid.appScope.downloadResume(row.entity.resumePath, row.entity.candidateName)"></div>'

		},
		{
			name: 'applicationStatus',
			displayName: "Application Status",
			width: '11%',
			cellTemplate : '<div class="applicationStatusGridText text-center"><p title="Status: {{row.entity.applicationStatus}}" class="hiredText" ng-show="row.entity.applicationStatus ==\'Hired\'">{{row.entity.applicationStatus}}</p><p title="Status: {{row.entity.applicationStatus}}" class="shortlistedText" ng-show="row.entity.applicationStatus ==\'Shortlisted\'">{{row.entity.applicationStatus}}</p><p title="Status: {{row.entity.applicationStatus}}" class="inReviewText"  ng-show="row.entity.applicationStatus ==\'In Review\'">{{row.entity.applicationStatus}}</p><p title="Status: {{row.entity.applicationStatus}}" class="rejectedText" ng-show="row.entity.applicationStatus ==\'Rejected\'">{{row.entity.applicationStatus}}</p></div>',
			cellTooltip: function(row, col) 
			{
				return 'Application Status: ' + row.entity.applicationStatus;
			}
		},
		{
			name: 'action',
			displayName: 'Update Status',
			width: '13%',
			cellTemplate: 'partials/updateCandidateApplicationStatusCellTemplate.html'
		},
		{
			name: 'appliedOn',
			displayName: 'Applied On',
			width: '10%',
			cellTooltip: function(row, col) {
				return 'Applied On: ' + row.entity.appliedOn;
			}
		},
		{
			name: 'view',
			displayName: 'View',
			width: '7%',
			cellTemplate:'<div class="text-center"><button class="btn standardGridButton viewApplicantDetailsGridButton" ng-click="grid.appScope.viewCandidateDetails(row.entity)" >View</button></div>'

		},
	]};
	
	
	
	// ui-grid to view candidate application details for small screen
	$scope.viewCandidateApplicationsSmallScreen = {
		enableGridMenus: false,
		enableSorting: false,
		enableFiltering: true,
		enableCellEdit: false,
		enableColumnMenus: false,
		enableHorizontalScrollbar: 1,
		enableVerticalScrollbar: 1,
		columnDefs: [{
			name: 'candidateName',
			displayName: "Candidate Name",
			width: '50%',
			cellTooltip: function(row, col) {
				return 'Candidate Name: ' + row.entity.candidateName;
			}
		},
		{
			name: 'educationQualification',
			displayName: "Education",
			width: '50%',
			cellTooltip: function(row, col) 
			{
				return 'Education: ' + row.entity.educationQualification;
			}
		},
		{
			name: 'professionalExperience',
			displayName: "Experience",
			width: '50%',
			cellTooltip: function(row, col) 
			{
				return 'Experience: ' + row.entity.professionalExperience;
			}
		},
//		{
//			name: 'isCgscCertified',
//			displayName: "CGSC Certified",
//			cellTemplate : '<div class="applicationStatusGridText text-center"><p title="Status: CGSC Certified Candidate" class="hiredText" ng-show="row.entity.isCgscCertified ==\'Y\'">Yes</p><p title="Not CGSC Certified" class="rejectedText" ng-show="row.entity.isCgscCertified ==\'N\'">No</p></div>',
//			width: '35%',
//			cellTooltip: function(row, col) 
//			{
//				return 'CGSC Certified: ' + row.entity.isCgscCertified;
//			}
//		},
		{
			name: 'resumePath',
			displayName: 'Resume',
			width: '25%',
			cellTemplate:'<div class="text-center"><img src="../images/pdfIcon.png" class="downloadResumeGridIcon" alt="Resume Pdf Icon" ng-click="grid.appScope.downloadResume(row.entity.resumePath, row.entity.candidateName)"></div>'

		},
		{
			name: 'applicationStatus',
			displayName: "Application Status",
			width: '40%',
			cellTemplate : '<div class="applicationStatusGridText text-center"><p title="Status: {{row.entity.applicationStatus}}" class="hiredText" ng-show="row.entity.applicationStatus ==\'Hired\'">{{row.entity.applicationStatus}}</p><p title=Status: {{row.entity.applicationStatus}}" class="shortlistedText" ng-show="row.entity.applicationStatus ==\'Shortlisted\'">{{row.entity.applicationStatus}}</p><p title="Status: {{row.entity.applicationStatus}}" class="inReviewText"  ng-show="row.entity.applicationStatus ==\'In Review\'">{{row.entity.applicationStatus}}</p><p title="Status: {{row.entity.applicationStatus}}" class="rejectedText" ng-show="row.entity.applicationStatus ==\'Rejected\'">{{row.entity.applicationStatus}}</p></div>',
			cellTooltip: function(row, col) {
				return 'Application Status: ' + row.entity.applicationStatus;
			}
		},
		{
			name: 'action',
			displayName: 'Update Status',
			width: '45%',
			cellTemplate: 'partials/updateCandidateApplicationStatusCellTemplateSmallScreen.html'
		},
		{
			name: 'appliedOn',
			displayName: 'Applied On',
			width: '35%',
			cellTooltip: function(row, col) {
				return 'Applied On: ' + row.entity.appliedOn;
			}
		},
		{
			name: 'view',
			displayName: 'View',
			width: '25%',
			cellTemplate:'<div class="text-center"><button class="btn standardGridButton viewApplicantDetailsGridButton" ng-click="grid.appScope.viewCandidateDetails(row.entity)" >View</button></div>'

		}
	]};
	
	
	//Method to fetch error message
	$http.get(errorMessageUrl)
	.then(function(response){
		$scope.errorMessageArray = response.data[0];
	});
	
	//request to fetch static content
	$http.get(staticContentUrl)
	.then(function(response){
		$scope.staticContentArray= response.data[0];
	});
	
    //Request to fetch posts
    $scope.fetchJobPosts = function(){
      var getJobPostUrl= '/viewPublishedOrClosedJobs';
        $http.get(getJobPostUrl)
    	.then(function(response){
    		$scope.jobPostList = response.data;
    	});    	
    }
    $scope.fetchJobPosts();
   
    
    $scope.viewCandidateApplicationDetails=function(){
    	$scope.error=false;
		$scope.success=false;
		$scope.noApplicationsError=false;
    	let getApplicantDetailsUrl="/viewJobApplications?jobId="+$scope.jobPostInfo.id;
    	$http.get(getApplicantDetailsUrl)
		.then(function(response){
			if(response.data.length>0){
				$scope.showCandidateApplicationDetails=true;
				$scope.viewCandidateApplications.data=[];
				$scope.viewCandidateApplicationsSmallScreen.data= [];
				$scope.viewCandidateApplications.data= response.data;
				$scope.viewCandidateApplicationsSmallScreen.data= response.data;
				var gridHeight = $scope.viewCandidateApplications.data.length * rowHeight + headerHeight;
				if(gridHeight>maxGridHeight){
					document.getElementById("viewCandidateApplications").style.height=maxGridHeight+"px";
					document.getElementById("viewCandidateApplicationsSmallScreen").style.height=maxGridHeight+"px";
				}
				else
				{
					document.getElementById("viewCandidateApplications").style.height=gridHeight+"px";
					document.getElementById("viewCandidateApplicationsSmallScreen").style.height=gridHeight+"px";
				}
				
			}
			else{
				$scope.showCandidateApplicationDetails=false;
				document.getElementById("viewCandidateApplications").style.height=minHeight;
				document.getElementById("viewCandidateApplicationsSmallScreen").style.height=minHeight;
				$scope.viewCandidateApplications.data= [];
				$scope.viewCandidateApplicationsSmallScreen.data= [];
				$scope.noApplicationsError=true;
				$scope.noApplicationsErrorMessage=$scope.errorMessageArray.noJobApplicationsError;
				$timeout(function(){
					$scope.noApplicationsError=false;
				},7000);
			}
		});
    }
    
    
    
    //Method to download resume
    $scope.downloadResume=function(resumePath,applicantName){
    	$scope.downloadGif=true;
    	$scope.error=false;
    	$scope.success=false;
    	
    	let viewResumeUrl ="/downloadPdfFile/"+resumePath;
		$http.get(viewResumeUrl, {
			responseType: 'arraybuffer'
		})
		.then(function (response) {
			if (response.data.byteLength != 0) {
				var setContentType = response.headers("content-type");
				var viewResume = new Blob([response.data], {
					type: setContentType
				})
				var downloadURL = URL.createObjectURL(viewResume);
				var link = document.createElement('a');
				link.href = downloadURL;
				link.download =applicantName+$scope.staticContentArray.underScore+$scope.staticContentArray.resumeText;
				document.body.appendChild(link);
				link.click();
				$scope.downloadGif=false;
			} else {
				$scope.errorMessage = $scope.errorMessageArray.resumeDownloadError;
				$scope.error=true;
				$scope.downloadGif=false;
				$timeout(function(){
					$scope.error=false;
				},7000);
			}
			
		},function(error){
			$scope.errorMessage = $scope.errorMessageArray.resumeDownloadError;
			$scope.error=true;
			$scope.downloadGif=false;
			$timeout(function(){
				$scope.error=false;
			},7000);
		});
    }
    
  //Method to download certificates
    $scope.downloadCertificates=function(certificatePath,applicantName){
    	$scope.downloadGif=true;
    	$scope.error=false;
    	$scope.success=false;
    	
    	let viewCertificatesUrl ="/downloadZipFile/"+certificatePath;
		$http.get(viewCertificatesUrl, {
			responseType: 'arraybuffer'
		})
		.then(function (response) {
			if (response.data.byteLength != 0) {
				var setContentType = response.headers("content-type");
				var viewCertificates = new Blob([response.data], {
					type: setContentType
				})
				var downloadURL = URL.createObjectURL(viewCertificates);
				var link = document.createElement('a');
				link.href = downloadURL;
				link.download =applicantName+$scope.staticContentArray.underScore+$scope.staticContentArray.certificatesText;
				document.body.appendChild(link);
				link.click();
				$scope.downloadGif=false;
			} else {
				$scope.errorMessage = $scope.errorMessageArray.certificatesDownloadError;
				$scope.error=true;
				$scope.downloadGif=false;
				$timeout(function(){
					$scope.error=false;
				},7000);
			}
			
		},function(error){
			$scope.errorMessage = $scope.errorMessageArray.certificatesDownloadError;
			$scope.error=true;
			$scope.downloadGif=false;
			$timeout(function(){
				$scope.error=false;
			},7000);
		});
    }
    
    
    //Method to view candidate details
    $scope.viewCandidateDetails = function(row){
    	$scope.applicantInfo=row
    	$scope.downloadGif=false;
    	$scope.error=false;
    	$scope.success=false;
    	$scope.viewAllApplicationsGridSection=false;
    }
	
    //Method to view grid section on back button click
    $scope.viewApplicantsGridSection=function(){
    	$scope.downloadGif=false;
    	$scope.error=false;
    	$scope.success=false;
    	$scope.viewAllApplicationsGridSection=true;
    }
    
    
    
    //Method to update candidate application status
    $scope.updateApplicationStatus=function(rowValue, rowIndex){
    	$scope.updateApplicationStatusFlag=true;
    	
    	var windowWidth = $(window).width();
    	$scope.error=false;
		$scope.success=false;
		
    	let candidateList=[];
    	let applicantUpdatedStatus;
    	
    	//Code block to update individual candidate status
    	if((rowIndex !=undefined && rowIndex != null && rowIndex != "") || rowIndex >= 0){
    		candidateList.push(rowValue.candidateId);
			
			if(windowWidth>991){
    			applicantUpdatedStatus=document.getElementById("applicantStatus"+rowIndex).value;
    			if(applicantUpdatedStatus == $scope.staticContentArray.applicationStatusHired){
    				if(rowValue.applicationStatus == $scope.staticContentArray.applicationStatusShortlisted){
    					$("#hireCandidateModal").modal('show');
    					document.getElementById('hireCandidateForm').reset();
    					$scope.candidateRowIndex=rowIndex;
    				}
    				else{
    					$scope.errorMessage=$scope.errorMessageArray.onlyShortlistedCandidateCanBeHiredError;
        				$scope.error=true;
        				$scope.hideErrorMessage();
        				$scope.updateApplicationStatusFlag=false;
        				return;
    				}
    			}
    			else if(rowValue.applicationStatus == $scope.staticContentArray.applicationStatusHired){
    				$scope.errorMessage=$scope.errorMessageArray.cannotUpdateHiredCandidateStatusError;
    				$scope.error=true;
    				$scope.hideErrorMessage();
    				$scope.updateApplicationStatusFlag=false;
    				document.getElementById("applicantStatus"+rowIndex).value="";
    				return;
    			}
			}
			else{
				applicantUpdatedStatus=document.getElementById("applicantStatusSmallScreen"+rowIndex).value;
				if(applicantUpdatedStatus == $scope.staticContentArray.applicationStatusHired){
    				if(rowValue.applicationStatus == $scope.staticContentArray.applicationStatusShortlisted){
    					document.getElementById('hireCandidateForm').reset();
    					$("#hireCandidateModal").modal('show');
    				}
    				else{
    					$scope.errorMessage=$scope.errorMessageArray.onlyShortlistedCandidateCanBeHiredError;
        				$scope.error=true;
        				$scope.hideErrorMessage();
        				$scope.updateApplicationStatusFlag=false;
        				return;
    				}
    			}
				else if(rowValue.applicationStatus == $scope.staticContentArray.applicationStatusHired){
    				$scope.errorMessage=$scope.errorMessageArray.cannotUpdateHiredCandidateStatusError;
    				$scope.error=true;
    				$scope.hideErrorMessage();
    				$scope.updateApplicationStatusFlag=false;
    				document.getElementById("applicantStatusSmallScreen"+rowIndex).value="";
    				return;
    			}
			}
    	}
    	//else block for batch update
    	else{	
    		angular.forEach($scope.viewCandidateApplications.data,function(row){
    			if(row.applicationStatus != $scope.staticContentArray.applicationStatusHired){
    				candidateList.push(row.candidateId);
    			}	
    		});
    		applicantUpdatedStatus=$scope.updatedStatus;
    		
    		//check if there is aleast one candidate for application status update
    		if(candidateList.length<1){
    			$scope.errorMessage=$scope.errorMessageArray.cannotUpdateHiredCandidateStatusError;
				$scope.error=true;
				$scope.hideErrorMessage();
				$scope.updateApplicationStatusFlag=false;
				return;
    		}
    	}
    	
    	$scope.updateApplicationStatusData={
    		jobId:$scope.jobPostInfo.id,
    		updatedStatus:applicantUpdatedStatus,
    		candidateId:candidateList,
    		salary:"",
    		joiningDate:"",
    		offerLetter:""
    	};

    	//If status is not hired, update candidate application status
    	if($scope.updateApplicationStatusData.updatedStatus != $scope.staticContentArray.applicationStatusHired && $scope.updateApplicationStatusFlag){
    		$scope.updateCandidateApplicationStatus($scope.updateApplicationStatusData);
    	}
    };
	
    //Method to hire candidate on modal confirm button click
    $scope.hireCandidate = function()
    {
    	$scope.modalActionButton=true;
    	$scope.error=false;
		$scope.success=false;
		$scope.modalError=false;
    	$scope.updateApplicationStatusFlag=true;
    	$scope.updateApplicationStatusData.salary=$scope.candidateSalary;
		
    	var today = new Date();
		$scope.currentDate = $scope.formatDateToString(today);
		
		let applicationLastDate = new Date($scope.jobPostInfo.applicationDate);
		formattedApplicationLastDate = $scope.formatDateToString(applicationLastDate);
		
		if($scope.joiningDate < $scope.currentDate){
			$scope.modalErrorMessage=$scope.errorMessageArray.joiningDateLessThanCurrentDateError;
			$scope.modalError=true;
			$scope.hideErrorMessage();
			$scope.updateApplicationStatusFlag=false;
			$scope.modalActionButton=false;
		}
		else if($scope.joiningDate <= formattedApplicationLastDate){
			$scope.modalErrorMessage=$scope.errorMessageArray.joiningDateLessThanApplicationLastDateError+$scope.jobPostInfo.applicationDate;
			$scope.modalError=true;
			$scope.hideErrorMessage();
			$scope.updateApplicationStatusFlag=false;
			$scope.modalActionButton=false;
		}
		else{
			$scope.updateApplicationStatusData.joiningDate=$scope.joiningDate;
		}
		
		var fileName = document.getElementById('uploadedOfferLetter').value;
		var fileExtension = fileName.substr(fileName.lastIndexOf('.')+1);
		var documentUploaded = document.getElementById('uploadedOfferLetter').files[0];
		var fileSizeInMb = documentUploaded.size/(1024*1024);
		//If file is pdf
		if(fileExtension == 'pdf'){
			//check size
			if(fileSizeInMb>10)
			{
				$scope.modalErrorMessage = $scope.errorMessageArray.offerLetterSizeError;
				$scope.modalError=true;
				$scope.hideErrorMessage();
				$scope.updateApplicationStatusFlag=false;
				$scope.modalActionButton=false;
        	}
			else{
				$scope.updateApplicationStatusData.offerLetter=$scope.base64StringOfferLetter;
			}
    	}
		//show error if offer letter is not a pdf
		else{
			$scope.modalErrorMessage = $scope.errorMessageArray.invalidOfferLetterTypeError;
			$scope.modalError=true;
			$scope.hideErrorMessage();
			$scope.modalActionButton=false;
			$scope.updateApplicationStatusFlag=false;
    	}
		
		if($scope.updateApplicationStatusFlag){
			 $scope.updateCandidateApplicationStatus();
		}
    };
    
    
    //Method to update candidate application status
    $scope.updateCandidateApplicationStatus = function()
    {
    	$("#hireCandidateModal").modal('hide');
    	$scope.updateStatusGif = true;
    	
    	let updateCandidateStatusUrl="/updateCandidateApplicationStatus";
    	$http({
			method: 'POST',
			url: updateCandidateStatusUrl,
			data: $scope.updateApplicationStatusData
        }).then(function(response){
        	if(response.data==1){
        		$scope.updateAllApplications=false;
        		$scope.viewCandidateApplicationDetails();
        		$scope.updatedStatus="";
        		$scope.successMessage=$scope.errorMessageArray.updateApplicationStatusSuccess;
        		$scope.success=true;
        		$scope.modalActionButton=false;
        		$scope.updateStatusGif = false;
        		$scope.hideErrorMessage();
        		
        	}
        	else if(response.data == -88){
        		$scope.updatedStatus="";
        		$scope.errorMessage=$scope.errorMessageArray.invalidApplicationStatusError;
        		$scope.error=true;
        		$scope.modalActionButton=false;
        		$scope.updateStatusGif = false;
        		$scope.hideErrorMessage();
        		
        	}
        	else if(response.data == -11){
        		$scope.updatedStatus="";
        		$scope.errorMessage=$scope.errorMessageArray.noMoreCandidatesCanBeHiredError;
        		$scope.error=true;
        		$scope.modalActionButton=false;
        		$scope.updateStatusGif = false;
        		$scope.hideErrorMessage();
        	}
        	else if(response.data == -61){
        		$scope.updatedStatus="";
        		$scope.errorMessage=$scope.errorMessageArray.salaryLessThanMinimumSalaryError;
        		$scope.error=true;
        		$scope.modalActionButton=false;
        		$scope.updateStatusGif = false;
        		$scope.hideErrorMessage();
        	}
        	else{
        		$scope.updatedStatus="";
        		$scope.errorMessage=$scope.errorMessageArray.updateApplicationStatusError;
        		$scope.error=true;
        		$scope.modalActionButton=false;
        		$scope.updateStatusGif = false;
        		$scope.hideErrorMessage();
        	}
        },function(error){
        	$scope.updatedStatus="";
    		$scope.errorMessage=$scope.errorMessageArray.updateApplicationStatusError;
    		$scope.error=true;
    		$scope.modalActionButton=false;
    		$scope.updateStatusGif = false;
    		$scope.hideErrorMessage();
        });
    }
    
    
    //Method to populate modal details
    $scope.showModal = function(candidateDetails){
    	$scope.candidateInfo = candidateDetails;
    }
    
    
  //Method to calculate date in yyyy-mm-dd format
	$scope.formatDateToString = function (date){
		var dd = (date.getDate() < 10 ? '0' : '') + date.getDate();
		var MM = ((date.getMonth() + 1) < 10 ? '0' : '') + (date.getMonth() + 1);
		var yyyy = date.getFullYear();
		return (yyyy + $scope.staticContentArray.hyphenText + MM + $scope.staticContentArray.hyphenText + dd);
	}
	
    //Method to hide error/succes message
    $scope.hideErrorMessage=function(){
    	$timeout(function(){
    		$scope.error=false;
    		$scope.success=false;
    		$scope.modalError=false;
    	},7000)
    };
    
    
    //Method to convert uploaded offer letter to base 64 string
    $scope.readURLObj = function (event)
	{
		var files = event.target.files;
		var file = files[0];
		var reader = new FileReader();
		reader.onload = $scope.imageUploadedBinaryString; 
		reader.readAsBinaryString(file);  
	}
	
	$scope.imageUploadedBinaryString = function(e){
	    $scope.$apply(function() {
	    	var byteArray = e.target.result;
			$scope.base64StringOfferLetter = btoa(byteArray);
	    });
	}
    
    
});