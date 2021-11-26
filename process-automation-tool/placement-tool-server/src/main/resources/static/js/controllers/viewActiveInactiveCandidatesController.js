var viewActiveInactiveCandidates = angular.module("app");
viewActiveInactiveCandidates.controller("viewActiveInactiveCandidatesController",function($scope, $http, $timeout,$window){
	
	var errorMessageUrl ="js/controllers/errorMessages.json";
	var staticContentUrl ="js/controllers/staticContent.json";
	var getActiveInactiveCandidatesUrl="/getInactiveCandidates";		
	
	var rowHeight=30;
	var headerHeight=55;
	var minHeight = 90+"px";
	var maxGridHeight = 350;
	
	// ui-grid to view active/inactive candidates
	$scope.viewActiveInactiveCandidatesGrid = {
			enableGridMenus: false,
			enableSorting: false,
			enableFiltering: true,
			enableCellEdit: false,
			enableColumnMenus: false,
			enableHorizontalScrollbar: 1,
			enableVerticalScrollbar: 1,
			columnDefs: [{
                name: 'candidateName',
                displayName: "Candidate Name",
                width: '23%',
                cellTooltip: function(row, col) {
                    return 'Candidate Name: ' + row.entity.candidateName;
                }
            },
            {
                name: 'mobileNumber',
                displayName: "Mobile Number",
                width: '15%',
                cellTooltip: function(row, col) {
                    return 'Mobile Number: ' + row.entity.mobileNumber;
                }
            },
            {
                name: 'guardianMobileNumber',
                displayName: 'Guardian\'s Mobile Number',
                width: '22%',
                cellTooltip: function(row, col) {
                    return 'Guardian\'s Mobile Number: ' + row.entity.guardianMobileNumber;
                }
            },
            {
                name: 'gender',
                displayName: 'Gender',
                width: '12%',
                cellTooltip: function(row, col) {
                    return 'Gender: ' + row.entity.gender;
                }
            },
            {
                name: 'state',
                displayName: 'State',
                width: '18%',
                cellTooltip: function(row, col) {
                    return 'State: ' + row.entity.state;
                }
            },
//			{
//                name: 'trainingPartnerName',
//                displayName: 'Training Partner',
//                width: '24%',
//                cellTooltip: function(row, col) {
//                    return 'Training Partner: ' + row.entity.trainingPartnerName;
//                }
//            },
            {
                name: 'pollCount',
                displayName: 'Poll Count',
                width: '12%',
                cellTooltip: function(row, col) {
                    return 'Poll Count: ' + row.entity.pollCount;
                }
            },
            {
                name: 'lastPolledOn',
                displayName: 'Polled On',
                width: '20%',
                cellTooltip: function(row, col) {
                    return 'Polled On: ' + row.entity.lastPolledOn;
                }
            },
	        {
	        	name: 'action',
	        	displayName: 'Action',
	        	width: '12%',
	        	cellTemplate:'<div class="text-center"><button class="btn standardGridButton gridActionButton activateButton" ng-click="grid.appScope.updateCandidateStatus(row.entity)">Activate</button></div>'
	        }
	    ]};
	
	// ui-grid to view active/inactive candidates for small screen
	$scope.viewActiveInactiveCandidatesGridSmallScreen = {
			enableGridMenus: false,
			enableSorting: false,
			enableFiltering: true,
			enableCellEdit: false,
			enableColumnMenus: false,
			enableHorizontalScrollbar: 1,
			enableVerticalScrollbar: 1,
			columnDefs: [{
                name: 'candidateName',
                displayName: "Candidate Name",
                width: '65%',
                cellTooltip: function(row, col) {
                    return 'Candidate Name: ' + row.entity.candidateName;
                }
            },
            {
                name: 'mobileNumber',
                displayName: "Mobile Number",
                width: '45%',
                cellTooltip: function(row, col) {
                    return 'Mobile Number: ' + row.entity.mobileNumber;
                }
            },
            {
                name: 'guardianMobileNumber',
                displayName: 'Guardian\'s Mobile Number',
                width: '50%',
                cellTooltip: function(row, col) {
                    return 'Guardian\'s Mobile Number: ' + row.entity.guardianMobileNumber;
                }
            },
            {
                name: 'gender',
                displayName: 'Gender',
                width: '30%',
                cellTooltip: function(row, col) {
                    return 'Gender: ' + row.entity.gender;
                }
            },
            {
                name: 'state',
                displayName: 'State',
                width: '55%',
                cellTooltip: function(row, col) {
                    return 'State: ' + row.entity.state;
                }
            },
//			{
//                name: 'trainingPartnerName',
//                displayName: 'Training Partner',
//                width: '70%',
//                cellTooltip: function(row, col) {
//                    return 'Training Partner: ' + row.entity.trainingPartnerName;
//                }
//            },
            {
                name: 'pollCount',
                displayName: 'Poll Count',
                width: '32%',
                cellTooltip: function(row, col) {
                    return 'Poll Count: ' + row.entity.pollCount;
                }
            },
            {
                name: 'lastPolledOn',
                displayName: 'Polled On',
                width: '45%',
                cellTooltip: function(row, col) {
                    return 'Polled On: ' + row.entity.lastPolledOn;
                }
            },
	        {
	        	name: 'action',
	        	displayName: 'Action',
	        	width: '25%',
	        	cellTemplate:'<div class="text-center"><button class="btn standardGridButton gridActionButton activateButton" ng-click="grid.appScope.updateCandidateStatus(row.entity)">Activate</button></div>'
	        }
	    ]};
	
	
	
	/** ------------------------ Request to fetch error messages--------------------------*/
	$http.get(errorMessageUrl)
		.then(function(response){
			$scope.errorMessageArray = response.data[0];
		});
	
	/** ------------------------ Request to fetch static contente --------------------------*/
	$http.get(staticContentUrl)
		.then(function(response){
			$scope.staticContentArray= response.data[0];
		});
	
	
	/** ----------- Method to populate candidate details in ui-grid --------------**/
	$scope.populateCandidateDetails = function(){
		$http.get(getActiveInactiveCandidatesUrl)
		.then(function(response){
			if(response.data.length>0){
				
				$scope.candidateDetails=response.data;
				
				//loop to set value of gender for each candidate's details
				angular.forEach($scope.candidateDetails,function(row,index){
					if(row.gender == $scope.staticContentArray.genderMale)
					{
						$scope.candidateDetails[index].gender=$scope.staticContentArray.genderMaleText;
					}
					else if(row.gender == $scope.staticContentArray.genderFemale)
					{
						$scope.candidateDetails[index].gender=$scope.staticContentArray.genderFemaleText;
					}
					else{
						$scope.candidateDetails[index].gender=$scope.staticContentArray.genderOtherText;
					}
						
				});
				
				$scope.viewActiveInactiveCandidatesGrid.data= $scope.candidateDetails;
				$scope.viewActiveInactiveCandidatesGridSmallScreen.data= $scope.candidateDetails;
				if(screen.width>1200){
					headerHeight=70;
				}
				var gridHeight = $scope.viewActiveInactiveCandidatesGrid.data.length * rowHeight + headerHeight;
				if(gridHeight>maxGridHeight){
					document.getElementById("viewActiveInactiveCandidatesGrid").style.height=maxGridHeight+"px";
					document.getElementById("viewActiveInactiveCandidatesGridSmallScreen").style.height=maxGridHeight+"px";
				}
				else{
					document.getElementById("viewActiveInactiveCandidatesGrid").style.height=gridHeight+"px";
					document.getElementById("viewActiveInactiveCandidatesGridSmallScreen").style.height=gridHeight+"px";
				}
			}
			else{
				$scope.viewActiveInactiveCandidatesGrid.data= [];
				$scope.viewActiveInactiveCandidatesGridSmallScreen.data= [];
				document.getElementById("viewActiveInactiveCandidatesGrid").style.height=minHeight;
				document.getElementById("viewActiveInactiveCandidatesGridSmallScreen").style.height=minHeight;
			}
		});
	}
	
	$scope.populateCandidateDetails();
	
	
	/** ----------- Method to deactivate candidates ----------- */
	$scope.updateCandidateStatus=function(row){
		
		let updatedStatus=$scope.staticContentArray.trueFlag;
		
		$scope.loadingGif=true;
		$scope.error=false;
		$scope.success=false;
		
		let updateCandidateStatusUrl="/updateCandidateStatus?userId="+row.userId+"&updatedStatus="+updatedStatus;
		$http.get(updateCandidateStatusUrl)
		.then(function(response){
			if(response.data == 1){
				$scope.loadingGif=false;
				$scope.successMessage=$scope.errorMessageArray.updateCandidateStatusSuccess;
				$scope.success=true;
				$scope.hideErrorMessage();
				$scope.populateCandidateDetails();
			}
			else if(response.data == -66){
				$scope.loadingGif=false;
				$scope.errorMessage=$scope.errorMessageArray.updateCandidateStatusInvalidStatusError;
				$scope.error=true;
				$scope.hideErrorMessage();
			}
			else{
				$scope.loadingGif=false;
				$scope.errorMessage=$scope.errorMessageArray.updateCandidateStatusError;
				$scope.error=true;
				$scope.hideErrorMessage();
			}
		},function(error){
			$scope.loadingGif=false;
			$scope.errorMessage=$scope.errorMessageArray.updateCandidateStatusError;
			$scope.error=true;
			$scope.hideErrorMessage();
		});
	}
	
	
	//Method to hide error messages
	$scope.hideErrorMessage = function(){
		$timeout(function(){
			$scope.error=false;
			$scope.success=false;
		},7000);
	}
});