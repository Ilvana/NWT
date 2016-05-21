app.controller("RegistrationController", ['$scope', '$log', 'RegistrationService', '$window',
    function ($scope, $log, RegistrationService, $window) {

        $scope.user = {
            address: "",
            adminFlag: false,
            email: "",
            name: "",
            number: "",
            username: "",
            password: "",
            enabled: false,
            role: "ROLE_USER",
            recaptcha: ""
        };

        $scope.error = false;
        $scope.errorMessage = "Registration failed. Wrong parameters.";

        grecaptcha.render('recaptcha', {
            'sitekey' : '6Le89x4TAAAAAMugrzia5v1kHjD9BgBVUILXF9VW'
        });

        $scope.invalidAddress = true;

        $scope.validateAddress = function () {
            var invalid = angular.element(address).val() == null ||angular.element(address).val() == "";
            if(!invalid) {
                $scope.invalidAddress = false;
                angular.element(addressWrapper).removeClass("has-error");
            } else {
                $scope.invalidAddress = true;
                angular.element(addressWrapper).addClass("has-error");
            }
        };
        $scope.invalidName = true;

        $scope.validateName = function () {
            var invalid = angular.element(fullName).val() == null ||angular.element(fullName).val() == "";
            if(!invalid) {
                $scope.invalidName = false;
                angular.element(fullNameWrapper).removeClass("has-error");
            } else {
                $scope.invalidName = true;
                angular.element(fullNameWrapper).addClass("has-error");
            }
        };
        $scope.invalidPhone = true;

        $scope.validatePhone = function () {
            var invalid = angular.element(phone).val() == null ||angular.element(phone).val() == "";
            if(!invalid) {
                $scope.invalidPhone = false;
                angular.element(phoneWrapper).removeClass("has-error");
            } else {
                $scope.invalidPhone = true;
                angular.element(phoneWrapper).addClass("has-error");
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

        $scope.invalidUsername = true;

        $scope.validateUsername = function () {
            var invalid = angular.element(username).val() == null ||angular.element(username).val() == "";
            if(!invalid) {
                $scope.invalidUsername = false;
                angular.element(usernameWrapper).removeClass("has-error");
            } else {
                $scope.invalidUsername = true;
                angular.element(usernameWrapper).addClass("has-error");
            }
        };
        $scope.invalidPassword = true;

        $scope.validatePassword = function () {
            var invalid = angular.element(password).val() == null ||angular.element(password).val() == "";
            if(!invalid) {
                $scope.invalidPassword = false;
                angular.element(passwordWrapper).removeClass("has-error");
            } else {
                $scope.invalidPassword = true;
                angular.element(passwordWrapper).addClass("has-error");
            }
        };




        $scope.createNewUser = function () {

            if ($scope.invalidName) {
                angular.element(fullNameWrapper).addClass("has-error");
                angular.element(fullName)[0].focus();
                $scope.error = true;
            } else if ($scope.invalidEmail) {
                angular.element(emailWrapper).addClass("has-error");
                angular.element(email)[0].focus();
                $scope.error = true;
            } else if ($scope.invalidAddress) {
                angular.element(addressWrapper).addClass("has-error");
                angular.element(address)[0].focus();
                $scope.error = true;
            } else if ($scope.invalidPhone) {
                angular.element(phoneWrapper).addClass("has-error");
                angular.element(phone)[0].focus();
                $scope.error = true;
            } else if ($scope.invalidUsername) {
                angular.element(usernameWrapper).addClass("has-error");
                angular.element(username)[0].focus();
                $scope.error = true;
            } else if ($scope.invalidPassword) {
                angular.element(passwordWrapper).addClass("has-error");
                angular.element(password)[0].focus();
                $scope.error = true;
            } else {
                $scope.error = false;
                $scope.user.address = angular.element(address).val();
                $scope.user.number = angular.element(phone).val();
                $scope.user.email = angular.element(email).val();
                $scope.user.name = angular.element(fullName).val();
                $scope.user.username = angular.element(username).val();
                $scope.user.password = angular.element(password).val();
                $scope.user.recaptcha = grecaptcha.getResponse();
                RegistrationService.createNewUser($scope.user).then(
                    function() {
                        $log.info("Registration success.");
                        $window.location.href = "/#/login/confirm";
                    },
                    function() {
                        $scope.error=true;
                        $scope.errorMessage="Registration failed. You might be a robot. Whoa!";
                    }
                );
            }
        };
    }]);