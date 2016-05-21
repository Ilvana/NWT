app.controller("AdminController", ['$scope', '$log', 'AdminService', '$window',
    function ($scope, $log, AdminService, $window) {
        $scope.templatePath = 'users';

        $scope.loadTemplate = function(templatePath) {
            $scope.templatePath = templatePath;
        }

        $scope.users = [];

        AdminService.getUsers().then(function(data) {
            $scope.users = data;
        });

        $scope.deleteUser = function(id) {
            $log.log(id);
        }
        $scope.editUser = function(id) {
            $log.log(id);
        }

        $scope.cleanUserDialog = function() {
            $(angular.element(createNewUser)).modal("hide");
            // Clean input

            // Set current user to default
        }

        $scope.createUser = function() {
            $(angular.element(createNewUser)).modal('show');
        }

    }]);