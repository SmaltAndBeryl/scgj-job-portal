var deleteJobPost = angular.module("app");
deleteJobPost.controller("deleteJobPostController",function($scope,$http,$timeout){

	var errorMessageUrl ="js/controllers/errorMessages.json";
	var staticContentUrl ="js/controllers/staticContent.json";
	var rowHeight=30;
	var headerHeight=55;
	var minHeight = "90px";
	var maxGridHeight = 350;
    $scope.deleteJobPostError=false;
	$scope.deleteJobPostSuccess=false;
	$scope.deleteJobPostGif=false;
    $scope.deletedJobPostError=false;
	$scope.deletedJobPostGif=false;	
	$scope.getDeleteJobPostUrl="/getAllJobPostByEmployer";
	$scope.getDeletedJobPostUrl="/getAllJDeletedJobPostByEmployer"; 
	$scope.deleteJobPostById = "/deleteJobPost";
	$scope.viewJobDescriptionUrl="/downloadPdfFile/";

	//ui-grid for Job Post to be deleted
    $scope.deleteJobPostGrid={
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
            name : "applicationDate",
            displayName : "Application Last Date",
            width : '15%',
            cellTooltip: function(row, col) {
            	return 'Application Last Date: ' + row.entity.applicationDate;
            }, 
			headerTooltip: true
        },
        {
            name : "updatedAt", 
            displayName : "Updated On",
            width : '13%',
            cellTooltip: function(row, col) {
            	return 'Updated On: ' + row.entity.updatedAt;
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
            cellTooltip: "Download Document", 
            cellTemplate:'<div class="text-center"><img ng-hide="row.entity.descriptionDocumentPath == null" src="../images/pdfIcon.png" alt="Download Pdf Icon" class="gridDownloadJobDescriptionIcon deleteGrid" ng-click="grid.appScope.downloadDeleteJobDescription(row.entity)"><p class="notAvailableGridText" ng-show="row.entity.descriptionDocumentPath == null">N/A</p></div>'
        },
        {
            name:'Delete',
            width: '10%',
            cellTemplate:'<div class="text-center"><button class="btn standardGridButton deleteJobPostButton" ng-click="grid.appScope.deleteJobPost(row.entity)">Delete</button></div>'
        }
    ]
    };

	//ui-grid for Job Post to be deleted for Small Screen
   $scope.deleteJobPostGridSmallScreen={
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
            name : "applicationDate",
            displayName : "Application Last Date",
            width : '50%',
            cellTooltip: function(row, col) {
            	return 'Application Last Date: ' + row.entity.applicationDate;
            }, 
			headerTooltip: true
        },
        {
            name : "updatedAt", 
            displayName : "Updated On",
            width : '35%',
            cellTooltip: function(row, col) {
            	return 'Updated On: ' + row.entity.updatedAt;
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
            cellTemplate:'<div class="text-center"><img ng-hide="row.entity.descriptionDocumentPath == null" src="../images/pdfIcon.png" alt="Download Pdf Icon" class="gridDownloadJobDescriptionIcon deleteGrid" ng-click="grid.appScope.downloadDeleteJobDescription(row.entity)"><p class="notAvailableGridText" ng-show="row.entity.descriptionDocumentPath == null">N/A</p></div>'
        },
        {
            name:'Delete',
            width: '25%',
			cellToolTip : "Delete Job Post",
            cellTemplate:'<div class="text-center"><button class="btn standardGridButton deleteJobPostButton" ng-click="grid.appScope.deleteJobPost(row.entity)">Delete</button></div>'
        }
    ]
    };

		//ui-grid for deleted job post
        $scope.deletedJobPostGrid={
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
            name : "applicationDate",
            displayName : "Application Last Date",
            width : '15%',
            cellTooltip: function(row, col) {
            	return 'Application Last Date: ' + row.entity.applicationDate;
            }, 
			headerTooltip: true
        },
        {
            name : "updatedAt",
            displayName : "Deleted On",
            width : '15%',
            cellTooltip: function(row, col) {
            	return 'Deleted On: ' + row.entity.updatedAt;
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
            cellTemplate:'<div class="text-center"><img ng-hide="row.entity.descriptionDocumentPath == null" src="../images/pdfIcon.png" alt="Download Pdf Icon" class="gridDownloadJobDescriptionIcon" id="pdfIconInDeletedJobPostGrid" ng-click="grid.appScope.downloadDeletedJobDescription(row.entity)"><p class="notAvailableGridText" ng-show="row.entity.descriptionDocumentPath == null">N/A</p></div>'
        }
    ]
    };
    
		//ui-grid for deleted job post for Small Screen
        $scope.deletedJobPostGridSmallScreen={
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
            name : "applicationDate",
            displayName : "Application Last Date",
            width : '50%',
            cellTooltip: function(row, col) {
            	return 'Application Last Date: ' + row.entity.applicationDate;
            }, 
			headerTooltip: true
        },
        {
            name : "updatedAt",
            displayName : "Deleted On",
            width : '35%',
            cellTooltip: function(row, col) {
            	return 'Deleted On: ' + row.entity.updatedAt;
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
            cellTemplate:'<div class="text-center"><img ng-hide="row.entity.descriptionDocumentPath == null" src="../images/pdfIcon.png" alt="Download Pdf Icon" class="gridDownloadJobDescriptionIcon" id="pdfIconInDeletedJobPostGrid" ng-click="grid.appScope.downloadDeletedJobDescription(row.entity)"><p class="notAvailableGridText" ng-show="row.entity.descriptionDocumentPath == null">N/A</p></div>'
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
		$scope.staticContentArray= response.data[0];
		
		//calling methods to populate ui-grid
		$scope.populateDeleteJobPostGrid();
		$scope.populateDeletedJobPostGrid();
	});
	
	//function to filter out closed job post from the response object
	function filterOutClosedJobPost(element){
  		return element.jobStatus!=="Closed";
	}

