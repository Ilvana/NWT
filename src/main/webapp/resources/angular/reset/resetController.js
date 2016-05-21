app.controller("ResetController", ['$scope', '$log', '$window', '$http',
    function ($scope, $log, $window, $http) {
        $scope.email = "";
        $scope.error = false;

        $scope.resetPassword = function() {
            if($scope.email != "") {
                $http.post("/user/reset", $scope.email, { headers: { 'Content-Type':'application/json' } })
                        .success(function() {
                            $window.location.href = "/#/login";
                        })
                        .error(function(data, status, headers, config) {
                            $log.log(data, status, headers, config);
                            $scope.error = true;
                        });
            }
        }
    }]);