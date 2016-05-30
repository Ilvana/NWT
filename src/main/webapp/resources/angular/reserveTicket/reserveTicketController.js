app.controller("ReserveTicketController", ['$scope', '$routeParams', '$filter', '$log', '$window', '$http', 'ReserveTicketService', 'HomeService',
    function ($scope, $routeParams, $filter, $log, $window, $http, ReserveTicketService, HomeService) {

        ReserveTicketService.getUserLogged().then(
            function() {
                $log.log("Creating reservation service for logged user.");
            },
            function() {
                $window.location.replace('/#/loginOrRegister');
            }
        );

        $scope.tempRes = "";
        $scope.changeReservation = function(seatX,seatY,status) {
            if(status == 1) {
                $scope.currentReservations.push({
                    positionX: seatX + 1,
                    positionY: seatY + 1
                });
            }
            else {
                var tempArray = [];
                angular.forEach($scope.currentReservations, function(currentRes) {
                    if(!(currentRes.positionX == seatX + 1 && currentRes.positionY == seatY + 1)) {
                        tempArray.push(currentRes);
                    }
                });
                $scope.currentReservations = tempArray;
            }
        };

        $scope.reserveTicket = function () {

            delete $scope.currentScreening.timeBegin;
            delete $scope.currentScreening.timeEnd;

            angular.forEach($scope.currentReservations, function(currentRes) {
                var tempTicket = {
                    seatX: currentRes.positionX,
                    seatY: currentRes.positionY,
                    screening: $scope.currentScreening,
                    user: null
                };

                ReserveTicketService.createNewTicket(tempTicket).then(
                    function(data) {
                        $log.log("New ticket reserved successfully.");
                    },
                    function() {
                        $window.location.replace('/403');
                    }
                )
            });
            $window.location.replace('/#/');
        };

        $scope.changeScreening = function(screeningId) {
            getTheaterAndTicketsForScreening($scope.screenings[screeningId]);
        };

        movieId = $routeParams.movieId;
        $scope.movie = null;
        $scope.screenings = [];
        $scope.currentTheater = null;
        $scope.currentTickets = [];
        $scope.seatsSchedule = [];
        $scope.currentScreening = null;
        $scope.currentReservations = [];
        $scope.seatsX = [];
        $scope.seatsY = [];

        $scope.rowSigns = ["A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W", "X", "Y", "Z"];

        function getTheaterAndTicketsForScreening(screening) {
            $scope.seatsX = [];
            $scope.seatsY = [];
            $scope.currentTickets = [];
            $scope.seatsSchedule = [];
            $scope.currentTheater = screening.theater;
            $scope.currentScreening = screening;
            $log.log($scope.currentScreening.timeBegin);
            ReserveTicketService.getAllTickets().then(function(data) {
                var localTickets = data;
                angular.forEach(localTickets, function(ticket) {
                    if(ticket.screening.id == screening.id) {
                        $scope.currentTickets.push(ticket);
                        //$log.log(ticket.id);
                    }
                });
                for(var i = 1; i <= $scope.currentTheater.sizeY; i++) {
                    for(var j = 1; j <= $scope.currentTheater.sizeX; j++) {
                        $scope.seatsSchedule.push(0);
                    }
                }

                angular.forEach($scope.currentTickets, function(ticket) {
                    $scope.seatsSchedule[(ticket.seatY - 1)*$scope.currentTheater.sizeX + (ticket.seatX - 1)] = 1;
                });

                for(var i = 1; i <= $scope.currentTheater.sizeX; i++) {
                    $scope.seatsX.push(i);
                }
                for(var i = 1; i <= $scope.currentTheater.sizeY; i++) {
                    $scope.seatsY.push(i);
                }


            }, function() {
                $window.location.replace('/403')}
            );
        }

        ReserveTicketService.getMovieById(movieId).then(function(data) {
            $scope.movie = data;
            HomeService.getAllScreenings().then(function(scrData) {
                var localScreenings = scrData;
                angular.forEach(localScreenings, function(screening) {
                    var tempDate = $filter('date')(new Date(), "dd-MM-yyyy");
                    var dateStr = screening.timeBegin.split(' ')[0];
                    if(screening.movie.id == movieId && tempDate == dateStr) {
                        $scope.screenings.push(screening);
                        $log.log(screening.timeBegin);
                        //sorting needed here
                    }
                });
                getTheaterAndTicketsForScreening($scope.screenings[0]);
                angular.forEach($scope.screenings, function(screening) {
                    var dateStr = screening.timeBegin.split(' ')[0].split('-');
                    var timeStr = screening.timeBegin.split(' ')[1].split(':');
                    var newDateBegin =new Date(dateStr[2], parseInt(dateStr[1])-1, dateStr[0], timeStr[0], timeStr[1], timeStr[2]);

                    dateStr = screening.timeEnd.split(' ')[0].split('-');
                    var timeStr = screening.timeEnd.split(' ')[1].split(':');
                    var newDateEnd =new Date(dateStr[2], parseInt(dateStr[1])-1, dateStr[0], timeStr[0], timeStr[1], timeStr[2]);

                    var tempDateBegin = $filter('date')(newDateBegin, "HH:mm");
                    var tempDateEnd = $filter('date')(newDateEnd, "HH:mm");
                    screening.scrBegin = tempDateBegin;
                    screening.scrEnd = tempDateEnd;
                })

                }, function() {
                    $window.location.replace('/403')}
            );

        }, function() {
                $window.location.replace('/403')}
        );
    }]);