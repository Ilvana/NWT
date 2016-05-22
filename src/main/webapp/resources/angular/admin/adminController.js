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




        $scope.movies = [];

        $scope.movie = {
            'id': null,
            'name': '',
            'duration': '',
            'description': '',
            'director': '',
            'genre': '',
        };

        AdminService.getMovies().then(function(data) {
            $scope.movies = data;
        });

        $scope.deleteMovie = function(movie) {
            var index = $scope.movies.indexOf(movie);
            if (index == -1) {
                return;
            }
            AdminService.deleteMovie(movie).then(function () {
                $scope.movies.splice(index, 1);
            }, function() { $window.location.href='/404' });
        };

        $scope.editMovie = function(movie) {
            $scope.submitModal = 'Update';
            $scope.modalHeader = 'Update movie';
            $scope.create = false;
            $scope.movie = angular.copy(movie);
            $(angular.element(movieModal)).modal('show');
        }

        $scope.cleanMovieDialog = function() {
            $scope.resetMovie();
            $(angular.element(movieModal)).modal("hide");
            // Clean input

            // Set current movie to default
        }

        $scope.createMovie = function() {
            $scope.submitModal = 'Create';
            $scope.modalHeader = 'Create new movie';
            $scope.create = true;
            $(angular.element(movieModal)).modal('show');
        }

        $scope.handleMovie = function() {
            // Validacija
            if($scope.create) {
                delete $scope.movie.id;
                AdminService.createMovie($scope.movie);
                $scope.movies.push(angular.copy($scope.movie));
            } else {
                AdminService.updateMovie($scope.movie);
                for(i=0; i < $scope.movies.length; i++) {
                    if($scope.movies[i].id == $scope.movie.id) {
                        $scope.movies[i] = angular.copy($scope.movie);
                        break;
                    }
                }
            }
            $scope.cleanMovieDialog();
        }

        $scope.resetMovie = function() {
            $scope.movie.id = null;
            $scope.movie.name = '';
            $scope.movie.duration = '';
            $scope.movie.description = '';
            $scope.movie.director = '';
            $scope.movie.genre = '';
        }

    }]);