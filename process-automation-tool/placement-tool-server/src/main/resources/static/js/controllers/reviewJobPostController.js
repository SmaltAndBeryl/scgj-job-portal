angular.module("app")
.controller("reviewJobPostController",function($scope,$timeout,$http,$window){
	$scope.viewGridSection=true;	
	var errorMessageUrl ="js/controllers/errorMessages.json";
	var staticContentUrl ="js/controllers/staticContent.json";
	var rowHeight=30;
	var headerHeight=75;
	var minHeight = 90+"px";
	var maxGridHeight = 350;
	$scope.updateJobPostObject = {
			jobId:"",
			adminComments:"",
			updatedStatus:""
		}
	
	$scope.showAlertBox=false;
	
	//ui-grid for in review job posts
	$scope.reviewJobPostGrid ={
			enableFiltering:true,
			enableGridMenus: false,
			enableSorting: false,
			enableColumnMenus: false,
	        enableHorizontalScrollbar: 1,
	        enableVerticalScrollbar: 1,
	        enableRowHeaderSelection : false,
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
                width: '22%',
                cellTooltip: function(row, col) {
                    return 'Job Title: ' + row.entity.jobTitle;
                }
            },
            {
                name: 'vacancy',
                displayName: 'Vacancies',
                width: '11%',
                cellTooltip: function(row, col) {
                    return 'Vacancies: ' + row.entity.vacancy;
                }
            },
            {
                name: 'createdBy',
                displayName: 'Created By',
                width: '20%',
                cellTooltip: function(row, col) {
                    return 'Created By: ' + row.entity.createdBy;
                }
            },
            {
                name: 'publishedOn',
                displayName: 'Published On',
                width: '14%',
                cellTooltip: function(row, col) {
                    return 'Published On: ' + row.entity.publishedOn;
                }
            },
            {
                name: 'status',
                displayName: 'Status',
                width: '10%',
                cellTemplate:'<div class="text-center"><p title="Status: {{row.entity.status}}" class="gridText inReviewText">{{row.entity.status}}</p></div>'
            },
            {
                name: 'approve',
                displayName: 'Approve',
                width: '10%',
                cellTemplate:'<div class="text-center"><button class="btn standardGridButton reviewJobPostGridButton approveJobPostButton" ng-click="grid.appScope.updateJobPostStatus(row.entity,\'Approved\')">Approve</button></div>'
            },
            {
                name: 'reject',
                displayName: 'Reject',
                width: '10%',
                cellTemplate:'<div class="text-center"><button class="btn standardGridButton reviewJobPostGridButton rejectJobPostButton"  ng-click="grid.appScope.showModal(row.entity)">Reject</button></div>'
            },
            {
                name: 'view',
                displayName: 'View',
                width: '10%',
                cellTemplate:'<div class="text-center"><button class="btn btn-primary standardGridButton reviewJobPostGridButton" ng-click="grid.appScope.showHideJobDetails(row.entity)">View</button></div>'
            }]
	};
	
	
	//ui-grid for in review job posts for small screens
	$scope.reviewJobPostGridSmallScreen ={
			enableFiltering:true,
			enableGridMenus: false,
			enableSorting: false,
			enableColumnMenus: false,
	        enableHorizontalScrollbar: 1,
	        enableVerticalScrollbar: 1,
	        enableRowHeaderSelection : false,
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
                width: '60%',
                cellTooltip: function(row, col) {
                    return 'Job Title: ' + row.entity.jobTitle;
                }
            },
            {
                name: 'vacancy',
                displayName: 'Vacancies',
                width: '25%',
                cellTooltip: function(row, col) {
                    return 'Vacancies: ' + row.entity.vacancy;
                }
            },
            {
                name: 'createdBy',
                displayName: 'Created By',
                width: '60%',
                cellTooltip: function(row, col) {
                    return 'Created By: ' + row.entity.createdBy;
                }
            },
            {
                name: 'publishedOn',
                displayName: 'Published On',
                width: '30%',
                cellTooltip: function(row, col) {
                    return 'Published On: ' + row.entity.publishedOn;
                }
            },
            {
                name: 'status',
                displayName: 'Status',
                width: '25%',
                cellTemplate:'<div class="text-center"><p title="Status: {{row.entity.status}}" class="gridText inReviewText">{{row.entity.status}}</p></div>'
            },
            {
                name: 'approve',
                displayName: 'Approve',
                width: '25%',
                cellTemplate:'<div class="text-center"><button class="btn standardGridButton reviewJobPostGridButton approveJobPostButton" ng-click="grid.appScope.updateJobPostStatus(row.entity,\'Approved\')">Approve</button></div>'
            },
            {
                name: 'reject',
                displayName: 'Reject',
                width: '25%',
                cellTemplate:'<div class="text-center"><button class="btn standardGridButton reviewJobPostGridButton rejectJobPostButton" ng-click="grid.appScope.showModal(row.entity)">Reject</button></div>'
            },
            {
                name: 'view',
                displayName: 'View',
                width: '25%',
                cellTemplate:'<div class="text-center"><button class="btn btn-primary standardGridButton reviewJobPostGridButton" ng-click="grid.appScope.showHideJobDetails(row.entity)">View</button></div>'
            }]
	};

