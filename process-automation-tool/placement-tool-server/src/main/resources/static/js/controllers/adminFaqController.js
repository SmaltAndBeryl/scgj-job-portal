var adminFaq = angular.module("app");
adminFaq.controller("adminFaqController",function($scope, $http, $timeout){
	
	//variable initialization
	$scope.faq1Answer=0;
	$scope.faq2Answer=0;
	$scope.faq3Answer=0;
	$scope.faq4Answer=0;
	$scope.faq5Answer=0;
	$scope.faq6Answer=0;
	$scope.faq7Answer=0;
	$scope.faq8Answer=0;
	$scope.errorFaq2 = false;
	$scope.errorFaq3 = false;
	$scope.errorFaq4 = false;
	$scope.errorFaq7 = false;
	$scope.errorFaq9 = false;
	$scope.errorFaq10 = false;
	$scope.errorFaq11 = false;


	var rowHeight=30;
	var headerHeight=55;
	var minHeight = 90+"px";
	var maxGridHeight = 210;
	
	// ui-grid to employer details
	$scope.viewEmployerList = {
			enableGridMenus: false,
			enableSorting: false,
			enableFiltering: true,
			enableCellEdit: false,
			enableColumnMenus: false,
			enableHorizontalScrollbar: 0,
			enableVerticalScrollbar: 1,
			columnDefs: [{
                name: 'employerName',
                displayName: "Employer Name",
                width: '22%',
                cellTooltip: function(row, col) {
                    return 'Employer Name: ' + row.entity.employerName;
                }
            },
            {
                name: 'liasingAuthorityName',
                displayName: 'Liasing Authority Name',
                width: '22%',
                cellTooltip: function(row, col) {
                    return 'Liasing Authority Name: ' + row.entity.liasingAuthorityName;
                }
            },
            {
                name: 'mobileNumber',
                displayName: "Contact Number",
                width: '15%',
                cellTooltip: function(row, col) {
                    return 'Contact Number: ' + row.entity.mobileNumber;
                }
            },
            
            {
                name: 'email',
                displayName: 'Email',
                width: '26%',
                cellTooltip: function(row, col) {
                    return 'Email: ' + row.entity.email;
                }
            },
            {
                name: 'totalJobPostings',
                displayName: 'Job Postings',
                width: '16%',
                cellTooltip: function(row, col) {
                    return 'Job Postings: ' + row.entity.totalJobPostings;
                }
            }
	    ]};
	
	
	// ui-grid to employer details for small screen
	$scope.viewEmployerListSmallScreen = {
			enableGridMenus: false,
			enableSorting: false,
			enableFiltering: true,
			enableCellEdit: false,
			enableColumnMenus: false,
			enableHorizontalScrollbar: 1,
			enableVerticalScrollbar: 1,
			columnDefs: [{
                name: 'employerName',
                displayName: "Employer Name",
                width: '60%',
                cellTooltip: function(row, col) {
                    return 'Employer Name: ' + row.entity.employerName;
                }
            },
            {
                name: 'liasingAuthorityName',
                displayName: 'Liasing Authority Name',
                width: '50%',
                cellTooltip: function(row, col) {
                    return 'Liasing Authority Name: ' + row.entity.liasingAuthorityName;
                }
            },
            {
                name: 'mobileNumber',
                displayName: "Contact Number",
                width: '30%',
                cellTooltip: function(row, col) {
                    return 'Contact Number: ' + row.entity.mobileNumber;
                }
            },   
            {
                name: 'email',
                displayName: 'Email',
                width: '75%',
                cellTooltip: function(row, col) {
                    return 'Email: ' + row.entity.email;
                }
            },
            {
                name: 'totalJobPostings',
                displayName: 'Total Job Postings',
                width: '40%',
                cellTooltip: function(row, col) {
                    return 'Total Job Postings: ' + row.entity.totalJobPostings;
                }
            }
	    ]};
	
	
	// ui-grid to view candidate details
	$scope.viewCandidateList = {
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
				width: '11%',
				cellTooltip: function(row, col) {
					return 'Job Id: ' + row.entity.jobId;
				}
			},
			{
				name: 'jobTitle',
				displayName: "Job Title",
				width: '25%',
				cellTooltip: function(row, col) {
					return 'Job Title: ' + row.entity.jobTitle;
				}
			},
			{
				name: 'candidateName',
				displayName: "Candidate Name",
                width: '22%',
                cellTooltip: function(row, col) {
                    return 'Candidate Name: ' + row.entity.candidateName;
                }
            },
            {
                name: 'mobileNumber',
                displayName: 'Mobile Name',
                width: '17%',
                cellTooltip: function(row, col) {
                    return 'Mobile Name: ' + row.entity.mobileNumber;
                }
            },
            {
                name: 'guardianNumber',
                displayName: "Guardian Number",
                width: '17%',
                cellTooltip: function(row, col) {
                    return 'Guardian Number: ' + row.entity.guardianNumber;
                }
            },
            
            {
                name: 'placementStatus',
                displayName: 'Status',
                width: '14%',
    			cellTemplate : '<div class="jobStatusGridText text-center"><p title="Status: {{row.entity.placementStatus}}" class="hiredText" ng-show="row.entity.placementStatus ==\'Hired\'">{{row.entity.placementStatus}}</p><p title="Status: {{row.entity.placementStatus}}" class="shortlistedText" ng-show="row.entity.placementStatus ==\'Shortlisted\'">{{row.entity.placementStatus}}</p>',
    			cellTooltip: function(row, col) 
    			{
    				return 'Status: ' + row.entity.placementStatus;
    			}
            },
            {
                name : "descriptionDocumentPath",
                displayName : "Description",
                width : '12%',
                cellTooltip: "Download Document", 
                cellTemplate:'<div class="text-center"><img ng-hide="row.entity.descriptionDocumentPath == null" src="../images/pdfIcon.png" alt="Download Pdf Icon" class="gridDownloadJobDescriptionIcon" ng-click="grid.appScope.downloadJobDescription(row.entity)"><p class="notAvailableGridText" ng-show="row.entity.descriptionDocumentPath == null">N/A</p></div>'
            }
	    ]};
	
	
	// ui-grid to view candidate  details small screen
	$scope.viewCandidateListSmallScreen = {
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
				name: 'candidateName',
				displayName: "Candidate Name",
                width: '50%',
                cellTooltip: function(row, col) {
                    return 'Candidate Name: ' + row.entity.candidateName;
                }
            },
            {
                name: 'mobileNumber',
                displayName: 'Mobile Name',
                width: '40%',
                cellTooltip: function(row, col) {
                    return 'Mobile Name: ' + row.entity.mobileNumber;
                }
            },
            {
                name: 'guardianNumber',
                displayName: "Guardian Number",
                width: '40%',
                cellTooltip: function(row, col) {
                    return 'Guardian Number: ' + row.entity.guardianNumber;
                }
            }, 
            {
                name: 'placementStatus',
                displayName: 'Status',
                width: '28%',
    			cellTemplate : '<div class="jobStatusGridText text-center"><p title="Status: {{row.entity.placementStatus}}" class="hiredText" ng-show="row.entity.placementStatus ==\'Hired\'">{{row.entity.placementStatus}}</p><p title="Status: {{row.entity.placementStatus}}" class="shortlistedText" ng-show="row.entity.placementStatus ==\'Shortlisted\'">{{row.entity.placementStatus}}</p>',
    			cellTooltip: function(row, col) 
    			{
    				return 'Status: ' + row.entity.placementStatus;
    			}
            },
            {
                name : "descriptionDocumentPath",
                displayName : "Description",
                width : '30%',
                cellTooltip: "Download Document", 
                cellTemplate:'<div class="text-center"><img ng-hide="row.entity.descriptionDocumentPath == null" src="../images/pdfIcon.png" alt="Download Pdf Icon" class="gridDownloadJobDescriptionIcon" ng-click="grid.appScope.downloadJobDescription(row.entity)"><p class="notAvailableGridText" ng-show="row.entity.descriptionDocumentPath == null">N/A</p></div>'
            }
	    ]};
	
	

	
	//Request to fetch error message
	$http.get("js/controllers/errorMessages.json")
	.then(function(errorResponse){
		$scope.errorMessages=errorResponse.data[0];
	});	

	//request to fetch static content
	$http.get("js/controllers/staticContent.json")
		.then(function(response){
			$scope.staticContentArray= response.data[0];
		});
	
	//function to calculate date in yyyy-mm-dd format
	$scope.formatDateToString = function (date){
		var dd = (date.getDate() < 10 ? '0' : '') + date.getDate();
		var MM = ((date.getMonth() + 1) < 10 ? '0' : '') + (date.getMonth() + 1);
		var yyyy = date.getFullYear();
		return (yyyy + $scope.staticContentArray.hyphenText + MM + $scope.staticContentArray.hyphenText + dd);
	}
	
	
	//Function to expand FAQ panel
	$scope.expandFaqPanel = function(idGlyphicon,elementId)
	{
		//section to update glyphicon
		var glyphiconId = angular.element(document.querySelector(idGlyphicon));
		var panelId = angular.element(document.querySelector(elementId));
		if(glyphiconId.hasClass("glyphicon glyphicon-plus")){
			glyphiconId.removeClass("glyphicon glyphicon-plus");
			glyphiconId.addClass("glyphicon glyphicon-minus");
		}
		else{
			glyphiconId.removeClass("glyphicon glyphicon-minus");
			glyphiconId.addClass("glyphicon glyphicon-plus");
		}
		
		//Section to show/hide panel
		if(panelId.hasClass("hidePanel")){
			panelId.removeClass("hidePanel");
		}
		else{
			panelId.addClass("hidePanel");
		}
	}


	// if panel is already open & data is empty, hide the panel
	$scope.hideIfGridDataIsEmpty= function (idGlyphicon,elementId){
		var glyphiconId = angular.element(document.querySelector(idGlyphicon));
		var panelId = angular.element(document.querySelector(elementId));
		if(glyphiconId.hasClass("glyphicon glyphicon-minus")){
			glyphiconId.removeClass("glyphicon glyphicon-minus");
		}
		glyphiconId.addClass("glyphicon glyphicon-plus");
		if(!panelId.hasClass("hidePanel")){
			panelId.addClass("hidePanel");
		}
	}
	
	
	//Fetch employer list 
	$scope.getAllEmployer=function(){
		$http.get("/getEmployers")
		.then(function(response){
			$scope.totalEmployersList=response.data;
		});	
	}
	$scope.getAllEmployer();
	
	
	//Fetch job role list 
	$scope.getAllJobRoles=function(){
		$http.get("/getJobRoles")
		.then(function(response){
			$scope.totalJobRolesList=response.data;
		});	
	}
	$scope.getAllJobRoles();
	
	//Fetch states list
	$scope.getStatesList = function(){
	    $http.get('/getStates')
		.then(function(response){
			let statesArray = response.data;
	      	for (i in statesArray) {
	      		$scope.statesList.push(statesArray[i].stateName);
	      	}
		});
	}
	
	
	//Method to check date validations
	$scope.dateValidations = function(selectedTimePeriod,customStartDate,customEndDate,faqQuestionNumber)
	{
		var validDate=true
		let error=false;

		//setting all error messages as false
			$scope.errorFaq2 = false;
			$scope.errorFaq3 = false;
			$scope.errorFaq4 = false;
			$scope.errorFaq7 = false;
			$scope.errorFaq9 = false;
			$scope.errorFaq10 = false;
			$scope.errorFaq11 = false;
			$scope.errorFaq12 = false;


		if(selectedTimePeriod == $scope.staticContentArray.oneMonthText){
			var currentDate = new Date();
			$scope.formattedEndDate = $scope.formatDateToString(currentDate);
			currentDate.setMonth(currentDate.getMonth() - 1);
			$scope.formattedStartDate = $scope.formatDateToString(currentDate);
		}
		else if(selectedTimePeriod == $scope.staticContentArray.threeMonthText){
			var currentDate = new Date();
			$scope.formattedEndDate = $scope.formatDateToString(currentDate);
			currentDate.setMonth(currentDate.getMonth() - 3);
			$scope.formattedStartDate = $scope.formatDateToString(currentDate);
		}
		else if(selectedTimePeriod == $scope.staticContentArray.sixMonthText){
			var currentDate = new Date();
			$scope.formattedEndDate = $scope.formatDateToString(currentDate);
			currentDate.setMonth(currentDate.getMonth() - 6);
			$scope.formattedStartDate = $scope.formatDateToString(currentDate);
		}
		
		else if(selectedTimePeriod == $scope.staticContentArray.customDateText){			
			var today = new Date();
			$scope.todaysDate = $scope.formatDateToString(today);
			
			if(customStartDate =="" || customStartDate == undefined || customStartDate == null){
				error=true;
				$scope.errorMessage=$scope.errorMessageArray.faqEnterStartDate;
				validDate=false;
			}
			else if(customEndDate =="" || customEndDate == undefined || customEndDate == null){
				error=true;
				$scope.errorMessage=$scope.errorMessageArray.faqEnterEndDate;
				validDate=false;
			}
			else if(customStartDate > $scope.todaysDate){
				error=true;
				$scope.errorMessage=$scope.errorMessageArray.faqInvalidStartDateError;
				validDate=false;
			}
			else if(customEndDate > $scope.todaysDate){
				error=true;
				$scope.errorMessage=$scope.errorMessageArray.faqInvalidEndDateError;
				validDate=false;
			}
			else if(customEndDate < customStartDate){
				error=true;
				$scope.errorMessage=$scope.errorMessageArray.faqStartDateGreaterThanEndDateError;
				validDate=false;
			}
			else{
				$scope.formattedEndDate = customEndDate;
				$scope.formattedStartDate = customStartDate;
			}
		}
		else{
			error=true;
			$scope.errorMessage=$scope.errorMessageArray.faqSelectTimePeriodError;
			validDate=false;
		}

		//setting error according to the faq panel number
		if(error==true){
			switch(faqQuestionNumber){
				case 2:
					$scope.errorFaq2=true;
					$scope.hideErrorMessageFaq2();
					break;
				case 3:
					$scope.errorFaq3=true;
					$scope.hideErrorMessageFaq3();
					break;
				case 4:
					$scope.errorFaq4=true;
					$scope.hideErrorMessageFaq4();
					break;
				case 7:
					$scope.errorFaq7=true;
					$scope.hideErrorMessageFaq7();
					break;
				case 9:
					$scope.errorFaq9=true;
					$scope.hideErrorMessageFaq9();
					break;
				case 10:
					$scope.errorFaq10=true;
					$scope.hideErrorMessageFaq10();
					break;
				case 11:
					$scope.errorFaq11=true;
					$scope.hideErrorMessageFaq11();
					break;
			    case 12:
			    	$scope.errorFaq12=true;
					$scope.hideErrorMessageFaq12();
					break;
			}
		}
		return validDate;
	}
	
	
	
	/** -----------------------Functions for FAQ One------------------------------**/
	
	//Function to fetch total job listings by the selected employer
	$scope.viewTotalJobsByEmployer=function(idGlyphicon,elementId){
		$http.get('/getTotalJobsByEmployer?employerUserId='+$scope.faq1Employer.id)
		.then(function(response){
			$scope.faq1Answer=response.data;
			$scope.expandFaqPanel(idGlyphicon,elementId);
		});	
	}
	
	
	/** -----------------------Functions for FAQ Two------------------------------**/
	
	//Function to reset selected time if time period is changed
	$scope.resetFaq2TimePeriod = function(){
		if($scope.faq2SelectedTime != $scope.staticContentArray.customDateText){
			$scope.faq2StartTime="";
			$scope.faq2EndTime="";
		}
	}
	
	
	//Function to get count of candidates that got placed under “Jobrole” from “date” to “date”
	$scope.viewJobRoleSpecificCandidates = function(idGlyphicon,elementId){
		$scope.errorFaq2 = false;
		var dateValidationFaq2=$scope.dateValidations($scope.faq2SelectedTime,$scope.faq2StartTime,$scope.faq2EndTime,2);
		if(dateValidationFaq2){
			$http.get('/countOfJobroleSpecificHiredCandidates?jobrole='+$scope.faq2Jobrole.jobRole+'&startDate='+$scope.formattedStartDate+'&endDate='+$scope.formattedEndDate)
			.then(function(response){
				$scope.faq2Answer=response.data;
			});
			$scope.expandFaqPanel(idGlyphicon,elementId);
		}
	}


	/** -----------------------Functions for FAQ Eleven------------------------------**/

	//Function to reset selected time if time period is changed
	$scope.resetFaq11TimePeriod = function(){
		if($scope.faq11SelectedTime != $scope.staticContentArray.customDateText){
			$scope.faq11StartTime="";
			$scope.faq11EndTime="";
		}
	}

	//method to get the total hired candidate count for custom time duration
	$scope.viewTotalHiredCandidates = function(idGlyphicon,elementId){
		$scope.errorFaq11 = false;
		var dateValidationFaq11=$scope.dateValidations($scope.faq11SelectedTime,$scope.faq11StartTime,$scope.faq11EndTime,11);
		if(dateValidationFaq11){
			$http.get('/hiredCandidatesCount?'+'&startDate='+$scope.formattedStartDate+'&endDate='+$scope.formattedEndDate)
				.then(function(response){
					if(response.data!=-25)
						$scope.faq11Answer=response.data;
					else
						$scope.faq11Answer=0;
				});
			$scope.expandFaqPanel(idGlyphicon,elementId);
		}
	}

	/** -----------------------Functions for FAQ Three------------------------------**/	
	
	//Function to reset selected time if time period is changed
	$scope.resetFaq3TimePeriod = function(){
		if($scope.faq3SelectedTime != $scope.staticContentArray.customDateText){
			$scope.faq3StartTime="";
			$scope.faq3EndTime="";
		}
	}
	
	//Function to get count of candidates that got placed under “Jobrole” and selected state from “date” to “date”
	$scope.viewJobRoleStateSpecificCandidates=function(idGlyphicon,elementId){
		$scope.errorFaq3 = false;
		var dateValidationFaq3=$scope.dateValidations($scope.faq3SelectedTime,$scope.faq3StartTime,$scope.faq3EndTime,3);
		if(dateValidationFaq3){
			$http.get('/countStateWiseHiredCandidates?jobrole='+$scope.faq3Jobrole.jobRole+'&state='+$scope.faq3State+'&startDate='+$scope.formattedStartDate+'&endDate='+$scope.formattedEndDate)
			.then(function(response){
				$scope.faq3Answer=response.data;
			});
			$scope.expandFaqPanel(idGlyphicon,elementId);
		}
	}
	
    
    /** --------------------Functions for FAQ Four----------------------------------- **/
   
    //Function to reset selected time if time period is changed
	$scope.resetFaq4TimePeriod = function(){
		if($scope.faq4SelectedTime != $scope.staticContentArray.customDateText){
			$scope.faq4StartTime="";
			$scope.faq4EndTime="";
		}
	}
	
    //Function to get companies are not active on the job portal from “Date” to “Date”?
    $scope.viewInactiveCompanies=function(idGlyphicon,elementId){
    	$scope.errorFaq4 = false;
		var dateValidation=$scope.dateValidations($scope.faq4SelectedTime,$scope.faq4StartTime,$scope.faq4EndTime,4);
		if(dateValidation){
			$http.get('/countOfInactiveEmployer?startDate='+$scope.formattedStartDate+'&endDate='+$scope.formattedEndDate)
			.then(function(response){
				$scope.faq4Answer=response.data;
			});
			$scope.expandFaqPanel(idGlyphicon,elementId);
		}
    }
    
    
    /** --------------------Functions for FAQ Five----------------------------------- **/
  //Function to fetch total active candidates
    $scope.viewActiveCandidates=function(idGlyphicon,elementId){
    	$http.get('/getActiveCandidatesCount')
		.then(function(response){
			$scope.faq5Answer=response.data;
			$scope.expandFaqPanel(idGlyphicon,elementId);
		});
    }
   
    
    /** --------------------Functions for FAQ Six----------------------------------- **/
    
	//Function to fetch total job posting as per “jobroles”
	$scope.viewJobRoleSpecificJobs=function(idGlyphicon,elementId){
		$http.get('/countOfJobroleSpecificJobs?jobrole='+$scope.faq6Jobrole.jobRole)
		.then(function(response){
			$scope.faq6Answer=response.data;
			$scope.expandFaqPanel(idGlyphicon,elementId);
		});	
	}
    
	
	/** --------------------Functions for FAQ Seven----------------------------------- **/
	
	//Function to reset selected time if time period is changed
	$scope.resetFaq7TimePeriod = function(){
		if($scope.faq7SelectedTime != $scope.staticContentArray.customDateText){
			$scope.faq7StartTime="";
			$scope.faq7EndTime="";
		}
	}
	
	//Function to fetch count of placed cgsc certified candidates
	$scope.countPlacedCGSCCertifiedCandidate=function(idGlyphicon,elementId){
		$scope.errorFaq7 = false;
		var dateValidation=$scope.dateValidations($scope.faq7SelectedTime,$scope.faq7StartTime,$scope.faq7EndTime,7);
		if(dateValidation){
			$http.get('/getCountOfPlacedCGSCCertifiedCandidates?startDate='+$scope.formattedStartDate+'&endDate='+$scope.formattedEndDate)
			.then(function(response){
				$scope.faq7Answer=response.data;
			});
			$scope.expandFaqPanel(idGlyphicon,elementId);
		}
	}
	    
	    
	/** --------------------Functions for FAQ Eight----------------------------------- **/
	$scope.viewActiveJobPostings=function(idGlyphicon,elementId){
		$http.get('/getTotalJobsCount')
		.then(function(response){
			$scope.faq8Answer=response.data;
			$scope.expandFaqPanel(idGlyphicon,elementId);
		});	
	}
	
	/** ------------------------------Functions for FAQ Nine---------------------------------------**/
	
	//Function to reset selected time if time period is changed
	$scope.resetFaq9TimePeriod = function(){
		if($scope.faq9SelectedTime != $scope.staticContentArray.customDateText){
			$scope.faq9StartTime="";
			$scope.faq9EndTime="";
		}
	}
	
	//Method to populate job occupations
	$scope.populateJobOccupation = function(){
		let viewOccupationUrl ="/getOccupationByJobRole?jobRole="+$scope.faq9Jobrole.jobRole;
		$http.get(viewOccupationUrl)
		.then(function(response){
			 $scope.occupationList = response.data;
		});
	}
	
	//Method to get the list of employers with job postings count > min job count for the selected jobrole & occupation within the selected startDate and endDate  
	$scope.getEmployersWithMinimumJobPostings=function(idGlyphicon,elementId){
		$scope.errorFaq9=false;
		var dateValidation=$scope.dateValidations($scope.faq9SelectedTime,$scope.faq9StartTime,$scope.faq9EndTime,9);
		if(dateValidation){
			let getEmployerDetailsUrl='/getEmployerListWithJobCount?minJobCount='+$scope.faq9JobCount+'&jobrole='+$scope.faq9Jobrole.jobRole+'&occupation='+$scope.faq9Occupation.occupationName+'&startDate='+$scope.formattedStartDate+'&endDate='+$scope.formattedEndDate;
			$http.get(getEmployerDetailsUrl)
			.then(function(response){
				if(response.data.length>0){
					$scope.viewEmployerList.data= response.data;
					$scope.viewEmployerListSmallScreen.data= response.data;
					var gridHeight = $scope.viewEmployerList.data.length * rowHeight + headerHeight;
					if(gridHeight>maxGridHeight){
						document.getElementById("viewEmployerList").style.height=maxGridHeight+"px";
						document.getElementById("viewEmployerListSmallScreen").style.height=maxGridHeight+"px";
					}
					else{
						document.getElementById("viewEmployerList").style.height=gridHeight+"px";
						document.getElementById("viewEmployerListSmallScreen").style.height=gridHeight+"px";
					}
					$scope.expandFaqPanel(idGlyphicon,elementId);
				}
				else{
					$scope.errorFaq9=true;
					$scope.errorMessage=$scope.errorMessageArray.faqNoEmployerDetailsError;
					$scope.hideErrorMessageFaq9();
					$scope.viewEmployerList.data=[];
					$scope.viewEmployerListSmallScreen.data= [];
					document.getElementById("viewEmployerList").style.height=minHeight;
					document.getElementById("viewEmployerListSmallScreen").style.height=minHeight;
					$scope.hideIfGridDataIsEmpty(idGlyphicon,elementId);
				}
			});
		}
	}
	
	
	//Method to get the list of candidates placed (company wise) between selected time period
	$scope.getCompanyWiseCandidatesForJobrole=function(idGlyphicon,elementId){
		$scope.errorFaq10=false;
		var dateValidation=$scope.dateValidations($scope.faq10SelectedTime,$scope.faq10StartTime,$scope.faq10EndTime,10);
		if(dateValidation){
			let getCandidateDetailsUrl='/companyWiseHiredShortlistedCandidates?employerUserId='+$scope.faq10Employer.id+'&jobrole='+$scope.faq10Jobrole.jobRole+'&startDate='+$scope.formattedStartDate+'&endDate='+$scope.formattedEndDate;
			$http.get(getCandidateDetailsUrl)
			.then(function(response){
				if(response.data.length>0){
					$scope.viewCandidateList.data= response.data;
					$scope.viewCandidateListSmallScreen.data= response.data;
					var gridHeight = $scope.viewCandidateList.data.length * rowHeight + headerHeight;
					if(gridHeight>maxGridHeight){
						document.getElementById("viewCandidateList").style.height=maxGridHeight+"px";
						document.getElementById("viewCandidateListSmallScreen").style.height=maxGridHeight+"px";
					}
					else{
						document.getElementById("viewCandidateList").style.height=gridHeight+$scope.staticContentArray.ten+"px";
						document.getElementById("viewCandidateListSmallScreen").style.height=gridHeight+"px";
					}
					$scope.expandFaqPanel(idGlyphicon,elementId);
				}
				else{
					$scope.errorFaq10=true;
					$scope.errorMessage=$scope.errorMessageArray.faqNoCandidateDetailsError;
					$scope.hideErrorMessageFaq10();
					$scope.viewCandidateList.data=[];
					$scope.viewCandidateListSmallScreen.data= [];
					document.getElementById("viewCandidateList").style.height=minHeight;
					document.getElementById("viewCandidateListSmallScreen").style.height=minHeight;
					$scope.hideIfGridDataIsEmpty(idGlyphicon,elementId);
				}
			});
		}
	}


	/** ---------------------- Function for FAQ number 12 -----------------------------------------------------**/
	$scope.resetFaq12TimePeriod = function(){
		if($scope.faq12SelectedTime != $scope.staticContentArray.customDateText){
			$scope.faq12StartTime="";
			$scope.faq12EndTime="";
		}
	}
	
	$scope.viewStateWiseVacanciesCreated = function(idGlyphicon,elementId){
		$scope.errorFaq12 = false;
		var dateValidationFaq12=$scope.dateValidations($scope.faq12SelectedTime,$scope.faq12StartTime,$scope.faq12EndTime,12);
		if(dateValidationFaq12){
			$http.get('/fetchStateWiseVacancy?startDate='+$scope.formattedStartDate+'&endDate='+$scope.formattedEndDate+'&state='+$scope.faq12State)
				.then(function(response){
					if(response.data!=-25)
						$scope.faq12Answer=response.data;
					else
						$scope.faq12Answer=0;
				});
			$scope.expandFaqPanel(idGlyphicon,elementId);
		}
	}

	
	
	
	/** ----------------------------------- METHOD TO DOWNLOAD JOB DESCRIPTION DOCUMENT -----------------------**/

    $scope.downloadJobDescription = function(row){
    	$scope.errorFaq10 = false;
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
				$scope.errorMessage = $scope.errorMessageArray.descriptionDocumentDownloadError;
				$scope.errorFaq10=true;
				$scope.downloadGif=false;
				$scope.hideErrorMessageFaq10();
			}
			
		},function(error){
			$scope.errorMessage = $scope.errorMessageArray.descriptionDocumentDownloadError;
			$scope.errorFaq10=true;
			$scope.downloadGif=false;
			$scope.hideErrorMessageFaq10();
			
		});
    }

    
  // Method to hide messages
    $scope.hideErrorMessage = function(){
    	$timeout(function() {
			$scope.errorFaq2 = false;
			$scope.errorFaq3 = false;
			$scope.errorFaq4 = false;
			$scope.errorFaq7 = false;
			$scope.errorFaq9 = false;
			$scope.errorFaq10 = false;
			$scope.errorFaq11 = false;
			$scope.errorFaq12 = false;
		}, 7000);
    }

	$scope.hideErrorMessageFaq2 = function(){
		$timeout(function() {
			$scope.errorFaq2 = false;
		}, 7000);
	};
	$scope.hideErrorMessageFaq3 = function(){
		$timeout(function() {
			$scope.errorFaq3 = false;
		}, 7000);
	};
	$scope.hideErrorMessageFaq4 = function(){
		$timeout(function() {
			$scope.errorFaq4 = false;
		}, 7000);
	};
	$scope.hideErrorMessageFaq7 = function(){
		$timeout(function() {
			$scope.errorFaq7 = false;
		}, 7000);
	};
	$scope.hideErrorMessageFaq9 = function(){
		$timeout(function() {
			$scope.errorFaq9 = false;
		}, 7000);
	};
	$scope.hideErrorMessageFaq10 = function(){
		$timeout(function() {
			$scope.errorFaq10 = false;
		}, 7000);
	};
	$scope.hideErrorMessageFaq11 = function(){
		$timeout(function() {
			$scope.errorFaq11 = false;
		}, 7000);
	};
	$scope.hideErrorMessageFaq12 = function(){
		$timeout(function() {
			$scope.errorFaq12 = false;
		}, 7000);
	};

});
