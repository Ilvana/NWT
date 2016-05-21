app.service('AdminService', ['$http', '$q', '$log', function ($http, $q, $log) {

    this.getUsers = function () {
        var deferred = $q.defer();
        $http.get('/user')
            .success(function (data) {
                deferred.resolve(data);
            }).error(function (data, status, headers, config) {
                $log.log(data, status, headers, config);
            });
        return deferred.promise;
    };

}]);