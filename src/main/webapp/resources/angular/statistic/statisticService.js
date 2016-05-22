app.service('StatisticService', ['$http', '$q', '$log', function ($http, $q, $log) {
    this.getScreenings = function() {
        var deferred = $q.defer();
        $http.get('/screening/daily')
            .success(function (data) {
                deferred.resolve(data);
            }).error(function (data, status, headers, config) {
                $log.log(data, status, headers, config);
            });
        return deferred.promise;
    }
}]);