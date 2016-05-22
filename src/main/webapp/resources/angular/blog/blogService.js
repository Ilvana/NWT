app.service('BlogService', ['$http', '$q', '$log', function ($http, $q, $log) {

    this.getNews = function () {

        var deferred = $q.defer();

        $http.get('/news', {  headers: { 'Content-Type': 'application/json' } }
            ).success(function () {
                deferred.resolve();
            }).error(function (data, status, headers, config) {
                $log.log(data, status, headers, config);
                deferred.reject();
            });

        return deferred.promise;
    };

}]);