app.service('AnnouncementsService', ['$http', '$q', '$log', function ($http, $q, $log) {

    this.getAllEvents = function () {

        var deferred = $q.defer();
        var that = this;

        var url = '/event';

        $http.get(url, {
            headers: {
                'Content-type': 'Application/json'
            }
        }).success(function (data) {
            var events = [];

            events= data;

            deferred.resolve(events);

        }).error(function (data, status, headers, config) {
            $log.log(data, status, headers, config);
            deferred.reject();
        });

        return deferred.promise;
    };
}]);