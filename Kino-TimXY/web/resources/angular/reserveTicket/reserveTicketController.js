app.controller("ReserveTicketController", ['$scope', '$routeParams',  '$log', '$window', '$http',
    function ($scope, $routeParams, $log, $window, $http) {

        $scope.movieId = $routeParams.movieId;
        $log.log($scope.movieId);
    }]);