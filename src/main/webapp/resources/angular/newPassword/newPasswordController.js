app.controller("NewPasswordController", ['$scope', '$routeParams',  '$log', '$window', '$http',
    function ($scope, $routeParams, $log, $window, $http) {

        token = $routeParams.token;

        $scope.error = false;
        $scope.errorMessage = "There was a problem setting new password. Please try again.";

        $scope.password = "";
        $scope.passwordConfirm = "";

        $scope.newPassword = function() {
            if($scope.password != "" && $scope.passwordConfirm != "" && $scope.password == $scope.passwordConfirm)  {

                $http.post("/user/newPassword/"+token, $scope.password, { headers: { 'Content-Type':'application/json' } })
                        .success(function() {
                            $window.location.href = "/#/login";
                        })
                        .error(function(data, status, headers, config) {
                            $log.log(data, status, headers, config);
                            $scope.error = true;
                            $scope.errorMessage = "Something went wrong. Token may be wrong.";
                        });
            } else {
                $scope.error = true;
                $scope.errorMessage = "Please enter same password twice.";
            }
        }
    }]);