/** ----------------------------------- METHOD TO POPULATE GRIDS -----------------------**/

    //Method to populate grid for job post to be deleted
    $scope.populateDeleteJobPostGrid = function(){
    $http.get($scope.getDeleteJobPostUrl)
    .then(function(response){
		let getPublishAndNotPublishedJobPostData = response.data.filter(filterOutClosedJobPost);
		if(getPublishAndNotPublishedJobPostData.length>0){
			$scope.deleteJobPostGrid.data = getPublishAndNotPublishedJobPostData;
			$scope.deleteJobPostGridSmallScreen.data = getPublishAndNotPublishedJobPostData;
			var gridHeight = $scope.deleteJobPostGrid.data.length * rowHeight + headerHeight;
			if(gridHeight>maxGridHeight){
				document.querySelector(".deleteJobPostGrid").style.height=maxGridHeight +"px";
				document.querySelector(".deleteJobPostGridSmallScreen").style.height=maxGridHeight +"px";
			}
			else{
				document.querySelector(".deleteJobPostGrid").style.height=gridHeight +"px";
				document.querySelector(".deleteJobPostGridSmallScreen").style.height=gridHeight +"px";
				}
		}
		else{
			$scope.deleteJobPostGrid.data =[];
			$scope.deleteJobPostGridSmallScreen.data = [];	
			document.querySelector(".deleteJobPostGrid").style.height=minHeight +"px";
			document.querySelector(".deleteJobPostGridSmallScreen").style.height=minHeight +"px";			
		}
    });
	};


    //Method to populate grid ford deleted job post
    $scope.populateDeletedJobPostGrid= function(){
        $http.get($scope.getDeletedJobPostUrl) 
        .then(function(response){
			if(response.data.length>0){ 
				$scope.deletedJobPostGrid.data = response.data;
				$scope.deletedJobPostGridSmallScreen.data = response.data;
				var gridHeight = $scope.deletedJobPostGrid.data.length * rowHeight + headerHeight;
				if(gridHeight>maxGridHeight){
					document.querySelector(".deletedJobPostGrid").style.height=maxGridHeight +"px";
					document.querySelector(".deletedJobPostGridSmallScreen").style.height=maxGridHeight +"px";
				}
				else{
					document.querySelector(".deletedJobPostGrid").style.height=gridHeight +"px";
					document.querySelector(".deletedJobPostGridSmallScreen").style.height=gridHeight +"px";
					}
				}
			else{
				$scope.deletedJobPostGrid.data =[];
				$scope.deletedJobPostGridSmallScreen.data = [];	
				document.querySelector(".deletedJobPostGrid").style.height=minHeight +"px";
				document.querySelector(".deletedJobPostGridSmallScreen").style.height=minHeight +"px";			
				}
    	},
	);
    };

