var employerViewOfferLetters = angular.module("app");
employerViewOfferLetters.controller("employerViewOfferLettersController",function($scope, $http, $timeout){
	
	$scope.jobPostList=[];
	$scope.jobPostInfo={};
	$scope.showOfferLetterDetails=false;
	$scope.viewAllCandidateDetailsGrid=true;
	
	var errorMessageUrl ="js/controllers/errorMessages.json";	
	var staticContentUrl ="js/controllers/staticContent.json";
	
	var rowHeight=30;
	var headerHeight=55;
	var minHeight = 90+"px";
	var maxGridHeight = 350;
	
	
	// ui-grid to view candidate application details
	$scope.hiredCandidateDetailsGrid = {
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
			width: '22%',
			cellTooltip: function(row, col) {
				return 'Candidate Name: ' + row.entity.candidateName;
			}
		},
		{
			name: 'professionalExperience',
			displayName: "Experience",
			width: '14%',
			cellTooltip: function(row, col) 
			{
				return 'Experience: ' + row.entity.professionalExperience;
			}
		},
		{
			name: 'hiredOn',
			displayName: "Hired On",
			width: '13%',
			cellTooltip: function(row, col) 
			{
				return 'Hired On: ' + row.entity.hiredOn;
			}
		},
		{
			name: 'joiningDate',
			displayName: "Joining Date",
			width: '14%',
			cellTooltip: function(row, col) 
			{
				return 'Joining Date: ' + row.entity.joiningDate;
			}
		},
		{
			name: 'salaryOffered',
			displayName: "Salary Offered",
			width: '15%',
			cellTooltip: function(row, col) 
			{
				return 'Salary Offered: ' + row.entity.salaryOffered;
			}
		},
		{
			name: 'offerLetterPath',
			displayName: 'Offer Letter',
			width: '13%',
			cellTemplate:'<div class="text-center"><button class="btn standardGridButton downloadOfferLetterButton" ng-click="grid.appScope.downloadOfferLetter(row.entity)" >Download</button></div>'

		},
		{
			name: 'view',
			displayName: 'View',
			width: '10.5%',
			cellTemplate:'<div class="text-center"><button class="btn standardGridButton viewCandidateInfoGridButton" ng-click="grid.appScope.viewCandidateDetails(row.entity)" >View</button></div>'

		},
	]};
	
	
	
	// ui-grid to view candidate application details
	$scope.hiredCandidateDetailsGridSmallScreen = {
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
			width: '55%',
			cellTooltip: function(row, col) {
				return 'Candidate Name: ' + row.entity.candidateName;
			}
		},
		{
			name: 'professionalExperience',
			displayName: "Experience",
			width: '40%',
			cellTooltip: function(row, col) 
			{
				return 'Experience: ' + row.entity.professionalExperience;
			}
		},
		{
			name: 'hiredOn',
			displayName: "Hired On",
			width: '35%',
			cellTooltip: function(row, col) 
			{
				return 'Hired On: ' + row.entity.hiredOn;
			}
		},
		{
			name: 'joiningDate',
			displayName: "Joining Date",
			width: '35%',
			cellTooltip: function(row, col) 
			{
				return 'Joining Date: ' + row.entity.joiningDate;
			}
		},
		{
			name: 'salaryOffered',
			displayName: "Salary Offered",
			width: '35%',
			cellTooltip: function(row, col) 
			{
				return 'Salary Offered: ' + row.entity.salaryOffered;
			}
		},
		{
			name: 'offerLetterPath',
			displayName: 'Offer Letter',
			width: '35%',
			cellTemplate:'<div class="text-center"><button class="btn standardGridButton downloadOfferLetterButton" ng-click="grid.appScope.downloadOfferLetter(row.entity)" >Download</button></div>'

		},
		{
			name: 'view',
			displayName: 'View',
			width: '30%',
			cellTemplate:'<div class="text-center"><button class="btn standardGridButton viewCandidateInfoGridButton" ng-click="grid.appScope.viewCandidateDetails(row.entity)" >View</button></div>'

		},
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
   
    
    
    //Method to fetch hired candidate details once a job post is selected
	$scope.viewHiredCandidateDetails=function(){
		$scope.error=false;
		$scope.noCandidatesHiredError=false;
    	let getApplicantDetailsUrl="/getHiredCandidatesForJobId?id="+$scope.jobPostInfo.id;
    	$http.get(getApplicantDetailsUrl)
		.then(function(response){
			if(response.data.length>0){
				$scope.showOfferLetterDetails=true;
				$scope.hiredCandidateDetailsGrid.data= response.data;
				$scope.hiredCandidateDetailsGridSmallScreen.data= response.data;
				var gridHeight = $scope.hiredCandidateDetailsGrid.data.length * rowHeight + headerHeight;
				if(gridHeight>maxGridHeight){
					document.getElementById("hiredCandidateDetailsGrid").style.height=maxGridHeight+"px";
					document.getElementById("hiredCandidateDetailsGridSmallScreen").style.height=maxGridHeight+"px";
				}
				else
				{
					document.getElementById("hiredCandidateDetailsGrid").style.height=gridHeight+"px";
					document.getElementById("hiredCandidateDetailsGridSmallScreen").style.height=gridHeight+"px";
				}
				
			}
			else{
				$scope.showOfferLetterDetails=false;
				document.getElementById("hiredCandidateDetailsGrid").style.height=minHeight;
				document.getElementById("hiredCandidateDetailsGridSmallScreen").style.height=minHeight;
				$scope.hiredCandidateDetailsGrid.data= [];
				$scope.hiredCandidateDetailsGridSmallScreen.data= [];
				$scope.noCandidatesHiredError=true;
				$scope.noCandidatesHiredErrorMessage=$scope.errorMessageArray.noCandidatesHiredError;
				$timeout(function(){
					$scope.noCandidatesHiredError=false;
				},7000);
			}
		});
	}
	
	
	//Method to view candidate details
    $scope.viewCandidateDetails = function(row){
    	$scope.candidateInfo=row
    	$scope.downloadGif=false;
    	$scope.error=false;
    	$scope.viewAllCandidateDetailsGrid=false;
    }
    
  //Method to view grid section on back button click
    $scope.viewApplicantsGridSection=function(){
    	$scope.downloadGif=false;
    	$scope.error=false;
    	$scope.viewAllCandidateDetailsGrid=true;
    }
    
    
    //Method to download offer letter
	$scope.downloadOfferLetter = function (row){
		$scope.error = false;
		$scope.downloadGif=true;
		let viewDocumentUrl ="/downloadImage/"+row.offerLetterPath;
		$http.get(viewDocumentUrl, {
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
				link.download =$scope.jobPostInfo.jobId+$scope.staticContentArray.underScore+row.candidateName+$scope.staticContentArray.underScore+$scope.staticContentArray.offerLetterText;
				document.body.appendChild(link);
				link.click();
				$scope.downloadGif=false;
			}
			else {
				$scope.errorMessage = $scope.errorMessageArray.offerLetterDownloadError;
				$scope.error=true;
				$scope.downloadGif=false;
				$timeout(function(){
					$scope.error=false;
				},7000);
			}
		},function(error){
			$scope.errorMessage = $scope.errorMessageArray.offerLetterDownloadError;
			$scope.error=true;
			$scope.downloadGif=false;
			$timeout(function(){
				$scope.error=false;
			},7000);
		});
	};
	
    
    //Method to download resume
    $scope.downloadResume=function(resumePath,applicantName){
    	$scope.downloadGif=true;
    	$scope.error=false;
    	
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
    
});