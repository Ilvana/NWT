app.controller("AdminController", ['$scope', '$log', 'AdminService', '$window',
    function ($scope, $log, AdminService, $window) {
        $scope.templatePath = 'users';

        $scope.modalHeader = '';
        $scope.submitModal = '';
        $scope.create = true;

        $scope.loadTemplate = function(templatePath) {
            $scope.templatePath = templatePath;
        };

        $scope.users = [];

        $scope.user = {
            'id': null,
            'name': '',
            'address': '',
            'number': '',
            'email': '',
            'username': '',
            'password': '',
            'admin': false,
            'enabled': false
        };

        AdminService.getUsers().then(function(data) {
            $scope.users = data;
        });

        $scope.deleteUser = function(user) {
            var index = $scope.users.indexOf(user);
            if (index == -1) {
                return;
            }
            AdminService.deleteUser(user).then(function () {
                $scope.users.splice(index, 1);
            }, function() { $window.location.href='/404' });
        };

        $scope.editUser = function(user) {
            $scope.submitModal = 'Update';
            $scope.modalHeader = 'Update user';
            $scope.create = false;
            $scope.user = angular.copy(user);
            $scope.user.password = '';
            $(angular.element(userModal)).modal('show');
        }

        $scope.cleanUserDialog = function() {
            $scope.resetUser();
            $(angular.element(userModal)).modal("hide");
            // Clean input

            // Set current user to default
        }

        $scope.createUser = function() {
            $scope.submitModal = 'Create';
            $scope.modalHeader = 'Create new user';
            $scope.create = true;
            $(angular.element(userModal)).modal('show');
        }

        $scope.handleUser = function() {
            // Validacija
            if($scope.create) {
                delete $scope.user.id;
                AdminService.createUser($scope.user);
                $scope.users.push(angular.copy($scope.user));
            } else {
                AdminService.updateUser($scope.user);
                for(i=0; i < $scope.users.length; i++) {
                    if($scope.users[i].id == $scope.user.id) {
                        $scope.users[i] = angular.copy($scope.user);
                        break;
                    }
                }
            }
            $scope.cleanUserDialog();
        }

        $scope.resetUser = function() {
            $scope.user.id = null;
            $scope.user.name = '';
            $scope.user.address = '';
            $scope.user.number = '';
            $scope.user.email = '';
            $scope.user.username = '';
            $scope.user.password = '';
            $scope.user.admin = false;
            $scope.user.enabled = false;
        }

    }]);