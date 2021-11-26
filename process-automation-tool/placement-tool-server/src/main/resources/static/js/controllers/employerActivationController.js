angular.module("app")
.controller("employerActivationController",function($scope,$timeout,$http){
	
	$scope.deactivateEmployerGif = false;
	$scope.deactivateEmployerSuccess = false;
	$scope.deactivateEmployerError = false;
	$scope.activateEmployerGif = false;
	$scope.activateEmployerSuccess = false;
	$scope.activateEmployerError = false;
	$scope.viewDeactivateEmployerGridSection=true;
	$scope.viewActivateEmployerGridSection=true;
	$scope.isValidWebsiteFlag=true;
	
	const errorMessageUrl ="js/controllers/errorMessages.json";
	const staticContentUrl ="js/controllers/staticContent.json";
	const getEmployerByStatusUrl = "/viewEmployerByActiveStatus";
	const setEmployerActiveStatusUrl = "/updateCandidateStatus";
	const rowHeight=30;
	const headerHeight=75;
	const minHeight = "90px";
	const maxGridHeight = 350;

//--------------------------- DEACTIVATE GRID --------------------------------------//

	//deactivate employer grid
	$scope.deactivateEmployerGrid={
		enableFiltering:true,
		enableGridMenus: false,
		enableSorting: false,
		enableColumnMenus: false,
        enableHorizontalScrollbar: 1,
        enableVerticalScrollbar: 1,
        enableRowHeaderSelection : false,
		columnDefs: [
			{
				name: 'companyName',
				displayName : 'Company',
				width : '22%',
				headerTooltip: true,
				cellTooltip : function(row,col){
					return 'Company: ' + row.entity.companyName;
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
				displayName : 'Mobile Number',
				width : '15%',
				headerTooltip: true,
				cellTooltip : function(row,col){
					return 'Mobile Number: ' + row.entity.mobileNumber;
				}
			},
			{
				name: 'email',
				displayName : 'Email',
				width : '22%',
				headerTooltip: true,
				cellTooltip : function(row,col){
					return 'Email: ' + row.entity.email;
				}
			},
			{
				name: 'registeredOn',
				displayName : 'Registered On',
				width : '15%',
				headerTooltip: true,
				cellTooltip : function(row,col){
					return 'Registered On: ' + row.entity.registeredOn;
				}
			},
			{
				name: 'activationUpdatedOn',
				displayName : 'Activated On',
				width : '15%',
				headerTooltip: true,
				cellTooltip : function(row,col){
					return 'Activated On: ' + row.entity.activationUpdatedOn;
				}
			},
			{
				name: 'More Details',
				headerTooltip: true,
				width: '11%',
				cellTemplate:'<div class="text-center"><button class="btn standardGridButton deactivateButton viewButton" ng-click="grid.appScope.viewDeactivateEmployerGridDetails(row.entity)" >View</button></div>'
			},			
			{
				name : 'Deactivate',
				width : '15%',
				headerTooltip: true,
				cellTemplate:'<div title="Deactivate {{row.entity.companyName}}" class="text-center"><button class="btn standardGridButton deactivateButton" ng-click="grid.appScope.deactivateEmployer(row.entity)">Deactivate</button></div>',
				cellTooltip : function(row,col){
					return "Deactivate "+row.entity.companyName;
				}
			}
		]
	};
	
	
	
	//deactivate employer grid for Small Screen
	$scope.deactivateEmployerGridSmallScreen={
		enableFiltering:true,
		enableGridMenus: false,
		enableSorting: false,
		enableColumnMenus: false,
        enableHorizontalScrollbar: 1,
        enableVerticalScrollbar: 1,
        enableRowHeaderSelection : false,
		columnDefs: [
			{
				name: 'companyName',
				displayName : 'Company',
				width : '45%',
				headerTooltip: true,
				cellTooltip : function(row,col){
					return 'Company: ' + row.entity.companyName;
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
				displayName : 'Mobile Number',
				width : '40%',
				headerTooltip: true,
				cellTooltip : function(row,col){
					return 'Mobile Number: ' + row.entity.mobileNumber;
				}
			},
			{
				name: 'email',
				displayName : 'Email',
				width : '55%',
				headerTooltip: true,
				cellTooltip : function(row,col){
					return 'Email: ' + row.entity.email;
				}
			},
			{
				name: 'registeredOn',
				displayName : 'Registered On',
				width : '30%',
				headerTooltip: true,
				cellTooltip : function(row,col){
					return 'Registered On: ' + row.entity.registeredOn;
				}
			},
			{
				name: 'activationUpdatedOn',
				displayName : 'Activated On',
				width : '30%',
				headerTooltip: true,
				cellTooltip : function(row,col){
					return 'Activated On: ' + row.entity.activationUpdatedOn;
				}
			},
			{
				name: 'More Details',
				headerTooltip: true,
				width: '30%',
				cellTemplate:'<div class="text-center"><button class="btn standardGridButton deactivateButton viewButton" ng-click="grid.appScope.viewDeactivateEmployerGridDetails(row.entity)" >View</button></div>'
			},
			{
				name : 'Deactivate',
				width : '30%',
				headerTooltip: true,
				cellTemplate:'<div title="Deactivate {{row.entity.companyName}}" class="text-center" ><button class="btn standardGridButton deactivateButton" ng-click="grid.appScope.deactivateEmployer(row.entity)">Deactivate</button></div>',
				cellTooltip : function(row,col){
					return "Deactivate "+row.entity.companyName;
				}
				
			}
		]
	};
	
	
//--------------------------- ACTIVATE GRID --------------------------------------//

	//activate employer grid
	$scope.activateEmployerGrid={
		enableFiltering:true,
		enableGridMenus: false,
		enableSorting: false,
		enableColumnMenus: false,
        enableHorizontalScrollbar: 1,
        enableVerticalScrollbar: 1,
        enableRowHeaderSelection : false, //check //remvoe
		columnDefs: [
			{
				name: 'companyName',
				displayName : 'Company',
				width : '22%',
				headerTooltip: true,
				cellTooltip : function(row,col){
					return 'Company: ' + row.entity.companyName;
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
				displayName : 'Mobile Number',
				width : '15%',
				headerTooltip: true,
				cellTooltip : function(row,col){
					return 'Mobile Number: ' + row.entity.mobileNumber;
				}
			},
			{
				name: 'email',
				displayName : 'Email',
				width : '22%',
				headerTooltip: true,
				cellTooltip : function(row,col){
					return 'Email: ' + row.entity.email;
				}
			},
			{
				name: 'registeredOn',
				displayName : 'Registered On',
				width : '15%',
				headerTooltip: true,
				cellTooltip : function(row,col){
					return 'Registered On: ' + row.entity.registeredOn;
				}
			},
			{
				name: 'activationUpdatedOn',
				displayName : 'Deactivated On',
				width : '15%',
				headerTooltip: true,
				cellTooltip : function(row,col){
					return 'Deactivated On: ' + row.entity.activationUpdatedOn;
				}
			},
			{
				name: 'More Details',
				headerTooltip: true,
				width: '11%',
				cellTemplate:'<div class="text-center"><button class="btn standardGridButton deactivateButton viewButton" ng-click="grid.appScope.viewActivateEmployerGridDetails(row.entity)" >View</button></div>'
			},
			{
				displayName : 'Activate',
				name: 'Activate',
				width : '15%',
				headerTooltip: true,
				cellTemplate:'<div title="Activate {{row.entity.companyName}}" class="text-center"><button class="btn standardGridButton activateButton" ng-click="grid.appScope.activateEmployer(row.entity)">Activate</button></div>',
				cellTooltip : function(row,col){
					return "Activate " + row.entity.companyName;
				}
				
			}
		]
	};
	
	
	//activate employer grid for Small Screen
	$scope.activateEmployerGridSmallScreen={
		enableFiltering:true,
		enableGridMenus: false,
		enableSorting: false,
		enableColumnMenus: false,
        enableHorizontalScrollbar: 1,
        enableVerticalScrollbar: 1,
        enableRowHeaderSelection : false,
		columnDefs: [
			{
				name: 'companyName',
				displayName : 'Company',
				width : '45%',
				headerTooltip: true,
				cellTooltip : function(row,col){
					return 'Company: ' + row.entity.companyName;
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
				displayName : 'Mobile Number',
				width : '40%',
				headerTooltip: true,
				cellTooltip : function(row,col){
					return 'Mobile Number: ' + row.entity.mobileNumber;
				}
			},
			{
				name: 'email',
				displayName : 'Email',
				width : '55%',
				headerTooltip: true,
				cellTooltip : function(row,col){
					return 'Email: ' + row.entity.email;
				}
			},
			{
				name: 'registeredOn',
				displayName : 'Registered On',
				width : '30%',
				headerTooltip: true,
				cellTooltip : function(row,col){
					return 'Registered On: ' + row.entity.registeredOn;
				}
			},
			{
				name: 'activationUpdatedOn',
				displayName : 'Deactivated On',
				width : '34%',
				headerTooltip: true,
				cellTooltip : function(row,col){
					return 'Deactivated On: ' + row.entity.activationUpdatedOn;
				}
			},
			{
				name: 'More Details',
				headerTooltip: true,
				width: '30%',
				cellTemplate:'<div class="text-center"><button class="btn standardGridButton deactivateButton viewButton" ng-click="grid.appScope.viewActivateEmployerGridDetails(row.entity)" >View</button></div>'
			},
			{
				displayName : 'Activate',
				name: 'Activate',
				width : '30%',
				headerTooltip: true,
				cellTemplate:'<div title="Activate {{row.entity.companyName}}" class="text-center"><button class="btn standardGridButton activateButton"  ng-click="grid.appScope.activateEmployer(row.entity)">Activate</button></div>',
				cellTooltip : function(row,col){
					return "Activate " + row.entity.companyName;
				}
				
			}
		]
	};
	

//---------------------------FETCH ERROR MESSAGE------------------------------------------//
	$http.get(errorMessageUrl)
	.then(function(response){
		$scope.errorMessageArray = response.data[0];
	});
	
//--------------------------- FETCH STATIC CONTENT------------------------------------------//
	$http.get(staticContentUrl)
	.then(function(response){
		$scope.staticContentArray = response.data[0];
		//calling methods to populate ui-grid
		$scope.populateDeactivateGrid();
		$scope.populateActivateGrid();
		
	});

//--------------------------- METHOD TO POPULATE GRIDS------------------------------------------//
	$scope.populateDeactivateGrid = function(){
		$http.get(getEmployerByStatusUrl+"?activeStatus="+$scope.staticContentArray.trueFlag)
		.then(function(response){
			if(response.data.length>0){
				$scope.deactivateEmployerGrid.data=response.data;
				$scope.deactivateEmployerGridSmallScreen.data=response.data;	
			}	
			else{
				$scope.deactivateEmployerGrid.data=[];
				$scope.deactivateEmployerGridSmallScreen.data=[];
			}
			$scope.resizeDeactivateEmployerGrid();
		});
	};
	
	$scope.populateActivateGrid = function(){
		$http.get(getEmployerByStatusUrl+"?activeStatus="+$scope.staticContentArray.falseFlag)
		.then(function(response){
			if(response.data.length>0){
				$scope.activateEmployerGrid.data=response.data;
				$scope.activateEmployerGridSmallScreen.data=response.data;
			}
			else{
				$scope.activateEmployerGrid.data=[];
				$scope.activateEmployerGridSmallScreen.data=[];
			}
			$scope.resizeActivateEmployerGrid();
		});
	};
	
	
	
	//-------------------------------METHOD TO RESIZE DEACTIVATE EMPLOYER GRID---------------------------------
	$scope.resizeDeactivateEmployerGrid = function(){
		if($scope.deactivateEmployerGrid.data.length == 0)
		{
            document.querySelector(".deactivateEmployerGrid").style.height=minHeight;
            document.querySelector(".deactivateEmployerGridSmallScreen").style.height=minHeight;
        }
		else
	    {
			var gridHeight = $scope.deactivateEmployerGrid.data.length * rowHeight + headerHeight;
			if(gridHeight>maxGridHeight){
				document.querySelector(".deactivateEmployerGrid").style.height=maxGridHeight+"px";
				document.querySelector(".deactivateEmployerGridSmallScreen").style.height=maxGridHeight+"px";
			}
			else{
				document.querySelector(".deactivateEmployerGrid").style.height=gridHeight+"px";
				document.querySelector(".deactivateEmployerGridSmallScreen").style.height=gridHeight+"px";
			}
	    }
	};
	
	//-------------------------------METHOD TO RESIZE ACTIVATE EMPLOYER GRID---------------------------------
	$scope.resizeActivateEmployerGrid = function(){
		if($scope.activateEmployerGrid.data.length == 0)
		{
            document.querySelector(".activateEmployerGrid").style.height=minHeight;
            document.querySelector(".activateEmployerGridSmallScreen").style.height=minHeight;
        }
		else
	    {
			var gridHeight = $scope.activateEmployerGrid.data.length * rowHeight + headerHeight;
			if(gridHeight>maxGridHeight){
				document.querySelector(".activateEmployerGrid").style.height=maxGridHeight+"px";
				document.querySelector(".activateEmployerGridSmallScreen").style.height=maxGridHeight+"px";
			}
			else{
				document.querySelector(".activateEmployerGrid").style.height=gridHeight+"px";
				document.querySelector(".activateEmployerGridSmallScreen").style.height=gridHeight+"px";
			}
	    }
	};
	
	
	//--------------------------- METHOD TO UPDATE EMPLOYER STATUS------------------------------------------//
	$scope.deactivateEmployer = function(row){
		$scope.deactivateEmployerSuccessMessage=$scope.errorMessageArray.deactivateEmployerSuccess;
		$scope.deactivateEmployerErrorMessage=$scope.errorMessageArray.deactivateEmployerError;
		$scope.deactivateEmployerGif = true;
		$scope.deactivateEmployerSuccess = false;
		$scope.deactivateEmployerError = false;
		$http.get(setEmployerActiveStatusUrl+"?userId="+row.userId+"&updatedStatus="+$scope.staticContentArray.falseFlag)
		.then(function(response){
			$scope.deactivateEmployerGif = false;
			if(response.data==1){
				$scope.deactivateEmployerSuccess = true;
				$scope.deactivateEmployerError = false;
				$scope.populateDeactivateGrid();
				$scope.populateActivateGrid();
			}
			else{
				$scope.deactivateEmployerError = true;
				$scope.deactivateEmployerSuccess = false;
			}
			$scope.hideDeactivateGridMessages();
		},function(error){
				$scope.deactivateEmployerGif = false;
				$scope.deactivateEmployerError = true;
				$scope.deactivateEmployerSuccess = false;
				$scope.hideDeactivateGridMessages();
		});
	};
	
	$scope.activateEmployer = function(row){
		$scope.activateEmployerSuccessMessage=$scope.errorMessageArray.activateEmployerSuccess;
		$scope.activateEmployerErrorMessage=$scope.errorMessageArray.activateEmployerError;
		$scope.activateEmployerGif = true;
		$scope.activateEmployerSuccess = false;
		$scope.activateEmployerError = false;
		$http.get(setEmployerActiveStatusUrl+"?userId="+row.userId+"&updatedStatus="+$scope.staticContentArray.trueFlag)
		.then(function(response){
			$scope.activateEmployerGif = false;
			if(response.data==1){
				$scope.activateEmployerSuccess = true;
				$scope.activateEmployerError = false;
				$scope.populateActivateGrid();
				$scope.populateDeactivateGrid();
			}
			else{
				$scope.activateEmployerError = true;
				$scope.activateEmployerSuccess = false;
			}
			$scope.hideActivateGridMessages();
		},function(error){
				$scope.activateEmployerGif = false;
				$scope.activateEmployerSuccess = false;
				$scope.activateEmployerError = true;
				$scope.hideActivateGridMessages();
		})
	};


//------------------------METHOD TO VIEW EMPLOYER DETAILS-----------------------//
	
	//details for deactivate grid employer
	$scope.viewDeactivateEmployerGridDetails = function(row){
		$scope.viewDeactivateEmployerGridSection=false;
		$scope.deactivateGridEmployerInfo=row;
	};	
	
	//details for activate grid employer
	$scope.viewActivateEmployerGridDetails = function(row){
		$scope.viewActivateEmployerGridSection=false;
		$scope.activateGridEmployerInfo=row;
	};	
	
	
//------------------------METHOD TO HIDE ERROR MESSAGES-----------------------//
	//method to hide deactivate grid messages
	$scope.hideDeactivateGridMessages=function(){
		$timeout(()=>{
		$scope.deactivateEmployerGif = false;
		$scope.deactivateEmployerSuccess = false;
		$scope.deactivateEmployerError = false;
		},7000);
	};
	
	//method to hide activate grid messages
	$scope.hideActivateGridMessages=function(){
		$timeout(()=>{
		$scope.activateEmployerGif = false;
		$scope.activateEmployerSuccess = false;
		$scope.activateEmployerError = false;
		},7000);
	};
	
});
