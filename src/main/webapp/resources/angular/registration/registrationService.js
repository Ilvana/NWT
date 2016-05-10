app.service('RegistrationService', ['$http', '$q', '$log', function ($http, $q, $log) {

    this.createNewUser = function (user) {

        var deferred = $q.defer();

        $http.post('/user', user, {  headers: { 'Content-Type': 'application/json' } }
            ).success(function () {
                deferred.resolve();
            }).error(function (data, status, headers, config) {
                $log.log(data, status, headers, config);
                deferred.reject();
            });

        return deferred.promise;
    };

}]);