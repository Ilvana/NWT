app.service('AnnouncementsService', ['$http', '$q', '$log','$window', function ($http, $q, $log,$window) {

    this.getAllEvents = function () {

        var deferred = $q.defer();

        var url = '/event';

        $http.get(url, {
            headers: {
                'Content-type': 'Application/json'
            }
        }).success(function (data) {

            deferred.resolve(data);

        }).error(function (data, status, headers, config) {
            $log.log(data, status, headers, config);
            deferred.reject();
        });

        return deferred.promise;
    };
}]);