/*------------------------------APPROVED GRID-----------------------------------------------*/
    //ui-grid for in approved job posts
    $scope.approvedJobPostGrid ={
        enableFiltering:true,
        enableGridMenus: false,
        enableSorting: false,
        enableColumnMenus: false,
        enableHorizontalScrollbar: 1,
        enableVerticalScrollbar: 1,
        enableRowHeaderSelection : false,
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
                width: '22%',
                cellTooltip: function(row, col) {
                    return 'Job Title: ' + row.entity.jobTitle;
                }
            },
            {
                name: 'vacancy',
                displayName: 'Vacancies',
                width: '11%',
                cellTooltip: function(row, col) {
                    return 'Vacancies: ' + row.entity.vacancy;
                }
            },
            {
                name: 'createdBy',
                displayName: 'Created By',
                width: '20%',
                cellTooltip: function(row, col) {
                    return 'Created By: ' + row.entity.createdBy;
                }
            },
            {
                name: 'publishedOn',
                displayName: 'Published On',
                width: '14%',
                cellTooltip: function(row, col) {
                    return 'Published On: ' + row.entity.publishedOn;
                }
            },
            {
                name: 'status',
                displayName: 'Status',
                width: '10%',
                cellTemplate:'<p title="Status: Approved" class="approvedStatus text-center gridText">Approved</p>'
            },
            {
                name: 'approvedRejectedOn',
                displayName: 'Approved On',
                width: '14%',
                cellTooltip: function (row, col) {
                    return 'Approved on : ' + row.entity.approvedRejectedOn;
                }
            },
            {
                name: 'view',
                displayName: 'View',
                width: '10%',
                cellTemplate:'<div class="text-center"><button class="btn btn-primary standardGridButton reviewJobPostGridButton" ng-click="grid.appScope.showHideJobDetails(row.entity)">View</button></div>'
            }]
    };


    $scope.approvedJobPostGridSmallScreen ={
        enableFiltering:true,
        enableGridMenus: false,
        enableSorting: false,
        enableColumnMenus: false,
        enableHorizontalScrollbar: 1,
        enableVerticalScrollbar: 1,
        enableRowHeaderSelection : false,
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
                width: '60%',
                cellTooltip: function(row, col) {
                    return 'Job Title: ' + row.entity.jobTitle;
                }
            },
            {
                name: 'vacancy',
                displayName: 'Vacancies',
                width: '25%',
                cellTooltip: function(row, col) {
                    return 'Vacancies: ' + row.entity.vacancy;
                }
            },
            {
                name: 'createdBy',
                displayName: 'Created By',
                width: '60%',
                cellTooltip: function(row, col) {
                    return 'Created By: ' + row.entity.createdBy;
                }
            },
            {
                name: 'publishedOn',
                displayName: 'Published On',
                width: '30%',
                cellTooltip: function(row, col) {
                    return 'Published On: ' + row.entity.publishedOn;
                }
            },
            {
                name: 'status',
                displayName: 'Status',
                width: '25%',
                cellTemplate:'<p title="Status: Approved" class="approvedStatus text-center gridText">Approved</p>'

            },
            {
                name: 'approvedRejectedOn',
                displayName: 'Approved On',
                width: '30%',
                cellTooltip: function (row, col) {
                    return 'Approved on : ' + row.entity.approvedRejectedOn;
                }
            },
            {
                name: 'view',
                displayName: 'View',
                width: '25%',
                cellTemplate:'<div class="text-center"><button class="btn btn-primary standardGridButton reviewJobPostGridButton" ng-click="grid.appScope.showHideJobDetails(row.entity)">View</button></div>'
            }]
    };



    /*------------------------------REJECTED GRID-----------------------------------------------*/
    //ui-grid for in rejected job posts
    $scope.rejectedJobPostGrid ={
        enableFiltering:true,
        enableGridMenus: false,
        enableSorting: false,
        enableColumnMenus: false,
        enableHorizontalScrollbar: 1,
        enableVerticalScrollbar: 1,
        enableRowHeaderSelection : false,
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
                width: '22%',
                cellTooltip: function(row, col) {
                    return 'Job Title: ' + row.entity.jobTitle;
                }
            },
            {
                name: 'vacancy',
                displayName: 'Vacancies',
                width: '11%',
                cellTooltip: function(row, col) {
                    return 'Vacancies: ' + row.entity.vacancy;
                }
            },
            {
                name: 'createdBy',
                displayName: 'Created By',
                width: '20%',
                cellTooltip: function(row, col) {
                    return 'Created By: ' + row.entity.createdBy;
                }
            },
            {
                name: 'publishedOn',
                displayName: 'Published On',
                width: '14%',
                cellTooltip: function(row, col) {
                    return 'Published On: ' + row.entity.publishedOn;
                }
            },
            {
                name: 'status',
                displayName: 'Status',
                width: '10%',
                cellTemplate:'<p title="Status: Rejected" class="rejectedStatus text-center gridText">Rejected</p>'

            },
            {
                name: 'approvedRejectedOn',
                displayName: 'Rejected On',
                width: '14%',
                cellTooltip: function (row, col) {
                    return 'Rejected on : ' + row.entity.approvedRejectedOn;
                }
            },
            {
                name: 'view',
                displayName: 'View',
                width: '10%',
                cellTemplate:'<div class="text-center"><button class="btn btn-primary standardGridButton reviewJobPostGridButton" ng-click="grid.appScope.showHideJobDetails(row.entity)">View</button></div>'
            }]
    };


    $scope.rejectedJobPostGridSmallScreen ={
        enableFiltering:true,
        enableGridMenus: false,
        enableSorting: false,
        enableColumnMenus: false,
        enableHorizontalScrollbar: 1,
        enableVerticalScrollbar: 1,
        enableRowHeaderSelection : false,
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
                width: '60%',
                cellTooltip: function(row, col) {
                    return 'Job Title: ' + row.entity.jobTitle;
                }
            },
            {
                name: 'vacancy',
                displayName: 'Vacancies',
                width: '25%',
                cellTooltip: function(row, col) {
                    return 'Vacancies: ' + row.entity.vacancy;
                }
            },
            {
                name: 'createdBy',
                displayName: 'Created By',
                width: '60%',
                cellTooltip: function(row, col) {
                    return 'Created By: ' + row.entity.createdBy;
                }
            },
            {
                name: 'publishedOn',
                displayName: 'Published On',
                width: '30%',
                cellTooltip: function(row, col) {
                    return 'Published On: ' + row.entity.publishedOn;
                }
            },
            {
                name: 'status',
                displayName: 'Status',
                width: '25%',
                cellTemplate:'<p title="Status: Rejected" class="rejectedStatus text-center gridText">Rejected</p>'
            },
            {
                name: 'approvedRejectedOn',
                displayName: 'Rejected On',
                width: '30%',
                cellTooltip: function (row, col) {
                    return 'Rejected on : ' + row.entity.approvedRejectedOn;
                }
            },
            {
                name: 'view',
                displayName: 'View',
                width: '25%',
                cellTemplate:'<div class="text-center"><button class="btn btn-primary standardGridButton reviewJobPostGridButton" ng-click="grid.appScope.showHideJobDetails(row.entity)">View</button></div>'
            }]
    };

    /** ------------------------ REQUEST TO FETCH ERROR MESSAGES--------------------------*/
    $http.get(errorMessageUrl)
        .then(function(response){
            $scope.errorMessageArray = response.data[0];
        });

    /** ------------------------ REQUEST TO FETCH STATIC CONTENT--------------------------*/
    $http.get(staticContentUrl)
        .then(function(response){
            $scope.staticContentArray= response.data[0];
        }).then(function(){
        $scope.getInReviewJobPostUrl="/fetchJobPostDetailsWithStatus?status="+$scope.staticContentArray.jobInReviewStatus;
        $scope.getApprovedJobPostUrl = "/fetchJobPostDetailsWithStatus?status="+$scope.staticContentArray.jobApprovedStatus;
        $scope.getRejectedJobPosturl = "/fetchJobPostDetailsWithStatus?status="+$scope.staticContentArray.jobRejectedStatus;
        $scope.populateInReviewJobs();
        $scope.viewApprovedJobPosts();
        $scope.viewRejectedJobPosts();
        
    });


    /*-------------------------------------FUNCTION TO POPULATE GRID---------------------------------*/

    $scope.populateInReviewJobs=function(){
        $http.get($scope.getInReviewJobPostUrl)
            .then(function(response){
                if(response.data.length>0){
                    $scope.reviewJobPostGrid.data= response.data;
                    $scope.reviewJobPostGridSmallScreen.data= response.data;

                    if($scope.reviewJobPostGrid.data.length == 0)
                    {
                        document.getElementById("reviewJobPostGrid").style.height=minHeight;
                        document.getElementById("reviewJobPostGridSmallScreen").style.height=minHeight;
                    }
                    else
                    {
                        var gridHeight = $scope.reviewJobPostGrid.data.length * rowHeight + headerHeight;
                        if(gridHeight>maxGridHeight){
                            document.getElementById("reviewJobPostGrid").style.height=maxGridHeight+"px";
                            document.getElementById("reviewJobPostGridSmallScreen").style.height=maxGridHeight+"px";
                        }
                        else{
                            document.getElementById("reviewJobPostGrid").style.height=gridHeight+"px";
                            document.getElementById("reviewJobPostGridSmallScreen").style.height=gridHeight+"px";
                        }
                    }
                }
                else{
                    $scope.reviewJobPostGrid.data= [];
                    $scope.reviewJobPostGridSmallScreen.data= [];
                    document.getElementById("reviewJobPostGrid").style.height=minHeight;
                    document.getElementById("reviewJobPostGridSmallScreen").style.height=minHeight;
                }
            });
    };

    //Method to populate view job posts ui-grid in which candidate has applied
    $scope.viewApprovedJobPosts = function()
    {
        $http.get($scope.getApprovedJobPostUrl)
            .then(function(response) {
                if (response.data.length > 0) {
                    $scope.approvedJobPostGrid.data = response.data;
                    $scope.approvedJobPostGridSmallScreen.data = response.data;
                }
                $scope.correctApprovedJobPostGridSizing();
            });
    };

    $scope.viewRejectedJobPosts = function()
    {
        $http.get($scope.getRejectedJobPosturl)
            .then(function(response) {
                if (response.data.length > 0) {
                    $scope.rejectedJobPostGrid.data = response.data;
                    $scope.rejectedJobPostGridSmallScreen.data = response.data;
                }
                $scope.correctRejectedJobPostGridSizing();
            });
    };

    /*-------------------------------------FUNCTION TO RESIZE GRID----------------------------------*/
    //for reviewJobPostGrid
    $scope.correctInReviewJobPostGridSizing = function (){
        if($scope.reviewJobPostGrid.data.length == 0){
            $scope.reviewJobPostGrid.data= [];
            $scope.reviewJobPostGridSmallScreen.data= [];
            document.getElementById("reviewJobPostGrid").style.height=minHeight;
            document.getElementById("reviewJobPostGridSmallScreen").style.height=minHeight;
        }
        else
        {
            var gridHeight = $scope.reviewJobPostGrid.data.length * rowHeight + headerHeight;
            if(gridHeight>maxGridHeight){
                document.getElementById("reviewJobPostGrid").style.height=maxGridHeight+"px";
                document.getElementById("reviewJobPostGridSmallScreen").style.height=maxGridHeight+"px";
            }
            else{
                document.getElementById("reviewJobPostGrid").style.height=gridHeight+"px";
                document.getElementById("reviewJobPostGridSmallScreen").style.height=gridHeight+"px";
            }
        }
    };

    //For approvedJobPostGrid
    $scope.correctApprovedJobPostGridSizing = function (){
        if($scope.approvedJobPostGrid.data.length == 0)
        {
            $scope.approvedJobPostGrid.data= [];
            $scope.approvedJobPostGridSmallScreen.data= [];
            document.getElementById("approvedJobPostGrid").style.height=minHeight;
            document.getElementById("approvedJobPostGridSmallScreen").style.height=minHeight;
        }
        else
        {
            var gridHeight = $scope.approvedJobPostGrid.data.length * rowHeight + headerHeight;
            if(gridHeight>maxGridHeight){
                document.getElementById("approvedJobPostGrid").style.height=maxGridHeight+"px";
                document.getElementById("approvedJobPostGridSmallScreen").style.height=maxGridHeight+"px";
            }
            else
            {
                document.getElementById("approvedJobPostGrid").style.height=gridHeight+"px";
                document.getElementById("approvedJobPostGridSmallScreen").style.height=gridHeight+"px";
            }
        }
    };

    // For rejectedJobPostGrid
    $scope.correctRejectedJobPostGridSizing = function (){
        if($scope.rejectedJobPostGrid.data.length == 0){

            $scope.rejectedJobPostGrid.data= [];
            $scope.rejectedJobPostGridSmallScreen.data= [];
            document.getElementById("rejectedJobPostGrid").style.height=minHeight;
            document.getElementById("rejectedJobPostGridSmallScreen").style.height=minHeight;
        }
        else
        {
            var gridHeight = $scope.rejectedJobPostGrid.data.length * rowHeight + headerHeight;
            if(gridHeight>maxGridHeight){
                document.getElementById("rejectedJobPostGrid").style.height=maxGridHeight+"px";
                document.getElementById("rejectedJobPostGridSmallScreen").style.height=maxGridHeight+"px";
            }
            else
            {
                document.getElementById("rejectedJobPostGrid").style.height=gridHeight+"px";
                document.getElementById("rejectedJobPostGridSmallScreen").style.height=gridHeight+"px";
            }
        }
    }


	//Method to view job details
	$scope.showHideJobDetails = function(row){
		$scope.jobPostInfo=row;
		$scope.viewGridSection=!$scope.viewGridSection;
	}
	
	
	//Method to open popup to add admin comments
	$scope.showModal=function(row){
		$scope.error=false;
		$scope.success=false;
		$scope.modalError=false;
		$('#rejectJobPostModal').modal('show');
		$scope.adminComments="";
		$scope.jobPostInfo=row;
	}
	
	
	//Method to reject job post status
	$scope.rejectJobPost=function(row){
		$scope.updateJobPostObject.updatedStatus=$scope.staticContentArray.jobRejectedStatus;
		if($scope.adminComments =="" || $scope.adminComments == null || $scope.adminComments == undefined){
			$scope.modalErrorMessage=$scope.errorMessageArray.invalidCommentError;
			$scope.modalError=true;
			$scope.hideErrorSuccessMessage();
			$scope.updateStatusFlag = false;
		}
		else{
			$scope.updateJobPostObject.adminComments=$scope.adminComments;
			$scope.updateJobPostStatus(row);
		}
	}
	
	
	
	//Method to approve job post
	$scope.updateJobPostStatus = function(row,updatedStatus){
		$('#rejectJobPostModal').modal('hide');
		$scope.error=false;
		$scope.success=false;
		$scope.updateStatusFlag = true;
		
		$scope.updateJobPostObject.jobId=row.id;
		if(updatedStatus == $scope.staticContentArray.jobApprovedStatus){
			$scope.updateJobPostObject.updatedStatus=updatedStatus;
		}
		
		//If input is valid, update job post approval status
		if($scope.updateStatusFlag){
			$http({
				method: 'POST',
				url: '/updateJobPostApprovalStatus',
				data: $scope.updateJobPostObject
	        }).then(function(response){
	        	if(response.data == 1){
	        		$scope.successMessage=$scope.errorMessageArray.updateJobPostApprovalStatusSuccess;
	    			$scope.success=true;
                    $scope.populateInReviewJobs();
                    $scope.viewApprovedJobPosts();
                    $scope.viewRejectedJobPosts();
	    			$scope.hideErrorSuccessMessage();
	    			if(updatedStatus == $scope.staticContentArray.jobApprovedStatus){
	    				$scope.showAlertBox=true;
	    			}
	        	}
	        	else if(response.data == -88){
	        		$scope.errorMessage = $scope.errorMessageArray.invalidJobPostApprovalStatusError;
	        		$scope.error=true;
	        		$scope.hideErrorSuccessMessage();
	        	}
	        	else{
	        		$scope.errorMessage = $scope.errorMessageArray.updateJobPostApprovalStatusError;
	        		$scope.error=true;
	        		$scope.hideErrorSuccessMessage();
	        	}
	        },function(error){
	        	$scope.errorMessage = $scope.errorMessageArray.updateJobPostApprovalStatusError;
        		$scope.error=true;
        		$scope.hideErrorSuccessMessage();
	        });
		}
	};
	
	
	
	
