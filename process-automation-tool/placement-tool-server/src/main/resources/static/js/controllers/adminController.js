var admin = angular.module("app");
admin.controller("adminController",function($scope, $http){

	var staticContentUrl ="js/controllers/staticContent.json";
	var downloadDocumentUrl = "/downloadPdfFile/";

	/**
	 * METHOD TO ADD BORDER
	 */
	$scope.addBorder = function(listItem)
	{
		var subMenu = document.getElementsByClassName("sublist");
		var i;
		for(i=0;i<subMenu.length;i++)
		{
			angular.element(subMenu[i]).removeClass("activeMenu");
    	}	
    	  angular.element(document.querySelector(listItem)).addClass("activeMenu");
    }; 
	
    
    /**
	 * METHOD TO SHOW/HIDE NESTED SUBMENU 
	 */
    $scope.displaySublist= function(sublistMenu ,idOfElement)
    {
    	var elementId = document.getElementById(idOfElement);
    	if(angular.element(document.querySelector(sublistMenu)).hasClass("glyphicon-chevron-down"))
    	{
    		angular.element(document.querySelector(sublistMenu)).removeClass("glyphicon-chevron-down");
    		angular.element(document.querySelector(sublistMenu)).addClass("glyphicon-chevron-up");
    		elementId.style.display = "block";
    	}
    	else
    	{
    		angular.element(document.querySelector(sublistMenu)).removeClass("glyphicon-chevron-up");
    		angular.element(document.querySelector(sublistMenu)).addClass("glyphicon-chevron-down");
    		elementId.style.display = "none";
    	}
    } 
    
    /**
     * FUNCTION TO SHOW/HIDE SIDE NAVIGATION BAR ON HAMBURGER CLICK
     */
    $scope.toggleCollapseClass= true;
    $scope.collapseNavigation = function ()
    {
    	if($scope.toggleCollapseClass)
    	{
    		document.getElementById('sideNavigationBar').classList.remove('navigationOpacityHideAnimation');
    		document.getElementById('sideNavigationBar').classList.add('navigationOpacityAnimation');
    		$scope.toggleCollapseClass= false;
      	}
    	else
    	{
    		document.getElementById('sideNavigationBar').classList.remove('navigationOpacityAnimation');
    		document.getElementById('sideNavigationBar').classList.add('navigationOpacityHideAnimation');
    		$scope.toggleCollapseClass= true;
      	}
    };

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