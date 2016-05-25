app.service('ReserveTicketService', ['$http', '$q', '$log', function ($http, $q, $log) {

    this.getMovieById = function (movieId) {

        var deferred = $q.defer();

        var url = '/movie/' + movieId;

        $http.get(url, {
            headers: {
                'Content-type': 'Application/json'
            }
        }).success(function (data) {
            var movie = null;

            movie = data;

            deferred.resolve(movie);

        }).error(function (data, status, headers, config) {
            $log.log(data, status, headers, config);
            deferred.reject();
        });

        return deferred.promise;
    };

    this.createNewTicket = function (ticket) {

        var deferred = $q.defer();

        $http.post('/ticket', ticket, {  headers: { 'Content-Type': 'application/json' } }
        ).success(function () {
            deferred.resolve();
        }).error(function (data, status, headers, config) {
            $log.log(data, status, headers, config);
            deferred.reject();
        });

        return deferred.promise;
    };

    this.getUserLogged = function () {

        var deferred = $q.defer();

        var url = '/user/logged';

        $http.get(url).success(function (data) {
            var status = null;

            status = data;

            deferred.resolve(status);

        }).error(function (data, status, headers, config) {
            $log.log(data, status, headers, config);
            deferred.reject();
        });

        return deferred.promise;
    };

    this.getAllTickets = function () {

        var deferred = $q.defer();

        var url = '/ticket';

        $http.get(url, {
            headers: {
                'Content-type': 'Application/json'
            }
        }).success(function (data) {
            var tickets = [];

            tickets = data;

            deferred.resolve(tickets);

        }).error(function (data, status, headers, config) {
            $log.log(data, status, headers, config);
            deferred.reject();
        });

        return deferred.promise;
    };
}]);