var viewAppliedJobs = angular.module("app");
viewAppliedJobs.controller("viewAppliedJobsController",function($scope, $http, $timeout){

	var errorMessageUrl ="js/controllers/errorMessages.json";
	var staticContentUrl ="js/controllers/staticContent.json";
	var viewAllAppliedJobsUrl = "/candidateViewAppliedJobs";
	var rowHeight=30;
	var headerHeight=60;
	var minHeight = 90+"px";
	var maxGridHeight = 350;
	
	$scope.viewAppliedJobsGrid=true;
	
	// ui-grid to view job posts in which the candidate has applied
	$scope.viewAppliedJobs = {
			enableGridMenus: false,
			enableSorting: false,
			enableFiltering: true,
			enableCellEdit: false,
			enableColumnMenus: false,
			enableHorizontalScrollbar: 1,
			enableVerticalScrollbar: 1,
			columnDefs: [{
                name: 'jobId',
                displayName: "Job Id",
                width: '10%',
                cellTooltip: function(row, col) {
                    return 'Job Id: ' + row.entity.jobId;
                }
            },
            {
                name: 'jobTitle',
                displayName: "Job Title",
                width: '20%',
                cellTooltip: function(row, col) {
                    return 'Job Title: ' + row.entity.jobTitle;
                }
            },
            {
                name: 'appliedOn',
                displayName: 'Applied On',
                width: '12%',
                cellTooltip: function(row, col) {
                    return 'Applied On: ' + row.entity.appliedOn;
                }
            },
			{
				name: 'applicationStatus',
				displayName: 'Application Status',
				width: '12%',
				cellTemplate:'<div class="applicationStatusGridText text-center"><p title="Status: {{row.entity.applicationStatus}}" class="hiredText" ng-show="row.entity.applicationStatus ==\'Hired\'">{{row.entity.applicationStatus}}</p><p title="Status: {{row.entity.applicationStatus}}" class="shortlistedText" ng-show="row.entity.applicationStatus ==\'Shortlisted\'">{{row.entity.applicationStatus}}</p><p class="inReviewText"  title="Status: {{row.entity.applicationStatus}}" ng-show="row.entity.applicationStatus ==\'In Review\'">{{row.entity.applicationStatus}}</p><p title="Status: {{row.entity.applicationStatus}}" class="rejectedText" ng-show="row.entity.applicationStatus ==\'Rejected\'">{{row.entity.applicationStatus}}</p></div>'

			},
			{
				name: 'descriptionDocumentPath',
				displayName: 'Description',
				width: '11%',
				cellTemplate:'<div class="text-center"><img ng-hide="row.entity.descriptionDocumentPath == null" src="../images/pdfIcon.png" alt="Download Pdf Icon" class="gridDownloadJobDescriptionIcon" ng-click="grid.appScope.downloadJobDescription(row.entity);"><p class="notAvailableGridText" ng-show="row.entity.descriptionDocumentPath == null">N/A</p></div>'
			},
            {
                name: 'joiningDate',
                displayName: 'Joining Date',
                width: '12%',
				cellTemplate :'<div class="applicationStatusGridText text-center"><p title="Joining Date : {{row.entity.joiningDate}}" ng-show="row.entity.joiningDate!=null">{{row.entity.joiningDate}}</p><p title="Joining Date : Not Applicable" ng-show="row.entity.joiningDate==null">N/A</p>',
                cellTooltip: function(row, col) {
                    return 'Joining Date: ' + row.entity.joiningDate;
                }
            },
            {
            	name: 'salary',
            	displayName: 'Salary (Monthly)',
            	width: '12%',
				cellTemplate :'<div class="applicationStatusGridText text-center"><p title="Salary Offered : {{row.entity.salary}}  per month" ng-show="row.entity.applicationStatus==\'Hired\'">{{row.entity.salary}}</p><p title="Salary : Not Applicable" ng-show="row.entity.applicationStatus!=\'Hired\'">N/A</p>',

				cellTooltip: function(row, col) {
					return 'Salary Offered: ' + row.entity.salary + " per month";
				}
	        },
			{
				name: 'offerLetterPath',
				displayName: 'Offer Letter',
				width: '11%',
				cellTemplate:'<div class="text-center"><button class="btn standardGridButton viewJobPostGridButton" ng-click="grid.appScope.downloadOfferLetter(row.entity)" ng-disabled="row.entity.offerLetterPath==null">Download</button></div>'
			},
	        {
	        	name: 'view',
	        	displayName: 'View',
	        	width: '11%',
	        	cellTemplate:'<div class="text-center"><button class="btn standardGridButton viewJobPostGridButton viewGridButton" ng-click="grid.appScope.viewJobPostInformation(row.entity)">View</button></div>'
	        }
	    ]};
	
	
	// ui-grid to view job posts in which the candidate has applied
	$scope.viewAppliedJobsSmallScreen = {
			enableGridMenus: false,
			enableSorting: false,
			enableFiltering: true,
			enableCellEdit: false,
			enableColumnMenus: false,
			enableHorizontalScrollbar: 1,
			enableVerticalScrollbar: 1,
			columnDefs: [{
                name: 'jobId',
                displayName: "Job Id",
                width: '22%',
                cellTooltip: function(row, col) {
                    return 'Job Id: ' + row.entity.jobId;
                }
            },
            {
                name: 'jobTitle',
                displayName: "Job Title",
                width: '72%',
                cellTooltip: function(row, col) {
                    return 'Job Title: ' + row.entity.jobTitle;
                }
            },
            {
                name: 'appliedOn',
                displayName: 'Applied On',
                width: '26%',
                cellTooltip: function(row, col) {
                    return 'Applied On: ' + row.entity.appliedOn;
                }
            },
            {
                name: 'applicationStatus',
                displayName: 'Status',
                width: '26%',
                cellTemplate:'<div class="applicationStatusGridText text-center"><p title="Status: {{row.entity.applicationStatus}}" class="hiredText" ng-show="row.entity.applicationStatus ==\'Hired\'">{{row.entity.applicationStatus}}</p><p title="Status: {{row.entity.applicationStatus}}" class="shortlistedText" ng-show="row.entity.applicationStatus ==\'Shortlisted\'">{{row.entity.applicationStatus}}</p><p class="inReviewText"  title="Status: {{row.entity.applicationStatus}}" ng-show="row.entity.applicationStatus ==\'In Review\'">{{row.entity.applicationStatus}}</p><p title="Status: {{row.entity.applicationStatus}}" class="rejectedText" ng-show="row.entity.applicationStatus ==\'Rejected\'">{{row.entity.applicationStatus}}</p></div>'

            },
            {
            	name: 'descriptionDocumentPath',
            	displayName: 'Description',
            	width: '25%',
            	cellTemplate:'<div class="text-center"><img ng-hide="row.entity.descriptionDocumentPath == null" src="../images/pdfIcon.png" alt="Download Pdf Icon" class="gridDownloadJobDescriptionIcon" ng-click="grid.appScope.downloadJobDescription(row.entity);"><p class="notAvailableGridText" ng-show="row.entity.descriptionDocumentPath == null">N/A</p></div>'
	        },
			{
				name: 'joiningDate',
				displayName: 'Joining Date',
				width: '26%',
				cellTemplate :'<div class="applicationStatusGridText text-center"><p title="Joining Date : {{row.entity.joiningDate}}" ng-show="row.entity.joiningDate!=null">{{row.entity.joiningDate}}</p><p title="Joining Date : Not Applicable" ng-show="row.entity.joiningDate==null">N/A</p>',
				cellTooltip: function(row, col) {
					return 'Joining Date: ' + row.entity.joiningDate;
				}
			},
			{
				name: 'salary',
				displayName: 'Salary (Monthly)',
				width: '26%',
				cellTemplate :'<div class="applicationStatusGridText text-center"><p title="Salary Offered : {{row.entity.salary}}  per month" ng-show="row.entity.salary!=null">{{row.entity.salary}}</p><p title="Salary : Not Applicable" ng-show="row.entity.salary==null">N/A</p>',

				cellTooltip: function(row, col) {
					return 'Salary Offered: ' + row.entity.salary + " per month";
				}
			},
			{
				name: 'offerLetterPath',
				displayName: 'Offer Letter',
				width: '25%',
				cellTemplate:'<div class="text-center"><button class="btn standardGridButton viewJobPostGridButton" ng-click="grid.appScope.downloadOfferLetter(row.entity)" ng-disabled="row.entity.offerLetterPath==null">Download</button></div>'
			},

			{
			name: 'view',
			displayName: 'View',
			width: '25%',
			cellTemplate:'<div class="text-center"><button class="btn standardGridButton viewJobPostGridButton" ng-click="grid.appScope.viewJobPostInformation(row.entity)">View</button></div>'
		}
	]};
	
	
	
	
	
	//Request  to fetch error messages
	$http.get(errorMessageUrl)
	.then(function(response){
		$scope.errorMessageArray = response.data[0];
	});
	
	//Request fetch to static content
	$http.get(staticContentUrl)
	.then(function(response){
		$scope.staticContentArray= response.data[0];
		$scope.descriptionDocumentText = $scope.staticContentArray.descriptionDocumentText;
		$scope.offerLetterText = $scope.staticContentArray.offerLetterText;
	});

	//method to correct the size of the grid
	$scope.correctGridSizing = function (){
		if($scope.viewAppliedJobs.data.length == 0)
		{
			document.getElementById("viewAppliedJobs").style.height=minHeight;
			document.getElementById("viewAppliedJobsSmallScreen").style.height=minHeight;
		}
		else
		{
			var gridHeight = $scope.viewAppliedJobs.data.length * rowHeight + headerHeight;
			if(gridHeight>maxGridHeight){
				document.getElementById("viewAppliedJobs").style.height=maxGridHeight+"px";
				document.getElementById("viewAppliedJobsSmallScreen").style.height=maxGridHeight+"px";
			}
			else
			{
				document.getElementById("viewAppliedJobs").style.height=gridHeight+$scope.staticContentArray.ten+"px";
				document.getElementById("viewAppliedJobsSmallScreen").style.height=gridHeight+"px";
			}
		}
	}

	//Method to populate view job posts ui-grid in which candidate has applied
	$scope.viewAllAppliedJobs = function()
	{
		$http.get(viewAllAppliedJobsUrl)
		.then(function(response){
			if(response.data.length>0){
				$scope.viewAppliedJobs.data= response.data;
				$scope.viewAppliedJobsSmallScreen.data= response.data;
				if($scope.viewAppliedJobs.data.length == 0)
				{
					document.getElementById("viewAppliedJobs").style.height=minHeight;
					document.getElementById("viewAppliedJobsSmallScreen").style.height=minHeight;
				}
				else
				{
					var gridHeight = $scope.viewAppliedJobs.data.length * rowHeight + headerHeight;
					if(gridHeight>maxGridHeight){
						document.getElementById("viewAppliedJobs").style.height=maxGridHeight+"px";
						document.getElementById("viewAppliedJobsSmallScreen").style.height=maxGridHeight+"px";
					}
					else
					{
						document.getElementById("viewAppliedJobs").style.height=gridHeight+$scope.staticContentArray.ten+"px";
						document.getElementById("viewAppliedJobsSmallScreen").style.height=gridHeight+"px";
					}
				}
			}
			else{
				$scope.viewAppliedJobs.data= [];
				$scope.viewAppliedJobsSmallScreen.data= [];
				document.getElementById("viewAppliedJobs").style.height=minHeight;
				document.getElementById("viewAppliedJobsSmallScreen").style.height=minHeight;
			}
		});
	}
		
	$scope.viewAllAppliedJobs();
	
		
	//Method to view ui-grid
	$scope.viewAppliedJobsGridSection = function(){
		$scope.viewAppliedJobsGrid=true;
	}
		
	//Method to view job post information
	$scope.viewJobPostInformation = function(row){
		let fetchJobDetailsUsrl = "/fetchJobDetails?jobId="+row.id;
		$http.get(fetchJobDetailsUsrl)
		.then(function(response){			
			$scope.viewJobPostDetails = response.data;
			$scope.viewJobPostDetails.publishedAt=row.publishedAt;
			$scope.viewJobPostDetails.postedBy=row.postedBy;
			$scope.viewJobPostDetails.jobStatus=row.jobStatus;
			$scope.viewJobPostDetails.joiningDate=row.joiningDate;
			$scope.viewJobPostDetails.salary=row.salary;

			$scope.viewAppliedJobsGrid=false;
		});
	}

	//download offer letter
	$scope.downloadOfferLetter = function (row){
		$scope.error = false;
		$scope.downloadDescriptionDocumentGif=true;
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
					link.download =row.jobId+$scope.staticContentArray.underScore+$scope.staticContentArray.offerLetterText;
					document.body.appendChild(link);
					link.click();
					$scope.downloadDescriptionDocumentGif=false;
				}
				else {
					$scope.errorMessage = $scope.errorMessageArray.offerLetterDownloadError;
					$scope.error=true;
					$scope.downloadDescriptionDocumentGif=false;
					$timeout(function(){
						$scope.error=false;
					},7000);
				}
			}
		)
	};


	//download job description document
	$scope.downloadJobDescription = function (row){
		$scope.error = false;
		$scope.downloadDescriptionDocumentGif=true;
		let viewDocumentUrl ="/downloadPdfFile/"+row.descriptionDocumentPath;
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
						link.download =row.jobId+$scope.staticContentArray.underScore+$scope.staticContentArray.descriptionDocumentText;
						document.body.appendChild(link);
						link.click();
						$scope.downloadDescriptionDocumentGif=false;
					}
					else {
						$scope.errorMessage = $scope.errorMessageArray.descriptionDocumentDownloadError;
						$scope.error=true;
						$scope.downloadDescriptionDocumentGif=false;
						$timeout(function(){
							$scope.error=false;
						},7000);
					}
				}
			)
	};

    
    
	
});