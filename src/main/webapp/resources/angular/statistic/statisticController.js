app.controller("StatisticController", ['$scope', '$log', 'StatisticService', '$window', '$interval',
    function ($scope, $log, StatisticService, $window, $interval) {
        $scope.series = ['Screenings per day'];
        $scope.data = [];
        $scope.onClick = function (points, evt) {
        console.log(points, evt);
        };
        $scope.days = [];
        $scope.dailyNumber = [];
        StatisticService.getScreenings().then(function(data) {
            data.forEach(function(item){
                $scope.days.push(item.date.split(' ')[0]);
                $scope.dailyNumber.push(item.numberOfScreenings);
            });
            $scope.labels = $scope.days;
            $scope.data[0] = $scope.dailyNumber;
        });

        $interval(function () {
            $scope.days = [];
            $scope.dailyNumber = [];
            StatisticService.getScreenings().then(function(data) {
                data.forEach(function(item){
                    $scope.days.push(item.date.split(' ')[0]);
                    $scope.dailyNumber.push(item.numberOfScreenings);
                });
                $scope.labels = $scope.days;
                $scope.data[0] = $scope.dailyNumber;
            });
        }, 3000);
}]);