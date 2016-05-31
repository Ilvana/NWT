app.controller("LoginController", ['$scope', '$routeParams', '$log', '$window', '$http',
    function ($scope, $routeParams, $log, $window, $http) {

        $scope.csrfValue = $window.document.getElementsByName('_csrf')[0].content;

        $scope.error = false;
        $scope.confirm = ($routeParams.confirm != null && $routeParams.confirm == 'confirm');

        $scope.user = {
            username: "",
            password: ""
        };

        $scope.login = function(event) {
            user = $scope.user;
            event.preventDefault();
            if(user.username == null || user.username == "" || user.password == null || user.password == "") {
                $scope.error = true;
                return;
            }

            $http.post('/user/login', user).success(function (data) {
                angular.element(loginForm)[0].submit();
            }).error(function (data, status, headers, config) {
                $log.log(data, status, headers, config);
                $scope.error = true;
                return;
            });
        }

        $scope.forgotPassword = function(){
            $window.location.href = "/#/reset";
        }
    }]);