var candidateProfile = angular.module("app");
candidateProfile.controller("candidateProfileController",function($scope,$http,$timeout){
	
	$scope.downloading=false;
	$scope.showEditPersonalInformation=false;
	$scope.disableUserAction=false;
	$scope.trainingPartners=[];
	
	var errorMessageUrl ="js/controllers/errorMessages.json";
	var staticContentUrl ="js/controllers/staticContent.json";
	
	var fetchCandidateDetailsUrl="/getCandidateProfileDetails";
	var updateCandidateProfileUrl="/updateCandidateProfile";

	//Function to format String(yyyy-MM-dd) to String(dd-MM-yyyy)
	let formatToDisplayDate = function(dateString){
		let dateStringArray = dateString.split('-');
		let outputDateString = [];
		outputDateString.push(dateStringArray[2]);
		outputDateString.push(dateStringArray[1]);
		outputDateString.push(dateStringArray[0]);
		outputDateString = outputDateString.join('-');
		return outputDateString;
	}

	//Function to format String of pattern(yyyy-MM-dd) to date
	$scope.formatStringToDate = function (dateString){
		let dateStringArray = dateString.split('-');
		let date= new Date(dateStringArray[2], dateStringArray[1] - 1, dateStringArray[0]);
		return date;
	}

	//Function to format date captured from FE(input type="date") to date of pattern(yyyy-mm-dd)
	let formatInputDateToyyyyMMddDate= function(inputDate, offset){
		if(offset==null || offset==undefined || offset=="")
			offset=0;
		let year = inputDate.getFullYear(),
			month = ("0" + (inputDate.getMonth() + 1)).slice(-2),
			day = ("0" + inputDate.getDate()).slice(-2);
		var date = [year - offset,month,day].join('-');
		return date;
	}

	//Request to get job role
	$http.get('/getJobRoles')
		.then(function(response){
			$scope.jobRolesListObject = response.data;
		});

	//Request to fetch states
	$http.get('/getStates')
	.then(function(response){
		$scope.statesList=[];
		let statesArray = response.data;
      	for (i in statesArray) {
      		$scope.statesList.push(statesArray[i].stateName);
      	}
	});
	
	//View list of training partners
//    $http.get('/viewTrainingPartners')
//    .then(function(response)
//    {
//    	$scope.trainingPartners = response.data;
//    });
	
	//Method to fetch error message
	$http.get(errorMessageUrl)
		.then(function(response){
			$scope.errorMessageArray = response.data[0];
		});

	//Method to fetch static content
	$http.get(staticContentUrl)
		.then(function(response){
			$scope.staticContentArray = response.data[0];
			$scope.certificateDeleteFlag=$scope.staticContentArray.falseFlag;
			//block to set max & min date for dob
			{
				let zero = $scope.staticContentArray.zero;
				let today = new Date();
				let minAge = $scope.staticContentArray.minAge;
				let maxAge = $scope.staticContentArray.maxAge;
				let maxDateForDateOfBirth = formatInputDateToyyyyMMddDate(today,minAge);
				let minDateForDateOfBirth = formatInputDateToyyyyMMddDate(today,maxAge)
				document.getElementById("dobPicker").setAttribute("max", maxDateForDateOfBirth);
				document.getElementById("dobPicker").setAttribute("min", minDateForDateOfBirth);
				$scope.maxDateForDateOfBirth = new Date(maxDateForDateOfBirth);
				$scope.minDateForDateOfBirth = new Date(minDateForDateOfBirth);

				//resetting time to zero, instead of being picked as the timezone
				$scope.maxDateForDateOfBirth.setHours(zero,zero,zero,zero);
				$scope.minDateForDateOfBirth.setHours(zero,zero,zero,zero);
			}
		}).then(function(){
			$scope.fetchCandidateDetails();
		});
	
	//Request to fetch candidate Profile details
	$scope.fetchCandidateDetails=function(){
		
		$http.get(fetchCandidateDetailsUrl)
		.then(function(response){
			$scope.candidateDetails=response.data;
			$scope.candidateDetails.dob = formatToDisplayDate($scope.candidateDetails.dob);
			let dob = response.data.dob;

			let candidateContactNumber = $scope.candidateDetails.mobileNumber.toString().slice(2);
			$scope.candidateDetails.mobileNumber=parseInt(candidateContactNumber);
			
			let guardianContactNumber = $scope.candidateDetails.guardianMobileNumber.toString().slice(2);
			$scope.candidateDetails.guardianMobileNumber=parseInt(guardianContactNumber);
			
			$scope.profileDetails=response.data;

			dob = $scope.formatStringToDate(dob);
			$scope.profileDetails.dob = dob;

			$scope.profileDetails.jobRoleDropdown = $scope.profileDetails.jobRoleId;

			document.getElementById('updateProfileCandidateResume').value=$scope.candidateDetails.candidateName+$scope.staticContentArray.underScore+$scope.staticContentArray.resumeText;
			
			if($scope.candidateDetails.certificatesPath != null){
				document.getElementById('updateProfileCandidateCertificates').value=$scope.candidateDetails.candidateName+$scope.staticContentArray.underScore+$scope.staticContentArray.certificatesText;				
			}
			if($scope.profileDetails.tpName!=null || $scope.profileDetails.tpName!=undefined || $scope.profileDetails.tpName!=""){
				$scope.profileDetails.trainingPartner = $scope.profileDetails.tpName;
			}
		});
	}
	
	//Method to download resume
    $scope.downloadResume=function(){
    	$scope.downloading=true;
    	$scope.loadingGif=true;
    	$scope.error=false;
    	$scope.success=false;
    	
    	let downloadResumeUrl ="/downloadPdfFile/"+$scope.candidateDetails.resumePath;
		$http.get(downloadResumeUrl, {
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
				link.download =$scope.candidateDetails.candidateName+$scope.staticContentArray.underScore+$scope.staticContentArray.resumeText;
				document.body.appendChild(link);
				link.click();
				$scope.loadingGif=false;
				$scope.downloading=false;
			} else {
				$scope.updateProfileError = $scope.errorMessageArray.resumeDownloadError;
				$scope.error=true;
				$scope.loadingGif=false;
				$scope.downloading=false;
				$timeout(function(){
					$scope.error=false;
				},7000);
			}
			
		},function(error){
			$scope.updateProfileError = $scope.errorMessageArray.resumeDownloadError;
			$scope.error=true;
			$scope.loadingGif=false;
			$scope.downloading=false;
			$timeout(function(){
				$scope.error=false;
			},7000);
		});
    }
    
    
  //Method to download certificates
    $scope.downloadCertificates=function(){
    	$scope.downloading=true;
    	$scope.loadingGif=true;
    	$scope.error=false;
    	$scope.success=false;
    	
    	let downloadCertificatesUrl ="/downloadZipFile/"+$scope.candidateDetails.certificatesPath;
		$http.get(downloadCertificatesUrl, {
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
				link.download =$scope.candidateDetails.candidateName+$scope.staticContentArray.underScore+$scope.staticContentArray.certificatesText;
				document.body.appendChild(link);
				link.click();
				$scope.loadingGif=false;
				$scope.downloading=false;
			} else {
				$scope.updateProfileError = $scope.errorMessageArray.certificatesDownloadError;
				$scope.error=true;
				$scope.loadingGif=false;
				$scope.downloading=false;
				$timeout(function(){
					$scope.error=false;
				},7000);
			}
			
		},function(error){
			$scope.updateProfileError = $scope.errorMessageArray.certificatesDownloadError;
			$scope.error=true;
			$scope.loadingGif=false;
			$scope.downloading=false;
			$timeout(function(){
				$scope.error=false;
			},7000);
		});
    }
    
    
    
    
    /** --------------------------------------METHODS FOR EDIT/UPDATE PROFILE SECTION-------------------------------------------------------------------------**/
    
    
    //Edit Personal Information Section
	$scope.editPersonalInformation = function(){
		$scope.showEditPersonalInformation=true;
	}
	
    //Method to delete certificates
    $scope.deleteCertificates = function(){
    	$scope.certificateDeleteFlag=$scope.staticContentArray.trueFlag;
    	document.getElementById('updateProfileCandidateCertificates').value="";
    	document.getElementById('candidateCertificateDocuments').value="";
    }
    
    //Method to set delete flag to false if certificates are uploaded
    $scope.uploadCandidateCertificates=function(){
    	$scope.certificateDeleteFlag=$scope.staticContentArray.falseFlag;
    }
    
    
    //Method to update candidate profile details
    $scope.updateProfileDetails=function(){
    	$scope.error=false;
		$scope.loadingGif=true;
    	$scope.disableUpdateButton=true;
    	$scope.postCandidateDetails=true;
    	$scope.disableUserAction=true;
    	$scope.updateDetails=true;


    	if($scope.profileDetails.candidateName==undefined || $scope.profileDetails.candidateName==null || $scope.profileDetails.candidateName=="")
     	{
	        $scope.updateProfileError = $scope.errorMessageArray.invalidCandidateNameInputError;
			$scope.updateDetails=false;
			$scope.error=true;
	        $scope.loadingGif=false;
	        $scope.disableUserAction=false;
	        $scope.disableUpdateButton=false;
			$scope.updateDetails=false;

			$scope.hideMessages();
     	}
		else if($scope.profileDetails.pincode.toString().length != 6)
		{
			$scope.updateProfileError = $scope.errorMessageArray.invalidPincodeLengthError;
			$scope.updateDetails=false;
			$scope.error=true;
			$scope.loadingGif=false;
			$scope.disableUserAction=false;
			$scope.disableUpdateButton=false;
			$scope.updateDetails=false;

			$scope.hideMessages();
		}
		else if($scope.profileDetails.guardianMobileNumber==undefined || $scope.profileDetails.guardianMobileNumber.toString().length !=10)
		{
			$scope.updateProfileError = $scope.errorMessageArray.guardianMobileNumberLengthError;
			$scope.updateDetails=false;
			$scope.error=true;
			$scope.loadingGif=false;
			$scope.disableUserAction=false;
			$scope.disableUpdateButton=false;
			$scope.updateDetails=false;

			$scope.hideMessages();
		}
		//certified, but didn't enter certificate details
//		else if($scope.profileDetails.isCgscCertified==$scope.staticContentArray.trueFlag && ($scope.profileDetails.certificateNumber==null || $scope.profileDetails.certificateNumber==undefined || $scope.profileDetails.certificateNumber=="")){
//			$scope.updateProfileError = $scope.errorMessageArray.certificateNumberError;
//			$scope.updateDetails=false;
//			$scope.error=true;
//			$scope.loadingGif=false;
//			$scope.disableUserAction=false;
//			$scope.disableUpdateButton=false;
//			$scope.updateDetails=false;
//			$scope.hideMessages();
//		}
//		//certified, but didn't enter tpname
//		else if($scope.profileDetails.isCgscCertified==$scope.staticContentArray.trueFlag && ($scope.profileDetails.trainingPartner==null || $scope.profileDetails.trainingPartner==undefined || $scope.profileDetails.trainingPartner=="")){
//			$scope.updateProfileError = $scope.errorMessageArray.trainingPartnerDetailsError;
//			$scope.updateDetails=false;
//			$scope.error=true;
//			$scope.loadingGif=false;
//			$scope.disableUserAction=false;
//			$scope.disableUpdateButton=false;
//			$scope.updateDetails=false;
//			$scope.hideMessages();
//		}
		else if($scope.profileDetails.aadhaarNumber==undefined ||$scope.profileDetails.aadhaarNumber.toString().length != 12){
			$scope.updateProfileError=$scope.errorMessageArray.aadhaarNumberLengthError;
			$scope.updateDetails=false;
			$scope.error=true;
			$scope.loadingGif=false;
			$scope.disableUserAction=false;
			$scope.disableUpdateButton=false;
			$scope.updateDetails=false;
			$scope.hideMessages();
		}
		else if($scope.profileDetails.aadhaarNumber<=0) {
			$scope.updateProfileError=$scope.errorMessageArray.aadhaarNumberNegativeError;
			$scope.updateDetails=false;
			$scope.error=true;
			$scope.loadingGif=false;
			$scope.disableUserAction=false;
			$scope.disableUpdateButton=false;
			$scope.updateDetails=false;
			$scope.hideMessages();
		}
		else if($scope.profileDetails.guardianName==undefined || $scope.profileDetails.guardianName==null || $scope.profileDetails.guardianName==""){
			$scope.updateProfileError = $scope.errorMessageArray.invalidGuardianNameInputError;
			$scope.updateDetails=false;
			$scope.error=true;
			$scope.loadingGif=false;
			$scope.disableUserAction=false;
			$scope.disableUpdateButton=false;
			$scope.updateDetails=false;
			$scope.hideMessages();
		}
		else if($scope.profileDetails.address==undefined || $scope.profileDetails.address==null || $scope.profileDetails.address==""){
			$scope.updateProfileError = $scope.errorMessageArray.invalidAddressLengthError;
			$scope.updateDetails=false;
			$scope.error=true;
			$scope.loadingGif=false;
			$scope.disableUserAction=false;
			$scope.disableUpdateButton=false;
			$scope.updateDetails=false;
			$scope.hideMessages();
		}
		else if($scope.profileDetails.jobRoleDropdown==undefined || $scope.profileDetails.jobRoleDropdown==null || $scope.profileDetails.jobRoleDropdown==""){
			$scope.updateProfileError = $scope.errorMessageArray.invalidJobRoleError;
			$scope.updateDetails=false;
			$scope.error=true;
			$scope.loadingGif=false;
			$scope.disableUserAction=false;
			$scope.disableUpdateButton=false;
			$scope.updateDetails=false;
			$scope.hideMessages();
		}
		//checking if dob is b/w acceptable range
		else if ($scope.profileDetails.dob==undefined || $scope.profileDetails.dob> $scope.maxDateForDateOfBirth || $scope.profileDetails.dob<$scope.minDateForDateOfBirth ) {
			$scope.updateProfileError = $scope.errorMessageArray.invalidDobError + formatToDisplayDate(formatInputDateToyyyyMMddDate($scope.minDateForDateOfBirth)) +" "+ $scope.staticContentArray.andText + " "+ formatToDisplayDate(formatInputDateToyyyyMMddDate($scope.maxDateForDateOfBirth));
			$scope.error=true;
			$scope.updateDetails=false;
			$scope.loadingGif=false;
			$scope.disableUserAction=false;
			$scope.disableUpdateButton=false;
			$scope.hideMessages();
		}
		//should be kept at last, since it'll go in the loop for checking, if the training partner entered is valid
//		else if($scope.profileDetails.isCgscCertified==$scope.staticContentArray.trueFlag){
//				for (let i = 0; i < $scope.trainingPartners.length; i++) {
//					if (typeof $scope.profileDetails.trainingPartner == "string" && $scope.trainingPartners[i].trainingPartnerName == $scope.profileDetails.trainingPartner) {
//						//allow registration
//						$scope.updateDetails = true;
//						break;
//					} else if (typeof $scope.profileDetails.trainingPartner == "object" && $scope.trainingPartners[i].userId == $scope.profileDetails.trainingPartner.userId) {
//						//allow registration
//						$scope.updateDetails = true;
//						break;
//					} else {
//						$scope.updateDetails = false;
//					}
//				}
//				if($scope.updateDetails==false){
//					$scope.updateProfileError = $scope.errorMessageArray.trainingPartnerInvalidDetailsError;
//					$scope.error=true;
//					$scope.loadingGif=false;
//					$scope.disableUserAction=false;
//					$scope.disableUpdateButton=false;
//					$scope.hideMessages();
//				}
//		}

		if($scope.updateDetails==true)
		{
		
			var updatedProfileDetails = new FormData();
        	let candidateMobileNumber = $scope.staticContentArray.mobileNumberPrefix+$scope.profileDetails.mobileNumber;
        	let guardianMobileNumber = $scope.staticContentArray.mobileNumberPrefix+$scope.profileDetails.guardianMobileNumber;
        	updatedProfileDetails.append('userId',$scope.profileDetails.userId);
        	updatedProfileDetails.append('candidateName',$scope.profileDetails.candidateName);
        	updatedProfileDetails.append('mobileNumber',candidateMobileNumber)
        	updatedProfileDetails.append('guardianMobileNumber',guardianMobileNumber);
        	updatedProfileDetails.append('professionalExperience',$scope.profileDetails.professionalExperience);
        	updatedProfileDetails.append('educationQualification',$scope.profileDetails.educationQualification);
        	updatedProfileDetails.append('gender',$scope.profileDetails.gender);
        	updatedProfileDetails.append('state',$scope.profileDetails.state);
        	updatedProfileDetails.append('pincode',$scope.profileDetails.pincode);
        	updatedProfileDetails.append('exArmyPersonnel',$scope.profileDetails.exArmyPersonnel);
        	updatedProfileDetails.append('certificateDeleteFlg',$scope.certificateDeleteFlag);
        	//dob changed to yyyy-mm-dd format
			updatedProfileDetails.append('dob',formatInputDateToyyyyMMddDate($scope.profileDetails.dob));
			updatedProfileDetails.append('aadhaarNumber',$scope.profileDetails.aadhaarNumber);
			updatedProfileDetails.append('jobRoleId',$scope.profileDetails.jobRoleDropdown);
			updatedProfileDetails.append('address',$scope.profileDetails.address);
			updatedProfileDetails.append('guardianName',$scope.profileDetails.guardianName);

//			updatedProfileDetails.append('isCgscCertified',$scope.profileDetails.isCgscCertified);

//			if($scope.profileDetails.isCgscCertified==$scope.staticContentArray.trueFlag){
//				updatedProfileDetails.append('certificateNumber',$scope.profileDetails.certificateNumber);
//				if(typeof($scope.profileDetails.trainingPartner) == "string"){
//					for(let i=0;i<$scope.trainingPartners.length;i++){
//						if ($scope.trainingPartners[i].trainingPartnerName==$scope.profileDetails.trainingPartner){
//							updatedProfileDetails.append('tpId',$scope.trainingPartners[i].userId)
//							break;
//						}
//					}
//				}
//				else {
//					updatedProfileDetails.append('tpId', $scope.profileDetails.trainingPartner.userId);
//				}
//			}
			
        	if(document.getElementById('candidateResumeDocument').files[0]!=undefined){
        		
        		var candidateResume = document.getElementById('candidateResumeDocument').files[0];
	        	var fileName = document.getElementById('updateProfileCandidateResume').value;
	      	    var fileExtensionPdf = fileName.substr(fileName.lastIndexOf('.')+1);
	      	    if(fileExtensionPdf =='pdf')
	      	    {
	      	    	//File extension is .pdf, checking the file size
	      	    	var fileSizeInMb = candidateResume.size/(1024*1024);
	      	    	if(fileSizeInMb>$scope.staticContentArray.pdfFileSize10Mb)
	      	    	{
	      	    		$scope.postCandidateDetails=false;
	      	    		$scope.updateProfileError = $scope.errorMessageArray.resumeSizeGreaterThanLimit;
	      				$scope.error=true;	
	      				$scope.loadingGif=false;
	      				$scope.disableUpdateButton=false;
	      				$scope.disableUserAction=false;
	      				$scope.hideMessages();
	      				
	      	    	}	
	      	    	else
	      	    	{
	      	    		$scope.postCandidateDetails=true;
	      	    		updatedProfileDetails.append('candidateResume',candidateResume);
	      	    	}
	      	    }
	      	    
	      	  else
	      	    {
	      	    	$scope.postCandidateDetails=false;
      	    		$scope.updateProfileError = $scope.errorMessageArray.invalidResumeFileType;
      				$scope.error=true;	
      				$scope.loadingGif=false;
      				$scope.disableUserAction=false;
      				$scope.disableUpdateButton=false;
      				$scope.hideMessages();
	      	    }
	      	}
        	
        	if(document.getElementById('candidateCertificateDocuments').files[0]!=undefined && $scope.postCandidateDetails==true && $scope.certificateDeleteFlag == $scope.staticContentArray.falseFlag)
        	{
        		var candidateCertificatesFile = document.getElementById('candidateCertificateDocuments').files[0];
 	        	let zipFileName = document.getElementById('updateProfileCandidateCertificates').value;
 	      	    let fileExtensionZip = zipFileName.substr(zipFileName.lastIndexOf('.')+1);
 	      	    if(fileExtensionZip=='zip')
	      	    {
	      	    	var zipFileSizeInMb = candidateCertificatesFile.size/(1024*1024);
	      	    	if(zipFileSizeInMb>$scope.staticContentArray.zipFileSize50Mb)
	      	    	{
	      	    		$scope.postCandidateDetails=false;
	      	    		$scope.updateProfileError = $scope.errorMessageArray.certificateSizeGreaterThanLimit;
	      				$scope.error=true;	
	      				$scope.loadingGif=false;
	      				$scope.disableUserAction=false;
	      				$scope.disableUpdateButton=false;
	      				$scope.hideMessages();
	      	    	}
	      	    	else
	      	    	{
	      	    		$scope.postCandidateDetails=true;
	      	    		updatedProfileDetails.append('candidateCertificates',candidateCertificatesFile);
	      	    	}
	      	    }
	   	    	else
	   	    	{
	   	    		$scope.postCandidateDetails=false;
      	    		$scope.updateProfileError = $scope.errorMessageArray.invalidCertificateFileType;
      				$scope.error=true;	
      				$scope.loadingGif=false;
      				$scope.disableUserAction=false;
      				$scope.disableUpdateButton=false;
      				$scope.hideMessages();
	   	    	}
 	      	 
        	}


        	        	
        	//Section to update candidate profile if all user input are valid
        	if($scope.postCandidateDetails)
      	    {
        		$http({
    				method: 'POST',
    				url: updateCandidateProfileUrl,
    				data: updatedProfileDetails,
    				headers: {
    	               'Content-Type': undefined
    				}
    	        }).then(function(response){
    	        	if(response.data == 1){
    	        		$scope.certificateDeleteFlag=$scope.staticContentArray.falseFlag;
    	        		$scope.updateProfileSuccess=$scope.errorMessageArray.updateCandidateProfileSuccess;
    	        		$scope.success=true;	
          				$scope.loadingGif=false;
          				$scope.disableUpdateButton=false;
          				$scope.disableUserAction=false;
          				$scope.hideMessages();
          				$scope.fetchCandidateDetails();
          				document.getElementById('candidateCertificateDocuments').value="";
          				document.getElementById('candidateResumeDocument').value="";
          				$scope.showEditPersonalInformation=false;
    	        	}
    	        	else{
    	        		$scope.updateProfileError=$scope.errorMessageArray.updateCandidateProfileError;
    	        		$scope.error=true;	
          				$scope.loadingGif=false;
          				$scope.disableUserAction=false;
          				$scope.disableUpdateButton=false;
          				$scope.hideMessages();
    	        	}
      				
    	        },function(error){
    	        	$scope.updateProfileError=$scope.errorMessageArray.updateCandidateProfileError;
	        		$scope.error=true;	
      				$scope.loadingGif=false;
      				$scope.disableUserAction=false;
      				$scope.disableUpdateButton=false;
      				$scope.hideMessages();
    	        });
      	    }//If block to post candidate details ends here
       }
	};

	$scope.updateDetails=true;

    //Method to hide error/success messages
    $scope.hideMessages = function(){
    	$timeout(function() {
    		$scope.error=false;
    		$scope.success=false;
    	}, 7000);
    }

	//Method to go back from update profile form to 
	$scope.goBack = function(){
		$scope.showEditPersonalInformation=false;
	}
});