app.controller("ReserveTicketController", ['$scope', '$routeParams', '$log', '$window', '$http', 'ReserveTicketService', 'HomeService',
    function ($scope, $routeParams, $log, $window, $http, ReserveTicketService, HomeService) {

        movieId = $routeParams.movieId;
        $scope.movie = null;
        $scope.screenings = [];
        $scope.currentTheater = null;
        $scope.currentTickets = [];
        //$log.log($scope.movieId);

        function getTheaterAndTicketsForScreening(screening) {
            $scope.currentTheater = screening.theater;
            ReserveTicketService.getAllTickets().then(function(data) {
                    var localTickets = data;
                    angular.forEach(localTickets, function(ticket) {
                        if(ticket.screening.id == screening.id) {
                            $scope.currentTickets.push(ticket);
                            $log.log(ticket.id);
                        }
                    })


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

                }, function() {
                    $window.location.replace('/403')}
            );

        }, function() {
                $window.location.replace('/403')}
        );
    }]);