/** ----------------------------------- METHOD TO DOWNLOAD JOB DESCRIPTION DOCUMENT -----------------------**/
	
	$scope.downloadGif=false;
    $scope.downloadJobDescription = function(jobPostDetails){
    	$scope.downloadError = false;
    	$scope.downloadGif=true;
    	let viewJobDescriptionUrl ="/downloadPdfFile/"+jobPostDetails.jobDescriptionDocumentPath;
		$http.get(viewJobDescriptionUrl, {
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
				link.download =jobPostDetails.jobId+$scope.staticContentArray.underScore+$scope.staticContentArray.descriptionDocumentText;
				document.body.appendChild(link);
				link.click();
		    	$scope.downloadGif=false;
			} else {
				$scope.downloadErrorMessage = $scope.errorMessageArray.descriptionDocumentDownloadError;
				$scope.downloadError = false;
		    	$scope.downloadGif=true;
			}
			$scope.hideErrorSuccessMessage();
			
		},function(error){
			$scope.downloadErrorMessage = $scope.errorMessageArray.descriptionDocumentDownloadError;
			$scope.downloadError = false;
	    	$scope.downloadGif=true;
			$scope.hideErrorSuccessMessage();
		});
    }
	
	//hide error message
	$scope.hideErrorSuccessMessage = function(){
		$timeout(function(){
			$scope.error=false;
			$scope.success=false;
			$scope.modalError=false;
		},7000);
	}
	
	//Method to dismiss error/success message on close button click
	$scope.dismissErrorSuccessMessage = function(){
		$scope.success=false;
		$scope.error=false;
	}
	
	//Method to reload page
	$scope.reloadPage = function(){
    	$window.location.reload();
    }

});