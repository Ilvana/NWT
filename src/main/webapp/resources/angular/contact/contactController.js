app.controller("ContactController", ['$scope', '$filter', '$log', "ContactService", "$window",
    function ($scope, $filter, $log, ContactService, $window) {
        $scope.contact={
            clientName:"",
            email:"",
            subject:"",
            message:""
        };

        $scope.invalidName = true;

        $scope.validateClientName = function () {
            var invalid = angular.element(clientName).val() == null ||angular.element(clientName).val() == "";
            if(!invalid) {
                $scope.invalidName = false;
                angular.element(nameWrapper).removeClass("has-error");
            } else {
                $scope.invalidName = true;
                angular.element(nameWrapper).addClass("has-error");
            }
        };

        $scope.invalidEmail = true;

        $scope.validateEmail = function () {
            var invalid = angular.element(email).val() == null ||angular.element(email).val() == "";
            if(!invalid) {
                $scope.invalidEmail = false;
                angular.element(emailWrapper).removeClass("has-error");
            } else {
                $scope.invalidEmail = true;
                angular.element(emailWrapper).addClass("has-error");
            }
        };

        $scope.invalidSubject = true;

        $scope.validateSubject = function () {
            var invalid = angular.element(subject).val() == null ||angular.element(subject).val() == "";
            if(!invalid) {
                $scope.invalidSubject = false;
                angular.element(subjectWrapper).removeClass("has-error");
            } else {
                $scope.invalidSubject = true;
                angular.element(subjectWrapper).addClass("has-error");
            }
        };

        $scope.invalidMessage = true;

        $scope.validateMessage = function () {
            var invalid = angular.element(message).val() == null ||angular.element(message).val() == "";
            if(!invalid) {
                $scope.invalidMessage = false;
                angular.element(messageWrapper).removeClass("has-error");
            } else {
                $scope.invalidMessage = true;
                angular.element(messageWrapper).addClass("has-error");
            }
        };

        $scope.contactUs = function () {
            if ($scope.invalidName) {
                angular.element(nameWrapper).addClass("has-error");
                angular.element(clientName)[0].focus();
            } else if ($scope.invalidEmail) {
                angular.element(emailWrapper).addClass("has-error");
                angular.element(email)[0].focus();
            }else if ($scope.invalidSubject) {
                angular.element(subjectWrapper).addClass("has-error");
                angular.element(subject)[0].focus();
            }else if ($scope.invalidMessage) {
                angular.element(messageWrapper).addClass("has-error");
                angular.element(message)[0].focus();
            }else {
                $scope.contact.clientName=angular.element(clientName).val();
                $scope.contact.email=angular.element(email).val();
                $scope.contact.subject=angular.element(subject).val();
                $scope.contact.message=angular.element(message).val();
            }
            ContactService.contactUs($scope.contact);
        }

        $log.log("Contact controller.");

    }]);