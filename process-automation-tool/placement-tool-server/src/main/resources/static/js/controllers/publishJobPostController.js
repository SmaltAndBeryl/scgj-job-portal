var publishJobPost = angular.module("app");
publishJobPost.controller("publishJobPostController",function($scope,$http,$timeout,$window){
	
	var errorMessageUrl ="js/controllers/errorMessages.json";
	var staticContentUrl ="js/controllers/staticContent.json";
	var rowHeight=30;
	var headerHeight=55;
	var minHeight = 90+"px";
	var maxGridHeight = 350;
	$scope.showGridSection=true;
	
	
	// ui-grid to job posts to be published
	$scope.viewUnpublishedJobs = {
			enableGridMenus: false,
			enableSorting: false,
			enableFiltering: true,
			enableCellEdit: false,
			enableColumnMenus: false,
			enableHorizontalScrollbar: 0,
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
                width: '30%',
                cellTooltip: function(row, col) {
                    return 'Job Title: ' + row.entity.jobTitle;
                }
            },
            {
                name: 'vacancy',
                displayName: 'Vacancy',
                width: '9%',
                cellTooltip: function(row, col) {
                    return 'Vacancy: ' + row.entity.vacancy;
                }
            },
            {
                name: 'applicationLastDate',
                displayName: 'Application Last Date',
                width: '19%',
                cellTooltip: function(row, col) {
                    return 'Application Last Date: ' + row.entity.applicationLastDate;
                }
            },
            {
                name: 'createdOn',
                displayName: 'Created On',
                width: '10.5%',
                cellTooltip: function(row, col) {
                    return 'Created On: ' + row.entity.createdOn;
                }
            },
            {
	        	name: 'view',
	        	displayName: 'View',
	        	width: '11%',
	        	cellTemplate:'<div class="text-center"><button class="btn standardGridButton publishJobPostButton viewButton" ng-click="grid.appScope.viewJobPost(row.entity)">View</button></div>'
	        },
	        {
	        	name: 'publish',
	        	displayName: 'Publish',
	        	width: '11%',
	        	cellTemplate:'<div class="text-center"><button class="btn standardGridButton publishJobPostButton" ng-click="grid.appScope.publishJobPost(row.entity)">Publish</button></div>'
	        }
	    ]};
	
	// ui-grid to job posts to be published for small screen view
	$scope.viewUnpublishedJobsSmallScreen = {
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
                width: '25%',
                cellTooltip: function(row, col) {
                    return 'Job Id: ' + row.entity.jobId;
                }
            },
            {
                name: 'jobTitle',
                displayName: "Job Title",
                width: '75%',
                cellTooltip: function(row, col) {
                    return 'Job Title: ' + row.entity.jobTitle;
                }
            },
            {
                name: 'vacancy',
                displayName: 'Vacancy',
                width: '25%',
                cellTooltip: function(row, col) {
                    return 'Vacancy: ' + row.entity.vacancy;
                }
            },
            {
                name: 'applicationLastDate',
                displayName: 'Application Last Date',
                width: '45%',
                cellTooltip: function(row, col) {
                    return 'Application Last Date: ' + row.entity.applicationLastDate;
                }
            },
            {
                name: 'createdOn',
                displayName: 'Created On',
                width: '25%',
                cellTooltip: function(row, col) {
                    return 'Created On: ' + row.entity.createdOn;
                }
            },
            {
	        	name: 'view',
	        	displayName: 'View',
	        	width: '25%',
	        	cellTemplate:'<div class="text-center"><button class="btn standardGridButton publishJobPostButton viewButton" ng-click="grid.appScope.viewJobPost(row.entity)">View</button></div>'
	        },
	        {
	        	name: 'publish',
	        	displayName: 'Publish',
	        	width: '25%',
	        	cellTemplate:'<div class="text-center"><button class="btn standardGridButton publishJobPostButton" ng-click="grid.appScope.publishJobPost(row.entity)">Publish</button></div>'
	        }
	    ]};
	
	
	// ui-grid to view published job posts
	$scope.viewPublishedJobs = {
			enableGridMenus: false,
			enableSorting: false,
			enableFiltering: true,
			enableCellEdit: false,
			enableColumnMenus: false,
			enableHorizontalScrollbar: 0,
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
                width: '30%',
                cellTooltip: function(row, col) {
                    return 'Job Title: ' + row.entity.jobTitle;
                }
            },
            {
                name: 'vacancy',
                displayName: 'Vacancy',
                width: '9%',
                cellTooltip: function(row, col) {
                    return 'Vacancy: ' + row.entity.vacancy;
                }
            },
            {
                name: 'applicationLastDate',
                displayName: 'Application Last Date',
                width: '19%',
                cellTooltip: function(row, col) {
                    return 'Application Last Date: ' + row.entity.applicationLastDate;
                }
            },
            {
                name: 'approvalStatus',
                displayName: 'Status',
                width: '10.5%',
    			cellTemplate : '<div class="text-center approvalStatusText"><p title="Status: {{row.entity.approvalStatus}}" class="approvedText" ng-show="row.entity.approvalStatus ==\'Approved\'">{{row.entity.approvalStatus}}</p><p title="Status: {{row.entity.approvalStatus}}" class="inReviewText" ng-show="row.entity.approvalStatus ==\'In Review\'">{{row.entity.approvalStatus}}</p><p title="Status: {{row.entity.approvalStatus}}" class="rejectedText"  ng-show="row.entity.approvalStatus ==\'Rejected\'">{{row.entity.approvalStatus}}</p></div>',
            },
            {
	        	name: 'view',
	        	displayName: 'View',
	        	width: '11%',
	        	cellTemplate:'<div class="text-center"><button class="btn standardGridButton publishJobPostButton viewButton" ng-click="grid.appScope.viewJobPost(row.entity)">View</button></div>'
	        },
	        {
	        	name: 'unpublish',
	        	displayName: 'Unpublish',
	        	width: '11%',
	        	cellTemplate:'<div class="text-center"><button class="btn standardGridButton publishJobPostButton" ng-click="grid.appScope.unpublishJobPost(row.entity)">Unpublish</button></div>'
	        }
	    ]};
	
	// ui-grid to view published job posts for small screen view
	$scope.viewPublishedJobsSmallScreen = {
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
                width: '25%',
                cellTooltip: function(row, col) {
                    return 'Job Id: ' + row.entity.jobId;
                }
            },
            {
                name: 'jobTitle',
                displayName: "Job Title",
                width: '75%',
                cellTooltip: function(row, col) {
                    return 'Job Title: ' + row.entity.jobTitle;
                }
            },
            {
                name: 'vacancy',
                displayName: 'Vacancy',
                width: '25%',
                cellTooltip: function(row, col) {
                    return 'Vacancy: ' + row.entity.vacancy;
                }
            },
            {
                name: 'applicationLastDate',
                displayName: 'Application Last Date',
                width: '45%',
                cellTooltip: function(row, col) {
                    return 'Application Last Date: ' + row.entity.applicationLastDate;
                }
            },
            {
                name: 'status',
                displayName: 'Status',
                width: '25%',
    			cellTemplate : '<div class="text-center approvalStatusText"><p title="Status: {{row.entity.approvalStatus}}" class="approvedText" ng-show="row.entity.approvalStatus ==\'Approved\'">{{row.entity.approvalStatus}}</p><p title="Status: {{row.entity.approvalStatus}}" class="inReviewText" ng-show="row.entity.approvalStatus ==\'In Review\'">{{row.entity.approvalStatus}}</p><p title="Status: {{row.entity.approvalStatus}}" class="rejectedText"  ng-show="row.entity.approvalStatus ==\'Rejected\'">{{row.entity.approvalStatus}}</p></div>',
            },
            {
	        	name: 'view',
	        	displayName: 'View',
	        	width: '25%',
	        	cellTemplate:'<div class="text-center"><button class="btn standardGridButton publishJobPostButton viewButton" ng-click="grid.appScope.viewJobPost(row.entity)">View</button></div>'
	        },
	        {
	        	name: 'unpublish',
	        	displayName: 'Unpublish',
	        	width: '25%',
	        	cellTemplate:'<div class="text-center"><button class="btn standardGridButton publishJobPostButton" ng-click="grid.appScope.unpublishJobPost(row.entity)">Unpublish</button></div>'
	        }
	    ]};
	
	
	
	/** ------------------------ REQUEST TO FETCH ERROR MESSAGES--------------------------*/
	$http.get(errorMessageUrl)
		.then(function(response){
			$scope.errorMessageArray = response.data[0];
		});
	
	
	//function to calculate date in yyyy-mm-dd format
	$scope.formatDateToString = function (date){
		var dd = (date.getDate() < 10 ? '0' : '') + date.getDate();
		var MM = ((date.getMonth() + 1) < 10 ? '0' : '') + (date.getMonth() + 1);
		var yyyy = date.getFullYear();
		return (yyyy + $scope.staticContentArray.hyphenText + MM + $scope.staticContentArray.hyphenText + dd);
	}
	
	/** ------------------------ REQUEST TO FETCH STATIC CONTENT--------------------------*/
	$http.get(staticContentUrl)
		.then(function(response){
			$scope.staticContentArray= response.data[0];
		}).then(function(){
			$scope.getUnpublishedJobsUrl="/viewJobPostByStatus?jobStatus="+$scope.staticContentArray.statusNotPublished+"&page="+$scope.staticContentArray.publishJobPostPage;
			$scope.getPublishedJobsUrl="/viewJobPostByStatus?jobStatus="+$scope.staticContentArray.statusPublished+"&page="+$scope.staticContentArray.publishJobPostPage;
			$scope.populateUnpublishedJobs();
			$scope.populatePublishedJobs();
			var today = new Date();
			$scope.currentDate = $scope.formatDateToString(today);
		});
	
	
	/** ----------------------------------- METHOD TO POPULATE GRIDS -----------------------**/
	
	//Method to populate unpublished job posts
	$scope.populateUnpublishedJobs = function(){
		$http.get($scope.getUnpublishedJobsUrl)
		.then(function(response){
			if(response.data.length>0){
				$scope.viewUnpublishedJobs.data= response.data;
				$scope.viewUnpublishedJobsSmallScreen.data= response.data;
				if($scope.viewUnpublishedJobs.data.length == 0)
				{
					document.getElementById("viewUnpublishedJobs").style.height=minHeight;
					document.getElementById("viewUnpublishedJobsSmallScreen").style.height=minHeight;
				}
				else
				{
					var gridHeight = $scope.viewUnpublishedJobs.data.length * rowHeight + headerHeight;
					if(gridHeight>maxGridHeight){
						document.getElementById("viewUnpublishedJobs").style.height=maxGridHeight+"px";
						document.getElementById("viewUnpublishedJobsSmallScreen").style.height=maxGridHeight+"px";
					}
					else{
						document.getElementById("viewUnpublishedJobs").style.height=gridHeight+"px";
						document.getElementById("viewUnpublishedJobsSmallScreen").style.height=gridHeight+"px";
					}
				}
			}
			else{
				$scope.viewUnpublishedJobs.data= [];
				$scope.viewUnpublishedJobsSmallScreen.data= [];
				document.getElementById("viewUnpublishedJobs").style.height=minHeight;
				document.getElementById("viewUnpublishedJobsSmallScreen").style.height=minHeight;
			}
		});
	}
	
	
	
	//Method to populate published job posts
	$scope.populatePublishedJobs = function(){
		$http.get($scope.getPublishedJobsUrl)
		.then(function(response){
			if(response.data.length>0){
				$scope.viewPublishedJobs.data= response.data;
				$scope.viewPublishedJobsSmallScreen.data= response.data;
				if($scope.viewPublishedJobs.data.length == 0)
				{
					document.getElementById("viewPublishedJobs").style.height=minHeight;
					document.getElementById("viewPublishedJobsSmallScreen").style.height=minHeight;
				}
				else
				{
					var gridHeight = $scope.viewPublishedJobs.data.length * rowHeight + headerHeight;
					if(gridHeight>maxGridHeight){
						document.getElementById("viewPublishedJobs").style.height=maxGridHeight+"px";
						document.getElementById("viewPublishedJobsSmallScreen").style.height=maxGridHeight+"px";
					}
					else{
						document.getElementById("viewPublishedJobs").style.height=gridHeight+"px";
						document.getElementById("viewPublishedJobsSmallScreen").style.height=gridHeight+"px";
					}
				}
			}
			else{
				$scope.viewPublishedJobs.data= [];
				$scope.viewPublishedJobsSmallScreen.data= [];
				document.getElementById("viewPublishedJobs").style.height=minHeight;
				document.getElementById("viewPublishedJobsSmallScreen").style.height=minHeight;
			}
		});
	}
	
	
	/** ----------------------------------- METHOD TO UPDATE JOB STATUS -----------------------**/
	
	//Method to publish job post
	$scope.publishJobPost = function(row){
		$scope.publishJobGif=true;
		$scope.publishJobError = false;
		$scope.publishJobSuccess=false;
		var applicationLastDate = new Date(row.applicationLastDate);
		if($scope.currentDate>$scope.formatDateToString(applicationLastDate)){
			$scope.publishJobGif=false;
			$scope.publishJobErrorMessage = $scope.errorMessageArray.pastApplicationLastDateError;
			$scope.publishJobError = true;
			$scope.hideUnpublishedJobErrorMessages();
		}
		else{
			let publishJobPostUrl="/updateJobPostStatus?jobId="+row.id+"&updatedStatus="+$scope.staticContentArray.statusPublished;
			$http.get(publishJobPostUrl)
			.then(function(response){
				if(response.data == 1)
				{
					$scope.publishJobSuccessMessage = $scope.errorMessageArray.publishJobPostStatusSuccess;
					$scope.publishJobSuccess = true;
					$scope.publishJobGif=false;
					$scope.populateUnpublishedJobs();
					$scope.populatePublishedJobs();
					$scope.hideUnpublishedJobErrorMessages();
				}
				else
				{
					$scope.publishJobErrorMessage = $scope.errorMessageArray.updateJobPostStatusError;
					$scope.publishJobError = true;
					$scope.publishJobGif=false;
					$scope.hideUnpublishedJobErrorMessages();
				}
			},function(error)
			{
				$scope.publishJobErrorMessage = $scope.errorMessageArray.updateJobPostStatusError;
				$scope.publishJobError = true;
				$scope.publishJobGif=false;
				$scope.hideUnpublishedJobErrorMessages();
			});
		}
	}
	
	
	//Method to unpublish job post
	$scope.unpublishJobPost = function(row){
		$scope.publishedJobGif=true;
		$scope.publishedJobError = false;
		$scope.publishedJobSuccess=false;
		
		let unpublishJobPostUrl="/updateJobPostStatus?jobId="+row.id+"&updatedStatus="+$scope.staticContentArray.statusNotPublished;
		$http.get(unpublishJobPostUrl)
		.then(function(response){
			if(response.data == 1)
			{
				$scope.publishedJobSuccessMessage = $scope.errorMessageArray.updateJobPostStatusSuccess;
				$scope.publishedJobSuccess = true;
				$scope.publishedJobGif=false;
				$scope.populateUnpublishedJobs();
				$scope.populatePublishedJobs();
				$scope.hidePublishedJobErrorMessages();
			}
			else
			{
				$scope.publishedJobErrorMessage = $scope.errorMessageArray.updateJobPostStatusError;
				$scope.publishedJobError = true;
				$scope.publishedJobGif=false;
				$scope.hidePublishedJobErrorMessages();
			}
		},function(error)
		{
			$scope.publishedJobErrorMessage = $scope.errorMessageArray.updateJobPostStatusError;
			$scope.publishedJobError = true;
			$scope.publishedJobGif=false;
			$scope.hidePublishedJobErrorMessages();
		});
		
	}
	
	
	/** ----------------------------------- METHOD TO DOWNLOAD JOB DESCRIPTION DOCUMENT -----------------------**/

    $scope.downloadJobDescription = function(row){
    	$scope.downloadError = false;
    	$scope.downloadGif=true;
    	let viewJobDescriptionDocumentUrl ="/downloadPdfFile/"+row.descriptionDocumentPath;
		$http.get(viewJobDescriptionDocumentUrl, {
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
				$scope.downloadGif=false;
			} else {
				$scope.downloadErrorMessage = $scope.errorMessageArray.descriptionDocumentDownloadError;
				$scope.downloadError=true;
				$scope.downloadGif=false;
				$timeout(function(){
					$scope.downloadError=false;
				},7000);
			}
			
		},function(error){
			$scope.downloadErrorMessage = $scope.errorMessageArray.descriptionDocumentDownloadError;
			$scope.downloadError=true;
			$scope.downloadGif=false;
			$timeout(function(){
				$scope.downloadError=false;
			},7000);
			
		});
    }
    
    
    //Method to view job post details
    $scope.viewJobPost=function(row){
    	$scope.showGridSection=false;
    	$scope.jobPostInfo=row;
    }
    
    //Method to hide job post information
    $scope.hideJobDetails=function(){
    	$scope.showGridSection=true;
    }

    //Method to hide error messages
    $scope.hideUnpublishedJobErrorMessages=function(){
    	$timeout(function(){
			$scope.publishJobError=false;
			$scope.publishJobSuccess=false;
		},7000);
    }
    
    //Method to hide error messages
    $scope.hidePublishedJobErrorMessages=function(){
    	$timeout(function(){
			$scope.publishedJobError=false;
			$scope.publishedJobSuccess=false;
		},7000);
    }
        
    
});