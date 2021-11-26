var resetMobileNumber = angular.module("app");
resetMobileNumber.controller("resetMobileNumberController",function($scope, $http, $timeout,$window){
	
	var errorMessageUrl ="js/controllers/errorMessages.json";
	var staticContentUrl ="js/controllers/staticContent.json";
	
	$scope.showUserDetails=false;
	$scope.error=false;
	$scope.success=false;
	$scope.loadingGif=false;
		
	// ui-grid to view user details
	$scope.viewUserDetailsGrid = {
			enableGridMenus: false,
			enableSorting: false,
			enableFiltering: false,
			enableCellEdit: false,
			enableColumnMenus: false,
			enableHorizontalScrollbar: 0,
			enableVerticalScrollbar: 1,
			columnDefs: [{
                name: 'name',
                displayName: "Name",
                width: '23%',
                cellTooltip: function(row, col) {
                    return 'Name: ' + row.entity.name;
                }
            },
            {
                name: 'userRole',
                displayName: 'User Role',
                width: '12%',
                cellTooltip: function(row, col) {
                    return 'User Role: ' + row.entity.userRole;
                }
            },
            {
                name: 'activeStatus',
                displayName: 'Status',
                width: '9%',
                cellTooltip: function(row, col) {
                    return 'Status: ' + row.entity.activeStatus;
                }
            },
            {
                name: 'state',
                displayName: 'State',
                width: '20%',
                cellTooltip: function(row, col) {
                    return 'State: ' + row.entity.state;
                }
            },
            {
                name: 'pincode',
                displayName: 'Pincode',
                width: '10%',
                cellTooltip: function(row, col) {
                    return 'Pincode: ' + row.entity.pincode;
                }
            },
            {
                name: 'mobileNumber',
                displayName: 'Mobile Number',
                width: '15%',
                cellTemplate:'<div class="text-center resetMobileGridInputDiv"><span class="gridMobileNumberPrefix">91</span><input type="number" min="0" oninput="this.value=parseInt(this.value)" value="{{row.entity.mobileNumber}}" ng-model="grid.appScope.newMobileNumber"></div>'
            },
	        {
	        	name: 'update',
	        	displayName: 'Update',
	        	width: '12%',
	        	cellTemplate:'<div class="text-center"><button class="btn standardGridButton updateMobileNumberButton" ng-click="grid.appScope.updateMobileNumber(row.entity)">Update</button></div>'
	        }
	    ]};
	
	// ui-grid to view user details for small screen
	$scope.viewUserDetailsGridSmallScreen = {
			enableGridMenus: false,
			enableSorting: false,
			enableFiltering: false,
			enableCellEdit: false,
			enableColumnMenus: false,
			enableHorizontalScrollbar: 1,
			enableVerticalScrollbar: 1,
			columnDefs: [{
                name: 'name',
                displayName: "Name",
                width: '70%',
                cellTooltip: function(row, col) {
                    return 'Name: ' + row.entity.name;
                }
            },
            {
                name: 'userRole',
                displayName: 'User Role',
                width: '35%',
                cellTooltip: function(row, col) {
                    return 'User Role: ' + row.entity.userRole;
                }
            },
            {
                name: 'activeStatus',
                displayName: 'Status',
                width: '30%',
                cellTooltip: function(row, col) {
                    return 'Status: ' + row.entity.activeStatus;
                }
            },
            {
                name: 'state',
                displayName: 'State',
                width: '60%',
                cellTooltip: function(row, col) {
                    return 'State: ' + row.entity.state;
                }
            },
            
            {
                name: 'pincode',
                displayName: 'Pincode',
                width: '30%',
                cellTooltip: function(row, col) {
                    return 'Pincode: ' + row.entity.pincode;
                }
            },
            {
                name: 'mobileNumber',
                displayName: 'Mobile Number',
                width: '45%',
                cellTemplate:'<div class="text-center resetMobileGridInputDiv"><span class="gridMobileNumberPrefix">91</span><input type="number" min="0" oninput="this.value=parseInt(this.value)" value="{{row.entity.mobileNumber}}" ng-model="grid.appScope.newMobileNumber"></div>'
            },
	        {
	        	name: 'update',
	        	displayName: 'Update',
	        	width: '25%',
	        	cellTemplate:'<div class="text-center"><button class="btn standardGridButton updateMobileNumberButton" ng-click="grid.appScope.updateMobileNumber(row.entity)">Update</button></div>'
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
		});
	
	
	/** ------------------------ Method to check valid mobile number and search user details on search button click ------------------------**/
	$scope.populateUserDetails = function(){
		
		$scope.error=false;
		$scope.success=false;		
		if($scope.oldMobileNumber.toString().length !== 10)
		{
			$scope.showUserDetails=false;
			$scope.errorMessage=$scope.errorMessageArray.resetMobileNumberLengthError;
			$scope.error=true;
			$scope.hideMessages();
		}
		else
		{
			$scope.populateUserDetailsGrid($scope.oldMobileNumber);
		}
	}
	
	/** ------------------------ Method to populate user details on search button click ------------------------**/
	$scope.populateUserDetailsGrid = function(mobileNumber){
		$scope.userDetailsArray=[];
		let fetchUserDetailsUrl="/getUserDetailsByMobileNumber?mobileNumber="+$scope.staticContentArray.mobileNumberPrefix+mobileNumber;
		$http.get(fetchUserDetailsUrl)
		.then(function(response){	
			
			$scope.userDetailsArray.push(response.data);				
			if(response.data != "" && response.data != null && response.data != undefined)
			{
				
				$scope.newMobileNumber=parseInt($scope.userDetailsArray[0].mobileNumber.toString().slice(2));
				
				if($scope.userDetailsArray[0].activeStatus == $scope.staticContentArray.trueFlag){
					$scope.userDetailsArray[0].activeStatus = $scope.staticContentArray.statusActive;
				}
				else{
					$scope.userDetailsArray[0].activeStatus = $scope.staticContentArray.statusInactive;
				}
				
				$scope.viewUserDetailsGrid.data= $scope.userDetailsArray;
				$scope.viewUserDetailsGridSmallScreen.data= $scope.userDetailsArray;
				$scope.showUserDetails=true;
			}
			else
			{
				$scope.viewUserDetailsGrid.data= [];
				$scope.viewUserDetailsGridSmallScreen.data= [];
				$scope.showUserDetails=false;
				$scope.errorMessage=$scope.errorMessageArray.mobileNumberDoesnotExistError;
				$scope.error=true;
				$scope.hideMessages();
			}
		});
	}
	
	
	/** ------------------------ Method to update user mobile number ------------------------**/
	$scope.updateMobileNumber=function(row){
		$scope.error=false;
		$scope.success=false;
		$scope.loadingGif=true;
		
		if($scope.newMobileNumber == null || $scope.newMobileNumber == "" || $scope.newMobileNumber== undefined ){
			$scope.loadingGif=false;
			$scope.errorMessage=$scope.errorMessageArray.invalidUpdatedMobileNumberError;
			$scope.error=true;
			$scope.hideMessages();
		}
		else if($scope.newMobileNumber.toString().length !== 10){
			$scope.loadingGif=false;
			$scope.errorMessage=$scope.errorMessageArray.resetMobileNumberLengthError;
			$scope.error=true;
			$scope.hideMessages();
		}
		else
		{
			let updateMobileNumberUrl = "/resetMobileNumber?oldMobileNumber="+row.mobileNumber+"&newMobileNumber="+$scope.staticContentArray.mobileNumberPrefix+$scope.newMobileNumber;
			$http.get(updateMobileNumberUrl)
			.then(function(response){	
				if(response.data == 1){
					$scope.loadingGif=false;
					$scope.successMessage=$scope.errorMessageArray.updateMobileNumberSuccess;
					$scope.success=true;
					$scope.hideMessages();
					$scope.populateUserDetailsGrid($scope.newMobileNumber);
				}
				else if(response.data == -55 ){
					$scope.errorMessage=$scope.errorMessageArray.oldMobileNumberDoesnotExistError;
					$scope.error=true;
					$scope.loadingGif=false;
					$scope.hideMessages();
				}
				else if(response.data == -88 ){
					$scope.errorMessage=$scope.errorMessageArray.updatedMobileNumberAlreadyExistsError;
					$scope.error=true;
					$scope.loadingGif=false;
					$scope.hideMessages();
				}
				else if(response.data == 0){
					$scope.errorMessage=$scope.errorMessageArray.adminMobileNumberUpdateError;
					$scope.error=true;
					$scope.loadingGif=false;
					$scope.hideMessages();
				}
				else{
					$scope.errorMessage=$scope.errorMessageArray.updateMobileNumberError;
					$scope.error=true;
					$scope.loadingGif=false;
					$scope.hideMessages();
				}
			},function(error){
				$scope.errorMessage=$scope.errorMessageArray.updateMobileNumberError;
				$scope.error=true;
				$scope.loadingGif=false;
				$scope.hideMessages();
			});
		}
	}
	
	
	
	//Method to hide error success message
	$scope.hideMessages = function(){
		$timeout(function(){
			$scope.error=false;
			$scope.success=false;
		},7000);
	}
	
});