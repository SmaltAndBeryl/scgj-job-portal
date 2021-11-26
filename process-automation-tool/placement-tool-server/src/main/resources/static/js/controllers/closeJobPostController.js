var closeJobPost = angular.module("app");
closeJobPost.controller("closeJobPostController",function($scope,$http,$timeout){

	var errorMessageUrl ="js/controllers/errorMessages.json";
	var staticContentUrl ="js/controllers/staticContent.json";
	var rowHeight=30;
	var headerHeight=55;
	var minHeight = "90px";
	var maxGridHeight = 350;
    $scope.closeJobPostError=false;
	$scope.closeJobPostSuccess=false;
	$scope.closeJobPostGif=false;
    $scope.closedJobPostError=false;
	$scope.closedJobPostGif=false;
	$scope.getCloseJobPostUrl="/viewJobPostByStatus";
	$scope.getClosedJobPostUrl="/viewJobPostByStatus";
	$scope.closeJobPostUrl="/closeJobPost";
	$scope.viewJobDescriptionUrl="/downloadPdfFile/";
	
	
    
    //ui-grid for job posts to be closed [published]
	$scope.closeJobPostGrid={
        enableFiltering:true,
		enableGridMenus: false,
		enableSorting: false,
		enableColumnMenus: false,
        enableHorizontalScrollbar: 0,
        enableVerticalScrollbar: 1,
        enableRowHeaderSelection : false,
        columnDefs:[{
            name : "jobId",
            displayName : "Job Id",
            width : '10%',
            cellTooltip: function(row, col) {
            	return 'Job Id: ' + row.entity.jobId;
            },
			headerTooltip: true
        },
        {
            name : "jobTitle",
            displayName : "Job Title",
            width : '20%',
            cellTooltip: function(row, col) {
            	return 'Job Title: ' + row.entity.jobTitle;
            },
			headerTooltip: true
        },
        {
            name : "vacancy",
            displayName : "Vacancy",
            width : '10%',
            cellTooltip: function(row, col) {
            	return 'Vacancy: ' + row.entity.vacancy;
            },
			headerTooltip: true
        },
        {
            name : "applicationLastDate",
            displayName : "Application Last Date",
            width : '15%',
            cellTooltip: function(row, col) {
            	return 'Application Last Date: ' + row.entity.applicationLastDate;
            },
			headerTooltip: true
        },
        {
            name : "publishedOn", 
            displayName : "Published On",
            width : '13%',
            cellTooltip: function(row, col) {
            	return 'Published On: ' + row.entity.publishedOn;
            },
			headerTooltip: true
        },
        {
            name : "jobStatus",
            displayName : "Status",
            width : '12%',
            cellTooltip: function(row, col) {
            	return 'Status: ' + row.entity.jobStatus;
            },
			headerTooltip: true
        },
        {
            name : "descriptionDocumentPath",
            displayName : "Description",
            width : '10%',
            cellTooltip: 'Download Document',
            cellTemplate:'<div class="text-center"><img ng-hide="row.entity.descriptionDocumentPath == null" src="../images/pdfIcon.png" alt="Download Pdf Icon" class="gridDownloadJobDescriptionIcon closeGrid" ng-click="grid.appScope.downloadCloseJobDescription(row.entity)"><p class="notAvailableGridText" ng-show="row.entity.descriptionDocumentPath == null">N/A</p></div>'
        },
        {
            name:'Close',
			cellToolTip : "Close Job Post",
            width: '10%',
            cellTemplate:'<div class="text-center"><button class="btn standardGridButton closeJobPostButton" ng-click="grid.appScope.closeJobPost(row.entity)">Close</button></div>'
        }
    ]
    };

	//ui-grid for Job Post to be closed for Small Screen
   $scope.closeJobPostGridSmallScreen={
        enableFiltering:true,
		enableGridMenus: false,
		enableSorting: false,
		enableColumnMenus: false,
        enableHorizontalScrollbar: 1,
        enableVerticalScrollbar: 1,
        enableRowHeaderSelection : false,
        columnDefs:[{
            name : "jobId",
            displayName : "Job Id",
            width : '25%',
            cellTooltip: function(row, col) {
            	return 'Job Id: ' + row.entity.jobId;
            },
			headerTooltip: true
        },
        {
            name : "jobTitle",
            displayName : "Job Title",
            width : '70%',
            cellTooltip: function(row, col) {
            	return 'Job Title: ' + row.entity.jobTitle;
            },
			headerTooltip: true
        },
        {
            name : "vacancy",
            displayName : "Vacancy",
            width : '25%',
            cellTooltip: function(row, col) {
            	return 'Vacancy: ' + row.entity.vacancy;
            },
			headerTooltip: true
        },
        {
            name : "applicationLastDate",
            displayName : "Application Last Date",
            width : '50%',
            cellTooltip: function(row, col) {
            	return 'Application Last Date: ' + row.entity.applicationLastDate;
            },
			headerTooltip: true
        },
        {
            name : "publishedOn", 
            displayName : "Published On",
            width : '35%',
            cellTooltip: function(row, col) {
            	return 'Published On: ' + row.entity.publishedOn;
            },
			headerTooltip: true
        },
        {
            name : "jobStatus",
            displayName : "Status",
            width : '35%',
            cellTooltip: function(row, col) {
            	return 'Status: ' + row.entity.jobStatus;
            },
			headerTooltip: true
        },
        {
            name : "descriptionDocumentPath",
            displayName : "Description",
            width : '28%',
            cellTooltip: 'Download Document',
            cellTemplate:'<div class="text-center"><img ng-hide="row.entity.descriptionDocumentPath == null" src="../images/pdfIcon.png" alt="Download Pdf Icon" class="gridDownloadJobDescriptionIcon closeGrid" ng-click="grid.appScope.downloadCloseJobDescription(row.entity)"><p class="notAvailableGridText" ng-show="row.entity.descriptionDocumentPath == null">N/A</p></div>'
        },
        {
            name:'Close',
            width: '28%',
			cellToolTip : "Close Job Post",
            cellTemplate:'<div class="text-center"><button class="btn standardGridButton closeJobPostButton" ng-click="grid.appScope.closeJobPost(row.entity)">Close</button></div>'
        }
    ]
    };

		//ui-grid for closed job post
        $scope.closedJobPostGrid={
        enableFiltering:true,
		enableGridMenus: false,
		enableSorting: false,
		enableColumnMenus: false,
        enableHorizontalScrollbar: 0,
        enableVerticalScrollbar: 1,
        enableRowHeaderSelection : false,
        columnDefs:[{
            name : "jobId",
            displayName : "Job Id",
            width : '10%',
            cellTooltip: function(row, col) {
            	return 'Job Id: ' + row.entity.jobId;
            },
			headerTooltip: true
        },
        {
            name : "jobTitle",
            displayName : "Job Title",
            width : '20%',
            cellTooltip: function(row, col) {
            	return 'Job Title: ' + row.entity.jobTitle;
            },
			headerTooltip: true
        },
        {
            name : "vacancy",
            displayName : "Vacancy",
            width : '15%',
            cellTooltip: function(row, col) {
            	return 'Vacancy: ' + row.entity.vacancy;
            },
			headerTooltip: true
        },
        {
            name : "applicationLastDate",
            displayName : "Application Last Date",
            width : '15%',
            cellTooltip: function(row, col) {
            	return 'Application Last Date: ' + row.entity.applicationLastDate;
            },
			headerTooltip: true
        },
        {
            name : "updatedOn",
            displayName : "Closed On",
            width : '15%',
            cellTooltip: function(row, col) {
            	return 'Closed On: ' + row.entity.updatedOn;
            },
			headerTooltip: true
        },
        {
            name : "jobStatus",
            displayName : "Status",
            width : '15%',
            cellTooltip: function(row, col) {
            	return 'Status: ' + row.entity.jobStatus;
            },
			headerTooltip: true
        },
        {
            name : "descriptionDocumentPath",
            displayName : "Description",
            width : '10%',
            cellTooltip: 'Download Document',
            cellTemplate:'<div class="text-center"><img ng-hide="row.entity.descriptionDocumentPath == null" src="../images/pdfIcon.png" alt="Download Pdf Icon" class="gridDownloadJobDescriptionIcon" id="pdfIconInClosedJobPostGrid" ng-click="grid.appScope.downloadClosedJobDescription(row.entity)"><p class="notAvailableGridText" ng-show="row.entity.descriptionDocumentPath == null">N/A</p></div>'
        }
    ]
    };
    
		//ui-grid for closed job post for Small Screen
        $scope.closedJobPostGridSmallScreen={
        enableFiltering:true,
		enableGridMenus: false,
		enableSorting: false,
		enableColumnMenus: false,
        enableHorizontalScrollbar: 1,
        enableVerticalScrollbar: 1,
        enableRowHeaderSelection : false,
        columnDefs:[{
            name : "jobId",
            displayName : "Job Id",
            width : '25%',
            cellTooltip: function(row, col) {
            	return 'Job Id: ' + row.entity.jobId;
            },
			headerTooltip: true
        },
        {
            name : "jobTitle",
            displayName : "Job Title",
            width : '70%',
            cellTooltip: function(row, col) {
            	return 'Job Title: ' + row.entity.jobTitle;
            },
			headerTooltip: true
        },
        {
            name : "vacancy",
            displayName : "Vacancy",
            width : '25%',
            cellTooltip: function(row, col) {
            	return 'Vacancy: ' + row.entity.vacancy;
            },
			headerTooltip: true
        },
        {
            name : "applicationLastDate",
            displayName : "Application Last Date",
            width : '50%',
            cellTooltip: function(row, col) {
            	return 'Application Last Date: ' + row.entity.applicationLastDate;
            },
			headerTooltip: true
        },
        {
            name : "updatedOn",
            displayName : "Closed On",
            width : '35%',
            cellTooltip: function(row, col) {
            	return 'Closed On: ' + row.entity.updatedOn;
            },
			headerTooltip: true
        },
        {
            name : "jobStatus",
            displayName : "Status",
            width : '35%',
            cellTooltip: function(row, col) {
            	return 'Status: ' + row.entity.jobStatus;
            },
			headerTooltip: true
        },
        {
            name : "descriptionDocumentPath",
            displayName : "Description",
            width : '28%',
            cellTooltip: function(row, col) {
            	return 'Job Id: ' + row.entity.jobId;
            },
            cellTemplate:'<div class="text-center"><img ng-hide="row.entity.descriptionDocumentPath == null" src="../images/pdfIcon.png" alt="Download Pdf Icon" class="gridDownloadJobDescriptionIcon" id="pdfIconInClosedJobPostGrid" ng-click="grid.appScope.downloadClosedJobDescription(row.entity)"><p class="notAvailableGridText" ng-show="row.entity.descriptionDocumentPath == null">N/A</p></div>'
        }
    ]
    };

/** ------------------------ REQUEST TO FETCH ERROR MESSAGES--------------------------**/

	$http.get(errorMessageUrl)
	.then(function(response){
		$scope.errorMessageArray = response.data[0];
	});
	
/** ------------------------ REQUEST TO FETCH STATIC CONTENT--------------------------**/
	
	$http.get(staticContentUrl)
	.then(function(response){
		$scope.staticContentArray=response.data[0];
		//calling methods to populate ui-grid
		$scope.populateCloseJobPostGrid();
		$scope.populateClosedJobPostGrid();
	})
	
	
/** ----------------------------------- METHOD TO POPULATE GRIDS -----------------------**/

//method to populate grid to close job post
	$scope.populateCloseJobPostGrid = function(){
		$http.get($scope.getCloseJobPostUrl+"?jobStatus="+$scope.staticContentArray.statusPublished+"&page="+$scope.staticContentArray.closeJobPostPage)
		.then(function(response){
			$scope.closeJobPostGrid.data = response.data;
			$scope.closeJobPostGridSmallScreen.data = response.data;
			var gridHeight=$scope.closeJobPostGrid.data.length*rowHeight + headerHeight;
			if(response.data.length>0){
				if(gridHeight>maxGridHeight){
					document.querySelector(".closeJobPostGrid").style.height = maxGridHeight + "px";
					document.querySelector(".closeJobPostGridSmallScreen").style.height = maxGridHeight + "px";
				}
				else{
					document.querySelector(".closeJobPostGrid").style.height = gridHeight + "px";
					document.querySelector(".closeJobPostGridSmallScreen").style.height = gridHeight + "px";
				}
			}
			else{
				document.querySelector(".closeJobPostGrid").style.height = minHeight;
				document.querySelector(".closeJobPostGridSmallScreen").style.height = minHeight;
			}
		});
	};
	
	//method to populate closed job post grid
	$scope.populateClosedJobPostGrid = function(){
		$http.get($scope.getClosedJobPostUrl+"?jobStatus="+$scope.staticContentArray.statusClosed+"&page="+$scope.staticContentArray.closeJobPostPage)
		.then(function(response){
			$scope.closedJobPostGrid.data = response.data;
			$scope.closedJobPostGridSmallScreen.data = response.data;
			var gridHeight=$scope.closedJobPostGrid.data.length*rowHeight + headerHeight;
			if(response.data.length>0){
				if(gridHeight>maxGridHeight){
					document.querySelector(".closedJobPostGrid").style.height = maxGridHeight + "px";
					document.querySelector(".closedJobPostGridSmallScreen").style.height = maxGridHeight + "px";
				}
				else{
					document.querySelector(".closedJobPostGrid").style.height = gridHeight + "px";
					document.querySelector(".closedJobPostGridSmallScreen").style.height = gridHeight + "px";
				}
			}
			else{
				document.querySelector(".closedJobPostGrid").style.height = minHeight;
				document.querySelector(".closedJobPostGridSmallScreen").style.height = minHeight;
			}
		});
	};

/** ----------------------------------- METHOD TO CLOSE JOB POST -----------------------**/

	//method to close Job Post
	$scope.closeJobPost= function(row){
		$scope.closeJobPostErrorMessage=$scope.errorMessageArray.closeJobPostError;
		$scope.closeJobPostSuccessMessage=$scope.errorMessageArray.closeJobPostSuccess;
	 	$scope.closeJobPostError=false;
		$scope.closeJobPostSuccess=false;
		$scope.closeJobPostGif=true;
		$http.get($scope.closeJobPostUrl +"?id="+row.id)
		.then(function(response){
			if(response.data==1){
				$scope.closeJobPostError=false;
				$scope.closeJobPostGif=false;
				$scope.closeJobPostSuccess=true;
				$scope.populateCloseJobPostGrid();
				$scope.populateClosedJobPostGrid();
			}
			else{
				$scope.closeJobPostError=true;
				$scope.closeJobPostGif=false;
				$scope.closeJobPostSuccess=false;
			}
			$scope.hideCloseJobPostMessages();
		},function(error){
				$scope.closeJobPostError=true;
				$scope.closeJobPostGif=false;
				$scope.closeJobPostSuccess=false;
				$scope.hideCloseJobPostMessages();
		}
		);
	};
	
	
/** ----------------------------------- METHOD TO DOWNLOAD JOB DESCRIPTION DOCUMENT -----------------------**/

	$scope.downloadCloseJobDescription= function(row){
			$scope.closeJobPostError=false;
			$scope.closeJobPostSuccess=false;
			$scope.closeJobPostGif=true;
			let viewJobDescriptionUrl = $scope.viewJobDescriptionUrl + row.descriptionDocumentPath;
			$http.get(viewJobDescriptionUrl,{
				responseType: 'blob'
			})
			.then(function(response){
				if(response.data.byteLength!=0){
					let link = document.createElement('a');
					link.href = URL.createObjectURL(response.data);
					link.download = row.jobId + $scope.staticContentArray.underScore+$scope.staticContentArray.descriptionDocumentText;
					document.body.appendChild(link);
					link.click();
					$scope.closeJobPostGif=false;
				}
				else{
					$scope.closeJobPostErrorMessage=$scope.errorMessageArray.descriptionDocumentDownloadError;
					$scope.closeJobPostError=true;
					$scope.closeJobPostGif=false;
				}
				$scope.hideCloseJobPostMessages();
			},
			function(error){
					$scope.closeJobPostErrorMessage=$scope.errorMessageArray.descriptionDocumentDownloadError;
					$scope.closeJobPostError=true;
					$scope.closeJobPostGif=false;
					$scope.hideCloseJobPostMessages();
			});
	};
	
		$scope.downloadClosedJobDescription= function(row){
			$scope.closedJobPostError=false;
			$scope.closedJobPostGif=true;
			let viewJobDescriptionUrl = $scope.viewJobDescriptionUrl + row.descriptionDocumentPath;
			$http.get(viewJobDescriptionUrl,{
				responseType: 'blob'
			})
			.then(function(response){
				if(response.data.byteLength!=0){
					let link = document.createElement('a');
					link.href = URL.createObjectURL(response.data);
					link.download = row.jobId + $scope.staticContentArray.underScore+$scope.staticContentArray.descriptionDocumentText;
					document.body.appendChild(link);
					link.click();
					$scope.closedJobPostGif=false;
				}
				else{
					$scope.closedJobPostErrorMessage=$scope.errorMessageArray.descriptionDocumentDownloadError;
					$scope.closedJobPostError=true;
					$scope.closedJobPostGif=false;
				}
				$scope.hideClosedJobPostMessages();
			},
			function(error){
					$scope.closecJobPostErrorMessage=$scope.errorMessageArray.descriptionDocumentDownloadError;
					$scope.closecJobPostError=true;
					$scope.closecJobPostGif=false;
					$scope.hideClosecJobPostMessages();
			});
	};
	
/** ----------------------------------- METHOD TO HIDE ERROR MESSAGES -----------------------**/

	//method to hide close job post messages
	$scope.hideCloseJobPostMessages=function(){
		$timeout(()=>{
			$scope.closeJobPostError=false;
			$scope.closeJobPostSuccess=false;
			$scope.closeJobPostGif=false;

		},7000);
	};
	
	//method to hide closed job post messages
	$scope.hideClosedJobPostMessages=function(){
		$timeout(()=>{
			$scope.closedJobPostError=false;
			$scope.closedJobPostGif=false;
		},7000);
	};
});