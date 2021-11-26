var viewJobApplications = angular.module("app");
viewJobApplications.controller("viewJobApplicationsController",function($scope,$http,$timeout){

	$scope.downloading=false;
	$scope.error=false;
	var errorMessageUrl ="js/controllers/errorMessages.json";	
	var staticContentUrl ="js/controllers/staticContent.json";
	$scope.jobPostInfo={};
	$scope.showJobApplicationDetails=false;
	$scope.jobPostList=[];
	$scope.viewJobApplicationDetails={};
	
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
    $scope.noDescriptionDocumentFlag=false;

    //Request to fetch job application details
    $scope.getJobApplicationDetails = function (){
    	$scope.noDescriptionDocumentFlag=false;
    	let viewApplicationsUrl= '/viewJobApplicants?jobId='+$scope.jobPostInfo.id;
        $http.get(viewApplicationsUrl)
    	.then(function(response){
    		$scope.viewJobApplicationDetails = response.data;
			
    		//check if the job posts have at least one application
    		if($scope.viewJobApplicationDetails.totalNumberOfApplicants < 1){
    			$scope.noApplicationsError=true;
    			$scope.showJobApplicationDetails=false;
    			$scope.noApplicationsErrorMessage=$scope.errorMessageArray.noCandidateApplicationsError;
    			$timeout(function(){
    				$scope.noApplicationsError=false;
    			},7000);
    		}
    		else{
    			$scope.noApplicationsError=false;
    			$scope.showJobApplicationDetails=true;
    			if($scope.viewJobApplicationDetails.jobDescriptionDocument == null){
    				$scope.noDescriptionDocumentFlag=true;
    			}
    		}
    	}); 
    }
    
    
    //Method to download document
    $scope.downloadFile = function(documentType){
    	$scope.downloading=true;
    	$scope.error=false;
    	
    	if(documentType == 'description'){
    		let downloadJobDescriptionUrl ="/downloadPdfFile/"+$scope.viewJobApplicationDetails.jobDescriptionDocument;
    		$http.get(downloadJobDescriptionUrl, {
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
    				$scope.downloading=false;
    			} else {
    				$scope.errorMessage = $scope.errorMessageArray.descriptionDocumentDownloadError;
    				$scope.error=true;
    				$scope.downloading=false;
    				$timeout(function(){
    					$scope.error=false;
    				},7000);
    			}
    			
    		},function(error){
    			$scope.errorMessage = $scope.errorMessageArray.descriptionDocumentDownloadError;
    			$scope.error=true;
    			$scope.downloading=false;
    			$timeout(function(){
    				$scope.error=false;
    			},7000);
    			
    		});
    	}
    	
    	else {
    		let downloadJobApplicationsUrl ="downloadAllApplicantDetails?jobId="+$scope.jobPostInfo.id+"&reportFormat="+documentType;
    		$http.get(downloadJobApplicationsUrl, {
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
    				link.download =$scope.jobPostInfo.jobId+$scope.staticContentArray.underScore+$scope.staticContentArray.candidateApplicationsText;
    				document.body.appendChild(link);
    				link.click();
    				$scope.downloading=false;
    			} else {
    				$scope.errorMessage = $scope.errorMessageArray.jobAllApplicationsDownloadError;
    				$scope.error=true;
    				$scope.downloading=false;
    				$timeout(function(){
    					$scope.error=false;
    				},7000);
    			}
    			
    		},function(error){
    			$scope.errorMessage = $scope.errorMessageArray.jobAllApplicationsDownloadError;
    			$scope.error=true;
    			$scope.downloading=false;
    			$timeout(function(){
    				$scope.error=false;
    			},7000);
    			
    		});
    	}
    	
    }
	
});