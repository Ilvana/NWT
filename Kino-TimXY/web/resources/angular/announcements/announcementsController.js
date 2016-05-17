app.controller("AnnouncementsController", ['$scope','$filter', '$log', "AnnouncementsService", "$http", "$window",
    function ($scope, $filter, $log, AnnouncementsService, $http, $window) {

        $scope.events = [];
        $scope.date=null;
        $scope.time=null;

        AnnouncementsService.getAllEvents().then(function (data) {
            $scope.events = data;
            $log.log($scope.events.length);
                angular.forEach($scope.events, function(event) {
                    $scope.date = event.timeBegin.split(' ')[0];
                    $scope.time = event.timeBegin.split(' ')[1];
                });
        });
    }]);