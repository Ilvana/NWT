app.controller("StatisticController", ['$scope', '$log', 'StatisticService', '$window', '$interval',
    function ($scope, $log, StatisticService, $window, $interval) {

        var month = ["January", "February", "March", "April", "May", "June",
                        "July", "August", "September", "October", "November", "December"];

        $scope.loadScreeningsPerDayChart = function() {
            $scope.days = [];
            $scope.dailyNumber = [];
            StatisticService.getScreenings().then(function(data) {

                data.forEach(function(item){
                    $scope.days.push(item.date.split(' ')[0]);
                    $scope.dailyNumber.push(item.numberOfScreenings);
                });
                $scope.labels = $scope.days;
                $scope.series = ['Screenings per day'];
                $scope.data[0] = $scope.dailyNumber;
            });
        }

        $scope.loadScreeningsPerMonthChart = function() {
            $scope.months = [];
            $scope.monthlyNumber = [];
            StatisticService.getMonthlyScreenings().then(function(data) {
                data.forEach(function(item){
                    $scope.months.push(month[parseInt(item.date.split(' ')[0].split('-')[1])-1]);
                    $scope.monthlyNumber.push(item.numberOfScreenings);
                });
                $scope.labels = $scope.months;
                $scope.series = ['Screenings per month'];
                $scope.data[0] = $scope.monthlyNumber;
            });
        }

        $scope.loadTicketsPerDayChart = function() {
            $scope.days = [];
            $scope.dailyNumber = [];
            StatisticService.getTickets().then(function(data) {

                data.forEach(function(item){
                    $scope.days.push(item.date.split(' ')[0]);
                    $scope.dailyNumber.push(item.numberOfTickets);
                });
                $scope.labels = $scope.days;
                $scope.series = ['Tickets per day'];
                $scope.data[0] = $scope.dailyNumber;
            });
        }

        $scope.loadTicketsPerMonthChart = function() {
            $scope.months = [];
            $scope.monthlyNumber = [];
            StatisticService.getMonthlyTickets().then(function(data) {
                data.forEach(function(item){
                    $scope.months.push(month[parseInt(item.date.split(' ')[0].split('-')[1])-1]);
                    $scope.monthlyNumber.push(item.numberOfTickets);
                });
                $scope.labels = $scope.months;
                $scope.series = ['Tickets per month'];
                $scope.data[0] = $scope.monthlyNumber;
            });
        }

        $scope.templatePath="screeningsPerDay";

        $scope.series = ['Screenings per day'];
        $scope.data = [];

        $scope.loadChart = function(chart) {
            $interval.cancel($scope.interval);
            if(chart == 'screeningsPerDay') {
                $scope.templatePath='screeningsPerDay';
                $scope.loadScreeningsPerDayChart();
                $scope.interval = $interval($scope.loadScreeningsPerDayChart, 3000);
            } else if(chart == 'screeningsPerMonth') {
                $scope.templatePath='screeningsPerMonth';
                $scope.loadScreeningsPerMonthChart();
                $scope.interval = $interval($scope.loadScreeningsPerMonthChart, 3000);
            } else if(chart == 'ticketsPerDay') {
                $scope.templatePath='ticketsPerDay';
                $scope.loadTicketsPerDayChart();
                $scope.interval = $interval($scope.loadTicketsPerDayChart, 3000);
            } else if(chart == 'ticketsPerMonth') {
                $scope.templatePath='ticketsPerMonth';
                $scope.loadTicketsPerMonthChart();
                $scope.interval = $interval($scope.loadTicketsPerMonthChart, 3000);
            }

        }

        $scope.loadChart('screeningsPerDay');

}]);