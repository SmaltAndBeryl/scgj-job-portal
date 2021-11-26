var app = angular.module('app', ['ngRoute',
    'ui.grid',
    'ui.grid.edit',
    'ui.grid.cellNav',
    'ui.grid.autoResize',
    'ui.bootstrap',
    'ui.grid.pagination',
    'ui.grid.selection', 
    'ui.grid.exporter'
    
]);

app.config(function($routeProvider, $httpProvider) {
    $routeProvider.when('/admin', {
            templateUrl: 'admin.html',
            controller: 'adminController'
    	})
    	.when('/employer', {
            templateUrl: 'employer.html',
            controller: 'employerController'
    	})
    	.when('/candidate', {
            templateUrl: 'candidate.html',
            controller: 'candidateController'
    	})
		.when('/error',{
			templateUrl: 'error.html',
            controller: 'errorController'
		})
        .otherwise('/');
    $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';

})
.run(function($rootScope, $location) {
    $rootScope.$on( "$routeChangeStart", function(event, next, current) {
  	  if ($rootScope.userRole=='"Employer"') {
			$location.path( "/employer");
		}
		else if ($rootScope.userRole=='"Admin"') {
			$location.path( "/admin");
		}
		else if ($rootScope.userRole=='"Candidate"') {
			$location.path( "/candidate");
		}
		else{
			$location.path( "/");
		}
	})
});


