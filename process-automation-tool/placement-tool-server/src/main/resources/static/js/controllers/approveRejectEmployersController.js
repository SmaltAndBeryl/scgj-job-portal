var manageEmployer = angular.module("app");
manageEmployer.controller("approveRejectEmployerController",function($scope, $http, $timeout,$window){
	
	$scope.viewInReviewEmployerGridSection=true;
	$scope.viewApprovedEmployerGridSection=true;
	$scope.viewRejectedEmployerGridSection=true;
	$scope.isValidWebsiteFlag=true;
	
	var errorMessageUrl ="js/controllers/errorMessages.json";
	var staticContentUrl ="js/controllers/staticContent.json";
	var rowHeight=30;
	var headerHeight=55;
	var minHeight = 90+"px";
	var maxGridHeight = 350;

	
	// ui-grid to view new employers
	$scope.viewInReviewEmployerGrid = {
			enableGridMenus: false,
			enableSorting: false,
			enableFiltering: true,
			enableCellEdit: false,
			enableColumnMenus: false,
			enableHorizontalScrollbar: 1,
			enableVerticalScrollbar: 1,
			columnDefs: [{
                name: 'companyName',
                displayName: "Company Name",
                width: '25%',
                cellTooltip: function(row, col) {
                    return 'Company Name: ' + row.entity.companyName;
                }
            },
            {
                name: 'liaisingAuthority',
                displayName: "Liaising Authority",
                width: '20%',
                cellTooltip: function(row, col) {
                    return 'Liaising Authority: ' + row.entity.liaisingAuthority;
                }
            },
			{
				name: 'designation',
				displayName: "Designation",
				width: '15%',
				cellTooltip: function(row, col) {
					return 'Designation: ' + row.entity.designation;
				}
			},
            {
                name: 'mobileNumber',
                displayName: 'Mobile Number',
                width: '15%',
                cellTooltip: function(row, col) {
                    return 'Mobile Number: ' + row.entity.mobileNumber;
                }
            },
            {
                name: 'email',
                displayName: 'Email',
                width: '22%',
                cellTooltip: function(row, col) {
                    return 'Email: ' + row.entity.email;
                }
            },
            {
                name: 'registeredOn',
                displayName: 'Registered On',
                width: '13%',
                cellTooltip: function(row, col) {
                    return 'Registered On: ' + row.entity.registeredOn;
                }
            },
			{
				name: 'More Details',
				headerTooltip: true,
				width: '11%',
				cellTemplate:'<div class="text-center"><button class="btn standardGridButton deactivateButton viewButton" ng-click="grid.appScope.viewInReviewEmployerGridDetails(row.entity)" >View</button></div>'
			},
	        {
	        	name: 'approve',
	        	displayName: 'Approve',
	        	width: '12%',
	        	cellTemplate:'<div class="text-center"><button class="btn standardGridButton gridActionButton approveEmployerButton" ng-click="grid.appScope.approveEmployer(row.entity,\'true\')">Approve</button></div>'
	        },
	        {
	        	name: 'reject',
	        	displayName: 'Reject',
	        	width: '12%',
	        	cellTemplate:'<div class="text-center"><button class="btn standardGridButton gridActionButton rejectEmployerButton" ng-click="grid.appScope.rejectEmployer(row.entity,\'true\')">Reject</button></div>'
	        }
	    ]};

	// ui-grid to view new employers for small screen
	$scope.viewInReviewEmployerGridSmallScreen = {
			enableGridMenus: false,
			enableSorting: false,
			enableFiltering: true,
			enableCellEdit: false,
			enableColumnMenus: false,
			enableHorizontalScrollbar: 1,
			enableVerticalScrollbar: 1,
			columnDefs: [{
                name: 'companyName',
                displayName: "Company Name",
                width: '70%',
                cellTooltip: function(row, col) {
                    return 'Company Name: ' + row.entity.companyName;
                }
            },
            {
                name: 'liaisingAuthority',
                displayName: "Liaising Authority",
                width: '50%',
                cellTooltip: function(row, col) {
                    return 'Liaising Authority: ' + row.entity.liaisingAuthority;
                }
            },
			{
				name: 'designation',
				displayName: "Designation",
				width: '40%',
				cellTooltip: function(row, col) {
					return 'Designation: ' + row.entity.designation;
				}
			},
            {
                name: 'mobileNumber',
                displayName: 'Mobile Number',
                width: '35%',
                cellTooltip: function(row, col) {
                    return 'Mobile Number: ' + row.entity.mobileNumber;
                }
            },
            {
                name: 'email',
                displayName: 'Email',
                width: '60%',
                cellTooltip: function(row, col) {
                    return 'Email: ' + row.entity.email;
                }
            },
            {
                name: 'registeredOn',
                displayName: 'Registered On',
                width: '30%',
                cellTooltip: function(row, col) {
                    return 'Registered On: ' + row.entity.registeredOn;
                }
            },
			{
				name: 'More Details',
				headerTooltip: true,
				width: '30%',
				cellTemplate:'<div class="text-center"><button class="btn standardGridButton deactivateButton viewButton" ng-click="grid.appScope.viewInReviewEmployerGridDetails(row.entity)" >View</button></div>'
			},
	        {
	        	name: 'approve',
	        	displayName: 'Approve',
	        	width: '25%',
	        	cellTemplate:'<div class="text-center"><button class="btn standardGridButton gridActionButton approveEmployerButton" ng-click="grid.appScope.approveEmployer(row.entity,\'true\')">Approve</button></div>'
	        },
	        {
	        	name: 'reject',
	        	displayName: 'Reject',
	        	width: '25%',
	        	cellTemplate:'<div class="text-center"><button class="btn standardGridButton gridActionButton rejectEmployerButton" ng-click="grid.appScope.rejectEmployer(row.entity,\'true\')">Reject</button></div>'
	        }
	    ]};


	// ui-grid to view approved employers
	$scope.approvedEmployersGrid = {
			enableGridMenus: false,
			enableSorting: false,
			enableFiltering: true,
			enableCellEdit: false,
			enableColumnMenus: false,
			enableHorizontalScrollbar: 1,
			enableVerticalScrollbar: 1,
			columnDefs: [{
                name: 'companyName',
                displayName: "Company Name",
                width: '25%',
                cellTooltip: function(row, col) {
                    return 'Company Name: ' + row.entity.companyName;
                }
            },
            {
                name: 'liaisingAuthority',
                displayName: "Liaising Authority",
                width: '20%',
                cellTooltip: function(row, col) {
                    return 'Liaising Authority: ' + row.entity.liaisingAuthority;
                }
            },
			{
				name: 'designation',
				displayName: "Designation",
				width: '15%',
				cellTooltip: function(row, col) {
					return 'Designation: ' + row.entity.designation;
				}
			},
            {
                name: 'mobileNumber',
                displayName: 'Mobile Number',
                width: '15%',
                cellTooltip: function(row, col) {
                    return 'Mobile Number: ' + row.entity.mobileNumber;
                }
            },
            {
                name: 'email',
                displayName: 'Email',
                width: '22%',
                cellTooltip: function(row, col) {
                    return 'Email: ' + row.entity.email;
                }
            },
            {
                name: 'enrollmentUpdatedOn',
                displayName: 'Approved On',
                width: '13%',
                cellTooltip: function(row, col) {
                    return 'Approved On: ' + row.entity.enrollmentUpdatedOn;
                }
            },
			{
				name: 'More Details',
				headerTooltip: true,
				width: '11%',
				cellTemplate:'<div class="text-center"><button class="btn standardGridButton deactivateButton viewButton" ng-click="grid.appScope.viewApprovedEmployerGridDetails(row.entity)" >View</button></div>'
			},
	        {
	        	name: 'reject',
	        	displayName: 'Reject',
	        	width: '12%',
	        	cellTemplate:'<div class="text-center"><button class="btn standardGridButton gridActionButton rejectEmployerButton" ng-click="grid.appScope.rejectEmployer(row.entity)">Reject</button></div>'
	        }
	    ]};

	// ui-grid to view approved employers
	$scope.approvedEmployersGridSmallScreen = {
			enableGridMenus: false,
			enableSorting: false,
			enableFiltering: true,
			enableCellEdit: false,
			enableColumnMenus: false,
			enableHorizontalScrollbar: 1,
			enableVerticalScrollbar: 1,
			columnDefs: [{
                name: 'companyName',
                displayName: "Company Name",
                width: '70%',
                cellTooltip: function(row, col) {
                    return 'Company Name: ' + row.entity.companyName;
                }
            },
            {
                name: 'liaisingAuthority',
                displayName: "Liaising Authority",
                width: '50%',
                cellTooltip: function(row, col) {
                    return 'Liaising Authority: ' + row.entity.liaisingAuthority;
                }
            },
			{
				name: 'designation',
				displayName: "Designation",
				width: '40%',
				cellTooltip: function(row, col) {
					return 'Designation: ' + row.entity.designation;
				}
			},
            {
                name: 'mobileNumber',
                displayName: 'Mobile Number',
                width: '35%',
                cellTooltip: function(row, col) {
                    return 'Mobile Number: ' + row.entity.mobileNumber;
                }
            },
            {
                name: 'email',
                displayName: 'Email',
                width: '60%',
                cellTooltip: function(row, col) {
                    return 'Email: ' + row.entity.email;
                }
            },
			{
				name: 'More Details',
				headerTooltip: true,
				width: '30%',
				cellTemplate:'<div class="text-center"><button class="btn standardGridButton deactivateButton viewButton" ng-click="grid.appScope.viewApprovedEmployerGridDetails(row.entity)" >View</button></div>'
			},
            {
                name: 'enrollmentUpdatedOn',
                displayName: 'Approved On',
                width: '30%',
                cellTooltip: function(row, col) {
                    return 'Approved On: ' + row.entity.enrollmentUpdatedOn;
                }
            },
	        {
	        	name: 'reject',
	        	displayName: 'Reject',
	        	width: '25%',
	        	cellTemplate:'<div class="text-center"><button class="btn standardGridButton gridActionButton rejectEmployerButton" ng-click="grid.appScope.rejectEmployer(row.entity)">Reject</button></div>'
	        }
	    ]};



	// ui-grid to view rejected employers
	$scope.rejectedEmployersGrid = {
			enableGridMenus: false,
			enableSorting: false,
			enableFiltering: true,
			enableCellEdit: false,
			enableColumnMenus: false,
			enableHorizontalScrollbar: 1,
			enableVerticalScrollbar: 1,
			columnDefs: [{
                name: 'companyName',
                displayName: "Company Name",
                width: '25%',
                cellTooltip: function(row, col) {
                    return 'Company Name: ' + row.entity.companyName;
                }
            },
            {
                name: 'liaisingAuthority',
                displayName: "Liaising Authority",
                width: '20%',
                cellTooltip: function(row, col) {
                    return 'Liaising Authority: ' + row.entity.liaisingAuthority;
                }
            },
			{
				name: 'designation',
				displayName: "Designation",
				width: '15%',
				cellTooltip: function(row, col) {
					return 'Designation : ' + row.entity.designation;
				}
			},
            {
                name: 'mobileNumber',
                displayName: 'Mobile Number',
                width: '15%',
                cellTooltip: function(row, col) {
                    return 'Mobile Number: ' + row.entity.mobileNumber;
                }
            },
            {
                name: 'email',
                displayName: 'Email',
                width: '22%',
                cellTooltip: function(row, col) {
                    return 'Email: ' + row.entity.email;
                }
            },
            {
                name: 'enrollmentUpdatedOn',
                displayName: 'Rejected On',
                width: '13%',
                cellTooltip: function(row, col) {
                    return 'Rejected On: ' + row.entity.enrollmentUpdatedOn;
                }
            },
			{
				name: 'More Details',
				headerTooltip: true,
				width: '11%',
				cellTemplate:'<div class="text-center"><button class="btn standardGridButton deactivateButton viewButton" ng-click="grid.appScope.viewRejectedEmployerGridDetails(row.entity)" >View</button></div>'
			},
	        {
	        	name: 'approve',
	        	displayName: 'Approve',
	        	width: '12%',
	        	cellTemplate:'<div class="text-center"><button class="btn standardGridButton gridActionButton approveEmployerButton" ng-click="grid.appScope.approveEmployer(row.entity)">Approve</button></div>'
	        }
	    ]};



	// ui-grid to view rejected employers for small screen
	$scope.rejectedEmployersGridSmallScreen = {
			enableGridMenus: false,
			enableSorting: false,
			enableFiltering: true,
			enableCellEdit: false,
			enableColumnMenus: false,
			enableHorizontalScrollbar: 1,
			enableVerticalScrollbar: 1,
			columnDefs: [{
                name: 'companyName',
                displayName: "Company Name",
                width: '70%',
                cellTooltip: function(row, col) {
                    return 'Company Name: ' + row.entity.companyName;
                }
            },
            {
                name: 'liaisingAuthority',
                displayName: "Liaising Authority",
                width: '50%',
                cellTooltip: function(row, col) {
                    return 'Liaising Authority: ' + row.entity.liaisingAuthority;
                }
            },
			{
				name: 'designation',
				displayName: "Designation",
				width: '40%',
				cellTooltip: function(row, col) {
					return 'Designation: ' + row.entity.designation;
				}
			},
            {
                name: 'companyScale',
                displayName: 'Company Scale',
                width: '38%',
                cellTooltip: function(row, col) {
                    return 'Company Scale: ' + row.entity.companyScale;
                }
            },
            {
                name: 'mobileNumber',
                displayName: 'Mobile Number',
                width: '35%',
                cellTooltip: function(row, col) {
                    return 'Mobile Number: ' + row.entity.mobileNumber;
                }
            },
            {
                name: 'email',
                displayName: 'Email',
                width: '60%',
                cellTooltip: function(row, col) {
                    return 'Email: ' + row.entity.email;
                }
            },
            {
                name: 'enrollmentUpdatedOn',
                displayName: 'Rejected On',
                width: '30%',
                cellTooltip: function(row, col) {
                    return 'Rejected On: ' + row.entity.enrollmentUpdatedOn;
                }
            },
			{
				name: 'More Details',
				headerTooltip: true,
				width: '30%',
				cellTemplate:'<div class="text-center"><button class="btn standardGridButton deactivateButton viewButton" ng-click="grid.appScope.viewRejectedEmployerGridDetails(row.entity)" >View</button></div>'
			},
	        {
	        	name: 'approve',
	        	displayName: 'Approve',
	        	width: '25%',
	        	cellTemplate:'<div class="text-center"><button class="btn standardGridButton gridActionButton approveEmployerButton" ng-click="grid.appScope.approveEmployer(row.entity)">Approve</button></div>'
	        }
	    ]};
	
	
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
			$scope.getNewEmployersUrl="/viewEmployers?approvalStatus="+$scope.staticContentArray.inReviewFlag;
			$scope.getApprovedEmployersUrl="/viewEmployers?approvalStatus="+$scope.staticContentArray.approvedFlag;
			$scope.getRejectedEmployersUrl="/viewEmployers?approvalStatus="+$scope.staticContentArray.rejectedFlag;
			$scope.populateNewEmployers();
			$scope.populateApprovedEmployers();
			$scope.populateRejectedEmployers();
			
		});
	
	
	/**------------------------------------------METHODS TO POPULATE UI-GRID------------------------------------------------------------------------------**/
	
	
	//Method to populate newly registered employers 
	$scope.populateNewEmployers = function(){
		$http.get($scope.getNewEmployersUrl)
		.then(function(response){
			if(response.data.length>0){
				$scope.viewInReviewEmployerGrid.data= response.data;
				$scope.viewInReviewEmployerGridSmallScreen.data= response.data;
				if(screen.width>1200){
					headerHeight=70;
				}
				var gridHeight = $scope.viewInReviewEmployerGrid.data.length * rowHeight + headerHeight;
				if(gridHeight>maxGridHeight){
					document.getElementById("viewEmployersGrid").style.height=maxGridHeight+"px";
					document.getElementById("viewEmployersGridSmallScreen").style.height=maxGridHeight+"px";
				}
				else{
					document.getElementById("viewEmployersGrid").style.height=gridHeight+"px";
					document.getElementById("viewEmployersGridSmallScreen").style.height=gridHeight+"px";
				}
			}
			else{
				$scope.viewInReviewEmployerGrid.data= [];
				$scope.viewInReviewEmployerGridSmallScreen.data= [];
				document.getElementById("viewEmployersGrid").style.height=minHeight;
				document.getElementById("viewEmployersGridSmallScreen").style.height=minHeight;
			}
		});
	}
	
	
	//Method to populate approved registered employers 
	$scope.populateApprovedEmployers = function(){
		$http.get($scope.getApprovedEmployersUrl)
		.then(function(response){
			if(response.data.length>0){
				$scope.approvedEmployersGrid.data= response.data;
				$scope.approvedEmployersGridSmallScreen.data= response.data;
				if(screen.width>1200){
					headerHeight=70;
				}
				var gridHeight = $scope.approvedEmployersGrid.data.length * rowHeight + headerHeight;
				if(gridHeight>maxGridHeight){
					document.getElementById("approvedEmployersGrid").style.height=maxGridHeight+"px";
					document.getElementById("approvedEmployersGridSmallScreen").style.height=maxGridHeight+"px";
				}
				else{
					document.getElementById("approvedEmployersGrid").style.height=gridHeight+"px";
					document.getElementById("approvedEmployersGridSmallScreen").style.height=gridHeight+"px";
				}
			}
			else{
				$scope.approvedEmployersGrid.data= [];
				$scope.approvedEmployersGridSmallScreen.data= [];
				document.getElementById("approvedEmployersGrid").style.height=minHeight;
				document.getElementById("approvedEmployersGridSmallScreen").style.height=minHeight;
			}
		});
	}
	
	
	//Method to populate rejected employer details
	$scope.populateRejectedEmployers = function(){
		$http.get($scope.getRejectedEmployersUrl)
		.then(function(response){
			if(response.data.length>0){
				$scope.rejectedEmployersGrid.data= response.data;
				$scope.rejectedEmployersGridSmallScreen.data= response.data;
				if(screen.width>1200){
					headerHeight=70;
				}
				var gridHeight = $scope.rejectedEmployersGrid.data.length * rowHeight + headerHeight;
				if(gridHeight>maxGridHeight){
					document.getElementById("rejectedEmployersGrid").style.height=maxGridHeight+"px";
					document.getElementById("rejectedEmployersGridSmallScreen").style.height=maxGridHeight+"px";
				}
				else{
					document.getElementById("rejectedEmployersGrid").style.height=gridHeight+"px";
					document.getElementById("rejectedEmployersGridSmallScreen").style.height=gridHeight+"px";
				}
			}
			else{
				$scope.rejectedEmployersGrid.data= [];
				$scope.rejectedEmployersGridSmallScreen.data= [];
				document.getElementById("rejectedEmployersGrid").style.height=minHeight;
				document.getElementById("rejectedEmployersGridSmallScreen").style.height=minHeight;
			}
		});
	}
	
	
	//Method to approve employer
	$scope.approveEmployer = function(row, newEmployer){
		
		let approveEmployerUrl="updateEmployerStatus?approvalStatus="+$scope.staticContentArray.approvedFlag+"&employerId="+row.userId;
		if(newEmployer)
		{
			$scope.viewEmployerError=false;
			$scope.viewEmployerSuccess=false;
			$scope.viewEmployerGif=true;
			
			$http.get(approveEmployerUrl)
			.then(function(response){
				if(response.data ==1){
					$scope.viewEmployerGif=false;
					$scope.viewEmployerSuccess=true;
					$scope.viewEmployerSuccessMessage=$scope.errorMessageArray.updateEmployerStatusSuccess;
					$scope.populateNewEmployers();
					$scope.populateApprovedEmployers();
					$scope.populateRejectedEmployers();
					$scope.hideInReviewEmployerMessages();
				}
				else if(response.data == -66){
					$scope.viewEmployerGif=false;
					$scope.viewEmployerError=true;
					$scope.viewEmployerErrorMessage=$scope.errorMessageArray.updateEmployerStatusInvalidStatusError;
					$scope.hideInReviewEmployerMessages();
				}
				else{
					$scope.viewEmployerGif=false;
					$scope.viewEmployerError=true;
					$scope.viewEmployerErrorMessage=$scope.errorMessageArray.updateEmployerStatusError;
					$scope.hideInReviewEmployerMessages();
				}
			},function(error){
				$scope.viewEmployerGif=false;
				$scope.viewEmployerError=true;
				$scope.viewEmployerErrorMessage=$scope.errorMessageArray.updateEmployerStatusError;
				$scope.hideInReviewEmployerMessages();
			});
		}
		else
		{
			$scope.rejectedEmployerError=false;
			$scope.rejectedEmployerSuccess=false;
			$scope.rejectedEmployerGif=true;
			
			$http.get(approveEmployerUrl)
			.then(function(response){
				if(response.data ==1){
					$scope.rejectedEmployerGif=false;
					$scope.rejectedEmployerSuccess=true;
					$scope.rejectedEmployerSuccessMessage=$scope.errorMessageArray.updateEmployerStatusSuccess;
					$scope.populateApprovedEmployers();
					$scope.populateRejectedEmployers();
					$scope.hideRejectedEmployersMessages();
				}
				else if(response.data == -66){
					$scope.rejectedEmployerGif=false;
					$scope.rejectedEmployerError=true;
					$scope.rejectedEmployerErrorMessage=$scope.errorMessageArray.updateEmployerStatusInvalidStatusError;
					$scope.hideRejectedEmployersMessages();
				}
				else{
					$scope.rejectedEmployerGif=false;
					$scope.rejectedEmployerError=true;
					$scope.rejectedEmployerErrorMessage=$scope.errorMessageArray.updateEmployerStatusError;
					$scope.hideRejectedEmployersMessages();
				}
			},function(error){
				$scope.rejectedEmployerGif=false;
				$scope.rejectedEmployerError=true;
				$scope.rejectedEmployerErrorMessage=$scope.errorMessageArray.updateEmployerStatusError;
				$scope.hideRejectedEmployersMessages();
			});
		}
	}
	
	
	
	//Method to reject employer
	$scope.rejectEmployer = function(row, newEmployer){
		
		let rejectEmployerUrl="updateEmployerStatus?approvalStatus="+$scope.staticContentArray.rejectedFlag+"&employerId="+row.userId;
		if(newEmployer)
		{
			$scope.viewEmployerError=false;
			$scope.viewEmployerSuccess=false;
			$scope.viewEmployerGif=true;
			
			$http.get(rejectEmployerUrl)
			.then(function(response){
				if(response.data ==1){
					$scope.viewEmployerGif=false;
					$scope.viewEmployerSuccess=true;
					$scope.viewEmployerSuccessMessage=$scope.errorMessageArray.updateEmployerStatusSuccess;
					$scope.populateNewEmployers();
					$scope.populateApprovedEmployers();
					$scope.populateRejectedEmployers();
					$scope.hideInReviewEmployerMessages();
				}
				else if(response.data == -66){
					$scope.viewEmployerGif=false;
					$scope.viewEmployerError=true;
					$scope.viewEmployerErrorMessage=$scope.errorMessageArray.updateEmployerStatusInvalidStatusError;
					$scope.hideInReviewEmployerMessages();
				}
				else{
					$scope.viewEmployerGif=false;
					$scope.viewEmployerError=true;
					$scope.viewEmployerErrorMessage=$scope.errorMessageArray.updateEmployerStatusError;
					$scope.hideInReviewEmployerMessages();
				}
			},function(error){
				$scope.viewEmployerGif=false;
				$scope.viewEmployerError=true;
				$scope.viewEmployerErrorMessage=$scope.errorMessageArray.updateEmployerStatusError;
				$scope.hideInReviewEmployerMessages();
			});
		}
		else
		{
			$scope.approvedEmployerError=false;
			$scope.approvedEmployerSuccess=false;
			$scope.approvedEmployerGif=true;
			
			$http.get(rejectEmployerUrl)
			.then(function(response){
				if(response.data ==1){
					$scope.approvedEmployerGif=false;
					$scope.approvedEmployerSuccess=true;
					$scope.approvedEmployerSuccessMessage=$scope.errorMessageArray.updateEmployerStatusSuccess;
					$scope.populateApprovedEmployers();
					$scope.populateRejectedEmployers();
					$scope.hideApprovedEmployersMessages();
				}
				else if(response.data == -66){
					$scope.approvedEmployerGif=false;
					$scope.approvedEmployerError=true;
					$scope.approvedEmployerErrorMessage=$scope.errorMessageArray.updateEmployerStatusInvalidStatusError;
					$scope.hideApprovedEmployersMessages();
				}
				else{
					$scope.approvedEmployerGif=false;
					$scope.approvedEmployerError=true;
					$scope.approvedEmployerErrorMessage=$scope.errorMessageArray.updateEmployerStatusError;
					$scope.hideApprovedEmployersMessages();
				}
			},function(error){
				$scope.approvedEmployerGif=false;
				$scope.approvedEmployerError=true;
				$scope.approvedEmployerErrorMessage=$scope.errorMessageArray.updateEmployerStatusError;
				$scope.hideApprovedEmployersMessages();
			});
		}
	}
	
	
	
	
	//Method to hide view newly registered employer section error message
	$scope.hideInReviewEmployerMessages = function(){
		$timeout(function(){
			$scope.viewEmployerError=false;
			$scope.viewEmployerSuccess=false;
		},7000);
	}



	//details for in review employer grid
	$scope.viewInReviewEmployerGridDetails = function(row){
		$scope.viewInReviewEmployerGridSection=false;
		$scope.inReviewGridEmployerInfo=row;
	};	
	
	//details for in approved employer grid
	$scope.viewApprovedEmployerGridDetails = function(row){
		$scope.viewApprovedEmployerGridSection=false;
		$scope.approvedGridEmployerInfo=row;
	};	
	
	//details for rejected employer grid
	$scope.viewRejectedEmployerGridDetails = function(row){
		$scope.viewRejectedEmployerGridSection=false;
		$scope.rejectedGridEmployerInfo=row;
	};	
	
	
		
	//Method to hide view approved employer section error message
	$scope.hideApprovedEmployersMessages = function(){
		$timeout(function(){
			$scope.approvedEmployerError=false;
			$scope.approvedEmployerSuccess=false;
		},7000);
	}
	
	//Method to hide view rejected employer section error message
	$scope.hideRejectedEmployersMessages = function(){
		$timeout(function(){
			$scope.rejectedEmployerError=false;
			$scope.rejectedEmployerSuccess=false;
		},7000);
	}
	
});