/** ----------------------------------- METHOD TO DELETE JOB POST -----------------------**/

    //DELETE BUTTON CLICK
    $scope.deleteJobPost= function(row){
		deleteJobPostGif=true;
		$scope.deleteJobPostError=false;
		$scope.deleteJobPostSuccess=false;
		$scope.deleteJobPostSuccessMessage=$scope.errorMessageArray.deleteJobPostSuccess;
        $http.get($scope.deleteJobPostById + "?id="+row.id)
        .then(function(response){
            if(response.data==1){
				$scope.deleteJobPostError=false;
				$scope.deleteJobPostSuccess=true;
				$scope.deleteJobPostGif=false;
                $scope.populateDeletedJobPostGrid();
                $scope.populateDeleteJobPostGrid();
				$scope.hideDeleteJobPostMessages();
            }
			else if(response.data==-35){
                $scope.deleteJobPostErrorMessage=$scope.errorMessageArray.canOnlyBeClosedError;
                $scope.deleteJobPostError=true;
				$scope.deleteJobPostSuccess=false;
				$scope.deleteJobPostGif=false;		
				$scope.hideDeleteJobPostMessages();	
            }
			else{
                $scope.deleteJobPostErrorMessage=$scope.errorMessageArray.deleteJobPostError;
                $scope.deleteJobPostError=true;
                $scope.deleteJobPostSuccess=false;
                $scope.deleteJobPostGif=false;
                $scope.hideDeleteJobPostMessages();
            }
        },function(error){
				$scope.deleteJobPostError=true;
				$scope.deleteJobPostSuccess=false;
				$scope.deleteJobPostGif=false;		
				$scope.hideDeleteJobPostMessages();
        });
        
    }

/** ----------------------------------- METHOD TO DOWNLOAD JOB DESCRIPTION DOCUMENT -----------------------**/

	//Method to download description document which are to be deleted
	$scope.downloadDeleteJobDescription = function(row){
			$scope.deleteJobPostError=false;
			$scope.deleteJobPostSuccess=false;
			$scope.deleteJobPostGif=true;
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
					$scope.deleteJobPostGif=false;
				}
				else{
					$scope.deleteJobPostErrorMessage=$scope.errorMessageArray.descriptionDocumentDownloadError;
					$scope.deleteJobPostError=true;
					$scope.deleteJobPostGif=false;
				}
				$scope.hideDeleteJobPostMessages();
			},
			function(error){
					$scope.deleteJobPostErrorMessage=$scope.errorMessageArray.descriptionDocumentDownloadError;
					$scope.deleteJobPostError=true;
					$scope.deleteJobPostGif=false;
					$scope.hideDeleteJobPostMessages();
			});
	};
	
	
		//Method to download deleted job posts description document 
	$scope.downloadDeletedJobDescription = function(row){
			$scope.deletedJobPostError=false;
			$scope.deletedJobPostGif=true;
			let viewJobDescriptionUrl = $scope.viewJobDescriptionUrl + row.descriptionDocumentPath;
			$http.get(viewJobDescriptionUrl,{
				responseType: 'arraybuffer'
			})
			.then(function(response){
				if(response.data.byteLength!=0){
					var setContentType = response.headers("content-type");
					var viewDocument = new Blob([response.data], {
						type: setContentType
					});
					let link = document.createElement('a');
					link.href = URL.createObjectURL(viewDocument);
					link.download = row.jobId + $scope.staticContentArray.underScore+$scope.staticContentArray.descriptionDocumentText;
					document.body.appendChild(link);
					link.click();
					$scope.deletedJobPostGif=false;
				}
				else{
					$scope.deletedJobPostErrorMessage=$scope.errorMessageArray.descriptionDocumentDownloadError;
					$scope.deletedJobPostError=true;
					$scope.deletedJobPostGif=false;
				}
				$scope.hideDeletedJobPostMessages();
			},
			function(error){
					$scope.deletedJobPostErrorMessage=$scope.errorMessageArray.descriptionDocumentDownloadError;
					$scope.deletedJobPostError=true;
					$scope.deletedJobPostGif=false;
					$scope.hideDeletedJobPostMessages();
			});
	};

/** ----------------------------------- METHOD TO HIDE ERROR MESSAGES -----------------------**/

	//method to hide messages for delete job post content section
	$scope.hideDeleteJobPostMessages = function(){
		$timeout(()=>{
			$scope.deleteJobPostError=false;
			$scope.deleteJobPostSuccess=false;
			$scope.deleteJobPostGif=false;
		},7000);
	}
	
	//method to hide messages for deleted job post content section
	$scope.hideDeletedJobPostMessages = function(){
		$timeout(()=>{
			$scope.deletedJobPostError=false;
			$scope.deleteJobPostGif=false;
		},7000);
	}
});