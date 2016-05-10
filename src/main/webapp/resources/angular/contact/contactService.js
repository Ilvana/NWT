app.service('ContactService', ['$http', '$q', '$log', '$window', function ($http, $q, $log, $window) {

    this.contactUs = function (contact) {

        var deferred = $q.defer();

        $http.post('/contactUs', contact, {  headers: { 'Content-Type': 'application/json' } }
        ).success(function () {
                deferred.resolve();
                $window.location.href = "/#/";
            }).error(function (data, status, headers, config) {
                $log.log(data, status, headers, config);
                deferred.reject();
            });

        return deferred.promise;
    };

}]);