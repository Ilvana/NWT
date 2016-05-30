app.service('WeeklyService', ['$http', '$q', '$log', '$window', function ($http, $q, $log, $window) {

    this.getWeeklyMovies = function () {

        var deferred = $q.defer();

        $http.get('/movie/weekly')
            .success(function (data) {
                deferred.resolve(data);
            }).error(function (data, status, headers, config) {
                $log.log(data, status, headers, config);
                deferred.reject();
            });

        return deferred.promise;
    };

}]);