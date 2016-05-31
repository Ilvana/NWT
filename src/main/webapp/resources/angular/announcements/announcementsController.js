app.controller("AnnouncementsController", ['$scope','$filter', '$log', "AnnouncementsService","$http", "$window",
    function ($scope, $filter, $log, AnnouncementsService) {

        $scope.announcementTemplate = 'announcements';
        $scope.events = [];
        $scope.oneEvent = {
            'id': null,
            'name': '',
            'description': '',
            'timeBegin': '',
            'timeEnd': ''
        };
        $scope.totalItems = 1;
        AnnouncementsService.getAllEvents().then(function (data) {
            $scope.events = data;
            $scope.totalItems = $scope.events.length;
            angular.forEach($scope.events, function(event) {
                event.date = event.timeBegin.split(' ')[0];
                event.time = event.timeBegin.split(' ')[1];
            });
        });

        $scope.showDetails = function(event) {

            $scope.oneEvent=angular.copy(event);
            $scope.announcementTemplate = 'announcement';
        }
        $scope.currentPage = 1;
        $scope.itemsPerPage = 1;
        $scope.maxSize = 3; //Number of pager buttons to show

        $scope.setPage = function (pageNo) {
            $scope.currentPage = pageNo;
        };

        $scope.pageChanged = function() {
            console.log('Page changed to: ' + $scope.currentPage);
        };
    }]);