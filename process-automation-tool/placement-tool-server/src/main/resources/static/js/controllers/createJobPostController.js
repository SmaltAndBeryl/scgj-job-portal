var createJobPost = angular.module("app");
createJobPost.controller("createJobPostController",function($scope,$http,$timeout){

	$scope.disableFormAction=false;
	$scope.addJobDescription = false;
	$scope.jobPostDetails={};
	$scope.workExperienceList=[];
	$scope.statesList=[];
	$scope.qualificationList=[];
	$scope.jobRoleList=[];
	
	
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
    
    //method to update job description flag
    $scope.updateJobDescriptionFlag = function(){
    	$scope.addJobDescription = true;
    	 $scope.$apply();
    }
    
    //method to delete uploaded description document
    $scope.deleteDescriptionDocument = function(){
    	 $scope.addJobDescription = false;
    	 document.getElementById('uploadJobDescriptionFile').value="";
    	 document.getElementById('uploadJobDescription').value="";
    }
    
    //method to validate form additional information
	$scope.validateAdditionalInformation = function(){		
		if($scope.jobPostDetails.leavePolicyDetails != undefined && ($scope.jobPostDetails.leavePolicyDetails.length > 0  && $scope.jobPostDetails.leavePolicyDetails.trim() == ""))
		{
			$scope.createJobPostError = $scope.errorMessageArray.invalidLeavePolicyError;
			$scope.error=true;
			$scope.validInput=false;
			$scope.disableFormAction=false;
			$scope.messageTimeout();
		}
		else if($scope.jobPostDetails.monthlyIncentivesDetails != undefined && ($scope.jobPostDetails.monthlyIncentivesDetails.length > 0  && $scope.jobPostDetails.monthlyIncentivesDetails.trim() == ""))
		{
			$scope.createJobPostError = $scope.errorMessageArray.invalidMonthlyIncentiveDetailsError;
			$scope.error=true;
			$scope.validInput=false;
			$scope.disableFormAction=false;
			$scope.messageTimeout();
		}
		else if($scope.jobPostDetails.workTimingsDetails != undefined && ($scope.jobPostDetails.workTimingsDetails.length > 0  && $scope.jobPostDetails.workTimingsDetails.trim() == ""))
		{
			$scope.createJobPostError = $scope.errorMessageArray.invalidWorkTimingsDetailsDetailsError;
			$scope.error=true;
			$scope.validInput=false;
			$scope.disableFormAction=false;
			$scope.messageTimeout();
		}
		else if($scope.jobPostDetails.contactNumber != undefined && ($scope.jobPostDetails.contactNumber <= 0))
		{
			$scope.createJobPostError = $scope.errorMessageArray.invalidContactNumber;
			$scope.error=true;
			$scope.validInput=false;
			$scope.disableFormAction=false;
			$scope.messageTimeout();
		}
		else if($scope.jobPostDetails.contactNumber != undefined && ($scope.jobPostDetails.contactNumber.toString().length != 10))
		{
			$scope.createJobPostError = $scope.errorMessageArray.contactNumberLengthError;
			$scope.error=true;
			$scope.validInput=false;
			$scope.disableFormAction=false;
			$scope.messageTimeout();
		}
		else if($scope.jobPostDetails.interviewStartDateTime != undefined && $scope.jobPostDetails.interviewEndDateTime == undefined)
		{
			$scope.createJobPostError = $scope.errorMessageArray.interviewEndDateTimeRequired;
			$scope.error=true;
			$scope.validInput=false;
			$scope.disableFormAction=false;
			$scope.messageTimeout();
		}
		else if($scope.jobPostDetails.interviewStartDateTime == undefined && $scope.jobPostDetails.interviewEndDateTime != undefined)
		{
			$scope.createJobPostError = $scope.errorMessageArray.interviewStartDateTimeRequired;
			$scope.error=true;
			$scope.validInput=false;
			$scope.disableFormAction=false;
			$scope.messageTimeout();
		}
		else if($scope.jobPostDetails.interviewStartDateTime !=undefined &&  $scope.jobPostDetails.interviewEndDateTime != undefined){
			var currentTime = Math.floor(Date.now() / 1000);
			var interviewstartTime = Math.floor($scope.jobPostDetails.interviewStartDateTime.getTime()/1000);
			var interviewEndTime = Math.floor($scope.jobPostDetails.interviewEndDateTime.getTime()/1000);

			if($scope.jobPostDetails.applicationDate == $scope.formatDateToString($scope.jobPostDetails.interviewStartDateTime) )
			{
				$scope.createJobPostError = $scope.errorMessageArray.interviewMustStartAfterApplicationLastDateError;
				$scope.error=true;
				$scope.validInput=false;
				$scope.disableFormAction=false;
				$scope.messageTimeout();
			}
			else if(interviewstartTime<currentTime)
			{
				$scope.createJobPostError = $scope.errorMessageArray.pastInterviewStartDateTimeError;
				$scope.error=true;
				$scope.validInput=false;
				$scope.disableFormAction=false;
				$scope.messageTimeout();	
			}
			else if(interviewEndTime<=interviewstartTime)
			{
				$scope.createJobPostError = $scope.errorMessageArray.interviewEndDateTimeLessThanStartDateTimeError;
				$scope.error=true;
				$scope.validInput=false;
				$scope.disableFormAction=false;
				$scope.messageTimeout();
			}
		}
	};
	
    
	//method to create job post
    $scope.createJobPost = function(){
    	$scope.error=false;
		$scope.success= false;
    	$scope.validInput=true;
    	$scope.disableFormAction=true;

    	if($scope.addJobDescription)
    	{
    		var fileName = document.getElementById('uploadJobDescriptionFile').value;
    		var fileExtension = fileName.substr(fileName.lastIndexOf('.')+1);
    		var documentUploaded = document.getElementById('uploadJobDescriptionFile').files[0];
    		var fileSizeInMb = documentUploaded.size/(1024*1024);
    		if(fileExtension != 'pdf')
    		{
    			$scope.createJobPostError = $scope.errorMessageArray.invalidJobDescriptionDocument;
    			$scope.error=true;
    			$scope.disableFormAction=false;
    			$scope.validInput=false;
    			$scope.messageTimeout();	
    		}
    		else if(fileSizeInMb>10)
    		{
    			$scope.createJobPostError = $scope.errorMessageArray.jobDescriptionDocumentSizeError;
    			$scope.error=true;
    			$scope.disableFormAction=false;
    			$scope.validInput=false;
    			$scope.messageTimeout();
    		}
    	}
    	
		if($scope.jobPostDetails.jobTitle == undefined || $scope.jobPostDetails.jobTitle == null || $scope.jobPostDetails.jobTitle == "")
		{
			$scope.createJobPostError = $scope.errorMessageArray.invalidJobTitleError;
			$scope.error=true;
			$scope.disableFormAction=false;
			$scope.validInput=false;
			$scope.messageTimeout();
		}
		else if($scope.jobPostDetails.minSalary >=  $scope.jobPostDetails.maxSalary)
		{
			$scope.createJobPostError = $scope.errorMessageArray.invalidMinSalaryError;
			$scope.error=true;
			$scope.disableFormAction=false;
			$scope.validInput=false;
			$scope.messageTimeout();
		}
		else if($scope.jobPostDetails.district == undefined || $scope.jobPostDetails.district == null || $scope.jobPostDetails.district == "")
		{
			$scope.createJobPostError = $scope.errorMessageArray.invalidDistrictError;
			$scope.error=true;
			$scope.disableFormAction=false;
			$scope.validInput=false;
			$scope.messageTimeout();
		}
		else if($scope.jobPostDetails.applicationDate < $scope.currentDate)
		{
			$scope.createJobPostError = $scope.errorMessageArray.invalidApplicationLastDateError;
			$scope.error=true;
			$scope.disableFormAction=false;
			$scope.validInput=false;
			$scope.messageTimeout();
		}
		else if($scope.jobPostDetails.jobSummary == undefined || $scope.jobPostDetails.jobSummary == null || $scope.jobPostDetails.jobSummary =="")
		{
			$scope.createJobPostError = $scope.errorMessageArray.invalidJobSummaryError;
			$scope.error=true;
			$scope.disableFormAction=false;
			$scope.validInput=false;
			$scope.messageTimeout();
		}
		if($scope.validInput && $scope.jobPostDetails.addAdditionalInfo != undefined && $scope.jobPostDetails.addAdditionalInfo != null && $scope.jobPostDetails.addAdditionalInfo != "")
		{
			$scope.validateAdditionalInformation();
		}
		if($scope.validInput)
		{
			var jobPostData = new FormData();
			jobPostData.append('jobTitle', $scope.jobPostDetails.jobTitle);
			jobPostData.append('minSalary', $scope.jobPostDetails.minSalary);
			jobPostData.append('maxSalary', $scope.jobPostDetails.maxSalary);
			jobPostData.append('totalVacancy', $scope.jobPostDetails.vacancy);
			jobPostData.append('state', $scope.jobPostDetails.state);
			jobPostData.append('district', $scope.jobPostDetails.district);
			jobPostData.append('minimumExperience', $scope.jobPostDetails.workExperience);
			jobPostData.append('educationQualification', $scope.jobPostDetails.qualification);
			jobPostData.append('jobRole', $scope.jobPostDetails.jobRole);
			jobPostData.append('occupation',$scope.jobPostDetails.occupation);
			jobPostData.append('applicationDate', $scope.jobPostDetails.applicationDate);
			jobPostData.append('jobSummary', $scope.jobPostDetails.jobSummary);
//			jobPostData.append('cgscCertificatePreferred',$scope.jobPostDetails.cgscCertificatePreferred);
			if($scope.addJobDescription){
				jobPostData.append('descriptionDocument', document.getElementById('uploadJobDescriptionFile').files[0]);
			}
			
			
			//non-mandatory additional fields
			if($scope.jobPostDetails.addAdditionalInfo)
			{
				if($scope.jobPostDetails.leavePolicyDetails != undefined && $scope.jobPostDetails.leavePolicyDetails != "" && $scope.jobPostDetails.leavePolicyDetails.length > 3)
				{
					jobPostData.append('leavePolicy', $scope.jobPostDetails.leavePolicyDetails);
				}
				if($scope.jobPostDetails.monthlyIncentivesDetails != undefined && $scope.jobPostDetails.monthlyIncentivesDetails != "" && $scope.jobPostDetails.monthlyIncentivesDetails.length > 3)
				{
					jobPostData.append('monthlyIncentives', $scope.jobPostDetails.monthlyIncentivesDetails);
				}
				if($scope.jobPostDetails.workTimingsDetails != undefined && $scope.jobPostDetails.workTimingsDetails != "" && $scope.jobPostDetails.workTimingsDetails.length > 3)
				{
					jobPostData.append('workTimmings', $scope.jobPostDetails.workTimingsDetails);
				}
				if($scope.jobPostDetails.contactNumber != undefined && $scope.jobPostDetails.contactNumber != "")
				{
					jobPostData.append('contactNumber', $scope.staticContentArray.mobileNumberPrefix+$scope.jobPostDetails.contactNumber);
				}
				if($scope.jobPostDetails.interviewStartDateTime != undefined && $scope.jobPostDetails.interviewStartDateTime != null)
				{
					var startTime = document.getElementById("interviewStartTime").value;
					var endTime = document.getElementById("interviewEndTime").value;
					var t1 = startTime.split('T');
					var t2 = endTime.split('T');
					var formattedStartTime = t1[0]+' '+t1[1]+':00';
					var formattedEndTime = t2[0]+' '+t2[1]+':00';
					
					jobPostData.append('interviewStartDateTime',formattedStartTime);
					jobPostData.append('interviewEndDateTime',formattedEndTime);
				}
				if($scope.jobPostDetails.isWalkinInterview != undefined)
				{
					jobPostData.append('isWalkinInterview',$scope.jobPostDetails.isWalkinInterview);
				}
				if($scope.jobPostDetails.gender != undefined)
				{
					jobPostData.append('preferredGender',$scope.jobPostDetails.gender);
				}
				if($scope.jobPostDetails.armyBackgroundPreferred != undefined)
				{
					jobPostData.append('armyBackgroundPreferred',$scope.jobPostDetails.armyBackgroundPreferred);
				}
			}
			$http({
				method: 'POST',
				url: '/createJobPost',
				data: jobPostData,
				headers: {
	               'Content-Type': undefined
				}
	        }).then(function(response){
	        	if(response.data == 1)
	        	{
	        		$scope.createJobPostSuccess = $scope.errorMessageArray.createJobPostSuccess
	        		$scope.success= true;
	        		$scope.disableFormAction=false;
	        		document.getElementById('createJobPostForm').reset();
//					$scope.jobPostDetails.cgscCertificatePreferred="";
					$scope.occupationList=[];
	        		$scope.jobPostDetails.addAdditionalInfo=false;
	        		$scope.addJobDescription = false;
	        		$scope.messageTimeout();
	        	}
	        	else
	        	{
	        		$scope.createJobPostError = $scope.errorMessageArray.createJobPostError;
	    			$scope.error=true;
	    			$scope.disableFormAction=false;
	    			$scope.messageTimeout();
	        	}
	        },function(errorResponse){
	        	$scope.createJobPostError = $scope.errorMessageArray.createJobPostError;
    			$scope.error=true;
    			$scope.disableFormAction=false;
    			$scope.messageTimeout();
	        });
		}
	};
    
	
	//method to hide error/success messages
    $scope.messageTimeout = function(){
    	$timeout(function () {
			$scope.error=false;
			$scope.success= false;
		}, 7000);
    };
    
});