app.controller("ReserveTicketController", ['$scope', '$routeParams', '$filter', '$log', '$window', '$http', 'ReserveTicketService', 'HomeService',
    function ($scope, $routeParams, $filter, $log, $window, $http, ReserveTicketService, HomeService) {


        $scope.tempRes = "";
        $scope.changeReservation = function(seatX,seatY,status) {
            //$log.log(seatX + "," + seatY + "," + status);
            $scope.tempRes = seatX + "," + seatY + "," + status;
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
        $scope.seatsX = [];
        $scope.seatsY = [];

        $scope.rowSigns = ["A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W", "X", "Y", "Z"];
        //$log.log($scope.movieId);

        function getTheaterAndTicketsForScreening(screening) {
            $scope.seatsX = [];
            $scope.seatsY = [];
            $scope.currentTickets = [];
            $scope.seatsSchedule = [];
            $scope.currentTheater = screening.theater;
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
                    if(screening.movie.id == movieId) {
                        $scope.screenings.push(screening);
                        //sorting needed here
                    }
                });
                getTheaterAndTicketsForScreening($scope.screenings[0]);
                angular.forEach($scope.screenings, function(screening) {
                    var newDateBegin =new Date(screening.timeBegin);
                    var newDateEnd =new Date(screening.timeEnd);
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