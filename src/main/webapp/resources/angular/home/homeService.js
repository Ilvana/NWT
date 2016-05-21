app.service('HomeService', ['$http', '$q', '$log', function ($http, $q, $log) {

    this.getAllMovies = function () {

        var deferred = $q.defer();
        var that = this;

        var url = '/movie';

        $http.get(url, {
            headers: {
                'Content-type': 'Application/json'
            }
        }).success(function (data) {
            var movies = [];

            movies = data;

            deferred.resolve(movies);

        }).error(function (data, status, headers, config) {
            $log.log(data, status, headers, config);
            deferred.reject();
        });

        return deferred.promise;
    };

    this.getAllScreenings = function () {

        var deferred = $q.defer();
        var that = this;

        var url = '/screening';

        $http.get(url, {
            headers: {
                'Content-type': 'Application/json'
            }
        }).success(function (data) {
            var screenings = [];

            screenings = data;

            deferred.resolve(screenings);

        }).error(function (data, status, headers, config) {
            $log.log(data, status, headers, config);
            deferred.reject();
        });

        return deferred.promise;
    };


    this.createMovie = function() {
        var deferred = $q.defer();
        var movie = {
            'name':'Test Movie',
            'description':'Some description',
            'duration':10
        };

        $http.post('/movie', movie, {
            headers: {
                'Content-Type': 'application/json'
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