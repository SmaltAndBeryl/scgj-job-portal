var app = angular.module('accountVerifiedModule', []);
app.controller("accountVerifiedModuleController", function($scope, $window, $http) {	

	var staticContentUrl ="js/controllers/staticContent.json";
	var downloadDocumentUrl = "/downloadPdfFile/";

	$scope.redirectToPlacementPortal = function(){
		 $window.location.href="/";	
	}
	
		
	//Method to fetch static content
	$http.get(staticContentUrl)
		.then(function(response){
			$scope.staticContentArray = response.data[0];
		});
	
	//Method to fetch terms and conditions path
	$http.get("/getTermsAndConditions")
	.then(function(response){
		$scope.termsAndConditionsFilePath=response.data.filepath;
	});
	
	//show or download terms and condition
	$scope.downloadTermsAndConditionsDocument= function(){
		let downloadTermsAndConditionsUrl = downloadDocumentUrl + $scope.termsAndConditionsFilePath;
		$http.get(downloadTermsAndConditionsUrl,{
			responseType: 'blob'
		})
		.then(function(response){
			if(response.data.byteLength!=0){
				let link = document.createElement('a');
				link.href = URL.createObjectURL(response.data);
				link.download = $scope.staticContentArray.termsAndConditionDocumentName;
				link.setAttribute('target','_blank');
				document.body.appendChild(link);
				link.click();
			}
		});
	};


});