app.controller('mainController', function($rootScope, $scope,$http, $location, $route, $window, $timeout) {
	$rootScope.mainTemplate = true;
	$scope.homePage=true;
	$scope.showMyProfileSection=false;
	$scope.showOtpInput=false;
	$scope.selectUserRole=true;
	$scope.loginError=false;
	
	$scope.showIndustryTypeTextInput=false;
	$scope.registrationError=false;
    $scope.registerationSuccess=false;
    $scope.disableEmployerRegistrationButton = false;
    $scope.showLoginText=true;
	
	$scope.companyTypeList=[];
	$scope.industryTypeList=[];
	$scope.companyScaleList=[];
	$scope.professionalExperienceList=[];
	$scope.educationQualificationList=[];
	$scope.employer={};
	$scope.statesList=[];
	$scope.trainingPartners=[];
	$scope.disableUploadButton=false;


	$scope.maxCardsLimit = 8;
	$scope.jobRoleList=[];
	$scope.employerList=[];
	$scope.filteredJobPosts=[];
    $scope.allJobPosts=[];
    $scope.showRemoveFilterText=false;
    $scope.viewJobPostSection=false;
    $scope.showModalButton=false;
    $scope.showDownloadDescriptionGif=false;
    $scope.showCertificationDetails=false;
	
	var errorMessageUrl ="js/controllers/errorMessages.json";
	var staticContentUrl ="js/controllers/staticContent.json";
	var viewAllJobPostsUrl="/viewJobPost";
	var getAllEmployersUrl="/getEmployers";	
	var downloadDocumentUrl = "/downloadPdfFile/";

	//function to format date to yyyy-MM-dd
	$scope.formatDate = function (dateStr) {
		var date = new Date(dateStr),
			month = ("0" + (date.getMonth() + 1)).slice(-2),
			day = ("0" + date.getDate()).slice(-2);
		return [date.getFullYear(), month, day].join("-");
	}

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

	//function to format String to date
	let stringToDate = function (dateStr){
		var date =dateStr.split('-');
		var formattedDate = new Date(date[0], date[1] - 1, date[2]);
		return formattedDate;
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

	//Method to fetch error message
	$http.get(errorMessageUrl)
		.then(function(response){
			$scope.errorMessageArray = response.data[0];
			$scope.noJobPostsAvailableError = $scope.errorMessageArray.noJobPostsAvailableError;
		});
	
	//Method to fetch static content
	$http.get(staticContentUrl)
		.then(function(response){
			$scope.staticContentArray = response.data[0];
			//block to set max & min date for dob
			{
				let zero = $scope.staticContentArray.zero;
				let today = new Date();
				let minAge = $scope.staticContentArray.minAge;
				let maxAge = $scope.staticContentArray.maxAge;
				let maxDateForDateOfBirth = formatInputDateToyyyyMMddDate(today,minAge);
				let minDateForDateOfBirth = formatInputDateToyyyyMMddDate(today,maxAge);
				document.getElementById("datePicker").setAttribute("max", maxDateForDateOfBirth);
				document.getElementById("datePicker").setAttribute("min",minDateForDateOfBirth);
				$scope.maxDateForDateOfBirth = new Date(maxDateForDateOfBirth);
				$scope.minDateForDateOfBirth = new Date(minDateForDateOfBirth);

				//resetting time to zero, instead of being picked as the timezone
				$scope.maxDateForDateOfBirth.setHours(zero,zero,zero,zero);
				$scope.minDateForDateOfBirth.setHours(zero,zero,zero,zero);

			}
		});
	
	//Request to get job role
    $http.get('/getJobRoles')
	.then(function(response){
		$scope.jobRolesObject = response.data;
		let jobRolesArray = response.data;
      	for (i in jobRolesArray) {
      		$scope.jobRoleList.push(jobRolesArray[i].jobRole);
      	}
	});
    $(document).ready(function(){
    	  $('[data-toggle="popover"]').popover();   
    	});
    
    //View list of training partners
    $http.get('/viewTrainingPartners')
    .then(function(response)
    {
    	$scope.trainingPartners = response.data;
    });
    
  //Request to get employer details
    $http.get(getAllEmployersUrl)
	.then(function(response){
		let employerListArray = response.data;
      	for (i in employerListArray) {
      		$scope.employerList.push(employerListArray[i].employerName);
      	}
	});
    
    
   //Method to view user profile text on homepage
    $scope.changeProfileText = function(){
    	if($rootScope.userRole != undefined && $rootScope.userRole != null && $rootScope.userRole != ""){
    		$scope.showLoginText=false;
    	}
    	else{
    		$scope.showLoginText=true;
    	}
    }

	//Method to fetch terms and conditions path
	$http.get("/getTermsAndConditions")
	.then(function(response){
		$scope.termsAndConditionsFilePath=response.data.filepath;
	});
    
   
   /** ------------------------------- VIEW AND FILTER JOB POST SECTION ----------------------------------------**/
    
    //Request to get all job post details
    $scope.getAllJobPosts=function(){
    	$http.get(viewAllJobPostsUrl)
    	.then(function(response){
    		$scope.allJobPosts = response.data;
    		$scope.filteredJobPosts =$scope.allJobPosts.slice(0);
    	});
    }
    $scope.getAllJobPosts();
	
    
   //Method to populate job occupations
   $scope.populateJobOccupation = function(){
	   	$scope.occupationList=[];
		let viewOccupationUrl ="/getOccupationByJobRole?jobRole="+$scope.filterOptions.jobRole;
			$http.get(viewOccupationUrl)
	    	.then(function(response){
	    		let occupationListArray = response.data;
	          	for (i in occupationListArray) {
	          		$scope.occupationList.push(occupationListArray[i].occupationName);
	          	}
	    	});
			$scope.filterJobPosts();
	}
    
    //Method to filter job posts
	$scope.filterJobPosts= function()
	{
		var filterForEmployer = false;
		var filterForJobRole = false;
		var filterForState=false;
		var filterForOccupation=false;
		
		$scope.showRemoveFilterText=true;
		
		//check if occupation is selected
		if($scope.filterOptions.occupation != undefined && $scope.filterOptions.occupation != null && $scope.filterOptions.occupation != ""){
			filterForOccupation=true;
		}
		
		//check if job role is selected
		if($scope.filterOptions.jobRole != undefined && $scope.filterOptions.jobRole != null && $scope.filterOptions.jobRole != ""){
			filterForJobRole = true;				
		}
		
		//check if employer selected
		if( $scope.filterOptions.employer != undefined && $scope.filterOptions.employer != null && $scope.filterOptions.employer != ""){
			filterForEmployer = true;
		}
		
		//check if state selected
		if($scope.filterOptions.state != undefined && $scope.filterOptions.state != null && $scope.filterOptions.state != ""){
			filterForState = true;
		}
		
		// filter  based on job role only
		if(filterForJobRole==true && filterForEmployer == false && filterForState == false && filterForOccupation==false){
			$scope.filteredJobPosts = $scope.allJobPosts.filter(function (e) {
				 return e.jobRole  == $scope.filterOptions.jobRole;
			 });
		}
		
		//filter based on employer only 
		else if(filterForJobRole==false && filterForEmployer == true && filterForState == false){
			$scope.filteredJobPosts = $scope.allJobPosts.filter(function (e) {
				 return e.postedBy  == $scope.filterOptions.employer;
			 });			
		}
		
		//filter based on state only 
		else if(filterForJobRole==false && filterForEmployer == false && filterForState == true){
			$scope.filteredJobPosts = $scope.allJobPosts.filter(function (e) {
				 return e.state  == $scope.filterOptions.state;
			 });			
		}
		
		//filter on job role and occupation only
		else if(filterForJobRole==true && filterForEmployer == false && filterForState == false && filterForOccupation==true){
			$scope.filteredJobPosts = $scope.allJobPosts.filter(function (e) {
				 return e.jobRole  == $scope.filterOptions.jobRole &&  e.occupation  == $scope.filterOptions.occupation;
			 });
		}
		
		//filter on both employer and job role but not occupation
		else if(filterForJobRole==true && filterForEmployer == true && filterForState == false && filterForOccupation==false){
			$scope.filteredJobPosts = $scope.allJobPosts.filter(function (e) {
				 return e.jobRole  == $scope.filterOptions.jobRole &&  e.postedBy  == $scope.filterOptions.employer;
			 });
		}
		
		//filter on employer and job role and occupation
		else if(filterForJobRole==true && filterForEmployer == true && filterForState == false && filterForOccupation==true){
			$scope.filteredJobPosts = $scope.allJobPosts.filter(function (e) {
				 return e.jobRole  == $scope.filterOptions.jobRole &&  e.postedBy  == $scope.filterOptions.employer && e.occupation == $scope.filterOptions.occupation;
			 });
		}
		
		//filter on both job role and state but not occupation
		else if(filterForJobRole==true && filterForState == true && filterForEmployer == false && filterForOccupation==false){
			$scope.filteredJobPosts = $scope.allJobPosts.filter(function (e) {
				 return e.jobRole  == $scope.filterOptions.jobRole && e.state  == $scope.filterOptions.state;
			 });
		}
		
		//filter on job role and state and occupation
		else if(filterForJobRole==true && filterForState == true && filterForEmployer == false && filterForOccupation==true){
			$scope.filteredJobPosts = $scope.allJobPosts.filter(function (e) {
				 return e.jobRole  == $scope.filterOptions.jobRole && e.state  == $scope.filterOptions.state && e.occupation == $scope.filterOptions.occupation;
			 });
		}
		
		//filter both employer and state
		else if(filterForEmployer == true && filterForState==true && filterForJobRole==false ){
			$scope.filteredJobPosts = $scope.allJobPosts.filter(function (e) {
				 return e.postedBy  == $scope.filterOptions.employer && e.state  == $scope.filterOptions.state;
			 });
		}
		
		//filter on both employer and job role and state but not occupation
		else if(filterForJobRole==true && filterForEmployer == true && filterForState == true && filterForOccupation==false){
			$scope.filteredJobPosts = $scope.allJobPosts.filter(function (e) {
				 return e.jobRole  == $scope.filterOptions.jobRole &&  e.postedBy  == $scope.filterOptions.employer && e.state  == $scope.filterOptions.state;
			 });
		}
		
		//filter on both employer and job role and state and occupation
		else if(filterForJobRole==true && filterForEmployer == true && filterForState == true && filterForOccupation==true){
			$scope.filteredJobPosts = $scope.allJobPosts.filter(function (e) {
				 return e.jobRole  == $scope.filterOptions.jobRole &&  e.postedBy  == $scope.filterOptions.employer && e.state  == $scope.filterOptions.state &&  e.occupation  == $scope.filterOptions.occupation;
			 });
		}
		
	}
	
	
	//Method to Remove Filter
	$scope.removeFilter = function()
	{
		$scope.showRemoveFilterText=false;
		$scope.filterOptions.jobRole="";
		$scope.filterOptions.occupation="";
		$scope.filterOptions.employer="";
		$scope.filterOptions.state="";
		$scope.getAllJobPosts();
	}
    
	
    //Method to view job post details
	$scope.viewJobPost = function(jobPostDetails,index){
		$scope.viewJobPostSection=true;
		$scope.viewJobPostDetails = jobPostDetails;
		if(jobPostDetails.descriptionDocumentPath == null){
			$scope.noJobDescriptionFlag = true;
		}else{
			$scope.noJobDescriptionFlag = false;
		}
	}
    
    //Method to view all job posts
	$scope.viewAllJobPostsSearched = function(){
		$scope.viewJobPostSection=false;
	}
    
    
	//Method to view home page
	$scope.showHomePage = function(){
		$scope.homePage = true;
		$scope.showMyProfileSection=false;
		$rootScope.mainTemplate = true;
		angular.element(document.querySelector("#homePageOptionText")).addClass("selectedMenuOrange");
		angular.element(document.querySelector("#profilePageOptionText")).removeClass("selectedMenuOrange");
	}
	
	//Method to load more jobs
	$scope.loadMoreJobs = function(){
		if($scope.maxCardsLimit<$scope.filteredJobPosts.length){
			$scope.maxCardsLimit=$scope.maxCardsLimit+8;
		}
	}
	
	
	//Method to apply for a job post
	$scope.applyForJob = function(jobId)
	{
		$scope.showModalButton=false;
		if($rootScope.userRole == "" || $rootScope.userRole == undefined || $rootScope.userRole == null )
		{
			$('#homePageErrorModal').modal('show');
			$scope.applyForJobErrorMessage=$scope.errorMessageArray.loginToApplyError;
			$scope.showModalButton=true;
		}
		else if($rootScope.userRole == $scope.staticContentArray.userRoleEmployer || $rootScope.userRole == $scope.staticContentArray.userRoleAdmin )
		{
			$('#homePageErrorModal').modal('show');
			$scope.applyForJobErrorMessage=$scope.errorMessageArray.onlyCandidateCanApplyForJobError;
		}
		else if($rootScope.userRole == $scope.staticContentArray.userRoleCandidate)
		{
			var applyForJob = "/applyForJobPost?jobId="+jobId;
			$http.get(applyForJob)
			.then(function(response){
				if(response.data == 1){
					$scope.applyForJobSuccessMessage=$scope.errorMessageArray.applyForJobSuccess;
					$('#homePageSuccessModal').modal('show');
				}
				else if(response.data == - 60){
					$('#homePageErrorModal').modal('show');
					$scope.applyForJobErrorMessage=$scope.errorMessageArray.alreadyAppliedForJob;
				}
				else{
					$('#homePageErrorModal').modal('show');
					$scope.applyForJobErrorMessage=$scope.errorMessageArray.applyForJobError;
				}
			},function(){
				$('#homePageErrorModal').modal('show');
				$scope.applyForJobErrorMessage=$scope.errorMessageArray.applyForJobError;
			});
			
		}
	}
	
	
	// --- Method to switch to login page when login button in modal is clicked
	$scope.loginToApply = function(){
		$timeout(function(){
			$scope.credential={};
			$scope.showMyProfile();
		},400);
	}
	
	// --- Method to switch to sign up page when login button in modal is clicked
	$scope.signUpToApply = function(){
		$timeout(function(){
			$scope.showMyProfile();
			$scope.openRegistrationSection();
		},400);
	}
	
	//Method to download job description document 
    $scope.downloadJobDescription = function(jobPostInfo){
    	$scope.downloadJobDescriptionError = false;
    	$scope.showDownloadDescriptionGif=true;
    	let viewDescriptionDocumentUrl ="/downloadPdfFile/"+jobPostInfo.descriptionDocumentPath;
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
				link.download =jobPostInfo.jobId+$scope.staticContentArray.underScore+$scope.staticContentArray.descriptionDocumentText;
				document.body.appendChild(link);
				link.click();
				$scope.showDownloadDescriptionGif=false;
			} else {
				$scope.downloadJobDescriptionErrorMessage = $scope.errorMessageArray.descriptionDocumentDownloadError;
				$scope.downloadJobDescriptionError=true;
				$scope.showDownloadDescriptionGif=false;
			}
			$timeout(function(){
				$scope.downloadJobDescriptionError=false;
			},7000);
			
		},function(error){
			$scope.downloadJobDescriptionErrorMessage = $scope.errorMessageArray.descriptionDocumentDownloadError;
			$scope.downloadJobDescriptionError=true;
			$scope.showDownloadDescriptionGif=false;
			$timeout(function(){
				$scope.downloadJobDescriptionError=false;
			},7000);
		});
    }
    
	
	/** --------------------------------------------------------------------------------------------------------------------**/
	
	/**
	 * METHOD TO VIEW MY PROFILE SECTION
	 */
	$scope.showMyProfile = function(){
		$scope.showLoginForm = true;
		$scope.allowLogin=false;
		$scope.showRegistrationForm = false;
		$scope.loginError = false;
		$scope.processingOTP=false;
		if($rootScope.userRole != undefined && $rootScope.userRole != null && $rootScope.userRole != "")
		{
			if ($rootScope.userRole == '"Employer"') {
				$rootScope.mainTemplate = false;
				$location.path("/employer");
            }
			else if($rootScope.userRole == '"Candidate"') {
				$rootScope.mainTemplate = false;
				$location.path("/candidate");
				$window.location.reload();
            }
			else if($rootScope.userRole == '"Admin"') {
				$rootScope.mainTemplate = false;
				$location.path("/admin");
            }
		}
		else{
			$scope.homePage = false;
			$scope.showMyProfileSection=true;
			$scope.showOtpInput = false;
			$rootScope.mainTemplate = true;
			document.getElementById('userLoginForm').reset();
			angular.element(document.querySelector("#homePageOptionText")).removeClass("selectedMenuOrange");
			angular.element(document.querySelector("#profilePageOptionText")).addClass("selectedMenuOrange");
		}
	}
	
	/** ------------------------------------------------------USER LOGIN SECTION------------------------------------------------------------ */

	//Method to view Login section
	$scope.openLoginSection = function(){
		$scope.showLoginForm = true;
		document.getElementById('employerRegistrationForm').reset();
		document.getElementById('userLoginForm').reset();
		$scope.showRegistrationForm = false;
		$scope.showLoginForm = true;
	}
	
	/** ------------------------------------------------------USER REGISTRATION SECTION------------------------------------------------------------ */
	
	//Method to view registration section
	$scope.openRegistrationSection = function(){
		$scope.showLoginForm = false;
		$scope.registrationError=false;
	    $scope.registerationSuccess=false;
	    $scope.disableEmployerRegistrationButton = false;
	    $scope.selectUserRole=true;
	}
	
	//Method to view employer registration form
    $scope.openEmployerRegistration = function(){
    	$scope.selectUserRole=false;
    	$scope.registerAsCandidate=false;
    	$scope.registerAsEmployer=true;
    	$scope.showIndustryTypeTextInput=false;
    	$scope.employer={};
    	document.getElementById('employerRegistrationForm').reset();
    	document.getElementById('candidateRegistrationForm').reset();
    }
	
    $scope.openCandidateRegistration = function()
    {
    	$scope.selectUserRole=false;
    	$scope.registerAsEmployer=false;
    	$scope.registerAsCandidate=true;
    	$scope.candidate={};
    	document.getElementById('employerRegistrationForm').reset();
    	document.getElementById('candidateRegistrationForm').reset();
    }
    
    //Request to fetch company type
    $http.get('/getCompanyType')
	.then(function(response){
		let companyTypeArray = response.data;
      	for (i in companyTypeArray) {
      		$scope.companyTypeList.push(companyTypeArray[i].companyType);
      	}
	});

    //Request to fetch Professional Experience dropdown values
    $http.get('/getProfessionalExperience')
	.then(function(response){
		let professionalExperienceArray = response.data;
      	for (i in professionalExperienceArray) {
      		$scope.professionalExperienceList.push(professionalExperienceArray[i].experience);
      	
      	}
	});
    
    //Request to fetch education qualification dropdown values
    $http.get('/getEducationQualification')
	.then(function(response){
		let educationQualificationArray = response.data;
      	for (i in educationQualificationArray) {
      		$scope.educationQualificationList.push(educationQualificationArray[i].educationQualification);
      	
      	}
	});
    
    //Request to fetch industry type
    $http.get('/getIndustryTypes')
	.then(function(response){
		let industryTypeArray = response.data;
      	for (i in industryTypeArray) {
      		$scope.industryTypeList.push(industryTypeArray[i].industryType);
      	}
	});
    
    //Request to fetch industry type
    $http.get('/getCompanyScales')
	.then(function(response){
		let companyScaleArray = response.data;
      	for (i in companyScaleArray) {
      		$scope.companyScaleList.push(companyScaleArray[i].companyScale);
      	}
	});
    
    //Request to fetch industry type
    $http.get('/getStates')
	.then(function(response){
		let statesArray = response.data;
      	for (i in statesArray) {
      		$scope.statesList.push(statesArray[i].stateName);
      	}
	});
	
    //Method to show/hide industry type text box
    $scope.specifyIndustryType = function(){
    	$scope.showIndustryTypeTextInput = false;
    	if($scope.employer.industryType.toUpperCase() == $scope.staticContentArray.industryTypeOTher.toUpperCase()){
    		$scope.showIndustryTypeTextInput = true;
    	}
    }
    

    
    //Method for register employer 
    $scope.employerRegistration = function(){
        $scope.registrationError=false;
        $scope.registerationSuccess=false;
        $scope.allowEmployerRegistration = true;
        $scope.disableEmployerRegistrationButton = true;
    	var emailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;

		if($scope.employer.liasingAuthorityName.trim() ==""){
			$scope.registerationErrorMessage = $scope.errorMessageArray.invalidLiaisingAuthorityNameError;
			$scope.registrationError=true;
			$scope.allowEmployerRegistration = false;
			$scope.disableEmployerRegistrationButton = false;
			$scope.registrationMessageTimeout();
		}
		else if($scope.employer.designation == "" || $scope.employer.designation == undefined || $scope.employer.designation == null || $scope.employer.designation.trim()==""){
			$scope.registerationErrorMessage = $scope.errorMessageArray.invalidDesignationError;
			$scope.registrationError=true;
			$scope.allowEmployerRegistration = false;
			$scope.disableEmployerRegistrationButton = false;
			$scope.registrationMessageTimeout();
		}
		else if($scope.showIndustryTypeTextInput){
    		if($scope.employer.newIndustryType == undefined || $scope.employer.newIndustryType == null || $scope.employer.newIndustryType == "")
    		{
    			$scope.registerationErrorMessage = $scope.errorMessageArray.invalidIndustryTypeError;
    			$scope.registrationError=true;
    			$scope.allowEmployerRegistration = false;
    			$scope.disableEmployerRegistrationButton = false;
    			$scope.registrationMessageTimeout();
    		}
    	}
		if(!($scope.employer.landlineNumber == undefined || $scope.employer.landlineNumber == null || $scope.employer.landlineNumber == "")){
			if(($scope.employer.landlineNumber.toString().length <10) || ($scope.employer.landlineNumber.toString().length >16))
			{
				$scope.registerationErrorMessage=$scope.errorMessageArray.landlineNumberLengthError;
				$scope.registrationError=true;
				$scope.allowEmployerRegistration = false;
				$scope.disableEmployerRegistrationButton = false;
				$scope.registrationMessageTimeout();	
			}
			else if($scope.employer.landlineNumber == 0)
			{
				$scope.registerationErrorMessage=$scope.errorMessageArray.invalidLandlineNumberError;
				$scope.registrationError=true;
				$scope.allowEmployerRegistration = false;
				$scope.disableEmployerRegistrationButton = false;
				$scope.registrationMessageTimeout();	
			}
		}
		if($scope.employer.companyName == undefined || $scope.employer.companyName == null || $scope.employer.companyName == "")
		{
			$scope.registerationErrorMessage = $scope.errorMessageArray.invalidCompanyNameError;
			$scope.registrationError=true;
			$scope.allowEmployerRegistration = false;
			$scope.disableEmployerRegistrationButton = false;
			$scope.registrationMessageTimeout();
		}
    	else if($scope.employer.pincode.toString().length != 6 )
		{
			$scope.registerationErrorMessage = $scope.errorMessageArray.invalidPincodeLengthError;
			$scope.registrationError=true;	
			$scope.disableEmployerRegistrationButton = false;
			$scope.registrationMessageTimeout();
		}
    	else if($scope.employer.panNumber == undefined || $scope.employer.panNumber == null || $scope.employer.panNumber == "" )
		{
			$scope.registerationErrorMessage = $scope.errorMessageArray.invalidPanNumberError;
			$scope.registrationError=true;
			$scope.disableEmployerRegistrationButton = false;
			$scope.registrationMessageTimeout();
		}
    	else if($scope.employer.panNumber.toString().length != 10)
		{
			$scope.registerationErrorMessage = $scope.errorMessageArray.invalidLengthPanNumberError;
			$scope.registrationError=true;	
			$scope.disableEmployerRegistrationButton = false;
			$scope.registrationMessageTimeout();
		}
    	else if(!$scope.employer.email.match(emailformat))
		{
			$scope.registerationErrorMessage = $scope.errorMessageArray.invalidEmailError;
			$scope.registrationError=true;	
			$scope.disableEmployerRegistrationButton = false;
			$scope.registrationMessageTimeout();
		}
    	else if($scope.employer.mobileNumber.toString().length != 10)
		{
			$scope.registerationErrorMessage=$scope.errorMessageArray.mobileNumberLengthError;
			$scope.registrationError=true;
			$scope.disableEmployerRegistrationButton = false;
			$scope.registrationMessageTimeout();
		}
		else if($scope.employer.companyAddress==undefined || $scope.employer.companyAddress==null || $scope.employer.companyAddress==""){
			$scope.registerationErrorMessage=$scope.errorMessageArray.invalidAddressLengthError;
			$scope.registrationError=true;
			$scope.disableEmployerRegistrationButton = false;
			$scope.registrationMessageTimeout();
		}
    	else if ($scope.allowEmployerRegistration)
    	{
    		let employerRegistrationUrl = "/registerEmployer";
    		let employerDetails = {
    			companyName:$scope.employer.companyName,
    			companyType:$scope.employer.companyType,
    			industryType:($scope.showIndustryTypeTextInput ? $scope.employer.newIndustryType : $scope.employer.industryType),
    			companyScale:$scope.employer.companyScale,
    			panNumber:$scope.employer.panNumber,
    			state:$scope.employer.state,
    			pincode:$scope.employer.pincode,
    			liasingAuthorityName:$scope.employer.liasingAuthorityName,
    			designation:$scope.employer.designation,
    			email:$scope.employer.email,
    			mobileNumber:$scope.staticContentArray.mobileNumberPrefix+$scope.employer.mobileNumber,
				landlineNumber:$scope.employer.landlineNumber,
				employerWebsite:$scope.employer.employerWebsite,
				companyAddress:$scope.employer.companyAddress
    		}
    		$http.post(employerRegistrationUrl ,JSON.stringify(employerDetails))
    		.then(function(response){
    			if(response.data == 1){
    			    $scope.registerationSuccess=true;
    			    $scope.disableEmployerRegistrationButton = false;
    			    $scope.showIndustryTypeTextInput=false;
    				$scope.employer={};
    			    $scope.registerationSuccessMessage=$scope.errorMessageArray.employerRegistrationSuccess;
    			    $scope.registrationMessageTimeout();
    			    document.getElementById('employerRegistrationForm').reset();
    			}
    			else if(response.data == -55){
    			    $scope.registrationError=true;
    			    $scope.disableEmployerRegistrationButton = false;
    			    $scope.registerationErrorMessage=$scope.errorMessageArray.mobileNumberAlreadyExists;
    			    $scope.registrationMessageTimeout();
    			}
    			else if(response.data == -66){
    			    $scope.registrationError=true;
    			    $scope.disableEmployerRegistrationButton = false;
    			    $scope.registerationErrorMessage=$scope.errorMessageArray.emailIdAlreadyExists;
    			    $scope.registrationMessageTimeout();
    			}
    			else{
    				$scope.registrationError=true;
    				$scope.disableEmployerRegistrationButton = false;
     			    $scope.registerationErrorMessage=$scope.errorMessageArray.registrationError;
     			    $scope.registrationMessageTimeout();
    			}
    		},function(errorResponse){
    			$scope.registrationError=true;
    			$scope.disableEmployerRegistrationButton = false;
 			    $scope.registerationErrorMessage=$scope.errorMessageArray.registrationError;
 			    $scope.registrationMessageTimeout();
    		});
    	}
    }
    
	/**************************************** Methods for user authentication ***********************************************************/
    
	/**
	 * Method to resend OTP
	 */
	
	$scope.resendOtp = function(){
		$scope.loginError=false;
		$scope.sendOtpToUser();
		$timeout(function(){
			$scope.loginError = false;
		},7000);
	}
    
	/**
	 * METHOD TO REQUEST OTP
	 */
	
	$scope.requestOtp = function(){
		$scope.loginError=false;
		if($scope.allowLogin == false)
		{
			$scope.sendOtpToUser();
			$timeout(function(){
				$scope.loginError = false;
			},7000);
		}
	}
	
    /** Method to send OTP to registered mobile number**/
    $scope.sendOtpToUser = function()
    {
    	
    	if($scope.credential.mobileNumber == undefined || $scope.credential.mobileNumber == null || $scope.credential.mobileNumber ==  "")
    	{
			$scope.loginErrorMessage = $scope.errorMessageArray.invalidMobileNumberError;
			$scope.loginError = true;
			$scope.processingOTP=false;
		}
		
    	else if($scope.credential.mobileNumber.toString().length != 10){
    		$scope.loginError=false;
			$scope.loginErrorMessage = $scope.errorMessageArray.mobileNumberLengthError;
			$scope.loginError = true;
			$scope.processingOTP=false;
		}
		else
		{
			$scope.loginError=false;
			$scope.processingOTP=true;
			$scope.userMobileNumber=$scope.staticContentArray.mobileNumberPrefix+$scope.credential.mobileNumber;
	    	$http.get("/requestOtp?mobileNumber="+$scope.userMobileNumber)
			.then(function(response){
				if(response.data==1)
				{
					$scope.processingOTP=false;
					$scope.showOtpInput = true;
					$scope.allowLogin = true;
				}
				else if(response.data==-55)
				{
					$scope.processingOTP=false;
					$scope.loginErrorMessage = $scope.errorMessageArray.mobileNumberNotFound;
					$scope.loginError = true;
				}
				else if(response.data==-66)
				{
					$scope.processingOTP=false;
					$scope.loginErrorMessage = $scope.errorMessageArray.userAccountDisabledError;
					$scope.loginError = true;
				}
				else
				{
					$scope.processingOTP=false;
					$scope.loginErrorMessage = $scope.errorMessageArray.otpRequestFailError;
					$scope.loginError = true;
				}
			});
		}
    	
    }
    
    //Method to hide registration messages
    $scope.registrationMessageTimeout = function(){
    	$timeout(function() {
        	$scope.registrationError = false;
        	$scope.registerationSuccess=false;
        }, 7000);
    }
    
  //function to remove  pre-loader 
	$(window).on("load", function() { 
		document.getElementById('loadingPage').style.display="none";	
	});
	
	/**
	 * METHOD TO AUTHENTICATE CREDENTIALS FOR LOGIN
	 */
	 var authenticate = function(credentials) {
		
		 var headers = credentials ? {
			 authorization: "Basic " + btoa(credentials.number + ":" + credentials.otp)
	        } : {};
	        
	        $http.get('/user', {
	            headers: headers
	        }).then(function(response) {
	        	$scope.allowLogin=false;
	            $rootScope.userRole = JSON.stringify(response.data.authorities[0].authority);
	            $rootScope.loggedInUsername = response.data.userName;
	            var nameInitials = response.data.userName.split(" ", 2);
	            $rootScope.userNameInitials = nameInitials[0].charAt(0).toUpperCase();
	            if(nameInitials[1] != undefined) {
	            	$rootScope.userNameInitials = nameInitials[0].charAt(0).toUpperCase() + nameInitials[1].charAt(0).toUpperCase();
	            };
	        	
	            if ($rootScope.userRole == '"Candidate"') {
	            	$scope.homePage = true;
	        		$scope.showMyProfileSection=false;
	                $rootScope.mainTemplate = false;
	                $location.path("/candidate");
	            } else if ($rootScope.userRole == '"Employer"') {
	            	$scope.homePage = true;
	        		$scope.showMyProfileSection=false;
	                $rootScope.mainTemplate = false;
	                $location.path("/employer");
	            }
	            else if ($rootScope.userRole == '"Admin"') {
	            	$scope.homePage = true;
	        		$scope.showMyProfileSection=false;
	                $rootScope.mainTemplate = false;
	                $location.path("/admin");
	            }
	            else
	                $location.path("/");
	            	$scope.changeProfileText();
	        }, function(error) {
	            $rootScope.mainTemplate = true;
	            $scope.loginErrorMessage = $scope.errorMessageArray.invalidOtpError;
	            $scope.loginError=true;
	            $timeout(function() {
	            	$scope.loginError = false;
	            }, 7000);
	        });       
	    }
	
	
	/**
	 * Calling authenticate function, if user is already logged in and gave the reload command
	 */
    authenticate();
	
    /**
	 * METHOD TO LOGIN
	 */
    $scope.allowLogin = false;
	$scope.login = function(){
		if($scope.allowLogin){
			if($scope.credential == undefined || $scope.credential == null || $scope.credential ==  ""){
				$scope.loginErrorMessage = $scope.errorMessageArray.invalidMobileNumberError;
				$scope.loginError = true;
				$scope.processingOTP=false;
			}
			else if($scope.credential.mobileNumber.toString().length != 10){
				$scope.loginErrorMessage = $scope.errorMessageArray.mobileNumberLengthError;
				$scope.loginError = true;
				$scope.processingOTP=false;
			}
			else if($scope.credential.otp  == undefined || $scope.credential.otp == null || $scope.credential.otp ==  ""){
				$scope.loginErrorMessage = $scope.errorMessageArray.invalidOtpError;
	        	$scope.loginError = true;
				$scope.processingOTP=false;
			}
			else{
				$scope.userLoginDetails = {
					number:$scope.staticContentArray.mobileNumberPrefix+$scope.credential.mobileNumber,
					otp:$scope.credential.otp
				}
				authenticate($scope.userLoginDetails);
			}
			
			 $timeout(function() {
	            	$scope.loginError = false;
	            }, 7000);
		}
		
	}

	//Method to display the CGSC Certification Details
	$scope.displayCgscCertificationDetails = function()
	{
		if($scope.candidate.cgscCertification==$scope.staticContentArray.trueFlag)
		{
			$scope.showCertificationDetails=true;
		}
		else
		{
			$scope.showCertificationDetails=false;
		}
	}
	
	/**
	 * METHOD TO LOGOUT
	 */
	$scope.logout = function($route){
       	$http.get("/logout").then(function(response) {
       		if(response.data == 1){
       			$rootScope.mainTemplate = true;
                $rootScope.userRole="";
                angular.element(document.querySelector("#profilePageOptionText")).removeClass("selectedMenuOrange");
        		angular.element(document.querySelector("#homePageOptionText")).addClass("selectedMenuOrange");
                $location.path("/");
                $scope.allowLogin=false;
                $window.location.reload();
        	}
         });
    }	
	
	
	/*** Method to generate candidate credentials ***/
	$scope.generateCandidateCredentials = function()
	{
			$scope.registrationError=false;
	        $scope.registerationSuccess=false;
	        $scope.allowCandidateRegistration = true;
	        $scope.postCandidateDetails=false;
	        $scope.disableCandidateRegistrationButton=true;
	        $scope.trainingPartnerName=false;
	        $scope.allowCandidateRegistration=true;
	        if(!($scope.candidate.dob==undefined || $scope.candidate.dob==null || $scope.candidate.dob=="")){
				$scope.dob=stringToDate($scope.candidate.dob)
			}

	        if($scope.candidate.candidateName==undefined || $scope.candidate.candidateName==null || $scope.candidate.candidateName=="")
	        	{
					$scope.allowCandidateRegistration=false;
					$scope.registerationErrorMessage = $scope.errorMessageArray.invalidCandidateNameInputError;
					$scope.registrationError=true;
					$scope.disableCandidateRegistrationButton=false;
					$scope.registrationMessageTimeout();
	        	}
	        
	        else if($scope.candidate.pincode.toString().length != 6)
			{
				$scope.allowCandidateRegistration=false;
				$scope.registerationErrorMessage = $scope.errorMessageArray.invalidPincodeLengthError;
				$scope.registrationError=true;	
				$scope.disableCandidateRegistrationButton=false;
				$scope.registrationMessageTimeout();
			}
	        else if($scope.candidate.guardianMobileNumber==undefined || $scope.candidate.guardianMobileNumber.toString().length !=10)
	        {
				$scope.allowCandidateRegistration=false;
				$scope.registerationErrorMessage=$scope.errorMessageArray.guardianMobileNumberLengthError;
				$scope.registrationError=true;
				$scope.disableCandidateRegistrationButton=false;
				$scope.registrationMessageTimeout();
	        }
	        else if($scope.candidate.mobileNumber.toString().length != 10)
			{
				$scope.allowCandidateRegistration=false;
				$scope.registerationErrorMessage=$scope.errorMessageArray.mobileNumberLengthError;
				$scope.registrationError=true;
				$scope.disableCandidateRegistrationButton=false;
				$scope.registrationMessageTimeout();
			}
	        else if($scope.candidate.aadhaarNumber==undefined ||$scope.candidate.aadhaarNumber.toString().length != 12){
				$scope.allowCandidateRegistration=false;
				$scope.registerationErrorMessage=$scope.errorMessageArray.aadhaarNumberLengthError;
				$scope.registrationError=true;
				$scope.disableCandidateRegistrationButton=false;
				$scope.registrationMessageTimeout();
			}
	        else if($scope.candidate.aadhaarNumber<=0) {
				$scope.allowCandidateRegistration=false;
				$scope.registerationErrorMessage=$scope.errorMessageArray.aadhaarNumberNegativeError;
				$scope.registrationError=true;
				$scope.disableCandidateRegistrationButton=false;
				$scope.registrationMessageTimeout();
	        }
	        else if($scope.candidate.guardianName==undefined || $scope.candidate.guardianName==null || $scope.candidate.guardianName==""){
				$scope.allowCandidateRegistration=false;
				$scope.registerationErrorMessage = $scope.errorMessageArray.invalidGuardianNameInputError;
				$scope.registrationError=true;
				$scope.disableCandidateRegistrationButton=false;
				$scope.registrationMessageTimeout();
			}
			else if($scope.candidate.address==undefined || $scope.candidate.address==null || $scope.candidate.address==""){
				$scope.allowCandidateRegistration=false;
				$scope.registerationErrorMessage = $scope.errorMessageArray.invalidAddressLengthError;
				$scope.registrationError=true;
				$scope.disableCandidateRegistrationButton=false;
				$scope.registrationMessageTimeout();
			}
			else if($scope.candidate.jobRole==undefined || $scope.candidate.jobRole==null || $scope.candidate.jobRole==""){
				$scope.allowCandidateRegistration=false;
				$scope.registerationErrorMessage = $scope.errorMessageArray.invalidJobRoleError;
				$scope.registrationError=true;
				$scope.disableCandidateRegistrationButton=false;
				$scope.registrationMessageTimeout();
			}
			// else if ($scope.candidate.dob==undefined || $scope.candidate.dob> $scope.maxDateForDateOfBirth || $scope.candidate.dob<$scope.minDateForDateOfBirth) {
			else if($scope.dob==undefined || $scope.dob > $scope.maxDateForDateOfBirth || $scope.dob < $scope.minDateForDateOfBirth){
				$scope.allowCandidateRegistration=false;
				$scope.registerationErrorMessage = $scope.errorMessageArray.invalidDobError + formatToDisplayDate($scope.formatDate($scope.minDateForDateOfBirth)) + $scope.staticContentArray.andText + formatToDisplayDate($scope.formatDate($scope.maxDateForDateOfBirth));
				$scope.registrationError=true;
				$scope.disableCandidateRegistrationButton=false;
				$scope.registrationMessageTimeout();
			}
	        else
	        {
	        	if($scope.candidate.cgscCertification==$scope.staticContentArray.trueFlag)
	        	{
	        		if($scope.candidate.certificateNumber===undefined || $scope.candidate.certificateNumber===null || $scope.candidate.certificateNumber==="" || $scope.candidate.certificateNumber.trim()==="")
		        	{
		        		$scope.allowCandidateRegistration=false;
		        		$scope.registerationErrorMessage=$scope.errorMessageArray.enterCertificateNumberError;
	      	    		$scope.registrationError=true;
	      	    		$scope.disableCandidateRegistrationButton=false;
	      	    		$scope.registrationMessageTimeout();
		        		
		        	}
	        		else if($scope.candidate.trainingPartner===undefined || $scope.candidate.trainingPartner===null || $scope.candidate.trainingPartner==="")
		        	{
		        		$scope.allowCandidateRegistration=false;
		        		$scope.registerationErrorMessage=$scope.errorMessageArray.enterTrainingPartnerName;
	      	    		$scope.registrationError=true;
	      	    		$scope.disableCandidateRegistrationButton=false;
	      	    		$scope.registrationMessageTimeout();
	      	    		
		        	}
	        		else if($scope.trainingPartners.indexOf($scope.candidate.trainingPartner)==-1)
		        	{
		        		$scope.allowCandidateRegistration=false;
		        		$scope.registerationErrorMessage=$scope.errorMessageArray.trainingPartnerNotFoundError;
	      	    		$scope.registrationError=true;
	      	    		$scope.disableCandidateRegistrationButton=false;
	      	    		$scope.registrationMessageTimeout();
		        	}
	        	}
	        	
	        	if($scope.allowCandidateRegistration)
	        	{
		        	var candidateRegistrationDetails = new FormData();
		        	var candidateMobileNumber = $scope.staticContentArray.mobileNumberPrefix+$scope.candidate.mobileNumber;
		        	var guardianMobileNumber = $scope.staticContentArray.mobileNumberPrefix+$scope.candidate.guardianMobileNumber;
		        	candidateRegistrationDetails.append('candidateName',$scope.candidate.candidateName);
		        	candidateRegistrationDetails.append('mobileNumber',candidateMobileNumber)
		        	candidateRegistrationDetails.append('guardianMobileNumber',guardianMobileNumber);
		        	candidateRegistrationDetails.append('professionalExperience',$scope.candidate.professionalExperience);
		        	candidateRegistrationDetails.append('qualification',$scope.candidate.educationQualification);
		        	candidateRegistrationDetails.append('gender',$scope.candidate.gender);
		        	candidateRegistrationDetails.append('defenceBackground',$scope.candidate.defenceBackground);
		        	candidateRegistrationDetails.append('state',$scope.candidate.state);
		        	candidateRegistrationDetails.append('pincode',$scope.candidate.pincode);
		        	candidateRegistrationDetails.append('guardianName',$scope.candidate.guardianName);
		        	candidateRegistrationDetails.append('dob',$scope.formatDate($scope.candidate.dob));
		        	candidateRegistrationDetails.append('aadhaarNumber',$scope.candidate.aadhaarNumber);
		        	candidateRegistrationDetails.append('address',$scope.candidate.address);
					candidateRegistrationDetails.append("jobRoleId",$scope.candidate.jobRole);
//		        	candidateRegistrationDetails.append('cgscCertifiedCandidate',$scope.candidate.cgscCertification);
//		        	if($scope.candidate.cgscCertification==$scope.staticContentArray.trueFlag)
//		        	{
//		        		candidateRegistrationDetails.append('certificateNumber',$scope.candidate.certificateNumber);
//			        	candidateRegistrationDetails.append('trainingPartnerId',$scope.candidate.trainingPartner.userId);
//		        	}
		        	
		        	var candidateResume = document.getElementById('candidateResumeFile').files[0];
		        	var fileName = document.getElementById('candidateResume').value;
		      	    var fileExtensionPdf = fileName.substr(fileName.lastIndexOf('.')+1);
		      	    
		      	    var candidateCertificates = document.getElementById('candidateCertificateFile').files[0];
		        	var zipFileName = document.getElementById('candidateCertificates').value;
		      	    var fileExtensionZip = zipFileName.substr(zipFileName.lastIndexOf('.')+1);
		      	    
		      	    if(fileExtensionPdf =='pdf')
		      	    {
		      	    	//File extension is .pdf, checking the file size
		      	    	var fileSizeInMb = candidateResume.size/(1024*1024);
		      	    	if(fileSizeInMb>$scope.staticContentArray.pdfFileSize10Mb)
		      	    	{
		      	    		$scope.disableCandidateRegistrationButton=false;
		      	    		$scope.registerationErrorMessage=$scope.errorMessageArray.resumeSizeGreaterThanLimit;
		      	    		$scope.registrationError=true;
		      	    		$scope.postCandidateDetails=false;
		      	    		$scope.registrationMessageTimeout();
		      	    	}	
		      	    	else
		      	    	{
		      	    		$scope.postCandidateDetails=true;
		      	    		candidateRegistrationDetails.append('resume',candidateResume);
		      	    	}
		      	    }
		      	    else
		      	    {
		      	    	$scope.registerationErrorMessage = $scope.errorMessageArray.invalidResumeFileType;
		      	    	$scope.registrationError=true;
		      	    	$scope.disableCandidateRegistrationButton=false;
		      	    	$scope.postCandidateDetails=false;
		      	    }
		      	    
		      	    if(document.getElementById('candidateCertificateFile').files[0]!=undefined && $scope.postCandidateDetails==true)
		      	    {
		      	    	if(fileExtensionZip=='zip')
			      	    {
			      	    	var zipFileSizeInMb = candidateCertificates.size/(1024*1024);
			      	    	if(zipFileSizeInMb>$scope.staticContentArray.zipFileSize50Mb)
			      	    	{
			      	    		$scope.disableCandidateRegistrationButton=false;
			      	    		$scope.postCandidateDetails=false;
			      	    		$scope.registerationErrorMessage=$scope.errorMessageArray.certificateSizeGreaterThanLimit;
			      	    		$scope.registrationError=true;
			      	    		$scope.registrationMessageTimeout();
			      	    	}
			      	    	else
			      	    	{
			      	    		$scope.postCandidateDetails=true;
			      	    		candidateRegistrationDetails.append('certificates',candidateCertificates);
			      	    	}
			      	    }
		      	    	else
		      	    	{
		      	    		$scope.postCandidateDetails=false;
		      	    		$scope.disableCandidateRegistrationButton=false;
		      	    		$scope.registerationErrorMessage=$scope.errorMessageArray.invalidCertificateFileType;
		      	    		$scope.registrationError=true;
		      	    	}
		      	    	
		      	    }
		      	    
		      	    if($scope.postCandidateDetails)
		      	    {
		      	    	//Send request to the server to generate credentials of the candidate
		      	    	$scope.disableUploadButton=true;
		      	    	$http({
		      	    	   method: 'POST',
		      	           url: '/registerCandidates',
		      	           data: candidateRegistrationDetails,
		      	           headers: {
		      	               'Content-Type': undefined
		      	           },
		      	           transformRequest: angular.identity,
		      	           transformResponse: [function(data) {
		      	               thisIsResponse = data;
		      	               return data;
		      	           	}]
		      	        })
		      	        .then(function(response){
		      	        	$scope.disableCandidateRegistrationButton=false;
		      	        	$scope.disableUploadButton=false;
		      	        	if(response.data == 1)
		      	        	{
		      	        		$scope.registerationSuccessMessage=$scope.errorMessageArray.candidateRegistrationSuccess;
			      	    		$scope.registerationSuccess=true;
		      	        		document.getElementById('candidateRegistrationForm').reset();
		      	        		$timeout(function()
		      	        		{
		      	        			$scope.registerationSuccess=false;
		      	        		}, 7000);
		      	        	}
		      	        	else if(response.data == -55)
		      	        	{
		      	        		$scope.registerationErrorMessage=$scope.errorMessageArray.mobileNumberAlreadyExists;
			      	    		$scope.registrationError=true;
		      	        		$timeout(function() {
		      	              	$scope.registrationError=false;
		      	              }, 7000);
		      	        	}
		      	        	else
		      	        	{
		      	        		$scope.registerationErrorMessage=$scope.errorMessageArray.registrationError;
			      	    		$scope.registrationError=true;
		      	        		$timeout(function() {
		      	              	$scope.registrationError=false;
		      	              }, 7000);
		      	        	}
		      	        	
		      	        },function(error){
		      	        	$scope.registerationErrorMessage=$scope.errorMessageArray.registrationError;
		      	    		$scope.registrationError=true;
		      	    		$scope.disableCandidateRegistrationButton = false;
	      	        		$scope.disableUploadBtn = false;
	      	        		$timeout(function() {
	      	              	$scope.registrationError=false;
	      	              }, 7000);
		      			});
		      	    }
	        	}
	        }

	        $scope.allowCandidateRegistration=true;
	}
	
	
	//show or download terms and condition
	$scope.downloadTermsAndConditionsDocument= function(){
		let downloadTermsAndConditionsUrl = downloadDocumentUrl + $scope.termsAndConditionsFilePath;
		$http.get(downloadTermsAndConditionsUrl,{
			responseType: 'blob'
		})
		.then(function(response){
			if(response.data.byteLength!=0){
				let link = document.createElement('a');
				link.href = URL.createObjectURL(response.data);
				link.download = $scope.staticContentArray.termsAndConditionDocumentName;
				link.setAttribute('target','_blank');
				document.body.appendChild(link);
				link.click();
			}
		});
	};


});