var adminDashboard = angular.module("app");
adminDashboard.controller("dashboardController",function($scope, $http, $timeout){

	$scope.registeredEmployersCount=0;
	$scope.activeCandidatesCount=0;
	$scope.placedCandidatesCount=0;
	$scope.jobsGeneratedCount=0;
	$scope.downloadReportsFlag=true;
	$scope.disableAction=false;
	
	var errorMessageUrl ="js/controllers/errorMessages.json";	
	var staticContentUrl ="js/controllers/staticContent.json";
	
	//Method to get registered employer count
	$http.get("/getRegisteredEmployersCount")
		.then(function(response){
			if(response.data>=0){
				$scope.registeredEmployersCount = response.data;
			}
		});
	
	//Method to get active registered candidates count
	$http.get("/getActiveCandidatesCount")
		.then(function(response){
			if(response.data>=0){
				$scope.activeCandidatesCount = response.data;
			}
		});
	
	//Method to get placed candidates count
	$http.get("/getPlacedCandidatesCount")
		.then(function(response){
			if(response.data>=0){
				$scope.placedCandidatesCount = response.data;
			}
		});
	
	//Method to get total jobs count
	$http.get("/getTotalJobsCount")
		.then(function(response){
			if(response.data>=0){
				$scope.jobsGeneratedCount = response.data;
			}
		});
	
	
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
	
	//function to calculate date in yyyy-mm-dd format
	$scope.formatDateToString = function (date){
		var dd = (date.getDate() < 10 ? '0' : '') + date.getDate();
		var MM = ((date.getMonth() + 1) < 10 ? '0' : '') + (date.getMonth() + 1);
		var yyyy = date.getFullYear();
		return (yyyy + $scope.staticContentArray.hyphenText + MM + $scope.staticContentArray.hyphenText + dd);
	}
	
	
	//Method to reset selected time if time period is changed
	$scope.resetTimeperiod = function(){
		if($scope.selectedTimePeriod != $scope.staticContentArray.customDateText){
			$scope.customStartDate="";
			$scope.customEndDate="";
		}
	}
	
	
	//Method to calculate start/end date and their validations
	$scope.dateValidations = function()
	{
		$scope.downloadReportsFlag=true;
		if($scope.selectedTimePeriod == $scope.staticContentArray.oneMonthText){
			var currentDate = new Date();
			$scope.reportToDate = $scope.formatDateToString(currentDate);
			currentDate.setMonth(currentDate.getMonth() - 1);
			$scope.reportFromDate = $scope.formatDateToString(currentDate);
		}
		else if($scope.selectedTimePeriod == $scope.staticContentArray.threeMonthText){
			var currentDate = new Date();
			$scope.reportToDate = $scope.formatDateToString(currentDate);
			currentDate.setMonth(currentDate.getMonth() - 3);
			$scope.reportFromDate = $scope.formatDateToString(currentDate);
		}
		else if($scope.selectedTimePeriod == $scope.staticContentArray.sixMonthText){
			var currentDate = new Date();
			$scope.reportToDate = $scope.formatDateToString(currentDate);
			currentDate.setMonth(currentDate.getMonth() - 6);
			$scope.reportFromDate = $scope.formatDateToString(currentDate);
		}
		
		else if($scope.selectedTimePeriod == $scope.staticContentArray.customDateText){			
			var today = new Date();
			$scope.todaysDate = $scope.formatDateToString(today);
			
			if($scope.customStartDate =="" || $scope.customStartDate == undefined || $scope.customStartDate == null){
				$scope.error=true;
				$scope.errorMessage=$scope.errorMessageArray.reportCustomStartDateError;
				$scope.downloadReportsFlag=false;
			}
			else if($scope.customEndDate =="" || $scope.customEndDate == undefined || $scope.customEndDate == null){
				$scope.error=true;
				$scope.errorMessage=$scope.errorMessageArray.reportCustomEndDateError;
				$scope.downloadReportsFlag=false;
			}
			else if($scope.customStartDate > $scope.todaysDate){
				$scope.error=true;
				$scope.errorMessage=$scope.errorMessageArray.reportInvalidStartDateError;
				$scope.downloadReportsFlag=false;
			}
			else if($scope.customEndDate > $scope.todaysDate){
				$scope.error=true;
				$scope.errorMessage=$scope.errorMessageArray.reportInvalidEndDateError;
				$scope.downloadReportsFlag=false;
			}
			else if($scope.customEndDate < $scope.customStartDate){
				$scope.error=true;
				$scope.errorMessage=$scope.errorMessageArray.reportStartDateGreaterThanEndDateError;
				$scope.downloadReportsFlag=false;
			}
			else{
				$scope.reportToDate = $scope.customEndDate;
				$scope.reportFromDate = $scope.customStartDate;
			}
		}
		else{
			$scope.error=true;
			$scope.errorMessage=$scope.errorMessageArray.generateReportSelectTimePeriodError;
			$scope.downloadReportsFlag=false;
		}
	}
	
	
	// Method to download company report
	$scope.downloadCompanyReport = function(){
		$scope.error=false;
		$scope.disableAction=true;
		$scope.hideCompanyReportButton=true;

		$scope.dateValidations();

		if($scope.downloadReportsFlag){
			let downloadCompanyReportUrl ="/downloadCompanyReport?startDate="+$scope.reportFromDate+"&endDate="+$scope.reportToDate;
			$http.get(downloadCompanyReportUrl, {
				responseType: 'arraybuffer'
			})
			.then(function (response) {
				if (response.data.byteLength != 0) {
					var setContentType = response.headers("content-type");
					var viewReport = new Blob([response.data], {
						type: setContentType
					})
					var downloadURL = URL.createObjectURL(viewReport);
					var link = document.createElement('a');
					link.href = downloadURL;
					link.download =$scope.staticContentArray.companyReportText;
					document.body.appendChild(link);
					link.click();
					$scope.disableAction=false;
					$scope.hideCompanyReportButton=false;
				} else {
					$scope.errorMessage = $scope.errorMessageArray.noRecordFoundError;
					$scope.error=true;
					$scope.disableAction=false;
					$scope.hideCompanyReportButton=false;
					$scope.hideMessages();
				}

			},function(error){
				$scope.errorMessage = $scope.errorMessageArray.noRecordFoundError;
				$scope.error=true;
				$scope.disableAction=false;
				$scope.hideCompanyReportButton=false;
				$scope.hideMessages();

			});
		}
		else{
			$scope.disableAction=false;
			$scope.hideMessages();
			$scope.hideCompanyReportButton=false;
		}
	}


	// Method to download candidate report
	$scope.downloadCandidateReport = function(){
		$scope.error=false;
		$scope.disableAction=true;
		$scope.hideCandidateReportButton=true;

		$scope.dateValidations();
		if($scope.downloadReportsFlag){
			let downloadCandidateReportUrl ="/downloadCandidateReport?startDate="+$scope.reportFromDate+"&endDate="+$scope.reportToDate;
			$http.get(downloadCandidateReportUrl, {
				responseType: 'arraybuffer'
			})
			.then(function (response) {
				if (response.data.byteLength != 0) {
					var setContentType = response.headers("content-type");
					var viewReport = new Blob([response.data], {
						type: setContentType
					})
					var downloadURL = URL.createObjectURL(viewReport);
					var link = document.createElement('a');
					link.href = downloadURL;
					link.download =$scope.staticContentArray.candidateReportText;
					document.body.appendChild(link);
					link.click();
					$scope.disableAction=false;
					$scope.hideCandidateReportButton=false;
				} else {
					$scope.errorMessage = $scope.errorMessageArray.noRecordFoundError;
					$scope.error=true;
					$scope.disableAction=false;
					$scope.hideCandidateReportButton=false;
					$scope.hideMessages();
				}

			},function(error){
				$scope.errorMessage = $scope.errorMessageArray.noRecordFoundError;
				$scope.error=true;
				$scope.disableAction=false;
				$scope.hideCandidateReportButton=false;
				$scope.hideMessages();

			});
		}
		else{
			$scope.disableAction=false;
			$scope.hideMessages();
			$scope.hideCandidateReportButton=false;
		}
	}



//	Method to download candidate placement report
	$scope.downloadCandidatePlacementReport = function(){
		$scope.error=false;
		$scope.disableAction=true;
		$scope.hideCandidatePlacementReportButton=true;

		$scope.dateValidations();
		if($scope.downloadReportsFlag){
			let downloadCandidatePlacementReportUrl ="/downloadCandidatePlacementReport?startDate="+$scope.reportFromDate+"&endDate="+$scope.reportToDate;
			$http.get(downloadCandidatePlacementReportUrl, {
				responseType: 'arraybuffer'
			})
			.then(function (response) {
				if (response.data.byteLength != 0) {
					var setContentType = response.headers("content-type");
					var viewReport = new Blob([response.data], {
						type: setContentType
					})
					var downloadURL = URL.createObjectURL(viewReport);
					var link = document.createElement('a');
					link.href = downloadURL;
					link.download =$scope.staticContentArray.candidatePlacementReportText;
					document.body.appendChild(link);
					link.click();
					$scope.disableAction=false;
					$scope.hideCandidatePlacementReportButton=false;
				} else {
					$scope.errorMessage = $scope.errorMessageArray.noRecordFoundError;
					$scope.error=true;
					$scope.disableAction=false;
					$scope.hideCandidatePlacementReportButton=false;
					$scope.hideMessages();
				}
			},function(error){
				$scope.errorMessage = $scope.errorMessageArray.noRecordFoundError;
				$scope.error=true;
				$scope.disableAction=false;
				$scope.hideCandidatePlacementReportButton=false;
				$scope.hideMessages();
			});
		}
		else{
			$scope.disableAction=false;
			$scope.hideMessages();
			$scope.hideCandidatePlacementReportButton=false;
		}
	}

	
	
	//Method to hide messages
	$scope.hideMessages=function(){
		$timeout(function () {
			$scope.error=false;
		}, 7000);
	}
	
});