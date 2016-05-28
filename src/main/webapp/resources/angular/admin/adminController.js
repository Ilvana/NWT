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




        $scope.theaters = [];

        $scope.theater = {
            'id': null,
            'name': '',
            'duration': '',
            'description': '',
            'director': '',
            'genre': '',
        };

        AdminService.getTheaters().then(function(data) {
            $scope.theaters = data;
        });

        $scope.deleteTheater = function(theater) {
            var index = $scope.theaters.indexOf(theater);
            if (index == -1) {
                return;
            }
            AdminService.deleteTheater(theater).then(function () {
                $scope.theaters.splice(index, 1);
            }, function() { $window.location.href='/404' });
        };

        $scope.editTheater = function(theater) {
            $scope.submitModal = 'Update';
            $scope.modalHeader = 'Update theater';
            $scope.create = false;
            $scope.theater = angular.copy(theater);
            $(angular.element(theaterModal)).modal('show');
        }

        $scope.cleanTheaterDialog = function() {
            $scope.resetTheater();
            $(angular.element(theaterModal)).modal("hide");
            // Clean input

            // Set current theater to default
        }

        $scope.createTheater = function() {
            $scope.submitModal = 'Create';
            $scope.modalHeader = 'Create new theater';
            $scope.create = true;
            $(angular.element(theaterModal)).modal('show');
        }

        $scope.handleTheater = function() {
            // Validacija
            if($scope.create) {
                delete $scope.theater.id;
                AdminService.createTheater($scope.theater);
                $scope.theaters.push(angular.copy($scope.theater));
            } else {
                AdminService.updateTheater($scope.theater);
                for(i=0; i < $scope.theaters.length; i++) {
                    if($scope.theaters[i].id == $scope.theater.id) {
                        $scope.theaters[i] = angular.copy($scope.theater);
                        break;
                    }
                }
            }
            $scope.cleanTheaterDialog();
        }

        $scope.resetTheater = function() {
            $scope.theater.id = null;
            $scope.theater.name = '';
            $scope.theater.sizeX = '';
            $scope.theater.sizeY = '';
        }

        $('#timeBegin').datetimepicker();
        $('#timeEnd').datetimepicker();
        $scope.events = [];
        $scope.event = {
            'id': null,
            'name': '',
            'description': '',
            'timeBegin': '',
            'timeEnd':'',
            'picture':''
        };

        AdminService.getEvents().then(function(data) {
            $scope.events = data;
        });

        $scope.deleteEvent = function(event) {
            var index = $scope.events.indexOf(event);
            if (index == -1) {
                return;
            }
            AdminService.deleteEvent(event).then(function () {
                $scope.events.splice(index, 1);
            }, function () {
                $window.location.href = '/404'
            });
        };
        $scope.editEvent = function(event) {
            $scope.submitModal = 'Update';
            $scope.modalHeader = 'Update event';
            $scope.create = false;
            $scope.event = angular.copy(event);
            $(angular.element(eventModal)).modal('show');
        }

        $scope.cleanEventDialog = function() {
            $scope.resetEvent();
            $(angular.element(eventModal)).modal("hide");
            // Clean input
        }

        $scope.createEvent = function() {
            $scope.submitModal = 'Create';
            $scope.modalHeader = 'Create new event';
            $scope.create = true;
            $(angular.element(eventModal)).modal('show');
        }

        $scope.handleEvent = function() {
            // Validacija
            $scope.event.timeBegin = new Date($('#timeBegin').val());
            $scope.event.timeEnd = new Date($('#timeEnd').val());
            if($scope.create) {
                delete $scope.event.id;
                AdminService.createEvent($scope.event);
                $scope.events.push(angular.copy($scope.event));
            } else {
                validateDates();
                AdminService.updateEvent($scope.event);
                for(i=0; i < $scope.events.length; i++) {
                    if($scope.events[i].id == $scope.event.id) {
                        $scope.events[i] = angular.copy($scope.event);
                        break;
                    }
                }
            }
            $scope.cleanEventDialog();
        }
        var validateDates = function() {
            if($scope.event.timeBegin == 'Invalid Date') {
                dB = $('#timeBegin').val().split(' ')[0].split('-');
                tB = $('#timeBegin').val().split(' ')[1].split(':');
                $scope.event.timeBegin = new Date(dB[2], dB[1], dB[0], +1, tB[0], tB[1], tB[2]);

            }
            if($scope.event.timeEnd == 'Invalid Date') {
                dE = $('#timeEnd').val().split(' ')[0].split('-');
                tE = $('#timeEnd').val().split(' ')[1].split(':');
                $scope.event.timeEnd = new Date(dE[2], dE[1], dE[0], +1, tE[0], tE[1], tE[2]);
            }
        }
        $scope.resetEvent = function() {
            $scope.event.id = null;
            $scope.event.name = '';
            $scope.event.description = '';
            $scope.event.timeBegin = '';
            $scope.event.timeEnd = '';

        }

        $scope.screenings = [];

        var movieTmp='';
        var theaterTmp='';

        $scope.screening = {
            'id': null,
            'timeBegin': '',
            'timeEnd':'',
            'movie':'',
            'theater':''
        };

        $('#timeBeginScreening').datetimepicker();
        $('#timeEndScreening').datetimepicker();
        AdminService.getScreenings().then(function(data) {
            $scope.screenings = data;
        });
         $scope.deleteScreening = function(screening) {
            var index = $scope.screenings.indexOf(screening);
            if (index == -1) {
                return;
            }
            AdminService.deleteScreening(screening).then(function () {
                $scope.screenings.splice(index, 1);
            }, function () {
                $window.location.href = '/404'
            });
        };
        $scope.editScreening = function(screening) {
            $scope.submitModal = 'Update';
            $scope.modalHeader = 'Update screening';
            $scope.create = false;
            $scope.screening = angular.copy(screening);
            $(angular.element(screeningModal)).modal('show');
        }

        $scope.cleanScreeningDialog = function() {
            $scope.resetScreening();
            $(angular.element(screeningModal)).modal("hide");
            // Clean input
        }

        $scope.createScreening = function() {
            $scope.submitModal = 'Create';
            $scope.modalHeader = 'Create new screening';
            $scope.create = true;
            $(angular.element(screeningModal)).modal('show');
        }

        $scope.handleScreening = function() {
            // Validacija
            $scope.screening.timeBegin = new Date($('#timeBeginScreening').val());
            $scope.screening.timeEnd = new Date($('#timeEndScreening').val());
            if($scope.create) {
                delete $scope.screening.id;
                AdminService.getMovie($('#screeningMovie').val()).then(function(data) {
                    $log.log("filmic"+data);
                   movieTmp = data;
                });
                AdminService.getTheater($('#screeningTheater').val()).then(function(data) {
                    $log.log("teatar"+data.name);
                    theaterTmp = data;
                });
                $scope.screening.movie=movieTmp;
                $scope.screening.theater=theaterTmp;
                $log.log("filmic"+$scope.screening.movie.name);
                AdminService.createScreening($scope.screening);
                $scope.screenings.push(angular.copy($scope.screening));
            } else {
                validateDatesScreening();
                AdminService.updateScreening($scope.screening);
                for(i=0; i < $scope.screenings.length; i++) {
                    if($scope.screenings[i].id == $scope.screening.id) {
                        $scope.screenings[i] = angular.copy($scope.screening);
                        break;
                    }
                }
            }
            $scope.cleanScreeningDialog();
        }

        var validateDatesScreening = function() {
            if($scope.screening.timeBegin == 'Invalid Date') {
                dB = $('#timeBeginScreening').val().split(' ')[0].split('-');
                tB = $('#timeBeginScreening').val().split(' ')[1].split(':');
                $scope.screening.timeBegin = new Date(dB[2], dB[1], dB[0], +1, tB[0], tB[1], tB[2]);

            }
            if($scope.screening.timeEnd == 'Invalid Date') {
                dE = $('#timeEndScreening').val().split(' ')[0].split('-');
                tE = $('#timeEndScreening').val().split(' ')[1].split(':');
                $scope.screening.timeEnd = new Date(dE[2], dE[1], dE[0], +1, tE[0], tE[1], tE[2]);
            }
        }

        $scope.resetScreening = function() {
            $scope.screening.id = null;
            $scope.screening.movie = '';
            $scope.screening.theater = '';
            $scope.screening.timeBegin = '';
            $scope.screening.timeEnd = '';

        }



    }

]);



