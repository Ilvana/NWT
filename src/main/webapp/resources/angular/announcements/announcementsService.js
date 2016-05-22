app.service('AnnouncementsService', ['$http', '$q', '$log','$window', function ($http, $q, $log,$window) {

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

    this.showDetails = function (data) {

        var deferred = $q.defer();
        var that = this;
        $log.log("ilvana2");
        var oneEvent=data;
        $log.log("data"+data.name);
        $window.location.href = "/#/announcement";
        deferred.resolve(oneEvent);
        return deferred.promise;
    };
}]);