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
    this.getUser = function (id) {
        var deferred = $q.defer();
        $http.get('/user/'+id)
            .success(function (data) {
                deferred.resolve(data);
            }).error(function (data, status, headers, config) {
                $log.log(data, status, headers, config);
            });
        return deferred.promise;
    };

    this.deleteUser = function (item) {
        var deferred = $q.defer();
        $http.delete('/user/' + item.id).success(function (data) {
                deferred.resolve();
            }).error(function (data, status, headers, config) {
                $log.log(data, status, headers, config);
            });
        return deferred.promise;
    };
    this.createUser = function (user) {

        var deferred = $q.defer();

        var userToSend = angular.copy(user);

        userToSend.role = userToSend.admin ? 'ROLE_ADMIN' : 'ROLE_USER';

        $http.post('/user', userToSend).success(function () {
                deferred.resolve();
            }).error(function (data, status, headers, config) {
                $log.log(data, status, headers, config);
                deferred.reject();
            });

        return deferred.promise;
    };
    this.updateUser = function (user) {

        var deferred = $q.defer();

        var userToSend = angular.copy(user);

        userToSend.role = userToSend.admin ? 'ROLE_ADMIN' : 'ROLE_USER';

        $http.put('/user/'+userToSend.id, userToSend).success(function () {
                deferred.resolve();
            }).error(function (data, status, headers, config) {
                $log.log(data, status, headers, config);
                deferred.reject();
            });

        return deferred.promise;
    };


}]);