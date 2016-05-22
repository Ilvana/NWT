app.controller("AnnouncementsController", ['$scope','$filter', '$log', "AnnouncementsService","$http", "$window",
    function ($scope, $filter, $log, AnnouncementsService, $http, $window) {

        $scope.announcementTemplate = 'announcements';
        $scope.events = [];
        $scope.oneEvent = {
            'id': null,
            'name': '',
            'description': '',
            'timeBegin': '',
            'timeEnd': ''
        };

        AnnouncementsService.getAllEvents().then(function (data) {
            $scope.events = data;
            angular.forEach($scope.events, function(event) {
                event.date = event.timeBegin.split(' ')[0];
                event.time = event.timeBegin.split(' ')[1];
            });
        });

        $scope.showDetails = function(event) {

            $scope.oneEvent=angular.copy(event);
            $scope.announcementTemplate = 'announcement';
        }
    }]);