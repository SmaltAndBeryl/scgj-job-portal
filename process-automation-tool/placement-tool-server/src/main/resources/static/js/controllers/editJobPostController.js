var editJobPost = angular.module("app");
editJobPost.controller("editJobPostController",function($scope,$http,$timeout)
{

	$scope.processingEditJobPostAction=false;
	$scope.disabledUpdateButton=false;
	$scope.downloadDescriptionIcon=true;
	$scope.viewEditJobPostForm=false;
	$scope.jobPostDetails={};
	$scope.workExperienceList=[];
	$scope.statesList=[];
	$scope.qualificationList=[];
	$scope.jobRoleList=[];
	$scope.jobPostInfo=[];	
	
	$scope.showDeleteIcon=false;
	$scope.deleteFlag=false;
	
	//initially hide download icon
	document.getElementById('downloadIcon').style.display="none";
	
	var errorMessageUrl ="js/controllers/errorMessages.json";	
	var staticContentUrl ="js/controllers/staticContent.json";
    
	
	//function to calculate date in yyyy-mm-dd format
	$scope.formatDateToString = function (date){
		var dd = (date.getDate() < 10 ? '0' : '') + date.getDate();
		var MM = ((date.getMonth() + 1) < 10 ? '0' : '') + (date.getMonth() + 1);
		var yyyy = date.getFullYear();
		return (yyyy + $scope.staticContentArray.hyphenText + MM + $scope.staticContentArray.hyphenText + dd);
	}
	
	//Method to fetch error message
	$http.get(errorMessageUrl)
	.then(function(response){
		$scope.errorMessageArray = response.data[0];
	});
	
	//request to fetch static content
	$http.get(staticContentUrl)
	.then(function(response){
		$scope.staticContentArray= response.data[0];
	}).then(function(){
		var today = new Date();
		$scope.currentDate = $scope.formatDateToString(today);
	});
	
	//Request to fetch states
    $http.get('/getStates')
	.then(function(response){
		let statesArray = response.data;
      	for (i in statesArray) {
      		$scope.statesList.push(response.data[i].stateName);
      	}
	});
    
    //Request to fetch states
    $scope.fetchJobPostsToEdit = function(){
      var unpublishedJobPostUrl= '/getJobIdWithStatus?status='+$scope.staticContentArray.statusNotPublished;
        $http.get(unpublishedJobPostUrl)
    	.then(function(response){
    		$scope.jobPostList = response.data;
    	});    	
    }
    
    $scope.fetchJobPostsToEdit();
    
    
    //Request to education qualification
    $http.get('/getEducationQualification')
	.then(function(response){
		let educationQualificationArray = response.data;
      	for (i in educationQualificationArray) {
      		$scope.qualificationList.push(response.data[i].educationQualification);
      	}
	});
    
    //Request to get professional experience
    $http.get('/getProfessionalExperience')
	.then(function(response){
		let experienceArray = response.data;
      	for (i in experienceArray) {
      		$scope.workExperienceList.push(response.data[i].experience);
      	}
	});
    
    //Request to get job role
    $http.get('/getJobRoles')
	.then(function(response){
		let jobRolesArray = response.data;
      	for (i in jobRolesArray) {
      		$scope.jobRoleList.push(response.data[i].jobRole);
      	}
	});

	//method to get occupation against job role
	$scope.getOccupationList = function(jobRole){
		let getOccupationUrl = "/getOccupationByJobRole?jobRole=" + jobRole;
		$http.get(getOccupationUrl)
			.then(function (response){
				$scope.occupationList = response.data;
			})
	};
    
    //Method to populate job post details if a job post is selected
    $scope.populateJobPostDetails = function(){
    	$scope.disabledUpdateButton=false;
    	$scope.error=false;
    	$scope.success=false;
    	$scope.jobDescriptionUpdated = false;
    	$scope.deleteFlag=false;
    	
    	let viewJobDetailsUrl ="/fetchJobDetails?jobId="+$scope.jobPostInfo.id;
    	$http.get(viewJobDetailsUrl)
    	.then(function(response){
    		
    		$scope.viewEditJobPostForm=true;
    		$scope.jobPostObject = response.data;

    		//set value for occupation dropdown
			$scope.getOccupationList($scope.jobPostObject.jobRole);
    		
    		//Check to populate description document name only if document was uploaded
    		if($scope.jobPostObject.descriptionDocument != null)
    		{
    			document.getElementById('uploadJobDescription').value=$scope.staticContentArray.jobDescriptionDocumentName;
    			document.getElementById('downloadIcon').style.display="block";
    			$scope.showDeleteIcon=true;
    		}
    		else
    		{
    			document.getElementById('uploadJobDescription').value="";
    			document.getElementById('downloadIcon').style.display="none";
    			$scope.showDeleteIcon=false;
    		}
    		
    		let applicationLastDate = new Date($scope.jobPostObject.applicationDate);
    		$scope.jobPostObject.applicationDate = $scope.formatDateToString(applicationLastDate);

    		//Populate interview date time if any
    		if($scope.jobPostObject.interviewStartDateTime != null)
    		{
    			$scope.jobPostObject.interviewStartDateTime = new Date($scope.jobPostObject.interviewStartDateTime);
        		$scope.jobPostObject.interviewEndDateTime = new Date($scope.jobPostObject.interviewEndDateTime);
    		}    		
    		
    		$scope.jobPostDetails = Object.assign({}, $scope.jobPostObject);
			$scope.jobPostDetails.occupation = $scope.jobPostObject.occupation;


			if($scope.jobPostObject.contactNumber != null && $scope.jobPostObject.contactNumber != undefined && $scope.jobPostObject.contactNumber != "" ){
				let contactNumber = $scope.jobPostObject.contactNumber.toString().slice(2);
				$scope.jobPostDetails.contactNumber=parseInt(contactNumber);
			}
    		
    		//Show hide additional information
    		if($scope.jobPostObject.additionalInformation == $scope.staticContentArray.trueFlag)
    		{
    			$scope.addAdditionalInfo=true;
    		}
    		else{
    			$scope.addAdditionalInfo=false;
    		}
    	});
    };
    
    
    //Method to change flag value if description document is uploaded
    $scope.updateDocumentUploadedFlg = function(){
    	 $scope.jobDescriptionUpdated = true;
    	 document.getElementById('downloadIcon').style.display="none";
    	 $scope.showDeleteIcon=true;
    	 $scope.deleteFlag=false;
    	 $scope.$apply();
    }
    
    
    //Method to delete description document
    $scope.deleteDescriptionDocument= function(){
    	$scope.deleteFlag=true;
    	if($scope.jobPostObject.descriptionDocument != null)
    	{
    		$scope.jobDescriptionUpdated = true;
    	}
    	else
    	{
    		$scope.jobDescriptionUpdated = false;
    	}
	   	document.getElementById('downloadIcon').style.display="none";
	   	document.getElementById('uploadJobDescription').value="";
	   	document.getElementById('uploadJobDescriptionFile').value="";
	   	$scope.showDeleteIcon=false;
    }
    
    //Method to download job description document 
    $scope.downloadJobDescription = function(){
    	$scope.error = false;
    	$scope.downloadDescriptionIcon=false;
    	let viewDescriptionDocumentUrl ="/downloadPdfFile/"+$scope.jobPostDetails.descriptionDocument;
		$http.get(viewDescriptionDocumentUrl, {
			responseType: 'arraybuffer'
		})
		.then(function (response) {
			if (response.data.byteLength != 0) {
				var setContentType = response.headers("content-type");
				var viewDocument = new Blob([response.data], {
					type: setContentType
				})
				var downloadURL = URL.createObjectURL(viewDocument);
				var link = document.createElement('a');
				link.href = downloadURL;
				link.download =$scope.jobPostInfo.jobId+$scope.staticContentArray.underScore+$scope.staticContentArray.descriptionDocumentText;
				document.body.appendChild(link);
				link.click();
				$scope.downloadDescriptionIcon=true;
			} else {
				$scope.editJobPostError = $scope.errorMessageArray.descriptionDocumentDownloadError;
				$scope.error=true;
				$scope.downloadDescriptionIcon=true;
			}
			$timeout(function(){
				$scope.error=false;
			},7000);
			
		},function(error){
			$scope.editJobPostError = $scope.errorMessageArray.descriptionDocumentDownloadError;
			$scope.error=true;
			$scope.downloadDescriptionIcon=true;
			$timeout(function(){
				$scope.error=false;
			},7000);
		});
    }
    
    
   //Method to validate additional information
    $scope.validateAdditionalInformation = function()
    {
    	if($scope.jobPostDetails.leavePolicy != undefined && ($scope.jobPostDetails.leavePolicy.length > 0  && $scope.jobPostDetails.leavePolicy.trim() == ""))
		{
			$scope.editJobPostError = $scope.errorMessageArray.invalidLeavePolicyError;
			$scope.error=true;
			$scope.validInput=false;
			$scope.processingEditJobPostAction=false;
			$scope.disabledUpdateButton=false;
			$scope.messageTimeout();
		}
    	else if($scope.jobPostDetails.monthlyIncentives != undefined && ($scope.jobPostDetails.monthlyIncentives.length > 0  && $scope.jobPostDetails.monthlyIncentives.trim() == ""))
		{
			$scope.editJobPostError = $scope.errorMessageArray.invalidMonthlyIncentiveDetailsError;
			$scope.error=true;
			$scope.validInput=false;
			$scope.processingEditJobPostAction=false;
			$scope.disabledUpdateButton=false;
			$scope.messageTimeout();
		}
    	else if($scope.jobPostDetails.workTimings != undefined && ($scope.jobPostDetails.workTimings.length > 0  && $scope.jobPostDetails.workTimings.trim() == ""))
		{
			$scope.editJobPostError = $scope.errorMessageArray.invalidWorkTimingsDetailsDetailsError;
			$scope.error=true;
			$scope.validInput=false;
			$scope.processingEditJobPostAction=false;
			$scope.disabledUpdateButton=false;
			$scope.messageTimeout();
		}
    	else if($scope.jobPostDetails.contactNumber != undefined && ($scope.jobPostDetails.contactNumber <= 0))
		{
			$scope.editJobPostError = $scope.errorMessageArray.invalidContactNumber;
			$scope.error=true;
			$scope.validInput=false;
			$scope.processingEditJobPostAction=false;
			$scope.disabledUpdateButton=false;
			$scope.messageTimeout();
		}
		else if($scope.jobPostDetails.contactNumber != undefined && ($scope.jobPostDetails.contactNumber.toString().length != 10))
		{
			$scope.editJobPostError = $scope.errorMessageArray.contactNumberLengthError;
			$scope.error=true;
			$scope.validInput=false;
			$scope.processingEditJobPostAction=false;
			$scope.disabledUpdateButton=false;
			$scope.messageTimeout();
		}
		else if($scope.jobPostDetails.interviewStartDateTime != undefined && $scope.jobPostDetails.interviewEndDateTime == undefined)
		{
			$scope.editJobPostError = $scope.errorMessageArray.interviewEndDateTimeRequired;
			$scope.error=true;
			$scope.validInput=false;
			$scope.processingEditJobPostAction=false;
			$scope.disabledUpdateButton=false;
			$scope.messageTimeout();
		}
		else if($scope.jobPostDetails.interviewStartDateTime == undefined && $scope.jobPostDetails.interviewEndDateTime != undefined)
		{
			$scope.editJobPostError = $scope.errorMessageArray.interviewStartDateTimeRequired;
			$scope.error=true;
			$scope.validInput=false;
			$scope.processingEditJobPostAction=false;
			$scope.disabledUpdateButton=false;
			$scope.messageTimeout();
		}
		else if($scope.jobPostDetails.interviewStartDateTime !=undefined &&  $scope.jobPostDetails.interviewEndDateTime != undefined)
		{
			var currentTime = Math.floor(Date.now() / 1000);
			var interviewstartTime = Math.floor($scope.jobPostDetails.interviewStartDateTime.getTime()/1000);
			var interviewEndTime = Math.floor($scope.jobPostDetails.interviewEndDateTime.getTime()/1000);

			if($scope.jobPostDetails.applicationDate == $scope.formatDateToString($scope.jobPostDetails.interviewStartDateTime) )
			{
				$scope.editJobPostError = $scope.errorMessageArray.interviewMustStartAfterApplicationLastDateError;
				$scope.error=true;
				$scope.validInput=false;
				$scope.processingEditJobPostAction=false;
				$scope.disabledUpdateButton=false;
				$scope.messageTimeout();
			}
			else if(interviewstartTime<currentTime)
			{
				$scope.editJobPostError = $scope.errorMessageArray.pastInterviewStartDateTimeError;
				$scope.error=true;
				$scope.validInput=false;
				$scope.processingEditJobPostAction=false;
				$scope.disabledUpdateButton=false;
				$scope.messageTimeout();	
			}
			else if(interviewEndTime<=interviewstartTime)
			{
				$scope.editJobPostError = $scope.errorMessageArray.interviewEndDateTimeLessThanStartDateTimeError;
				$scope.error=true;
				$scope.validInput=false;
				$scope.processingEditJobPostAction=false;
				$scope.disabledUpdateButton=false;
				$scope.messageTimeout();
			}
		}
    	
    }
    
    
    //Method to update job post details
    $scope.editJobPost = function(){
    	$scope.error=false;
		$scope.success= false;
    	$scope.validInput=true;
    	$scope.processingEditJobPostAction=true;
    	$scope.disabledUpdateButton=true;
    	
    	if($scope.jobPostDetails.jobTitle == undefined || $scope.jobPostDetails.jobTitle == null || $scope.jobPostDetails.jobTitle == "")
		{
			$scope.editJobPostError = $scope.errorMessageArray.invalidJobTitleError;
			$scope.error=true;
			$scope.processingEditJobPostAction=false;
			$scope.disabledUpdateButton=false;
			$scope.validInput=false;
			$scope.messageTimeout();
		}
    	else if($scope.jobPostDetails.minSalary >=  $scope.jobPostDetails.maxSalary)
		{
			$scope.editJobPostError = $scope.errorMessageArray.invalidMinSalaryError;
			$scope.error=true;
			$scope.processingEditJobPostAction=false;
			$scope.disabledUpdateButton=false;
			$scope.validInput=false;
			$scope.messageTimeout();
		}
    	else if($scope.jobPostDetails.district == undefined || $scope.jobPostDetails.district == null || $scope.jobPostDetails.district == "")
		{
			$scope.editJobPostError = $scope.errorMessageArray.invalidDistrictError;
			$scope.error=true;
			$scope.processingEditJobPostAction=false;
			$scope.disabledUpdateButton=false;
			$scope.validInput=false;
			$scope.messageTimeout();
		}
    	else if($scope.jobPostDetails.applicationDate < $scope.currentDate)
		{
			$scope.editJobPostError = $scope.errorMessageArray.invalidApplicationLastDateError;
			$scope.error=true;
			$scope.processingEditJobPostAction=false;
			$scope.disabledUpdateButton=false;
			$scope.validInput=false;
			$scope.messageTimeout();
		}
    	else if($scope.jobPostDetails.jobSummary == undefined || $scope.jobPostDetails.jobSummary == null || $scope.jobPostDetails.jobSummary =="")
		{
			$scope.editJobPostError = $scope.errorMessageArray.invalidJobSummaryError;
			$scope.error=true;
			$scope.processingEditJobPostAction=false;
			$scope.disabledUpdateButton=false;
			$scope.validInput=false;
			$scope.messageTimeout();
		}
    	if($scope.jobDescriptionUpdated && !$scope.deleteFlag)
    	{
    		var fileName = document.getElementById('uploadJobDescriptionFile').value;
    		var fileExtension = fileName.substr(fileName.lastIndexOf('.')+1);
    		var documentUploaded = document.getElementById('uploadJobDescriptionFile').files[0];
    		var fileSizeInMb = documentUploaded.size/(1024*1024);
    		
    		if(fileExtension != 'pdf')
    		{
    			$scope.editJobPostError = $scope.errorMessageArray.invalidJobDescriptionDocument;
    			$scope.error=true;
    			$scope.processingEditJobPostAction=false;
    			$scope.disabledUpdateButton=false;
    			$scope.validInput=false;
    			$scope.messageTimeout();	
    		}
    		else if(fileSizeInMb>10)
    		{
    			$scope.editJobPostError = $scope.errorMessageArray.jobDescriptionDocumentSizeError;
    			$scope.error=true;
    			$scope.processingEditJobPostAction=false;
    			$scope.disabledUpdateButton=false;
    			$scope.validInput=false;
    			$scope.messageTimeout();
    		}
    		
    	}
    	
    	if($scope.validInput && $scope.addAdditionalInfo)
		{
			$scope.validateAdditionalInformation();
		}
    	
    	//if all user input are valid
    	if($scope.validInput)
		{
    		var jobPostData = new FormData();
    		
    		//append all mandatory fields to the form data object
    		jobPostData.append('id',$scope.jobPostDetails.id);
    		jobPostData.append('jobId',$scope.jobPostInfo.jobId);
    		jobPostData.append('jobTitle', $scope.jobPostDetails.jobTitle);
			jobPostData.append('vacancy', $scope.jobPostDetails.vacancy);
			jobPostData.append('minSalary', $scope.jobPostDetails.minSalary);
			jobPostData.append('maxSalary', $scope.jobPostDetails.maxSalary);
			jobPostData.append('state', $scope.jobPostDetails.state);
			jobPostData.append('district', $scope.jobPostDetails.district);
			jobPostData.append('minimumExperience', $scope.jobPostDetails.minimumExperience);
			jobPostData.append('educationQualification', $scope.jobPostDetails.educationQualification);
			jobPostData.append('jobRole', $scope.jobPostDetails.jobRole);
			jobPostData.append('occupation',$scope.jobPostDetails.occupation);
			jobPostData.append('applicationDate', $scope.jobPostDetails.applicationDate);
			jobPostData.append('jobSummary', $scope.jobPostDetails.jobSummary);
			jobPostData.append('jobDescriptionUpdated', $scope.jobDescriptionUpdated);
//			jobPostData.append('cgscCertificatePreferred',$scope.jobPostDetails.cgscCertificatePreferred);
			if($scope.jobDescriptionUpdated && !$scope.deleteFlag){
				var jobDescriptionDocument = document.getElementById('uploadJobDescriptionFile').files[0];
				jobPostData.append('descriptionDocumentFile', jobDescriptionDocument);
			}
			
			//non-mandatory additional fields
			if($scope.addAdditionalInfo)
			{
				if($scope.jobPostDetails.leavePolicy != undefined && $scope.jobPostDetails.leavePolicy != "" && $scope.jobPostDetails.leavePolicy.length > 3)
				{
					jobPostData.append('leavePolicy', $scope.jobPostDetails.leavePolicy);
				}
				if($scope.jobPostDetails.monthlyIncentives != undefined && $scope.jobPostDetails.monthlyIncentives != "" && $scope.jobPostDetails.monthlyIncentives.length > 3)
				{
					jobPostData.append('monthlyIncentives', $scope.jobPostDetails.monthlyIncentives);
				}
				if($scope.jobPostDetails.workTimings != undefined && $scope.jobPostDetails.workTimings != "" && $scope.jobPostDetails.workTimings.length > 3)
				{
					jobPostData.append('workTimings', $scope.jobPostDetails.workTimings);
				}
				if($scope.jobPostDetails.contactNumber != undefined && $scope.jobPostDetails.contactNumber != "")
				{
					jobPostData.append('contactNumber', $scope.staticContentArray.mobileNumberPrefix+$scope.jobPostDetails.contactNumber);
				}
				if($scope.jobPostDetails.interviewStartDateTime != undefined && $scope.jobPostDetails.interviewStartDateTime != null)
				{
					var startTime;
					var endTime;
					if($scope.jobPostObject.interviewStartDateTime == $scope.jobPostDetails.interviewStartDateTime){
						startTime = document.getElementById("interviewStartTime").value;
						let lastIndex = startTime.lastIndexOf(":00.000");
						if(lastIndex != -1 ){
							startTime=startTime.slice(0,lastIndex);
						}
					}
					else{
						startTime = document.getElementById("interviewStartTime").value;
					}
					
					if($scope.jobPostObject.interviewEndDateTime == $scope.jobPostDetails.interviewEndDateTime){
						endTime = document.getElementById("interviewEndTime").value;
						let lastIndex = endTime.lastIndexOf(":00.000");
						if(lastIndex != -1 ){
							endTime=endTime.slice(0,lastIndex);
						}
					}
					else{
						endTime = document.getElementById("interviewEndTime").value;
					}
					var t1 = startTime.split('T');
					var t2 = endTime.split('T');
					var formattedStartTime = t1[0]+' '+t1[1]+':00';
					var formattedEndTime = t2[0]+' '+t2[1]+':00';
					jobPostData.append('interviewStartDateTime',formattedStartTime);
					jobPostData.append('interviewEndDateTime',formattedEndTime);
				}
				if($scope.jobPostDetails.isWalkInInterview != undefined)
				{
					jobPostData.append('isWalkInInterview',$scope.jobPostDetails.isWalkInInterview);
				}
				if($scope.jobPostDetails.preferredGender != undefined)
				{
					jobPostData.append('preferredGender',$scope.jobPostDetails.preferredGender);
				}
				if($scope.jobPostDetails.armyBackgroundPreferred != undefined)
				{
					jobPostData.append('armyBackgroundPreferred',$scope.jobPostDetails.armyBackgroundPreferred);
				}
			}
			let editJobUrl="/editJobPost";
			$http({
				method: 'POST',
				url: editJobUrl,
				data: jobPostData,
				headers: {
	               'Content-Type': undefined
				}
	        }).then(function(response){
	        	if(response.data == 1)
	        	{
	        		$scope.editJobPostSuccess = $scope.errorMessageArray.editJobPostSuccess
	        		$scope.success= true;
	        		$scope.processingEditJobPostAction=false;
	        		document.getElementById('editJobPostForm').reset();
//					$scope.jobPostDetails.cgscCertificatePreferred="";
	        		document.getElementById('downloadIcon').style.display="none";
					$scope.occupationList=[];
					$scope.jobPostDetails={};
	        		$scope.jobPostInfo={};
					$scope.fetchJobPostsToEdit ();
	        		$scope.addAdditionalInfo=false;
	        		$scope.messageTimeout();
	        		$scope.showDeleteIcon=false;
	        	}
	        	else
	        	{
	        		$scope.editJobPostError = $scope.errorMessageArray.editJobPostError;
	    			$scope.error=true;
	    			$scope.processingEditJobPostAction=false;
	    			$scope.disabledUpdateButton=false;
	    			$scope.messageTimeout();
	        	}
	        },function(errorResponse){
	        	$scope.editJobPostError = $scope.errorMessageArray.editJobPostError;
    			$scope.error=true;
    			$scope.processingEditJobPostAction=false;
    			$scope.disabledUpdateButton=false;
    			$scope.messageTimeout();
	        });
    		
		}// If block for making post request ends here
    	
    };
    
    
  //method to hide error/success messages
    $scope.messageTimeout = function(){
    	$timeout(function () {
			$scope.error=false;
			$scope.success= false;
		}, 7000);
    };
	
});