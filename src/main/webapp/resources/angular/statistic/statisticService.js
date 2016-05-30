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
    this.getMonthlyScreenings = function() {
        var deferred = $q.defer();
        $http.get('/screening/monthly')
            .success(function (data) {
                deferred.resolve(data);
            }).error(function (data, status, headers, config) {
                $log.log(data, status, headers, config);
            });
        return deferred.promise;
    }
    this.getEvents = function() {
        var deferred = $q.defer();
        $http.get('/event/daily')
            .success(function (data) {
                deferred.resolve(data);
            }).error(function (data, status, headers, config) {
                $log.log(data, status, headers, config);
            });
        return deferred.promise;
    }
    this.getMonthlyEvents = function() {
        var deferred = $q.defer();
        $http.get('/event/monthly')
            .success(function (data) {
                deferred.resolve(data);
            }).error(function (data, status, headers, config) {
                $log.log(data, status, headers, config);
            });
        return deferred.promise;
    }
    this.getTickets = function() {
        var deferred = $q.defer();
        $http.get('/ticket/daily')
            .success(function (data) {
                deferred.resolve(data);
            }).error(function (data, status, headers, config) {
            $log.log(data, status, headers, config);
        });
        return deferred.promise;
    }
    this.getMonthlyTickets = function() {
        var deferred = $q.defer();
        $http.get('/ticket/monthly')
            .success(function (data) {
                deferred.resolve(data);
            }).error(function (data, status, headers, config) {
            $log.log(data, status, headers, config);
        });
        return deferred.promise;
    }
}]);