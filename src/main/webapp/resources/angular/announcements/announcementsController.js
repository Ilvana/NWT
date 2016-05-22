app.controller("AnnouncementsController", ['$scope','$filter', '$log', "AnnouncementsService","$http", "$window",
    function ($scope, $filter, $log, AnnouncementsService) {

        $scope.events = [];
        $scope.date=null;
        $scope.time=null;
        $scope.oneEvent = {
            'id': null,
            'name': '',
            'description': '',
            'timeBegin': '',
            'timeEnd': ''
        };
        $log.log("ponovo");

        AnnouncementsService.getAllEvents().then(function (data) {
            $scope.events = data;
            $log.log($scope.events.length);
                angular.forEach($scope.events, function(event) {
                    $scope.date = event.timeBegin.split(' ')[0];
                    $scope.time = event.timeBegin.split(' ')[1];
                });
        });

        $scope.showDetails = function(event) {
            $log.log("ilvana1");
            $scope.oneEvent=angular.copy(event);
            $log.log($scope.oneEvent.name);
            $log.log("1111");
            AnnouncementsService.showDetails(event);
        }
    }]);