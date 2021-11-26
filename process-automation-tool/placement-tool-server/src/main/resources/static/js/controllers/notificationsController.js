var notificationsController= angular.module("app");
notificationsController.controller("notificationsController", function ($scope, $timeout, $http) {

    $scope.disableNotificationButton = false;
    let errorMessageUrl = "js/controllers/errorMessages.json";
    let staticContentUrl = "js/controllers/staticContent.json";
    let sendNotificationUrl = '/sendNotification'

    //populate Job Roles
    $http.get('/getJobRoles')
        .then(function (response) {
            $scope.jobRolesObject = response.data;
        });

    //Method to fetch error message
    $http.get(errorMessageUrl)
        .then(function (response) {
            $scope.errorMessageArray = response.data[0];
        });

    //Method to fetch static content
    $http.get(staticContentUrl)
        .then(function (response) {
            $scope.staticContentArray = response.data[0];
        });


    //send notification
    $scope.sendNotification = function () {

        $scope.disableNotificationButton = true;
        $scope.notificationSuccess = false;
        $scope.notificationError=false;
        $scope.allowNotificationRequest=true;

        //VALIDATIONS
        if($scope.notification.jobRoleId==undefined || $scope.notification.jobRoleId=="" || $scope.notification.jobRoleId==null){
            $scope.allowNotificationRequest=false;
            $scope.disableNotificationButton = false;
            $scope.notificationError=true;
            $scope.notificationErrorMessage=$scope.errorMessageArray.invalidJobRoleMessage;
            $scope.messageTimeout();
        }
        else if($scope.notification.message==undefined || $scope.notification.message=="" || $scope.notification.message==null || $scope.notification.message.length<3){
            $scope.allowNotificationRequest=false;
            $scope.disableNotificationButton = false;
            $scope.notificationError=true;
            $scope.notificationErrorMessage=$scope.errorMessageArray.invalidMessageError;
            $scope.messageTimeout();
        }

        if ($scope.allowNotificationRequest) {
            let notificationDetails = {
                jobRoleId: $scope.notification.jobRoleId,
                message: $scope.notification.message
            }
            $http.post(sendNotificationUrl, JSON.stringify(notificationDetails))
                .then(function (response) {
                    $scope.disableNotificationButton = false;
                    if (response.data == 1) {
                        document.getElementById('notificationForm').reset();
                        $scope.notificationSuccess = true;
                        $scope.notificationError=false;
                        $scope.notification = {};
                        $scope.notificationSuccessMessage = $scope.errorMessageArray.notificationSuccessMessage;
                        $scope.messageTimeout();
                    } else if (response.data == -25) {
                        $scope.notificationSuccess = false;
                        $scope.notificationError = true;
                        $scope.notificationErrorMessage = $scope.errorMessageArray.notificationErrorMessage;
                        $scope.messageTimeout();
                    } else if(response.data == -65){
                        $scope.notificationSuccess = false;
                        $scope.notificationError = true;
                        $scope.notificationErrorMessage = $scope.errorMessageArray.noCandidatesFoundError;
                        $scope.messageTimeout();
                    }
                }, function (err) {
                    $scope.disableNotificationButton = false;
                    $scope.notificationSuccess = false;
                    $scope.notificationError = true;
                    $scope.notificationErrorMessage = $scope.errorMessageArray.notificationErrorMessage;
                    $scope.messageTimeout();
                });
        }
        $scope.disableNotificationButton = false;
    }


    //Method to hide success/error messages
    $scope.messageTimeout = function(){
        $timeout(function() {
            $scope.notificationSuccess = false;
            $scope.notificationError=false;
        }, 7000);
    }
});