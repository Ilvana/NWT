app.controller("HomeController", ['$scope','$filter', '$log', "HomeService", "$http", "$window",
    function ($scope, $filter, $log, HomeService, $http, $window) {

        $scope.movies = [];
        HomeService.getAllMovies().then(function (data) {
            $scope.movies = data;
            $scope.extraData = [];

            for (var i=0; i<$scope.movies.length; i++) {
                (function(i) {
                    $scope.search = $scope.movies[i].name;
                    $scope.details = "";

                    $http.get("http://www.omdbapi.com/?t=" + $scope.search + "&tomatoes=true&plot=full")
                        .then(function (response) {
                            $scope.details = response.data;
                            $scope.movies[i].imdb = $scope.details.imdbRating;
                            $scope.movies[i].tomatoes = $scope.details.tomatoRating;
                            $scope.movies[i].poster = $scope.details.Poster;
                        });
                })(i);
            }
        }, function() {
            $window.location.replace('/403')}
            );
    }]);